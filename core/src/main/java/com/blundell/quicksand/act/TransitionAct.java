package com.blundell.quicksand.act;

import android.transition.Transition;

class TransitionAct implements Act {

    private final AccessibleTransition transition;

    public TransitionAct(AccessibleTransition transition) {
        this.transition = transition;
    }

    @Override
    public void setDuration(long duration) {
        transition.setDuration(duration);
    }

    @Override
    public long getDuration() {
        return transition.getDuration();
    }

    @Override
    public void addListener(final StartListener startListener) {
        transition.addListener(
                new SimplerTransitionListener() {
                    @Override
                    public void onTransitionStart(Transition transition) {
                        startListener.onStart(TransitionAct.this);
                    }
                });
    }
}
