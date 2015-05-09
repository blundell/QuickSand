package com.blundell.quicksand.viscosity;

/**
 * Will return one duration then after a threshold of X return half of the duration
 */
public class TwoStepViscosityInterpolator implements ViscosityInterpolator {

    private static final int DEFAULT_MAX_VIEWS = 10;

    private final int stepThreshold;

    public static ViscosityInterpolator defaultInstance() {
        return new TwoStepViscosityInterpolator(DEFAULT_MAX_VIEWS);
    }

    public TwoStepViscosityInterpolator(int stepThreshold) {
        this.stepThreshold = stepThreshold;
    }

    @Override
    public long calculateDuration(long currentDuration, long viewCount) {
        return viewCount < stepThreshold ? currentDuration : currentDuration / 2;
    }
}
