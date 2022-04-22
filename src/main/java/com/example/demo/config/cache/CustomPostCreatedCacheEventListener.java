package com.example.demo.config.cache;

import com.example.demo.model.Post;
import lombok.extern.slf4j.Slf4j;

import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryListenerException;

@Slf4j
public class CustomPostCreatedCacheEventListener implements CacheEntryCreatedListener<Long, Post> {

    @Override
    public void onCreated(final Iterable<CacheEntryEvent<? extends Long, ? extends Post>> cacheEntryEvents) throws CacheEntryListenerException {
        for (CacheEntryEvent entryEvent: cacheEntryEvents) {
            log.info("Cached [key: {}, value: {}]", entryEvent.getKey(), entryEvent.getValue());
        }
    }
}
