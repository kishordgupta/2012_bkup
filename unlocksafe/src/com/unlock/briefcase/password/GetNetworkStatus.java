package com.unlock.briefcase.password;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Vibrator;

public class GetNetworkStatus {
	static Context con;
	   public static boolean isNetworkAvailable(Context context) {
		  con=context;
		   ConnectivityManager connectivityManager 
	              = (ConnectivityManager)con.getSystemService(Context.CONNECTIVITY_SERVICE);
	        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	        return activeNetworkInfo != null;
	    }
	   
	   public static void vibrare(Context context, int n) {
		Vibrator va = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
	
		va.vibrate(n);
	   }
}
