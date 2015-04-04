package com.blundell.quicksand;

import com.blundell.quicksand.act.Act;
import com.blundell.quicksand.viscosity.Viscosity;

class ActManipulator {

    private final AnimationTracker animationTracker;
    private final DurationCalculator durationCalculator;
    private final ViscosityCollection viscosityCollection;

    private boolean onFinishDecrement;

    ActManipulator(AnimationTracker animationTracker, DurationCalculator durationCalculator, ViscosityCollection viscosityCollection) {
        this.animationTracker = animationTracker;
        this.durationCalculator = durationCalculator;
        this.viscosityCollection = viscosityCollection;
    }

    /**
     * Called in two potential scenarios:
     * - once everytime the act is created (before starting); therefore we need to maintain current duration ourselves external to act
     * - once only when act is created; therefore we need to increment the duration on act finish ready for next time
     * <p/>
     * onFinishDecrement means first time manipulate is called it will skip the setDuration in onFinish
     */
    public void manipulate(final String key, Act act) {
        setDuration(key, act);
        onFinishDecrement = false;
        act.addListener(
                new Act.StartListener() {
                    @Override
                    public void onStart(Act act) {
                        if (animationTracker.isTheStartOfANewAnimation(key, act.getDuration())) {
                            animationTracker.incrementAnimationViewCount(key);
                        }
                    }

                    @Override
                    public void onFinish(Act act) {
                        if (onFinishDecrement) {
                            setDuration(key, act);
                        }
                        onFinishDecrement = true;
                    }
                }
        );
    }

    private void setDuration(String key, Act act) {
        long timesTransitionViewed = animationTracker.getCount(key);
        Viscosity viscosity = viscosityCollection.getFor(key);
        long currentDuration = animationTracker.getCurrentDuration(key, act);
        long transitionDuration = durationCalculator.calculateNewDuration(timesTransitionViewed, viscosity, currentDuration);
        act.setDuration(transitionDuration);
        animationTracker.saveDuration(transitionDuration, key);
    }

    public void resetTransition(String key) {
        animationTracker.reset(key);
    }
}
