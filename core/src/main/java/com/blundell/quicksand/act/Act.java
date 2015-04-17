package com.blundell.quicksand.act;

/**
 * An Act is our abstraction away from Animations and Transitions, allowing us to treat them both as equals
 */
public interface Act {

    int getId();

    boolean isFirst();

    boolean isLast(); // TODO smells like I should have a first class collection? (and call monitor once for the collection WAT)

    void setDuration(long duration);

    long getDuration();

    void addListener(StartListener startListener); // Maybe split this into addStartListener and addEndListener

    interface StartListener {
        void onStart(Act act);

        void onFinish(Act act);
    }
}
