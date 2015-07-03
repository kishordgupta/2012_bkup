package com.lilakhelait.kishor.resource;

import java.util.ArrayList;

import android.util.Log;
import android.widget.Toast;

public class Helper {

	public static ArrayList<String> helper(String resource)
	{
    
		ArrayList<String> list = new ArrayList<String>();
		
		String[] trimed = resource.split("\n");
		
		for(String s:trimed )
		{
			if(s.length()>3)
			{
	//	Imageurl.newyearsvalues.add(s);
				Log.d("df", s+"");
		
			list.add(s);
			}
		}
		return list;
	}
}

		