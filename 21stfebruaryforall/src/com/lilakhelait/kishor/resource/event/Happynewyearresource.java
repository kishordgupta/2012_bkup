package com.lilakhelait.kishor.resource.event;

import com.ekushevashaandolon.motherlanguageday.myandroid.R;
import com.lilakhelait.kishor.listview.Happynewyearlist1;

import com.lilakhelait.kishor.resource.Helper;
import com.lilakhelait.kishor.resource.Resource;

import android.content.Context;

public class Happynewyearresource {

	
	public static void getnewyearfiles(Context con)
	{
	String resource=	Resource.getstringfromfiles(R.raw.kd, con);
	Happynewyearlist1.newyearsvalues1=Helper.helper(resource);
	}
	public static void getnewyearfilesd(Context con)
	{
	String resource=	Resource.getstringfromfiles(R.raw.adg, con);
	Happynewyearlist1.newyearsvalues=Helper.helper(resource);
	}
}