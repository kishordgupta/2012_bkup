package com.kd.search.webscrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.kd.search.Searchbyname;
import com.kd.search.list.Helperdata;
import com.kd.search.list.KdList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class StackParser extends Activity {
    /** Called when the activity is first created. */
	public String url="http://www.mediahost.in/index.php?a="+Helperdata.type+"&q="+Helperdata.SearchText.replace(" ","+" );
	public Context context=null;
	public static String datas="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     context=this;
       
			 if(GetNetworkStatus.isNetworkAvailable(context))
			       	{
					 
			           pd = ProgressDialog.show(StackParser.this,"Trabajando ...,Por favor espere, tomará hasta dos minuto, Trabajo ... ","solicitando al servidor", true, false);
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
                String[] pages=null;
                if(Helperdata.type==Helperdata.type_web)
               pages=page.split("<div class=\"result_c\">");
                if(Helperdata.type==Helperdata.type_news)
                    pages=page.split("<div class=\"result_c\">");
                if(Helperdata.type==Helperdata.type_images)
                	pages=	page.split("<div class=\"thumbnail\">");
         int i=0;
                 try{
                	 KdList.Searchlist.clear();
                	 for (String string : pages) {
                		 if(i!=0){
 								KdList.Searchlist.add("<div>"+string);
                		 }
                		 else
                			 i=1;
 							//Log.d("sf", string);
 					}
                	 Log.d("sfsdfsdf", pages.length+"");
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
            startActivity(new Intent(context,Searchbyname.class));
            //Imageurl.newyearsvalues=(ArrayList<String>) output;
         
        finish();    
        }
        
        
    }
}