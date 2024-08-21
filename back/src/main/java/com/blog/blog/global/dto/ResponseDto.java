package com.blog.blog.global.dto;

import org.springframework.http.HttpStatus;


// Controller => Client 에게 전달을 위한 공통 DTO
public class ResponseDto<T> {
    public int status;
    public T data;
}
