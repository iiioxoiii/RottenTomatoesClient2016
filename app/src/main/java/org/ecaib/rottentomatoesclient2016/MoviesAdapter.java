package org.ecaib.rottentomatoesclient2016;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;


public class MoviesAdapter extends ArrayAdapter<Movie> {

    public MoviesAdapter(Context context, int resource, List<Movie> objects) {
        super(context, resource, objects);
    }
}
