package com.appshat.kherokhata.OldAcrivity;

import android.app.Application;
import android.content.Context;

public class Fmcg extends Application {
    public static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }


}
