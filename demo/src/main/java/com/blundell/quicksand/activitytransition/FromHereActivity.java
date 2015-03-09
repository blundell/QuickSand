package com.blundell.quicksand.activitytransition;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;
import android.view.View;

import com.blundell.quicksand.Quicksand;
import com.blundell.quicksand.R;

public class FromHereActivity extends Activity {

    public static final String KEY_MY_ACTIVITY_TRANSITION = "MyActivityTransition";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Transition transition = new Explode();
        Quicksand.trap(KEY_MY_ACTIVITY_TRANSITION, transition);
        getWindow().setExitTransition(transition);
        setContentView(R.layout.activity_from_here);

        findViewById(R.id.from_here_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ToHereActivity.class);
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(FromHereActivity.this).toBundle();
                startActivity(intent, bundle);
            }
        });
    }

}
