package com.blundell.quicksand.activitytransition;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;

import com.blundell.quicksand.R;

public class ToHereActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setAllowEnterTransitionOverlap(true);
        Transition transition = new Explode();
        Quicksand.manage(R.id.car_transition, transition);
        getWindow().setEnterTransition(transition);
        setContentView(R.layout.activity_to_here);
    }

}
