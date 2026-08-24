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

# Agent, chatbot, or workflow?

<div class="three-columns">
  <div class="panel"><h3>“Answer a question”</h3><p>What makes this more than a prompt?</p></div>
  <div class="panel"><h3>“Choose a tool”</h3><p>Where does useful autonomy begin?</p></div>
  <div class="panel"><h3>“Change a membership”</h3><p>Where should autonomy stop?</p></div>
</div>

<div class="statement">Place each scenario on a spectrum: deterministic workflow → chatbot → agent.</div>

<!--
Story so far: we have promised to build an agent, but that word is used for everything from a prompt wrapper to an autonomous workflow. This spectrum gives us working distinctions without pretending there is one universally accepted cutoff.

Definitions:
- Deterministic workflow: application code selects every next step from explicit rules. The same input and state should lead to the same path.
- Chatbot: a conversational interface that generates a response but normally has no application-owned capabilities beyond the prompt and supplied context.
- Agent: a model participates in selecting the next step, for example choosing whether to answer, call `searchCustomers`, or inspect membership history.

Fitness example: answering “How long may a membership be paused?” can be a chatbot response. Choosing `getMembershipDetails` because the user asks about Maya is agentic. Executing `cancelMembership` would be agentic too, but far more consequential; this workshop intentionally stops before that boundary.

Facilitation: ask participants to place all three scenarios on the spectrum and explain the criterion they used. Collect criteria such as model choice, available tools, remembered state, reversibility, and consequence. Do not force agreement yet.

Transition: the five increments will move gradually along this spectrum while keeping consequential decisions under deterministic control.
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

Transition: the first capability is the smallest possible model-driven loop.
-->

---

<p class="kicker">Concept 01 · the agent loop</p>

# The model chooses the next step

<div class="flow">
  <div class="node"><strong>Goal</strong><span>staff message</span></div>
  <div class="node accent-node"><strong>LLM decision</strong><span>answer or act</span></div>
  <div class="node"><strong>Observation</strong><span>tool result</span></div>
  <div class="node"><strong>Outcome</strong><span>final response</span></div>
</div>

<div class="statement">Agent = model + loop + capabilities + stopping rule. Koog runs the loop; we define the boundary.</div>

<!--
Story so far: we now have a spectrum and a five-step journey. The first technical question is what Koog actually adds around an LLM call.

Definition — agent loop: a runtime cycle in which the model receives a goal and context, chooses either a final response or an action, observes the action result, and decides again. A stopping rule ends the cycle when a final result is produced or a configured limit is reached.

Read the diagram clockwise. The staff message supplies the goal. The LLM decision is probabilistic: it may answer or request a tool. A tool result becomes an observation added to the context. The model then decides again until Koog returns the outcome.

Fitness example: “What should staff consider before pausing a membership?” needs no member-specific tool and can end after one model response. “Why is Maya paused?” will later require several loop iterations: search the customer, locate the membership, read its current state, then inspect history.

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
  <div class="panel"><h3>What worked?</h3><p>Natural language, a stable API, and visible interaction.</p></div>
  <div class="panel"><h3>What is missing?</h3><p>The model cannot inspect a customer, membership, plan, or invoice.</p></div>
</div>

<div class="statement">The next increment adds capability—not a longer prompt.</div>

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

Code connection: `StaffAssistantModels.kt` defines `AgentAssessmentDraft` separately from `MembershipCaseAssessment`. In the current checkpoint, `KoogMembershipStaffAgent.parseDraft` strips an optional code fence and uses Jackson. A parse failure produces a conservative draft with no proposed action. Koog also provides native structured-output APIs; the workshop keeps validation as a separate concern regardless of parsing mechanism.

Transition: once a draft has fields, deterministic Kotlin code can compare them with current state and known evidence.

[Sources]
- https://docs.koog.ai/structured-output/
[/Sources]
-->

---

<p class="kicker">Concept 03 · guardrails</p>

# Draft → deterministic assessment

<div class="flow">
  <div class="node"><strong>Agent draft</strong><span>proposal + evidence</span></div>
  <div class="node"><strong>Current state</strong><span>membership projection</span></div>
  <div class="node accent-node"><strong>Kotlin policy</strong><span>verify and filter</span></div>
  <div class="node"><strong>Safe result</strong><span>warnings + confirmation</span></div>
</div>

<div class="statement">Never ask the same probabilistic component to police its own mistake.</div>

<!--
Story so far: the model can now produce a predictable draft, but predictable syntax is not trustworthy semantics. A guardrail must be owned by a component that does not share the model's uncertainty.

Definition — guardrail: an application control that constrains, checks, transforms, or rejects agent output before it becomes an accepted result or consequential action.

Read the diagram as a join. The agent supplies a proposal and evidence references. The application independently reloads the current membership snapshot and the set of known evidence references. `MembershipProposalValidator` then calculates possible actions, removes invalid proposals, filters unknown evidence, and emits warnings.

Fitness example: an ACTIVE membership permits `PAUSE` or `CANCEL`. If the model proposes `REACTIVATE`, the validator returns no proposed action and adds a warning. If it cites `membership-event:invented`, that reference is removed because it is absent from the history projection.

Why the model cannot validate itself: asking the same model to “double-check carefully” may improve an answer, but both passes remain probabilistic and may share the same misconception. Deterministic code provides an independent guarantee.

Code connection: open `MembershipProposalValidator.kt`. `MembershipActionPolicy.allowedFor(status)` calculates the allowed set. `takeIf` retains only allowed proposals. Evidence is filtered against `knownEvidenceReferences`, and `requiresHumanConfirmation` is always set by Kotlin code.

Transition: Exercise 3 makes this draft-to-assessment boundary visible in both code and UI.
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

Code connection: follow one field through the code. `AgentAssessmentDraft.proposedAction` begins as model output. `MembershipProposalValidator` filters it into `MembershipCaseAssessment.proposedAction`. The workshop stops there: no command gateway is invoked. The aggregate would remain the final authority in a future execution step.

Self-check: if the summary contains a misleading sentence but the action is valid, which guarantee failed? The schema did not fail; this is a reasoning or evaluation problem.

Transition: the assessment is now safer, but every request is still isolated. A follow-up such as “Why did that happen?” introduces the next missing capability: context over time.
-->

---

<p class="kicker">Concept 04 · memory</p>

# Four different things called “memory”

| Kind | What it remembers | Role today |
|---|---|---|
| Chat history | Previous messages | Implement |
| Domain event history | What happened to membership | Implement |
| Agent checkpoint | Execution state | Outlook |
| Semantic retrieval | Relevant knowledge | Outlook |

<div class="statement">Chat memory resolves language. Domain events establish facts.</div>

<!--
Story so far: we can produce a grounded and validated assessment for one request. Real staff conversations contain follow-ups, pronouns, and references to earlier answers, so the agent now needs state. The word “memory” hides several different mechanisms.

Definitions:
- Chat history: prior user and assistant messages for one conversation. It helps resolve language such as “that member” or “why?”
- Domain event history: authoritative facts describing state changes, such as MEMBERSHIP_PAUSED. It explains what happened to the business object.
- Agent checkpoint: saved execution state that lets an interrupted agent run resume. It is not chat history.
- Semantic retrieval: selecting relevant knowledge using embeddings or another search mechanism. It is often called long-term memory but is outside this workshop.

Fitness example: chat history tells the model that “it” refers to Maya's membership. Domain history tells it that the membership was paused from one date to another. Neither can replace the other.

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
  <div class="panel"><h3>Isolation</h3><p>Wrong session, wrong person, leaked context.</p></div>
  <div class="panel"><h3>Freshness</h3><p>Conversation remembers a fact that has changed.</p></div>
  <div class="panel"><h3>Growth</h3><p>Unbounded history raises cost and noise.</p></div>
</div>

<div class="statement">State needs identity, lifetime, bounds, and an authority model.</div>

<!--
Story so far: the agent can now understand a follow-up and cite temporal evidence. That success introduces risks that the stateless version did not have.

Isolation failure: if two users share a conversation ID, one staff member may receive another member's context. Controls include authenticated session ownership, unguessable IDs, tenant scoping, and authorization checks around every tool.

Freshness failure: the conversation may remember that a membership was ACTIVE even after it was paused. The control is to reread current application state for consequential answers and treat assistant messages as context, not authority.

Growth failure: unbounded history increases token cost, latency, and irrelevant context. A bounded window controls size, while summarization or persistent retrieval would require additional correctness and privacy decisions.

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
