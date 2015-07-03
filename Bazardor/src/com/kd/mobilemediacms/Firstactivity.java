package com.kd.mobilemediacms;

import sqlitedb.MySQLiteHelper;
import sqlitedb.SQLiteWraper;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.kd.helper.Datasync;
import common.GetNetworkStatus;

public class Firstactivity extends BaseActivity{
	static SQLiteWraper sqlitedb;
	static Context con;
	 public static final String PREFS_NAME = "MyPrefsFile";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		  setContentView(R.layout.splash);
		  setTitle("version code-0");
		Button buttonStart = (Button)findViewById(R.id.btn_start);
		Button   buttonExit = (Button)findViewById(R.id.btn_exit);
		buttonStart.setVisibility(View.INVISIBLE);
		buttonExit.setVisibility(View.INVISIBLE);
		con=this;
		Datasync.offline(con);
	
		  MySQLiteHelper my = new MySQLiteHelper(this);
	        my.getWritableDatabase();
	        my.close();
	        sqlitedb = new SQLiteWraper(getApplicationContext());
	        buttonStart.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(con,CatagoryActivity.class);
				    con.startActivity(i);
				    finish();
				}
			});
	        buttonExit.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
			});
	    	SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		       boolean silent = settings.getBoolean("silentMode", false);
		       if(!silent)
		       {
		    	   SharedPreferences.Editor editor = settings.edit();
		    	      editor.putBoolean("silentMode", true);
		    	      editor.commit();
		   if(GetNetworkStatus.isNetworkAvailable(this)){
	           Datasync.servicestar();
	           
	            ShowMessage("NetWork available, Sync with server DB please wait");
	            try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        
		   }
	        else
	        {
	        	
	        	 ShowMessage("NetWork unavailable, Sync with server DB failed");
	        }
		       }
		    
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
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
        	Intent i = new Intent(con,CatagoryActivity.class);
		    con.startActivity(i);
		   
      
	}
	
}
