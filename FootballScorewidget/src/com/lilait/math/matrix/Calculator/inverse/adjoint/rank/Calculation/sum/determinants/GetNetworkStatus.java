package com.lilait.math.matrix.Calculator.inverse.adjoint.rank.Calculation.sum.determinants;

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
	        return true;//activeNetworkInfo != null;
	    }
}
