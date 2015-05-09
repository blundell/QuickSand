package com.blundell.quicksand;

import com.blundell.quicksand.viscosity.ViscosityInterpolator;
import com.novoda.notils.logger.simple.Log;

class DurationCalculator {

    public long calculateNewDuration(ViscosityInterpolator viscosity, long timesAnimationViewed, long currentDuration) {
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
