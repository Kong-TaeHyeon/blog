package com.blog.blog.user.exception;

import com.blog.blog.global.exception.CustomException;
import com.blog.blog.global.exception.ErrorCode;

public class UserException extends CustomException {

    public UserException(ErrorCode errorCode) {
        super(errorCode);
    }

    public UserException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
