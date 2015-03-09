package com.blundell.quicksand.viscosity;

public class NoChangeViscosityCommand implements ViscosityCommand {
    @Override
    public long calculateDuration(long currentDuration, long viewCount) {
        return currentDuration;
    }
}
