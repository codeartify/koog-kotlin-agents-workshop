package com.codeartify.membership.billing

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/invoices")
class InvoiceController(
    private val invoiceRepository: InvoiceRepository
) {
    @GetMapping
    fun getAll(
        @RequestParam(required = false) membershipId: String?
    ): ResponseEntity<List<InvoiceResponse>> {
        val invoices = if (membershipId.isNullOrBlank()) {
            invoiceRepository.findAllByOrderByDueDateDesc()
        } else {
            invoiceRepository.findByMembershipIdOrderByDueDateDesc(membershipId.trim())
        }

        return ResponseEntity.ok(invoices.map { it.toResponse() })
    }

    @GetMapping("/{invoiceId}")
    fun getById(@PathVariable invoiceId: String): ResponseEntity<InvoiceResponse> =
        invoiceRepository.findById(invoiceId)
            .map { ResponseEntity.ok(it.toResponse()) }
            .orElseGet { ResponseEntity.notFound().build() }

    private fun Invoice.toResponse(): InvoiceResponse = InvoiceResponse(
        id = id,
        membershipId = membershipId,
        customerId = customerId,
        amount = amount,
        dueDate = dueDate,
        state = state,
        overdue = state == InvoiceState.OPEN && dueDate.isBefore(LocalDate.now())
    )
}

data class InvoiceResponse(
    val id: String,
    val membershipId: String,
    val customerId: String,
    val amount: Int,
    val dueDate: LocalDate,
    val state: InvoiceState,
    val overdue: Boolean
)
