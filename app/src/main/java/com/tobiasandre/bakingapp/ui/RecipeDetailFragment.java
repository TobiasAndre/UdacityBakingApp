package com.tobiasandre.bakingapp.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tobiasandre.bakingapp.R;
import com.tobiasandre.bakingapp.model.Ingredient;
import com.tobiasandre.bakingapp.model.Recipe;
import com.tobiasandre.bakingapp.model.Step;
import com.tobiasandre.bakingapp.ui.adapter.IngredientsAdapter;
import com.tobiasandre.bakingapp.ui.adapter.StepsAdapter;

import java.util.ArrayList;

/**
 * Created by Tobias Andre on 31/08/2017.
 */

public class RecipeDetailFragment extends Fragment implements IngredientsAdapter.Callbacks,
        StepsAdapter.Callbacks{

    private Recipe recipe;
    private RecyclerView mRecyclerIngredients;
    private RecyclerView mRecyclerSteps;
    private IngredientsAdapter mAdapterIngredients;
    private StepsAdapter mAdapterSteps;
    public static final String ARG_STEP = "ARG_STEP";

    public RecipeDetailFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipe = new Recipe();

        if (getArguments().containsKey(RecipeDetail.ARG_RECIPE)) {
            recipe = getArguments().getParcelable(RecipeDetail.ARG_RECIPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        mRecyclerIngredients = (RecyclerView) rootView.findViewById(R.id.recipe_details_ingredients);
        mRecyclerSteps = (RecyclerView) rootView.findViewById(R.id.recipe_details_steps);

        mAdapterIngredients = new IngredientsAdapter(new ArrayList<Ingredient>(), this);
        mAdapterSteps = new StepsAdapter(new ArrayList<Step>(), this);

        LinearLayoutManager managerIngredients = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerIngredients.setLayoutManager(managerIngredients);

        LinearLayoutManager managerSteps = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerSteps.setLayoutManager(managerSteps);

        mAdapterIngredients.add(recipe.getIngredients());
        mAdapterSteps.add(recipe.getSteps());

        mRecyclerIngredients.setAdapter(mAdapterIngredients);
        mRecyclerSteps.setAdapter(mAdapterSteps);

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {
        super.onSaveInstanceState(currentState);
    }


    @Override
    public void open(Step step, int position) {
        if(!step.getVideoURL().isEmpty()) {
            Intent intentPlayer = new Intent(getActivity(),PlayerActivity.class);
            intentPlayer.putExtra(ARG_STEP,step);
            startActivity(intentPlayer);
        }
    }

    @Override
    public void open(Ingredient ingredient, int position) {

    }
}
