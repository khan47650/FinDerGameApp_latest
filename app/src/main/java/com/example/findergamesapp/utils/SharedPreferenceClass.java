package com.example.findergamesapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceClass {
    private static final String PREF_NAME = "level_prefs";
    private static final String KEY_CURRENT_LEVEL = "current_level";

    private SharedPreferences sharedPreferences;


    public SharedPreferenceClass(Context context){
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }


    public void setCurrentLevel(int level) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_CURRENT_LEVEL, level);
        editor.apply();
    }

    public int getCurrentLevel(){
        return sharedPreferences.getInt(KEY_CURRENT_LEVEL,1);
    }
}
