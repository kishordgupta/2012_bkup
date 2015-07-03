/**
 * 
 */
package com.quiz.pretest.exam.s3a1;


import com.quiz.pretest.exam.s3a1.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
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
	;
		//finish button
		Button backBtn = (Button) findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		WebView w = (WebView)findViewById(R.id.adsa);
		w.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ONLY);
		w.loadUrl("file:///android_asset/in.html");
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
