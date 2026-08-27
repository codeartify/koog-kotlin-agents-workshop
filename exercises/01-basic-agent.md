# Exercise 1 — First Koog agent

## Goal

Replace the prepared placeholder with the smallest Koog agent that can answer a staff question.

## Concept checkpoint

An agent is an LLM inside a runtime loop. The model chooses the next step; Koog owns the loop and can later expose
tools. At this stage the model has no access to membership facts, so its answer is useful only as conversation—not as
an operational assessment.

## Start

```bash
git switch exercise/00-start
```

Open `KoogMembershipStaffAgent.kt`. The controller, response contract, Angular console, Docker stack, and optional
Google executor are already wired.

## Tasks

1. Inject the optional `googleExecutor` as a `PromptExecutor` provider.
2. Fail with `AgentNotConfiguredException` when no provider is configured.
3. Create an `AIAgent` with a low temperature and Gemini 3.5 Flash-Lite. Until Koog provides a named constant for it,
   derive the model definition with `GoogleModels.Gemini3_5Flash.copy(id = "gemini-3.5-flash-lite")`.
4. Give it a concise system prompt for a read-only gym staff assistant.
5. Run the user message and wrap the text in an `AgentAssessmentDraft`.
6. Return an empty tool trace.

## Acceptance criteria

- The backend starts with and without `GOOGLE_API_KEY`.
- Without a key, the endpoint returns `503 Service Unavailable`.
- With a key, the Angular console shows the model response.
- The response still has the stable `MembershipCaseAssessment` shape.
- No database or HTTP tool is available to the model.

## Try it

> What should staff consider before pausing a gym membership?

Then ask for the status of a specific member. The model cannot know it. That limitation motivates Exercise 2.

## Koog documentation

- [Quickstart](https://docs.koog.ai/quickstart/)
- [Basic agents](https://docs.koog.ai/agents/basic-agents/)
- [LLM parameters](https://docs.koog.ai/llm-parameters/)

## Solution checkpoint

```bash
git switch exercise/01-basic-agent
```
