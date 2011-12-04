package flood;

import service.cache.*;
import java.util.*;

/**
 *
 * @author Mustafa
 */
public class RateLimitManager {

    private AbstractCache cache;

    public RateLimitManager() {
    }

    public void setCache(AbstractCache cache) {
        this.cache = cache;
    }

    public boolean isAllowed(String actionKey, int timePeriod, int limit) {
        Action action = cache.get(actionKey, Action.class);            
        if (action == null) {
            action = new Action(getTimeStamp(), limit);
            cache.set(actionKey, action);
        } else {
            long actionTime = action.getTimestamp();
            if (getTimeStamp() - actionTime > timePeriod) {
                action.reset(limit, getTimeStamp());                
                cache.set(actionKey, action);
            } else if (action.updateAndCheck()) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    private static long getTimeStamp() {
        return (new Date()).getTime();
    }
}
