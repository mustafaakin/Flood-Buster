package com.mustafaakin.flood.cache;

import java.util.HashMap;

/**
 *
 * @author Mustafa
 */
public class SimpleCache extends AbstractCache {

    HashMap<String, Object> values;

    /**
     * 
     */
    public SimpleCache() {
        this.values = new HashMap<>();
    }

    /**
     * 
     * @param key
     */
    @Override
    public void delete(String key) {
        this.values.remove(key);
    }

    /**
     * 
     * @param key
     * @return
     */
    @Override
    public Object get(String key) {
        return this.values.get(key);
    }

    @Override
    public <T> T get(String key, Class<T> asType) {
        return (T) this.values.get(key);
    }

    /**
     * 
     * @param key
     * @param value
     */
    @Override
    public void set(String key, Object value) {
        this.values.put(key, value);
    }

    /**
     * 
     * @param key
     * @param value
     * @param expiration
     */
    @Override
    public void set(String key, Object value, String expiration) {
        this.set(key, value);
    }
}
