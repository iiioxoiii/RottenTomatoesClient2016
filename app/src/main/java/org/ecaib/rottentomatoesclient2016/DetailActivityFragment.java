package org.ecaib.rottentomatoesclient2016;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        Intent i = getActivity().getIntent();

        if (i != null) {
            Movie movie = (Movie) i.getSerializableExtra("movie");

            if (movie != null) {
                updateUi(movie);
            }
        }

        return view;
    }

    private void updateUi(Movie movie) {
        Log.d("MOVIE", movie.toString());
    }
}
