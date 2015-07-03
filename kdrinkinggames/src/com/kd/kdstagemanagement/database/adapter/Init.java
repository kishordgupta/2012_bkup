package com.kd.kdstagemanagement.database.adapter;

import java.util.ArrayList;

import com.kd.kdstagemanagement.database.DBHelper;

import com.kd.kdstagemanagement.model.String;
import com.kd.kdstagemanagement.model.MovieDetails;
import com.kd.kdstagemanagement.model.Movies;
import com.kd.kdstagemanagement.model.KdList;
import com.kd.kdstagemanagement.model.TripsNtrivia;
import com.kd.kdstagemanagement.settings.FilterSetting;
import com.kd.kdstagemanagement.settings.SettingsHelp;

import android.content.Context;
import android.database.SQLException;

public class Init {

	
	public static void setdata(Context con)
	{
		KdList.Movieslist.clear();
		KdList.Filterslist.clear();
		KdList.MoviesDetailslist.clear();
		KdList.TripsNtrivialist.clear();
		KdList.Searchlist.clear();
		DBHelper db = new DBHelper(con);
		try {
		db.openDataBase();
		}catch(SQLException sqle){
			throw sqle;
		}
		KdList.Movieslist= (ArrayList<Movies>) db.getMovieset();
		KdList.Filterslist= (ArrayList<String>) db.getfilterset();
		KdList.MoviesDetailslist= (ArrayList<MovieDetails>) db.getMovieDetailset();
		KdList.TripsNtrivialist=(ArrayList<TripsNtrivia>) db.getTriviaset();
		db.close();
		int i=0;
		
		for (String f : KdList.Filterslist) {
			i++;
			String g=new String();
			g.Filters=f.Filters;
			g.Filtersid=f.Filtersid;
			KdList.Searchlist.add(g);
			if(i==5){
				break;}
		}
		SettingsHelp.initsettings();
		FilterSetting.initsettings();
	}
}
