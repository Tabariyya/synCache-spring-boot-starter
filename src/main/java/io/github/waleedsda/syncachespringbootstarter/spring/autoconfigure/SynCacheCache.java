package io.github.waleedsda.syncachespringbootstarter.spring.autoconfigure;

import io.github.waleedsda.synCache.Controller;
import org.springframework.cache.Cache;

import java.util.concurrent.Callable;

public class SynCacheCache implements Cache {

    private final String name;
    private final Controller controller;

    public SynCacheCache(String name, Controller controller) {
        this.name = name;
        this.controller = controller;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public Controller getNativeCache() {
        return controller;
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
        return (((controller.get(name, key.toString(), type))));
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
        controller.set(name, key.toString(), value);

    }


    @Override
    public void evict(Object key) {
        controller.evict(name, key.toString());
    }


    @Override
    public void clear() {

    }


}
