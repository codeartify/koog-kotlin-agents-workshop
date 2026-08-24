package com.codeartify.membership.staff_assistant

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
@RequestMapping("/memberships/{membershipId}/history")
class MembershipHistoryController(
    private val membershipHistoryRepository: MembershipHistoryRepository
) {
    @GetMapping
    fun getHistory(@PathVariable membershipId: String): ResponseEntity<List<MembershipHistoryResponse>> =
        ResponseEntity.ok(
            membershipHistoryRepository.findByMembershipIdOrderByIdAsc(membershipId)
                .map {
                    MembershipHistoryResponse(
                        evidenceReference = it.evidenceReference(),
                        eventType = it.eventType,
                        occurredAt = it.occurredAt,
                        details = it.details
                    )
                }
        )
}

data class MembershipHistoryResponse(
    val evidenceReference: String,
    val eventType: String,
    val occurredAt: Instant,
    val details: String
)
