package com.tobiasandre.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;
import com.tobiasandre.bakingapp.R;
import com.tobiasandre.bakingapp.model.Ingredient;
import com.tobiasandre.bakingapp.model.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tobias Andre on 13/09/2017.
 */

public class ListWidgetService extends RemoteViewsService  {

    public static final String KEY_RECIPE = "INGREDIENTS";

    @Override public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewFactory(getApplicationContext(),intent);
    }

    class RemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {

        private final Context mContext;
        private final Intent mIntent;
        private List<Ingredient> mIngredients = new ArrayList<>();

        public RemoteViewFactory(Context context, Intent intent) {
            this.mContext = context;
            this.mIntent = intent;
        }

        @Override public void onCreate() {
            Recipe recipe = new Gson().fromJson(mIntent.getStringExtra(KEY_RECIPE), Recipe.class);
            mIngredients = recipe.getIngredients();
        }

        @Override public void onDataSetChanged() {
        }

        @Override public void onDestroy() {
        }

        @Override public int getCount() {
            return mIngredients.size();
        }

        @Override public RemoteViews getViewAt(int position) {

            Ingredient ingredient = mIngredients.get(position);

            RemoteViews views =
                    new RemoteViews(mContext.getPackageName(), R.layout.widget_recipe_ingredient);

            views.setTextViewText(R.id.recipe_ingredient_quantity, String.valueOf(ingredient.getQuantity()));
            views.setTextViewText(R.id.unit, ingredient.getMeasure());
            views.setTextViewText(R.id.recipe_ingredient_name, ingredient.getIngredient());

            return views;
        }

        @Override public RemoteViews getLoadingView() {
            return null;
        }

        @Override public int getViewTypeCount() {
            return 1;
        }

        @Override public long getItemId(int position) {
            return position;
        }

        @Override public boolean hasStableIds() {
            return true;
        }
    }


}
