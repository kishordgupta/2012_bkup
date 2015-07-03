package com.jackanik.medical.bangladesh.admission.modeltest;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Share {

	public static void share(String wish, Context context)
	{
		 Intent sendIntent = new Intent(Intent.ACTION_SEND);
		 sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT, wish);
			sendIntent.setType("text/plain");
         context.startActivity(Intent.createChooser(sendIntent, "Share image using"));
	}
	
	 public static void starthitman(Context c)
	 {
		 Intent intent;

		    try {
		        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.bcs.admissionok.modeltest.versity"));
		        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        c.startActivity(intent);
		    } catch (Exception e) {
		     
		    }
	 }
}
