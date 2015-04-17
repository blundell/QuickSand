package com.blundell.quicksand;

import android.os.CountDownTimer;

import com.blundell.quicksand.act.Act;

import java.util.HashMap;
import java.util.Map;

class AnimationTracker {

    private final Map<String, CountDownTimer> monitoredAnimations = new HashMap<>();

    private final AnimationCounter animationCounter;
    private final CountDownTimerFactory timerFactory;

    AnimationTracker(AnimationCounter animationCounter, CountDownTimerFactory timerFactory) {
        this.animationCounter = animationCounter;
        this.timerFactory = timerFactory;
    }

    /**
     * <p/>
     * We define a set of animations by:
     * all having the same key
     * starting in a chain like fashion i.e. start before the previous ends
     * <p/>
     * Once the first animation duration has finished any new animation with the same key is defined as a new start
     *
     * @param key      the key that binds the set of animations
     * @param duration how long this animation (key) will run for
     * @return true if this is the first animation in a set of animations
     */
    public boolean isTheStartOfANewAnimationSet(final String key, long duration) {
        CountDownTimer latestAnimationCountdown = timerFactory.getTimer(
                duration, new Runnable() {
                    @Override
                    public void run() {
                        monitoredAnimations.remove(key);
                    }
                });
        latestAnimationCountdown.start();

        CountDownTimer parallelAnimationCountdown = monitoredAnimations.get(key);
        boolean isANewAnimationSet;
        if (parallelAnimationCountdown == null) {
            isANewAnimationSet = true;
        } else {
            parallelAnimationCountdown.cancel();
            isANewAnimationSet = false;
        }
        monitoredAnimations.put(key, latestAnimationCountdown);
        return isANewAnimationSet;
    }

    public void incrementAnimationViewCount(String key) {
        animationCounter.incrementCount(key);
    }

    public long getCount(String key) {
        return animationCounter.getCount(key);
    }

    public void reset(String key) {
        animationCounter.reset(key);
    }

    public void saveDuration(String key, long duration) {
        animationCounter.saveDuration(key, duration);
    }

    public long getCurrentDuration(String key, Act act) {
        long storedDuration = animationCounter.getDuration(key);
        return storedDuration == 0 ? act.getDuration() : storedDuration;
    }
}
