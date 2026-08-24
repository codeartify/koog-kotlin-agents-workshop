package com.codeartify.membership.staff_assistant

import ai.koog.agents.core.agent.AIAgent
import ai.koog.prompt.executor.clients.google.GoogleModels
import ai.koog.prompt.executor.model.PromptExecutor
import org.springframework.beans.factory.ObjectProvider
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class KoogMembershipStaffAgent(
    @Qualifier("googleExecutor")
    private val googleExecutor: ObjectProvider<PromptExecutor>
) {
    suspend fun run(message: String, conversationId: String): AgentRun {
        require(conversationId.isNotBlank()) { "Conversation id must not be blank" }
        val executor = googleExecutor.getIfAvailable() ?: throw AgentNotConfiguredException()

        val agent = AIAgent(
            promptExecutor = executor,
            systemPrompt = SYSTEM_PROMPT,
            llmModel = GoogleModels.Gemini2_5FlashLite,
            temperature = 0.2
        )

        return AgentRun(
            draft = AgentAssessmentDraft(summary = agent.run(message)),
            trace = emptyList()
        )
    }

    companion object {
        private val SYSTEM_PROMPT = """
            You are a read-only membership operations assistant for gym staff.
            Explain clearly when you do not have access to a member's actual data.
            Never execute or claim to execute a membership action.
        """.trimIndent()
    }
}
