package com.codeartify.membership.staff_assistant

import com.codeartify.membership.managing_memberships.domain.values.MembershipStatus

data class StaffAssistantMessageRequest(
    val message: String
) {
    init {
        require(message.isNotBlank()) { "Message must not be blank" }
    }
}

data class StaffAssistantMessageResponse(
    val conversationId: String,
    val assessment: MembershipCaseAssessment,
    val trace: List<ToolCallTrace>
)

data class AgentAssessmentDraft(
    val membershipId: String? = null,
    val summary: String,
    val evidenceReferences: List<String> = emptyList(),
    val proposedAction: MembershipAction? = null
)

data class MembershipCaseAssessment(
    val membershipId: String?,
    val currentStatus: MembershipStatus?,
    val summary: String,
    val relevantEvidence: List<String>,
    val possibleActions: List<MembershipAction>,
    val proposedAction: MembershipAction?,
    val requiresHumanConfirmation: Boolean,
    val warnings: List<String>
)

data class MembershipSnapshot(
    val id: String,
    val customerId: String,
    val planId: String,
    val status: MembershipStatus
)

enum class MembershipAction {
    PAUSE,
    RESUME,
    REACTIVATE,
    CANCEL
}

data class ToolCallTrace(
    val tool: String,
    val result: String
)

data class AgentRun(
    val draft: AgentAssessmentDraft,
    val trace: List<ToolCallTrace>
)

class AgentNotConfiguredException : RuntimeException(
    "No LLM provider is configured. Set GOOGLE_API_KEY and enable the Google Koog provider."
)

class ExerciseNotCompletedException(message: String) : RuntimeException(message)
