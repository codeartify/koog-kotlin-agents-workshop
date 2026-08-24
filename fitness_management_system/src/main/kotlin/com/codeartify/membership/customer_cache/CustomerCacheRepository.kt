package com.codeartify.membership.customer_cache

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CustomerCacheRepository : JpaRepository<CustomerEntity, String> {
    @Query(
        """
        select customer from CustomerEntity customer
        where lower(customer.name) like lower(concat('%', :query, '%'))
           or lower(customer.email) like lower(concat('%', :query, '%'))
        order by customer.name
        """
    )
    fun searchByNameOrEmail(@Param("query") query: String): List<CustomerEntity>
}
