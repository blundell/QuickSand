package com.blundell.quicksand;

import android.view.ViewPropertyAnimator;

import com.blundell.quicksand.viscosity.Viscosity;
import com.novoda.notils.logger.simple.Log;

class AnimationManipulator {

    private final AnimationTracker animationTracker;
    private final DurationCalculator durationCalculator;
    private final ViscosityCollection viscosityCollection;

    // Future enhancement: Use the Interpolator interface and classes to degrade properties

    AnimationManipulator(AnimationTracker animationTracker, DurationCalculator durationCalculator, ViscosityCollection viscosityCollection) {
        this.animationTracker = animationTracker;
        this.durationCalculator = durationCalculator;
        this.viscosityCollection = viscosityCollection;
        Log.setShowLogs(true);
    }

    public void manipulate(final String key, final ViewPropertyAnimator animator) {
        animator.withStartAction(new Runnable() {
            @Override
            public void run() {
                long timesTransitionViewed = animationTracker.getCount(key);
                Viscosity viscosity = viscosityCollection.getFor(key);
                long transitionDuration = durationCalculator.calculateNewDuration(animator, timesTransitionViewed, viscosity);
                Log.d("Animation started " + key);
                Log.d("Duration will be " + transitionDuration);
                animator.setDuration(transitionDuration);

                animationTracker.incrementTransitionSetViewCount(key, transitionDuration);
            }
        })
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("Animation ended " + key);
                        Log.d("Animation viewed : " + animationTracker.getCount(key) + " times");
                    }
                });
    }

    public void resetTransition(String key) {
        animationTracker.resetCount(key);
    }
}
