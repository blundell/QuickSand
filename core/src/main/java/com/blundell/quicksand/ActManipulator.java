package com.blundell.quicksand;

import com.blundell.quicksand.act.Act;
import com.blundell.quicksand.viscosity.Viscosity;

class ActManipulator {

    private final AnimationTracker animationTracker;
    private final DurationCalculator durationCalculator;
    private final ViscosityCollection viscosityCollection;

    ActManipulator(AnimationTracker animationTracker, DurationCalculator durationCalculator, ViscosityCollection viscosityCollection) {
        this.animationTracker = animationTracker;
        this.durationCalculator = durationCalculator;
        this.viscosityCollection = viscosityCollection;
    }

    public void manipulate(final String key, Act act) {
        act.addListener(
                new Act.StartListener() {
                    @Override
                    public void onStart(Act act) {
                        long timesTransitionViewed = animationTracker.getCount(key);
                        Viscosity viscosity = viscosityCollection.getFor(key);
                        long transitionDuration = durationCalculator.calculateNewDuration(act, timesTransitionViewed, viscosity);
                        act.setDuration(transitionDuration);

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
