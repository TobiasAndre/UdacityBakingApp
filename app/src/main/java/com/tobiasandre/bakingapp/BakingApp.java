package com.tobiasandre.bakingapp;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.tobiasandre.bakingapp.util.PreferencesUtils;

import okhttp3.OkHttpClient;

/**
 * Created by Tobias Andre on 02/09/2017.
 */

public class BakingApp extends Application {

    private static BakingApp INSTANCE;

    public OkHttpClient client = new OkHttpClient();
    public PreferencesUtils preferencesUtils;

    public static BakingApp get() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;
        Stetho.initializeWithDefaults(this);
        client = new OkHttpClient.Builder().addInterceptor(new StethoInterceptor()).build();
        preferencesUtils = new PreferencesUtils(this);
    }
}
