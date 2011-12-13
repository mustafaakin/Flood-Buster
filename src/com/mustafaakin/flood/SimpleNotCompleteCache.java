package com.mustafaakin.flood;

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
        ExpiringAction expAct;
        if (values.containsKey(string)) {
            expAct = (ExpiringAction) values.get(string);
        } else {
            expAct = new ExpiringAction((Action) o, System.currentTimeMillis(), i);
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
        return a.action;
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

    class ExpiringAction {

        private Action action;
        private long timeStamp;
        private int TTL;

        public ExpiringAction(Action action, long timeStamp, int TTL) {
            this.action = action;
            this.timeStamp = timeStamp;
            this.TTL = TTL;
        }
        
        public String toString(){
            return action + "/" + timeStamp + "/" + TTL;
        }
    }
}
