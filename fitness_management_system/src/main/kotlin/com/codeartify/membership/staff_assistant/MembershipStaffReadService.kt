package com.codeartify.membership.staff_assistant

import com.codeartify.membership.billing.InvoiceRepository
import com.codeartify.membership.customer_cache.CustomerCacheRepository
import com.codeartify.membership.managing_memberships.domain.values.MembershipStatus
import com.codeartify.membership.managing_memberships.use_case.query_memberships.MembershipEntity
import com.codeartify.membership.managing_memberships.use_case.query_memberships.MembershipRepository
import com.codeartify.membership.managing_plans.data_access.PlanRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import java.time.LocalDate

@Service
@Transactional(readOnly = true)
class MembershipStaffReadService(
    private val customerRepository: CustomerCacheRepository,
    private val membershipRepository: MembershipRepository,
    private val historyRepository: MembershipHistoryRepository,
    private val invoiceRepository: InvoiceRepository,
    private val planRepository: PlanRepository,
    private val actionPolicy: MembershipActionPolicy
) : MembershipCaseContext {
    fun searchCustomers(query: String): List<CustomerToolResult> =
        customerRepository.searchByNameOrEmail(query.trim())
            .take(10)
            .map { CustomerToolResult(it.id, it.name, it.email, it.dateOfBirth) }

    fun membershipsForCustomer(customerId: String): List<MembershipToolResult> =
        membershipRepository.findByCustomerIdOrderById(customerId)
            .map { it.toToolResult() }

    fun membership(membershipId: String): MembershipToolResult? =
        membershipRepository.findById(membershipId)
            .map { it.toToolResult() }
            .orElse(null)

    override fun membershipSnapshot(membershipId: String?): MembershipSnapshot? = membershipId
        ?.let { membershipRepository.findById(it).orElse(null) }
        ?.let {
            MembershipSnapshot(
                id = it.id,
                customerId = it.customerId,
                planId = it.planId,
                status = MembershipStatus.valueOf(it.status)
            )
        }

    fun history(membershipId: String): List<MembershipHistoryToolResult> =
        historyRepository.findByMembershipIdOrderByIdAsc(membershipId)
            .map {
                MembershipHistoryToolResult(
                    evidenceReference = it.evidenceReference(),
                    eventType = it.eventType,
                    occurredAt = it.occurredAt,
                    details = it.details
                )
            }

    override fun evidenceReferences(membershipId: String?): Set<String> = membershipId
        ?.let { history(it).mapTo(linkedSetOf()) { entry -> entry.evidenceReference } }
        .orEmpty()

    fun invoices(membershipId: String): List<InvoiceToolResult> =
        invoiceRepository.findByMembershipIdOrderByDueDateDesc(membershipId)
            .map { InvoiceToolResult(it.id, it.amount, it.dueDate, it.state.name) }

    fun plan(planId: String): PlanToolResult? = planRepository.findById(planId)
        .map {
            PlanToolResult(
                id = it.id,
                title = it.title.value,
                description = it.description.value,
                price = it.price.value,
                durationInMonths = it.duration.value
            )
        }
        .orElse(null)

    fun allowedActions(membershipId: String): List<MembershipAction> =
        membershipSnapshot(membershipId)
            ?.let { actionPolicy.allowedFor(it.status) }
            .orEmpty()

    private fun MembershipEntity.toToolResult(): MembershipToolResult = MembershipToolResult(
        id = id,
        customerId = customerId,
        planId = planId,
        status = status,
        pauseStartDate = pauseStartDate,
        pauseEndDate = pauseEndDate
    )
}

data class CustomerToolResult(
    val id: String,
    val name: String,
    val email: String,
    val dateOfBirth: LocalDate
)

data class MembershipToolResult(
    val id: String,
    val customerId: String,
    val planId: String,
    val status: String,
    val pauseStartDate: LocalDate?,
    val pauseEndDate: LocalDate?
)

data class MembershipHistoryToolResult(
    val evidenceReference: String,
    val eventType: String,
    val occurredAt: Instant,
    val details: String
)

data class InvoiceToolResult(
    val id: String,
    val amount: Int,
    val dueDate: LocalDate,
    val state: String
)

data class PlanToolResult(
    val id: String,
    val title: String,
    val description: String,
    val price: Int,
    val durationInMonths: Int
)
