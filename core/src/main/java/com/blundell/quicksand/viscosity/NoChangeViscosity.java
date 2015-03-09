package com.blundell.quicksand.viscosity;

/**
 * Will never change the duration always returning the current duration
 */
public class NoChangeViscosity implements Viscosity {
    @Override
    public long calculateDuration(long currentDuration, long viewCount) {
        return currentDuration;
    }
}
