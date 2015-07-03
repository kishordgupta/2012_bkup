package com.lilakhelait.kishor.resource.event;

import android.content.Context;
import android.content.SharedPreferences;

import com.lilait.movie.story.spoiler.Mainwindowactivity;
import com.lilakhelait.kishor.listview.Happynewyearlist;
import com.lilakhelait.kishor.listview.Searchmovielist;
import com.lilakhelait.kishor.resource.Helper;

public class Happynewyearresource {

	
	public static void getnewyearfilesarchive(Context con)
	{
		SharedPreferences settings = con.getSharedPreferences(Mainwindowactivity.PREFS_NAME, 0);
	    Happynewyearlist.newyearsvalues=Helper.helperarchive(settings.getString("resource", "Need to update"));
	Searchmovielist.newyearsvalues=Happynewyearlist.newyearsvalues;
	}
}
