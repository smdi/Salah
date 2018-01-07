package com.al_muntaqimcrescent2017.salah;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class Choice extends AppCompatActivity {

     Button b1,b2,b3,b4;

     FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        floatingActionButton  = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String sAux = "\nLet me recommend you this application\n\n";
//                    sAux = sAux + "https://play.google.com/store/apps/details?id=Orion.Soft \n\n";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "choose one"));
                } catch(Exception e) {
                    e.printStackTrace();
                }

            }
        });

        b4 = (Button) findViewById(R.id.exit);
        b4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                finish();
            }

        });

        b3 = (Button) findViewById(R.id.masjid_finder1);
        b3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(getApplicationContext() ,Compass.class);
                startActivity(i1);
            }

        });

        b1 = (Button) findViewById(R.id.salah);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    Intent i1 = new Intent(getApplicationContext() ,City_Country.class);
                    startActivity(i1);
                }
                else {
                    Intent i2 = new Intent(getApplicationContext(),NointernetDisplay.class);
                    startActivity(i2);
                    Toast.makeText(getApplicationContext(), "No Internet Connection .... ", Toast.LENGTH_LONG).show();

                }
            }
        });




        b2 = (Button) findViewById(R.id.masjid_finder);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

                        WebView webView = (WebView) findViewById(R.id.webView1);
                        webView.getSettings().setJavaScriptEnabled(true);
                        webView.loadUrl("http://maps.google.com/maps?daddr=" + " Masjid");
                }
                else {
                    Intent i2 = new Intent(getApplicationContext(),NointernetDisplay.class);
                    startActivity(i2);
                    Toast.makeText(getApplicationContext(), "No Internet Connection....", Toast.LENGTH_LONG).show();

                }
            }
        });

    }


   public  void changeState(View view)
   {
       switch (view.getId())
       {
           case R.id.salah :
                 b1.setTextColor(Color.WHITE);

               break;

           case R.id.masjid_finder :

               b2.setTextColor(Color.WHITE);

               break;


           case R.id.masjid_finder1 :

               b3.setTextColor(Color.WHITE);
               break;


           case R.id.exit  :

               b4.setTextColor(Color.WHITE);
               break;






       }




   }


}
