package com.blundell.quicksand.demo;

import android.app.Application;

import com.blundell.quicksand.Quicksand;
import com.blundell.quicksand.demo.activitytransition.FromHereActivity;
import com.blundell.quicksand.demo.viewanimation.ViewAnimateActivity;
import com.blundell.quicksand.viscosity.LinearChangeViscosity;
import com.blundell.quicksand.viscosity.Viscosity;
import com.novoda.notils.logger.simple.Log;

import java.util.HashMap;
import java.util.Map;

public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.setShowLogs(true);
        Map<String, Viscosity> viscosities = new HashMap<>();
        viscosities.put(FromHereActivity.KEY_MY_ACTIVITY_TRANSITION, new LinearChangeViscosity(30));
        viscosities.put(ViewAnimateActivity.KEY_ANIM_SHOW_HIDE, LinearChangeViscosity.defaultInstance());

        Quicksand.create(this, viscosities);
    }
}
