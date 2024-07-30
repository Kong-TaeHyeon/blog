package com.blog.blog.global.auth.oauth;

import com.blog.blog.user.entity.Role;
import com.blog.blog.user.entity.User;

import java.util.Map;

public record OAuth2UserInfo(
        String name,
        String email,
        String profile
) {
    public static OAuth2UserInfo of(String registrationId, Map<String, Object> attributes) {
        return switch (registrationId) {
            case "kakao" -> ofKaKao(attributes);
            case "github" -> ofGitHub(attributes);
            default -> throw new IllegalStateException("Unexpected value: " + registrationId);
        };
    }

    private static OAuth2UserInfo ofKaKao(Map<String, Object> attributes) {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) account.get("profile");

        return new OAuth2UserInfo(
                (String) profile.get("nickname"),
                (String) account.get("email"),
                (String) profile.get("profile_image_url")
        );

    }

    private static OAuth2UserInfo ofGitHub(Map<String, Object> attributes) {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) account.get("profile");

        return new OAuth2UserInfo(
                (String) profile.get("nickname"),
                (String) account.get("email"),
                (String) profile.get("profile_image_url")
        );
    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .nickname(name)
                .role(Role.ROLE_GUEST)
                .build();
    }
}
