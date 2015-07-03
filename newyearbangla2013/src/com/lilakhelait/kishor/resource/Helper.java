package com.lilakhelait.kishor.resource;

import java.util.ArrayList;

public class Helper {

	public static ArrayList<Movies> helper(String resource)
	{
    
		ArrayList<Movies> list = new ArrayList<Movies>();
		
		String[] trimed = resource.split("8xxx8");
		
		for(String s:trimed )
		{
			if(s.length()>5)
			{
		//	s=	s.replace("#*#", "\n");
					
			Movies wish = new Movies();
			wish.setWishtext(s);
			list.add(wish);
			}
		}
		return list;
	}
}

		