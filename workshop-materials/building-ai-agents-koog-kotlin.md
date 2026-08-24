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

<p class="lead">From a chat response to a grounded, contextual, and reliably constrained workflow.</p>

<!--
Introduce the day as one evolving system rather than disconnected demos.

[Sources]
- https://docs.koog.ai/
[/Sources]
-->

---

<p class="kicker">Connect · 10 minutes</p>

# Agent, chatbot, or workflow?

<div class="three-columns">
  <div class="panel"><h3>“Answer a question”</h3><p>What makes this more than a prompt?</p></div>
  <div class="panel"><h3>“Choose a tool”</h3><p>Where does useful autonomy begin?</p></div>
  <div class="panel"><h3>“Change a membership”</h3><p>Where should autonomy stop?</p></div>
</div>

<div class="statement">Place each scenario on a spectrum: deterministic workflow → chatbot → agent.</div>

<!--
Ask participants to discuss in pairs. Collect criteria, not definitions: model chooses next step, tools, state, risk.
-->

---

<p class="kicker">The learning rhythm</p>

# One system. Five increments.

<div class="branch-flow">
  <div class="branch">01 · basic-agent <span>Run the first Koog agent</span></div>
  <div class="branch">02 · read-tools <span>Ground it in application-owned capabilities</span></div>
  <div class="branch">03 · structured-assessment <span>Integrate a typed draft and deterministic validation</span></div>
  <div class="branch">04 · context-and-memory <span>Remember the conversation; cite domain history</span></div>
  <div class="branch">05 · controlled-workflow <span>Make orchestration explicit and test the boundary</span></div>
</div>

<!--
Explain that every concept block is immediately followed by practice. Solution branches are recovery points.
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

<div class="statement">Koog runs the loop. The model decides within the capabilities we provide.</div>

<!--
Basic AIAgent repeatedly lets the model produce a final response or request a tool. With no tools, it is one model turn.

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
Use pause/resume/cancel to make the ownership line concrete. The workshop stops at a safe proposal.
-->

---

<!-- _class: practice -->

<p class="kicker">Concrete practice 01 · 60 minutes</p>

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
Let participants first ask a general question, then ask about a specific member and notice that the model cannot know.

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
Ask for one answer that sounded plausible but could not be verified.
-->

---

<p class="kicker">Concept 02 · tools</p>

# Tools turn intent into capability

<div class="three-columns">
  <div class="panel"><h3>Narrow</h3><p>One job with explicit arguments.</p></div>
  <div class="panel"><h3>Described</h3><p>Names and descriptions guide model choice.</p></div>
  <div class="panel"><h3>Owned</h3><p>Application code controls the implementation.</p></div>
</div>

<div class="statement">A tool contract is part API design, part model guidance, and part security boundary.</div>

<!--
Koog supports annotation-based ToolSets. Discuss the difference between exposing a repository and exposing a usefully
named application capability.

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
The system is staff-facing but still read-only. Authorization and mutation remain outside today's scope.
-->

---

<!-- _class: practice -->

<p class="kicker">Concrete practice 02 · 75 minutes</p>

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
Seed data before the exercise. Invite participants to alter an ambiguous tool description and observe selection quality.

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
Ask which part of the run trace increased or decreased trust.
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

<div class="statement">Parsing succeeded ≠ the content is correct.</div>

<!--
Structured output narrows shape and improves integration. It does not authenticate identifiers, evidence, or actions.
Koog also offers native structured-output APIs; this exercise begins with an explicit JSON contract so the validation
boundary remains visible.

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
Show an invalid REACTIVATE proposal for an ACTIVE membership and an invented evidence reference.
-->

---

<!-- _class: practice -->

<p class="kicker">Concrete practice 03 · 75 minutes</p>

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
Have participants deliberately prompt for an invalid action. Inspect possibleActions, proposedAction, and warnings.

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
Ask participants which guarantee they initially attributed to structured output but now place elsewhere.
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

<div class="statement">Context is not authority. Event history is not conversation state.</div>

<!--
Koog ChatMemory is session-scoped conversation history. Domain events remain application-owned facts.

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
Explain why a shared ChatHistoryProvider is injected when agents are created per HTTP request.

[Sources]
- https://docs.koog.ai/features/chat-memory/chat-backend-with-memory/
[/Sources]
-->

---

<!-- _class: practice -->

<p class="kicker">Concrete practice 04 · 75 minutes</p>

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
Test with two browser refreshes or manually changed conversation IDs.

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
Connect each failure to a concrete control: session IDs, rereading tools, window size, data classification.
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

<div class="statement">Reliability comes from composition—not from pretending the model is deterministic.</div>

<!--
Koog supports custom strategy graphs for more explicit flows. For this beginner slice, an application use case makes
the architecture and test seam visible without adding graph complexity.

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
Koog includes testing support, but tests should still assert useful owned behavior instead of its internal call sequence.

[Sources]
- https://docs.koog.ai/testing/
[/Sources]
-->

---

<!-- _class: practice -->

<p class="kicker">Concrete practice 05 · 65 minutes</p>

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
Encourage one additional failure case per pair. Discuss which layer owns it.

[Sources]
- https://docs.koog.ai/testing/
[/Sources]
-->

---

<p class="kicker">Recovery map</p>

# Every branch is a working checkpoint

<div class="branch-flow">
  <div class="branch">exercise/00-start <span>prepared infrastructure + exercise seam</span></div>
  <div class="branch">exercise/01-basic-agent <span>first model response</span></div>
  <div class="branch">exercise/02-read-tools <span>grounded investigation</span></div>
  <div class="branch">exercise/03-structured-assessment <span>typed and validated proposal</span></div>
  <div class="branch">exercise/04-context-and-memory <span>follow-ups + event evidence</span></div>
  <div class="branch">exercise/05-controlled-workflow <span>explicit orchestration + tests</span></div>
</div>

<!--
Show how to switch to the current solution checkpoint and continue if a participant falls behind.
-->

---

<!-- _class: dark-title -->

<p class="kicker">Conclusions</p>

# Agents need architecture

<p class="lead">Give models narrow capabilities, ground claims in application facts, separate kinds of state, and put deterministic checks around consequential outcomes.</p>

<!--
Return to the opening spectrum. Ask participants to redraw the system boundary and name one production control they
would add next.

[Sources]
- https://docs.koog.ai/
[/Sources]
-->

