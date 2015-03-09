package com.blundell.quicksand;

import android.content.Context;
import android.transition.Transition;

import com.novoda.notils.exception.DeveloperError;

/**
 * Quicksand allows the monitoring and manipulation of any Android animations.
 * This allows you to speed up animations the user has seen many times, thus allowing you to surprise
 * and delight your users to start with and then get straight to business after they are used to your UX.
 */
public class Quicksand {

    private static TransitionTracker transitionManipulator;

    // This is the public facing API, act accordingly

    /**
     * Initialises the Quicksand library.
     * Only needs to be called once.
     * You will most likely want to call this in onCreate of your (@link android.app.Application}.
     *
     * @param context any context, we will take the application context from this to avoid activity leaks
     */
    public static void createSandTrap(Context context) {
        Context applicationContext = context.getApplicationContext();
        TransitionCounter counter = TransitionCounter.newInstance(applicationContext);
        transitionManipulator = new TransitionTracker(counter);
    }

    /**
     * Quicksand will manipulate your transition duration based on the {@link com.blundell.quicksand.viscosity.ViscosityCommand} for the key.
     * Animations can be grouped by the same {@param key} so that the duration is not incremented multiple times for one group.
     * If you do not group by key, each transition will be treated separate and so will have an independent transition duration.
     *
     * @param key        a unique key to group a set of transition
     * @param transition the transition to be manipulated
     */
    public static void trap(String key, Transition transition) {
        if (transitionManipulator == null) {
            throw new DeveloperError("Please call createSandTrap(Context) first to initialise this library."); // TODO use arrow logger
        }
        transitionManipulator.manipulate(key, transition);
    }

}
