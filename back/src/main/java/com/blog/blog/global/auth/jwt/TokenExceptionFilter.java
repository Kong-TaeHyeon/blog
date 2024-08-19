package com.blog.blog.global.auth.jwt;

import com.blog.blog.global.auth.exception.TokenInvalidException;
import com.blog.blog.global.auth.exception.TokenNullException;
import com.blog.blog.global.dto.ExceptionResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class TokenExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);

        } catch (TokenInvalidException e) {
            response.setStatus(e.code);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(getResponse(e));

        } catch (TokenNullException e) {
            response.setStatus(e.code);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(getResponse(e));
        } catch (Exception e) {
            log.info("Exception = {} ",e.getMessage());
            e.printStackTrace();
            filterChain.doFilter(request, response);
        }
    }

    public static String getResponse(Exception e) throws JsonProcessingException {
        if (e instanceof TokenInvalidException) {
            TokenInvalidException tokenInvalidException = (TokenInvalidException) e;
            return new ObjectMapper().writeValueAsString(new ExceptionResponseDto(tokenInvalidException.code, tokenInvalidException.getMessage()));
        }

        if (e instanceof TokenNullException) {
            TokenNullException tokenNullException = (TokenNullException) e;
            return new ObjectMapper().writeValueAsString(new ExceptionResponseDto(tokenNullException.code, tokenNullException.getMessage()));
        }

        e.printStackTrace();
        return new ObjectMapper().writeValueAsString(new ExceptionResponseDto(500, e.getMessage()));
    }

}
