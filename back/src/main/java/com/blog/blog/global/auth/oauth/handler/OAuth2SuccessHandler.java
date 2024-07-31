package com.blog.blog.global.auth.oauth.handler;

import com.blog.blog.global.auth.JwtProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String token = jwtProvider.createToken(authentication);

        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        response.setHeader("Authorization", token);
        response.sendRedirect("http://localhost:5173/main");
    }
}
