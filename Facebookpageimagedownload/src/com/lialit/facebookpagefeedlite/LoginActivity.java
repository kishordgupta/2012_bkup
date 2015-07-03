package com.lialit.facebookpagefeedlite;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.util.ByteArrayBuffer;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lialit.facebookpagefeedlite.R;
import com.lilakhelait.kishor.helper.Dialoge;
import com.lilakhelait.kishor.helper.GetNetworkStatus;
import com.lilakhelait.kishor.resource.Imageurl;
import com.peakcoders.backgroundTasks.killerparser;


public class LoginActivity extends BaseActivity {

	Context con;
	protected String GetTextFromEditText(int id)
	{
		return ((EditText)findViewById(id)).getText().toString();
	}
	
	private boolean CheckEmptyFields()
	{
		return (GetTextFromEditText(R.id.textUserPin).equals(""));
	}
	
	 static int statsofdownload=0;
	   String stat="";
		public void DownloadFromUrl(String DownloadUrl, String fileName) {

		       try {
		               File root = android.os.Environment.getExternalStorageDirectory();               

		               File dir = new File (root.getAbsolutePath() + "/"+Imageurl.facebookpage);
		               if(dir.exists()==false) {
		                    dir.mkdirs();
		               }

		               URL url = new URL(DownloadUrl); //you can write here any link
		               File file = new File(dir, fileName);

		    

		               /* Open a connection to that URL. */
		               URLConnection ucon = url.openConnection();

		               /*
		                * Define InputStreams to read from the URLConnection.
		                */
		               InputStream is = ucon.getInputStream();
		               BufferedInputStream bis = new BufferedInputStream(is);

		               /*
		                * Read bytes to the Buffer until there is nothing more to read(-1).
		                */
		               ByteArrayBuffer baf = new ByteArrayBuffer(5000);
		               int current = 0;
		               while ((current = bis.read()) != -1) {
		                  baf.append((byte) current);
		               }


		               /* Convert the Bytes read to a String. */
		               FileOutputStream fos = new FileOutputStream(file);
		               fos.write(baf.toByteArray());
		               fos.flush();
		               fos.close();
		               LoginActivity.statsofdownload++;
		            
		               Log.d("DownloadManager","file://"+file.getAbsolutePath());

		       } catch (IOException e) {
		    	   Imageurl.pagestat="space";
		           Log.d("DownloadManager", "Error: " + e);
		       }

		    }
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==567)
		{
			if(Imageurl.pagestat.contains("success"))
			{try{
				 Imageurl.newyearsvalues.clear();
		           // MyFile f = new MyFile(context);
		        	  int imagea =0;
		        	  for (String string : Imageurl.output) {
		        			imagea++;
		        		final	int ind =imagea;
		        		final String ss=string;
		        		new Thread(new Runnable() {
		        			    public void run() {
		        			    	  DownloadFromUrl(ss,"IMAGE"+ind+".jpeg");
		        			    	  File root = android.os.Environment.getExternalStorageDirectory();               

		       		               
		        			    	   Imageurl.newyearsvalues.add("file://"+root.getAbsolutePath() + "/"+Imageurl.facebookpage+ "/"+"IMAGE"+ind+".jpeg");
		        			       
		        			      }
		        			    }).start();
		        		
		        		 
					}
		        	
		        	
		        	  ((Button)findViewById(R.id.gocategory)).setVisibility(View.VISIBLE);
						 Toast.makeText(this, "Downloaded and saved at"+"mnt/sdcard/"+Imageurl.facebookpage, Toast.LENGTH_LONG).show();
				// f.writeToSD("");
				
				
			}
			catch (Exception e) {
				// TODO: handle exception
				Toast.makeText(con, "Cant create folder to save need space in external sdcard", Toast.LENGTH_LONG).show();
			}
				
		}
				
			
			else if(Imageurl.pagestat.contains("space"))
			{
				Toast.makeText(con, "Cant create folder to save need space in external sdcard", Toast.LENGTH_LONG).show();
			}
			else
			{
				Toast.makeText(con, "not a public fb page, please check spelling or for capslock", Toast.LENGTH_LONG).show();
			}
		}
	}

	private OnClickListener clickListener = new View.OnClickListener() 
	{
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			int id = v.getId();
			if(id == R.id.gocategory)
			{
				Intent i = new Intent(getApplicationContext(),CategoryGridActivity.class);
				Toast.makeText(con, "wait download is progressing in... plz  click back and reclick that button few second letter to show all", Toast.LENGTH_LONG).show();
				startActivity(i);
			}
			if(id == R.id.submitButton)
			{
				if(CheckEmptyFields()){
					Toast.makeText(con, "add a fb page", Toast.LENGTH_LONG).show();
				}
				else{
					Imageurl.facebookpage=GetTextFromEditText(R.id.textUserPin);
					Imageurl.facebookpage=Imageurl.facebookpage.replace("", "");
					if(GetNetworkStatus.isNetworkAvailable(con))
			       	{
					 
Intent i = new Intent(getApplicationContext(),killerparser.class);
						
						startActivityForResult(i,567);
			       	}
				 else
				 {
					 Toast.makeText(con, "Need a working Internet Connection for new images", Toast.LENGTH_LONG).show();
				 }
						
				}
			}
		}
	};
	
	private void Initialize()
	{
		((Button)findViewById(R.id.submitButton)).setOnClickListener(clickListener);
		((Button)findViewById(R.id.gocategory)).setOnClickListener(clickListener);
		  ((Button)findViewById(R.id.gocategory)).setVisibility(View.GONE);
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        con=this;
        statsofdownload=0;
               setContentView(R.layout.activity_user_specific_pin);
      
    //    if(sqlitedb.CheckIfRegistered(GetDeviceId()))
		{
			((Button)findViewById(R.id.submitButton)).setEnabled(true);
			((Button)findViewById(R.id.submitButton)).setTextColor(Color.LTGRAY);
		}
        Initialize();
    
    }

    
}