/**
 * 
 */
package com.quiz.pretest.exam.b4;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.quiz.pretest.exam.b4.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author robert.hinds
 *
 */
public class RulesActivity extends Activity implements OnClickListener{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rules);
		AdRequest ad = new AdRequest();
	  	  //  ad.setTesting(true);
	  	    AdView adView = (AdView)findViewById(R.id.ad);
	  	    adView.loadAd(ad);
		//finish button
		Button backBtn = (Button) findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View arg0) {
		/**
		 * if the back button is clicked then go back to the main menu
		 */
		finish();
	}
	   @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	    	// TODO Auto-generated method stub
	    	 switch (item.getItemId()) {
	         case android.R.id.home:
	        	 
	        		Intent i = new Intent(this, SplashActivity.class);
	        		startActivity(i);
	        		finish();
	         }
	    	return super.onOptionsItemSelected(item);
	    }
@Override
public void onBackPressed() {
	// TODO Auto-generated method stub
	finish();
}
}
