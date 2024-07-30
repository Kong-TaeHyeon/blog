package com.blog.blog.user.controller;

import com.blog.blog.user.dto.UserLoginRequest;
import com.blog.blog.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/auth/login")
    public String login(@RequestBody UserLoginRequest userLoginRequest ) {
        String token = userService.login(userLoginRequest.email, userLoginRequest.password);
        return token;
    }
}
