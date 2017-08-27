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

import org.ecaib.rottentomatoesclient2016.databinding.FragmentDetailBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private View view;

    private FragmentDetailBinding binding;

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater);
        view = binding.getRoot();

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

        binding.tvTitle.setText(movie.getTitle());
        binding.tvReleaseDate.setText(movie.getReleaseDate());
        binding.tvSynopsis.setText(Html.fromHtml("<b>Synopsis:</b> " + movie.getOverview()));
        Glide.with(getContext()).load(
                "https://image.tmdb.org/t/p/w500/" + movie.getPosterPath()
        ).into(binding.ivPosterImage);
    }
}
