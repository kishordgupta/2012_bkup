package utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;

import android.util.Log;

public class ServiceProvider 
{
	private HttpClient httpClient;
	private HttpPost httpPost;
	private HttpResponse response;
	
	public ServiceProvider()
	{
		httpClient = new DefaultHttpClient();
	}
	
	public JSONArray Insert(String url,List<NameValuePair> nameValuePairs)
	{
		String text = null;
		StringBuilder sb = null;
		JSONArray jArray = null;
		InputStream is = null;
		BufferedReader reader = null;
		try {
				httpPost = new HttpPost(url);
				if(nameValuePairs != null)
					httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				response = httpClient.execute(httpPost);
				is = response.getEntity().getContent();
				reader = new BufferedReader(new InputStreamReader(is));
				sb = new StringBuilder();
				String line="";
				while ((line = reader.readLine()) != null) 
				{
					sb.append(line);
				}
				text = sb.toString();
				Log.d("The Response is: ", text);
				jArray = new JSONArray(text);

		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			Log.d("The Response is: ", text);
			e.printStackTrace();
		}
		finally
		{
			try {
				is.close();
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
			}
			
		}
		return jArray;
	}
	
	public List Get(String url,int id)
	{
		return null;
	}
	
	public List GetAll(String url)
	{
		return null;
	}
	
	public int Count(String url)
	{
		return -1;
	}
	
	public boolean Delete(String url,int id)
	{
		return false;
	}
	
	public boolean DeleteAll()
	{
		return false;
	}
	
	public boolean Update(String url,int id)
	{
		return false;
	}
}
