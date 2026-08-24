package com.codeartify.membership.staff_assistant

import ai.koog.agents.core.tools.annotations.LLMDescription
import ai.koog.agents.core.tools.annotations.Tool
import ai.koog.agents.core.tools.reflect.ToolSet
import com.fasterxml.jackson.databind.ObjectMapper

@LLMDescription("Read-only tools for investigating gym membership cases")
class MembershipStaffTools(
    private val readService: MembershipStaffReadService,
    private val objectMapper: ObjectMapper,
    private val trace: MutableList<ToolCallTrace>
) : ToolSet {

    @Tool
    @LLMDescription("Search customers by partial name or email address")
    fun searchCustomers(
        @LLMDescription("Partial customer name or email address") query: String
    ): String = result("searchCustomers", readService.searchCustomers(query))

    @Tool
    @LLMDescription("Get all memberships belonging to a customer")
    fun getMembershipsForCustomer(
        @LLMDescription("Customer identifier") customerId: String
    ): String = result("getMembershipsForCustomer", readService.membershipsForCustomer(customerId))

    @Tool
    @LLMDescription("Get the current projected details of a membership")
    fun getMembershipDetails(
        @LLMDescription("Membership identifier") membershipId: String
    ): String = result("getMembershipDetails", readService.membership(membershipId))

    @Tool
    @LLMDescription("Get the semantic event history of a membership, including evidence references")
    fun getMembershipHistory(
        @LLMDescription("Membership identifier") membershipId: String
    ): String = result("getMembershipHistory", readService.history(membershipId))

    @Tool
    @LLMDescription("Get invoices for a membership")
    fun getInvoicesForMembership(
        @LLMDescription("Membership identifier") membershipId: String
    ): String = result("getInvoicesForMembership", readService.invoices(membershipId))

    @Tool
    @LLMDescription("Get the gym plan referenced by a membership")
    fun getPlan(
        @LLMDescription("Plan identifier") planId: String
    ): String = result("getPlan", readService.plan(planId))

    @Tool
    @LLMDescription("Get the actions currently allowed by deterministic membership policy")
    fun getAllowedMembershipActions(
        @LLMDescription("Membership identifier") membershipId: String
    ): String = result("getAllowedMembershipActions", readService.allowedActions(membershipId))

    private fun result(tool: String, value: Any?): String {
        val json = objectMapper.writeValueAsString(value)
        val summary = when (value) {
            null -> "No result"
            is Collection<*> -> "Returned ${value.size} item(s)"
            else -> "Returned result"
        }
        trace += ToolCallTrace(tool, summary)
        return json
    }
}
