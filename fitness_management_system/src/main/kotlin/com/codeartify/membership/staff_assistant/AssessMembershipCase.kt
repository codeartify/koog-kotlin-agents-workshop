package com.codeartify.membership.staff_assistant

import org.springframework.stereotype.Service

interface MembershipStaffAgent {
    suspend fun run(message: String, conversationId: String): AgentRun
}

interface MembershipCaseContext {
    fun membershipSnapshot(membershipId: String?): MembershipSnapshot?
    fun evidenceReferences(membershipId: String?): Set<String>
}

@Service
class AssessMembershipCase(
    private val agent: MembershipStaffAgent,
    private val context: MembershipCaseContext,
    private val validator: MembershipProposalValidator
) {
    suspend fun execute(conversationId: String, message: String): StaffAssistantMessageResponse {
        val run = agent.run(message, conversationId)
        val membership = context.membershipSnapshot(run.draft.membershipId)
        val evidence = context.evidenceReferences(run.draft.membershipId)
        val assessment = validator.validate(run.draft, membership, evidence)

        return StaffAssistantMessageResponse(
            conversationId = conversationId,
            assessment = assessment,
            trace = run.trace
        )
    }
}
