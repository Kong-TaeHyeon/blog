package com.blog.blog.post.controller;

import com.blog.blog.post.dto.PostCreateRequest;
import com.blog.blog.post.entity.Post;
import com.blog.blog.post.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PostController {

    private PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @PostMapping("/api/post")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    public ResponseEntity<Post> createPost(@RequestBody PostCreateRequest request, Authentication authentication) {
        Long userId = Long.parseLong(((UserDetails) authentication.getPrincipal()).getUsername());

        log.info("userId = {}" , userId);
        request.setAuthorId(userId);

        Post post = new Post();
        return ResponseEntity.ok(postRepository.save(post));
    }
}
