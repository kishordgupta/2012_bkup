package com.codenginebd.mobilemediacms;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import common.BaseActivity;
import common.Constants;
import common.Constants.Actions;

import sqlitedb.SQLiteWraper;
import utils.AsyncTaskLoader;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends BaseActivity {

	String fullName = "";
	String surName = "";
	String idPassport = "";
	String mobileNumber = "";
	String password = "";
	boolean isCheckedAggreement = false;
	
	SQLiteWraper sqlitedb;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        sqlitedb = new SQLiteWraper(getApplicationContext());
        Initialize();
    }
    
    private String GetTextFromEditText(int id)
    {
    	return ((EditText)findViewById(id)).getText().toString();
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
    
    private void GrabText()
    {
    	fullName = GetTextFromEditText(R.id.textRegistrationName);
    	surName = GetTextFromEditText(R.id.textRegistrationSurName);
    	idPassport = GetTextFromEditText(R.id.textRegistrationIDPassport);
    	mobileNumber = GetTextFromEditText(R.id.textRegistrationMobile);
    	password = GetTextFromEditText(R.id.textRegistrationPassword);
    }
    
    private boolean CheckEmptyFields()
    {
    	return ((fullName.equals("")) || (surName.equals(""))  || (idPassport.equals("")) || (mobileNumber.equals("")) || (password.equals("")));
    }
    
    public void CallOnAsyncTaskFinish()
    {
    	Intent i = new Intent(getApplicationContext(),LoginActivity.class);
		startActivity(i);
    }
    
    public void CallOnPostAsyncTaskExecute(String merchantName,String vMerchantNo,long wallet,String userPin,String url)
    {
    	Intent i = new Intent(getApplicationContext(),UserSpecificPinActivity.class);
    	i.putExtra("vMerchantName", merchantName);
    	i.putExtra("vMerchantNo", vMerchantNo);
    	i.putExtra("wallet", wallet);
    	i.putExtra("userPin", userPin);
    	i.putExtra("url", url);
    	i.putExtra("monthlySale", "0");
    	startActivity(i);
    }
    
    private OnClickListener clickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View view) 
		{
			// TODO Auto-generated method stub
			try
			{
				GrabText();
				if(CheckEmptyFields())
				{
					ShowMessage("All fields are required.");
				}
				else if(!isCheckedAggreement)
				{
					ShowMessage("Accept User Agreement.");
				}
				else
				{
					ContentValues cValues = new ContentValues();
					cValues.put("AgentFullName", fullName);
					cValues.put("AgentSales", 0);
					cValues.put("AgenteWallet", 0);
					cValues.put("AgentIdPassport", idPassport);
					cValues.put("AgentNo", 0);
					cValues.putNull("AgentPhoto");
					cValues.put("AgentPword",password);
					cValues.put("AgentSurName",surName);
					cValues.put("AgentMobile",mobileNumber);
					long insertId = sqlitedb.Insert("Agent", "", cValues);
					
					cValues = new ContentValues();
					cValues.put("DeviceId", GetDeviceId());
					cValues.put("AgenteID", insertId);
					sqlitedb.Insert("DeviceInfo", "", cValues);
					
					Toast.makeText(getApplicationContext(), "Registration Successfull. Now enabling internet Connection", Toast.LENGTH_LONG).show();
					
					Map<String, String> _getParams = new HashMap<String, String>();
					_getParams.put("fullname", fullName);
					_getParams.put("agentsales", "0");
					_getParams.put("agentwallet", "0");
					_getParams.put("idpassport", idPassport);
					_getParams.put("AgentNo", "0");
					_getParams.put("password",password);
					_getParams.put("surname",surName);
					_getParams.put("mobile",mobileNumber);
					_getParams.put("op", "registration");
					_getParams.put("deviceId",GetDeviceId());
					
					
					/*_getParams.put("fullname", "ss");
					_getParams.put("agentsales", "0");
					_getParams.put("agentwallet", "0");
					_getParams.put("idpassport", "pp");
					_getParams.put("AgentNo", "0");
					_getParams.put("password","pw");
					_getParams.put("surname","sn");
					_getParams.put("mobile","mn");
					_getParams.put("op", "registration");
					_getParams.put("deviceId",GetDeviceId());*/
					
					AsyncTaskLoader asyncLoader = new AsyncTaskLoader(Constants.Net.URL, RegistrationActivity.this, -1, _getParams, Actions.REGISTRATION);
					asyncLoader.execute(new String[]{});
					
				}
			}catch(Exception exp)
			{
				Toast.makeText(getApplicationContext(), "Registration Unsuccessfull", Toast.LENGTH_LONG).show();
			}
			
			
		}
	};
    
    private void Initialize()
    {
    	((CheckBox)findViewById(R.id.termsAgreementCheckbox)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton button, boolean isChecked) 
			{
				// TODO Auto-generated method stub
				isCheckedAggreement = isChecked;
			}
		});
    	Button registerButton = (Button)findViewById(R.id.registerButton);
    	registerButton.setOnClickListener(clickListener);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_registration, menu);
        return true;
    }
}
