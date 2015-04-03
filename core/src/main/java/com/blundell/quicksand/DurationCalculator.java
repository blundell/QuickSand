package com.blundell.quicksand;

import com.blundell.quicksand.act.Act;
import com.blundell.quicksand.viscosity.Viscosity;
import com.novoda.notils.logger.simple.Log;

class DurationCalculator {

    public long calculateNewDuration(Act act, long timesTransitionViewed, Viscosity viscosity) {
        long currentDuration = act.getDuration();
        Log.d("DUR xxx " + currentDuration);
        return calculateDuration(timesTransitionViewed, viscosity, currentDuration);
    }

    private long calculateDuration(long timesAnimationViewed, Viscosity viscosity, long currentDuration) {
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
