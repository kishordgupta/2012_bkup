package com.kd.phonecall;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.android.internal.telephony.ITelephony;
import com.lilakhelait.kishor.model.CallNumber;
import com.lilakhelait.kishor.utility.MyFile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.os.Handler;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class PhoneCallReceiver extends BroadcastReceiver {
	Context context = null;
	private static final String TAG = "Phone call";
	ITelephony telephonyService;
	TelephonyManager telephonyManager;
final static int callterminate=0;
final static int callwait =1;
final static int callok =2;
static int callstate = callwait;

	public void data(Context cont)
	{
		 MyFile f = new MyFile(cont);
		 CallNumber.values.clear();
         String s=	 f.readFromSD();
			String[] lines = s.split("\n");
			for (String string : lines) {
				if(string.length()>6)
				{
					string="+"+ string;
				CallNumber.values.add(string);
				}
			}
	}
	public static String incomm="";
	
	@Override
	public void onReceive(Context context, Intent arg1) {
		callstate = callwait;
		data(context);
		Toast.makeText(context, CallNumber.values.size()+"", 5000).show();
		// TODO Auto-generated method stub
		telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		telephonyManager.listen(new PhoneCallListener(),
				PhoneStateListener.LISTEN_CALL_STATE);
		// Java Reflections
		Class<?> c = null;
		try {
			c = Class.forName(telephonyManager.getClass().getName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Method m = null;
		try {
			m = c.getDeclaredMethod("getITelephony");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.setAccessible(true);
		try {
			telephonyService = (ITelephony) m.invoke(telephonyManager);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (String s : CallNumber.values) {
			
			
		//	Log.i(LOG_TAG, "RINGING, number: " + s+ " " + incomingNumber);
			if(s.contains(incomm))
			{telephonyService.endCall();
				callstate=callterminate;
				break;
			}
		}
		if(callstate==callwait)
			callstate=callok;
		/*
		while(true)
		{
			if(callstate==callterminate)
			{
				callstate=callwait;
		telephonyService.endCall();
	break;
			}
			if(callstate==callok)
			{callstate=callwait;
				break;
			}
		}*/
	}

	private class PhoneCallListener extends PhoneStateListener {

		private static final String LOG_TAG = "kk";
		private boolean isPhoneCalling = false;

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			incomm=incomingNumber;
			/*
			for (String s : CallNumber.values) {
			
				
				Log.i(LOG_TAG, "RINGING, number: " + s+ " " + incomingNumber);
				if(s.contains(incomingNumber))
				{
					callstate=callterminate;
					break;
				}
			}
			if(callstate==callwait)
				callstate=callok;*/
//			Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
//			if (TelephonyManager.CALL_STATE_RINGING == state) {
//				// phone ringing
//				Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
//			}
//
//			if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
//				// active
//				Log.i(LOG_TAG, "OFFHOOK");
//
//				isPhoneCalling = true;
//			}

		}
	}
}