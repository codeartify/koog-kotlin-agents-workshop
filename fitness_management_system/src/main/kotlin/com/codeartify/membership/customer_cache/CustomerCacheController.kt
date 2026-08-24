package com.codeartify.membership.customer_cache

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/customer-cache")
class CustomerCacheController(
    private val customerCacheRepository: CustomerCacheRepository
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping
    fun backfillCustomer(@RequestBody request: CustomerCacheBackfillRequest): ResponseEntity<String> {
        customerCacheRepository.save(
            CustomerEntity(
                id = request.id,
                name = request.name,
                email = request.email,
                dateOfBirth = request.dateOfBirth
            )
        )

        log.info("Customer ${request.id} has been backfilled with id ${request.id}")

        return ResponseEntity.ok(request.id)
    }

    @GetMapping
    fun getAll(
        @RequestParam(required = false) query: String?
    ): ResponseEntity<List<CustomerCacheResponse>> {
        val customers = if (query.isNullOrBlank()) {
            customerCacheRepository.findAllByOrderByNameAsc()
        } else {
            customerCacheRepository.searchByNameOrEmail(query.trim())
        }

        return ResponseEntity.ok(customers.map { it.toResponse() })
    }

    @GetMapping("/{customerId}")
    fun getById(@PathVariable customerId: String): ResponseEntity<CustomerCacheResponse> =
        customerCacheRepository.findById(customerId)
            .map { ResponseEntity.ok(it.toResponse()) }
            .orElseGet { ResponseEntity.notFound().build() }

    private fun CustomerEntity.toResponse(): CustomerCacheResponse =
        CustomerCacheResponse(id, name, email, dateOfBirth)
}

data class CustomerCacheResponse(
    val id: String,
    val name: String,
    val email: String,
    val dateOfBirth: LocalDate
)
