package com.al_muntaqimcrescent2017.salah;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;


public class SalahTimer extends AppCompatActivity {

    String passedArg1, passedArg2;
    public String USGS_REQUEST_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salah_timer);


        passedArg1 = getIntent().getExtras().getString("start date");
        passedArg2 = getIntent().getExtras().getString("End date");

        USGS_REQUEST_URL ="http://api.aladhan.com/timingsByCity?city="+passedArg1+"&country="+passedArg2+"&method=8";

        EarthAsyn earthAsyn = new EarthAsyn();
        earthAsyn.execute(USGS_REQUEST_URL);

    }

    private void updateUi(ArrayList<AndroidAdapter> androidAdapters)
    {


        AndroidFlavours an  = new AndroidFlavours(this ,androidAdapters);

        ListView lv = (ListView)findViewById(R.id.listview);
        lv.setAdapter(an);

    }

    private class EarthAsyn extends AsyncTask<String, Void, ArrayList<AndroidAdapter>>
    {
        @Override
        protected ArrayList<AndroidAdapter> doInBackground(String... url) {

            ArrayList<AndroidAdapter> earthQuake = QueryUtils.fetchEarthQuakeData(USGS_REQUEST_URL);
            return earthQuake;
        }

        @Override
        protected void onPostExecute(ArrayList<AndroidAdapter> androidAdapter) {

            updateUi(androidAdapter);
        }
    }

}
