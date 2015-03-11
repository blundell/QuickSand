package com.blundell.quicksand.demo;

import android.app.Application;

import com.blundell.quicksand.Quicksand;
import com.blundell.quicksand.demo.activitytransition.FromHereActivity;
import com.blundell.quicksand.demo.viewanimation.ViewAnimateActivity;
import com.blundell.quicksand.viscosity.LinearChangeViscosity;
import com.blundell.quicksand.viscosity.Viscosity;

import java.util.HashMap;
import java.util.Map;

public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Map<String, Viscosity> viscosities = new HashMap<>();
        viscosities.put(FromHereActivity.KEY_MY_ACTIVITY_TRANSITION, LinearChangeViscosity.defaultInstance());
        viscosities.put(ViewAnimateActivity.KEY_ANIM_SHOW_HIDE, LinearChangeViscosity.defaultInstance());

        Quicksand.create(this, viscosities);
    }
}
