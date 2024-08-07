package com.blog.blog.user.dto;

import lombok.Builder;

@Builder
public class UserGetResponse {
    public Long id;
    public String email;
    public String nickname;
}
