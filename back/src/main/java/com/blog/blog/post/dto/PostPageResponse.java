package com.blog.blog.post.dto;

import lombok.Builder;

import java.util.List;

@Builder
public class PostPageResponse {
    public List<PostGetResponse> posts;
    public int currentPage;
    public int totalPages;
}
