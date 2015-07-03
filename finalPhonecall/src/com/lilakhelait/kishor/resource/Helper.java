package com.lilakhelait.kishor.resource;

import java.util.ArrayList;

import android.util.Log;

import com.lilakhelait.kishor.model.CallNumber;

public class Helper {

	public static ArrayList<Wish> helper(String resource)
	{
    
		ArrayList<Wish> list = new ArrayList<Wish>();
		
		String[] trimed = resource.split("\n");
		
		for(String s:trimed )
		{
		    String[] k =s.split(",");
					if(k.length>=2)
					{
			Wish wish = new Wish();
			wish.setTitle(k[0].replace("  ", ""));
			wish.setWishtext(k[1].replace("  ", ""));
			CallNumber.valuesdb.add(wish);
			Log.d("ddf",wish.getTitle()+wish.getWishtext());
					}
		}
		return list;
	}
}

		