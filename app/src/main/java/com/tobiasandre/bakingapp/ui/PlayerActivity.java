package com.tobiasandre.bakingapp.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tobiasandre.bakingapp.R;
import com.tobiasandre.bakingapp.model.Step;

public class PlayerActivity extends AppCompatActivity {

    Step step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Bundle arguments = new Bundle();
        arguments.putParcelable(RecipeDetailFragment.ARG_STEP,
                getIntent().getParcelableExtra(RecipeDetailFragment.ARG_STEP));

        step = arguments.getParcelable(RecipeDetailFragment.ARG_STEP);

        Fragment fragment = new RecipeStepDetailFragment();
        fragment.setArguments(arguments);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

    }
}
