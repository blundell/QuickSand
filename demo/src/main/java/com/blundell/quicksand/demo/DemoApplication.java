package com.blundell.quicksand.demo;

import android.app.Application;

import com.blundell.quicksand.Quicksand;
import com.blundell.quicksand.demo.activitytransition.FromHereActivity;
import com.blundell.quicksand.viscosity.NoChangeViscosity;
import com.blundell.quicksand.viscosity.Viscosity;

import java.util.HashMap;
import java.util.Map;

public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Map<String, Viscosity> viscosities = new HashMap<>();
        viscosities.put(FromHereActivity.KEY_MY_ACTIVITY_TRANSITION, new NoChangeViscosity());

        Quicksand.create(this, viscosities);
    }
}
