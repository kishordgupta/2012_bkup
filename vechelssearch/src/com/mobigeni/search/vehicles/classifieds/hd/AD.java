package com.mobigeni.search.vehicles.classifieds.hd;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


public class AD extends Activity {
    /** Called when the activity is first created. */
	public static String url="http://app.oneclickoffice.com/client/getmainwindownew.php?username=testapp&action=login";
	public Context context=null;
	public static String datas="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     context=this;
       url=MainActivity.URL;
    URI u =   URI.create(url);
   try {
	URL ul=u.toURL();
	url=ul.toString();
} catch (MalformedURLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
   
				 if(GetNetworkStatus.isNetworkAvailable(context))
			       	{
					 
			     //     pd = ProgressDialog.show(StackParser.this,"wait ","wait please", true, false);
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

   
  
    private class ParseSite extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... arg) {
          String output = "";
            try 
            {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                httpClient.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "OneClickOffice App");
                HttpGet httpGet = new HttpGet(arg[0]);
                ResponseHandler<String> resHandler = new BasicResponseHandler();
                String page = httpClient.execute(httpGet, resHandler);
             //   Toast.makeText(context, ""+page, Toast.LENGTH_LONG).show();
               String s="";
                Log.d("sdf", page);
                String[] a = null;
                if(page.contains("\"")){
              a = page.split("\"");
              s=a[5];
              Log.d("sdf", a[5]+"");
              
              }
                String result = md5("123456!") + "app" + md5(s);
                String auth =md5(result);  //   String[] b =a[1].split("\"") ;
                output = auth;
          
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
        public String md5(final String s) {
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
        protected void onPostExecute(String output) {

        	
          //  pd.dismiss();
            
            /*String id="";
            //Imageurl.newyearsvalues=(ArrayList<String>) output;
            String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";

            Pattern compiledPattern = Pattern.compile(pattern);
            Matcher matcher = compiledPattern.matcher(output);

            if(matcher.find()){
            	id= matcher.group();
            }
            else
            {
            	
            }
           Toast.makeText(context,id, Toast.LENGTH_LONG).show();
           try{
               Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
               startActivity(intent);                 
               }catch (ActivityNotFoundException ex){
                   Intent intent=new Intent(Intent.ACTION_VIEW, 
                   Uri.parse("http://www.youtube.com/watch?v="+id));
                   startActivity(intent);
               }*/
          /* Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(output));
           
          Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + output));
            startActivity(intent);*/
        	
        	/*MainActivity.URL="http://app.oneclickoffice.com/client/getmainwindownew.php?auth="+output;
        	url=MainActivity.URL;
        	Intent i = new Intent(context,AD.class);
        	Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(MainActivity.URL));
		      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    		context.startActivity(i);*/
finish();    
        }
        
        
    }
}