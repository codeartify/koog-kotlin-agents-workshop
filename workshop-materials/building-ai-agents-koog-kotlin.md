---
marp: true
paginate: true
size: 16:9
theme: default
title: "Building AI Agents with Koog and Kotlin"
description: "Codeartify workshop — concept blocks followed by concrete practice"
style: |
  :root {
    --ink: #111111;
    --muted: #5b5b5b;
    --line: #d8d8d8;
    --soft: #f5f5f5;
    --paper: #ffffff;
    --accent: #a30000;
    --accent-soft: #fff1f1;
    --dark: #101010;
    --dark-line: #313131;
  }

  section {
    background: var(--paper);
    color: var(--ink);
    font-family: "Roboto", Helvetica, Arial, sans-serif;
    font-size: 28px;
    font-weight: 300;
    line-height: 1.28;
    padding: 50px 66px;
  }

  section * { box-sizing: border-box; }

  h1, h2, h3 {
    color: var(--ink);
    font-weight: 700;
    letter-spacing: 0;
    line-height: 1.03;
    margin: 0;
  }

  h1 {
    border-bottom: 5px solid var(--ink);
    font-size: 58px;
    max-width: 1080px;
    padding-bottom: 14px;
  }

  h2 { color: #252525; font-size: 36px; margin-top: 26px; max-width: 980px; }
  h3 { font-size: 27px; }
  p, ul, ol, pre, table { margin-bottom: 0; }
  ul, ol { padding-left: 1.15em; }
  li + li { margin-top: 10px; }
  strong { font-weight: 700; }

  .kicker {
    color: var(--accent);
    font-family: "Roboto Mono", monospace;
    font-size: 17px;
    font-weight: 600;
    letter-spacing: 0.08em;
    margin: 0 0 22px;
    text-transform: uppercase;
  }

  .lead { color: var(--muted); font-size: 34px; font-weight: 500; margin-top: 26px; max-width: 960px; }
  .small { color: var(--muted); font-size: 20px; }
  .accent { color: var(--accent); font-weight: 700; }

  section::after {
    color: #888888;
    font-family: "Roboto Mono", monospace;
    font-size: 13px;
  }

  section::before {
    bottom: 26px;
    color: var(--ink);
    content: "Codeartify";
    font-size: 17px;
    font-weight: 700;
    left: 66px;
    line-height: 26px;
    position: absolute;
    z-index: 5;
  }

  section.dark-title {
    background: var(--dark);
    color: var(--paper);
    display: flex;
    flex-direction: column;
    justify-content: center;
  }

  section.dark-title h1, section.dark-title h2 { border-color: var(--paper); color: var(--paper); }
  section.dark-title h1 { font-size: 72px; }
  section.dark-title.long-title h1 { font-size: 62px; }
  section.dark-title .lead { color: #d7d7d7; }
  section.dark-title .kicker { color: #ff5a5a; }
  section.dark-title::after { color: #777777; }
  section.dark-title::before { color: var(--paper); }

  .two-columns { display: grid; gap: 30px; grid-template-columns: 1fr 1fr; margin-top: 34px; }
  .three-columns { display: grid; gap: 22px; grid-template-columns: repeat(3, 1fr); margin-top: 34px; }

  .system-compare { display: grid; gap: 24px; grid-template-columns: 1fr 1fr; margin-top: 26px; }
  .system { border: 1px solid var(--line); border-top: 6px solid var(--accent); min-height: 230px; padding: 20px 24px; }
  .system h3 { margin: 0 0 18px; }
  .mini-flow, .agent-observation { align-items: center; display: flex; gap: 10px; justify-content: center; }
  .agent-observation { margin-top: 12px; }
  .mini-flow .step, .agent-observation .step {
    background: var(--soft);
    border: 1px solid var(--line);
    font-size: 17px;
    font-weight: 700;
    padding: 12px 10px;
    text-align: center;
  }
  .mini-flow .model { background: var(--accent); color: var(--paper); }
  .mini-flow .arrow, .agent-observation .arrow { color: var(--accent); font-size: 25px; font-weight: 700; }
  .system-copy { color: var(--muted); font-size: 18px; margin: 20px 0 0; }
  .boundary {
    background: var(--soft);
    color: var(--accent);
    font-family: "Roboto Mono", monospace;
    font-size: 13px;
    font-weight: 700;
    margin-top: 12px;
    padding: 9px 10px;
    text-align: center;
  }
  .statement.compact { font-size: 27px; margin-top: 28px; max-width: none; padding: 20px 28px; }

  .panel {
    background: var(--paper);
    border: 1px solid var(--line);
    border-top: 6px solid var(--accent);
    min-height: 190px;
    padding: 24px 26px;
  }

  .panel h3 { margin-bottom: 14px; }
  .panel p, .panel li { color: var(--muted); font-size: 23px; }
  .panel.dark { background: var(--dark); border-color: var(--dark-line); border-top-color: #ff5a5a; }
  .panel.dark h3 { color: var(--paper); }
  .panel.dark p, .panel.dark li { color: #d7d7d7; }

  .statement {
    background: var(--soft);
    border-left: 7px solid var(--accent);
    font-size: 35px;
    font-weight: 500;
    margin-top: 44px;
    max-width: 1080px;
    padding: 28px 34px;
  }

  .flow {
    align-items: stretch;
    display: grid;
    gap: 16px;
    grid-template-columns: repeat(4, 1fr);
    margin-top: 52px;
  }

  .validation-join {
    align-items: stretch;
    display: grid;
    gap: 14px 18px;
    grid-template-columns: 1.15fr 54px 1.2fr 54px 1.15fr;
    grid-template-rows: 112px 112px;
    margin-top: 28px;
  }
  .join-box {
    align-items: center;
    background: var(--soft);
    border: 1px solid var(--line);
    display: flex;
    flex-direction: column;
    justify-content: center;
    padding: 12px 16px;
    text-align: center;
  }
  .join-box strong { font-size: 22px; }
  .join-box span { color: var(--muted); font-size: 16px; margin-top: 6px; }
  .join-proposal { grid-column: 1; grid-row: 1; }
  .join-facts { grid-column: 1; grid-row: 2; }
  .join-validator { background: var(--accent); border-color: var(--accent); color: white; grid-column: 3; grid-row: 1 / 3; }
  .join-validator span { color: #ffd8d8; }
  .join-result { grid-column: 5; grid-row: 1 / 3; }
  .join-arrow { align-self: center; color: var(--accent); font-size: 34px; font-weight: 700; text-align: center; }
  .join-arrow-proposal { grid-column: 2; grid-row: 1; }
  .join-arrow-facts { grid-column: 2; grid-row: 2; }
  .join-arrow-result { grid-column: 4; grid-row: 1 / 3; }


  .agent-cycle {
    align-items: center;
    display: grid;
    gap: 14px 18px;
    grid-template-columns: 210px 54px 250px 54px 290px;
    grid-template-rows: 64px 64px 64px;
    margin: 30px auto 0;
    width: 950px;
  }
  .cycle-node {
    align-items: center;
    background: var(--soft);
    border: 1px solid var(--line);
    display: flex;
    flex-direction: column;
    font-size: 23px;
    font-weight: 700;
    height: 100%;
    justify-content: center;
    text-align: center;
  }
  .cycle-goal { grid-column: 1; grid-row: 1; }
  .cycle-context { grid-column: 1; grid-row: 2; }
  .cycle-model { background: var(--accent); color: var(--paper); grid-column: 3; grid-row: 1 / 3; }
  .cycle-final { grid-column: 5; grid-row: 1 / 3; }
  .cycle-result { grid-column: 3; grid-row: 3; }
  .cycle-arrow { color: var(--accent); font-size: 30px; font-weight: 700; text-align: center; }
  .cycle-goal-arrow { grid-column: 2; grid-row: 1; }
  .cycle-context-arrow { grid-column: 2; grid-row: 2; }
  .cycle-stop-arrow { grid-column: 4; grid-row: 1 / 3; }
  .cycle-down { align-self: end; grid-column: 3; grid-row: 2; transform: translateY(25px); }
  .cycle-return { color: var(--accent); font-family: "Roboto Mono", monospace; font-size: 16px; grid-column: 1 / 3; grid-row: 3; text-align: right; }
  .cycle-tag { color: var(--accent); display: block; font-family: "Roboto Mono", monospace; font-size: 14px; margin-top: 7px; }

  .node {
    align-items: center;
    background: var(--soft);
    border: 1px solid var(--line);
    display: flex;
    flex-direction: column;
    justify-content: center;
    min-height: 145px;
    padding: 20px;
    text-align: center;
  }

  .node strong { font-size: 25px; }
  .node span { color: var(--muted); font-size: 19px; margin-top: 8px; }
  .node.accent-node { background: var(--accent); border-color: var(--accent); color: white; }
  .node.accent-node span { color: #ffd8d8; }

  .branch-flow { display: grid; gap: 9px; margin-top: 28px; }
  .branch {
    align-items: center;
    border: 1px solid var(--line);
    border-left: 6px solid var(--accent);
    display: grid;
    font-family: "Roboto Mono", monospace;
    font-size: 20px;
    grid-template-columns: 300px 1fr;
    padding: 12px 20px;
  }
  .branch span { color: var(--muted); font-family: "Roboto", sans-serif; font-size: 21px; }

  pre {
    background: #f7f7f7;
    border: 1px solid var(--line);
    color: var(--ink);
    font-family: "Roboto Mono", monospace;
    font-size: 21px;
    line-height: 1.34;
    margin-top: 28px;
    padding: 22px 26px;
  }
  code { font-family: "Roboto Mono", monospace; }

  table { border-collapse: collapse; font-size: 23px; margin-top: 30px; width: 100%; }
  th { border-bottom: 4px solid var(--ink); font-weight: 700; text-align: left; }
  td, th { border-top: 1px solid var(--line); padding: 12px 15px; }
  td:first-child { font-family: "Roboto Mono", monospace; font-size: 20px; font-weight: 600; }

  section.practice { border-left: 14px solid var(--accent); }
  section.practice h1 { font-size: 54px; }
  .checkpoint {
    background: var(--dark);
    color: white;
    font-family: "Roboto Mono", monospace;
    font-size: 20px;
    margin-top: 28px;
    padding: 16px 20px;
  }
  .acceptance { display: grid; gap: 11px; grid-template-columns: 1fr 1fr; margin-top: 30px; }
  .acceptance div { background: var(--soft); border-left: 4px solid var(--accent); font-size: 22px; padding: 14px 18px; }
---

<!-- _class: dark-title long-title -->

<p class="kicker">Codeartify · hands-on workshop</p>

# Building AI Agents with Koog and Kotlin

<p class="lead">One staff question. Five increments. Each fixes the failure exposed by the previous one.</p>

<!--
Story: This workshop follows one staff question through the entire day: “What happened to Maya's membership, and what can staff safely propose?” We begin with a model that can only generate plausible text. Each exercise exposes a limitation in that version and adds the smallest architectural capability needed to address it.

Definition — agentic system: software in which a model can choose a next step inside a set of capabilities supplied by the application. The model does not receive unlimited autonomy. The application decides which tools exist, which state is available, which results are accepted, and where a human must confirm.

Learning lens: do not judge a slide or exercise in isolation. Ask what failure the previous version exposed, what responsibility the new version adds, and which failure deliberately remains. The final system is not “a smarter chatbot”; it is a composition of probabilistic interpretation and deterministic software boundaries.

Code connection: the complete progression is visible in the branch chain from `exercise/00-start` to `exercise/05-controlled-workflow`. The staff-facing implementation lives under `fitness_management_system/.../staff_assistant`, while the Angular console makes every increment visible.

Transition: before looking at Koog, we need a shared idea of where a chatbot ends and an agent begins.

[Sources]
- https://docs.koog.ai/
[/Sources]
-->

---

<p class="kicker">Connect · 25 minutes</p>

# Simple AI chat or agentic system?

<div class="system-compare">
  <div class="system">
    <h3>Simple AI chat</h3>
    <div class="mini-flow"><span class="step">Staff question</span><span class="arrow">→</span><span class="step model">LLM</span><span class="arrow">→</span><span class="step">Answer</span></div>
    <p class="system-copy">One response from prompt and supplied context.<br><strong>Example:</strong> “Explain the pause rules.”</p>
  </div>
  <div class="system">
    <h3>Agentic system</h3>
    <div class="mini-flow"><span class="step">Staff goal</span><span class="arrow">→</span><span class="step model">Model chooses</span><span class="arrow">→</span><span class="step">Answer</span></div>
    <div class="agent-observation"><span class="step">Read tool</span><span class="arrow">→</span><span class="step">Observation</span><span class="arrow">↺</span></div>
    <div class="boundary">Application boundary: tools | state | accepted results | human confirmation</div>
  </div>
</div>

<div class="statement compact">Chat generates an answer. An agent chooses the next step—inside application boundaries.</div>

<!--
Story so far: we have promised to build an agent, but the same chat interface can hide two very different software structures. This slide makes the structural difference visible before Koog appears in the code.

Definition — simple AI chat: the application sends messages and supplied context to a model, and the model generates an answer. The model does not choose an application capability or continue from a tool observation. “Explain the pause rules” fits when the rules are already present in the prompt or context.

Definition — agentic system: software in which a model can choose a next step inside a set of capabilities supplied by the application. The model does not receive unlimited autonomy. The application decides which tools exist, which state is available, which results are accepted, and where a human must confirm.

Read the left diagram: the staff question and available context go to the model, which returns one answer. A conversational UI alone does not make the system agentic.

Read the right diagram: the staff goal reaches a model that may answer or select a read tool. A tool result becomes an observation, and the model chooses again. The loop is bounded by application decisions:
- Tools: only narrow capabilities such as `searchCustomers` and `getMembershipDetails` exist.
- State: the application decides whether the model sees chat history, current projections, or domain history.
- Accepted results: parsing and `MembershipProposalValidator` decide what enters the stable response.
- Human confirmation: the workshop stops at a proposal; no mutating tool is exposed.

Worked comparison:
1. “Explain the pause rules.” Simple chat can generate an explanation from supplied policy context.
2. “Find Maya and explain why her membership is paused.” The model must choose customer, membership, and history tools, so this is agentic investigation.
3. “Is a 45-day pause allowed?” Deterministic Kotlin policy and the domain model own that business decision even if an agent collected the facts.
4. “Pause Maya’s membership now.” A production workflow would need authorization, validation, an explicit command, idempotency, and human confirmation. This workshop deliberately does not cross that boundary.

Important nuance: the wording does not determine the architecture. “Explain why Maya is paused” is simple chat if all facts are supplied, agentic if the model chooses how to retrieve them, and a deterministic workflow if code prescribes every retrieval step.

Facilitation: ask participants which arrow makes the right-hand system agentic. The answer is not “the arrow to a tool” alone; it is the model choosing the next step and choosing again after an observation, within capabilities defined by the application.

Transition: the five increments will gradually build the bounded right-hand system while keeping consequential decisions under deterministic control.

[Sources]
- https://docs.koog.ai/agents/basic-agents/
- https://docs.koog.ai/tools/
[/Sources]
-->
---

<p class="kicker">The learning rhythm</p>

# One system. Five increments.

<div class="branch-flow">
  <div class="branch">01 · basic-agent <span>Fluent response — but no membership facts</span></div>
  <div class="branch">02 · read-tools <span>Grounded investigation — but proposals remain probabilistic</span></div>
  <div class="branch">03 · structured-assessment <span>Validated proposal — but follow-ups lack context</span></div>
  <div class="branch">04 · context-and-memory <span>Contextual answer — but orchestration remains mixed</span></div>
  <div class="branch">05 · controlled-workflow <span>Explicit use case — safety tests need no LLM</span></div>
</div>

<!--
Story: the branch chain is a causal sequence, not a feature catalogue. Exercise 1 creates useful conversation and exposes missing facts. Exercise 2 adds facts and exposes unsafe interpretation. Exercise 3 validates proposals and exposes missing conversational continuity. Exercise 4 adds state and exposes orchestration and testing concerns. Exercise 5 makes those responsibilities explicit.

Definition — increment: the smallest working change that adds one capability while preserving the previous system. Each increment keeps the HTTP contract and Angular console stable, so attention stays on the new Koog or architecture concept.

Definition — checkpoint branch: a known working solution that participants can switch to when they are blocked. It is both an answer key and a recovery mechanism. Starting the next exercise from the previous solution ensures the story remains cumulative.

How to read the deck: concept slides explain why the next capability is needed. Practice slides identify the implementation seam. Debrief slides state the failure that remains and therefore introduce the next concept.

One request through the five checkpoints: use “What happened to Maya’s membership, and what can staff safely propose?” as the thread. Exercise 1 can produce fluent prose but must admit it has no member facts. Exercise 2 can search Maya, follow returned identifiers, and inspect the current membership. Exercise 3 may draft `REACTIVATE` for an ACTIVE membership, but Kotlin removes the invalid proposal. Exercise 4 can answer the follow-up “When can it resume?” using the same conversation and cite domain history. Exercise 5 preserves that behavior behind an application use case whose safety tests use a fake agent.

Transition: the first capability is the smallest possible model-driven loop.
-->

---

<p class="kicker">Concept 01 · the agent loop</p>

# The model chooses the next step

<div class="agent-cycle">
  <div class="cycle-node cycle-goal">Goal</div>
  <div class="cycle-arrow cycle-goal-arrow">→</div>
  <div class="cycle-node cycle-context">Context</div>
  <div class="cycle-arrow cycle-context-arrow">→</div>
  <div class="cycle-node cycle-model">LLM decision</div>
  <div class="cycle-arrow cycle-stop-arrow">→</div>
  <div class="cycle-node cycle-final">Final response / terminal action<span class="cycle-tag">STOP</span></div>
  <div class="cycle-arrow cycle-down">↓</div>
  <div class="cycle-node cycle-result">Action result</div>
  <div class="cycle-return">↰ add result to context · CONTINUE</div>
</div>

<div class="statement compact">Action result → Context → LLM repeats. A final response or terminal action stops the loop.</div>

<!--
Story so far: we now have a spectrum and a five-step journey. The first technical question is what Koog actually adds around an LLM call.

Definition — agent loop: a runtime cycle in which the model receives a goal and context, chooses either a final response or an action, observes the action result, and decides again. A stopping rule ends the cycle when a final result is produced or a configured limit is reached.

Read the diagram from left to right. The goal tells the model what the staff member wants. Context contains everything available for the current decision: the conversation, system instructions, and any previous action results. The LLM then chooses one of two paths.

Stop path: the LLM produces a final response or terminal action. Koog returns that outcome and the run ends. A configured step or token limit is an additional technical stopping rule when no final outcome is produced.

Continue path: the LLM requests an available capability. Its action result is appended to Context rather than returned directly as the final answer. The enriched Context is sent to the LLM again, so the model can choose the next step with the new evidence.

Fitness example: “What should staff consider before pausing a membership?” needs no member-specific tool and can end after one model response. “Why is Maya paused?” will later require several loop iterations: search the customer, locate the membership, read its current state, then inspect history.

Compare three loop shapes:
1. “Explain what a membership pause means.” The model can answer immediately; there is one decision and no action result.
2. “Show membership `membership-1`.” The model chooses `getMembershipDetails`, the tool result is added to Context, and the model then answers.
3. “Find Maya and tell me whether she has unpaid invoices.” The model may search the customer, find her membership, retrieve its invoices, and decide after every action result whether it has enough evidence. If customer search returns no match, the correct final response is to report that rather than invent an identifier.

Code connection: on `exercise/01-basic-agent`, open `KoogMembershipStaffAgent.kt`. `AIAgent(...)` configures the executor, model, system prompt, temperature, and eventually the tool registry. `agent.run(message, conversationId)` starts the loop. With no tools registered in Exercise 1, the loop effectively produces one model response.

Transition: a loop explains how the agent acts, but it does not decide which responsibilities the agent should own.

[Sources]
- https://docs.koog.ai/agents/basic-agents/
[/Sources]
-->
---

<p class="kicker">Concept 01 · architecture boundary</p>

# The agent is not the domain model

| Responsibility | Owner |
|---|---|
| Interpret staff language | Koog agent |
| Select read capabilities | Koog agent |
| Explain and propose | Koog agent |
| Enforce lifecycle rules | Kotlin domain/application code |
| Record business truth | Aggregate + domain events |

<div class="statement">Probabilistic interpretation outside. Deterministic invariants inside.</div>

<!--
Story so far: Koog can let a model choose the next step. That flexibility is useful for interpreting staff language, but it would be dangerous if the same probabilistic component also defined membership rules.

Definitions:
- Probabilistic: the result is generated from model probabilities and may vary or be incorrect even when the input looks similar.
- Deterministic: the result is calculated from explicit code and state; given the same inputs, the rule is expected to produce the same answer.
- Invariant: a business rule that must always hold, regardless of how a request was expressed. For example, a cancelled membership is terminal.

Fitness example: the agent may interpret “put Maya's account on hold” as a request to pause. Kotlin code must still calculate whether `PAUSE` is allowed for the current status and enforce the required pause period. The model may propose; it does not redefine the lifecycle.

Boundary examples:
- ACTIVE plus a drafted `REACTIVATE`: `MembershipActionPolicy` rejects the proposal because ACTIVE permits `PAUSE` or `CANCEL`.
- SUSPENDED plus a drafted `REACTIVATE`: the proposal may survive validation, but it still remains a proposal requiring confirmation.
- CANCELLED plus any proposed action: the allowed-action set is empty because cancellation is terminal.
- ACTIVE plus a future request to pause for 20 days: the action name may look plausible, but the aggregate's pause-period invariant must reject a duration below 30 days if execution is ever added.

Code connection: compare `KoogMembershipStaffAgent.kt` with `MembershipActionPolicy` and the membership aggregate. The Koog adapter owns interpretation and investigation. The policy and aggregate own allowed transitions. `MembershipProposalValidator` later joins those two worlds without giving the model command authority.

Teaching point: the architecture is not an apology for an unreliable model. It is the design that makes a probabilistic component useful inside reliable software.

Transition: with the responsibility boundary fixed, we can implement the smallest safe model call.
-->

---

<!-- _class: practice -->

<p class="kicker">Concrete practice 01 · 25 minutes coding</p>

# Make the first model call

1. Inject the optional Google `PromptExecutor`.
2. Create an `AIAgent` with a read-only staff system prompt.
3. Run the message and wrap the text in `AgentAssessmentDraft`.
4. See the response in the Angular console.

<div class="checkpoint">start: exercise/00-start → solution: exercise/01-basic-agent</div>

<div class="acceptance">
  <div>503 without an API key</div><div>Answer visible with a key</div>
  <div>Stable HTTP response shape</div><div>No tools yet</div>
</div>

<!--
Story so far: we know the first version must converse but must not pretend to own membership truth. Exercise 1 builds exactly that version.

Definitions:
- `PromptExecutor`: Koog's provider-facing abstraction for sending prompts to an LLM.
- System prompt: instructions that establish the agent's role and behavioral boundary for the run.
- Temperature: a sampling control. Lower values generally reduce variation, but they do not turn unsupported claims into facts.
- Draft: model-produced content that has not yet passed application validation.

Code walkthrough: on `exercise/00-start`, open `KoogMembershipStaffAgent.kt` and identify the prepared seam. On the solution branch, the optional `googleExecutor` is resolved; missing configuration becomes `AgentNotConfiguredException`. The code constructs `AIAgent` with `Gemini2_5FlashLite`, a temperature of `0.2`, and a read-only staff prompt, then calls `agent.run` and wraps the result in the stable response model.

Runtime behavior: without `GOOGLE_API_KEY`, the application still starts but the endpoint returns `503`. With a key, the Angular console displays the model response. This separation lets infrastructure and deterministic tests run without a provider credential.

Try two prompts. First ask a general policy question. Then ask for Maya's current status. The second answer may sound convincing, but Exercise 1 has no route to the database or projections.

Transition: the missing route to application facts is the reason Exercise 2 introduces tools.

[Sources]
- https://docs.koog.ai/agents/basic-agents/
- https://docs.koog.ai/spring-boot/
[/Sources]
-->

---

<p class="kicker">Debrief 01</p>

# A fluent answer can still be ungrounded

<div class="two-columns">
  <div class="panel"><h3>Grounding</h3><p>A response traceable to authoritative facts retrieved from the application.</p><p><strong>Example:</strong> membership status returned by a tool.</p></div>
  <div class="panel"><h3>Hallucination</h3><p>Unsupported or false in the current context.</p><p><strong>Danger:</strong> plausible IDs, statuses, dates, or rules.</p></div>
</div>

<div class="statement">Fluent ≠ grounded. Add capability—not a longer prompt.</div>

<!--
Story so far: the first agent produced a natural response through the stable UI and API. That is genuine progress, but it also created the day's first important failure: fluent language can hide missing evidence.

Definition — grounding: connecting a model response to authoritative, retrievable facts supplied by the application. A grounded answer should be traceable to data the agent actually received rather than to patterns remembered during model training.

Definition — hallucination: generated content that is unsupported or false in the current context. Hallucination is not limited to absurd statements; the dangerous cases are plausible customer IDs, statuses, dates, and rules.

Fitness example: the model might say “Maya's membership is active and can be paused” because that is a likely gym scenario. In Exercise 1, it has not searched for Maya, found a membership, or read its status. The answer is therefore ungrounded even if it happens to be correct.

Code connection: inspect the empty tool registry or absence of a registry on `exercise/01-basic-agent`, and notice that the response trace is empty. The system honestly exposes that no application capability contributed to the answer.

Self-check: identify one sentence in the response that could only be trusted after reading application data. That sentence becomes the requirement for the next increment.

Transition: a longer prompt cannot supply facts it does not contain. We need to give the loop narrow application capabilities.
-->

---

<p class="kicker">Concept 02 · tools</p>

# Tools turn intent into capability

<div class="three-columns">
  <div class="panel"><h3>Narrow</h3><p>One job with explicit arguments.</p></div>
  <div class="panel"><h3>Described</h3><p>Names and descriptions guide model choice.</p></div>
  <div class="panel"><h3>Owned</h3><p>Application code controls the implementation.</p></div>
</div>

<div class="statement">Tool = a named, described, typed function the model may choose to call.</div>

<!--
Story so far: Exercise 1 can converse but cannot inspect Maya's case. A tool closes that gap by giving the model a controlled action it may select during the agent loop.

Definition — tool: a named function exposed to the model with a description, typed arguments, and an application-owned implementation. The model chooses whether and how to call it; ordinary Kotlin code performs the operation.

Why the three properties matter:
- Narrow: `getMembershipDetails(membershipId)` has one clear responsibility. A generic `queryDatabase(sql)` tool has unbounded and ambiguous capability.
- Described: the model sees names and descriptions when deciding which tool fits its current goal. Wording is therefore part of executable behavior.
- Owned: a tool delegates to application services that can enforce authorization, limits, validation, logging, and data minimization.

Fitness example: the model cannot jump directly from the name “Maya” to a membership. It should call `searchCustomers`, take a returned customer ID, then call `getMembershipsForCustomer`. The identifiers create a grounded chain rather than a guessed shortcut.

Good and bad tool examples:
- Good: `getInvoicesForMembership(membershipId)` states one business purpose and requires a real identifier.
- Weak: `getData(query)` hides what data exists and gives the model little guidance for choosing it.
- Dangerous: `queryDatabase(sql)` combines arbitrary access, technical coupling, and an unnecessarily large capability.
- Good composition: `searchCustomers("maya@example.com")` returns `customer-1`; the next tool accepts `customer-1`. Each result narrows the next valid choice.

Code connection: open `MembershipStaffTools.kt` on `exercise/02-read-tools`. `ToolSet`, `@Tool`, and `@LLMDescription` expose the contracts. The implementation delegates to `MembershipStaffReadService` instead of handing repositories directly to the model.

Transition: adding tools raises the next design question: which capabilities are safe enough to expose?

[Sources]
- https://docs.koog.ai/tools/
- https://docs.koog.ai/tools/annotation-based-tools/
[/Sources]
-->

---

<p class="kicker">Concept 02 · tool boundary</p>

# Read models in. Commands out.

<div class="two-columns">
  <div class="panel"><h3>Expose</h3><ul><li>customer search</li><li>membership details</li><li>plan and invoices</li></ul></div>
  <div class="panel dark"><h3>Do not expose</h3><ul><li>command gateway</li><li>aggregate internals</li><li>“execute anything” endpoints</li></ul></div>
</div>

<div class="statement">Least capability is more useful than pretending every tool call is equally safe.</div>

<!--
Story so far: tools can ground an answer, but every exposed tool also expands what the model can cause. The correct question is therefore not “Which APIs already exist?” but “Which capabilities does this use case need?”

Definition — capability boundary: the set of operations an agent is allowed to select. A capability should be expressed in domain and application language, not as unrestricted technical access.

Definition — least capability: give the agent only the smallest permissions and operations required for the current outcome. This is the agent equivalent of least privilege.

Fitness example: staff need customer search, membership details, plans, invoices, history, and allowed actions to assess a case. They do not need the aggregate, command gateway, arbitrary HTTP access, or a tool named `executeCommand`. Those would allow the model to bypass the safe proposal boundary.

Read model versus command: a read model answers what the application currently knows. A command expresses an intention to change business state and must pass authorization and domain invariants. Both can technically be tools, but they do not have the same risk.

Capability ladder:
1. `getMembershipDetails` reads current state. Expose it.
2. `getAllowedMembershipActions` calculates deterministic options. Expose it as decision support.
3. “Propose PAUSE” produces a draft that Kotlin validates. Return it to staff.
4. `pauseMembership` changes business state. Do not expose it in this workshop; a production version would need authorization, confirmation, idempotency, and the aggregate command path.

Code connection: `MembershipStaffReadService` offers purpose-specific read methods over repositories and projections. `MembershipStaffTools` exposes those methods. No mutating tool depends on an Axon command gateway, and the final workflow still returns a proposal rather than executing it.

Transition: with the boundary chosen, Exercise 2 implements the read capabilities and makes their use observable.
-->

---

<!-- _class: practice -->

<p class="kicker">Concrete practice 02 · 35 minutes coding</p>

# Ground the answer with read tools

1. Implement five annotation-based tools.
2. Serialize projected data as compact JSON.
3. Register the `ToolSet` in Koog.
4. Record a completed-call trace for the UI.

<div class="checkpoint">start: exercise/01-basic-agent → solution: exercise/02-read-tools</div>

<div class="acceptance">
  <div>Search from name or email</div><div>Follow real identifiers</div>
  <div>Trace selected tools</div><div>No write capability</div>
</div>

<!--
Story so far: the agent has a loop and a safe capability boundary. Exercise 2 connects the two by registering five read tools and letting the model assemble a grounded investigation.

Expected reasoning path: for “Find Maya and explain her membership,” the agent should normally call `searchCustomers`, use the returned customer ID with `getMembershipsForCustomer`, then inspect `getMembershipDetails`. A later question may require `getPlan` or `getInvoicesForMembership`. The model selects the path; the application constrains every available step.

Prompt examples for the exercise:
1. “Find Maya and report her current membership status.” Expected chain: `searchCustomers` → `getMembershipsForCustomer` → `getMembershipDetails`.
2. “Which plan is Maya on, and what does it cost?” Expected chain: locate Maya and her membership, then call `getPlan` with the returned plan ID.
3. “Does Maya have any unpaid invoices?” Expected chain: locate the membership, then call `getInvoicesForMembership`.
4. Create two customers named Maya. A safe result asks staff to disambiguate instead of silently choosing the first match.

Code walkthrough:
1. `MembershipStaffTools.kt` declares annotation-based functions and argument descriptions.
2. `MembershipStaffReadService.kt` converts repositories and projections into compact tool-facing DTOs.
3. `KoogMembershipStaffAgent.kt` builds a `ToolRegistry` and registers the `ToolSet`.
4. Each completed tool call adds a `ToolCallTrace` summary that the Angular console can display.

Definition — tool trace: an observable record of which capabilities completed during the run. It helps explain the execution path, but it is not proof that the model interpreted every result correctly. Avoid storing secrets or full sensitive payloads merely for observability.

Experiment: make one description ambiguous, for example describe `getPlan` as “get details.” Compare whether the model still selects the intended tool. This demonstrates that tool descriptions are part of agent behavior, not documentation added afterward.

Transition: after the tools work, inspect the answer and ask whether grounded data is enough to make a proposed action safe.

[Sources]
- https://docs.koog.ai/tools/annotation-based-tools/
[/Sources]
-->

---

<p class="kicker">Debrief 02</p>

# Grounded does not mean safe to execute

<div class="two-columns">
  <div class="panel"><h3>Better</h3><p>The answer is based on current application data.</p></div>
  <div class="panel"><h3>Still probabilistic</h3><p>The model may misread a result, choose the wrong member, or recommend an invalid action.</p></div>
</div>

<div class="statement">Tool results are evidence for reasoning—not permission to mutate state.</div>

<!--
Story so far: the agent can now follow real identifiers and read current projections. Its answer is more defensible because the trace shows which capabilities supplied context. Yet the model still performs the final interpretation.

Important distinction:
- Grounded means the model received relevant application facts.
- Correct means its conclusion accurately follows from those facts.
- Authorized means the current user and workflow may perform the resulting action.
These are three different claims.

Fitness example: the tools may return two customers named Maya. The model can choose the wrong one. It may also read an ACTIVE membership correctly and still propose `REACTIVATE`, a transition that makes no sense for that status. Tool access prevents some hallucinations; it does not make the reasoning deterministic.

Three grounded-but-wrong outcomes:
- Identity error: both Mayas are real, but the model follows the wrong returned customer ID.
- Interpretation error: invoices include one PAID and one OVERDUE entry, but the summary says “there is no outstanding balance.”
- Action error: membership status is correctly read as ACTIVE, yet the model recommends `REACTIVATE`.
The first may require disambiguation in the interaction, the second needs evaluation and clearer evidence presentation, and the third needs deterministic action validation.

Evidence versus permission: a tool result can support the sentence “the membership is ACTIVE.” It does not grant permission to change the membership. Permission comes from application policy, authorization, and eventually human intent.

Code connection: inspect the tool trace returned from `KoogMembershipStaffAgent`. It records selected tools and compact result summaries. Notice that nothing in Exercise 2 validates a final `proposedAction` or evidence reference.

Self-check: which failures belong in better tool design, and which require a separate deterministic validation step?

Transition: Exercise 3 first gives the model output a predictable shape, then validates the claims inside that shape.
-->

---

<p class="kicker">Concept 03 · structured output</p>

# Structure is an integration contract

```kotlin
data class AgentAssessmentDraft(
    val membershipId: String?,
    val summary: String,
    val evidenceReferences: List<String>,
    val proposedAction: MembershipAction?
)
```

<div class="statement">Structured output = machine-readable shape. It constrains syntax, not truth.</div>

<!--
Story so far: Exercise 2 grounds the model in real data, but the application still receives prose whose structure may vary. Before deterministic code can validate a proposal, the model and application need an explicit integration contract.

Definition — structured output: a model response constrained to a machine-readable schema such as a Kotlin data class or JSON object. Structure makes fields addressable and parse failures manageable.

Definition — schema: the expected fields, types, and nesting of a response. A schema can require a `proposedAction` field to contain a membership action or null. It cannot prove that the chosen action is valid for the current membership.

Definition — draft: structured model output that remains untrusted until application validation. Naming the class `AgentAssessmentDraft` makes that status visible in the design.

Fitness example: valid JSON can contain `membershipId = "invented"`, `evidenceReferences = ["membership-event:999"]`, and `proposedAction = REACTIVATE`. Parsing succeeds, but none of those claims are thereby verified.

Concrete draft that is syntactically valid and semantically unsafe:
```json
{
  "membershipId": "membership-1",
  "summary": "The active membership can be reactivated.",
  "evidenceReferences": ["membership-event:invented"],
  "proposedAction": "REACTIVATE"
}
```
Jackson can parse every field. The schema has done its job. The validator must still compare the ID, evidence, and action with current application state.

Code connection: `StaffAssistantModels.kt` defines `AgentAssessmentDraft` separately from `MembershipCaseAssessment`. In the current checkpoint, `KoogMembershipStaffAgent.parseDraft` strips an optional code fence and uses Jackson. A parse failure produces a conservative draft with no proposed action. Koog also provides native structured-output APIs; the workshop keeps validation as a separate concern regardless of parsing mechanism.

Transition: once a draft has fields, deterministic Kotlin code can compare them with current state and known evidence.

[Sources]
- https://docs.koog.ai/structured-output/
[/Sources]
-->

---

<p class="kicker">Concept 03 · structured output</p>

# Structure gives us a draft—not truth

<div class="three-columns">
  <div class="panel"><h3>Structured output</h3><p>A model response constrained to a machine-readable shape.</p></div>
  <div class="panel"><h3>Schema</h3><p>Expected fields, types, and nesting. Form—not correctness.</p></div>
  <div class="panel"><h3>Draft</h3><p>Structured model output that stays untrusted until application validation.</p></div>
</div>

<div class="statement">Addressable fields make validation possible. They do not make the content true.</div>

<!--
Story so far: Exercise 2 grounds the model in real data, but the application still receives prose whose structure may vary. Before deterministic code can validate a proposal, the model and application need an explicit integration contract.

Definition — **structured output**: a model response constrained to a machine-readable schema such as a Kotlin data class or JSON object. Structure makes fields addressable and parse failures manageable.

Definition — **schema**: the expected fields, types, and nesting of a response. A schema can require a `proposedAction` field to contain a membership action or null. It cannot prove that the chosen action is valid for the current membership.

Definition — **draft**: structured model output that remains untrusted until application validation. Naming the class `AgentAssessmentDraft` makes that status visible in the design.

Read the three definitions together: structured output is the response form, the schema describes that form, and the draft is the trust status of the result. These concepts solve an integration problem; they do not solve semantic correctness.

Transition: the next slide shows a draft that satisfies the format but remains unsafe to accept.

[Sources]
- https://docs.koog.ai/structured-output/
[/Sources]
-->

---

<p class="kicker">Concept 03 · structured output</p>

# Valid JSON can still be wrong

```json
{
  "membershipId": "membership-1",
  "summary": "The active membership can be reactivated.",
  "evidenceReferences": ["membership-event:invented"],
  "proposedAction": "REACTIVATE"
}
```

<div class="statement">Valid JSON. Unsafe meaning: invented evidence + forbidden action.</div>

<!--
Story so far: the model and application now share a schema, so parsing can succeed. This concrete draft demonstrates why syntactic validity is only the beginning.

Concrete draft that is syntactically valid and semantically unsafe:

```json
{
  "membershipId": "membership-1",
  "summary": "The active membership can be reactivated.",
  "evidenceReferences": ["membership-event:invented"],
  "proposedAction": "REACTIVATE"
}
```

Why it is syntactically valid:
- It is valid JSON.
- Every field has the expected name and type.
- `proposedAction` contains a known membership-action value.

Why it is semantically unsafe:
- The summary claims that an ACTIVE membership can be reactivated.
- `membership-event:invented` is not evidence returned by the application.
- `REACTIVATE` is not allowed for an ACTIVE membership.
- The schema cannot determine any of these facts.

Teaching move: ask participants whether parsing should succeed. It should. Then ask whether the application should accept the proposal. It should not. This separates integration success from business validity.

Transition: the next slide introduces the deterministic guardrail that compares the draft with current state and known evidence.

[Sources]
- https://docs.koog.ai/structured-output/
[/Sources]
-->
---

<p class="kicker">Concept 03 · guardrails</p>

# Draft → deterministic assessment

<div class="flow">
  <div class="node"><strong>Agent draft</strong><span>REACTIVATE + unknown ref</span></div>
  <div class="node"><strong>Current state</strong><span>ACTIVE + event:42</span></div>
  <div class="node accent-node"><strong>Kotlin policy</strong><span>verify and filter</span></div>
  <div class="node"><strong>Safe result</strong><span>no action + warnings</span></div>
</div>

<div class="statement">Never ask the same probabilistic component to police its own mistake.</div>

<!--
Story so far: the model can now produce a predictable draft, but predictable syntax is not trustworthy semantics. A guardrail must be owned by a component that does not share the model's uncertainty.

Definition — guardrail: an application control that constrains, checks, transforms, or rejects agent output before it becomes an accepted result or consequential action.

Read the diagram as a join. The agent supplies a proposal and evidence references. The application independently reloads the current membership snapshot and the set of known evidence references. `MembershipProposalValidator` then calculates possible actions, removes invalid proposals, filters unknown evidence, and emits warnings.

Fitness example: an ACTIVE membership permits `PAUSE` or `CANCEL`. If the model proposes `REACTIVATE`, the validator returns no proposed action and adds a warning. If it cites `membership-event:invented`, that reference is removed because it is absent from the history projection.

Worked before and after:
- Draft: membership `membership-1`, proposed action `REACTIVATE`, evidence `membership-event:42` and `membership-event:invented`.
- Reloaded context: membership `membership-1` is ACTIVE; known evidence contains only `membership-event:42`; possible actions are `PAUSE` and `CANCEL`.
- Safe assessment: `proposedAction = null`, relevant evidence contains only `membership-event:42`, warnings explain both removals, and `requiresHumanConfirmation = true`.

Why the model cannot validate itself: asking the same model to “double-check carefully” may improve an answer, but both passes remain probabilistic and may share the same misconception. Deterministic code provides an independent guarantee.

Code connection: open `MembershipProposalValidator.kt`. `MembershipActionPolicy.allowedFor(status)` calculates the allowed set. `takeIf` retains only allowed proposals. Evidence is filtered against `knownEvidenceReferences`, and `requiresHumanConfirmation` is always set by Kotlin code.

Transition: the next slide expands the guardrail into a concrete join between a model draft and independently reloaded application truth.
-->

---

<p class="kicker">Concept 03 · application validation</p>

# Validation joins a draft with current truth

<div class="validation-join">
  <div class="join-box join-proposal"><strong>Agent proposal</strong><span>action = REACTIVATE<br>evidence = event:42 + invented</span></div>
  <div class="join-arrow join-arrow-proposal">↘</div>
  <div class="join-box join-facts"><strong>Application facts</strong><span>status = ACTIVE<br>known evidence = event:42</span></div>
  <div class="join-arrow join-arrow-facts">↗</div>
  <div class="join-box join-validator"><strong>MembershipProposal<br>Validator</strong><span>calculate actions<br>filter evidence<br>emit warnings</span></div>
  <div class="join-arrow join-arrow-result">→</div>
  <div class="join-box join-result"><strong>Validated assessment</strong><span>action = null<br>evidence = event:42<br>warnings = 2</span></div>
</div>

<div class="statement compact">ACTIVE allows PAUSE or CANCEL. REACTIVATE and invented evidence are removed.</div>

<!--
Story so far: structured output gave the application addressable fields, and the previous slide introduced a deterministic guardrail. This diagram makes the validation boundary concrete by showing two independent inputs converging on one application-owned validator.

Read the diagram as a join. The agent supplies a proposal and evidence references. The application independently reloads the current membership snapshot and the set of known evidence references. `MembershipProposalValidator` then calculates possible actions, removes invalid proposals, filters unknown evidence, and emits warnings.

The independence matters. The validator does not ask the model whether its own proposal is valid, and it does not trust status or evidence data repeated inside the draft. It reads current facts through application-owned interfaces.

Fitness example: an ACTIVE membership permits `PAUSE` or `CANCEL`. If the model proposes `REACTIVATE`, the validator returns no proposed action and adds a warning. If it cites `membership-event:invented`, that reference is removed because it is absent from the history projection.

Read the inputs:
- Agent proposal: `proposedAction = REACTIVATE`; evidence contains `membership-event:42` and `membership-event:invented`.
- Application facts: membership `membership-1` is ACTIVE; the known evidence set contains only `membership-event:42`.

Read the validator:
1. `MembershipActionPolicy.allowedFor(ACTIVE)` calculates `PAUSE` and `CANCEL`.
2. `REACTIVATE` is not in that set, so `proposedAction` becomes null and a warning is emitted.
3. Evidence references are intersected with the known set, so `membership-event:invented` is removed and another warning is emitted.
4. `requiresHumanConfirmation` remains true because Kotlin code—not the draft—owns the response contract.

Read the result: the explanation can remain useful, but the consequential fields have been constrained by current application truth. The validated assessment contains no action, retains only `membership-event:42`, and exposes two warnings to the staff UI.

Code connection: compare `AgentAssessmentDraft`, `MembershipActionPolicy`, and `MembershipProposalValidator`. The first is probabilistic input; the latter two are deterministic application controls.

Facilitation: cover the result and ask participants to predict it from the two inputs. Then reveal the result and ask which component owns each removal.

Transition: Exercise 3 implements this join and makes the difference between the raw draft and validated assessment visible in Angular.
-->

---

<!-- _class: practice -->

<p class="kicker">Concrete practice 03 · 35 minutes coding</p>

# Structure, then validate

1. Add the deterministic allowed-actions tool.
2. Request one `AgentAssessmentDraft` JSON object.
3. Parse safely; malformed output produces no action.
4. Compare the draft with the final validated card.

<div class="checkpoint">start: exercise/02-read-tools → solution: exercise/03-structured-assessment</div>

<div class="acceptance">
  <div>Typed draft</div><div>Invalid action removed</div>
  <div>Unsupported evidence removed</div><div>Confirmation always required</div>
</div>

<!--
Story so far: we have separated structured model output from trusted application output. Exercise 3 implements that separation and makes failure safe rather than invisible.

Implementation sequence:
1. Add `getAllowedMembershipActions` so the model can see deterministic policy while drafting.
2. Update the system prompt to return one `AgentAssessmentDraft` JSON object.
3. Parse defensively. Malformed output becomes a draft with the raw summary and no action.
4. Load the membership snapshot and known evidence independently of the draft.
5. Validate the draft and return the final `MembershipCaseAssessment` card.

Example experiment: ask the model to reactivate an already ACTIVE membership. Compare `possibleActions`, `proposedAction`, and `warnings` in the UI. Then make the draft reference a nonexistent event and observe that the unsupported reference disappears.

Safety behavior: the validator does not silently replace an invalid action with what it thinks the model meant. It removes the proposal and reports why. This keeps deterministic correction observable to staff.

Code connection: compare `KoogMembershipStaffAgent.kt`, `MembershipProposalValidator.kt`, `MembershipStaffReadService.allowedActions`, and the assessment types in `StaffAssistantModels.kt` on `exercise/03-structured-assessment`.

Transition: after the solution, identify exactly which guarantee each layer now provides—and which none of them can provide alone.

[Sources]
- https://docs.koog.ai/structured-output/
[/Sources]
-->

---

<p class="kicker">Debrief 03</p>

# Where does trust come from?

| Layer | Useful guarantee |
|---|---|
| Schema / parser | Shape |
| Tool adapter | Controlled access |
| Application validator | Current-state consistency |
| Domain model | Business invariants |
| Human confirmation | Intent and accountability |

<!--
Story so far: Exercise 3 adds several controls, but “trust” is still too vague to be assigned to one component. This table decomposes trust into useful, testable guarantees.

Read the layers from top to bottom:
- Schema/parser: the response has a usable shape or fails safely.
- Tool adapter: the model can only access selected application capabilities.
- Application validator: the proposal is consistent with the freshly loaded membership state and known evidence.
- Domain model: business invariants hold when a command is eventually executed.
- Human confirmation: a responsible staff member supplies intent and accountability.

Fitness example: a parsed `PAUSE` proposal may be grounded and allowed for an ACTIVE membership, yet staff may still decide not to pause it. Conversely, a human click must not override aggregate invariants. Each layer answers a different question.

Which layer catches which example?
- Missing `proposedAction` field or malformed JSON → schema/parser.
- Model attempts an unavailable `cancelMembership` tool → capability boundary; the tool does not exist.
- ACTIVE membership with drafted `REACTIVATE` → application validator.
- Future confirmed pause of only 20 days → domain model and `PausePeriod` invariant.
- Valid PAUSE that the staff member does not intend to apply → human confirmation.
- Misleading prose with a technically allowed action → no current deterministic layer fully proves summary quality; this needs evaluation cases and staff review.

Code connection: follow one field through the code. `AgentAssessmentDraft.proposedAction` begins as model output. `MembershipProposalValidator` filters it into `MembershipCaseAssessment.proposedAction`. The workshop stops there: no command gateway is invoked. The aggregate would remain the final authority in a future execution step.

Self-check: if the summary contains a misleading sentence but the action is valid, which guarantee failed? The schema did not fail; this is a reasoning or evaluation problem.

Transition: the assessment is now safer, but every request is still isolated. A follow-up such as “Why did that happen?” introduces the next missing capability: context over time.
-->

---

<p class="kicker">Concept 04 · memory</p>

# Four different things called “memory”

| Kind | What it remembers | Role today |
|---|---|---|
| Chat history | “It” = `membership-1` | Implement |
| Domain event history | PAUSED on 12 Aug | Implement |
| Agent checkpoint | Resume after history tool | Outlook |
| Semantic retrieval | Find the pause policy | Outlook |

<div class="statement">Chat memory resolves language. Domain events establish facts.</div>

<!--
Story so far: we can produce a grounded and validated assessment for one request. Real staff conversations contain follow-ups, pronouns, and references to earlier answers, so the agent now needs state. The word “memory” hides several different mechanisms.

Definitions:
- Chat history: prior user and assistant messages for one conversation. It helps resolve language such as “that member” or “why?”
- Domain event history: authoritative facts describing state changes, such as MEMBERSHIP_PAUSED. It explains what happened to the business object.
- Agent checkpoint: saved execution state that lets an interrupted agent run resume. It is not chat history.
- Semantic retrieval: selecting relevant knowledge using embeddings or another search mechanism. It is often called long-term memory but is outside this workshop.

Fitness example: chat history tells the model that “it” refers to Maya's membership. Domain history tells it that the membership was paused from one date to another. Neither can replace the other.

One example for each kind:
- Chat history: staff first asks “Show Maya's membership,” then follows with “What plan is it on?” The previous turn resolves “it”; the plan tool supplies the current answer.
- Domain event history: `membership-event:42` records that `membership-1` was paused on 12 August. This is evidence about what happened, even after the chat is gone.
- Agent checkpoint: a long-running investigation times out after the history tool. A checkpoint could resume the same execution instead of starting the loop again; we do not implement this.
- Semantic retrieval: the agent searches a large staff handbook for the relevant pause-policy paragraph. That is retrieval of knowledge, not conversation continuity or business history; it remains an outlook topic.

Code connection: `InMemoryConversationHistory.kt` supplies Koog's `ChatHistoryProvider`. `MembershipHistoryProjection.kt` listens to domain events and stores semantic history entries with evidence references. They have different lifetimes, owners, and authority.

Important boundary: a previous assistant message may contain an outdated or wrong fact. The agent should reread application tools when current truth matters instead of treating its own conversation as a database.

Transition: the next slide applies both histories to one deceptively simple follow-up question.

[Sources]
- https://docs.koog.ai/features/chat-memory/
- https://docs.koog.ai/features/agent-persistence/
[/Sources]
-->

---

<p class="kicker">Concept 04 · temporal context</p>

# “Why is it paused?” needs two histories

<div class="two-columns">
  <div class="panel"><h3>Conversation history</h3><p>“It” refers to Maya's membership from the previous turn.</p></div>
  <div class="panel"><h3>Domain history</h3><p><code>membership-event:42</code> says when and why the lifecycle changed.</p></div>
</div>

<div class="statement">Resolve the reference with chat context. Answer the question with domain evidence.</div>

<!--
Story so far: the memory slide separated conversation from domain truth. “Why is it paused?” shows why the agent needs both in the same response.

Definition — referent: the person or object a word such as “it,” “that,” or “the previous one” points to. Resolving a referent is a language problem, not a domain lookup.

Definition — temporal context: information about how state changed over time. A current projection may say PAUSED, while event history explains when the pause began and which business event recorded it.

Walk through the example:
1. The previous turn identified Maya and membership `membership-1`.
2. Chat history lets the model resolve “it” to that membership.
3. The agent calls `getMembershipHistory("membership-1")`.
4. The tool returns semantic events such as `membership-event:42` with type, timestamp, and details.
5. The final answer cites the evidence reference rather than claiming the conversation itself proves the fact.

Change the question and notice the authority changes. “Why is it paused?” needs chat history to resolve “it” and domain history to explain why. “What plan is it on now?” still needs chat history for “it,” but the current membership and plan projections—not event history—supply the answer. “What did I ask you before?” can be answered from chat history alone.

Code connection: `MembershipHistoryProjection` converts Axon lifecycle events into staff-readable entries. `MembershipStaffReadService.history` maps them to tool results. `evidenceReferences` later reloads the known set for validation. This is deliberately not direct access to Axon's event store.

Transition: Exercise 4 wires the session history and semantic event tool into the Koog run.

[Sources]
- https://docs.koog.ai/features/chat-memory/chat-backend-with-memory/
[/Sources]
-->

---

<!-- _class: practice -->

<p class="kicker">Concrete practice 04 · 40 minutes coding</p>

# Add context without confusing truth

1. Add the semantic membership-history tool.
2. Install `ChatMemory` with a bounded window.
3. Share a thread-safe `ChatHistoryProvider`.
4. Use the HTTP conversation ID as Koog's session ID.

<div class="checkpoint">start: exercise/03-structured-assessment → solution: exercise/04-context-and-memory</div>

<div class="acceptance">
  <div>Follow-up works</div><div>New session is isolated</div>
  <div>Known event citations only</div><div>Conversation is not truth</div>
</div>

<!--
Story so far: Exercise 4 makes the agent conversationally stateful while preserving the distinction between context and truth.

Definitions:
- Session ID: an application-supplied identifier that isolates one conversation's messages from another.
- Bounded window: a maximum number of recent messages supplied to the model. Bounding controls cost and noise but may forget older details.
- `ChatHistoryProvider`: Koog's storage abstraction for loading and saving messages by session.

Code walkthrough: `KoogMembershipStaffAgent` installs `ChatMemory`, injects the shared provider, configures `windowSize(20)`, and runs the agent with `agent.run(message, conversationId)`. The shared provider matters because a new agent instance is created for each HTTP request; the conversation must outlive that object.

`InMemoryConversationHistory` is thread-safe and sufficient for the exercise, but it is intentionally not durable. Restarting the backend removes chat history. The domain history remains available because it is a projection stored in PostgreSQL.

Test three cases:
1. Ask a follow-up using the same conversation ID; the referent should resolve.
2. Use a new conversation ID; prior chat must not leak.
3. Restart the service; chat context disappears while membership history can still be queried.

Code connection: compare `KoogMembershipStaffAgent.kt`, `InMemoryConversationHistory.kt`, `MembershipHistoryProjection.kt`, and the history tool in `MembershipStaffTools.kt` on `exercise/04-context-and-memory`.

Transition: state improves usability, but every stateful mechanism introduces new failure modes that must be designed explicitly.

[Sources]
- https://docs.koog.ai/features/chat-memory/
[/Sources]
-->

---

<p class="kicker">Debrief 04</p>

# State creates new failure modes

<div class="three-columns">
  <div class="panel"><h3>Isolation</h3><p>Staff B reuses <code>conversation-7</code> and sees Maya’s context.</p></div>
  <div class="panel"><h3>Freshness</h3><p>Chat says ACTIVE after a new pause event.</p></div>
  <div class="panel"><h3>Growth</h3><p>Eighty old turns hide the newest evidence.</p></div>
</div>

<div class="statement">State needs identity, lifetime, bounds, and an authority model.</div>

<!--
Story so far: the agent can now understand a follow-up and cite temporal evidence. That success introduces risks that the stateless version did not have.

Isolation failure: if two users share a conversation ID, one staff member may receive another member's context. Controls include authenticated session ownership, unguessable IDs, tenant scoping, and authorization checks around every tool.

Freshness failure: the conversation may remember that a membership was ACTIVE even after it was paused. The control is to reread current application state for consequential answers and treat assistant messages as context, not authority.

Growth failure: unbounded history increases token cost, latency, and irrelevant context. A bounded window controls size, while summarization or persistent retrieval would require additional correctness and privacy decisions.

Concrete failure and control pairs:
- Staff B accidentally submits `conversation-7`, which belongs to Staff A's Maya investigation → bind session IDs to authenticated staff and tenant ownership.
- The assistant previously said ACTIVE, but `MembershipPausedEvent` has since updated the projection → reread current state before every consequential assessment.
- Eighty prior messages bury the most relevant event and expand the prompt → use the bounded window, then design summarization or retrieval as a separate production feature.

Definition — lifetime: how long state should exist. The exercise uses in-memory chat for one process lifetime and persistent domain history for business history. Production systems need explicit retention and deletion rules.

Code connection: `windowSize(20)` is a visible bound, but it is not a complete production policy. `conversationId` is required and nonblank, but the exercise does not yet authenticate ownership. These are deliberate teaching seams, not hidden production claims.

Transition: after five capabilities have accumulated, orchestration and safety responsibilities are spread across the HTTP path. The final increment makes that mixed workflow explicit.
-->

---

<p class="kicker">Concept 05 · controlled workflow</p>

# Make the mixed workflow explicit

<div class="flow">
  <div class="node"><strong>Investigate</strong><span>probabilistic</span></div>
  <div class="node"><strong>Structure</strong><span>probabilistic</span></div>
  <div class="node accent-node"><strong>Validate</strong><span>deterministic</span></div>
  <div class="node"><strong>Present</strong><span>stable contract</span></div>
</div>

<div class="statement">Controlled workflow = probabilistic investigation inside deterministic orchestration.</div>

<!--
Story so far: the agent can investigate, structure, validate, and remember, but these activities have different reliability characteristics. Treating the whole request as “the AI” hides which guarantees the application actually owns.

Definition — controlled workflow: a sequence in which probabilistic steps are deliberately composed with deterministic steps and a stable external contract. Control comes from the composition, not from forcing the model to behave like ordinary code.

Read the flow:
- Investigate: the model decides which read tools to call and how to interpret the results.
- Structure: the model expresses its assessment as a draft.
- Validate: Kotlin reloads current state, checks policy and evidence, and emits warnings.
- Present: the application returns the stable HTTP response consumed by Angular.

Fitness example: the model may investigate Maya's history differently across runs. The validator must nevertheless remove the same forbidden `REACTIVATE` proposal for the same ACTIVE membership. Variability is acceptable in explanation, not in the invariant.

End-to-end example:
1. Staff asks “What happened to Maya, and what can we propose?”
2. Investigate: Koog selects customer, membership, history, invoice, and allowed-action tools as needed.
3. Structure: the model drafts membership `membership-1`, cites `membership-event:42`, and proposes `REACTIVATE`.
4. Validate: the application reloads an ACTIVE membership, keeps the known event, removes `REACTIVATE`, and adds a warning.
5. Present: Angular receives the same `MembershipCaseAssessment` contract whether the draft was accepted or corrected.

Code connection: `AssessMembershipCase.execute` names the application workflow. It invokes the `MembershipStaffAgent` port, asks `MembershipCaseContext` for current state and known evidence, runs `MembershipProposalValidator`, and creates `StaffAssistantMessageResponse`.

Koog strategy graphs can make model-driven flows explicit. For this beginner workshop, the application use case exposes the key seam with less framework complexity. Graph strategies remain an outlook, not a missing requirement.

Transition: once the workflow has an application-owned boundary, we can test its guarantees without a real model.

[Sources]
- https://docs.koog.ai/custom-strategy-graphs/
- https://docs.koog.ai/agents/graph-based-agents/
[/Sources]
-->

---

<p class="kicker">Concept 05 · test seams</p>

# Test behavior at the boundary you own

<div class="two-columns">
  <div class="panel"><h3>Fast behavior test</h3><p>Fake the agent draft. Exercise the real validator and use case.</p></div>
  <div class="panel"><h3>Focused integration test</h3><p>Test Koog tools and provider wiring separately when needed.</p></div>
</div>

<div class="statement">Your core safety test should not need an API key, network, or model mood.</div>

<!--
Story so far: `AssessMembershipCase` gives the mixed workflow a name and a boundary. The testing question is now where to replace probabilistic behavior and where to keep real code.

Definition — test seam: a stable boundary where one component can be replaced in a test without rewriting the behavior under test.

Definition — fake: a lightweight implementation with predictable behavior. `FakeMembershipStaffAgent` returns a prepared draft and does not try to simulate Koog's internal call sequence.

Fast behavior test: fake the agent, keep the real use case, policy, and validator, and assert externally meaningful outcomes. Examples include retaining an allowed PAUSE proposal, removing forbidden REACTIVATE, filtering invented evidence, and always requiring confirmation.

Concrete test specimen: `FakeMembershipStaffAgent` returns membership `membership-1`, summary “The active membership could be reactivated,” proposed action `REACTIVATE`, and evidence `membership-event:invented`. `FakeMembershipCaseContext` returns an ACTIVE snapshot and no known evidence. The expected assessment has `proposedAction = null`, an empty evidence list, warnings for the forbidden action and unknown evidence, and `requiresHumanConfirmation = true`.

Positive counterexample: when the same ACTIVE snapshot receives proposed action `PAUSE` with known evidence `membership-event:1`, the validator retains both. Testing acceptance and rejection prevents a guardrail that simply discards every proposal.

Focused integration test: when necessary, test tool serialization, Spring provider wiring, or a real model call separately. Those tests may require credentials and can be slower or more variable, so they should not carry the core safety guarantee.

Why not mock every Koog call: such tests become coupled to internal framework interactions and can pass while application behavior is wrong. The owned boundary is `MembershipStaffAgent.run`, not every prompt executor method.

Code connection: open `AssessMembershipCaseTest.kt`. The tests use `FakeMembershipStaffAgent` and `FakeMembershipCaseContext` while exercising the real `MembershipProposalValidator` and `MembershipActionPolicy`.

Transition: Exercise 5 extracts this seam and proves the safety behavior without `GOOGLE_API_KEY`.

[Sources]
- https://docs.koog.ai/testing/
[/Sources]
-->

---

<!-- _class: practice -->

<p class="kicker">Concrete practice 05 · 40 minutes coding</p>

# Extract and test the use case

1. Introduce the application-owned `MembershipStaffAgent` port.
2. Move orchestration into `AssessMembershipCase`.
3. Keep the controller as an HTTP adapter.
4. Test allowed, forbidden, and unsupported proposals with a fake agent.

<div class="checkpoint">start: exercise/04-context-and-memory → solution: exercise/05-controlled-workflow</div>

<div class="acceptance">
  <div>No API key in tests</div><div>Public application boundary</div>
  <div>Warnings observable</div><div>HTTP contract unchanged</div>
</div>

<!--
Story so far: the system has all workshop capabilities. Exercise 5 reorganizes them so the application—not the controller and not Koog—owns the workflow and its safety tests.

Definitions:
- Port: a purpose-specific interface through which the application talks to an external or variable component. Here, `MembershipStaffAgent` hides Koog behind `run(message, conversationId)`.
- Use case: application code that coordinates one business-facing outcome. `AssessMembershipCase` turns an agent draft plus current context into the response.
- Adapter: an implementation that connects a port to technology. `KoogMembershipStaffAgent` is the Koog adapter; `StaffAssistantController` is the HTTP adapter.

Refactoring path:
1. Introduce `MembershipStaffAgent` without changing HTTP behavior.
2. Move agent invocation, context loading, validation, and response construction into `AssessMembershipCase`.
3. Leave the controller responsible for request mapping and status translation.
4. Add behavior tests using a fake agent and fake context.

Compare before and after: the Koog adapter remains free to change its prompt, tools, or parsing strategy. The use-case tests continue to prove deterministic validation because they depend only on the port contract.

Two solution examples to present:
- Allowed path: fake draft proposes `PAUSE` for ACTIVE and cites `membership-event:1`; the assessment retains both and requires confirmation.
- Rejected path: fake draft proposes `REACTIVATE` and cites `membership-event:invented`; the assessment removes both, returns warnings, and still preserves the HTTP contract.

Fast-finisher experiment: alter the fake draft to include both an invented evidence reference and a forbidden action. Predict the final assessment before running the test.

Transition: the five solutions now form a complete learning and recovery chain. The final slides reconnect them to the opening question.

[Sources]
- https://docs.koog.ai/testing/
[/Sources]
-->

---

<p class="kicker">Recovery map</p>

# Every branch is a working checkpoint

<div class="branch-flow">
  <div class="branch">exercise/00-start <span>prepared infrastructure + exercise seam</span></div>
  <div class="branch">exercise/01-basic-agent <span>fluent but ungrounded</span></div>
  <div class="branch">exercise/02-read-tools <span>grounded but proposals remain probabilistic</span></div>
  <div class="branch">exercise/03-structured-assessment <span>validated but stateless</span></div>
  <div class="branch">exercise/04-context-and-memory <span>contextual but orchestration remains mixed</span></div>
  <div class="branch">exercise/05-controlled-workflow <span>explicit and testable</span></div>
</div>

<!--
Story: the branch map is also a map of the system's changing guarantees. Each checkpoint works, preserves previous behavior, and intentionally leaves one reason to continue.

Read the exact chain:
- `exercise/00-start`: infrastructure, UI, API contract, and deliberate exercise seam.
- `exercise/01-basic-agent`: fluent model response, still ungrounded.
- `exercise/02-read-tools`: grounded investigation, still probabilistic in its proposal.
- `exercise/03-structured-assessment`: structured and validated proposal, still stateless between turns.
- `exercise/04-context-and-memory`: conversational continuity plus event evidence, with orchestration still mixed.
- `exercise/05-controlled-workflow`: explicit use case, agent port, and deterministic behavior tests.

Definition — recovery checkpoint: a known working state that lets learning continue after a local implementation problem. Switching to a solution is not failure; it preserves the cumulative learning path.

Useful commands:
`git switch exercise/03-structured-assessment`
`git diff exercise/02-read-tools..exercise/03-structured-assessment -- fitness_management_system`

Self-study method: demonstrate the behavior before reading the diff. Then identify the new capability, the failure that remains, and which layer owns the next control.

Transition: the final conclusion can now answer the opening question with concrete architecture rather than a label.
-->

---

<!-- _class: dark-title -->

<p class="kicker">Conclusions</p>

# Agents need architecture

<p class="lead">Give models narrow capabilities, ground claims in application facts, separate kinds of state, and put deterministic checks around consequential outcomes.</p>

<!--
Resolution: the completed system is agentic because the model chooses investigation steps and tool calls. It is controlled because the application limits capabilities, reloads authoritative facts, validates proposals deterministically, and stops at human confirmation.

The story in one line: fluent text exposed missing facts; tools exposed unsafe interpretation; structure exposed the need for validation; memory exposed state risks; an application-owned workflow made the guarantees testable.

Central takeaway: agents do not replace software architecture. They make responsibility boundaries more important because probabilistic interpretation must coexist with deterministic business rules, authorization, privacy, observability, and operations.

Code synthesis: `KoogMembershipStaffAgent` investigates and produces a draft. `MembershipStaffTools` exposes narrow reads. `ChatHistoryProvider` supplies conversational context. `MembershipHistoryProjection` supplies temporal evidence. `AssessMembershipCase` orchestrates. `MembershipProposalValidator` protects the result. The aggregate remains business truth, and no command is executed.

Production controls still missing by design: authenticated tool authorization, persistent and privacy-aware sessions, prompt-injection defenses, retries and provider fallback, evaluation datasets, tracing and cost limits, idempotent event-triggered execution, and a reviewed path from proposal to command.

The same pattern transfers beyond fitness:
- Customer support: the agent reads account and ticket history, proposes a remedy, and deterministic policy validates refund eligibility.
- Incident operations: the agent reads alerts and runbooks, explains likely causes, and a reviewed workflow executes operational changes.
- Financial services: the agent summarizes a position and proposes an investigation, while authorization and trading rules prevent it from placing a trade.
The domain changes; the separation between interpretation, evidence, validation, and execution remains.

Final self-check:
1. Which steps are probabilistic?
2. Which guarantees are deterministic?
3. Which source is authoritative for current state and history?
4. Where would you add authorization before exposing a mutating tool?
5. Which tests should work without a provider key?

Return to the opening spectrum and redraw the boundary. The useful answer is no longer merely “agent” or “workflow”; it is an explicit account of where autonomy begins, where it stops, and why.

[Sources]
- https://docs.koog.ai/
[/Sources]
-->
