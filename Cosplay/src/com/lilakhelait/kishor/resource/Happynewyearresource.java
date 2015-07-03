package com.lilakhelait.kishor.resource;

import android.content.Context;
import android.widget.Toast;

import com.cosplay.costumeshow.fantasydress.R;

public class Happynewyearresource {

	
	public static void getnewyearfiles(Context con)
	{
	String resource=	Resource.getstringfromfiles(R.raw.cosplay, con);
	Imageurl.newyearsvalues=Helper.helper(resource);
	//Toast.makeText(con, Imageurl.newyearsvalues.size() +Imageurl.newyearsvalues.get(0)+"",Toast.LENGTH_LONG).show();
	}
}
