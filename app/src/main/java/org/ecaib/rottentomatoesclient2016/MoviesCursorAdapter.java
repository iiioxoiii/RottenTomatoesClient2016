package org.ecaib.rottentomatoesclient2016;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import org.ecaib.rottentomatoesclient2016.databinding.LvPelisRowBinding;

public class MoviesCursorAdapter extends CupboardCursorAdapter<Movie> {
    public MoviesCursorAdapter(Context context, Class<Movie> entityClass) {
        super(context, entityClass);
    }

    @Override
    public View newView(Context context, Movie model, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        LvPelisRowBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.lv_pelis_row, parent, false);

        return binding.getRoot();
    }

    @Override
    public void bindView(View view, Context context, Movie model) {
        LvPelisRowBinding binding = DataBindingUtil.getBinding(view);
        binding.tvTitle.setText(model.getTitle());
        binding.tvCriticsScore.setText(model.getCritics_score());
        Glide.with(context).load(model.getPosterUrl()).into(binding.ivPosterImage);
    }
}
