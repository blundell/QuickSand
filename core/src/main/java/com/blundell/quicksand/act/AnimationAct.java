package com.blundell.quicksand.act;

import android.view.ViewPropertyAnimator;

class AnimationAct implements Act {

    final ViewPropertyAnimator animator;

    public AnimationAct(ViewPropertyAnimator animator) {
        this.animator = animator;
    }

    @Override
    public void setDuration(long duration) {
        animator.setDuration(duration);
    }

    @Override
    public long getDuration() {
        return animator.getDuration();
    }

    @Override
    public void addListener(final StartListener listener) {
        animator.withStartAction(
                new Runnable() {
                    @Override
                    public void run() {
                        listener.onStart(AnimationAct.this);
                    }
                })
                .withEndAction(
                        new Runnable() {
                            @Override
                            public void run() {
                                listener.onFinish(AnimationAct.this);
                            }
                        });
    }
}
