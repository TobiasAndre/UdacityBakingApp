package com.tobiasandre.bakingapp;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.tobiasandre.bakingapp.data.RecipeFakeData;
import com.tobiasandre.bakingapp.matchers.RecyclerViewMatcher;
import com.tobiasandre.bakingapp.model.Recipe;
import com.tobiasandre.bakingapp.ui.RecipeDetail;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;


/**
 * Created by Tobias Andre on 10/09/2017.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ActivityTests {
    @Rule
    public IntentsTestRule<MainActivity> activityTestRule =
            new IntentsTestRule<>(MainActivity.class, true, false);

    private List<Recipe> RECIPE_LIST;


    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Before
    public void setUp() throws Throwable {

        activityTestRule.launchActivity(new Intent());

        RECIPE_LIST = RecipeFakeData.getRecipeList();

    }

    @Test
    public void testItemDisplayed() {

        int TESTING_POS = 2;
        Recipe TESTING_RECIPE = RECIPE_LIST.get(TESTING_POS);


        onView(withRecyclerView(R.id.rv_recipes).atPosition(TESTING_POS)).check(matches(
                hasDescendant(allOf(withId(R.id.tv_recipe_name), withText(TESTING_RECIPE.getName())))));

    }
}
