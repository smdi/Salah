package com.al_muntaqimcrescent2017.salah;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class City_Country extends AppCompatActivity {

    boolean has = false;
    City_Country_store city_country_store;
    EditText ed1 ,ed2;
    Button bt1;

    public AutoCompleteTextView ac,ac1;

    ArrayList<String> arrayList ,arrayList1;
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city__country);


        city_country_store = new City_Country_store(getApplicationContext());


         arrayList = new ArrayList<String>();
         arrayList1 = new ArrayList<String>();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int i1 = preferences.getInt("int",1);

        if(i1 == 1)
        {

            insertData( "" ,"");
            SharedPreferences prefs =
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

            SharedPreferences.Editor editor = prefs.edit();

            editor.putInt("int", 2);

            editor.commit();


        }
        else {
            displayDatabaseInfo();
        }

        int i =1 ;

        if(i==1)
        {
            i=2;
            arrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arrayList);
            final AutoCompleteTextView actv= (AutoCompleteTextView)findViewById(R.id.edit_x);
            actv.setThreshold(1);
            actv.setAdapter(arrayAdapter);
            actv.setTextColor(Color.RED);
            ac = actv;
        }
        if(i==2)
        {
            i=1;
            arrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arrayList1);
            final AutoCompleteTextView actv1= (AutoCompleteTextView)findViewById(R.id.edit_y);
            actv1.setThreshold(1);//will start working from first character
            actv1.setAdapter(arrayAdapter);//setting the adapter data into the AutoCompleteTextView
            actv1.setTextColor(Color.RED);
             ac1= actv1;
        }

        bt1 = (Button) findViewById(R.id.get_The_Results);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    String x = ac.getText().toString().trim();
                    String y = ac1.getText().toString().trim();



                    SQLiteDatabase db = city_country_store.getReadableDatabase();

                    String[] project = {PetsContract.PetEntry._ID, PetsContract.PetEntry.COLUMN_City, PetsContract.PetEntry.COLUMN_Country};

                    Cursor cursor = db.query(
                            PetsContract.PetEntry.TABLE_NAME,
                            project,
                            null,
                            null,
                            null,
                            null,
                            null
                    );


                    try {

                        String currenName = "";
                        String currenBreed = "";

                        int colounName = cursor.getColumnIndex(PetsContract.PetEntry.COLUMN_City);
                        int columnBreed = cursor.getColumnIndex(PetsContract.PetEntry.COLUMN_Country);

                        while (cursor.moveToNext()) {

                            currenName = cursor.getString(colounName);
                            currenBreed = cursor.getString(columnBreed);

                            if (currenName.equals(x) && currenBreed.equals(y)) {

                                dontSetup(x, y);
                                has = true;
                                break;
                            }
                            if (currenName.equals(x) && currenBreed.equals("dont")) {

                                dontSetup(x, y);
                                has = true;
                                break;
                            }
                            if (currenName.equals("dont") && currenBreed.equals(y)) {

                                dontSetup(x, y);
                                has = true;
                                break;
                            }

                        }

                        if (has == false) {

                            if (currenName.equals(x) && !currenBreed.equals(y)) {
                                setupY(x, y);
                            } else if (!currenName.equals(x) && currenBreed.equals(y)) {
                                setupX(x, y);
                            } else if(!currenName.equals(x) && !currenBreed.equals(y)){
                                goSetup(x, y);
                            }

                        }

                    }
                    finally {
                        cursor.close();
                    }

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }


        });
    }

    private void setupX(String x, String y) {

        if (x.length()>=2&&y.length()>=2)
        {
            Toast.makeText(getApplicationContext(),"Fetching Data! Plz wait....",Toast.LENGTH_LONG).show();
            insertData(x,"dont");
            Intent i1 = new Intent(getApplicationContext(), SalahTimer.class);
            i1.putExtra("start date", x);
            i1.putExtra("End date", y);
            startActivity(i1);
        }
        else {
            Toast.makeText(getApplicationContext(), "Please! Enter the City and Country", Toast.LENGTH_LONG).show();
        }
    }

    private void setupY(String x, String y) {

        if (x.length()>=2&&y.length()>=2)
        {
            Toast.makeText(getApplicationContext(),"Fetching Data! Plz wait....",Toast.LENGTH_LONG).show();
            insertData("dont",y);
            Intent i1 = new Intent(getApplicationContext(), SalahTimer.class);
            i1.putExtra("start date", x);
            i1.putExtra("End date", y);
            startActivity(i1);
        }
        else {
            Toast.makeText(getApplicationContext(), "Please! Enter the City and Country", Toast.LENGTH_LONG).show();
        }
    }

    private void goSetup(String x, String y) {

        if (x.length()>=2&&y.length()>=2)
        {
            Toast.makeText(getApplicationContext(),"Fetching Data! Plz wait....",Toast.LENGTH_LONG).show();
            insertData(x,y);
            Intent i1 = new Intent(getApplicationContext(), SalahTimer.class);
            i1.putExtra("start date", x);
            i1.putExtra("End date", y);
            startActivity(i1);
        }
        else {
            Toast.makeText(getApplicationContext(), "Please! Enter the City and Country", Toast.LENGTH_LONG).show();
        }
    }

    private void dontSetup(String x ,String y) {

        if (x.length()>=2&&y.length()>=2)
        {
            Toast.makeText(getApplicationContext(),"Fetching Data! Plz wait....",Toast.LENGTH_LONG).show();
            Intent i1 = new Intent(getApplicationContext(), SalahTimer.class);
            i1.putExtra("start date", x);
            i1.putExtra("End date", y);
            startActivity(i1);
        }
        else {
            Toast.makeText(getApplicationContext(), "Please! Enter the City and Country", Toast.LENGTH_LONG).show();
        }
    }
    private void displayDatabaseInfo() {

        SQLiteDatabase db = city_country_store.getReadableDatabase();

        String []project = {PetsContract.PetEntry._ID, PetsContract.PetEntry.COLUMN_City, PetsContract.PetEntry.COLUMN_Country};

        Cursor cursor = db.query(
                PetsContract.PetEntry.TABLE_NAME,
                project,
                null,
                null,
                null,
                null,
                null
        );


        try {

            int colounName = cursor.getColumnIndex(PetsContract.PetEntry.COLUMN_City);
            int columnBreed = cursor.getColumnIndex(PetsContract.PetEntry.COLUMN_Country);
            while(cursor.moveToNext())
            {

                String currenName = cursor.getString(colounName);
                String currenBreed = cursor.getString(columnBreed);

                arrayList.add(currenName);
                arrayList1.add(currenBreed);
            }
        } finally {
            cursor.close();
        }

    }

    private void insertData(String x ,String y) {

        SQLiteDatabase sqldb =city_country_store.getWritableDatabase();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int i1 = preferences.getInt("int",1);


          if(i1==1) {

              ContentValues values = new ContentValues();
              values.put(PetsContract.PetEntry.COLUMN_City, "");
              values.put(PetsContract.PetEntry.COLUMN_Country, "");


              SharedPreferences prefs =
                      PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

              SharedPreferences.Editor editor = prefs.edit();

              editor.putInt("int", 2);

              editor.commit();

              long newRowId = sqldb.insert(PetsContract.PetEntry.TABLE_NAME, null, values);
              Log.v("","" +newRowId);

          }
          else
          {

              ContentValues values = new ContentValues();
              values.put(PetsContract.PetEntry.COLUMN_City, x);
              values.put(PetsContract.PetEntry.COLUMN_Country, y);

              long newRowId = sqldb.insert(PetsContract.PetEntry.TABLE_NAME, null, values);
              Log.v("","" +newRowId);

          }
    }
}
