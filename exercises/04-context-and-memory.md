# Exercise 4 — Context and domain history

## Goal

Support follow-up questions and ground temporal explanations in semantic membership events.

## Concept checkpoint

The word “memory” hides different responsibilities:

| Memory | Purpose | Authority |
|---|---|---|
| Conversation history | Resolve follow-up language in one staff session | Context only |
| Membership event history | Explain what happened over time | Authoritative domain facts |
| Agent checkpoint | Resume an interrupted execution | Runtime state |
| Semantic retrieval | Find relevant knowledge at scale | Retrieved context |

This exercise implements the first two. They must not be conflated.

## Start

```bash
git switch exercise/03-structured-assessment
```

## Tasks

1. Add `getMembershipHistory` to the tool set.
2. Add Koog's chat-memory feature dependency.
3. Provide a shared, thread-safe `ChatHistoryProvider` so history survives the per-request agent instance.
4. Install `ChatMemory` with a bounded window.
5. Pass the HTTP `conversationId` to `agent.run` as the Koog session ID.
6. Instruct the agent to cite only evidence references returned by the history tool.

## Acceptance criteria

- “What plan is Maya on?” followed by “Why is it paused?” works without repeating the member name.
- A new conversation ID does not inherit the old conversation.
- The response cites only known `membership-event:*` references.
- Unknown evidence references are removed by deterministic validation.
- Conversation memory contains messages; it is not used as the source of membership truth.

## Solution checkpoint

```bash
git switch exercise/04-context-and-memory
```

