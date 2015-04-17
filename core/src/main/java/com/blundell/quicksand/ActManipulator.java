package com.blundell.quicksand;

import com.blundell.quicksand.act.Act;
import com.blundell.quicksand.viscosity.Viscosity;

class ActManipulator {

    private final AnimationTracker animationTracker;
    private final DurationCalculator durationCalculator;
    private final ViscosityCollection viscosityCollection;

    private boolean preRunDurationSet = true;

    ActManipulator(AnimationTracker animationTracker, DurationCalculator durationCalculator, ViscosityCollection viscosityCollection) {
        this.animationTracker = animationTracker;
        this.durationCalculator = durationCalculator;
        this.viscosityCollection = viscosityCollection;
    }

    /**
     * Called in two potential scenarios:
     * - S1 once every time the act is created (before starting); therefore we need to maintain current duration ourselves external to act
     * - S2 once only when act is created; therefore we need to increment the duration in the listener on act finish ready for next time
     * <p/>
     * onFinishUpdate means first time manipulate() is called it will skip the setDuration in onFinish ()
     */
    public void manipulate(String key, Act act) {
        updateDurationPreRun(key, act);
        monitorAct(key, act);
    }

    private void updateDurationPreRun(String key, Act act) {
        updateDuration(key, act);
        if (act.isLast()) {
            preRunDurationSet = true;
        }
    }

    private void updateDuration(String key, Act act) {
        long duration = getViscosityAffectedDuration(key, act);
        act.setDuration(duration);
        animationTracker.saveDuration(key + act.getId(), duration);
    }

    private long getViscosityAffectedDuration(String key, Act act) {
        Viscosity viscosity = viscosityCollection.getFor(key);
        long viewCount = animationTracker.getCount(key);
        long currentDuration = getCurrentDuration(key, act);
        return durationCalculator.calculateNewDuration(viscosity, viewCount, currentDuration);
    }

    private long getCurrentDuration(String key, Act act) {
        return animationTracker.getCurrentDuration(key + act.getId(), act);
    }

    private void monitorAct(final String key, Act act) {
        act.addListener(
                new Act.StartListener() {
                    @Override
                    public void onStart(Act act) {
                        if (act.isLast()) { // (animationTracker.isTheStartOfANewAnimationSet(key, act.getDuration())) {
                            animationTracker.incrementAnimationViewCount(key);
                        }
                    }

                    @Override
                    public void onFinish(Act act) {
                        updateDurationPostRun(act, key);
                        if (act.isLast()) {
                            preRunDurationSet = false;
                        }
                    }
                }
        );
    }

    private void updateDurationPostRun(Act act, String key) {
        if (preRunDurationSet) {
            return;
        }
        updateDuration(key, act);
    }

    public void resetTransition(String key) {
        animationTracker.reset(key);
    }
}
