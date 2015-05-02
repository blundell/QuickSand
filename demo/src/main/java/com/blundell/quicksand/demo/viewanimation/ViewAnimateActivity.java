package com.blundell.quicksand.demo.viewanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.blundell.quicksand.Quicksand;
import com.blundell.quicksand.demo.R;

public class ViewAnimateActivity extends Activity {

    public static final String KEY_ANIM_SHOW_HIDE = "MyUniqueKeyForShowHideViewAnimation";

    private Button animateButton;
    private ImageView sandImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_property);

        animateButton = (Button) findViewById(R.id.button_animate);
        sandImage = (ImageView) findViewById(R.id.image_animate);
        animateButton.setOnClickListener(onClickAnimateButton);
    }

    private final View.OnClickListener onClickAnimateButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (clickedShow()) {

                animateButton
                        .animate()
                        .alpha(0F)
                        .setDuration(500L)
                        .translationY(sandImage.getHeight())
                        .setListener(
                                new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        animateButton.setText(R.string.view_property_hide_button);
                                        animateButton
                                                .animate()
                                                .alpha(1F)
                                                .setDuration(500L);
                                    }
                                });

                sandImage
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

            } else {
                animateButton
                        .animate()
                        .alpha(0F)
                        .translationYBy(-sandImage.getHeight())
                        .setDuration(500L)
                        .setListener(
                                new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        animateButton.setText(R.string.view_property_show_button);
                                        animateButton
                                                .animate()
                                                .alpha(1F)
                                                .setDuration(500L);
                                    }
                                });

                sandImage
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
            }
        }

        private boolean clickedShow() {
            return animateButton.getText().toString().equals(getString(R.string.view_property_show_button));
        }
    };
}
