package com.blog.blog.global.auth.exception;

import org.springframework.http.HttpStatus;


public class TokenNullException extends RuntimeException {
    public int code = HttpStatus.UNAUTHORIZED.value();
    public TokenNullException() {
        super("Token is Null");
    }
}
