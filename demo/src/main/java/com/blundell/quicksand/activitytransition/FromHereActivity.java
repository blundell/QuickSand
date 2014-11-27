package com.blundell.quicksand.activitytransition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;
import android.view.View;

import com.blundell.quicksand.R;

public class FromHereActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Transition transition = new Explode();
        Quicksand.manage(R.id.car_transition, transition);
        getWindow().setExitTransition(transition);
        setContentView(R.layout.activity_from_here);

        findViewById(R.id.from_here_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ToHereActivity.class);
                startActivity(intent);
            }
        });
    }

}
