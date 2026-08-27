# Exercise 3 — Structure and validate

## Goal

Turn an agent response into a typed assessment draft and validate it against deterministic Kotlin policy.

## Concept checkpoint

Structured output makes a probabilistic response easier to integrate; it does not make the content true. The agent
may propose. Application code must verify membership identity, action eligibility, and evidence references.

## Start

```bash
git switch exercise/02-read-tools
```

## Tasks

1. Add `getAllowedMembershipActions` to the read-only tool set.
2. Change the system prompt to request exactly one `AgentAssessmentDraft` JSON object.
3. Parse the JSON response with the configured `ObjectMapper`.
4. Fall back to a safe text-only draft when parsing fails.
5. Return the draft to the prepared controller, which resolves the current membership and invokes
   `MembershipProposalValidator`.
6. Inspect how the final response differs from the model draft.

## Acceptance criteria

- A valid response populates `membershipId`, `summary`, `evidenceReferences`, and `proposedAction`.
- Malformed output does not accidentally produce an action.
- An action not allowed by the current membership state is removed.
- `requiresHumanConfirmation` is always `true`.
- No command gateway or mutating tool is exposed.

## Try it

Use a paused membership and ask the agent to cancel, resume, or reactivate it. Compare the model proposal with
`possibleActions`, `proposedAction`, and `warnings` in the structured card.

## Koog documentation

- [Structured output](https://docs.koog.ai/structured-output/)
- [Serialization](https://docs.koog.ai/serialization/)

## Solution checkpoint

```bash
git switch exercise/03-structured-assessment
```

