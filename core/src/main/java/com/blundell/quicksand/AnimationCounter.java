package com.blundell.quicksand;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Locale;

class AnimationCounter {

    private static final String NAME = "AnimationCountPreferences";
    private static final String KEY_COUNT = "AnimationCount%s";
    private static final String KEY_DURATION = "Duration%s";

    private final SharedPreferences sharedPreferences;

    public static AnimationCounter newInstance(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return new AnimationCounter(sharedPreferences);
    }

    private AnimationCounter(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public long getCount(String key) {  // a getter? hmm
        return sharedPreferences.getLong(getCountKey(key), 1L);
    }

    public void incrementCount(String key) {
        long currentCount = getCount(key);
        sharedPreferences.edit().putLong(getCountKey(key), ++currentCount).apply();
    }

    private String getCountKey(String key) {
        return String.format(Locale.UK, KEY_COUNT, key);
    }

    public void reset(String key) {
//        sharedPreferences.edit()
//                .remove(getCountKey(key))
//                .remove(getDurationKey(key))
//                .apply();
        sharedPreferences.edit()
                .clear() // TODO LOLZ - bug, in that the duration key now has an id we do not know appended to the end
                .apply();
    }

    public long getDuration(String key) {
        return sharedPreferences.getLong(getDurationKey(key), 0L);
    }

    public void saveDuration(String key, long duration) {
        sharedPreferences.edit()
                .putLong(getDurationKey(key), duration)
                .apply();
    }

    private String getDurationKey(String key) {
        return String.format(Locale.UK, KEY_DURATION, key);
    }
}
