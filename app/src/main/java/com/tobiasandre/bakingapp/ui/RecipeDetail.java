package com.tobiasandre.bakingapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import com.tobiasandre.bakingapp.R;


public class RecipeDetail extends AppCompatActivity {

    public static final String ARG_RECIPE = "ARG_RECIPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(ARG_RECIPE,
                    getIntent().getParcelableExtra(ARG_RECIPE));

            Fragment fragment = new RecipeDetailFragment();
            fragment.setArguments(arguments);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragmentFrame, fragment).commit();
        }
    }
}
