package com.lilakhelait.kishor.resource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.res.Resources;

public class Resource {

	public static String getstringfromfiles(int a,Context con)
	{
		try {
        Resources res = con.getResources();
        InputStream in_s = res.openRawResource(a);

      //  byte[] b = new byte[in_s.available()];
     //   in_s.read(b);
    //  in_s= con.getAssets().open("movilist.txt");
      BufferedReader br=new BufferedReader(new 
              InputStreamReader(con.getAssets().open("movilist.txt")));
     String  s ="";
     String  b =null;
      while((s=br.readLine())!=null)
      {
    	 b=b+s; 
      }
        return ""+ b;//new String(b);
     
    } catch (Exception e) {
        // e.printStackTrace();
      //  txtHelp.setText("Error: can't show help.");
        return "";
    }}
}
