package com.lilakhelait.kishor.resource;

import java.util.ArrayList;

import android.util.Log;

public class Helper {

	public static ArrayList<Wish> helperarchive(String resource)
	{
    
		ArrayList<Wish> list = new ArrayList<Wish>();
		
		String[] trimed = resource.split("<a href=\"Spoilers/");
		int j=0;
		
		for(String s:trimed )
		{if(j!=0)
		{
			try{
			if(s.contains("html")){
			String[] as=	s.split(".html");
		String h = as[0];
	//	Log.d("1st",h );
		//
	//	String[] l = h.split(".html\"><");
			Wish wish = new Wish();
			String link = "http://www.themoviespoiler.com/Spoilers/"+h+".html";
		//	String[] la = l[1].split(">");
		wish.setDate(link);
		wish.setWishtext(h.toUpperCase());//la[1].replace("</font", ""));
		//Log.d("2nd",l[0]);
			list.add(wish);
			//Log.d("2nd",wish.getWishtext());
			}
			}catch (Exception e) {
				// TODO: handle exception
				
			}
		}
		else
		{
			j=1;
		}
		}
		return list;
	}
	
	//
	

	public static ArrayList<Wish> helperarchiv1e(String resource)
	{
    
		ArrayList<Wish> list = new ArrayList<Wish>();
		
		String[] trimed = resource.split("#");
		int j=0;
		
		for(int i=0;i<trimed.length;i++)
		{
			Wish wish = new Wish();
			
	    i++;
		wish.setDate(trimed[i]);
		i++;
		wish.setWishtext(trimed[i]);//la[1].replace("</font", ""));
		//Log.d("2nd",l[0]);
			list.add(wish);
			//Log.d("2nd",wish.getWishtext());
			}
			
		return list;
	}
}

		