package org.ecaib.rottentomatoesclient2016;

import android.net.Uri;

import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

class RottenTomatoesAPI {
    private final String BASE_URL = "http://api.rottentomatoes.com/api/public/v1.0/";
    private final String API_KEY = "9htuhtcb4ymusd73d4z6jxcj";

    ArrayList<Movie> getPeliculesMesVistes(String pais) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("lists")
                .appendPath("movies")
                .appendPath("box_office.json")
                .appendQueryParameter("country", pais)
                .appendQueryParameter("apikey", API_KEY)
                .build();
        String url = builtUri.toString();

        return doCall(url);
    }

    ArrayList<Movie> getProximesEstrenes(String pais) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("lists")
                .appendPath("movies")
                .appendPath("upcoming.json")
                .appendQueryParameter("country", pais)
                .appendQueryParameter("apikey", API_KEY)
                .build();
        String url = builtUri.toString();

        return doCall(url);
    }

    @Nullable
    private ArrayList<Movie> doCall(String url) {
        try {
            String JsonResponse = HttpUtils.get(url);
            return processJson(JsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Movie> processJson(String jsonResponse) {
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
