package org.ecaib.rottentomatoesclient2016;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by carlesgm on 29/8/17.
 */

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<Movie> selected = new MutableLiveData<Movie>();

    public void select(Movie movie) {
        selected.setValue(movie);
    }

    public LiveData<Movie> getSelected() {
        return selected;
    }
}