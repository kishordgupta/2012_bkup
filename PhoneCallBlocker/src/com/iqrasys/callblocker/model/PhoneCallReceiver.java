package com.iqrasys.callblocker.model;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.android.internal.telephony.ITelephony;
import com.iqrasys.callblocker.activity.DateExpireNotifyActivity;
import com.iqrasys.callblocker.activity.HomePageActivity;
import com.iqrasys.callblocker.activity.IncomingCallScreen;
import com.iqrasys.db.DBHelper;
import com.iqrasys.db.MyFile;
import com.iqrasys.resource.PhoneNumberModel;
import com.iqrasys.utility.ApplicationSecurityHandler;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;

import android.media.AudioManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.WindowManager;
//import android.view.WindowManager;
import android.widget.Toast;

public class PhoneCallReceiver extends BroadcastReceiver {
	Context context = null;
	// private static final String TAG = "Phone call";
	ITelephony telephonyService;
	TelephonyManager telephonyManager;

	final static int callterminate = 0;
	final static int callwait = 1;
	final static int callok = 2;
	static int callstate = 0;// Idle
	public static int call = 0;// Normal

	void showHome(Context con) {
		final SharedPreferences settings = con.getSharedPreferences("ph", 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean("incommingcall", true);
		editor.commit();
	}

	private boolean isAppOnForeground(Context con) {
		ActivityManager activityManager = (ActivityManager) con
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> appProcesses = activityManager
				.getRunningAppProcesses();

		if (appProcesses == null) {
			return false;
		}

		final String packageName = con.getPackageName();
		for (RunningAppProcessInfo appProcess : appProcesses) {
			if (appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND
					&& appProcess.processName.equals(packageName)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void onReceive(final Context context, Intent intent) {

		if (isAppOnForeground(context)) {
			showHome(context);
		}

		// Checking authentication time.
//		SharedPreferences settings = context.getSharedPreferences("ph", 0);
//		if (settings.getBoolean("authenticationRequire", true) == true) {
//			return;
//		}
		
		Bundle extras = intent.getExtras();
		if (extras != null) {
			String state = extras.getString(TelephonyManager.EXTRA_STATE);
			 //Log.w("MY_DEBUG_TAG", state);

			if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
				callstate = 1;// Ringing
				// Checking authentication time.
				if (new ApplicationSecurityHandler()
						.isValidationExpired(context)) {
					Intent intentExpireDate = new Intent();
					intentExpireDate.setClass(context,
							DateExpireNotifyActivity.class);
					intentExpireDate.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					intentExpireDate
							.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
					intentExpireDate
							.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

					context.startActivity(intentExpireDate);
				} else {
					final String incomingNumber = extras
							.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
					// Log.w("MY_DEBUG_TAG", phoneNumber);
					// Toast.makeText(context, "Ringing number is:" +
					// phoneNumber,
					// Toast.LENGTH_LONG).show();

					AudioManager audioManager = (AudioManager) context
							.getSystemService(Context.AUDIO_SERVICE);
					// Turn ON the mute
					audioManager.setStreamMute(AudioManager.STREAM_RING, true);
					TelephonyManager telephonyManager = (TelephonyManager) context
							.getSystemService(Context.TELEPHONY_SERVICE);
					try {
						// Toast.makeText(context,
						// "incoming number "+incomingNumber,
						// Toast.LENGTH_LONG).show();
						Class clazz = Class.forName(telephonyManager.getClass().getName());
						Method method = clazz.getDeclaredMethod("getITelephony");
						method.setAccessible(true);

						ITelephony telephonyService = (ITelephony) method.invoke(telephonyManager);
						// Checking incoming call number
						ArrayList<PhoneNumberModel> allNumber = new ArrayList<PhoneNumberModel>();
						allNumber.addAll(PhoneNumberContainer.localNumber);
						allNumber.addAll(PhoneNumberContainer.serverNumber);

						// System.out.println("Call "+block_number);
						for (PhoneNumberModel numberModel : allNumber) {
							String strInCallNumber = incomingNumber
									.toLowerCase();
							String strDBNumber = numberModel.getNumberText()
									.toLowerCase();

							if (strInCallNumber.contains(strDBNumber)) {
								telephonyService = (ITelephony) method.invoke(telephonyManager);
								if (call == 2)// Total block
								{
									telephonyService.endCall();
								} else if (call == 1) // Vibrate
								{
									// Get instance of Vibrator from current
									// Context
									Vibrator mVibrator = (Vibrator) context
											.getSystemService(Context.VIBRATOR_SERVICE);
									// mVibrator.vibrate(2000);

									long pattern[] = { 0, 800, 200, 1200, 0,
											1000, 400, 1000, 0, 800, 200, 1200,
											0, 1000, 400, 1000, 0, 800, 200,
											1200, 0, 1000, 400, 0 };
									mVibrator.vibrate(pattern, -1);
									telephonyService.endCall();

								} else if (call == 0) // Normal
								{
									final String strCallerTitle = numberModel
											.getTitleText();

									Thread pageTimer = new Thread() {
										@Override
										public void run() {
											try {
												sleep(1000);
											} catch (InterruptedException e) {
												e.printStackTrace();
											} finally {
												Intent intentCallScreen = new Intent();
												intentCallScreen
														.setClass(
																context,
																IncomingCallScreen.class);
												intentCallScreen
														.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
												intentCallScreen
														.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
												intentCallScreen
														.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

												intentCallScreen.putExtra(
														"caller_title",
														strCallerTitle);
												intentCallScreen.putExtra(
														"caller_number",
														incomingNumber);

												context.startActivity(intentCallScreen);
											}
										}
									};
									pageTimer.start();
								}

								break;
							}
						}

						// Turn OFF the mute
						audioManager.setStreamMute(AudioManager.STREAM_RING,
								false);

					} catch (Exception e) {
						Toast.makeText(context, e.toString(), Toast.LENGTH_LONG)
								.show();
					}

				}
			}
		}

	}

	
}