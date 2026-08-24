# Workshop exercises

The workshop grows one staff-facing membership operations assistant through five working checkpoints. Each solution
branch is the starting point for the following exercise.

| Exercise | Start here | Compare with | Outcome |
|---|---|---|---|
| 1. First agent | `exercise/00-start` | `exercise/01-basic-agent` | A prompt reaches a Koog agent and the Angular console shows its answer. |
| 2. Ground with tools | `exercise/01-basic-agent` | `exercise/02-read-tools` | The agent investigates real customer, membership, plan, and invoice data. |
| 3. Structure and validate | `exercise/02-read-tools` | `exercise/03-structured-assessment` | Free text becomes a typed draft; Kotlin applies deterministic guardrails. |
| 4. Add context | `exercise/03-structured-assessment` | `exercise/04-context-and-memory` | A conversation remembers earlier turns and can cite semantic domain events. |
| 5. Make the workflow reliable | `exercise/04-context-and-memory` | `exercise/05-controlled-workflow` | Orchestration has an explicit application boundary and behavior-focused tests. |

## How to work

1. Check out the branch in the **Start here** column.
2. Read the matching exercise file before changing code.
3. Run the system with `./start-dev.sh` and open `http://localhost:4200`.
4. Implement the smallest change that satisfies the acceptance criteria.
5. If you get stuck, compare with the solution branch or switch to it and continue.

The Angular console, Docker environment, provider configuration, stable HTTP response, read models, semantic event
projection, and deterministic membership policy are prepared. The exercises focus on the AI integration rather than
framework setup.

## Workshop boundary

The agent is intentionally read-only. It may interpret intent, choose tools, summarize evidence, and propose an action.
It cannot send a membership command. The domain model remains responsible for lifecycle rules and business truth.

## Exercise files

- [Exercise 1 — First Koog agent](01-basic-agent.md)
- [Exercise 2 — Ground with read-only tools](02-read-tools.md)
- [Exercise 3 — Structure and validate](03-structured-assessment.md)
- [Exercise 4 — Context and domain history](04-context-and-memory.md)
- [Exercise 5 — Controlled workflow and tests](05-controlled-workflow.md)
- [Instructor guide](instructor-guide.md)

