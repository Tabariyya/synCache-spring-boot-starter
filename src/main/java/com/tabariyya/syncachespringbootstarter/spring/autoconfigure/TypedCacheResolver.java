package com.tabariyya.syncachespringbootstarter.spring.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;


@Component
@ConditionalOnProperty(prefix = "spring.cache", name = "type", havingValue = "syncache")
public class TypedCacheResolver implements CacheResolver {

    private final CacheManager cacheManager;

    public TypedCacheResolver(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
        Collection<Cache> caches = new ArrayList<>();
        for (String cacheName : context.getOperation().getCacheNames()) {
            Cache cache = cacheManager.getCache(cacheName);
            if (cache != null) {
                caches.add(new TypedCacheWrapper(cache, context.getMethod().getReturnType()));
            }
        }
        return caches;
    }

    private static class TypedCacheWrapper implements Cache {
        private final Cache delegate;
        private final Class<?> defaultType;

        public TypedCacheWrapper(Cache delegate, Class<?> defaultType) {
            this.delegate = delegate;
            this.defaultType = defaultType;
        }

        @Override
        public String getName() { return delegate.getName(); }

        @Override
        public Object getNativeCache() { return delegate.getNativeCache(); }

        @Override
        public ValueWrapper get(Object key) {
            Object value = delegate.get(key, defaultType);
            return value != null ? new SimpleValueWrapper(value) : null;
        }

        @Override
        public <T> T get(Object key, Class<T> type) {
            return delegate.get(key, type);
        }

        @Override
        public <T> T get(Object key, Callable<T> valueLoader) {
            return delegate.get(key, valueLoader);
        }

        @Override
        public void put(Object key, Object value) {
            delegate.put(key, value);
        }

        @Override
        public void evict(Object key) {
            delegate.evict(key);
        }

        @Override
        public void clear() {
            delegate.clear();
        }
    }
}
