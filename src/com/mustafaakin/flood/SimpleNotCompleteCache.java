package com.mustafaakin.flood;

import java.util.HashMap;
import java.util.Map;
import play.cache.CacheImpl;

/**
 *
 * @author Mustafa
 */
public class SimpleNotCompleteCache implements CacheImpl {

    HashMap<String, Object> values = new HashMap<String, Object>();

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
        ExpiringAction expAct;
        if (values.containsKey(string)) {
            expAct = (ExpiringAction) values.get(string);
        } else {
            expAct = new ExpiringAction((Integer)o, System.currentTimeMillis(), i);
        }
        values.put(string, expAct);
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
        ExpiringAction a = (ExpiringAction) values.get(string);
        if (a == null) {
            return null;
        } else if (a.TTL + a.timeStamp < System.currentTimeMillis()) {
            values.remove(string);
            return null;
        }
        return a;
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
        ExpiringAction ea = (ExpiringAction)this.get(string);
        ea.count -= i;
        values.put(string, ea);
        return ea.count;
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

    class ExpiringAction {
        private int count;
        private long timeStamp;
        private int TTL;

        public ExpiringAction(int count, long timeStamp, int TTL) {
            this.count = count;
            this.timeStamp = timeStamp;
            this.TTL = TTL;
        }
    }
}
