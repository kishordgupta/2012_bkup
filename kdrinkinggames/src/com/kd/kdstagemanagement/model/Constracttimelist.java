package com.kd.kdstagemanagement.model;

import java.util.ArrayList;

import android.graphics.drawable.LevelListDrawable;

import com.kd.kdstagemanagement.helper.Timereturn;

public class Constracttimelist {


	
	public static ArrayList<Timeline> constructtimelinelist(String Movieid)
	{
		ArrayList<Timeline> timelinelist = new ArrayList<Timeline>();
		for (MovieDetails f : KdList.MoviesDetailslist) {
			
			if(f.MovieID.contains(Movieid))
			{
				Timeline t = new Timeline();
				t.time=f.Time;
				t.Type=f.TypeID;
				t.count=Timereturn.timereturn(f.Time);
				timelinelist.add(t);
			}
		}
		return timelinelist;
	}
	public static ArrayList<Trivialline> constructtrivialist(String Movieid)
	{
		ArrayList<Trivialline> timelinelist = new ArrayList<Trivialline>();
		for (TripsNtrivia f : KdList.TripsNtrivialist) {
			
			if(f.MovieID.contains(Movieid))
			{
				Trivialline t = new Trivialline();
				t.Type=f.Trivia;
	
				
				timelinelist.add(t);
			}
		}
		return timelinelist;
	}
	public static ArrayList<Leveline> constructLevel(Movies Movieid)
	{
		ArrayList<Leveline> timelinelist = new ArrayList<Leveline>();
		Leveline l = new Leveline();
		l.level=Movieid.Drinkinggamelevel1;
		timelinelist.add(l);
		l = new Leveline();
		l.level=Movieid.Drinkinggamelevel2;
		timelinelist.add(l);
		l = new Leveline();
		l.level=Movieid.Drinkinggamelevel3;
		timelinelist.add(l);
		l = new Leveline();
		l.level=Movieid.Drinkinggamelevel4;
		timelinelist.add(l);
		l = new Leveline();
		l.level=Movieid.Summmery;
		timelinelist.add(l);
		return timelinelist;
	}
}
