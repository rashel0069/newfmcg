package com.appshat.fmcgapp;

import android.content.Context;
import android.content.SharedPreferences;

public class Helper {
    Context context;
    private static final String PREFS_NAME = "fcm";
    private static final String name = "asdasd";
    private static final String bangla = "fdsdf";


    public static boolean setPreference(String key, String value) {
        SharedPreferences settings = Fmcg.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public static String getName() {
        return name;
    }

    public static String getPreference(String key) {
        SharedPreferences settings = Fmcg.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, "none");
    }

    public static boolean setPreferenceInt(String key, int value) {
        SharedPreferences settings = Fmcg.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    public static int getPreferenceInt(String key) {
        SharedPreferences settings = Fmcg.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getInt(key, 0);
    }

    public static boolean setPreferenceLong(String key, Long value) {
        SharedPreferences settings = Fmcg.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    public static Long getPreferenceLong(String key) {
        SharedPreferences settings = Fmcg.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getLong(key,0L);
    }

    public static boolean setPreferenceFloat(String key, Float value) {
        SharedPreferences settings = Fmcg.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    public static Float getPreferenceFloat(String key) {
        SharedPreferences settings = Fmcg.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getFloat(key,0f);
    }


    public static boolean setPreferenceBool(String key, Boolean value) {
        SharedPreferences settings = Fmcg.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public static boolean getPreferenceBool(String key) {
        SharedPreferences settings = Fmcg.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getBoolean(key, false);
    }

    //==============================setting Language============================================

    public static void setBangla(Boolean status){
        setPreferenceBool(bangla,status);

    }



    public static boolean getBangla(){
        return getPreferenceBool(bangla);
    }
}


