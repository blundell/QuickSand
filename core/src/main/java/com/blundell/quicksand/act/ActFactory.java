package com.blundell.quicksand.act;

import android.transition.Transition;
import android.view.ViewPropertyAnimator;

import java.util.ArrayList;
import java.util.List;

public class ActFactory {

    public List<Act> getActs(Transition[] transitions) {
        List<Act> acts = new ArrayList<>(transitions.length);
        int id = 0;
        for (Transition transition : transitions) {
            boolean isFirst = id == 0;
            boolean isLast = id == transitions.length - 1;
            acts.add(getAct(transition, id, isFirst, isLast));
            id++;
        }
        return acts;
    }

    private Act getAct(Transition transition, int id, boolean isFirst, boolean isLast) {
        return new TransitionAct(new AccessibleTransition(transition), id, isFirst, isLast);
    }

    public List<Act> getActs(ViewPropertyAnimator[] animators) {
        List<Act> acts = new ArrayList<>(animators.length);
        int id = 0;
        for (ViewPropertyAnimator animator : animators) {
            boolean isFirst = id == 0;
            boolean isLast = id == animators.length - 1;
            acts.add(getAct(animator, id, isFirst, isLast));
            id++;
        }
        return acts;
    }

    private Act getAct(ViewPropertyAnimator animator, int id, boolean isFirst, boolean isLast) {
        return new AnimationAct(animator, id, isFirst, isLast);
    }
}
