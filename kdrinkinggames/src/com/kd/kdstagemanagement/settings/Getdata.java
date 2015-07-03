package com.kd.kdstagemanagement.settings;

import android.content.Context;
import android.content.SharedPreferences;

public class Getdata {
	
	public static  void SavePreferences(Context c, String key, Boolean value){
		
	    SharedPreferences sharedPreferences = c.getSharedPreferences("prefs", 1);//getPreferences(1);
	    SharedPreferences.Editor editor = sharedPreferences.edit();
	    editor.putBoolean(key, value);
	    editor.commit();
	   }
	public static Boolean LoadPreferences(Context c,String key){
		    SharedPreferences sharedPreferences = c.getSharedPreferences("prefs", 1);
		    return  sharedPreferences.getBoolean(key, false);
		    
		    
		   }
}
