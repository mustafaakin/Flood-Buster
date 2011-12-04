package service.cache;

/**
 * A generic key-value store interface, e.g. for HashMap, memcached etc.
 *
 * @author ahmet alp balkan
 *
 */
public abstract class AbstractCache {

    public abstract void set(String key, Object value);

    public abstract Object get(String key);

    public abstract <T> T get(String key, Class<T> asType);

    public abstract void set(String key, Object value, String expiration);

    public abstract void delete(String key);
}