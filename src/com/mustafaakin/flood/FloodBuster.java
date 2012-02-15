package com.mustafaakin.flood;

import play.cache.CacheImpl;

/**
 *
 * @author Mustafa
 */
public class FloodBuster {

    private CacheImpl cache;

    /**
     *
     */
    public FloodBuster() {
        this.setCache(new SimpleNotCompleteCache());
    }

    public FloodBuster(CacheImpl cache) {
        this.setCache(cache);
    }

    /**
     * Sets the cache to given AbstractCache implementation.
     *
     * @param cache
     */
    public void setCache(CacheImpl cache) {
        this.cache = cache;
    }

    /**
     * Method checks for the cache, it has 4 possibilities. <ul> <li><b>Action
     * is not found on cache.</b> <p>Initializes the action information with
     * given key, period and action limit on the cache.</p> </li> <li><b>Action
     * is found, and limit is not reached.</b> <p>Decrements actions remaining
     * allowed action count by 1 and returns the remaining action amount.</p>
     * </li> <li><b>Action is found, and limit is exceeded.</b> <p>Still
     * decrements the actions remaining allowed action count by 1 and returns
     * the remaining action amount. It is useful for detecting abuses; if the
     * number is too small, system can handle the abuser.</li><li><b>Action is
     * found, but action has expired.</b><p>It resets the action to the given
     * time limit and timestamp.</p></li></ul>
     *
     * @param actionKey Represents the unique key of the associated action. It
     * is suggested to prepare action key as follows: user_id + '-' + actionKey.
     * @param timePeriod Represents the time amount that action is allowed to be
     * done. Chosen to be integer for scalability issues.
     * @param actionLimit Represents how many times that action can be done in
     * given period.
     * @return Returns the amount of remaining allowed actions. If it is larger
     * than 0, action should be considered allowed. If it is 0, action has just
     * run out of trials. If the number is negative, action has been repeated
     * absolute value of the returned value times after reaching the limit.
     * @throws CacheNotSetException If the Cache is not set to an AbstractCache
     * implementation, It will not be possible to fetch any information so an
     * exception is thrown.
     */
    public long getAllowedActionCount(String actionKey, int timePeriod, int actionLimit) {
        if (cache == null) {
            throw new FloodBusterException("Cache is not set.");
        }
        try {
            Object remaining = cache.get(actionKey);
            if (remaining == null) {
                cache.set(actionKey, actionLimit, timePeriod);
                return actionLimit;
            }
            return cache.decr(actionKey, 1);
        } catch (Exception ex) {
            return 1;
        }
    }
}
