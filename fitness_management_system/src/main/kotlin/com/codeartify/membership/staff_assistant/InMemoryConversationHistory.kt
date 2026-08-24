package com.codeartify.membership.staff_assistant

import ai.koog.agents.chatMemory.feature.ChatHistoryProvider
import ai.koog.prompt.message.Message
import ai.koog.prompt.message.MessagePart
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

/**
 * Workshop-sized conversation storage. It is shared between per-request agent instances,
 * isolated by conversation id, and intentionally lost when the application restarts.
 */
@Component
class InMemoryConversationHistory : ChatHistoryProvider {
    private val conversations = ConcurrentHashMap<String, List<Message>>()

    override suspend fun store(conversationId: String, messages: List<Message>) {
        conversations[conversationId] = messages
            .filter { message ->
                message.parts.any { it is MessagePart.Text } &&
                    message.parts.none { it is MessagePart.Tool }
            }
            .takeLast(MAX_HISTORY_MESSAGES)
    }

    override suspend fun load(conversationId: String): List<Message> =
        conversations[conversationId].orEmpty()

    companion object {
        private const val MAX_HISTORY_MESSAGES = 20
    }
}
