package com.lilakhelait.kishor.helper;

import android.app.ProgressDialog;
import android.content.Context;

public class Dialoge {
	public static void runDialog(final int seconds,Context ctx)
	{
		final ProgressDialog   progressDialog = ProgressDialog.show(ctx, "Please wait while we set up", "wait please");
		 
		        new Thread(new Runnable(){
		            public void run(){
		                try {
		                            Thread.sleep(seconds * 1000);
		                    progressDialog.dismiss();
		                } catch (InterruptedException e) {
		                    e.printStackTrace();
		                }
		            }
		        }).start();
		}
}
