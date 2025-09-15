package io.github.waleedsda.syncachespringbootstarter.spring.autoconfigure;

import com.synCache.CacheEntry;
import com.synCache.Controller;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

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
        Object value = controller.get(name, key.toString());
        return new SimpleValueWrapper(value);
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        return (T) controller.get(name, key.toString());
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {

        T value = get(key, (Class<T>) Object.class); // try to get from cache first
        if (value != null) return value;

        try {
            // compute the value using valueLoader
            T computedValue = valueLoader.call();
            put(key, computedValue); // store it in cache
            return computedValue;
        } catch (Exception e) {
            throw new RuntimeException("Error computing cache value for key: " + key, e);
        }
    }


    @Override
    public void put(Object key, Object value) {
        var entry = new CacheEntry(name, key.toString(), value, null);
        controller.set(entry);

    }


    @Override
    public void evict(Object key) {
        controller.evict(name, key.toString());
    }


    @Override
    public void clear() {

    }

}
