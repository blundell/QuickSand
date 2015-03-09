package com.blundell.quicksand.viscosity;

public class NoChangeViscosity implements Viscosity {
    @Override
    public long calculateDuration(long currentDuration, long viewCount) {
        return currentDuration;
    }
}
