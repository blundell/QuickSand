package com.blundell.quicksand;

import android.view.ViewPropertyAnimator;

import com.blundell.quicksand.viscosity.Viscosity;

class AnimationManipulator {

    private final AnimationTracker animationTracker;
    private final DurationCalculator durationCalculator;
    private final ViscosityCollection viscosityCollection;

    AnimationManipulator(AnimationTracker animationTracker, DurationCalculator durationCalculator, ViscosityCollection viscosityCollection) {
        this.animationTracker = animationTracker;
        this.durationCalculator = durationCalculator;
        this.viscosityCollection = viscosityCollection;
    }

    public void manipulate(final String key, final ViewPropertyAnimator animator) {
        animator.withStartAction(new Runnable() {
            @Override
            public void run() {
                long timesTransitionViewed = animationTracker.getCount(key);
                Viscosity viscosity = viscosityCollection.getFor(key);
                long transitionDuration = durationCalculator.calculateNewDuration(animator, timesTransitionViewed, viscosity);
                animator.setDuration(transitionDuration);

                if (animationTracker.isTheStartOfANewAnimation(key, transitionDuration)) {
                    animationTracker.incrementAnimationViewCount(key);
                }
            }
        });
    }

    public void resetTransition(String key) {
        animationTracker.resetCount(key);
    }
}
