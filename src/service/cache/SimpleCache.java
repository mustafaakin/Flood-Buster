/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.cache;

import java.util.*;

/**
 *
 * @author Mustafa
 */
public class SimpleCache extends AbstractCache {

    HashMap<String, Object> values;

    public SimpleCache() {
        this.values = new HashMap<>();
    }

    @Override
    public void delete(String key) {
        this.values.remove(key);
    }

    @Override
    public Object get(String key) {
        return this.values.get(key);
    }

    @Override
    public <T> T get(String key, Class<T> asType) {
        return (T) this.values.get(key);
    }

    @Override
    public void set(String key, Object value) {
        this.values.put(key, value);
    }

    @Override
    public void set(String key, Object value, String expiration) {
        this.set(key, value);
    }
}
