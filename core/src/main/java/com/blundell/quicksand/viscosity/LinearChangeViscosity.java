package com.blundell.quicksand.viscosity;

public class LinearChangeViscosity implements Viscosity {
    private static final int DEFAULT_MAX_VIEWS = 10;

    private final int maxViews;

    public static Viscosity defaultInstance() {
        return new LinearChangeViscosity(DEFAULT_MAX_VIEWS);
    }

    public LinearChangeViscosity(int maxViews) {
        this.maxViews = maxViews;
    }

    @Override
    public long calculateDuration(long currentDuration, long viewCount) {
        if (viewCount == maxViews) {
            return 0;
        }

        long viewCountAsPercent = maxViews / viewCount;
        return currentDuration * viewCountAsPercent;
    }
}
