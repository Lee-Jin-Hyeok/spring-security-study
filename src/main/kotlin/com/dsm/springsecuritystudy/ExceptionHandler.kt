package com.dsm.springsecuritystudy

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(CommonException::class)
    fun commonExceptionHandler(e: CommonException) =
        ResponseEntity(
            ExceptionResponse(
                code = e.code,
                message = e.message?: "큰 문제는 아닌데 이거 나오면 안 되긴 함",
            ),
            e.status,
        )

    @ExceptionHandler(RuntimeException::class)
    fun runtimeExceptionHandler(e: RuntimeException): ResponseEntity<ExceptionResponse> {
        e.printStackTrace()
        return ResponseEntity(
            ExceptionResponse(
                code = "INTERNAL_SERVER_ERROR",
                message = "큰 문제긴 한데 이거 나오면 안 되긴 함",
            ),
            HttpStatus.INTERNAL_SERVER_ERROR,
        )
    }
}