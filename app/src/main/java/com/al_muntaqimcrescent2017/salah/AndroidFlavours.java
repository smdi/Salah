package com.al_muntaqimcrescent2017.salah;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AndroidFlavours extends ArrayAdapter<AndroidAdapter> {

   public static final String LOG_TAG = AndroidFlavours.class.getSimpleName();

   public AndroidFlavours(Activity context , ArrayList<AndroidAdapter> androidAdapters)
   {
          super(context , 0 , androidAdapters);
   }
   @SuppressLint("ResourceType")
   @Override
   public View getView(int position , View convert , ViewGroup parent)
   {
       View listItemView = convert;
       if(listItemView == null) {
           listItemView = LayoutInflater.from(getContext()).inflate(
                   R.layout.listview, parent, false);
       }
        AndroidAdapter   curr = getItem(position);

       TextView t1 = (TextView) listItemView.findViewById(R.id.Heading);
       t1.setText(curr.mtext);


       TextView t2 = (TextView) listItemView.findViewById(R.id.Hadees);
       t2.setText(curr.mtext1);

       TextView t3 = (TextView) listItemView.findViewById(R.id.Number);
       t3.setText(curr.mtext2);

       return listItemView;
   }

 }
