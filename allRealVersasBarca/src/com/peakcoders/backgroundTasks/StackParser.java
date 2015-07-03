package com.peakcoders.backgroundTasks;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.lilait.realversasbarca.MainActivity;
import com.lilakhelait.kishor.helper.GetNetworkStatus;
import com.lilakhelait.kishor.helper.MyFile;
import com.lilakhelait.kishor.resource.Imageurl;

public class StackParser extends Activity {
    /** Called when the activity is first created. */
	public String url="http://www.diffen.com/difference/LG_Lucid_vs_ZTE_Fury";
	public Context context=null;
	public static String datas="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     context=this;
     String safeUrl = null;
	try {
		safeUrl = URLEncoder.encode(Imageurl.url, "UTF-8");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     url=  Imageurl.url;
				 if(GetNetworkStatus.isNetworkAvailable(context))
			       	{
					 
			           pd = ProgressDialog.show(StackParser.this, "Please wait, it will take up to two minute, please let jervis setup, Working...", "request to server", true, false);
			           new ParseSite().execute(urlmaker(url));
			       	}
				 else
				 {finish();
					 Toast.makeText(context, "Need a working Internet Connection for new images", Toast.LENGTH_LONG).show();
				 }
		
    }

    public String urlmaker(String data)
    {
    	String Data = data.replace("  "," ");
        Data = data.replace(" ","+");
    //	Data = url+Data;
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
                
                String[] pagesall=page.split("<h2 class=\"chartHeading\">Comparison chart</h2>");
                
               Imageurl.values=pagesall[1];
          // 	Log.d("ddf","1"+Imageurl.values );
          
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

       // 	Log.d("ddf","2"+Imageurl.values );
            pd.dismiss();
          /*  MyFile f = new MyFile(context);
           Log.d("ddf",Imageurl.values );
            	 f.writeToSD("");*/
            startActivity(new Intent(context, MainActivity.class));
        finish();    
        }
        
        
    }
}