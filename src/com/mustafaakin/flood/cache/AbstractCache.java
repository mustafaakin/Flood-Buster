package com.mustafaakin.flood.cache;

/**
 * A generic key-value store interface, e.g. for HashMap, memcached etc.
 *
 * @author ahmet alp balkan
 *
 */
public abstract class AbstractCache {

    /**
     * 
     * @param key
     * @param value
     */
    public abstract void set(String key, Object value);

    /**
     * 
     * @param key
     * @return
     */
    public abstract Object get(String key);

    /**
     * 
     * @param <T>
     * @param key
     * @param asType
     * @return
     */
    public abstract <T> T get(String key, Class<T> asType);

    /**
     * 
     * @param key
     * @param value
     * @param expiration
     */
    public abstract void set(String key, Object value, String expiration);

    /**
     * 
     * @param key
     */
    public abstract void delete(String key);
}