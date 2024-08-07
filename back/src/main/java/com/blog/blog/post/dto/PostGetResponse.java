package com.blog.blog.post.dto;

import com.blog.blog.user.dto.UserGetResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
public class PostGetResponse {
    public Long id;
    public String title;
    public String content;
    public UserGetResponse author;
}
