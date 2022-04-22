package com.example.demo.config;

import com.example.demo.integration.JSONPlaceholderPostGateway;
import com.example.demo.integration.PostGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import({
        RestConfig.class
})
public class ApplicationConfig {

    @Bean
    public PostGateway postGateway(final RestTemplate restTemplate) {
        return new JSONPlaceholderPostGateway(restTemplate);
    }

}
