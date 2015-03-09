package com.blundell.quicksand;

import com.blundell.quicksand.viscosity.NoChangeViscosityCommand;
import com.novoda.notils.logger.simple.Log;

class DurationCalculator {

    /**
     * This is where we change the animation duration & is where we should implement user choice for
     * the algorithm used to decrement animation time
     */
    public long calculateNewDuration(AccessibleTransition transition, long timesAnimationViewed) {
        long currentDuration = transition.getDuration();
        if (currentDuration == 0) {
            Log.e("duration was zero");
            return 0;
        }
        if (timesAnimationViewed == 0) {
            Log.v("first time viewing so no duration change");
            return currentDuration;
        }
        return calculateNewDuration(currentDuration, timesAnimationViewed);
    }

    private static long calculateNewDuration(long currentDuration, long timesAnimationViewed) {
        // Implement degradation algorithms here
        // Consider the command pattern
        // Consider interpolator
        return new NoChangeViscosityCommand().calculateDuration(currentDuration, timesAnimationViewed);
    }

}
