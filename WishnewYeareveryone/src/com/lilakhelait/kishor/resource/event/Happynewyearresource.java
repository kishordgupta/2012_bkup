package com.lilakhelait.kishor.resource.event;

import com.lilakhelait.kishor.listview.Happynewyearlist;
import com.lilakhelait.kishor.mainscreenactivity.R;
import com.lilakhelait.kishor.resource.Helper;
import com.lilakhelait.kishor.resource.Resource;

import android.content.Context;

public class Happynewyearresource {

	
	public static void getnewyearfiles(Context con)
	{
	String resource=	Resource.getstringfromfiles(R.raw.rawhappynewyear, con);
	Happynewyearlist.newyearsvalues=Helper.helper(resource);
	}
}
