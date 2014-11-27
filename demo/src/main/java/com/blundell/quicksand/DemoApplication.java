package com.blundell.quicksand;

import android.app.Application;

import com.blundell.quicksand.activitytransition.Quicksand;

public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Quicksand.startToSink(this);
    }
}
