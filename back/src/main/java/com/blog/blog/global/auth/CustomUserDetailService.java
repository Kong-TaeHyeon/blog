package com.blog.blog.global.auth;

import com.blog.blog.user.repository.UserRepository;
import com.blog.blog.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;

@RequiredArgsConstructor
@Component
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(Long.parseLong(username))
                .orElseThrow(() -> new UsernameNotFoundException("Could Not Found Username : " + username));

        return new PrincipalDetails(user, null, null);
    }

    public UserDetails loadUserByEmailWithPassword(String email, String password) throws UsernameNotFoundException {
        User user = userRepository.findByEmailAndPassword(email,password)
                .orElseThrow(() -> new RuntimeException("Could Not Found Email with Password: " + email + " " + password));

        return new PrincipalDetails(user, null, null);
    }
}
