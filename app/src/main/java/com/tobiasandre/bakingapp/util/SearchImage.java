package com.tobiasandre.bakingapp.util;

import com.tobiasandre.bakingapp.BakingApp;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Single;
import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tobias Andre on 02/09/2017.
 */

public class SearchImage {

    public static Single<String> getFirstImage(String query) {

        String url = HttpUrl.parse("https://www.googleapis.com/customsearch/v1")
                .newBuilder()
                .addQueryParameter("key", "AIzaSyBXQ5ZxXzoBtKXmFUBVe3M1hh_uWKoWMTs") //change GoogleApiKey
                .addQueryParameter("cx", "005645092098115632980:3yd9kfrvgry") //change GoogleSearchEngineId
                .addQueryParameter("searchType", "image")
                .addQueryParameter("q", query)
                .build()
                .toString();

        Request request = new Request.Builder().url(url).build();

        return Single.fromCallable(() -> BakingApp.get().clientHttp.newCall(request).execute()).map(response -> {
            String string = getFirstImageFromJson(response.body().string());
            //cache the result because search api free is limited to 100 requests a day
            BakingApp.get().preferencesUtils.saveRecipeImage(query,string);
            return string;
        });
    }

    private static String getFirstImageFromJson(String json) {
        try {
            JSONObject root = new JSONObject(json);
            JSONArray items = root.getJSONArray("items");
            return items.getJSONObject(0).getString("link");
        } catch (JSONException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
