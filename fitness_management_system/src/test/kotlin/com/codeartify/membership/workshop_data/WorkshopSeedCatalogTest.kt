package com.codeartify.membership.workshop_data

import com.codeartify.membership.managing_memberships.domain.values.MembershipStatus
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class WorkshopSeedCatalogTest {
    @Test
    fun `catalog contains varied memberships with unique identifiers`() {
        val memberships = WorkshopSeedCatalog.memberships

        assertEquals(12, memberships.size)
        assertEquals(memberships.size, memberships.map { it.membershipId }.toSet().size)
        assertEquals(memberships.size, memberships.map { it.customerId }.toSet().size)
        assertEquals(MembershipStatus.entries.toSet(), memberships.map { it.expectedStatus }.toSet())
        assertTrue(memberships.all { it.plan in WorkshopSeedCatalog.plans })
    }

    @Test
    fun `Maya has a suspended case with at least three lifecycle events`() {
        val maya = WorkshopSeedCatalog.memberships.single { it.customerName == "Maya Example" }

        assertEquals(MembershipStatus.SUSPENDED, maya.expectedStatus)
        assertEquals(listOf(Pause(30), Resume, Suspend), maya.lifecycle)
        assertTrue(maya.lifecycle.size >= 3)
    }

    @Test
    fun `declared status matches the lifecycle sequence`() {
        WorkshopSeedCatalog.memberships.forEach { membership ->
            assertEquals(
                membership.expectedStatus,
                membership.lifecycle.fold(MembershipStatus.ACTIVE, ::apply),
                "Unexpected final status for ${membership.customerName}"
            )
        }
    }

    private fun apply(status: MembershipStatus, step: WorkshopLifecycleStep): MembershipStatus = when (step) {
        is Pause -> {
            assertEquals(MembershipStatus.ACTIVE, status)
            MembershipStatus.PAUSED
        }

        Resume -> {
            assertEquals(MembershipStatus.PAUSED, status)
            MembershipStatus.ACTIVE
        }

        Suspend -> {
            assertEquals(MembershipStatus.ACTIVE, status)
            MembershipStatus.SUSPENDED
        }

        Reactivate -> {
            assertEquals(MembershipStatus.SUSPENDED, status)
            MembershipStatus.ACTIVE
        }

        Cancel -> {
            assertTrue(status != MembershipStatus.CANCELLED)
            MembershipStatus.CANCELLED
        }
    }
}
