package com.mustafaakin.flood;

/**
 *
 * @author Mustafa
 */
public class Action implements java.io.Serializable {
    private int remaining;

    /**
     * Initializes the Action object with given timestamp and remaining, which are must values.
     * @param remaining
     */
    protected Action(int remaining) {
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
     */
    protected void reset(int remaining) {
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

    /**
     * 
     * @param timestamp
     */

    @Override
    public String toString() {
        return "R:" + remaining;
    }
}
