package com.blog.blog.user.dto;

public class UserLoginRequest {
    public String email;
    public String password;

    public UserLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
