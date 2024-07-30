package com.blog.blog.user.dto;

public class UserJoinRequest {
    public String email;
    public String nickname;
    public String password;

    public UserJoinRequest(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }
}
