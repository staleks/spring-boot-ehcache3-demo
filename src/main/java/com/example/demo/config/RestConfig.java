package com.example.demo.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    @Value("${rest.client.http.max.connections:50}")
    private int maxConnections;

    @Value("${rest.client.http.max.connections.per.route:5}")
    private int maxConnectionPerRoute;

    @Value("${rest.client.http.request.timeout:30000}")
    private int requestTimeout;

    @Value("${rest.client.http.connection.timeout:30000}")
    private int connectTimeout;

    @Value("${rest.client.http.socket.timeout:30000}")
    private int socketTimeout;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(httpRequestFactory());
    }

    @Bean
    public ClientHttpRequestFactory httpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory(httpClient());
    }

    @Bean
    public CloseableHttpClient httpClient() {
        final RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(requestTimeout)
                .setConnectTimeout(connectTimeout)
                .setSocketTimeout(socketTimeout).build();

        return HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager())
                .build();
    }

    @Bean
    public HttpClientConnectionManager connectionManager() {
        final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxConnections);
        connectionManager.setDefaultMaxPerRoute(maxConnectionPerRoute);
        return connectionManager;
    }

}
