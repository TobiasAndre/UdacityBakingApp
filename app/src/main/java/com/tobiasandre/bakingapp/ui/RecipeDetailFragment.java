package com.tobiasandre.bakingapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;
import com.tobiasandre.bakingapp.R;
import com.tobiasandre.bakingapp.model.Ingredient;
import com.tobiasandre.bakingapp.model.Recipe;
import com.tobiasandre.bakingapp.model.Step;
import com.tobiasandre.bakingapp.ui.views.IngredientItemView;
import com.tobiasandre.bakingapp.ui.views.StepItemView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Tobias Andre on 31/08/2017.
 */

public class RecipeDetailFragment extends Fragment implements StepItemView.Callbacks {

    private Recipe mRecipe;
    public RecipeDetailFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecipe = new Recipe();

        if (getArguments().containsKey(RecipeDetail.ARG_RECIPE)) {
            mRecipe = getArguments().getParcelable(RecipeDetail.ARG_RECIPE);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Activity activity = getActivity();
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout)
                activity.findViewById(R.id.toolbar);
        if (appBarLayout != null && activity instanceof RecipeDetail) {
            appBarLayout.setTitle(mRecipe.getName());
        }

        ImageView recipeBackdrop = ((ImageView) activity.findViewById(R.id.recipe_backdrop));
        if (recipeBackdrop != null) {
            Picasso.with(activity)
                    .load(mRecipe.getImage())
                    .config(Bitmap.Config.RGB_565)
                    .into(recipeBackdrop);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        LinearLayout mLinearLayoutIngredients = (LinearLayout)rootView.findViewById(R.id.ingredient_layout_list);
        if(mLinearLayoutIngredients!=null){
            for(Ingredient ingredient : mRecipe.getIngredients()){
                mLinearLayoutIngredients.addView(new IngredientItemView(getContext()).bind(ingredient));
            }
        }

        LinearLayout mLinearLayoutSteps = (LinearLayout)rootView.findViewById(R.id.steps_layout_list);
        if(mLinearLayoutSteps!=null){
            int index = 0;
            for(Step step : mRecipe.getSteps()){
                mLinearLayoutSteps.addView(new StepItemView(getContext(),this).bind(mRecipe.getSteps(),index));
                index++;
            }
        }
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {
        super.onSaveInstanceState(currentState);
    }

    @Override
    public void open(List<Step> steps,int position) {
        showStep(position);
    }

    private void showStep(int position){
        try {
            Intent intentPlayer = new Intent(getActivity(), PlayerActivity.class);

            intentPlayer.putExtra(PlayerActivity.ARG_STEP, mRecipe);
            intentPlayer.putExtra(PlayerActivity.ARG_POSITION, position);

            startActivity(intentPlayer);
        }catch (Exception error){
            System.out.println(error.getMessage());
        }
    }
}
