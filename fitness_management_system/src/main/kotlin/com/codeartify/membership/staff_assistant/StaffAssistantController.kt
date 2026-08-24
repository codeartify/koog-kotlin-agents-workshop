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
    private val assessMembershipCase: AssessMembershipCase
) {
    @PostMapping("/conversations/{conversationId}/messages")
    suspend fun sendMessage(
        @PathVariable conversationId: String,
        @RequestBody request: StaffAssistantMessageRequest
    ): ResponseEntity<StaffAssistantMessageResponse> {
        return ResponseEntity.ok(assessMembershipCase.execute(conversationId, request.message))
    }
}
