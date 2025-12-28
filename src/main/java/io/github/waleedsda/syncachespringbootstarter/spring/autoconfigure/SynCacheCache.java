package io.github.waleedsda.syncachespringbootstarter.spring.autoconfigure;



import com.tabariyya.synCache.Cache;

import java.util.concurrent.Callable;

public class SynCacheCache implements org.springframework.cache.Cache {

    private final String name;
    private final Cache cache;

    public SynCacheCache(String name, Cache cache) {
        this.name = name;
        this.cache = cache;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public Cache getNativeCache() {
        return cache;
    }

    @Override
    public ValueWrapper get(Object key) {
        throw new UnsupportedOperationException(
                "SynCache requires type information. Please use get(Object key, Class<T> type) instead. " +
                        "Key: " + key + ", Cache: " + name
        );
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        return (((cache.get(name, key.toString(), type))));
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        throw new UnsupportedOperationException(
                "SynCache requires type information. Please use get(Object key, Class<T> type) instead. " +
                        "Key: " + key + ", Cache: " + name
        );
    }


    @Override
    public void put(Object key, Object value) {
        cache.set(name, key.toString(), value);

    }


    @Override
    public void evict(Object key) {
        cache.evict(name, key.toString());
    }


    @Override
    public void clear() {

    }


}
