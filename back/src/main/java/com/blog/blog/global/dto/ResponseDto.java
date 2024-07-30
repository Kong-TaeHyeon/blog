package com.blog.blog.global.dto;

import org.springframework.http.HttpStatus;

public class ResponseDto<T> {
    public HttpStatus status;
    public T data;
}
