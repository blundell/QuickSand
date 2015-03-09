package com.blundell.quicksand;

import android.transition.Transition;

/**
 * A simpler listener that uses our {@link com.blundell.quicksand.AccessibleTransition} and only enforces
 * to override start and end
 */
abstract class AccessibleTransitionListener implements Transition.TransitionListener {
    @Override
    public void onTransitionStart(Transition transition) {
        AccessibleTransition accessibleTransition = new AccessibleTransition(transition);
        onTransitionStart(accessibleTransition);
    }

    protected abstract void onTransitionStart(AccessibleTransition transition);

    @Override
    public void onTransitionCancel(Transition transition) {
        // not-used
    }

    @Override
    public void onTransitionPause(Transition transition) {
        // not-used
    }

    @Override
    public void onTransitionResume(Transition transition) {
        // not-used
    }
}
