package com.kd.kdstagemanagement.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.kd.dasinnovation.movie.chickflicksurvivalguide.R;
import com.kd.kdstagemanagement.database.Createdb;
import com.kd.kdstagemanagement.database.adapter.Init;

public class Firstactivity extends BaseActivity{
	
	static Context con;
	 public void datainitialization()
	 {
		 Createdb.create(this);
		 Init.setdata(this);
	 }
	 public static final String PREFS_NAME = "MyPrefsFile";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		  setContentView(R.layout.splash);
		  setTitle("version code-1");
		Button buttonStart = (Button)findViewById(R.id.btn_start);
		Button   buttonExit = (Button)findViewById(R.id.btn_exit);
		buttonStart.setVisibility(View.INVISIBLE);
		buttonExit.setVisibility(View.INVISIBLE);
		con=this;
		datainitialization();
		ImageView im=(ImageView)findViewById(R.id.imageView1);
		im.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
		        	//Intent i = new Intent(getApplicationContext(),MainActivity.class);
				//startActivity(i);
		 
			}
		});
		
	    
		    
		   start();   
	      
	        
	}
	public static void start(){ new Thread(new Runnable() {
	    public void run() {
	    	Start();
	       
	       
	      }
	    }).start();
};

	protected static void Start() {
		// TODO Auto-generated method stub
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	((Activity) con).finish();
        	Intent i = new Intent(con,MainActivity.class);
		con.startActivity(i);
     
	}
}
