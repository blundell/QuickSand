package com.blundell.quicksand.demo;

import android.graphics.Color;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class UnimportantForDemoUtils {

    public static void addTouchFeedback(final ImageView view) {
        view.setOnTouchListener(
                new View.OnTouchListener() {
                    private Rect rect;

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            view.setColorFilter(Color.argb(50, 0, 0, 0));
                            rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                        }
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            view.setColorFilter(Color.argb(0, 0, 0, 0));
                        }
                        if (event.getAction() == MotionEvent.ACTION_MOVE) {
                            if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                                view.setColorFilter(Color.argb(0, 0, 0, 0));
                            }
                        }
                        return false;
                    }
                });
    }

}
