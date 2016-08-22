package org.ecaib.rottentomatoesclient2016;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.CursorLoader;

import java.util.ArrayList;

import nl.littlerobots.cupboard.tools.provider.UriHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by carlesgm on 22/8/16.
 */

public class DataManager {
    private static UriHelper URI_HELPER = UriHelper.with(RottenTomatoesContentProvider.AUTHORITY);
    private static Uri MOVIE_URI = URI_HELPER.getUri(Movie.class);

    static void saveMovies(ArrayList<Movie> movies, Context context) {
        cupboard().withContext(context).put(MOVIE_URI, Movie.class, movies);
    }

    static void deleteMovies(Context context) {
        cupboard().withContext(context).delete(MOVIE_URI, "_id > ?", "0");
    }

    static CursorLoader getCursorLoader(Context context) {
        return new CursorLoader(context, MOVIE_URI, null, null, null, null);
    }

}
