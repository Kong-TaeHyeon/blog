package com.blog.blog.global.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JwtProvider {


    private static final String TOKEN_SECRET_KEY = "secret-key";
    private static final long TOKEN_EXPIRE_TIME = 1000 * 60 * 30L;

    public String createToken(Authentication authentication) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + TOKEN_EXPIRE_TIME);

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining());

        log.info("authentication Name : {} ", authentication.getName());

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("role", authorities)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET_KEY.getBytes())
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = parseClaim(token);
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(
                claims.get("role").toString()
        ));

        User user = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(user, "", authorities);
    }

    public boolean validateToken(String token) {
        if (!StringUtils.hasText(token)) {
            return false;
        }

        Claims claims = parseClaim(token);
        return claims.getExpiration().before(new Date());
    }

    private Claims parseClaim(String token) {
        try {
            return Jwts.parser().setSigningKey(TOKEN_SECRET_KEY.getBytes()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
