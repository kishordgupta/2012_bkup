package com.lilakhelait.kishor.resource.event;

import java.io.IOException;

import com.lilait.football.toptenteams.R;
import com.lilakhelait.kishor.listview.Happynewyearlist;

import com.lilakhelait.kishor.resource.Helper;
import com.lilakhelait.kishor.resource.Resource;

import android.content.Context;

public class Happynewyearresource {

	
	public static void getnewyearfiles(Context con)
	{
	String resource=	Resource.getstringfromfiles(R.raw.haunted, con);
	try {
		Happynewyearlist.newyearsvalues1=Helper.helper(resource,con);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public static void getnewyearfilesd(Context con)
	{
	String resource=	Resource.getstringfromfiles(R.raw.haunted, con);
	try {
		Happynewyearlist.newyearsvalues=Helper.helper(resource,con);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
