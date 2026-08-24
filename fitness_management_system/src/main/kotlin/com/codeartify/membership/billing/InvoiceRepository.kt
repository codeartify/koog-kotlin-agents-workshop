package com.codeartify.membership.billing

import org.springframework.data.jpa.repository.JpaRepository

interface InvoiceRepository : JpaRepository<Invoice, String> {
    fun findAllByOrderByDueDateDesc(): List<Invoice>
    fun findByMembershipIdOrderByDueDateDesc(membershipId: String): List<Invoice>
}
