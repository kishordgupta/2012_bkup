package com.lilakhelait.kishor.helper.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.lilait.movie.story.spoiler.Mainwindowactivity;
import com.lilakhelait.kishor.helper.GetNetworkStatus;


public class NewParser extends Activity {
    /** Called when the activity is first created. */
	public String url="http://www.themoviespoiler.com/Pages/Spoilers.html";
	public Context context=null;
	public static String datas="";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     context=this;
    
				 if(GetNetworkStatus.isNetworkAvailable(context))
			       	{
					 
			           pd = ProgressDialog.show(NewParser.this, "Please wait, it will take up to 10-20sec Working...", "request to server", true, false);
			           new ParseSite().execute(urlmaker(datas));
			       	}
				 else
				 {finish();
					 Toast.makeText(context, "Need a working Internet Connection", Toast.LENGTH_LONG).show();
				 }
		
    }

    public String urlmaker(String data)
    {
    	String Data = data.replace("  "," ");
        Data = data.replace(" ","+");
    	Data = url+Data;
    	return Data;
    	
    }
    private ProgressDialog pd;

   
  
    private class ParseSite extends AsyncTask<String, Void, List<String>> {

        protected List<String> doInBackground(String... arg) {
            List<String> output = new ArrayList<String>();
            try 
            {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(arg[0]);
                ResponseHandler<String> resHandler = new BasicResponseHandler();
                String page = httpClient.execute(httpGet, resHandler);
                SharedPreferences settings = context.getSharedPreferences(Mainwindowactivity.PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
      	      editor.putString("resource", page);
      	      editor.commit();
          
            }
            catch (ClientProtocolException e)
            {
                e.printStackTrace();
             
            }
            catch (IOException e)
            {
                e.printStackTrace();
               
            }


            return output;
        }

        protected void onPostExecute(List<String> output) {

        	
        	pd.dismiss();
        finish();    
        }
        
        
    }
}