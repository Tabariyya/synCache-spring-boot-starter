package io.github.waleedsda.syncachespringbootstarter.spring.autoconfigure;

import io.github.waleedsda.synCache.Controller;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A simple Spring CacheManager for SynCache.
 */
public class SynCacheCacheManager implements CacheManager {

    private final Controller controller;
    private final ConcurrentHashMap<String, Cache> caches = new ConcurrentHashMap<>();

    public SynCacheCacheManager(Controller controller) {
        this.controller = controller;
    }

    @Override
    public Cache getCache(String name) {
        return caches.computeIfAbsent(name, n -> new SynCacheCache(n, controller));
    }

    @Override
    public Collection<String> getCacheNames() {
        return Collections.unmodifiableSet(caches.keySet());
    }
}
