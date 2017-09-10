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

    TextView quantity;
    TextView name;
    TextView unit;

    public IngredientItemView(Context context) {
        super(context);

        View view = inflate(getContext(), R.layout.item_ingredient,this);
        quantity = (TextView)view.findViewById(R.id.quantity);
        name = (TextView)view.findViewById(R.id.item_name);
        unit = (TextView)view.findViewById(R.id.unit);
    }

    public View bind(Ingredient ingredient){
        quantity.setText(String.valueOf(ingredient.getQuantity()));
        name.setText(ingredient.getIngredient());
        unit.setText(ingredient.getMeasure());
        return this;
    }
}
