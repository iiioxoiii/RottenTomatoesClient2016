package org.ecaib.rottentomatoesclient2016;

import android.net.Uri;
import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

class RottenTomatoesAPI {
    private static final String BASE_URL = "http://api.rottentomatoes.com/api/public/v1.0/";
    private static final String API_KEY = "9htuhtcb4ymusd73d4z6jxcj";
    private static final Integer LIMIT = 50;
    private static final int PAGES = 10;

    static ArrayList<Movie> getPeliculesMesVistes(String pais) {
        ArrayList<Movie> result = new ArrayList<>();

        return doCall("box_office.json", pais);
    }

    static ArrayList<Movie> getProximesEstrenes(String pais) {
        return doCall("upcoming.json", pais);
    }

    private static String getUrlPage(String pais, String endpoint, int page) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("lists")
                .appendPath("movies")
                .appendPath(endpoint)
                .appendQueryParameter("country", pais)
                .appendQueryParameter("limit", LIMIT.toString())
                .appendQueryParameter("apikey", API_KEY)
                .appendQueryParameter("page", String.valueOf(page))
                .build();
        return builtUri.toString();
    }

    @Nullable
    private static ArrayList<Movie> doCall(String endpoint, String pais) {
        ArrayList<Movie> movies = new ArrayList<>();

        for (int i = 0; i < PAGES; i++) {
            try {
                String url = getUrlPage(pais, endpoint, i);
                String JsonResponse = HttpUtils.get(url);
                ArrayList<Movie> list = processJson(JsonResponse);
                movies.addAll(list);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return movies;
    }

    private static ArrayList<Movie> processJson(String jsonResponse) {
        ArrayList<Movie> movies = new ArrayList<>();
        try {
            JSONObject data = new JSONObject(jsonResponse);
            JSONArray jsonMovies = data.getJSONArray("movies");
            for (int i = 0; i < jsonMovies.length(); i++) {
                JSONObject jsonMovie = jsonMovies.getJSONObject(i);

                Movie movie = new Movie();
                movie.setTitle(jsonMovie.getString("title"));
                movie.setYear(jsonMovie.getInt("year"));
                movie.setSynopsis(jsonMovie.getString("synopsis"));
                movie.setPosterUrl(jsonMovie.getJSONObject("posters").getString("thumbnail"));
                movie.setCritics_score(jsonMovie.getJSONObject("ratings").getInt("critics_score"));

                movies.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movies;
    }
}
