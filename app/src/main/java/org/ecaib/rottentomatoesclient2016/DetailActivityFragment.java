package org.ecaib.rottentomatoesclient2016;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private View view;
    private ImageView ivPosterImage;
    private TextView tvTitle;
    private TextView tvCriticsScore;
    private TextView tvAudienceScore;
    private TextView tvCriticsConsensus;
    private TextView tvSynopsis;
    private TextView tvReleaseDate;

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail, container, false);

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

        ivPosterImage = view.findViewById(R.id.ivPosterImage);
        tvTitle =  view.findViewById(R.id.tvTitle);
        tvReleaseDate = view.findViewById(R.id.tvReleaseDate);
        tvSynopsis =   view.findViewById(R.id.tvSynopsis);

        tvTitle.setText(movie.getTitle());
        tvReleaseDate.setText(movie.getReleaseDate());
        tvSynopsis.setText(Html.fromHtml("<b>Synopsis:</b> " + movie.getOverview()));
        Glide.with(getContext()).load(
                "https://image.tmdb.org/t/p/w500/" + movie.getPosterPath()
        ).into(ivPosterImage);
    }
}
