package com.blundell.quicksand.demo.activitytransition;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;

import com.blundell.quicksand.Quicksand;
import com.blundell.quicksand.demo.R;

import java.util.concurrent.TimeUnit;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class ToHereActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setAllowEnterTransitionOverlap(true);
        Transition transition = getTransition();
        Quicksand.trap(FromHereActivity.KEY_MY_ACTIVITY_TRANSITION, transition);
        getWindow().setEnterTransition(transition);
        setContentView(R.layout.activity_to_here);
    }

    private Transition getTransition() {
        Transition transition = new Explode();
        transition.setDuration(TimeUnit.SECONDS.toMillis(6));
        return transition;
    }

}
