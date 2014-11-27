package com.blundell.quicksand.activitytransition;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Locale;

public class TransitionCountPreferences {

    private static final String NAME = "TransitionCountPreferences";
    private static final String KEY_COUNT = "TransitionCount%s";

    private final SharedPreferences sharedPreferences;

    private TransitionCountPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public static TransitionCountPreferences newInstance() {
        Context context = Quicksand.globalContext;     // TODO coupling between TCprefs & quicksand
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return new TransitionCountPreferences(sharedPreferences);
    }

    public int getCount(int key) {  // a getter? hmm
        return sharedPreferences.getInt(getCountKey(key), 0);
    }

    public void incrementCount(int key) {
        int currentCount = getCount(key);
        sharedPreferences.edit().putInt(getCountKey(key), ++currentCount).apply();
    }

    private String getCountKey(int key) {
        return String.format(Locale.UK, KEY_COUNT, key);
    }
}
