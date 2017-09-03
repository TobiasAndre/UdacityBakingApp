package com.tobiasandre.bakingapp.sync;

import com.tobiasandre.bakingapp.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Tobias Andre on 29/08/2017.
 */

public interface RecipesService {

    @GET("baking.json")
    Call<List<Recipe>> discoverRecipes();
}
