package com.tobiasandre.bakingapp.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tobiasandre.bakingapp.R;
import com.tobiasandre.bakingapp.model.Recipe;


public class PlayerActivity extends AppCompatActivity {

    public static final String ARG_STEP = "ARG_STEP";
    public static final String ARG_POSITION = "ARG_POSITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Bundle arguments = new Bundle();
        arguments.putParcelable(ARG_STEP,
                getIntent().getParcelableExtra(ARG_STEP));
        arguments.putInt(ARG_POSITION,getIntent().getIntExtra(ARG_POSITION,0));


        Fragment fragment = new RecipeStepDetailFragment();
        fragment.setArguments(arguments);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

    }
}
