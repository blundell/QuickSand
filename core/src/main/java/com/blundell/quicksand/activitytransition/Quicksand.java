package com.blundell.quicksand.activitytransition;

import android.content.Context;
import android.transition.Transition;
import android.util.Log;

public class Quicksand {

    static Context globalContext;

    public static void startToSink(Context context) {
        globalContext = context.getApplicationContext();
    }

    // Use the Interpolator interface and classes to degrade properties

    public static void manage(Transition transition) {
        // Maybe generate a key when one isn't given to us
        // and keep a pool of known keys
        // (otherwise how do we differentiate transition usage, to keep tally of usages etc)

        manage(0, transition);
    }

    public static void manage(final int key, Transition transition) {   // TODO get out of static world
        Log.d("TAG", "This transition with key " + key + " has been seen 0 times");

        final TransitionCountPreferences preferences = TransitionCountPreferences.newInstance();

        // use the key to match up pairs of transitions

        long duration = transition.getDuration();

        if (preferences.getCount(key) > 4) {
            Log.d("TAG", "Transition seen four times, speeding up");
            duration = 0;
        }

        transition.setDuration(duration);

        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                Log.d("TAG", "Transition started");
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                Log.d("TAG", "Transition ended");
                Log.d("TAG", "Recorded another transition viewing");

                preferences.incrementCount(key);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }
}
