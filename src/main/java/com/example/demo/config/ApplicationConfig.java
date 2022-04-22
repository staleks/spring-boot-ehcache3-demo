package com.example.demo.config;

import com.example.demo.config.cache.CacheConfig;
import com.example.demo.endpoint.FetchPostController;
import com.example.demo.integration.JSONPlaceholderPostGateway;
import com.example.demo.integration.PostGateway;
import com.example.demo.service.PostService;
import com.example.demo.service.PostServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import({
        RestConfig.class,
        CacheConfig.class
})
public class ApplicationConfig {

    @Bean
    public PostGateway postGateway(final RestTemplate restTemplate) {
        return new JSONPlaceholderPostGateway(restTemplate);
    }

    @Bean
    public PostService postService(final PostGateway postGateway) {
        return new PostServiceImpl(postGateway);
    }

    @Bean
    public FetchPostController postController(final PostService postService) {
        return new FetchPostController(postService);
    }

}
