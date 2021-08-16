package br.com.woodriver.hstrackerapi.shared

import br.com.woodriver.hstrackerapi.exceptions.HeroException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ErrorHandleController: ResponseEntityExceptionHandler() {

    @ExceptionHandler(HeroException::class)
    fun handleHeroException(exception: HeroException, request: WebRequest): ResponseEntity<Any> {
        val body = mapOf(
            Pair("internal_error_code", exception.errorCode),
            Pair("message", exception.message),
            Pair("hero_id", exception.heroId)
        )

        return ResponseEntity(body, HttpStatus.UNPROCESSABLE_ENTITY)
    }
}