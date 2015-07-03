/**
 * 
 */
package com.anime;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.orinsharmin.anime.deathnote.quiz.R;
import com.orinsharmin.anime.deathnote.quiz.Share;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

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
		final Context con=this;
		Button b = (Button) findViewById(R.id.hitman);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Share.starthitman(con);
			}
		});
		
		Button c = (Button) findViewById(R.id.fairytale);
		c.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Share.startfairytail(con);
			}
		});
		
		c = (Button) findViewById(R.id.dgrayman);
		c.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Share.startdgre(con);
			}
		});
		
		c = (Button) findViewById(R.id.ganime);
		c.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Share.startdgus(con);
			}
		});
	}
	
	@Override
	public void onClick(View arg0) {
		/**
		 * if the back button is clicked then go back to the main menu
		 */
		finish();
	}

}
