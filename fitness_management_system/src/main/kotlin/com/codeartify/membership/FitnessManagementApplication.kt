package com.codeartify.membership

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EntityScan(
    basePackages = [
        "com.codeartify.membership",
        "org.axonframework.eventsourcing.eventstore.jpa",
        "org.axonframework.messaging.eventhandling.processing.streaming.token.store.jpa"
    ]
)
class FitnessManagementApplication

fun main(args: Array<String>) {
    runApplication<FitnessManagementApplication>(*args)
}
