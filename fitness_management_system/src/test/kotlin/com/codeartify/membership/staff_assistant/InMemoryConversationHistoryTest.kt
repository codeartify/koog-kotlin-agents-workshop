package com.codeartify.membership.staff_assistant

import ai.koog.prompt.message.Message
import ai.koog.prompt.message.MessagePart
import ai.koog.prompt.message.RequestMetaInfo
import ai.koog.prompt.message.ResponseMetaInfo
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class InMemoryConversationHistoryTest {
    @Test
    fun `stores conversational text without provider-specific tool fragments`() = runBlocking {
        val history = InMemoryConversationHistory()
        val messages = listOf(
            Message.User("Find Maya", RequestMetaInfo.Empty),
            Message.Assistant(
                MessagePart.Tool.Call(id = "call-1", tool = "searchCustomers", args = "{}"),
                ResponseMetaInfo.Empty
            ),
            Message.User(
                MessagePart.Tool.Result(id = "call-1", tool = "searchCustomers", output = "[]"),
                RequestMetaInfo.Empty
            ),
            Message.Assistant("Maya is suspended.", ResponseMetaInfo.Empty)
        )

        history.store("conversation-1", messages)

        val stored = history.load("conversation-1")
        assertEquals(listOf("Find Maya", "Maya is suspended."), stored.map { it.textContent() })
        assertTrue(stored.none { message -> message.parts.any { it is MessagePart.Tool } })
    }

    @Test
    fun `keeps only the latest twenty safe messages`() = runBlocking {
        val history = InMemoryConversationHistory()
        val messages = (1..25).map { number ->
            Message.User("message-$number", RequestMetaInfo.Empty)
        }

        history.store("conversation-1", messages)

        val stored = history.load("conversation-1")
        assertEquals(20, stored.size)
        assertEquals("message-6", stored.first().textContent())
        assertEquals("message-25", stored.last().textContent())
    }
}
