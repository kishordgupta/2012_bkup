package com.androidhive.jsonparsing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.kd.search.Searchbyname;
import com.kd.search.list.Helperdata;
import com.kd.search.list.KdList;
import com.kd.search.webscrapper.GetNetworkStatus;
import com.kd.search.webscrapper.StackParser;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class JSONParser {

	static InputStream is = null;
	public static JSONObject jObj = null;
	static String json = "";
	private ProgressDialog pd;
	private static String url = "http://api.androidhive.info/contacts/";
	static ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();

	// JSON Node names
	private static final String TAG_CONTACTS = "results";
	private static final String TAG_ID = "kind";
	private static final String TAG_NAME = "trackViewUrl";
	private static final String TAG_EMAIL = "previewUrl";
	private static final String TAG_ADDRESS = "artworkUrl60";
	private static final String TAG_GENDER = "longDescription";
	private static final String TAG_PHONE = "collectionId";
	private static final String TAG_PHONE_MOBILE = "collectionPric";
	private static final String TAG_PHONE_HOME = "country";
	private static final String TAG_PHONE_OFFICE = "currency";

	// contacts JSONArray
	JSONArray contacts = null;
	Context con = null;
	// constructor
	public JSONParser(Context context, String url) {
		  con=context;
		 if(GetNetworkStatus.isNetworkAvailable(context))
		       	{
				 
		           pd = ProgressDialog.show(context,"Trabajando ...,Por favor espere, tomará hasta dos minuto, Trabajo ... ","solicitando al servidor", true, false);
		           new ParseSite().execute(url);
		       	}
			 else
			 {
				 Toast.makeText(context, "Need a working Internet Connection for new images", Toast.LENGTH_LONG).show();
		 }
	}

		 private class ParseSite extends AsyncTask<String, JSONObject, JSONObject> {

	        protected JSONObject doInBackground(String... arg) {
	       
	        	try {
	    			// defaultHttpClient
	    			DefaultHttpClient httpClient = new DefaultHttpClient();
	    			HttpPost httpPost = new HttpPost(arg[0]);

	    			HttpResponse httpResponse = httpClient.execute(httpPost);
	    			HttpEntity httpEntity = httpResponse.getEntity();
	    			is = httpEntity.getContent();			

	    		} catch (UnsupportedEncodingException e) {
	    			e.printStackTrace();
	    		} catch (ClientProtocolException e) {
	    			e.printStackTrace();
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    		
	    		try {
	    			BufferedReader reader = new BufferedReader(new InputStreamReader(
	    					is, "iso-8859-1"), 8);
	    			StringBuilder sb = new StringBuilder();
	    			String line = null;
	    			while ((line = reader.readLine()) != null) {
	    				sb.append(line + "\n");
	    			}
	    			is.close();
	    			json = sb.toString();
	    		} catch (Exception e) {
	    			Log.e("Buffer Error", "Error converting result " + e.toString());
	    		}

	    		// try parse the string to a JSON object
	    		try {
	    			jObj = new JSONObject(json);
	    		} catch (JSONException e) {
	    			Log.e("JSON Parser", "Error parsing data " + e.toString());
	    		}

	    		// return JSON String
	    		return jObj;

	           
	        }

	        protected void onPostExecute(JSONObject output) {

	        	pd.dismiss();
	        	try {
					Thread.sleep(1500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	JSONObject json = output;//getJSONFromUrl(url);
         //   contactList = new ArrayList<HashMap<String, String>>();

	    		// Creating JSON Parser instance
	    		//JSONParser jParser = new JSONParser(this,url);
        //	Log.d("f",json+"");
	    		try {
	    			
	    			// Getting Array of Contacts
	    			contacts = json.getJSONArray(TAG_CONTACTS);
	    			
	    			// looping through All Contacts
	    			for(int i = 0; i < contacts.length(); i++){
	    				JSONObject c = contacts.getJSONObject(i);
	    				JSONArray a = c.names();
	    		//		Toast.makeText(con, a+""+a.length(), Toast.LENGTH_LONG).show();
	    		Log.d("f", a+"");
	    				// Storing each json item in variable
	    				String id;
	    				String address;
	    				String name;
	    				String email;
	    				String gender;
	    				
	    				try{
	    				 id = c.getString(TAG_ID);
	    				}
	    				catch(Exception e){
	    					id="";
	    				}try{
	    				name = c.getString(TAG_NAME);
	    				}
	    				catch(Exception e){
	    					name="";
	    				}try{
	    				email = c.getString(TAG_EMAIL);
	    				}
	    				catch(Exception e){
	    					email="";
	    				}try{
	    				address = c.getString(TAG_ADDRESS);
	    				}
	    				catch(Exception e){
	    					address="";
	    				}try{
	    				gender = c.getString(TAG_GENDER);
	    				}
	    				catch(Exception e){
	    					gender="";
	    				}
	    				// Phone number is agin JSON Object
	    			//JSONObject phone = c.getJSONObject(TAG_PHONE);
	    				String mobile = c.optString(TAG_PHONE_MOBILE);
	    				String home = c.optString(TAG_PHONE_HOME);
	    				String office = c.optString(TAG_PHONE_OFFICE);
	    				
	    				// creating new HashMap
	    				HashMap<String, String> map = new HashMap<String, String>();
	    				
	    				// adding each child node to HashMap key => value
	    				map.put(TAG_ID, id);
	    				map.put(TAG_NAME, name);
	    				map.put(TAG_EMAIL, email);
	    				map.put(TAG_PHONE_MOBILE,address);
              Toast.makeText(con,id+name+email, Toast.LENGTH_SHORT).show();
	    				// adding HashList to ArrayList
              JSONParser.contactList.add(map);
	    			}
	    		} catch (JSONException e) {
	    			e.printStackTrace();
	    		}
	    		
	    		con.startActivity(new Intent(con, AndroidJSONParsingActivity.class));
	    		
	        }
	        
	        
	 }
}
