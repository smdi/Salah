package com.al_muntaqimcrescent2017.salah;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {


    private QueryUtils() {
    }

    public static ArrayList<AndroidAdapter> fetchEarthQuakeData(String reQuaetUrl)
    {

        URL url = CreateUrl(reQuaetUrl);

        String jsonResponse = null;
        try
        {
             jsonResponse= makeHttpRequest(url);

        }
        catch(Exception e)
        {
            Log.e("QueryUtils" ,"Unable to connect",e);
        }

        ArrayList<AndroidAdapter> earthquake = extractEarthquakes(jsonResponse);

        return  earthquake;
    }


    private static String makeHttpRequest(URL url) {
           String jsonRes = " ";

           if(url == null)
           {
               return  jsonRes;
           }

           HttpURLConnection urlConnection = null;
           InputStream inputStream = null;

           try
           {
                 urlConnection  =(HttpURLConnection) url.openConnection();
                 urlConnection.setReadTimeout(10000);
                 urlConnection.setConnectTimeout(15000);
                 urlConnection.setRequestMethod("GET");

                 if(urlConnection.getResponseCode()==200)
                 {
                    inputStream =urlConnection.getInputStream();
                    jsonRes  = readFromStream(inputStream);

                 }
                 else
                 {
                     Log.e("QueryUtils", "Error response code: " + urlConnection.getResponseCode());
                 }



           }
           catch (Exception  e)
           {

               Log.e("QueryUtils" ,"Unable to connect",e);
           }


          return  jsonRes;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {

        StringBuilder output = new StringBuilder();

        if(inputStream != null){

            InputStreamReader  inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line  = bufferedReader.readLine();
            while (line !=null)
            {
                output.append(line);
                line = bufferedReader.readLine();
            }
        }


        return output.toString();
    }

    private static URL CreateUrl(String reQuaetUrl) {

        URL url = null;

        try
        {
            url =new URL(reQuaetUrl);
        }

        catch (Exception  e)
        {
            Log.e("QueryUtils" ,"Unable to connect",e);
        }

        return   url;
    }

    public static ArrayList<AndroidAdapter> extractEarthquakes(String SAMPLE_JSON_RESPONSE) {


        ArrayList<AndroidAdapter> earthquakes = new ArrayList<>();
        try {

            int i=1;

            JSONObject baseJsonResponse = new JSONObject(SAMPLE_JSON_RESPONSE);
            JSONObject  data = baseJsonResponse.getJSONObject("data");
            JSONObject  timings = data.getJSONObject("timings");
            JSONObject  date = data.getJSONObject("date");
            String readable = date.getString("readable");
            JSONObject  meta = data.getJSONObject("meta");
            String timezone = meta.getString("timezone");


            switch(i=1)
            {
                case 1:

                    String fajr = timings.getString("Fajr");
                    AndroidAdapter androidAdapter = new AndroidAdapter("  Fajr  "+fajr,readable,timezone);
                    earthquakes.add(androidAdapter);
                case 2:
                    String sunrise = timings.getString("Sunrise");
                    AndroidAdapter androidAdapter1 = new AndroidAdapter("  Sunrise  "+sunrise,readable,timezone);
                    earthquakes.add(androidAdapter1);

                case 3:
                    String dhuhr = timings.getString("Dhuhr");
                    AndroidAdapter androidAdapter2 = new AndroidAdapter("  Dhuhr  "+dhuhr,readable,timezone);
                    earthquakes.add(androidAdapter2);

                case 4:
                    String asrr = timings.getString("Asr");
                    AndroidAdapter androidAdapter3 = new AndroidAdapter("  Asar  "+asrr,readable,timezone);
                    earthquakes.add(androidAdapter3);
                case 5:
                    String sunset = timings.getString("Sunset");
                    AndroidAdapter androidAdapter4 = new AndroidAdapter("  Sunset  "+sunset,readable,timezone);
                    earthquakes.add(androidAdapter4);

                case 6:
                    String magrib = timings.getString("Maghrib");
                    AndroidAdapter androidAdapter5 = new AndroidAdapter("  Magrib  "+magrib,readable,timezone);
                    earthquakes.add(androidAdapter5);

                case 7:
                    String isha = timings.getString("Isha");
                    AndroidAdapter androidAdapter6 = new AndroidAdapter("  Isha  "+isha,readable,timezone);
                    earthquakes.add(androidAdapter6);


                case 8:
                    String imask = timings.getString("Imsak");
                    AndroidAdapter androidAdapter7 = new AndroidAdapter("  Imsak  "+imask,readable,timezone);
                    earthquakes.add(androidAdapter7);

                case 9:
                    String midnight = timings.getString("Midnight");
                    AndroidAdapter androidAdapter8 = new AndroidAdapter("  Midnight  "+midnight,readable,timezone);
                    earthquakes.add(androidAdapter8);
               break;

            }




        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

}
