package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class JSONPlaceholderPost implements Post {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("userId")
    private Long userId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("body")
    private String body;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Long getOwnerId() {
        return userId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getBody() {
        return body;
    }
}
