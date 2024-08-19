package com.blog.blog.image.dto;


import lombok.Getter;

@Getter
public class ImageResponseDto {
    public Long id;
    public String url;

    public ImageResponseDto(Long id, String url) {
        this.id = id;
        this.url = url;
    }
}
