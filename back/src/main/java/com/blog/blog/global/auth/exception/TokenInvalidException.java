package com.blog.blog.global.auth.exception;

import org.springframework.http.HttpStatus;

public class TokenInvalidException extends RuntimeException {
    public int code = HttpStatus.UNAUTHORIZED.value();
    public TokenInvalidException() {
        super("TOKEN IS INVALID");
    }
}
