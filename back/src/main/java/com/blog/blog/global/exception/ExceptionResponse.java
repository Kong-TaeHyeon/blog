package com.blog.blog.global.exception;

import java.time.Instant;

public class ExceptionResponse {
    String code;
    String name;
    Integer status;
    String message;
    Instant timestamp;


    public ExceptionResponse(CustomException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        String errorName = exception.getClass().getName();
        errorName = errorName.substring(errorName.lastIndexOf('.') + 1);

        this.code = errorCode.getName();
        this.name = errorName;
        this.status = errorCode.getHttpStatus().value();
        this.message = errorCode.getMessage();
        this.timestamp = Instant.now();
    }
}
