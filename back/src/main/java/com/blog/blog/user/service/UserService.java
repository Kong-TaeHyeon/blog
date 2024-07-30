package com.blog.blog.user.service;

import com.blog.blog.global.auth.CustomUserDetailService;
import com.blog.blog.global.auth.JwtProvider;

import com.blog.blog.user.entity.User;
import com.blog.blog.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;



@Service
@Slf4j
public class UserService {
    private final JwtProvider jwtProvider;
    private UserRepository userRepository;
    private CustomUserDetailService customUserDetailService;

    public UserService(UserRepository userRepository, CustomUserDetailService customUserDetailService,  JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.customUserDetailService = customUserDetailService;
        this.jwtProvider = jwtProvider;
    }

    public String login(String email, String password) {
        UserDetails userDetails = customUserDetailService.loadUserByEmailWithPassword(email, password);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        String token = jwtProvider.createToken(authentication);

        return token;
    }

    public User join(String email, String nickname, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException();
        }

        User user = User.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();

        return userRepository.save(user);
    }
}
