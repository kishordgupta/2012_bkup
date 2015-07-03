package com.kd.lilait.maronitefaith.prayer.app;




import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Firstactivity extends BaseActivity implements DialogInterface{
	
	static Context con;
	 public static final String PREFS_NAME = "MyPrefsFile";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//  setContentView(R.layout.splash);
		 // setTitle("version code-1");
		con=this;
		if(!GetNetworkStatus.isNetworkAvailable(con))
    	{ new AlertDialog.Builder(this)
        .setTitle("No network")
        .setMessage("Try later")
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
                // continue with delet
            	start();
            	dialog.dismiss();
            
            }
         })
         .show();
    		//Toast.makeText(c, "Need working internet to use this app", Toast.LENGTH_LONG).show();
    	//finish();
    	}
		else
		{
			Intent i = new Intent(con,MainActivity.class);
			con.startActivity(i);
			finish();
		}
	      
	        
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
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
       
        		((Activity) con).finish();
        	
        	
        
	}
	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		finish();
	}
}
