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

        val rawResult = agent.run(message)
        return AgentRun(parseDraft(rawResult), trace.toList())
    }

    private fun parseDraft(rawResult: String): AgentAssessmentDraft {
        val json = rawResult.trim()
            .removePrefix("```json")
            .removePrefix("```")
            .removeSuffix("```")
            .trim()

        return runCatching {
            objectMapper.readValue(json, AgentAssessmentDraft::class.java)
        }.getOrElse {
            AgentAssessmentDraft(
                summary = rawResult,
                proposedAction = null
            )
        }
    }

    companion object {
        private val GEMINI_3_5_FLASH_LITE = GoogleModels.Gemini3_5Flash.copy(
            id = "gemini-3.5-flash-lite"
        )

        private val SYSTEM_PROMPT = """
            You are a read-only membership operations assistant for gym staff.
            Use the supplied tools to investigate customer, membership, plan, and invoice facts.
            Never invent identifiers or evidence. Never execute or claim to execute an action.
            The deterministic Kotlin application decides which actions are allowed and validates every proposal.

            Return only one JSON object with exactly this shape:
            {
              "membershipId": "membership id or null",
              "summary": "concise staff-facing assessment",
              "evidenceReferences": [],
              "proposedAction": "PAUSE, RESUME, REACTIVATE, CANCEL, or null"
            }

            Propose at most one action, and only after calling getAllowedMembershipActions.
        """.trimIndent()
    }
}
