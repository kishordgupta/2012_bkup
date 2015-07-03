package com.example.swipeuiforupclose;

/**
 * 
 */


import java.io.IOException;
import java.lang.reflect.Method;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.app.ProgressDialog;
import android.app.KeyguardManager.KeyguardLock;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * @author little
 *
 */
public class HelperActivity extends Activity {
	
	private HelperActivity ctx;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ctx=this;
		String action= (String)getIntent().getExtras().get("DO");
		notificationaction();
	}
	
	public void notificationaction()
	 {
			String action = (String) getIntent().getExtras().get("DO");
			if (action.equals("rep")) {
				try {
					if (!MainActivity.mIsPlaying) {
						MainActivity.playRadio();
					}
					else
						MainActivity.stopRadio();
				} catch (Exception e) {

				}

			}
			else if(action.equals("playlisten")){
				try {
					if (!MainActivity.mIsPlaying) {
						MainActivity.playRadio();
					}
					else
						MainActivity.stopRadio();
				} catch (Exception e) {

				}
			}
			else if (action.equals("close")) {
				Toast.makeText(getApplicationContext(), "Close", Toast.LENGTH_LONG)
						.show();
				NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				notificationManager.cancel(548853);
				MainActivity.justStop();

				System.exit(0);

			} else if (action.equals("open")) {
				NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				notificationManager.cancel(548853);

				startActivity(new Intent(this, MainActivity.class));
				finish();

			}
			
			finish();
			/*
			 * if(!action.equals("close")){ try { Object service = getSystemService
			 * ("statusbar"); Class <?> statusBarManager = Class.forName
			 * ("android.app.StatusBarManager"); //Use expand instead of collapse to
			 * view the notification area Method collapse =
			 * statusBarManager.getMethod ("collapse"); collapse.invoke (service); }
			 * catch (Exception e) { Toast.makeText(getApplicationContext(),
			 * e.getMessage(), Toast.LENGTH_LONG).show(); } }
			 */
		
			

		}
}
