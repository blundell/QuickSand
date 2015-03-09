package com.blundell.quicksand;

import com.blundell.quicksand.viscosity.Viscosity;
import com.novoda.notils.logger.simple.Log;

class DurationCalculator {

    // Implement degradation algorithms as viscosity commands
    // Consider where the user  can set these?
    // We can have a map of keys vs viscosity & a default fallback, but where does this map sit?
    // Consider the command pattern
    // Consider interpolator

    public long calculateNewDuration(AccessibleTransition transition, long timesAnimationViewed, Viscosity viscosity) {
        long currentDuration = transition.getDuration();
        if (currentDuration == 0) {
            Log.e("duration was zero");
            return 0;
        }
        if (timesAnimationViewed == 0) {
            Log.v("first time viewing so no duration change");
            return currentDuration;
        }
        return viscosity.calculateDuration(currentDuration, timesAnimationViewed);
    }

}
