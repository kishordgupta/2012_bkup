package com.example.swipeuiforupclose;



import java.io.IOException;
import java.io.InputStream;

import com.atomix.kurowiz.supports.ConstantValues;
import com.atomix.kurowiz.supports.InternetConnectivity;
import com.atomix.kurowiz.supports.SingleToneClass;
import com.atomix.kurowiz.supports.Urlmaker;
import com.atomix.kurowiz.xmlparser.DomParser;

import dataprocess.XMLLoadAsync;

import backgroundTasks.MyNotification;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class Firstactivity extends Activity {

	static public Context con;
	public static final String PREFS_NAME = "MyPrefsFile";
	protected static final String XML_URL = "http://radioboxplayer.net/station_xml.php?code=";
	public static String facebook="";
	public static String twitter="";
	public static String web="";
	public static String admobid="";
	private static ProgressDialog progressDialog;
	public static ProgressDialog progressDialog_stat;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		con = this;
		final SharedPreferences prefs = this.getSharedPreferences(
				"com.example.app", Context.MODE_PRIVATE);
		new MyNotification(con);
		MainActivity.Radio_tag = prefs.getString("Radio_tag", "0");
		
		MainActivity.arrayList.clear();
		Urlmaker.urlmaker();
		new XMLLoadAsync().execute();
		new APITask(this).execute();
	
			player.DBHelper myDbHelper = new player.DBHelper(this);
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
			myDbHelper.close();
	
		

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}
	
	private static class APITask extends AsyncTask<Void, Void, String> 
	{
		private String RESULT = "OK";
		  public Context ca=null;
		@Override
		protected void onPreExecute() 
		{
			Log.d("f", "preexecute");
			progressDialog = ProgressDialog.show(getActivity(), "", "Loading...", true, false);
			ConstantValues.isBottomReached = false;
		}
		 private APITask(Context con)
	        {
	        	ca=con;
	        }
		private Context getActivity() {
			// TODO Auto-generated method stub
			
			return ca;
		}

		@Override
		protected String doInBackground(Void... arg0) 
		{
			try 
			{
				if (InternetConnectivity.isConnectedToInternet(getActivity())) 
				{
					InputStream xml;
					DomParser domParser = new DomParser();
					
					//ArrayList<NameValuePair> nvp28 = apiFactory.get_buy_gift_list(ConstantValues.FUNCTION_ID_28, "5", Integer.toString(pageId));
					xml = SingleToneClass.getInstance().getcateeFromServer();
					domParser.parseAPI28(xml);
					
	/*		ArrayList<NameValuePair> nvp31 = apiFactory.get_gift_ticket_info(ConstantValues.FUNCTION_ID_31,  DomParser.userInfo.getUserId());
					xml = SingleToneClass.getInstance().getResponseFromServer(nvp31);
					domParser.parseAPI31(xml);
	*/				
					return RESULT;
				} 
				else 
				{
					SingleToneClass.getInstance().openInternetSettingsActivity(getActivity());
					return RESULT;
				}
			} 
			catch (Exception ex) 
			{
				return RESULT;
			}
		}

		@Override
		protected void onPostExecute(String result) 
		{if(progressDialog!=null){
			if (progressDialog.isShowing())
			{
				progressDialog.dismiss();
				progressDialog = null;
			
				ConstantValues.isBottomReached = true;
			}
		}
		Firstactivity.con.startActivity(new Intent(Firstactivity.con, MainActivity.class));
		((Activity) Firstactivity.con).finish();
		
		//	linearLayout.removeAllViews();
		//	LayoutInflater inflater = LayoutInflater.from(getActivity());
		
			
		
			
		}
	}

}
