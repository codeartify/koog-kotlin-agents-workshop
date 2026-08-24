# Instructor guide

## Teaching rhythm

Use each exercise as one Concept → Concrete Practice block. Keep the slide discussion interactive and under twenty
minutes; protect the coding time.

| Block | Concept emphasis | Practice | Suggested time |
|---|---|---|---:|
| Connect | Agent, chatbot, or deterministic workflow? | Card sort and expectations | 25 min |
| 1 | Agent loop and architecture boundary | First Koog agent | 60 min |
| 2 | Tool contracts and grounding | Add read-only tools | 75 min |
| 3 | Structured output versus truth | Draft and deterministic validation | 75 min |
| 4 | Four meanings of memory | Chat context and event evidence | 75 min |
| 5 | Explicit workflows and test seams | Orchestration and behavior tests | 65 min |
| Conclusions | Architectural trade-offs | Case review and action plan | 15 min |

The remaining clock time is two 15-minute breaks and a 60-minute lunch.

## Facilitation notes

- Seed a small but interesting case before the workshop: a customer, plan, active membership, invoice, and at least one
  lifecycle transition.
- Demonstrate the limitation before presenting the next concept. A failed follow-up question is a better introduction
  to memory than a definition slide.
- Keep pairs optional for up to ten participants. Use deliberate pairing above ten.
- Treat solution branches as recovery points, not as a race leaderboard.
- Use only synthetic member data with external LLM providers.

## Architecture invariant

The model may interpret, retrieve, summarize, and propose. It does not enforce lifecycle invariants, authorize staff,
or record business truth. The workshop ends at a safe proposal.

