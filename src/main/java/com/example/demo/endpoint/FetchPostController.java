package com.example.demo.endpoint;

import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequiredArgsConstructor
public class FetchPostController {

    private final PostService postService;

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> singlePostHandle(@PathVariable("postId") Long postId) {
        log.info("singlePostHandle: {}", postId);
        return ResponseEntity.ok(postService.fetchSinglePost(postId));
    }

}
