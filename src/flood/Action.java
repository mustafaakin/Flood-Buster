package flood;

/**
 *
 * @author Mustafa
 */
public class Action {

    private long timestamp;
    private int remaining;

    public Action(long timestamp, int remaining) {
        this.timestamp = timestamp;
        this.remaining = remaining;
    }

    public boolean updateAndCheck() {
        remaining--;
        return remaining > 0;
    }

    public void reset(int remaining, long timestamp) {
        this.timestamp = timestamp;
        this.remaining = remaining;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "R:" + remaining + " T:" + timestamp;
    }
    
    
}
