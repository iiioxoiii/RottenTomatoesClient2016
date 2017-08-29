package org.ecaib.rottentomatoesclient2016;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class RottenTomatoesAPI {
    private final String BASE_URL = "http://api.themoviedb.org/3";
    private final String API_KEY = "1ea3bf746c81fe337d4cf49e7e66d670";

    ArrayList<Movie> getPeliculesMesVistes(String pais) {
        return doCall("discover", "movie", pais);
    }

    ArrayList<Movie> getProximesEstrenes(String pais) {
        return doCall("movie", "upcoming", pais);
    }

    private ArrayList<Movie> doCall(String recurs, String tipus, String pais) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath(recurs)
                .appendPath(tipus)
                .appendQueryParameter("region", pais)
                .appendQueryParameter("api_key", API_KEY)
                .build();
        String url = builtUri.toString();

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
            JSONArray jsonMovies = data.getJSONArray("results");
            for (int i = 0; i < jsonMovies.length(); i++) {
                JSONObject jsonMovie = jsonMovies.getJSONObject(i);

                Movie movie = new Movie();
                movie.setTitle(jsonMovie.getString("title"));
                movie.setReleaseDate(jsonMovie.getString("release_date"));
                movie.setOverview(jsonMovie.getString("overview"));
                movie.setPosterPath(jsonMovie.getString("poster_path"));

                movies.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movies;
    }

}