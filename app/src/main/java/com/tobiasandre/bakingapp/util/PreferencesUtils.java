package com.tobiasandre.bakingapp.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Tobias Andre on 02/09/2017.
 */

public class PreferencesUtils {

    private  SharedPreferences imgPrefs;
    public static final String IMG_CACHE_PREFS_NAME = "image-cache";

    private  SharedPreferences appPrefs;
    public static final String PREFS_NAME = "prefs";

    private static final String WIDGET_PREFIX = "WIDGET_";

    private  SharedPreferences widgetPrefs;
    public static final String WIDGET_PREFS_NAME = "widget-prefs";

    public PreferencesUtils(Context context){
        appPrefs = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        imgPrefs = context.getSharedPreferences(IMG_CACHE_PREFS_NAME,Context.MODE_PRIVATE);
        widgetPrefs = context.getSharedPreferences(WIDGET_PREFS_NAME,Context.MODE_PRIVATE);
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

    public void setWidgetRecipeId(int widgetId,int recipeId){
        putInt(WIDGET_PREFIX+widgetId,recipeId,widgetPrefs);
    }

}
