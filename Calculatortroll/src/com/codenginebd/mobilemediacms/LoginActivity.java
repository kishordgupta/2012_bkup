package com.codenginebd.mobilemediacms;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import sqlitedb.SQLiteWraper;
import utils.AsyncTaskLoader;
import utils.ServiceProvider;
import common.BaseActivity;

import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

	SQLiteWraper sqlitedb;
	
	protected String GetTextFromEditText(int id)
	{
		return ((EditText)findViewById(id)).getText().toString();
	}
	
	private boolean CheckEmptyFields()
	{
		return (GetTextFromEditText(R.id.textUserName).equals("") || GetTextFromEditText(R.id.textPassword).equals(""));
	}
	
	private String GetDeviceId()
	{
		final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(getApplicationContext().TELEPHONY_SERVICE);

	    final String tmDevice, tmSerial, androidId;
	    tmDevice = "" + tm.getDeviceId();
	    tmSerial = "" + tm.getSimSerialNumber();
	    androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

	    UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
	    String deviceId = deviceUuid.toString();
	    return deviceId;
	}
	
	private OnClickListener clickListener = new View.OnClickListener() 
	{
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			if(id == R.id.loginButton)
			{
				Log.d("Clicked", ""+CheckEmptyFields());
				if(CheckEmptyFields())
				{
					ShowMessage("Username or Password should not be empty.");
				}
				else
				{
					/*Map<String,String> nameValuePairs = new HashMap<String, String>();
					nameValuePairs.put("username", GetTextFromEditText(R.id.textUserName));
					nameValuePairs.put("password", GetTextFromEditText(R.id.textPassword));
					AscyncTaskLoader loader = new AscyncTaskLoader("http://www.codenginebd.com/demo/service.php",MainActivity.this,R.id.textUserName,nameValuePairs);
					loader.execute(new String[]{""});*/
					/*ContentValues cValues = new ContentValues();
			    	cValues.put("AgentSales", 0);
			    	cValues.put("AgenteWallet", 0);
			    	cValues.put("AgentEmail", "codenginebd@gmail.com");
			    	cValues.put("AgentNo", 1);
			    	cValues.putNull("AgentPhoto");
			    	cValues.put("AgentPword", "123");
			    	cValues.put("AgentName", "me");
					sqlitedb.Insert("Agent", "", cValues);*/
					if(sqlitedb.CheckLogin(GetTextFromEditText(R.id.textUserName), GetTextFromEditText(R.id.textPassword)))
					{
						ShowMessage("Log In Successfull!");
						finish();
						Intent i = new Intent(getApplicationContext(),UserSpecificPinActivity.class);
						i.putExtra("marchantName", "X Ahmed");
						i.putExtra("vMarchantNo", "V123456");
						i.putExtra("eWalletValue", "100");
						i.putExtra("monthlySale", "0");
						startActivity(i);
					}
					else
					{
						ShowMessage("Login Failed.");
					}
					Log.d("Device Id: ", GetDeviceId());
				}
			}
			else if(id == R.id.signupButton)
			{
				finish();
				Intent i = new Intent(getApplicationContext(),RegistrationActivity.class);
				startActivity(i);
			}
		}
	};
	
	private void Initialize()
	{
		((Button)findViewById(R.id.loginButton)).setOnClickListener(clickListener);
		((Button)findViewById(R.id.signupButton)).setOnClickListener(clickListener);
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sqlitedb = new SQLiteWraper(getApplicationContext());
        setContentView(R.layout.activity_main);
        if(sqlitedb.CheckIfRegistered(GetDeviceId()))
		{
			((Button)findViewById(R.id.signupButton)).setEnabled(false);
			((Button)findViewById(R.id.signupButton)).setTextColor(Color.LTGRAY);
		}
        Initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}