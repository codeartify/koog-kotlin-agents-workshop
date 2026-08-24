package com.codeartify.membership.staff_assistant

import com.codeartify.membership.managing_memberships.domain.values.MembershipStatus
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class MembershipProposalValidatorTest {

    private val validator = MembershipProposalValidator(MembershipActionPolicy())

    @Test
    fun `keeps a proposal that is allowed by the current membership state`() {
        val draft = AgentAssessmentDraft(
            membershipId = "membership-42",
            summary = "The member asked for a temporary pause.",
            evidenceReferences = listOf("membership-event:1"),
            proposedAction = MembershipAction.PAUSE
        )

        val assessment = validator.validate(
            draft = draft,
            membership = MembershipSnapshot("membership-42", "customer-7", "plan-1", MembershipStatus.ACTIVE),
            knownEvidenceReferences = setOf("membership-event:1")
        )

        assertEquals(MembershipAction.PAUSE, assessment.proposedAction)
        assertEquals(listOf(MembershipAction.PAUSE, MembershipAction.CANCEL), assessment.possibleActions)
        assertTrue(assessment.requiresHumanConfirmation)
        assertTrue(assessment.warnings.isEmpty())
    }

    @Test
    fun `removes an action that the current membership state does not allow`() {
        val draft = AgentAssessmentDraft(
            membershipId = "membership-42",
            summary = "The membership is already cancelled.",
            proposedAction = MembershipAction.REACTIVATE
        )

        val assessment = validator.validate(
            draft = draft,
            membership = MembershipSnapshot("membership-42", "customer-7", "plan-1", MembershipStatus.CANCELLED),
            knownEvidenceReferences = emptySet()
        )

        assertNull(assessment.proposedAction)
        assertTrue(assessment.possibleActions.isEmpty())
        assertTrue(assessment.warnings.any { it.contains("not allowed") })
    }

    @Test
    fun `removes evidence references that are not present in the history projection`() {
        val draft = AgentAssessmentDraft(
            membershipId = "membership-42",
            summary = "The member paused and later resumed.",
            evidenceReferences = listOf("membership-event:1", "membership-event:999")
        )

        val assessment = validator.validate(
            draft = draft,
            membership = MembershipSnapshot("membership-42", "customer-7", "plan-1", MembershipStatus.ACTIVE),
            knownEvidenceReferences = setOf("membership-event:1")
        )

        assertEquals(listOf("membership-event:1"), assessment.relevantEvidence)
        assertTrue(assessment.warnings.any { it.contains("unknown evidence") })
    }

    @Test
    fun `returns no possible action when the referenced membership does not exist`() {
        val draft = AgentAssessmentDraft(
            membershipId = "invented-membership",
            summary = "No authoritative membership could be found.",
            proposedAction = MembershipAction.CANCEL
        )

        val assessment = validator.validate(
            draft = draft,
            membership = null,
            knownEvidenceReferences = emptySet()
        )

        assertNull(assessment.currentStatus)
        assertNull(assessment.proposedAction)
        assertTrue(assessment.possibleActions.isEmpty())
        assertTrue(assessment.warnings.any { it.contains("could not be verified") })
    }
}
