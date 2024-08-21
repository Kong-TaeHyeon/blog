package com.blog.blog.global.exception;

public class CustomException extends RuntimeException {
    protected ErrorCode ERROR_CODE;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.ERROR_CODE = errorCode;
    }

    public CustomException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.ERROR_CODE = errorCode;
    }

    public ErrorCode getErrorCode() {
        return ERROR_CODE;
    }
}
