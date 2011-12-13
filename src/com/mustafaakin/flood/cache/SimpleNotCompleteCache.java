package com.mustafaakin.flood.cache;

import java.util.HashMap;
import java.util.Map;
import play.cache.CacheImpl;
/**
 *
 * @author Mustafa
 */
public class SimpleNotCompleteCache implements CacheImpl {

    HashMap<String, Object> values = new HashMap<>();

    @Override
    public void add(String string, Object o, int i) {
        throw new UnsupportedOperationException("Not needed.");
    }

    @Override
    public boolean safeAdd(String string, Object o, int i) {
        throw new UnsupportedOperationException("Not needed.");
    }

    @Override
    public void set(String string, Object o, int i) {
        values.put(string, o);
    }

    @Override
    public boolean safeSet(String string, Object o, int i) {
        throw new UnsupportedOperationException("Not needed.");
    }

    @Override
    public void replace(String string, Object o, int i) {
        throw new UnsupportedOperationException("Not needed.");
    }

    @Override
    public boolean safeReplace(String string, Object o, int i) {
        throw new UnsupportedOperationException("Not needed.");
    }

    @Override
    public Object get(String string) {
        return values.get(string);
    }

    @Override
    public Map<String, Object> get(String[] strings) {
        throw new UnsupportedOperationException("Not needed.");
    }

    @Override
    public long incr(String string, int i) {
        throw new UnsupportedOperationException("Not needed.");
    }

    @Override
    public long decr(String string, int i) {
        throw new UnsupportedOperationException("Not needed.");
    }

    @Override
    public void clear() {
        values.clear();
    }

    @Override
    public void delete(String string) {
        throw new UnsupportedOperationException("Not needed.");
    }

    @Override
    public boolean safeDelete(String string) {
        throw new UnsupportedOperationException("Not needed.");
    }

    @Override
    public void stop() {
        throw new UnsupportedOperationException("Not needed.");
    }

}
