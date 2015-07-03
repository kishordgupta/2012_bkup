package com.lilakhelait.kishor.resource.event;


import java.util.ArrayList;

import com.lilakhelait.kishor.listview.Happynewyearlist;

import com.lilakhelait.kishor.resource.Helper;
import com.lilakhelait.kishor.resource.Resource;
import com.lilakhelait.kishor.resource.Wish;

import android.content.Context;

public class Happynewyearresource {

	
	public static ArrayList<Wish> getnewyearfiles(Context con,String string)
	{
	String resource=	Resource.getstringfromfiles( string, con);
	return Helper.helper(resource);
	}

	public static ArrayList<Wish> getnewyearfilesof(Context con,String string) {
		// TODO Auto-generated method stub
		String resource=	Resource.getstringfromfiles( string, con);
		return Helper.helperof(resource);
	}
	
}
