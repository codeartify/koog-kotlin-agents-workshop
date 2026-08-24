# Exercise 5 — Controlled workflow and tests

## Goal

Make the workflow explicit and test the safety boundary without calling a real LLM.

## Concept checkpoint

Reliable agentic software combines probabilistic and deterministic steps:

1. **Investigate** — the model selects read tools.
2. **Structure** — the model returns an integration-friendly draft.
3. **Validate** — Kotlin checks current state, actions, and evidence.
4. **Present** — the application returns a safe staff-facing assessment.

The workflow is autonomous only inside a deliberately small read-only boundary.

## Start

```bash
git switch exercise/04-context-and-memory
```

## Tasks

1. Introduce a `MembershipStaffAgent` port owned by the application.
2. Move orchestration from the controller into `AssessMembershipCase`.
3. Keep the controller as an HTTP adapter only.
4. Test the use case with a small fake agent and in-memory read boundary.
5. Cover at least:
   - an allowed proposal is retained;
   - a forbidden proposal is removed with a warning;
   - invented evidence is removed;
   - human confirmation remains required.

## Acceptance criteria

- Tests call an owned application API, not Koog internals.
- Tests do not need an API key or network call.
- Guardrail failures are visible in the returned assessment.
- The UI and HTTP response contract remain unchanged.
- The solution contains no mutating agent tool.

## Reflection

Which failure belongs to the model, the tool adapter, the application workflow, or the domain model? Where would you
add retries, idempotency, authorization, tracing, and cost controls in production?

## Solution checkpoint

```bash
git switch exercise/05-controlled-workflow
```

