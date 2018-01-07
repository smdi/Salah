package com.al_muntaqimcrescent2017.salah;

/**
 * Created by Imran on 20-11-2017.
 */

public class AndroidAdapter {

 int mresourse;

    String mtext,mtext1 ,mtext2;

    public AndroidAdapter(String mtext , String mtext1 , String mtext2)
    {
        this.mtext=mtext;
        this.mtext1=mtext1;
        this.mtext2=mtext2;
    }



    public String listText()
    {
      return mtext;
    }
    public String listTextfor()
    {
        return mtext1;
    }
    public String listTextforNum()
    {
        return mtext2;
    }

}
