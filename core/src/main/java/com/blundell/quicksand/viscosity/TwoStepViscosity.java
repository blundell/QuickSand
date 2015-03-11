package com.blundell.quicksand.viscosity;

/**
 * Will return one duration then after a threshold return half of that
 */
public class TwoStepViscosity implements Viscosity {

    private static final int DEFAULT_MAX_VIEWS = 10;

    private final int stepThreshold;

    public static Viscosity defaultInstance() {
        return new TwoStepViscosity(DEFAULT_MAX_VIEWS);
    }

    public TwoStepViscosity(int stepThreshold) {
        this.stepThreshold = stepThreshold;
    }

    @Override
    public long calculateDuration(long currentDuration, long viewCount) {
        return viewCount < stepThreshold ? currentDuration : currentDuration / 2;
    }
}
