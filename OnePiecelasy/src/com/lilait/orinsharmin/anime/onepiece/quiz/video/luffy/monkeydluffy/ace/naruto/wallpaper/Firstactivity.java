package com.lilait.orinsharmin.anime.onepiece.quiz.video.luffy.monkeydluffy.ace.naruto.wallpaper;



import com.lilait.orinsharmin.anime.onepiece.quiz.video.luffy.monkeydluffy.ace.naruto.wallpaper.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Firstactivity extends BaseActivity{
	
	static Context con;
	 public static final String PREFS_NAME = "MyPrefsFile";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		con=this;
    //	Intent i = new Intent(this,MainActivity.class);
	//startActivity(i);   
    	if(GetNetworkStatus.isNetworkAvailable(con)){
    		
        	Intent i = new Intent(con,MainActivity.class);
		con.startActivity(i);
		finish();
        	}
        	else
        	{
        		//((Activity) con).runOnUiThread(new Runnable() {
        			 // public void run() {
        			    Toast.makeText(((Activity) con), "Please, Provide working internet connection to run this app", Toast.LENGTH_SHORT).show();
        			//  }
        			//});
        		//((Activity) con).
        		finish();
        	}
	//finish();
	      
	        
	}
	public static void start(){ new Thread(new Runnable() {
	    public void run() {
	    	Start();
	       
	       
	      }
	    }).start();
};
@Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	
}
	protected static void Start() {
		// TODO Auto-generated method stub
		
	/*	try {
			//Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	*/
		
        	
        	if(GetNetworkStatus.isNetworkAvailable(con)){
        		
        	Intent i = new Intent(con,MainActivity.class);
		con.startActivity(i);
        	}
        	else
        	{
        		((Activity) con).runOnUiThread(new Runnable() {
        			  public void run() {
        			    Toast.makeText(((Activity) con), "Please, Provide working internet connection to run this app", Toast.LENGTH_SHORT).show();
        			  }
        			});
        		((Activity) con).finish();
        	}
        	
        
	}
}
