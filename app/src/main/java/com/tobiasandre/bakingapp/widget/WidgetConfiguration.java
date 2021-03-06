package com.tobiasandre.bakingapp.widget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tobiasandre.bakingapp.BakingApp;
import com.tobiasandre.bakingapp.R;
import com.tobiasandre.bakingapp.model.Recipe;
import com.tobiasandre.bakingapp.sync.CommandExec;
import com.tobiasandre.bakingapp.sync.GetRecipesTask;
import com.tobiasandre.bakingapp.ui.adapter.RecipesAdapter;

import java.util.ArrayList;


/**
 * Created by Tobias Andre on 13/09/2017.
 */

public class WidgetConfiguration extends Activity
        implements RecipesAdapter.Callbacks,GetRecipesTask.Listener {

    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    RecyclerView mRecyclerView;

    public WidgetConfiguration() {
        super();
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        getRecipes();
        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.

        setResult(RESULT_CANCELED);

        setContentView(R.layout.widget_configure);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId =
                    extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecipesAdapter adapter = new RecipesAdapter(BakingApp.get().mRecipes,true,this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void open(Recipe recipe, int position) {
        final Context context = WidgetConfiguration.this;
        BakingApp.get().preferencesUtils.setWidgetRecipeId(mAppWidgetId, recipe.getId());

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        BakingAppWidget.updateAppWidget(context, appWidgetManager, mAppWidgetId,recipe);

        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
        setResult(RESULT_OK, resultValue);
        finish();
    }

    private void getRecipes(){
        GetRecipesTask.NotifyTaskCompletedCommand command =
                new GetRecipesTask.NotifyTaskCompletedCommand(this);
        new GetRecipesTask(command).execute();
    }

    @Override
    public void onGetFinished(CommandExec command) {
        if (command instanceof GetRecipesTask.NotifyTaskCompletedCommand) {
            try {
                BakingApp.get().mRecipes = (ArrayList<Recipe>) ((GetRecipesTask.NotifyTaskCompletedCommand) command).getRecipes();
                RecipesAdapter adapter = new RecipesAdapter(BakingApp.get().mRecipes,true,this);
                mRecyclerView.setAdapter(adapter);
            }catch (Exception error){
                System.out.println(error.getMessage());
            }
        }
    }
}