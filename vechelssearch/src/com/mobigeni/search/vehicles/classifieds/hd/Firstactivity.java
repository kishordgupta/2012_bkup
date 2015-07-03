package com.mobigeni.search.vehicles.classifieds.hd;



import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.mobigeni.search.vehicles.classifieds.hd.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Firstactivity extends BaseActivity{
	
	static Context con;
	 public static final String PREFS_NAME = "MyPrefsFile";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		  setContentView(R.layout.splash);
		  setTitle("version code-1");
		con=this;
		
		  // start();   
	      
		Intent i = new Intent(con,StackParser.class);
		con.startActivity(i);    
	}
	
	public static final String md5(final String s) {
		try {
			// Create MD5 Hash
			MessageDigest digest = java.security.MessageDigest
					.getInstance("MD5");
			digest.update(s.getBytes());
			byte messageDigest[] = digest.digest();

			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++) {
				String h = Integer.toHexString(0xFF & messageDigest[i]);
				while (h.length() < 2)
					h = "0" + h;
				hexString.append(h);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	public static void start(){ new Thread(new Runnable() {
	    public void run() {
	    	Start();
	       
	       
	      }
	    }).start();
};

	protected static void Start() {
		// TODO Auto-generated method stub
		
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
        	
        	if(GetNetworkStatus.isNetworkAvailable(con)){
        		
        	Intent i = new Intent(con,StackParser.class);
		con.startActivity(i);
        	}
        	else
        	{
        		((Activity) con).runOnUiThread(new Runnable() {
        			  public void run() {
        			    Toast.makeText(((Activity) con), "Please, Provide working internet connection to run this app", Toast.LENGTH_SHORT).show();
        			  }
        			});
        		((Activity) con).finish();
        	}
        	
        
	}
}
