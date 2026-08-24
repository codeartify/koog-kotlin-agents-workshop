package com.codeartify.membership.staff_assistant

import com.codeartify.membership.managing_memberships.domain.values.MembershipStatus
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AssessMembershipCaseTest {
    private val activeMembership = MembershipSnapshot(
        id = "membership-1",
        customerId = "customer-1",
        planId = "plan-1",
        status = MembershipStatus.ACTIVE
    )

    @Test
    fun `keeps an allowed proposal and known evidence`() = runBlocking {
        val useCase = useCaseFor(
            AgentAssessmentDraft(
                membershipId = activeMembership.id,
                summary = "The active membership may be paused.",
                evidenceReferences = listOf("membership-event:1"),
                proposedAction = MembershipAction.PAUSE
            ),
            knownEvidence = setOf("membership-event:1")
        )

        val response = useCase.execute("conversation-1", "Can we pause it?")

        assertEquals(MembershipAction.PAUSE, response.assessment.proposedAction)
        assertEquals(listOf("membership-event:1"), response.assessment.relevantEvidence)
        assertTrue(response.assessment.requiresHumanConfirmation)
    }

    @Test
    fun `removes a forbidden proposal and exposes a warning`() = runBlocking {
        val useCase = useCaseFor(
            AgentAssessmentDraft(
                membershipId = activeMembership.id,
                summary = "The membership could be reactivated.",
                proposedAction = MembershipAction.REACTIVATE
            )
        )

        val response = useCase.execute("conversation-1", "Reactivate it")

        assertEquals(null, response.assessment.proposedAction)
        assertTrue(response.assessment.warnings.any { "not allowed" in it })
    }

    @Test
    fun `removes invented evidence`() = runBlocking {
        val useCase = useCaseFor(
            AgentAssessmentDraft(
                membershipId = activeMembership.id,
                summary = "A lifecycle event explains the case.",
                evidenceReferences = listOf("membership-event:invented")
            )
        )

        val response = useCase.execute("conversation-1", "What happened?")

        assertTrue(response.assessment.relevantEvidence.isEmpty())
        assertTrue(response.assessment.warnings.any { "unknown evidence" in it })
    }

    private fun useCaseFor(
        draft: AgentAssessmentDraft,
        knownEvidence: Set<String> = emptySet()
    ): AssessMembershipCase = AssessMembershipCase(
        agent = FakeMembershipStaffAgent(draft),
        context = FakeMembershipCaseContext(activeMembership, knownEvidence),
        validator = MembershipProposalValidator(MembershipActionPolicy())
    )
}

private class FakeMembershipStaffAgent(
    private val draft: AgentAssessmentDraft
) : MembershipStaffAgent {
    override suspend fun run(message: String, conversationId: String): AgentRun =
        AgentRun(draft, emptyList())
}

private class FakeMembershipCaseContext(
    private val membership: MembershipSnapshot,
    private val knownEvidence: Set<String>
) : MembershipCaseContext {
    override fun membershipSnapshot(membershipId: String?): MembershipSnapshot? =
        membership.takeIf { it.id == membershipId }

    override fun evidenceReferences(membershipId: String?): Set<String> =
        knownEvidence.takeIf { membership.id == membershipId }.orEmpty()
}
