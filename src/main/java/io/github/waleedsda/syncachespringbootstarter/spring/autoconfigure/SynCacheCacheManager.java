package io.github.waleedsda.syncachespringbootstarter.spring.autoconfigure;

import com.synCache.Controller;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Collection;
import java.util.Collections;

public class SynCacheCacheManager implements CacheManager {

    private final Controller controller;

    public SynCacheCacheManager(Controller controller) {
        this.controller = controller;
    }

    @Override
    public Cache getCache(String name) {
        return new SynCacheCache(name, controller);
    }

    @Override
    public Collection<String> getCacheNames() {
        return Collections.emptyList(); // optional: support predefined cache names
    }
}
