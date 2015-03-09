package com.blundell.quicksand;

import android.os.CountDownTimer;
import android.transition.Transition;

import com.novoda.notils.logger.simple.Log;

import java.util.HashMap;
import java.util.Map;

class TransitionTracker {

    private final Map<String, CountDownTimer> monitoredAnimations = new HashMap<>();
    private final DurationCalculator durationCalculator;

    // Future enhancement: Use the Interpolator interface and classes to degrade properties

    private TransitionCounter transitionCounter;

    TransitionTracker(TransitionCounter transitionCounter, DurationCalculator durationCalculator) {
        this.transitionCounter = transitionCounter;
        this.durationCalculator = durationCalculator;
        Log.setShowLogs(true);
    }

    // Need to:
    // Implement a way to select an algorithm for changing the anim duration
    //  this will most likely follow a design pattern: the command pattern?
    //  could be named algorithms like "expontential back off"

    void manipulate(final String key, Transition transition) {
        transition.addListener(new AccessibleTransitionListener() {
            @Override
            protected void onTransitionStart(AccessibleTransition transition) {
                long timesAnimationViewed = transitionCounter.getCount(key);
                long transitionDuration = durationCalculator.calculateNewDuration(transition, timesAnimationViewed);
                Log.d("Transition started " + key);
                Log.d("Duration will be " + transitionDuration);
                transition.setDuration(transitionDuration);

                incrementAnimationSetViewCount(transitionDuration, key);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                Log.d("Transition ended " + key);
                Log.d("Animation viewed : " + transitionCounter.getCount(key) + " times");
            }
        });
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
    private void incrementAnimationSetViewCount(final long transitionDuration, final String key) {
        CountDownTimer latestAnimationCountdown = new CountDownTimer(transitionDuration, transitionDuration) {

            @Override
            public void onTick(long millisUntilFinished) {
                // don't do anything on tick, that would be crazy
            }

            @Override
            public void onFinish() {
                monitoredAnimations.remove(key);
            }
        };
        latestAnimationCountdown.start();

        CountDownTimer parallelAnimationCountdown = monitoredAnimations.get(key);
        if (parallelAnimationCountdown == null) {
            transitionCounter.incrementCount(key);
        } else {
            parallelAnimationCountdown.cancel();
            Log.d("Transition is part of a group");
        }
        monitoredAnimations.put(key, latestAnimationCountdown);
    }

    public void resetTransition(String key) {
        transitionCounter.resetCount(key);
    }
}
