package com.codeartify.membership.staff_assistant

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/staff-assistant")
class StaffAssistantController(
    private val agent: KoogMembershipStaffAgent,
    private val readService: MembershipStaffReadService,
    private val validator: MembershipProposalValidator
) {
    @PostMapping("/conversations/{conversationId}/messages")
    suspend fun sendMessage(
        @PathVariable conversationId: String,
        @RequestBody request: StaffAssistantMessageRequest
    ): ResponseEntity<StaffAssistantMessageResponse> {
        val run = agent.run(request.message, conversationId)
        val membership = readService.membershipSnapshot(run.draft.membershipId)
        val evidence = readService.evidenceReferences(run.draft.membershipId)
        val assessment = validator.validate(run.draft, membership, evidence)

        return ResponseEntity.ok(
            StaffAssistantMessageResponse(
                conversationId = conversationId,
                assessment = assessment,
                trace = run.trace
            )
        )
    }
}
