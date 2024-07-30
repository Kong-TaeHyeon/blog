package com.blog.blog.user.dto;

public class UserJoinResponse {
    public String email;
    public String nickname;

    public UserJoinResponse(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
}
