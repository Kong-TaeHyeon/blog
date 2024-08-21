package com.blog.blog.global.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String getName();
    HttpStatus getHttpStatus();
    String getMessage();
    RuntimeException getException();
    RuntimeException getException(Throwable cause);
}
