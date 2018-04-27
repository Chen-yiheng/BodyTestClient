package com.perry.nightmode;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Administrator on 2017/9/22 0022.
 */

public class DayNightHelp {
    public static final int DAY = 1;
    public static final int NIGHT = 0;
    public final String MODE = "dayornight";

    private SharedPreferences preferences;

    public DayNightHelp(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setMode(int mode) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(MODE, mode);
        editor.apply();
    }

    public boolean isDay() {
        int mode = preferences.getInt(MODE, 2);
        if (mode == NIGHT) {
            return false;
        }
        return true;
    }

    public boolean isNight() {
        int mode = preferences.getInt(MODE, 2);
        if (mode == NIGHT) {
            return true;
        }
        return false;
    }

}
