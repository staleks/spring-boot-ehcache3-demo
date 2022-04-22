package com.example.demo.service;

import com.example.demo.integration.PostGateway;
import com.example.demo.model.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;

@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostGateway postGateway;

    @Cacheable(value = "postById", key = "#postId")
    @Override
    public Post fetchSinglePost(final Long postId) {
        log.info("PostServiceImpl - fetchSinglePost: {}", postId);
        return postGateway.fetchSinglePost(postId);
    }
}
