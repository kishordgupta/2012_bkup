package com.mobigeni.f1gp.live.formula1.videos;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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
