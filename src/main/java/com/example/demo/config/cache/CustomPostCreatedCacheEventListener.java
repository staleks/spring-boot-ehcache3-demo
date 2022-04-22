package com.example.demo.config.cache;

import com.example.demo.model.Post;
import lombok.extern.slf4j.Slf4j;

import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryExpiredListener;
import javax.cache.event.CacheEntryListenerException;
import javax.cache.event.CacheEntryRemovedListener;
import javax.cache.event.CacheEntryUpdatedListener;

@Slf4j
public class CustomPostCreatedCacheEventListener implements CacheEntryCreatedListener<Long, Post>,
        CacheEntryUpdatedListener<Long, Post>,
        CacheEntryExpiredListener<Long, Post>,
        CacheEntryRemovedListener<Long, Post> {

    @Override
    public void onCreated(final Iterable<CacheEntryEvent<? extends Long, ? extends Post>> cacheEntryEvents) throws CacheEntryListenerException {
        for (CacheEntryEvent entryEvent: cacheEntryEvents) {
            log.info("Cached [key: {}, value: {}]. Type: {}", entryEvent.getKey(), entryEvent.getValue(), entryEvent.getEventType());
        }
    }

    @Override
    public void onExpired(final Iterable<CacheEntryEvent<? extends Long, ? extends Post>> cacheEntryEvents) throws CacheEntryListenerException {
        for (CacheEntryEvent entryEvent: cacheEntryEvents) {
            log.info("Expired: [key: {}, value: {}]. Type: {}", entryEvent.getKey(), entryEvent.getValue(), entryEvent.getEventType());
        }
    }

    @Override
    public void onRemoved(final Iterable<CacheEntryEvent<? extends Long, ? extends Post>> cacheEntryEvents) throws CacheEntryListenerException {
        for (CacheEntryEvent entryEvent: cacheEntryEvents) {
            log.info("Removed: [key: {}, value: {}]. Type: {}", entryEvent.getKey(), entryEvent.getValue(), entryEvent.getEventType());
        }
    }

    @Override
    public void onUpdated(final Iterable<CacheEntryEvent<? extends Long, ? extends Post>> cacheEntryEvents) throws CacheEntryListenerException {
        for (CacheEntryEvent entryEvent: cacheEntryEvents) {
            log.info("Updated: [key: {}, value: {}]. Type: {}", entryEvent.getKey(), entryEvent.getValue(), entryEvent.getEventType());
        }
    }
}
