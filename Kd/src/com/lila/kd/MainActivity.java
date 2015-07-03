package com.lila.kd;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.util.ByteArrayBuffer;

import com.harrison.lee.twitpic4j.TwitPic;
import com.harrison.lee.twitpic4j.TwitPicResponse;
import com.harrison.lee.twitpic4j.exception.TwitPicException;


import android.os.Bundle;
import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		 

		       
		File picture = DownloadFromUrl("d","as.png");

		// Create TwitPic object and allocate TwitPicResponse object
		TwitPic tpRequest = new TwitPic("kishordgupta", "iamkishoranditismypassword");
		TwitPicResponse tpResponse = null;

		// Make request and handle exceptions                           
		try {
		        tpResponse = tpRequest.uploadAndPost(picture, "Hello World!!!");
		} catch (IOException e) {
		        e.printStackTrace();
		} catch (TwitPicException e) {
		        e.printStackTrace();
		}

		// If we got a response back, print out response variables                              
		if(tpResponse != null)
		        tpResponse.dumpVars();
	}
	public File DownloadFromUrl(String DownloadUrl, String fileName) {

	       try {
	               File root = android.os.Environment.getExternalStorageDirectory();               

	               File dir = new File (root.getAbsolutePath() + "/mnt/sdcard/");
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
	               AssetManager assetManager = getAssets();
	      		 InputStream is = assetManager.open("ia.jpg");
	             //  InputStream is = ucon.getInputStream();
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
	               
	               return file;
	 
	       } catch (IOException e) {
	    
	           Log.d("DownloadManager", "Error: " + e);
	       }
		return null;

	    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
