package org.ecaib.rottentomatoesclient2016;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MoviesViewModel extends AndroidViewModel {
    private final Application app;
    private MutableLiveData<List<Movie>> movies;

    public MoviesViewModel(Application application) {
        super(application);

        this.app = application;
    }

    public LiveData<List<Movie>> getMovies() {
        Log.d("DEBUG", "ENTRA");

        if (movies == null) {
            movies = new MutableLiveData<>();
            reload();
        }
        return movies;
    }

    public void reload() {
        // do async operation to fetch users
        RefreshDataTask task = new RefreshDataTask();
        task.execute();
    }

    private class RefreshDataTask extends AsyncTask<Void, Void, ArrayList<Movie>> {
        @Override
        protected ArrayList<Movie> doInBackground(Void... voids) {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(
                    app.getApplicationContext()
            );
            String pais = preferences.getString("pais", "es");
            String tipusConsulta = preferences.getString("tipus_consulta", "vistes");

            RottenTomatoesAPI api = new RottenTomatoesAPI();
            ArrayList<Movie> result;
            if (tipusConsulta.equals("vistes")) {
                result = api.getPeliculesMesVistes(pais);
            } else {
                result = api.getProximesEstrenes(pais);
            }

            Log.d("DEBUG", result != null ? result.toString() : null);

            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> results) {
            movies.postValue(results);
        }
    }
}
