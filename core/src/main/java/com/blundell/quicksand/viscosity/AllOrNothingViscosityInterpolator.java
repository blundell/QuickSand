package com.blundell.quicksand.viscosity;

/**
 * Will return the current transition duration without change up to a maximum of X views and then return 0
 */
public class AllOrNothingViscosityInterpolator implements ViscosityInterpolator {

    private static final int DEFAULT_MAX_VIEWS = 10;

    private final int maxViews;

    public static ViscosityInterpolator defaultInstance() {
        return new AllOrNothingViscosityInterpolator(DEFAULT_MAX_VIEWS);
    }

    public AllOrNothingViscosityInterpolator(int maxViews) {
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
