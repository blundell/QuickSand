package com.blundell.quicksand;

import android.os.CountDownTimer;

class CountDownTimerFactory {

    public CountDownTimer getTimer(long millisInFuture, Runnable onFinishRunnable) {
        OneShotCountDownTimer countDownTimer = new OneShotCountDownTimer(millisInFuture);
        countDownTimer.executeThisOnFinish(onFinishRunnable);
        return countDownTimer;
    }

    static final class OneShotCountDownTimer extends CountDownTimer {

        private Runnable onFinish;

        /**
         * @param millisInFuture The number of millis in the future from the call
         *                       to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                       is called.
         */
        public OneShotCountDownTimer(long millisInFuture) {
            super(millisInFuture, millisInFuture);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // don't do anything on tick, that's why we're called OneShot
        }

        public void executeThisOnFinish(Runnable onFinish) {
            this.onFinish = onFinish;
        }

        @Override
        public void onFinish() {
            if (onFinish == null) {
                throw new IllegalStateException("This timer expects you to have passed a non null Runnable.");
            }
            onFinish.run();
        }
    }

}
