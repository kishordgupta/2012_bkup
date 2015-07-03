package com.peakcoders.backgroundTasks;

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
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.lilait.anime.video.music.bleach.naruto.onepiece.song.opening.R;
import com.lilakhelait.kishor.helper.GetNetworkStatus;
import com.lilakhelait.kishor.helper.MyFile;
import com.lilakhelait.kishor.resource.Imageurl;


public class Adderparser extends Activity {
    /** Called when the activity is first created. */
	public String url="http://www.lilait.com//app/latestfootballgoal/feed.txt";
	
	public static Context context=null;
	public static String datas="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     context=this;
    
         url=getResources().getString(R.string.feeds);

  
     
		 if(GetNetworkStatus.isNetworkAvailable(context))
			       	{
					 
			           pd = ProgressDialog.show(Adderparser.this, "Trabajando ...", "Por favor espere, tomará hasta dos minuto, Trabajo ...", true, false);
			           new ParseSite().execute(urlmaker(datas));
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
                String page =  httpClient.execute(httpGet, resHandler);
             
                String[] pages=page.split("<entry><id>");
                Log.d("pagenumber",pages.length+"" );
                int i=0;
                       
                       	 for (String string : pages) {
                       		 if(i!=0)
                       		 { 
                       			 
                       			 String[] h = string.split("videos/") ;
            						
           							{
           					  String[] t=h[1].split("</id>");
           					  Imageurl.youtubeurl.add(t[0]);
           					  String im="https://i.ytimg.com/vi/"+t[0]+"/0.jpg";
           					  Imageurl.newyearsvalues.add(im);
           					  Log.d("yy",t[0] );
           					}
           							h=null;
           							
           						 h = string.split("<title type='text'>") ;
           							if(h[1].contains("</title>"))
           									{
           							  String[] t=h[1].split("</title>");
           							  Imageurl.title.add(t[0]);
           							  Log.d("sf",t[0] );
           					
           							}
           							
           							
           						
                       		
                       		 }
                       		 else
                       			 i=1;
           					}
          
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
         //Imageurl.newyearsvalues=(ArrayList<String>) output;
           // MyFile f = new MyFile(context);
          
        	// f.writeToSD("");
             	
           
        finish();    
        }
        
        
    }
}