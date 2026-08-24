package com.codeartify.membership.staff_assistant

import com.codeartify.membership.managing_memberships.domain.events.MembershipActivatedEvent
import com.codeartify.membership.managing_memberships.domain.events.MembershipCancelledEvent
import com.codeartify.membership.managing_memberships.domain.events.MembershipPausedEvent
import com.codeartify.membership.managing_memberships.domain.events.MembershipReactivatedEvent
import com.codeartify.membership.managing_memberships.domain.events.MembershipResumedEvent
import com.codeartify.membership.managing_memberships.domain.events.MembershipSuspendedEvent
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.axonframework.messaging.eventhandling.annotation.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import java.time.Instant

@Entity
@Table(name = "membership_history")
class MembershipHistoryEntry() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var membershipId: String = ""
    var eventType: String = ""
    var occurredAt: Instant = Instant.EPOCH
    var details: String = ""

    constructor(membershipId: String, eventType: String, details: String) : this() {
        this.membershipId = membershipId
        this.eventType = eventType
        this.occurredAt = Instant.now()
        this.details = details
    }

    fun evidenceReference(): String = "membership-event:$id"
}

interface MembershipHistoryRepository : JpaRepository<MembershipHistoryEntry, Long> {
    fun findByMembershipIdOrderByIdAsc(membershipId: String): List<MembershipHistoryEntry>
}

@Component
class MembershipHistoryProjection(
    private val repository: MembershipHistoryRepository
) {
    @EventHandler
    fun on(event: MembershipActivatedEvent) = record(
        membershipId = event.membershipId.value,
        eventType = "MEMBERSHIP_ACTIVATED",
        details = "Membership activated for plan ${event.planTerms.planReferenceId.value}."
    )

    @EventHandler
    fun on(event: MembershipPausedEvent) = record(
        membershipId = event.membershipId.value,
        eventType = "MEMBERSHIP_PAUSED",
        details = "Paused from ${event.pausePeriod.startDate} until ${event.pausePeriod.endDate}."
    )

    @EventHandler
    fun on(event: MembershipResumedEvent) = record(
        membershipId = event.membershipId.value,
        eventType = "MEMBERSHIP_RESUMED",
        details = "Membership resumed."
    )

    @EventHandler
    fun on(event: MembershipSuspendedEvent) = record(
        membershipId = event.membershipId.value,
        eventType = "MEMBERSHIP_SUSPENDED",
        details = "Membership suspended."
    )

    @EventHandler
    fun on(event: MembershipReactivatedEvent) = record(
        membershipId = event.membershipId.value,
        eventType = "MEMBERSHIP_REACTIVATED",
        details = "Membership reactivated."
    )

    @EventHandler
    fun on(event: MembershipCancelledEvent) = record(
        membershipId = event.membershipId.value,
        eventType = "MEMBERSHIP_CANCELLED",
        details = "Membership cancelled."
    )

    private fun record(membershipId: String, eventType: String, details: String) {
        repository.save(MembershipHistoryEntry(membershipId, eventType, details))
    }
}
