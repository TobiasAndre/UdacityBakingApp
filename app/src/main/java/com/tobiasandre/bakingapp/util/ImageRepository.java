package com.tobiasandre.bakingapp.util;

import com.tobiasandre.bakingapp.BakingApp;

import io.reactivex.Single;
import okhttp3.OkHttpClient;

/**
 * Created by Tobias Andre on 02/09/2017.
 */

public class ImageRepository implements ImageDataSource {

    OkHttpClient okHttpClient;

    public ImageRepository() {
        this.okHttpClient = BakingApp.get().clientHttp;
    }

    @Override public Single<String> getImage(String recipeName) {
        String urlFromPrefs = BakingApp.get().preferencesUtils.getRecipeImage(recipeName);
        if(urlFromPrefs == null || urlFromPrefs.isEmpty()){
            return SearchImage.getFirstImage(recipeName);
        }else{
            return Single.just(urlFromPrefs);
        }
    }
}
