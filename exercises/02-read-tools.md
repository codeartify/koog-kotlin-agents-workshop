# Exercise 2 — Ground with read-only tools

## Goal

Let the agent investigate real projected data while keeping every integration read-only.

## Concept checkpoint

A tool is an application-owned capability with a narrow contract. A good tool name, description, arguments, and result
shape help the model choose it correctly. Tool calling does not transfer business authority to the model.

## Start

```bash
git switch exercise/01-basic-agent
```

## Tasks

1. Create a `MembershipStaffTools : ToolSet` class.
2. Add annotation-based tools for:
   - `searchCustomers`
   - `getMembershipsForCustomer`
   - `getMembershipDetails`
   - `getInvoicesForMembership`
   - `getPlan`
3. Serialize tool results to JSON.
4. Record a small completed-call trace without storing sensitive payloads.
5. Register the tool set in the agent's `ToolRegistry`.
6. Update the system prompt so the agent distinguishes unknown facts from retrieved facts.

## Acceptance criteria

- A staff question can start from a partial name or email address.
- The agent can follow identifiers from customer to membership and plan.
- The Angular trace shows which tools completed.
- Tools query existing read models; they do not call aggregates or command gateways.
- The agent still returns natural-language text only.

## Try it

> Find Maya's membership and explain its current status and plan.

Inspect the tool trace. Then change a tool description so it becomes ambiguous and observe the impact before restoring
the precise contract.

## Solution checkpoint

```bash
git switch exercise/02-read-tools
```

