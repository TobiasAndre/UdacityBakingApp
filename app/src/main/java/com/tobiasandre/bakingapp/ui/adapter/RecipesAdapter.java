package com.tobiasandre.bakingapp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tobiasandre.bakingapp.R;
import com.tobiasandre.bakingapp.model.Recipe;
import com.tobiasandre.bakingapp.ui.RecipeDetail;
import com.tobiasandre.bakingapp.util.ImageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Tobias Andre on 29/08/2017.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    private final static String TAG = RecipesAdapter.class.getSimpleName();
    private final ArrayList<Recipe> mRecipes;
    private final Callbacks mCallbacks;

    public interface Callbacks {
        void open(Recipe recipe, int position);
    }

    public RecipesAdapter(ArrayList<Recipe> recipes, Callbacks callbacks) {
        mRecipes = recipes;
        this.mCallbacks = callbacks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_list_item, parent, false);
        final Context context = view.getContext();


        return new ViewHolder(view);
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.cleanUp();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Recipe recipe = mRecipes.get(position);
        final Context mContext = holder.mView.getContext();
        holder.tvrecipeName.setText(recipe.getName());
        holder.tvStepCount.setText(""+recipe.getSteps().size());
        holder.tvServingCount.setText(""+recipe.getServings());

        if (TextUtils.isEmpty(recipe.getImage()) == false) {
            Picasso.with(mContext)
                    .load(recipe.getImage())
                    .into(holder.imgRecipe);
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDetail = new Intent(mContext, RecipeDetail.class);
                intentDetail.putExtra(RecipeDetail.ARG_RECIPE, recipe);
                mContext.startActivity(intentDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        TextView tvServingCount, tvStepCount;
        TextView tvrecipeName;
        ImageView imgRecipe;

        public ViewHolder(View view){
            super(view);
            tvrecipeName = (TextView)view.findViewById(R.id.tv_recipe_name);
            tvServingCount = (TextView)view.findViewById(R.id.tv_number_serves);
            tvStepCount = (TextView)view.findViewById(R.id.tv_number_steps);
            imgRecipe = (ImageView)view.findViewById(R.id.img_recipe);
            mView = view;
        }

        public void cleanUp() {
            final Context context = mView.getContext();
            imgRecipe.setVisibility(View.GONE);
            tvrecipeName.setVisibility(View.INVISIBLE);
            tvServingCount.setVisibility(View.GONE);
            tvStepCount.setVisibility(View.GONE);
        }
    }

    public void add(List<Recipe> recipes) {
        mRecipes.clear();
        mRecipes.addAll(recipes);
        notifyDataSetChanged();
    }

}
