package com.blog.blog.post.service;

import com.blog.blog.post.dto.PostCreateRequest;
import com.blog.blog.post.entity.Post;
import com.blog.blog.post.repository.PostRepository;
import com.blog.blog.user.entity.User;
import com.blog.blog.user.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class PostService {
    private PostRepository postRepository;
    private UserRepository userRepository;


    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Post save(PostCreateRequest dto) {
        User user = userRepository.findById(dto.getAuthorId()).orElseThrow(() -> new RuntimeException("Not Found User"));

        Post post = Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(user)
                .build();

        return postRepository.save(post);
    }

}
