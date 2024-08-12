package com.blog.blog.post.controller;

import com.blog.blog.post.dto.PostCreateRequest;
import com.blog.blog.post.dto.PostCreateResponse;
import com.blog.blog.post.dto.PostGetResponse;
import com.blog.blog.post.entity.Post;
import com.blog.blog.post.service.PostService;
import com.blog.blog.user.dto.UserGetResponse;
import com.blog.blog.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }



    @PostMapping("/api/post")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    public ResponseEntity<PostCreateResponse> createPost(@RequestBody PostCreateRequest request, Authentication authentication) {

        Long userId = Long.parseLong(((UserDetails) authentication.getPrincipal()).getUsername());
        request.setAuthorId(userId);

        Post post = postService.save(request);
        PostCreateResponse response = PostCreateResponse.builder()
                .id(post.getId())
                .userId(userId)
                .title(post.getTitle())
                .content(post.getContent())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/post")
    public ResponseEntity<List<PostGetResponse>> getPosts(Pageable pageable) {
        log.info("Page Number = {}", pageable.getPageNumber());
        log.info("Page Size = {}", pageable.getPageSize());
        List<Post> posts= postService.findAll(pageable);
        List<PostGetResponse> responses = posts.stream()
                .map(post -> {
                    User user = post.getUser();
                    UserGetResponse author = UserGetResponse.builder()
                            .id(user.getId())
                            .nickname(user.getNickname())
                            .email(user.getEmail())
                            .build();

                    return PostGetResponse.builder()
                            .id(post.getId())
                            .author(author)
                            .title(post.getTitle())
                            .content(post.getContent())
                            .build();
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }


    @GetMapping("/api/post/{id}")
    public ResponseEntity<PostGetResponse> getPost(@PathVariable Long id) {
        Post post = postService.findById(id);

        UserGetResponse author = UserGetResponse.builder()
                .email(post.getUser().getEmail())
                .nickname(post.getUser().getNickname())
                .build();

        PostGetResponse response = PostGetResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(author).build();

        return ResponseEntity.ok(response);
    }
}
