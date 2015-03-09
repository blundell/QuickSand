package com.blundell.quicksand;

import android.content.Context;
import android.transition.Transition;

public class Quicksand {

    private static TransitionTracker transitionTracker;

    public static void createSandTrap(Context context) {
        TransitionCountPreferences preferences = TransitionCountPreferences.newInstance(context);
        transitionTracker = new TransitionTracker(preferences);
    }

    // This is the public facing API, act accordingly

    public static void trap(final String key, Transition transition) {
        transitionTracker.manage(key, transition);
    }

}
