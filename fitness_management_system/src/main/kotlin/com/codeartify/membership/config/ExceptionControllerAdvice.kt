package com.codeartify.membership.config

import com.codeartify.membership.staff_assistant.AgentNotConfiguredException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleValidationError(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity.badRequest().body(e.message)

    @ExceptionHandler(AgentNotConfiguredException::class)
    fun handleAgentNotConfigured(e: AgentNotConfiguredException): ResponseEntity<String> =
        ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.message)
}
