package com.blog.blog.global.auth.jwt;

import com.blog.blog.global.auth.SecurityConfig;
import com.blog.blog.global.auth.exception.TokenNullException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    private static final String[] ALLOWED_URI = {
            "/api/auth/join","/api/auth/login", "/api/auth/register", "/oauth2/authorize/**", "/oauth/token/**", "/api/image"
    };

    private static final String[] GET_ONLY_URI = {
            "/api/post/**", "/api/project/**"
    };

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


            String token = request.getCookies() != null
                    ? Arrays.stream(request.getCookies())
                    .filter(c -> "token".equals(c.getName()))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null)
                    : null;

            String servletPath = request.getServletPath();
            String method = request.getMethod();


            if (Arrays.stream(ALLOWED_URI).anyMatch(path -> matchesPath(servletPath, path))) {
                filterChain.doFilter(request, response);
                return;
            }

            if (Arrays.stream(GET_ONLY_URI).anyMatch(path -> matchesPath(servletPath, path)) && method.equalsIgnoreCase("GET")) {
                filterChain.doFilter(request, response);
                return;
            }


            if (jwtProvider.validateToken(token)) {
                Authentication authentication = jwtProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);
    }



    private boolean matchesPath(String servletPath, String pattern) {
        // This method can be enhanced to handle wildcard patterns more robustly if needed
        if (pattern.endsWith("/**")) {
            String basePattern = pattern.substring(0, pattern.length() - 3);
            return servletPath.startsWith(basePattern);
        }
        return servletPath.equals(pattern);
    }
}
