package com.blog.blog.post.dto;

import lombok.Builder;

import java.util.List;

@Builder
public class PostDeleteResponse {
    public String body;
    public List<Long> ids;
}
