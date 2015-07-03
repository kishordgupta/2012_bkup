package com.lilakhelait.kishor.helper;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Share {

	public static void share(String wish, Context context)
	{
		 Intent sharingIntent = new Intent(Intent.ACTION_SEND);
         Uri screenshotUri = Uri.parse(wish);

         sharingIntent.setType("image/jpg");
         sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
         context.startActivity(Intent.createChooser(sharingIntent, "Share image using"));
	}
}
