package com.blog.blog.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> handleException(CustomException exception) {

        ExceptionResponse response = new ExceptionResponse(exception);
        HttpStatus httpStatus = exception.getErrorCode().getHttpStatus();

        return new ResponseEntity<>(response, httpStatus);
    }
}
