package com.tobiasandre.bakingapp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tobiasandre.bakingapp.R;
import com.tobiasandre.bakingapp.model.Ingredient;
import com.tobiasandre.bakingapp.ui.RecipeDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tobias Andre on 31/08/2017.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    private final static String TAG = RecipesAdapter.class.getSimpleName();

    private final ArrayList<Ingredient> mIngredients;
    private final Callbacks mCallbacks;


    public interface Callbacks {
        void open(Ingredient ingredient, int position);
    }

    public IngredientsAdapter(ArrayList<Ingredient> ingredients, Callbacks callbacks) {
        mIngredients = ingredients;
        this.mCallbacks = callbacks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredients_list, parent, false);
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
        final Ingredient ingredient = mIngredients.get(position);
        final Context mContext = holder.mView.getContext();

        holder.tvIngredientName.setText("* "+ingredient.getIngredient());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mIngredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvIngredientName;

        public ViewHolder(View view){
            super(view);
            tvIngredientName = (TextView)view.findViewById(R.id.tv_ingredient_name);
            mView = view;
        }

        public void cleanUp() {
            final Context context = mView.getContext();
            tvIngredientName.setText("");
        }
    }

    public void add(List<Ingredient> ingredientList) {
        mIngredients.clear();
        mIngredients.addAll(ingredientList);
        notifyDataSetChanged();
    }
}
