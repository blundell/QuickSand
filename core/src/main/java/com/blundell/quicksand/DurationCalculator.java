package com.blundell.quicksand;

import android.view.ViewPropertyAnimator;

import com.blundell.quicksand.viscosity.Viscosity;
import com.novoda.notils.logger.simple.Log;

class DurationCalculator {

    public long calculateNewDuration(AccessibleTransition transition, long timesTransitionViewed, Viscosity viscosity) {
        long currentDuration = transition.getDuration();
        return calculateDuration(timesTransitionViewed, viscosity, currentDuration);
    }

    public long calculateNewDuration(ViewPropertyAnimator animator, long timesAnimationViewed, Viscosity viscosity) {
        long currentDuration = animator.getDuration();
        return calculateDuration(timesAnimationViewed, viscosity, currentDuration);
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
