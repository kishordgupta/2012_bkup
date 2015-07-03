package com.orinsharmin.anime.deathnote.quiz;

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
		        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.orinsharmin.anime.hitmanreborn"));
		        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        c.startActivity(intent);
		    } catch (Exception e) {
		     
		    }
	 }
	 public static void startdgre(Context c)
	 {
		 Intent intent;

		    try {
		        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.orinsharmin.anime.dgreyman"));
		        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        c.startActivity(intent);
		    } catch (Exception e) {
		     
		    }
	 }
	 public static void startfairytail(Context c)
	 {
		 Intent intent;

		    try {
		        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.anikasharmin.anime.fairytail.quiz"));
		        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        c.startActivity(intent);
		    } catch (Exception e) {
		     
		    }
	 }

	public static void startdgus(Context c) {
		// TODO Auto-generated method stub
		Intent intent;

	    try {
	        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.orinsharmin.anime.guessbydialogue.quotes"));
	        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        c.startActivity(intent);
	    } catch (Exception e) {
	     
	    }
	}
}
