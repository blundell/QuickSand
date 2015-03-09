package com.blundell.quicksand.demo;

import android.app.Application;

import com.blundell.quicksand.Quicksand;

public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Quicksand.createSandTrap(this);
    }
}
