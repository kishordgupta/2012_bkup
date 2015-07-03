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
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.lilakhelait.kishor.helper.GetNetworkStatus;
import com.lilakhelait.kishor.helper.MyFile;
import com.lilakhelait.kishor.resource.Imageurl;

public class StackParser extends Activity {
    /** Called when the activity is first created. */
	public String url="http://www.phdcomics.com/gradfeed.php";
	public Context context=null;
	public static String datas="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     context=this;
       
				 if(GetNetworkStatus.isNetworkAvailable(context))
			       	{
					 
			           pd = ProgressDialog.show(StackParser.this, "Please wait, it will take up to two minute, please let jervis setup, Working...", "request to server", true, false);
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
                
                String[] pagesall=page.split("<div class=\"span3\">");
                
               
           	 for (String pagea : pagesall) {
           		 if(pagea.contains("http://www.phdcomics.com/comics/archive/"))
           		 {
           		 String[] pages=pagea.split("http://www.phdcomics.com/comics/archive/");
                 try{
                	 for (String string : pages) {
                		 if(string.contains("s.gif")){
 						String[] h = string.split("s.gif") ;
 							String yrl ="http://www.phdcomics.com/comics/archive/"+h[0]+"s.gif";
 							output.add(yrl);
 							  Imageurl.newyearsvalues.add(yrl);
 							Log.d("urls", yrl);
 							}
                 }

                 }
                 catch(ArrayIndexOutOfBoundsException e)
                 {
                	 StackParser.datas="Currently service unavailble";
                 }
           		 }
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
            MyFile f = new MyFile(context);
           
            	 f.writeToSD("");
			
        finish();    
        }
        
        
    }
}