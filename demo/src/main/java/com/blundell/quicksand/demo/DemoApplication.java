package com.blundell.quicksand.demo;

import android.app.Application;

import com.blundell.quicksand.Quicksand;
import com.blundell.quicksand.demo.activitytransition.FromHereActivity;
import com.blundell.quicksand.demo.amazeanimation.ExplodeAnimation;
import com.blundell.quicksand.demo.simpleanimation.SimpleAnimationActivity;
import com.blundell.quicksand.demo.viewanimation.ViewAnimateActivity;
import com.blundell.quicksand.viscosity.LinearChangeViscosityInterpolator;
import com.blundell.quicksand.viscosity.ViscosityInterpolator;
import com.novoda.notils.logger.simple.Log;

import java.util.HashMap;
import java.util.Map;

public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.setShowLogs(true);

        // Here we decide how we want each Animation's duration to degrade as it is viewed more and more
        Map<String, ViscosityInterpolator> viscosities = new HashMap<>();
        viscosities.put(FromHereActivity.KEY_MY_ACTIVITY_TRANSITION, LinearChangeViscosityInterpolator.defaultInstance());
        viscosities.put(ViewAnimateActivity.KEY_ANIM_SHOW_HIDE, LinearChangeViscosityInterpolator.defaultInstance());
        viscosities.put(ExplodeAnimation.KEY_ANIMATION_SET, LinearChangeViscosityInterpolator.defaultInstance());
        viscosities.put(SimpleAnimationActivity.KEY_SIMPLE_ANIMATE_TEXT, LinearChangeViscosityInterpolator.defaultInstance());

        Quicksand.create(this, viscosities);
    }
}
