package com.lilait.kd.calulatorrecorder;




import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.lilait.kd.calculator2.Calculator;
import com.lilait.kd.calulatorrecorder.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Firstactivity extends Activity{

	static Context con;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		  setContentView(R.layout.splash);
	con=this;
	//AdRequest ad = new AdRequest();
 
  //  AdView adView = (AdView)findViewById(R.id.ad);
  //  adView.loadAd(ad);
    
    Button b =(Button)findViewById(R.id.button1);
    b.setOnClickListener(new OnClickListener() {
	
	
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
			Intent i = new Intent(con,Calculator.class);
			startActivity(i);
		}
	});
	ImageView im=(ImageView)findViewById(R.id.imageView1);
	im.setOnClickListener(new OnClickListener() {
	
	
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
			Intent i = new Intent(con,Calculator.class);
			startActivity(i);
		}
	});
   
	}
	
}
