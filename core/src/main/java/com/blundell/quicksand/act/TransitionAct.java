package com.blundell.quicksand.act;

import android.transition.Transition;

class TransitionAct implements Act {

    private final AccessibleTransition transition;
    private final int id;
    private final boolean isFirst;
    private final boolean isLast;

    public TransitionAct(AccessibleTransition transition, int id, boolean isFirst, boolean isLast) {
        this.transition = transition;
        this.id = id;
        this.isFirst = isFirst;
        this.isLast = isLast;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean isFirst() {
        return isFirst;
    }

    @Override
    public boolean isLast() {
        return isLast;
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
    public void addListener(final StartListener listener) {
        transition.addListener(
                new SimplerTransitionListener() {
                    @Override
                    public void onTransitionStart(Transition transition) {
                        listener.onStart(TransitionAct.this);
                    }

                    @Override
                    public void onTransitionEnd(Transition transition) {
                        listener.onFinish(TransitionAct.this);
                    }
                });
    }
}
