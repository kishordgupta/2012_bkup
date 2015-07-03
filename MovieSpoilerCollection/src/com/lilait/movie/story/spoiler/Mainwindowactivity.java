package com.lilait.movie.story.spoiler;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.lilait.movie.story.spoiler.R;
import com.lilakhelait.kishor.helper.GetNetworkStatus;
import com.lilakhelait.kishor.helper.service.StackParser;



public class Mainwindowactivity extends Activity implements OnClickListener{
	 public static final String PREFS_NAME = "MyPrefsFile";
	
	private Button update;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web);
		AdRequest ad = new AdRequest();
		//    ad.setTesting(true);
		    AdView adView = (AdView)findViewById(R.id.ad);
		    adView.loadAd(ad);
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		/* if(GetNetworkStatus.isNetworkAvailable(this))
         { startActivity(new Intent(this, StackParser.class));
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
						alertDialog.show();
		 }*/
		
		Button start=(Button)findViewById(R.id.ReadWebPage);
		start.setOnClickListener(this);
	    update=(Button)findViewById(R.id.update);
		update.setOnClickListener(this);
		Button exit=(Button)findViewById(R.id.exit);
		exit.setOnClickListener(this);
//	ListView listViewwish = (ListView) findViewById(R.id.list_of_wishes);
//		Happynewyearlist happynewyearlist = new Happynewyearlist(this);
//
//	listViewwish.setAdapter(happynewyearlist);
		
		
     ;
		
	}

	private void ConstructResources() {
		// TODO Auto-generated method stub
		
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
	       boolean silent = settings.getBoolean("silentMode", false);
	       if(!silent)
	       {
	    	  
	    	     
	    	   
	    	      
	    	      if(GetNetworkStatus.isNetworkAvailable(this))
		              {
	    	    	  
	    	          startActivity(new Intent(this, StackParser.class));
	    	    	 
		        //      waitstart();
		              SharedPreferences.Editor editor = settings.edit();
		    	      editor.putBoolean("silentMode", true);
		    	      editor.commit();
		    	      Intent intent = new Intent(this, MyAndroidAppActivity.class);
				
					   startActivity(intent);
		              }
	    	      else
	    	      {
	    	    	  Log.d("sdf", "adsdf");
	    	    	  AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
	  						this);
	    	    		alertDialogBuilder.setTitle("Network Unavailable to Update Showtime");
	    	   		 
						// set dialog message
						alertDialogBuilder
							.setMessage("For First time run , U must have a working Connection to See Show time")
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
							alertDialog.show();
	    	      }
	       }
	       else
	       {
	    	   Intent intent = new Intent(this, MyAndroidAppActivity.class);
				
			   startActivity(intent); 
	       }
	       
	}
	
	private void ConstructResourcesforupdate() {
		// TODO Auto-generated method stub
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
	      if(GetNetworkStatus.isNetworkAvailable(this))
 {
			
	    	  startActivity(new Intent(this, StackParser.class));
		//	waitstart();
			SharedPreferences.Editor editor = settings.edit();
			editor.putBoolean("silentMode", true);
			editor.commit();
			Toast.makeText(this, "Sync is on . Wait please", Toast.LENGTH_LONG).show();
			//Intent intent = new Intent(this, MyAndroidAppActivity.class);

			//startActivity(intent);
		}
	      else
	      {
	    	    	  AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
	  						this);
	    	    		alertDialogBuilder.setTitle("Network Unavailable to Update Showtime");
	    	   		 
						// set dialog message
						alertDialogBuilder
							.setMessage("You must have a working Connection to Update Show time")
							.setCancelable(false)
							.setPositiveButton("Try again",new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,int id) {
									// if this button is clicked, close
								
							  update.performClick();
							      
								
								}
							  })
							.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,int id) {
							
								dialog.dismiss();
								}
							});
			 
							// create alert dialog
							AlertDialog alertDialog = alertDialogBuilder.create();
			 
							// show it
							alertDialog.show();
	    
	}
	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ReadWebPage:
			
			ConstructResources();
			v.setBackgroundDrawable(getResources().getDrawable(R.drawable.button));
			break;
		case R.id.update:
			Toast.makeText(this, "Sync is on . Wait pleaze", Toast.LENGTH_LONG).show();
			
			ConstructResourcesforupdate();
			v.setBackgroundDrawable(getResources().getDrawable(R.drawable.button));
			break;
		case R.id.exit:
			
			finish();
			break;
		default:
			break;
		}
	}
}
