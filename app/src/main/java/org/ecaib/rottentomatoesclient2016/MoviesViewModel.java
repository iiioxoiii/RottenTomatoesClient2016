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
    private final AppDatabase appDatabase;
    private final MovieDao movieDao;
    private LiveData<List<Movie>> movies;


    public MoviesViewModel(Application application) {
        super(application);

        this.app = application;
        this.appDatabase = AppDatabase.getDatabase(this.getApplication());
        this.movieDao = appDatabase.getMovieDao();
    }

    public LiveData<List<Movie>> getMovies() {
        return movieDao.getMovies();
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

            movieDao.deleteMovies();
            movieDao.addMovies(result);

            return result;
        }

    }
}
