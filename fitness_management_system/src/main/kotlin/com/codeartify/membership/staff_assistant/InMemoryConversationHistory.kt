package com.codeartify.membership.staff_assistant

import ai.koog.agents.chatMemory.feature.ChatHistoryProvider
import ai.koog.prompt.message.Message
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
        conversations[conversationId] = messages.toList()
    }

    override suspend fun load(conversationId: String): List<Message> =
        conversations[conversationId].orEmpty()
}
