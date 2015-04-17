package com.blundell.quicksand.demo.amazeanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blundell.quicksand.Quicksand;

import java.util.concurrent.TimeUnit;

/**
 * original author SiYao of https://github.com/2359media/EasyAndroidAnimations
 * <p/>
 * It doesn't matter what this animation is or how it works. This is just an example of a complex animation.
 * See the {@link #monitor(ImageView[])}} method for how you integrate with Quicksand.
 */
public class ExplodeAnimation {

    public static final String KEY_ANIMATION_SET = "NewKey";

    private static final int MATRIX_3X3 = 33;
    private static final long DURATION_LONG = TimeUnit.SECONDS.toMillis(2);

    private final View view;

    private int xParts;
    private int yParts;

    private ViewGroup parentView;
    private TimeInterpolator interpolator;
    private long duration;
    private AnimationListener listener;

    public interface AnimationListener {

        /**
         * This method is called when the animation ends.
         *
         * @param animation The Animation object.
         */
        void onAnimationEnd(ExplodeAnimation animation);
    }

    /**
     * This animation creates a bitmap of the view, divides them into
     * customizable number of X and Y parts and translates the parts away from
     * the center of the view to mimic an explosion. The number of parts can
     * vary from 1x2 to 3x3. The view is set to invisible and added back for
     * reuse.
     *
     * @param view The view to be animated.
     */
    ExplodeAnimation(View view) {
        this.view = view;
        setExplodeMatrix(MATRIX_3X3);
        interpolator = new AccelerateDecelerateInterpolator();
        duration = DURATION_LONG;
        listener = null;
    }

    public void animate() {
        final LinearLayout explodeLayout = new LinearLayout(view.getContext());
        LinearLayout[] layouts = new LinearLayout[yParts];
        parentView = (ViewGroup) view.getParent();
        explodeLayout.setLayoutParams(view.getLayoutParams());
        explodeLayout.setOrientation(LinearLayout.VERTICAL);
        explodeLayout.setClipChildren(false);

        view.setDrawingCacheEnabled(true);
        Bitmap viewBmp = view.getDrawingCache(true);
        int totalParts = xParts * yParts, bmpWidth = viewBmp.getWidth()
                / xParts, bmpHeight = viewBmp.getHeight() / yParts, widthCount = 0, heightCount = 0, middleXPart = (xParts - 1) / 2;
        int[] translation;
        ImageView[] imageViews = new ImageView[totalParts];

        for (int i = 0; i < totalParts; i++) {
            int translateX = 0, translateY = 0;
            if (i % xParts == 0) {
                if (i != 0) {
                    heightCount++;
                }
                widthCount = 0;
                layouts[heightCount] = new LinearLayout(view.getContext());
                layouts[heightCount].setClipChildren(false);
                translation = sideTranslation(
                        heightCount, bmpWidth, bmpHeight,
                        yParts);
                translateX = translation[0];
                translateY = translation[1];
            } else if (i % xParts == xParts - 1) {
                translation = sideTranslation(
                        heightCount, -bmpWidth,
                        bmpHeight, yParts);
                translateX = translation[0];
                translateY = translation[1];
            } else {
                if (widthCount == middleXPart) {
                    if (heightCount == 0) {
                        translateX = 0;
                        if (yParts != 1) {
                            translateY = -bmpHeight;
                        }
                    } else if (heightCount == yParts - 1) {
                        translateX = 0;
                        translateY = bmpHeight;
                    }
                }
            }
            if (xParts == 1) {
                translation = sideTranslation(
                        heightCount, 0, bmpHeight,
                        yParts);
                translateX = translation[0];
                translateY = translation[1];
            }

            imageViews[i] = new ImageView(view.getContext());
            imageViews[i]
                    .setImageBitmap(
                            Bitmap.createBitmap(
                                    viewBmp, bmpWidth
                                            * widthCount, bmpHeight * heightCount, bmpWidth,
                                    bmpHeight));
            imageViews[i].animate().translationXBy(translateX)
                    .translationYBy(translateY).alpha(0)
                    .setInterpolator(interpolator).setDuration(duration);

            layouts[heightCount].addView(imageViews[i]);
            widthCount++;
        }

        monitor(imageViews);

        for (int i = 0; i < yParts; i++) {
            explodeLayout.addView(layouts[i]);
        }
        final int positionView = parentView.indexOfChild(view);
        parentView.removeView(view);
        parentView.addView(explodeLayout, positionView);

        ViewGroup rootView = (ViewGroup) explodeLayout.getRootView();
        while (!parentView.equals(rootView)) {
            parentView.setClipChildren(false);
            parentView = (ViewGroup) parentView.getParent();
        }
        rootView.setClipChildren(false);

        imageViews[0].animate().setListener(
                new AnimatorListenerAdapter() {

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        parentView = (ViewGroup) explodeLayout.getParent();
                        view.setLayoutParams(explodeLayout.getLayoutParams());
                        view.setVisibility(View.INVISIBLE);
                        parentView.removeView(explodeLayout);
                        parentView.addView(view, positionView);
                        if (getListener() != null) {
                            getListener().onAnimationEnd(ExplodeAnimation.this);
                        }
                    }
                });
    }

    /**
     * This is the line of interest below. It doesn't matter how you create your animation
     * as long as those views which want to be animated are added to Quicksand as a set.
     * <p/>
     * vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
     * vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
     * vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
     * vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
     * vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
     * vvvvvvvvvvvvvvvvvvvv
     * vvvvvvvvvv
     * vvvv
     * vv
     * v
     */
    private void monitor(ImageView[] imageViews) {
        Quicksand.trap(KEY_ANIMATION_SET, imageViews);
    }

    private int[] sideTranslation(int heightCount, int bmpWidth, int bmpHeight, int yParts) {
        int[] translation = new int[2];
        int middleYPart = (yParts - 1) / 2;
        if (heightCount == 0) {
            translation[0] = -bmpWidth;
            translation[1] = -bmpHeight;
        } else if (heightCount == yParts - 1) {
            translation[0] = -bmpWidth;
            translation[1] = bmpHeight;
        }

        if (yParts % 2 != 0) {
            if (heightCount == middleYPart) {
                translation[0] = -bmpWidth;
                translation[1] = 0;
            }
        }
        return translation;
    }

    /**
     * @param matrix The matrix that determines the number of X and Y parts to set.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    private ExplodeAnimation setExplodeMatrix(int matrix) {
        xParts = matrix / 10;
        yParts = matrix % 10;
        return this;
    }

    private AnimationListener getListener() {
        return listener;
    }

    /**
     * @param listener The listener to set for the end of the animation.
     * @return This object, allowing calls to methods in this class to be
     * chained.
     */
    public ExplodeAnimation setListener(AnimationListener listener) {
        this.listener = listener;
        return this;
    }

}
