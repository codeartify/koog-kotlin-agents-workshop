package com.codeartify.membership.staff_assistant

import com.codeartify.membership.managing_memberships.domain.values.MembershipStatus
import org.springframework.stereotype.Component

@Component
class MembershipActionPolicy {
    fun allowedFor(status: MembershipStatus): List<MembershipAction> = when (status) {
        MembershipStatus.ACTIVE -> listOf(MembershipAction.PAUSE, MembershipAction.CANCEL)
        MembershipStatus.PAUSED -> listOf(MembershipAction.RESUME, MembershipAction.CANCEL)
        MembershipStatus.SUSPENDED -> listOf(MembershipAction.REACTIVATE, MembershipAction.CANCEL)
        MembershipStatus.CANCELLED -> emptyList()
    }
}

@Component
class MembershipProposalValidator(
    private val actionPolicy: MembershipActionPolicy
) {
    fun validate(
        draft: AgentAssessmentDraft,
        membership: MembershipSnapshot?,
        knownEvidenceReferences: Set<String>
    ): MembershipCaseAssessment {
        val warnings = mutableListOf<String>()

        if (membership == null && draft.membershipId != null) {
            warnings += "The membership referenced by the agent could not be verified."
        }

        val possibleActions = membership?.let { actionPolicy.allowedFor(it.status) }.orEmpty()
        val proposedAction = draft.proposedAction?.takeIf { proposal ->
            val allowed = proposal in possibleActions
            if (!allowed) {
                warnings += "The proposed action $proposal is not allowed by the current membership state."
            }
            allowed
        }

        val relevantEvidence = draft.evidenceReferences.filter { it in knownEvidenceReferences }
        if (relevantEvidence.size != draft.evidenceReferences.size) {
            warnings += "The agent referenced unknown evidence; unsupported references were removed."
        }

        return MembershipCaseAssessment(
            membershipId = membership?.id ?: draft.membershipId,
            currentStatus = membership?.status,
            summary = draft.summary,
            relevantEvidence = relevantEvidence,
            possibleActions = possibleActions,
            proposedAction = proposedAction,
            requiresHumanConfirmation = true,
            warnings = warnings
        )
    }
}
