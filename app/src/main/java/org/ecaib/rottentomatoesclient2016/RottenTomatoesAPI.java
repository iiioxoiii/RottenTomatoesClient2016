package org.ecaib.rottentomatoesclient2016;

import android.net.Uri;

import java.io.IOException;

public class RottenTomatoesAPI {
    private final String BASE_URL = "http://api.themoviedb.org/3";
    private final String API_KEY = "1ea3bf746c81fe337d4cf49e7e66d670";

    String getPeliculesMesVistes(String pais) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("discover")
                .appendPath("movie")
                .appendQueryParameter("region", pais)
                .appendQueryParameter("api_key", API_KEY)
                .build();
        String url = builtUri.toString();

        try {
            String JsonResponse = HttpUtils.get(url);
            return JsonResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    String getProximesEstrenes(String pais) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("movie")
                .appendPath("upcoming")
                .appendQueryParameter("region", pais)
                .appendQueryParameter("api_key", API_KEY)
                .build();
        String url = builtUri.toString();

        try {
            String JsonResponse = HttpUtils.get(url);
            return JsonResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}