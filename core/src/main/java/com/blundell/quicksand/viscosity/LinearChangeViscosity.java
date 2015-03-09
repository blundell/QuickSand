package com.blundell.quicksand.viscosity;

/**
 * TODO fix this so that it slows the animation down in a sensible way
 */
public class LinearChangeViscosity implements Viscosity {
    @Override
    public long calculateDuration(long currentDuration, long viewCount) {
        return currentDuration / viewCount;
    }
}
