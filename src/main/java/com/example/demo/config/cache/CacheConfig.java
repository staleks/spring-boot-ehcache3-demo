package com.example.demo.config.cache;

import com.example.demo.model.Post;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.Factory;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableCacheEntryListenerConfiguration;
import javax.cache.event.CacheEntryEventFilter;
import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfig {
    private static final boolean IS_OLD_VALUE_REQUIRED = false;
    private static final boolean IS_SYNCHRONOUS = true;

    private static final Factory<? extends CacheEntryEventFilter<Long, Post>> NO_FILTER = null;

    private final javax.cache.configuration.Configuration<Long, Post> jCachePostConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                    CacheConfigurationBuilder
                            .newCacheConfigurationBuilder(Long.class, Post.class, ResourcePoolsBuilder.heap(100))
                            .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(5)))
                            .build()
            );

    CacheEntryListenerConfiguration<Long, Post> listenerConfiguration =
            new MutableCacheEntryListenerConfiguration<>(
                    FactoryBuilder.factoryOf(CustomPostCreatedCacheEventListener.class),
                    NO_FILTER,
                    IS_OLD_VALUE_REQUIRED,
                    IS_SYNCHRONOUS);

    private void createPostCache(final CacheManager cacheManager, final String cacheName) {
        Cache<Long, Post> postCache = cacheManager.getCache(cacheName);
        if (postCache == null) {
            cacheManager.createCache(cacheName, jCachePostConfiguration).registerCacheEntryListener(listenerConfiguration);
        }
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cacheManager -> {
            createPostCache(cacheManager, "postById");
        };
    }

}
