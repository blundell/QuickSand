package com.blundell.quicksand.demo.activitytransition;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;

import com.blundell.quicksand.Quicksand;
import com.blundell.quicksand.demo.R;

public class ToHereActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setAllowEnterTransitionOverlap(true);
        Transition transition = new Explode();
        Quicksand.trap(FromHereActivity.KEY_MY_ACTIVITY_TRANSITION, transition);
        getWindow().setEnterTransition(transition);
        setContentView(R.layout.activity_to_here);
    }

}
