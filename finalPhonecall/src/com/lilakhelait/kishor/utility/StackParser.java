package com.lilakhelait.kishor.utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.lilakhelait.kishor.helper.GetNetworkStatus;
import com.lilakhelait.kishor.model.CallNumber;
import com.lilakhelait.kishor.resource.Wish;
import com.tmm.android.chuck.db.MyFile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


public class StackParser extends Activity {
    /** Called when the activity is first created. */
	public String url="http://project.iqrasys.se/communify/";
	public Context context=null;
	public static String datas="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     context=this;
       
				 if(GetNetworkStatus.isNetworkAvailable(context))
			       	{
					 
			           pd = ProgressDialog.show(StackParser.this,"Var god vänta  ","Databasen uppdateras", true, false);
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
                
                String[] pages=page.split("<number>");
                
                MyFile.values.clear();
                int i=0;
                try{
                	 for (String string : pages) {
                		 i++;
                		 if(i>1){
 						String[] h = string.split("</number>") ;
 							if(h[0].length()>5){
 								
 								  MyFile.values.add(h[0]);
 							Log.d("urls", h[0]);
 							}
                		 }
 					}

                 }
                 catch(ArrayIndexOutOfBoundsException e)
                 {
                	 StackParser.datas="Currently service unavailble";
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
            	 CallNumber.valuesdb.clear();
              
               
                 String s=	 f.readFromSD();
         		String[] lines = s.split("\n");
         		for (String string : lines) {
         			Wish w = new Wish();
         			w.setTitle("Säljare");
         			w.setWishtext(string);
         			   CallNumber.valuesdb.add(w);
         		}
        finish();    
        }
        
        
    }
}