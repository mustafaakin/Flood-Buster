package com.mustafaakin.flood;

import com.mustafaakin.flood.cache.AbstractCache;
import java.util.Date;

/**
 *
 * @author Mustafa
 */
public class RateLimitManager {

    private AbstractCache cache;
    /**
     * If any error happens because of Cache mechanism, it will not cause a
     * crash, and instead return this CACHE_EXCEPTION value, which is unlikely
     * to be reached.
     */
    public final static int CACHE_EXCEPTION = Integer.MAX_VALUE;

    /**
     *
     */
    public RateLimitManager() {
    }

    public RateLimitManager(AbstractCache cache) {
        this.setCache(cache);
    }

    /**
     * Sets the cache to given AbstractCache implementation.
     *
     * @param cache
     */
    public void setCache(AbstractCache cache) {
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
    public int getAllowedActionCount(String actionKey, int timePeriod, int actionLimit) throws CacheNotSetException {
        if (cache == null) {
            throw new CacheNotSetException();
        }
        try {
            Action action = cache.get(actionKey, Action.class);
            if (action == null) { // Action is not available in cache, needs initialization.
                action = new Action(getTimeStamp(), actionLimit);
                
            } else {
                long actionTime = action.getTimestamp();
                long timeDifference = getTimeStamp() - actionTime; // How many milliseconds has passed before 

                if (timeDifference > timePeriod) { // True if the action has expired, needs to be reset.
                    action.reset(actionLimit, getTimeStamp());
                } else {
                    action.updateRemaining();
                }
            }
            cache.set(actionKey, action); // Will eventually update cache with action, so it is set in the end.
            return action.getRemaining();
        } catch (Exception e) {
            return CACHE_EXCEPTION;
        }
    }

    private static long getTimeStamp() {
        return (new Date()).getTime();
    }
}
