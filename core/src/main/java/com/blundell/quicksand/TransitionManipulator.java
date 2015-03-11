package com.blundell.quicksand;

import android.transition.Transition;

import com.blundell.quicksand.viscosity.Viscosity;
import com.novoda.notils.logger.simple.Log;

class TransitionManipulator {

    private final AnimationTracker animationTracker;
    private final DurationCalculator durationCalculator;
    private final ViscosityCollection viscosityCollection;

    // Future enhancement: Use the Interpolator interface and classes to degrade properties

    TransitionManipulator(AnimationTracker animationTracker, DurationCalculator durationCalculator, ViscosityCollection viscosityCollection) {
        this.animationTracker = animationTracker;
        this.durationCalculator = durationCalculator;
        this.viscosityCollection = viscosityCollection;
        Log.setShowLogs(true);
    }

    public void manipulate(final String key, Transition transition) {
        transition.addListener(new AccessibleTransitionListener() {
            @Override
            protected void onTransitionStart(AccessibleTransition transition) {
                long timesTransitionViewed = animationTracker.getCount(key);
                Viscosity viscosity = viscosityCollection.getFor(key);
                long transitionDuration = durationCalculator.calculateNewDuration(transition, timesTransitionViewed, viscosity);
                Log.d("Transition started " + key);
                Log.d("Duration will be " + transitionDuration);
                transition.setDuration(transitionDuration);

                animationTracker.incrementTransitionSetViewCount(key, transitionDuration);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                Log.d("Transition ended " + key);
                Log.d("Transition viewed : " + animationTracker.getCount(key) + " times");
            }
        });
    }

    public void resetTransition(String key) {
        animationTracker.resetCount(key);
    }
}
