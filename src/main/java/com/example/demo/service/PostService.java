package com.example.demo.service;

import com.example.demo.model.Post;

public interface PostService {

    Post fetchSinglePost(final Long postId);

}
