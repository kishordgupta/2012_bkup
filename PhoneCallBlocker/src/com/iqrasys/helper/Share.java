package com.iqrasys.helper;

import android.content.Context;
import android.content.Intent;

public class Share {

	public static void share(String wish, Context context) {
		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		sendIntent.putExtra(Intent.EXTRA_TEXT, wish);
		sendIntent.setType("text/plain");
		context.startActivity(sendIntent);
	}
}
