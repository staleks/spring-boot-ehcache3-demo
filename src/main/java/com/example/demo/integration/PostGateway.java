package com.example.demo.integration;

import com.example.demo.model.JSONPlaceholderPost;
import com.example.demo.model.Post;

public interface PostGateway {
    Post fetchSinglePost(final Long postId);
}
