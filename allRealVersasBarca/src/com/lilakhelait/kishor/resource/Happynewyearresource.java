package com.lilakhelait.kishor.resource;

import com.lilait.realversasbarca.R;

import android.content.Context;


public class Happynewyearresource {

	
	public static void getnewyearfiles(Context con)
	{
	String resource=	Resource.getstringfromfiles(R.raw.ab, con);
	Imageurl.newyearsvalues=Helper.helper(resource);
	}
}
