package com.blog.blog.user.controller;

import com.blog.blog.user.dto.UserLoginRequest;
import com.blog.blog.user.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@RestController
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/auth/login")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    public RedirectView login(UserLoginRequest userLoginRequest, HttpServletResponse response, HttpServletRequest request) throws IOException {
        String token = userService.login(userLoginRequest.email, userLoginRequest.password);

        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");


        return new RedirectView("http://localhost:5173/main");
    }
}
