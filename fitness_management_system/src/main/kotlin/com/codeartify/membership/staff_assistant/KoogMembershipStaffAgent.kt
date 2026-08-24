package com.codeartify.membership.staff_assistant

import ai.koog.agents.core.agent.AIAgent
import ai.koog.agents.core.tools.ToolRegistry
import ai.koog.prompt.executor.clients.google.GoogleModels
import ai.koog.prompt.executor.model.PromptExecutor
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.ObjectProvider
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.util.concurrent.CopyOnWriteArrayList

@Service
class KoogMembershipStaffAgent(
    @Qualifier("googleExecutor")
    private val googleExecutor: ObjectProvider<PromptExecutor>,
    private val readService: MembershipStaffReadService,
    private val objectMapper: ObjectMapper
) {
    suspend fun run(message: String, conversationId: String): AgentRun {
        require(conversationId.isNotBlank()) { "Conversation id must not be blank" }
        val executor = googleExecutor.getIfAvailable() ?: throw AgentNotConfiguredException()
        val trace = CopyOnWriteArrayList<ToolCallTrace>()
        val toolSet = MembershipStaffTools(readService, objectMapper, trace)

        val agent = AIAgent(
            promptExecutor = executor,
            systemPrompt = SYSTEM_PROMPT,
            llmModel = GEMINI_3_5_FLASH_LITE,
            temperature = 0.2,
            toolRegistry = ToolRegistry {
                tools(toolSet)
            }
        )

        return AgentRun(
            draft = AgentAssessmentDraft(summary = agent.run(message)),
            trace = trace.toList()
        )
    }

    companion object {
        private val GEMINI_3_5_FLASH_LITE = GoogleModels.Gemini3_5Flash.copy(
            id = "gemini-3.5-flash-lite"
        )

        private val SYSTEM_PROMPT = """
            You are a read-only membership operations assistant for gym staff.
            Use the supplied tools to investigate customer, membership, plan, and invoice facts.
            Distinguish clearly between facts returned by tools and information you do not know.
            Never invent an identifier or claim that a tool returned something it did not return.
            Never execute or claim to execute a membership action.
            Never infer why a membership was suspended from its status or from an invoice. An OPEN invoice proves only
            that the invoice is open; it does not prove causality. For any billing-causality question, call
            checkWhetherInvoiceExplainsSuspension and follow its conclusion.
        """.trimIndent()
    }
}
