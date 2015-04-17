package com.blundell.quicksand.demo.simpleanimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewPropertyAnimator;

import com.blundell.quicksand.Quicksand;
import com.blundell.quicksand.demo.R;

public class SimpleAnimationActivity extends Activity {

    public static final String KEY_SIMPLE_ANIMATE_TEXT = "SimpleAnimateText";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_anim);

        ViewPropertyAnimator animateText = findViewById(R.id.simple_anim_text)
                .animate()
                .setDuration(5000)
                .scaleXBy(.5f);
        Quicksand.trap(KEY_SIMPLE_ANIMATE_TEXT, animateText);
        animateText.start();
    }
}
