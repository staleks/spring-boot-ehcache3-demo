package com.example.demo.integration;

import com.example.demo.model.JSONPlaceholderPost;
import com.example.demo.model.Post;
import com.example.demo.model.error.PostGatewayException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
public class JSONPlaceholderPostGateway implements PostGateway {
    private final RestTemplate restTemplate;

    @Value("${jsonPlaceholder.host}")
    private String jsonPlaceholderHost;

    @Override
    public Post fetchSinglePost(final Long postId) {
        log.debug("Fetch SinglePost: {}", postId);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            URI uri = new URI(jsonPlaceholderHost + "/posts" + postId);
            return restTemplate.getForObject(uri, JSONPlaceholderPost.class);
        } catch(URISyntaxException | RestClientException ex) {
            // throw some custom application ex.
            throw new PostGatewayException("Can not get post with id: " + postId, ex);
        }
    }
}
