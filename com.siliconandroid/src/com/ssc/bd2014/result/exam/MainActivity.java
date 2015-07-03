package com.ssc.bd2014.result.exam;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.NameValuePair;


import android.support.v4.app.Fragment;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {
	private ProgressDialog progressDialog;
	private static WebView webview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		new APITask(this).execute();
	/*	if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}*/
	}



	/*@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			new APITask(this).execute();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}*/

	/**
	 * A placeholder fragment containing a simple view.
	 */
	/*public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			webview=	(WebView)rootView.findViewById(R.id.webView1);
			return rootView;
		}
	}*/
	
	private class APITask extends AsyncTask<Void, Void, String> 
	{
		private String RESULT = "";
		Context con=null;
		@Override
		protected void onPreExecute() 
		{
			progressDialog = ProgressDialog.show(con, "", "Loading...", true, false);
			
		}
       public APITask(Context c)
       {
    	   con=c;
       }
		@Override
		protected String doInBackground(Void... arg0) 
		{
			try 
			{
				if (InternetConnectivity.isConnectedToInternet(con)) 
				{
					String xml="";
					ArrayList<NameValuePair> nvp28 = APIFactory.get_buy_gift_list("sylhet","345356");
				    xml = SingleToneClass.getResponseFromServer(nvp28);
					
					
					return RESULT;
				} 
				return RESULT;
			} 
			catch (Exception ex) 
			{
				return RESULT;
			}
		}
		
		
		@Override
		protected void onPostExecute(String result) 
		{if (progressDialog.isShowing())
		{
			progressDialog.dismiss();
			progressDialog = null;
			Log.d("sdf", result);
			WebActivity.result=result;
			con.startActivity(new Intent(con, WebActivity.class));
		}}
	}

}
