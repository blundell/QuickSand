package com.blundell.quicksand.viscosity;

/**
 * Will return the current transition duration without change upto a maximum and then return 0
 */
public class AllOrNothingViscosity implements Viscosity {

    private static final int DEFAULT_MAX_VIEWS = 10;

    private final int maxViews;

    public static Viscosity defaultInstance() {
        return new AllOrNothingViscosity(DEFAULT_MAX_VIEWS);
    }

    public AllOrNothingViscosity(int maxViews) {
        this.maxViews = maxViews;
    }

    @Override
    public long calculateDuration(long currentDuration, long viewCount) {
        if (viewCount < maxViews) {
            return currentDuration;
        } else {
            return 0;
        }
    }
}
