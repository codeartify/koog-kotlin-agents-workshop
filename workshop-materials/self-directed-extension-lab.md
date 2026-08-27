# Self-directed extension lab

The five core exercises are the complete workshop journey. This optional lab lets you choose where to continue once you have that shared foundation.

## How the lab works

1. **Choose — 5 minutes.** Select one path: durability, knowledge, automation, or operations.
2. **Explore — 25–45 minutes.** Work individually or in a pair. Read documentation, inspect the workshop code, draw a design, or make a small code spike.
3. **Bring back.** Prepare one artifact, one useful finding, and one open question.
4. **Share — 15 minutes.** If you want, take about three minutes to show what you investigated, discovered, or built. Passing is fine.

A finished implementation is not required. A diagram, tool contract, Kotlin sketch, checkpoint plan, evaluation case, trace design, or threat model is a valid result.

## Path 1 — Durability

**Question:** What happens when an agent run is interrupted?

Investigate the difference between completed chat history and in-progress execution persistence. Mark sensible checkpoints in:

```text
investigate → structure → validate → present
```

Consider:

- Which messages, tool results, graph position, and identifiers must be saved?
- Which reads may be repeated?
- Which current facts and permissions must be reloaded after recovery?
- Where would replay require an idempotency key?

**Suggested artifact:** a checkpoint map with one replay risk and one mitigation per checkpoint.

Relevant slides: chat memory vs. execution persistence; checkpoint replay safety.

## Path 2 — Knowledge

**Question:** How should the agent retrieve general policy without confusing it with case history?

Design a read-only `searchStaffHandbook` tool. A possible result shape is:

```kotlin
data class PolicySearchResult(
    val reference: String,
    val title: String,
    val excerpt: String,
    val version: String,
    val accessScope: String
)
```

Consider:

- How are documents chunked and searched?
- How does the result preserve provenance and policy version?
- Which staff roles may retrieve which sections?
- What happens when nothing is relevant enough?
- How will you treat malicious instructions inside retrieved text?

**Suggested artifact:** the tool contract plus one successful, one empty, and one adversarial retrieval example.

Relevant slide: domain history vs. semantic retrieval.

## Path 3 — Automation

**Question:** What starts an agent run when there is no staff chat request?

Sketch an event-triggered membership assessment.

Consider:

- Which domain or integration event starts the run?
- What correlation and idempotency keys identify the logical request?
- How are retries, timeouts, and duplicate delivery handled?
- Where is the result stored or queued for human review?
- What happens when the membership changes during the run?

**Suggested artifact:** an event-to-review flow with its failure and retry paths.

Relevant slide: operating the agent in production.

## Path 4 — Operations

**Question:** How do we know the agent remains useful, safe, and affordable?

Choose one focus: observability, evaluation, or security.

Possible activities:

- Define which strategy, node, LLM, and tool information belongs in a trace and what must be redacted.
- Create evaluation cases for correct proposals, invented evidence, changed state, missing authorization, and malicious retrieved content.
- Decide which metrics and thresholds should trigger investigation: latency, cost, tool-call count, unsupported citations, rejected actions, or provider failures.
- Threat-model the tool boundary and human-confirmation boundary.

**Suggested artifact:** a trace schema, an evaluation table, or a compact threat model.

Relevant slide: operating the agent in production.

## Show-and-tell format

Use roughly three minutes:

1. What did you investigate?
2. What did you discover, design, or build?
3. What remains unclear?

If you have nothing useful to show yet, continue exploring or join the final workshop conclusion.

## Sources

- [Koog chat memory](https://docs.koog.ai/features/chat-memory/)
- [Koog agent persistence](https://docs.koog.ai/features/agent-persistence/)
- [Koog strategy graphs](https://docs.koog.ai/custom-strategy-graphs/)
- [Koog retrieval-augmented generation](https://docs.koog.ai/retrieval-augmented-generation/)
- [Koog OpenTelemetry](https://docs.koog.ai/features/open-telemetry/)
