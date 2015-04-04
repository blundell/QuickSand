package com.blundell.quicksand.act;

/**
 * An Act is our abstraction away from Animations and Transitions, allowing us to treat them both as equals
 */
public interface Act {

    void setDuration(long duration);

    long getDuration();

    void addListener(StartListener startListener);

    interface StartListener {
        void onStart(Act act);

        void onFinish(Act act);
    }
}
