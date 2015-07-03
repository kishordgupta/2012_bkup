package com.lilakhelait.kishor.helper.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.lilait.movie.story.spoiler.Mainwindowactivity;
import com.lilait.movie.story.spoiler.R;
import com.lilakhelait.kishor.helper.GetNetworkStatus;
import com.lilakhelait.kishor.listview.Happynewyearlist;
import com.lilakhelait.kishor.resource.Helper;
import com.lilakhelait.kishor.resource.Resource;
import com.lilakhelait.kishor.resource.Wish;
import com.tmm.android.chuck.db.DBHelper;


public class StackParser extends Activity {
    /** Called when the activity is first created. */
	public String url="http://www.moviepooper.com/11/5196despicable2.html";
	public Context context=null;
	public static String datas="";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     context=this;
     DBHelper myDbHelper = new DBHelper(this);
		try {
			myDbHelper.createDataBase();
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}
		try {
			myDbHelper.openDataBase();
		}catch(SQLException sqle){
			throw sqle;
		}
		
	 
		
		 Happynewyearlist.newyearsvalues=Helper.helperarchiv1e(Resource.getstringfromfiles(R.raw.movilist, context));
  for (Wish w:Happynewyearlist.newyearsvalues)
  {
	  myDbHelper.insert(w);
  }myDbHelper.close();
  urlmaker("");
  finish();
		 //startActivityForResult(new Intent(context,Mainwindowactivity.class),123);
				 if(GetNetworkStatus.isNetworkAvailable(context))
			       	{
					 
			       //    pd = ProgressDialog.show(StackParser.this, "Please wait, it will take up to 10-20sec Working...", "request to server", true, false);
			    //       new ParseSite().execute(urlmaker(datas));
			       	}
				 else
				 {
					  AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
								this);
		  	    		alertDialogBuilder.setTitle("Network Unavailable to Update Showtime");
		  	   		 
							// set dialog message
							alertDialogBuilder
								.setMessage("We need a working internet Connection to run the app")
								.setCancelable(false)
								.setPositiveButton("Try again",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
										// if this button is clicked, close
									
								  
								      Intent intent = getIntent();
									   finish();
									   startActivity(intent);
									
									}
								  })
								.setNegativeButton("Exit",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
								
									finish();
									}
								});
				 
								// create alert dialog
								AlertDialog alertDialog = alertDialogBuilder.create();
				 
								// show it
								alertDialog.show(); }
		
    }

    public String urlmaker(String data)
    {
    	String Data = data.replace("  "," ");
        Data = data.replace(" ","+");
    	Data = url+Data;
    
    	
    	File sd = Environment.getExternalStorageDirectory();

    	File src = new File("/data/data/com.lilait.movie.story.spoiler/databases/questionsDb");

    	File dest = new File(sd +  "/" +"dbmovieold");

    	      try 
    	      {
    	          if (Environment.getExternalStorageDirectory().canWrite()) 
    	          {                 
    	              if (src.exists()) 
    	              {
    	                  FileChannel srcdb = new FileInputStream(src).getChannel();
    	                  FileChannel dstdb = new FileOutputStream(dest).getChannel();
    	                  dstdb.transferFrom(srcdb, 0, srcdb.size());
    	                  srcdb.close();
    	                  dstdb.close();  
    	                //  flag=0; 

    	              }
    	              else
    	              {
    	                  Toast.makeText(getApplicationContext(), "ERROR: File not found in Database " , Toast.LENGTH_LONG).show();                    
    	              }
    	          }
    	          else
    	          {
    	              Toast.makeText(getApplicationContext(), "ERROR: Cannot write to file" , Toast.LENGTH_LONG).show();
    	          }
    	      }
    	      catch (Exception e) 
    	      {
    	          Log.e("Movedb", "Error in Copying" + e.toString());

    	      }
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
                Log.d("hjkhj", page);
      	      editor.putString("resource", page);
      	      editor.commit();
      	  /*    
      	    if(pd.isShowing())
            	pd.dismiss();*/
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
        	
        	finish();
       startActivityForResult(new Intent(context,Mainwindowactivity.class),123);
   
        }
        
        
        
        
    }
}