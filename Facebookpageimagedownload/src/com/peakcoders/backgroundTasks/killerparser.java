package com.peakcoders.backgroundTasks;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.lilakhelait.kishor.helper.GetNetworkStatus;
import com.lilakhelait.kishor.helper.MyFile;
import com.lilakhelait.kishor.resource.Imageurl;

public class killerparser extends Activity {
    /** Called when the activity is first created. */
	public String url="https://www.facebook.com/"+Imageurl.facebookpage+"/photos_stream";//"https://graph.facebook.com/"+Imageurl.facebookpage+"/photos?type=uploaded&limit="+Imageurl.limt;
	public Context context=null;
	public static String datas="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
    //	 url="https://graph.facebook.com/"+Imageurl.facebookpage+"/photos?type=uploaded&limit="+Imageurl.limt;
        super.onCreate(savedInstanceState);
     context=this;
     
     String url="https://www.facebook.com/"+Imageurl.facebookpage+"/photos_stream";
     
			Imageurl.pagestat="error"	;
			       	{
					 
			           pd = ProgressDialog.show(killerparser.this, "Working...2-3 minutes max dont press any key now", "request to server.........", true, false);
			           new ParseSite().execute(urlmaker(datas));
			       	}
				
		
    }

    public String urlmaker(String data)
    {
    	String Data = url.replace("  "," ");
        Data = data.replace(" ","+");
    	Data =  url.replace("  "," ");;
    	return Data;
    	
    }
    private ProgressDialog pd;
    int statsofdownload=0;
   String stat="";
	
    private class ParseSite extends AsyncTask<String, Void, List<String>> {
    
        protected List<String> doInBackground(String... arg) {
            List<String> output = new ArrayList<String>();
            try 
            {	Log.d("urls", arg[0]);
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(arg[0]);
                ResponseHandler<String> resHandler = new BasicResponseHandler();
                String page = httpClient.execute(httpGet, resHandler);
                
                String[] pages=page.split("data-starred-src=\"");
                int i=0;
                        try{
                       	 for (String string : pages) {
                       		 if(i!=0)
                       		 { 
        						String[] h = string.split("data-non-starred-src") ;
        							if(h[0].contains(".jpg")){
        								h[0] =h[0].replace("\"", "");
        								h[0] =h[0].replace(" ", "");
        							//	h[0] =h[0].replace("_s", "_n");
        						//		h[0] =h[0].replace("http://photos", "http://sphotos");*/
        							output.add(h[0]);
        							Log.d("urls", h[0]);
        							}
                       		 }
                       		 else
                       			 i=1;
        					}

                 }
                 catch(ArrayIndexOutOfBoundsException e)
                 {stat="error";
                	 killerparser.datas="Currently service unavailble";
                 }
                
          
            }
            catch (ClientProtocolException e)
            {
                e.printStackTrace();
                stat="error";
            }
            catch (IOException e)
            {stat="error";
                e.printStackTrace();
               
            }


            return output;
        }

        protected void onPostExecute(List<String> output) {

        
          
          if(stat.contains("error"))
          {
        	  Imageurl.pagestat="error";
        	  
          }
          else
          {  Imageurl.output=(ArrayList<String>) output;
        	 
            	 Imageurl.pagestat="success";
          }

      	pd.dismiss();
        finish();    
        }
        
        
    }
}