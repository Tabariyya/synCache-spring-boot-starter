package com.tabariyya.syncachespringbootstarter.spring.autoconfigure;

import com.tabariyya.synCache.Cache;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A simple Spring CacheManager for SynCache.
 */
@ConditionalOnProperty(prefix = "spring.cache", name = "type", havingValue = "syncache")
public class SynCacheCacheManager implements CacheManager {

    private final Cache cache;
    private final ConcurrentHashMap<String, org.springframework.cache.Cache> caches = new ConcurrentHashMap<>();

    public SynCacheCacheManager(Cache controller) {
        this.cache = controller;
    }

    @Override
    public org.springframework.cache.Cache getCache(String name) {
        return caches.computeIfAbsent(name, n -> new SynCacheCache(n, cache));
    }

    @Override
    public Collection<String> getCacheNames() {
        return Collections.unmodifiableSet(caches.keySet());
    }
}
