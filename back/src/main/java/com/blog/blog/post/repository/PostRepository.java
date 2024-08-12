package com.blog.blog.post.repository;

import com.blog.blog.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("select p from Post p join fetch p.user")
    List<Post> findAll();

    Optional<Post> findById(Long id);


    Page<Post> findAllByOrderByIdDesc(Pageable pageable);
}
