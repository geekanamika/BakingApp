package com.example.android.bakingapp;

import android.app.Application;
import android.content.Context;

import timber.log.Timber;

/**
 * Created by Anamika Tripathi on 12/10/18.
 */
public class App extends Application {

    private static App mInstance;

    public static Context getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = (App) getApplicationContext();
        if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());
    }
}
