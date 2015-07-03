package com.ssc.bd2014.result.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;

public class SingleToneClass {

	public static SingleToneClass instance;
	
	public static  String SERVER_URL = "http://archive.educationboard.gov.bd/index.php";

	
	
	
	public static String getResponseFromServer(ArrayList<NameValuePair> postData) throws Exception 
	{
		HttpClient httpClient = new DefaultHttpClient();
		
		HttpPost postRequest = new HttpPost(SERVER_URL);
		postRequest.setEntity(new UrlEncodedFormEntity(postData));
		
		HttpResponse httpResponse = httpClient.execute(postRequest);
		int statusCode = httpResponse.getStatusLine().getStatusCode();
        String s =inputStreamToString(httpResponse.getEntity().getContent()).toString();//Response.getEntity().getContent().toString();
      
		/*if(statusCode == HttpStatus.SC_OK) 
		{
			HttpEntity entity = httpResponse.getEntity();
			
			if(entity != null) 
			{
				return entity.getContent().toString();
			}
		}*/
	
		return s;
	}
	
	private static StringBuilder inputStreamToString(InputStream is) {
	    String line = "";
	    StringBuilder total = new StringBuilder();

	    // Wrap a BufferedReader around the InputStream
	    BufferedReader rd = new BufferedReader(new InputStreamReader(is));

	    // Read response until the end
	    try {
	        while ((line = rd.readLine()) != null) { 
	            total.append(line); 
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Return full string
	    return total;
	}

	
}
