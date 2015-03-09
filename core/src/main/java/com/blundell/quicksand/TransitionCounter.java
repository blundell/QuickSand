package com.blundell.quicksand;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Locale;

class TransitionCounter {

    private static final String NAME = "TransitionCountPreferences";
    private static final String KEY_COUNT = "TransitionCount%s";

    private final SharedPreferences sharedPreferences;

    private TransitionCounter(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public static TransitionCounter newInstance(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return new TransitionCounter(sharedPreferences);
    }

    public long getCount(String key) {  // a getter? hmm
        return sharedPreferences.getLong(getCountKey(key), 0L);
    }

    public void incrementCount(String key) {
        long currentCount = getCount(key);
        sharedPreferences.edit().putLong(getCountKey(key), ++currentCount).apply();
    }

    private String getCountKey(String key) {
        return String.format(Locale.UK, KEY_COUNT, key);
    }

    public void resetCount(String key) {
        sharedPreferences.edit().remove(key).apply();
    }
}
