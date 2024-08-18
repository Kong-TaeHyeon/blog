package com.blog.blog.post.service;

import com.blog.blog.post.dto.PostCreateRequest;
import com.blog.blog.post.entity.Post;
import com.blog.blog.post.repository.PostRepository;
import com.blog.blog.user.entity.User;
import com.blog.blog.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@Slf4j
public class PostService {
    private PostRepository postRepository;
    private UserRepository userRepository;


    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Post save(PostCreateRequest dto) {
        User user = userRepository.findById(dto.getAuthorId()).orElseThrow(() -> new RuntimeException("Not Found User"));

        log.info("userId = {}" , user.getId());

        Post post = Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(user)
                .build();

        return postRepository.save(post);
    }

    public List<Post> findAll(Pageable pageable) {


        Page<Post> test= postRepository.findAllByOrderByIdDesc(pageable);

        return postRepository.findAllByOrderByIdDesc(pageable).getContent();
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException());

    }

}
