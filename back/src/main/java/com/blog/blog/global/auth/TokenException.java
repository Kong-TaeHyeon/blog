package com.blog.blog.global.auth;

import org.springframework.http.HttpStatus;

public class TokenException extends RuntimeException {
    public int code = HttpStatus.UNAUTHORIZED.value();
    public TokenException(String message) {
        super(message);
    }
}
