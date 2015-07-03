package com.lilakhelait.kishor.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class GetNetworkStatus {
	static Context con;
	   public static boolean isNetworkAvailable(Context context) {
		  con=context;
		   ConnectivityManager connectivityManager 
	              = (ConnectivityManager)con.getSystemService(Context.CONNECTIVITY_SERVICE);
	        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	     
	        return activeNetworkInfo != null;
	    }
}
