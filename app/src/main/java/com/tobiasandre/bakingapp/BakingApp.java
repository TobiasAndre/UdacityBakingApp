package com.tobiasandre.bakingapp;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.tobiasandre.bakingapp.model.Recipe;
import com.tobiasandre.bakingapp.util.PreferencesUtils;

import java.util.ArrayList;

import okhttp3.OkHttpClient;

/**
 * Created by Tobias Andre on 02/09/2017.
 */

public class BakingApp extends Application {

    private static BakingApp Instance;
    public ArrayList<Recipe> mRecipes;
    public OkHttpClient clientHttp = new OkHttpClient();
    public PreferencesUtils preferencesUtils;

    public static BakingApp get() {
        return Instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mRecipes = new ArrayList<>();
        Instance = this;
        Stetho.initializeWithDefaults(this);
        clientHttp = new OkHttpClient.Builder().addInterceptor(new StethoInterceptor()).build();
        preferencesUtils = new PreferencesUtils(this);
    }
}
