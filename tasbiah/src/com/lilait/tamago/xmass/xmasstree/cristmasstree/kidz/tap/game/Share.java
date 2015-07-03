package com.lilait.tamago.xmass.xmasstree.cristmasstree.kidz.tap.game;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Share {

	public static void share(String wish, Context context)
	{
		 Intent sharingIntent = new Intent(Intent.ACTION_SEND);
      //   Uri screenshotUri = Uri.parse("file://" + wish.getAbsolutePath());

		 sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, "I just make the xmass!!! #xmass #wishuallxmass ");
         context.startActivity(Intent.createChooser(sharingIntent, "Share tamago"));
	}
}
