package com.blog.blog.user.exception;

import com.blog.blog.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum UserErrorCode implements ErrorCode {
    USERNAME_ALREADY_EXISTS("이미 사용 중인 계정입니다", HttpStatus.CONFLICT),
    MEMBER_NOT_FOUND("회원을 찾을 수 없습니다", HttpStatus.NOT_FOUND),
    DEFAULT("회원 오류가 발생하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus status;

    UserErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String getName() {
        return message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public RuntimeException getException() {
        return new UserException(this);
    }

    @Override
    public RuntimeException getException(Throwable cause) {
        return new UserException(this, cause);
    }
}
