package edu.ntnu.idatt2105.g16.backend.exception

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.TransactionSystemException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.stream.Collectors
import javax.persistence.RollbackException
import javax.validation.ConstraintViolationException

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler
    protected fun handle(e: TransactionSystemException): ResponseEntity<List<String>> {
        val exception: Throwable = e.cause!!

        if (exception !is RollbackException) throw exception
        if (exception.cause !is ConstraintViolationException) throw exception.cause!!

        val validationException = exception.cause as ConstraintViolationException
        val messages = validationException.constraintViolations.stream().map {
            it.message
        }.collect(Collectors.toList())

        return ResponseEntity(messages, HttpStatus.BAD_REQUEST)
    }
}
