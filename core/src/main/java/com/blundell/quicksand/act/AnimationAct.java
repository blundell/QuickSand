package com.blundell.quicksand.act;

import android.view.ViewPropertyAnimator;

class AnimationAct implements Act {

    private final ViewPropertyAnimator animator;
    private final int id;
    private final boolean first;
    private final boolean last;

    public AnimationAct(ViewPropertyAnimator animator, int id, boolean first, boolean last) {
        this.animator = animator;
        this.id = id;
        this.first = first;
        this.last = last;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean isFirst() {
        return first;
    }

    @Override
    public boolean isLast() {
        return last;
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
