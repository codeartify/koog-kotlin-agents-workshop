package com.codeartify.membership.staff_assistant

import org.springframework.stereotype.Service

/**
 * Exercise seam: the HTTP contract, deterministic policy, event-history projection,
 * provider configuration, Angular console, and Docker environment are prepared.
 *
 * Exercise 1 replaces this placeholder with the smallest useful Koog agent.
 */
@Service
class KoogMembershipStaffAgent {
    suspend fun run(message: String, conversationId: String): AgentRun {
        throw ExerciseNotCompletedException(
            "Exercise 1 is not completed yet. Implement the basic Koog agent in KoogMembershipStaffAgent."
        )
    }
}
