package com.lilakhelait.kishor.helper.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Html.TagHandler;
import android.util.Log;
import android.widget.Toast;

import com.lilait.movie.story.spoiler.Mainwindowactivity;
import com.lilakhelait.kishor.helper.GetNetworkStatus;


public class ReviewParser extends Activity {
    /** Called when the activity is first created. */
	public static String url="";
	public static String name="";
	public Context context=null;
	public static String datas="";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     context=this;
    
				 if(GetNetworkStatus.isNetworkAvailable(context))
			       	{
					 
			           pd = ProgressDialog.show(ReviewParser.this, "Please wait, it will take up to 10-20sec Working...", "request to server", true, false);
			           new ParseSite().execute(url);
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
              String page = Html.fromHtml(httpClient.execute(httpGet, resHandler)).toString();
             //   String page =httpClient.execute(httpGet, resHandler).toString();
                char[] c=page.toCharArray();
                char[] b=new char[c.length];
                String s="";
                boolean go=true;
                for (char d : c) {
                	
					if(d!='<' && go==true)
					{
						s=s+d;
					}
					if(d=='<')
					{
						go=false;
					}
					if(d=='>')
					{
						go=true;
					}
				}
              
               
                String[] t= s.split("SPOILER ARCHIVE");
             //   String h=null;
                if(t.length>1)
                { datas= t[1];}//.split("You can send in your spoiler to other movies by going");
                else 
                	 datas=page;/* if(h.length>1){
                String as=h[0].replace("   ","");
                
                if(as.contains(name))
                	{String a[] =as.split(name);
                	if(a.length>0)
                 datas = a[1];
                	else
                     	datas=as;
                	}
                if(as.contains(name.toLowerCase()))
            	{String a[] =as.split(name);
            	if(a.length>0)
             datas = a[1];
            	else
                 	datas=as;
            	}
                if(as.contains(name.toUpperCase()))
            	{String a[] =as.split(name);
            	if(a.length>0)
             datas = a[1];
            	 else
                 	datas=as;
            	}
                else
                	datas=as;
                
                }
            }else
            	datas="Review Not Found";
                */
               datas = datas.replace("  ", "");
            }
            catch (ClientProtocolException e)
            {
                e.printStackTrace();
             
            }
            catch (IOException e)
            {
                e.printStackTrace();
               
            }
           catch(ArrayIndexOutOfBoundsException e)
           {

           	datas="Review Not Found";
           }

            return output;
        }

        protected void onPostExecute(List<String> output) {

        	pd.dismiss();
			
        finish();    
        }
        
        
    }
}