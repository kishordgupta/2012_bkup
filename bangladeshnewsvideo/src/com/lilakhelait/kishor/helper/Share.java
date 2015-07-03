package com.lilakhelait.kishor.helper;

import java.io.File;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.MimeTypeMap;

public class Share {

	public static void share(String id, Context context)
	{
		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		sendIntent.putExtra(Intent.EXTRA_TEXT, id);
		sendIntent.setType("text/plain");
		context.startActivity(sendIntent);
	/*	try{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
            context.startActivity(intent);                 
            }catch (ActivityNotFoundException ex){
                Intent intent=new Intent(Intent.ACTION_VIEW, 
                Uri.parse("http://www.youtube.com/watch?v="+id));
                context.startActivity(intent);
            }*/
	}
}
