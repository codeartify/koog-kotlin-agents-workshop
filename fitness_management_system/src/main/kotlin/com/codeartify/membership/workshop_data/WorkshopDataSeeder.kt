package com.codeartify.membership.workshop_data

import com.codeartify.membership.customer_cache.CustomerCacheRepository
import com.codeartify.membership.customer_cache.CustomerEntity
import com.codeartify.membership.managing_memberships.domain.CustomerId
import com.codeartify.membership.managing_memberships.domain.MembershipId
import com.codeartify.membership.managing_memberships.domain.commands.ActivateMembershipCommand
import com.codeartify.membership.managing_memberships.domain.commands.CancelMembershipCommand
import com.codeartify.membership.managing_memberships.domain.commands.PauseMembershipCommand
import com.codeartify.membership.managing_memberships.domain.commands.ReactivateMembershipCommand
import com.codeartify.membership.managing_memberships.domain.commands.ResumeMembershipCommand
import com.codeartify.membership.managing_memberships.domain.commands.SuspendMembershipCommand
import com.codeartify.membership.managing_memberships.domain.values.CustomerEligibility
import com.codeartify.membership.managing_memberships.domain.values.Duration
import com.codeartify.membership.managing_memberships.domain.values.PausePeriod
import com.codeartify.membership.managing_memberships.domain.values.PlanReferenceId
import com.codeartify.membership.managing_memberships.domain.values.PlanTerms
import com.codeartify.membership.managing_memberships.domain.values.Price
import com.codeartify.membership.managing_memberships.use_case.query_memberships.MembershipRepository
import com.codeartify.membership.managing_plans.data_access.PlanRepository
import com.codeartify.membership.managing_plans.domain.Plan
import com.codeartify.membership.managing_plans.domain.PlanDescription
import com.codeartify.membership.managing_plans.domain.PlanDuration
import com.codeartify.membership.managing_plans.domain.PlanId
import com.codeartify.membership.managing_plans.domain.PlanPrice
import com.codeartify.membership.managing_plans.domain.PlanTitle
import org.axonframework.eventsourcing.eventstore.EventStorageEngine
import org.axonframework.eventsourcing.eventstore.SourcingCondition
import org.axonframework.messaging.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.eventhandling.TerminalEventMessage
import org.axonframework.messaging.eventstreaming.EventCriteria
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(
    prefix = "workshop.seed",
    name = ["enabled"],
    havingValue = "true",
    matchIfMissing = true
)
class WorkshopDataSeeder(
    private val customerRepository: CustomerCacheRepository,
    private val planRepository: PlanRepository,
    private val membershipRepository: MembershipRepository,
    private val commandGateway: CommandGateway,
    private val eventStorageEngine: EventStorageEngine
) : ApplicationRunner {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun run(args: ApplicationArguments) {
        seedPlans()
        seedCustomers()

        val seededMemberships = WorkshopSeedCatalog.memberships.count { seedMembershipIfMissing(it) }
        log.info(
            "Workshop data ready: {} plans, {} customers, {} new memberships ({} total fixture memberships)",
            WorkshopSeedCatalog.plans.size,
            WorkshopSeedCatalog.memberships.size,
            seededMemberships,
            WorkshopSeedCatalog.memberships.size
        )
    }

    private fun seedPlans() {
        WorkshopSeedCatalog.plans
            .filterNot { planRepository.existsById(it.id) }
            .map { seed ->
                Plan.create(
                    planId = PlanId.of(seed.id),
                    title = PlanTitle.of(seed.title),
                    description = PlanDescription.of(seed.description),
                    price = PlanPrice.of(seed.price),
                    duration = PlanDuration.of(seed.durationInMonths)
                )
            }
            .takeIf { it.isNotEmpty() }
            ?.let(planRepository::saveAll)
    }

    private fun seedCustomers() {
        WorkshopSeedCatalog.memberships
            .filterNot { customerRepository.existsById(it.customerId) }
            .map {
                CustomerEntity(
                    id = it.customerId,
                    name = it.customerName,
                    email = it.customerEmail,
                    dateOfBirth = it.customerDateOfBirth
                )
            }
            .takeIf { it.isNotEmpty() }
            ?.let(customerRepository::saveAll)
    }

    private fun seedMembershipIfMissing(seed: WorkshopMembershipSeed): Boolean {
        if (membershipRepository.existsById(seed.membershipId) || hasStoredEvents(seed.membershipId)) {
            return false
        }

        val membershipId = MembershipId.of(seed.membershipId)
        commandGateway.sendAndWait(
            ActivateMembershipCommand(
                membershipId = membershipId,
                customerId = CustomerId.of(seed.customerId),
                planTerms = PlanTerms(
                    planReferenceId = PlanReferenceId.of(seed.plan.id),
                    duration = Duration.of(seed.plan.durationInMonths),
                    price = Price.of(seed.plan.price)
                ),
                customerEligibility = CustomerEligibility(
                    dateOfBirth = seed.customerDateOfBirth,
                    guardianSignaturePresent = false
                )
            )
        )

        seed.lifecycle.forEach { step ->
            commandGateway.sendAndWait(step.toCommand(membershipId))
        }

        log.info(
            "Seeded workshop membership {} for {} with expected status {}",
            seed.membershipId,
            seed.customerName,
            seed.expectedStatus
        )
        return true
    }

    private fun hasStoredEvents(membershipId: String): Boolean {
        val stream = eventStorageEngine.source(
            SourcingCondition.conditionFor(
                EventCriteria.havingTags("Membership", membershipId)
            )
        )

        return try {
            stream
                .filter { entry -> entry.message() !is TerminalEventMessage }
                .first()
                .asCompletableFuture()
                .join() != null
        } finally {
            stream.close()
        }
    }

    private fun WorkshopLifecycleStep.toCommand(membershipId: MembershipId): Any = when (this) {
        is Pause -> PauseMembershipCommand(membershipId, PausePeriod.from(durationInDays))
        Resume -> ResumeMembershipCommand(membershipId)
        Suspend -> SuspendMembershipCommand(membershipId)
        Reactivate -> ReactivateMembershipCommand(membershipId)
        Cancel -> CancelMembershipCommand(membershipId)
    }
}
