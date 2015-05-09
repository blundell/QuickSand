package com.blundell.quicksand;

import android.content.Context;
import android.transition.Transition;
import android.view.View;
import android.view.ViewPropertyAnimator;

import com.blundell.quicksand.act.Act;
import com.blundell.quicksand.act.ActFactory;
import com.blundell.quicksand.viscosity.ViscosityInterpolator;
import com.novoda.notils.exception.DeveloperError;

import java.util.List;
import java.util.Map;

/**
 * Quicksand allows the monitoring and manipulation of any Android animations.
 * This allows you to speed up animations the user has seen many times, thus allowing you to surprise
 * and delight your users to start with and then get straight to business after they are used to your UX.
 */
public class Quicksand {

    private static ActManipulator actManipulator;
    private static ActFactory actFactory;

    // This is the public facing API, act accordingly

    /**
     * Initialises the Quicksand library adding a Map of viscosities which allow you to change transition duration over time.
     * Only needs to be called once.
     * You will most likely want to call this in onCreate of your (@link android.app.Application}.
     *
     * @param context     any context, we will take the application context from this to avoid activity leaks
     * @param viscosities a map which contains key values to allow you to change transition duration over time.
     */
    public static void create(Context context, Map<String, ViscosityInterpolator> viscosities) {
        Context applicationContext = context.getApplicationContext();
        AnimationCounter counter = AnimationCounter.newInstance(applicationContext);
        AnimationTracker tracker = new AnimationTracker(counter, new CountDownTimerFactory());
        DurationCalculator durationCalculator = new DurationCalculator();
        ViscosityInterpolatorMap viscosityCollection = ViscosityInterpolatorMap.newInstance(viscosities);
        actManipulator = new ActManipulator(tracker, durationCalculator, viscosityCollection);
        actFactory = new ActFactory();
    }

    /**
     * Quicksand will manipulate your transition duration based on the {@link ViscosityInterpolator} for the key.
     * Animations can be grouped by the same {@param key} so that the duration is not incremented multiple times for one group.
     * If you do not group by key, each transition will be treated separate and so will have an independent transition duration.
     *
     * @param key         a unique key to group a set of transition
     * @param transitions the transition or transitions to be manipulated
     */
    public static void trap(String key, Transition... transitions) {
        checkLibraryInstantiation();
        List<Act> acts = actFactory.getActs(transitions);
        trap(key, acts);
    }

    public static void trap(String key, View... views) {
        checkLibraryInstantiation();
        ViewPropertyAnimator[] animators = new ViewPropertyAnimator[views.length];
        for (int i = 0; i < views.length; i++) {
            View v = views[i];
            animators[i] = v.animate();
        }
        trap(key, animators);
    }

    public static void trap(String key, ViewPropertyAnimator... animators) {
        checkLibraryInstantiation();
        List<Act> acts = actFactory.getActs(animators);
        trap(key, acts);
    }

    private static void trap(String key, List<Act> acts) {
        for (Act act : acts) {
            actManipulator.manipulate(key, act);
        }
    }

    /**
     * Allows you to reset a transition trap, meaning the transition will next run at the original speed
     *
     * @param key the key of the transition you wish to reset
     */
    public static void resetTrap(String key) {
        checkLibraryInstantiation();
        actManipulator.resetTransition(key);
    }

    private static void checkLibraryInstantiation() {
        if (actManipulator == null) {
            throw new DeveloperError("Please call create(Context) first to initialise this library."); // TODO use arrow logger
        }
    }
}
