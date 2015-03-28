package com.blundell.quicksand;

import android.os.CountDownTimer;

import com.novoda.notils.logger.simple.Log;

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
     * TODO it could be useful to know if this animation is part of a set of anims elsewhere - split this method into two
     * <p/>
     * When an animation starts we increment the number of views
     * If a previous animation is running with the same key we do not increment the number of views
     * This is how we group a set of animations
     * As long as they are started in a chain like fashion i.e. start before the previous ends
     * then we should only increment the view count once
     */
    public void attemptToIncrementAnimationSetViewCount(final String key, long transitionDuration) {
        CountDownTimer latestAnimationCountdown = timerFactory.getTimer(transitionDuration, new Runnable() {
            @Override
            public void run() {
                monitoredAnimations.remove(key);
            }
        });
        latestAnimationCountdown.start();

        CountDownTimer parallelAnimationCountdown = monitoredAnimations.get(key);
        if (parallelAnimationCountdown == null) {
            animationCounter.incrementCount(key);
        } else {
            parallelAnimationCountdown.cancel();
            Log.d("Animation is part of a group");
        }
        monitoredAnimations.put(key, latestAnimationCountdown);
    }

    public long getCount(String key) {
        return animationCounter.getCount(key);
    }

    public void resetCount(String key) {
        animationCounter.resetCount(key);
    }
}
