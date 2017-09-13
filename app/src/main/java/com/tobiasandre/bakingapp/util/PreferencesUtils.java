package com.tobiasandre.bakingapp.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Tobias Andre on 02/09/2017.
 */

public class PreferencesUtils {

    private  SharedPreferences imgPrefs;
    public static final String IMG_CACHE_PREFS_NAME = "image-cache";
    public static final String IMAGE_CACHE_PREFS_NAME = "image-cache-prefs";

    private  SharedPreferences appPrefs;
    public static final String PREFS_NAME = "prefs";

    public static final String APP_PREFS_NAME = "app-prefs";

    private static final String WIDGET_PREFIX = "WIDGET_";

    private  SharedPreferences widgetPrefs;
    public static final String WIDGET_PREFS_NAME = "widget-prefs";


    public PreferencesUtils(Context context){
        appPrefs = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        imgPrefs = context.getSharedPreferences(IMG_CACHE_PREFS_NAME,Context.MODE_PRIVATE);
        widgetPrefs = context.getSharedPreferences(WIDGET_PREFS_NAME,Context.MODE_PRIVATE);
    }

    public PreferencesUtils(Context context,String prefsName){
        switch (prefsName){
            case IMAGE_CACHE_PREFS_NAME:
                imgPrefs = context.getSharedPreferences(IMAGE_CACHE_PREFS_NAME,Context.MODE_PRIVATE);
                break;
            case APP_PREFS_NAME:
                appPrefs = context.getSharedPreferences(APP_PREFS_NAME,Context.MODE_PRIVATE);
                break;
            case WIDGET_PREFS_NAME:
                widgetPrefs = context.getSharedPreferences(WIDGET_PREFS_NAME,Context.MODE_PRIVATE);
                break;
        }
    }

    private String getString(String key,SharedPreferences preferences){
        return preferences.getString(key,"");
    }

    private void putString(String key,String value,SharedPreferences preferences){
        preferences.edit().putString(key,value).apply();
    }

    public String getRecipeImage(String recipeName){
        return getString(recipeName,imgPrefs);
    }

    public void saveRecipeImage(String recipeName,String imageUrl){
        putString(recipeName,imageUrl,imgPrefs);
    }

    private void putInt(String key,int value,SharedPreferences preferences){
        preferences.edit().putInt(key,value).apply();
    }

    private int getInt(String key,SharedPreferences preferences){
        return preferences.getInt(key,-1);
    }

    public void setWidgetRecipeId(int widgetId,int recipeId){
        putInt(WIDGET_PREFIX+widgetId,recipeId,widgetPrefs);
    }

    public int getWidgetRecipeId(int widgetId){
        return getInt(WIDGET_PREFIX+widgetId,widgetPrefs);
    }

}
