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

import com.lilakhelait.kishor.helper.GetNetworkStatus;
import com.lilakhelait.kishor.helper.MyFile;
import com.lilakhelait.kishor.resource.Imageurl;

public class Adderparser extends Activity {
    /** Called when the activity is first created. */
	public String url="https://www.facebook.com/GoTMemes/photos_stream";
	public Context context=null;
	public static String datas="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     context=this;
       
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
        								 Imageurl.newyearsvalues.add(h[0]);
        							output.add(h[0]);
        							Log.d("urls", h[0]);
        							}
                       		 }
                       		 else
                       			 i=1;
        					}


                }
                 catch(ArrayIndexOutOfBoundsException e)
                 {
                	 Adderparser.datas="Currently service unavailble";
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
            MyFile f = new MyFile(context);
          
        	 f.writeToSD("");
            	
           
        finish();    
        }
        
        
    }
}