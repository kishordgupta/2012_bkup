package com.lilakhelait.kishor.resource;

import java.util.ArrayList;

public class Helper {

	public static ArrayList<String> helper(String resource)
	{
    
		ArrayList<String> list = new ArrayList<String>();
		
		String[] trimed = resource.split("\n");
		
		for(String s:trimed )
		{
			if(s.contains(".gif"))
			{
		//	s=	s.replace("#*#", "\n");
					
		
			list.add(s);
			}
		}
		return list;
	}
	

}

		