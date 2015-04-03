package com.blundell.quicksand.act;

import android.animation.Animator;
import android.transition.Transition;

import com.novoda.notils.logger.simple.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * A decorated {@link android.transition.Transition} that allows you to see the transitions duration
 */
class AccessibleTransition {

    private final Transition transition;

    public AccessibleTransition(Transition transition) {
        this.transition = transition;
    }

    @SuppressWarnings("unchecked") // I'm a bad boy using reflection, but I know the cast is safe
    private static List<Animator> getAnimators(Transition transition) {
        List<Animator> animators = new ArrayList<>();
        try {
            Field animatorsField = Transition.class.getDeclaredField("mAnimators");
            animatorsField.setAccessible(true);
            animators.addAll((List<Animator>) animatorsField.get(transition));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Log.wtf(e, "Oops can't get the animators from the transition.");
        }
        return animators;
    }

    public long getDuration() {
        List<Animator> animators = getAnimators(transition);
        if (animators.isEmpty()) {
            return transition.getDuration();
        } else {
            return animators.get(0).getDuration();
        }
    }

    public void setDuration(long duration) {
        transition.setDuration(duration);
    }

    public void addListener(Transition.TransitionListener listener) {
        transition.addListener(listener);
    }
}
