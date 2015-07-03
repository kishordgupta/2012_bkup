package com.lilait.bb.speaker.creator.richtext.editor.free;



import com.lilait.bb.speaker.creator.richtext.editor.free.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Firstactivity extends BaseActivity{
	
	static Context con;
	EditText e=null;
	 public static final String PREFS_NAME = "MyPrefsFile";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		  setContentView(R.layout.splash);
		  setTitle("version code-1");
		con=this;
		//start();    
	      e =(EditText)findViewById(R.id.editText1);
	      Button b = (Button)findViewById(R.id.button1);
	      b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MyFile.htm=e.getText().toString()+"_bbfile.txt";
				
				Intent i = new Intent(con,MainActivity.class);
				con.startActivity(i);
			}
		});
	        
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
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
        	
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
