package com.blundell.quicksand;

import android.app.Application;

public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Quicksand.createSandTrap(this);
    }
}
