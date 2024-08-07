package com.blog.blog.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateResponse {
    public Long id;
    public Long userId;
    public String title;
    public String content;
}
