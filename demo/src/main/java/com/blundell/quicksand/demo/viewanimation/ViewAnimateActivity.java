package com.blundell.quicksand.demo.viewanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.blundell.quicksand.Quicksand;
import com.blundell.quicksand.demo.R;

public class ViewAnimateActivity extends Activity {

    public static final String KEY_ANIM_SHOW_HIDE = "MyUniqueKeyForShowHideViewAnimation";

    private Button animateButton;
    private ImageView sandImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_anim);

        animateButton = (Button) findViewById(R.id.button_animate);
        sandImage = (ImageView) findViewById(R.id.image_animate);
        animateButton.setOnClickListener(onClickAnimateButton);

        findViewById(R.id.button_reset_animation_count).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Quicksand.resetTrap(KEY_ANIM_SHOW_HIDE);
                        Toast.makeText(ViewAnimateActivity.this, R.string.notify_reset, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }

    private final View.OnClickListener onClickAnimateButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (clickedShow()) {

                ViewPropertyAnimator propertyAnimator = animateButton
                        .animate()
                        .alpha(0F)
                        .setDuration(500L)
                        .translationY(sandImage.getHeight())
                        .setListener(
                                new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        animateButton.setText(R.string.button_animate_me_hide);
                                        animateButton
                                                .animate()
                                                .alpha(1F)
                                                .setDuration(500L)
                                                .start();
                                    }
                                });

                ViewPropertyAnimator viewPropertyAnimator = sandImage
                        .animate()
                        .alpha(1F)
                        .setDuration(500L)
                        .setListener(
                                new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {
                                        sandImage.setVisibility(View.VISIBLE);
                                    }
                                });
                Quicksand.trap(KEY_ANIM_SHOW_HIDE, animateButton, sandImage);

                propertyAnimator.start();
                viewPropertyAnimator.start();

            } else {
                ViewPropertyAnimator animator = animateButton
                        .animate()
                        .alpha(0F)
                        .translationYBy(-sandImage.getHeight())
                        .setDuration(500L)
                        .setListener(
                                new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        animateButton.setText(R.string.button_animate_me_show);
                                        animateButton
                                                .animate()
                                                .alpha(1F)
                                                .setDuration(500L)
                                                .start();
                                    }
                                });

                ViewPropertyAnimator viewPropertyAnimator = sandImage
                        .animate()
                        .alpha(0F)
                        .setDuration(500L)
                        .setListener(
                                new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        sandImage.setVisibility(View.INVISIBLE);
                                    }
                                });
                Quicksand.trap(KEY_ANIM_SHOW_HIDE, animateButton, sandImage);

                animator.start();
                viewPropertyAnimator.start();

            }
        }

        private boolean clickedShow() {
            return animateButton.getText().toString().equals(getString(R.string.button_animate_me_show));
        }
    };
}
