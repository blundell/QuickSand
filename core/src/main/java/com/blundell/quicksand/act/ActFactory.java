package com.blundell.quicksand.act;

import android.transition.Transition;
import android.view.ViewPropertyAnimator;

import java.util.ArrayList;
import java.util.List;

public class ActFactory {

    public List<Act> getActs(Transition[] transitions) {
        List<Act> acts = new ArrayList<>(transitions.length);
        for (Transition transition : transitions) {
            acts.add(getAct(transition));
        }
        return acts;
    }

    private Act getAct(Transition transition) {
        return new TransitionAct(new AccessibleTransition(transition));
    }

    public List<Act> getActs(ViewPropertyAnimator[] animators) {
        List<Act> acts = new ArrayList<>(animators.length);
        for (ViewPropertyAnimator animator : animators) {
            acts.add(getAct(animator));
        }
        return acts;
    }

    private Act getAct(ViewPropertyAnimator animator) {
        return new AnimationAct(animator);
    }
}
