package com.mustafaakin.flood;

/**
 *
 * @author Mustafa
 */
public class Action {

    private long timestamp;
    private int remaining;

    /**
     * Initializes the Action object with given timestamp and remaining, which are must values.
     * @param timestamp Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT 
     * @param remaining
     */
    protected Action(long timestamp, int remaining) {
        this.timestamp = timestamp;
        this.remaining = remaining;
    }

    /**
     * Decrements the remaining action count by 1.
     */
    protected void updateRemaining() {
        remaining--;
    }

    /**
     * Resets the remaining value of the action to the remaining and updating the timestamp.
     * @param remaining
     * @param timestamp
     */
    protected void reset(int remaining, long timestamp) {
        this.timestamp = timestamp;
        this.remaining = remaining;
    }

    /**
     * 
     * @return
     */
    protected int getRemaining() {
        return remaining;
    }

    /**
     * 
     * @param remaining
     */
    protected void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    /**
     * 
     * @return Returns the timestamp the set of action
     */
    protected long getTimestamp() {
        return timestamp;
    }

    /**
     * 
     * @param timestamp
     */
    protected void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "R:" + remaining + " T:" + timestamp;
    }
}
