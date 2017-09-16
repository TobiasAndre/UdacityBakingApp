package com.tobiasandre.bakingapp.ui.views;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tobiasandre.bakingapp.R;
import com.tobiasandre.bakingapp.model.Ingredient;


/**
 * Created by Tobias Andre on 07/09/2017.
 */

public class IngredientItemView extends LinearLayout {

    private TextView mQuantity;
    private TextView mName;
    private TextView mUnit;

    public IngredientItemView(Context context) {
        super(context);

        View view = inflate(getContext(), R.layout.item_ingredient,this);
        mQuantity = (TextView)view.findViewById(R.id.quantity);
        mName = (TextView)view.findViewById(R.id.item_name);
        mUnit = (TextView)view.findViewById(R.id.unit);
    }

    public View bind(Ingredient ingredient){
        mQuantity.setText(String.valueOf(ingredient.getQuantity()));
        mName.setText(ingredient.getIngredient());
        mUnit.setText(ingredient.getMeasure());
        return this;
    }
}
