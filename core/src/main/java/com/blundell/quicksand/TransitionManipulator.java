package com.blundell.quicksand;

import android.transition.Transition;

import com.blundell.quicksand.viscosity.Viscosity;

class TransitionManipulator {

    private final AnimationTracker animationTracker;
    private final DurationCalculator durationCalculator;
    private final ViscosityCollection viscosityCollection;

    TransitionManipulator(AnimationTracker animationTracker, DurationCalculator durationCalculator, ViscosityCollection viscosityCollection) {
        this.animationTracker = animationTracker;
        this.durationCalculator = durationCalculator;
        this.viscosityCollection = viscosityCollection;
    }

    public void manipulate(final String key, Transition transition) {
        transition.addListener(new AccessibleTransitionListener() {
            @Override
            protected void onTransitionStart(AccessibleTransition transition) {
                long timesTransitionViewed = animationTracker.getCount(key);
                Viscosity viscosity = viscosityCollection.getFor(key);
                long transitionDuration = durationCalculator.calculateNewDuration(transition, timesTransitionViewed, viscosity);
                transition.setDuration(transitionDuration);

                animationTracker.incrementTransitionSetViewCount(key, transitionDuration);
            }
        });
    }

    public void resetTransition(String key) {
        animationTracker.resetCount(key);
    }
}
