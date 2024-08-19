package com.blog.blog.global.dto;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class ExceptionResponseDto {
    public int status;
    public String body;
}
