package com.tobiasandre.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.google.gson.Gson;
import com.tobiasandre.bakingapp.BakingApp;
import com.tobiasandre.bakingapp.R;
import com.tobiasandre.bakingapp.model.Recipe;
import com.tobiasandre.bakingapp.util.PreferencesUtils;

/**
 * Created by Tobias Andre on 11/09/2017.
 */

public class BakingAppWidget extends AppWidgetProvider {

    Recipe mRecipe;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        PreferencesUtils widgetPreferencesUtils =
                new PreferencesUtils(context, PreferencesUtils.WIDGET_PREFS_NAME);
        int recipeId;

        mRecipe = null;

        for (int appWidgetId : appWidgetIds) {
            recipeId = widgetPreferencesUtils.getWidgetRecipeId(appWidgetId);
            mRecipe = BakingApp.get().mRecipes.get(1);

            updateAppWidget(context, appWidgetManager, appWidgetId,mRecipe);
        }
    }

    @Override public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.

    }

    @Override public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Recipe recipe) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_recipe);

        views.setTextViewText(R.id.widget_text, recipe.getName());
        Intent intent = new Intent(context, ListWidgetService.class);
        intent.putExtra(ListWidgetService.KEY_RECIPE, new Gson().toJson(recipe));
        views.setRemoteAdapter(R.id.widget_list, intent);
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }
}
