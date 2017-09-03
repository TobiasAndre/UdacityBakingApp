package com.tobiasandre.bakingapp.util;

import io.reactivex.Single;

/**
 * Created by Tobias Andre on 02/09/2017.
 */

public interface ImageDataSource {
    Single<String> getImage(String recipeName);
}