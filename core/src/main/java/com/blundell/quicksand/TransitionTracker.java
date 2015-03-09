package com.blundell.quicksand;

import android.os.CountDownTimer;
import android.transition.Transition;

import com.blundell.quicksand.viscosity.NoChangeViscosityCommand;
import com.novoda.notils.logger.simple.Log;

import java.util.HashMap;
import java.util.Map;

class TransitionTracker {

    private final Map<String, CountDownTimer> monitoredAnimations = new HashMap<>();

    // Future enhancement: Use the Interpolator interface and classes to degrade properties

    private TransitionCounter transitionCounter;

    TransitionTracker(TransitionCounter transitionCountPreferences) {
        this.transitionCounter = transitionCountPreferences;
        Log.setShowLogs(true);
    }

    // Need to:
    // match transitions using the key's
    //  this includes considering activity transitions that don't start at the same time
    //  but do start in the duration of the animation beforehand
    //  when we have a pair or more than a pair of transitions we only want to "tick" one viewing of the transition
    // Implement a way to select an algorithm for changing the anim duration
    //  this will most likely follow a design pattern: the command pattern?
    //  could be named algorithms like "expontential back off"

    void manipulate(final String key, Transition transition) {
        transition.addListener(new AccessibleTransitionListener() {
            @Override
            protected void onTransitionStart(AccessibleTransition transition) {
                long timesAnimationViewed = transitionCounter.getCount(key);
                long transitionDuration = calculateNewDuration(transition, timesAnimationViewed);
                Log.d("----");
                Log.d("Transition started " + key);
                Log.d("Duration will be " + transitionDuration);
                Log.d("----");
                transition.setDuration(transitionDuration);

                incrementAnimationSetViewCount(transitionDuration, key);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                Log.d("----");
                Log.d("Transition ended " + key);
                Log.d("Animation viewed : " + transitionCounter.getCount(key) + " times");
                Log.d("Duration was " + transition.getDuration());
                Log.d("----");
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
        }
        monitoredAnimations.put(key, latestAnimationCountdown);
    }

    /**
     * This is where we change the animation duration & is where we should implement user choice for
     * the algorithm used to decrement animation time
     */
    private static long calculateNewDuration(AccessibleTransition transition, long timesAnimationViewed) {
        long currentDuration = transition.getDuration();
        if (currentDuration == 0) {
            Log.e("duration was zero");
            return 0;
        }
        if (timesAnimationViewed == 0) {
            Log.v("first time viewing so no duration change");
            return currentDuration;
        }
        return calculateNewDuration(currentDuration, timesAnimationViewed);
    }

    private static long calculateNewDuration(long currentDuration, long timesAnimationViewed) {
        // Implement degradation algorithms here
        // Consider the command pattern
        // Consider interpolator
        return new NoChangeViscosityCommand().calculateDuration(currentDuration, timesAnimationViewed);
    }
}
