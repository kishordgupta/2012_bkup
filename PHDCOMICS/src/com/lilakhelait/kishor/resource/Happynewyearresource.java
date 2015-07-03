package com.lilakhelait.kishor.resource;

import android.content.Context;

import com.comics.phdcomics.phdcomic.R;

public class Happynewyearresource {

	
	public static void getnewyearfiles(Context con)
	{
	String resource=	Resource.getstringfromfiles(R.raw.cosplay, con);
	Imageurl.newyearsvalues=Helper.helper(resource);
	
	}
}
