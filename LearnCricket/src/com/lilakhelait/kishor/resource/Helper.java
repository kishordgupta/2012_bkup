package com.lilakhelait.kishor.resource;

import java.util.ArrayList;

public class Helper {

	public static ArrayList<Wish> helper(String resource)
	{
    
		ArrayList<Wish> list = new ArrayList<Wish>();
		
		String[] trimed = resource.split("xxxx");
		
		for(String s:trimed )
		{
		    String[] k =s.split("yyyy");
					if(k.length>=2)
					{
			Wish wish = new Wish();
			wish.setTitle(k[0].replace("  ", ""));
			wish.setWishtext(k[1].replace("  ", ""));
			list.add(wish);
					}
		}
		return list;
	}
}

		