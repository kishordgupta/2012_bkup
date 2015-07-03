package com.kd.phonecall;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.android.internal.telephony.ITelephony;
import com.lilakhelait.kishor.model.CallNumber;
import com.lilakhelait.kishor.resource.Wish;
import com.tmm.android.chuck.db.DBHelper;
import com.tmm.android.chuck.db.MyFile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;

import android.media.AudioManager;
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
public static int call=0;

	public void data(Context cont)
	{
		
		 CallNumber.values.clear();
		 DBHelper myDbHelper = new DBHelper(cont);
			try {
				myDbHelper.createDataBase();
			} catch (IOException ioe) {
				throw new Error("Unable to create database");
			}
			try {
				myDbHelper.openDataBase();
			}catch(SQLException sqle){
				throw sqle;
			}
			CallNumber.values= myDbHelper.getQuestionSet();
			myDbHelper.close();
			
		      MyFile f = new MyFile(cont);
	        String s=	 f.readFromSD();
			String[] lines = s.split("\n");
			for (String string : lines) {
				Wish w = new Wish();
				w.setTitle("unknown");
				w.setWishtext(string);
				   CallNumber.values.add(w);
			}
	}
	public static String incomm="";
	
	@Override
	public void onReceive(Context context, Intent arg1) {
		
		TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
	    PhoneCallStateListener customPhoneListener = new PhoneCallStateListener(context);
	    telephony.listen(customPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);

		/*callstate = callwait;
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
		
		for (Wish s : CallNumber.values) {
			
			
		//	Log.i(LOG_TAG, "RINGING, number: " + s+ " " + incomingNumber);
			if(s.getWishtext().contains(incomm))
			{telephonyService.endCall();
				callstate=callterminate;
				break;
			}
		}
		if(callstate==callwait)
			callstate=callok;*/
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

	public class PhoneCallStateListener extends PhoneStateListener {    

		private Context context;
		public PhoneCallStateListener(Context context){
		    this.context = context;
		    data(context);
		}


		@Override
		public void onCallStateChanged(int state, String incomingNumber) {  
		   // SharedPreferences prefs=PreferenceManager.getDefaultSharedPreferences(context);

		    switch (state) {

		        case TelephonyManager.CALL_STATE_RINGING:       

		              //String block_number = prefs.getString("block_number", null);
		            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE); 
		            //Turn ON the mute
		            audioManager.setStreamMute(AudioManager.STREAM_RING, true);                 
		            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		            try {
		              //  Toast.makeText(context, "incoming number "+incomingNumber, Toast.LENGTH_LONG).show();
		                Class clazz = Class.forName(telephonyManager.getClass().getName());
		                Method method = clazz.getDeclaredMethod("getITelephony");
		                method.setAccessible(true);
		                ITelephony telephonyService = (ITelephony) method.invoke(telephonyManager);     
		                //Checking incoming call number
		                //System.out.println("Call "+block_number);
		                for (Wish s : CallNumber.values) {
		                if (incomingNumber.toLowerCase().contains(s.getWishtext().toLowerCase())) {
		                    //telephonyService.silenceRinger();//Security exception problem
		                     telephonyService = (ITelephony) method.invoke(telephonyManager);
		                    // telephonyService.silenceRinger();
		                     if(call!=1)
		                    telephonyService.endCall();
		                     else
		                    	 Toast.makeText(context, "This number is in blocked list accept anyway",Toast.LENGTH_LONG).show();
		                    break;
		                }}
		            } catch (Exception e) {
		                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
		            }
		            //Turn OFF the mute     
		            audioManager.setStreamMute(AudioManager.STREAM_RING, false);
		            break;
		        case PhoneStateListener.LISTEN_CALL_STATE:

		    }
		    super.onCallStateChanged(state, incomingNumber);
		}}
}