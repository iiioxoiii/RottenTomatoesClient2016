package org.ecaib.rottentomatoesclient2016;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.alexvasilkov.events.Events;

import java.util.ArrayList;

class RefreshDataTask extends AsyncTask<Void, Void, Void> {
    private Context context;

    RefreshDataTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        Events.post("start-downloading-data");
    }

    @Override
    protected Void doInBackground(Void... voids) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String pais = preferences.getString("pais", "es");
        String tipusConsulta = preferences.getString("tipus_consulta", "vistes");

        ArrayList<Movie> result;
        if (tipusConsulta.equals("vistes")) {
            result = RottenTomatoesAPI.getPeliculesMesVistes(pais);
        } else {
            result = RottenTomatoesAPI.getProximesEstrenes(pais);
        }

        Log.d("DEBUG", result != null ? result.toString() : null);

        DataManager.deleteMovies(context);
        DataManager.saveMovies(result, context);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Events.post("finish-downloading-data");
    }

}
