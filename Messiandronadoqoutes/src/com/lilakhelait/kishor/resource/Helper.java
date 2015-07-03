package com.lilakhelait.kishor.resource;

import java.util.ArrayList;

public class Helper {

	public static ArrayList<Wish> helper(String resource)
	{
    
		ArrayList<Wish> list = new ArrayList<Wish>();
		
		String[] trimed = resource.split("8xxx8");
		
		for(String s:trimed )
		{
			if(s.length()>5)
			{
		//	s=	s.replace("#*#", "\n");
					
			Wish wish = new Wish();
			wish.setWishtext(s);
			list.add(wish);
			}
		}
		return list;
	}
}

		