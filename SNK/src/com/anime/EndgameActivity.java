/**
 * 
 */
package com.anime;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.orinsharmin.anime.attackontitan.snk.singekunokiyojin.quiz.Constants;
import com.orinsharmin.anime.attackontitan.snk.singekunokiyojin.quiz.GamePlay;
import com.orinsharmin.anime.attackontitan.snk.singekunokiyojin.quiz.Helper;
import com.orinsharmin.anime.attackontitan.snk.singekunokiyojin.quiz.R;
import com.orinsharmin.anime.attackontitan.snk.singekunokiyojin.quiz.Share;

/**
 * @author robert.hinds
 *
 */
public class EndgameActivity extends Activity implements OnClickListener {
	String result="";
	String comment="";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.endgame);
		GamePlay currentGame = ((ChuckApplication)getApplication()).getCurrentGame();
		result = "I Got " + currentGame.getRight() + "/" + currentGame.getNumRounds() + ".. ";
	    comment = Helper.getResultComment(currentGame.getRight(), currentGame.getNumRounds(), getDifficultySettings());
		AdRequest ad = new AdRequest();
	  	  //  ad.setTesting(true);
	  	    AdView adView = (AdView)findViewById(R.id.ad);
	  	    adView.loadAd(ad);
		
		TextView results = (TextView)findViewById(R.id.endgameResult);
		results.setText(result + comment);
		
		int image = Helper.getResultImage(currentGame.getRight(), currentGame.getNumRounds(), getDifficultySettings());
		ImageView resultImage = (ImageView)findViewById(R.id.resultPage);
		resultImage.setImageResource(image);
		
		//handle button actions
		Button finishBtn = (Button) findViewById(R.id.finishBtn);
		finishBtn.setOnClickListener(this);
		Button answerBtn = (Button) findViewById(R.id.answerBtn);
		answerBtn.setOnClickListener(this);
		Button shareBtn = (Button) findViewById(R.id.share);
		shareBtn.setOnClickListener(this);
	}
	
	
	/**
	 * Method to return the difficulty settings
	 * @return
	 */
	private int getDifficultySettings() {
		SharedPreferences settings = getSharedPreferences(Constants.SETTINGS, 0);
		int diff = settings.getInt(Constants.DIFFICULTY, 2);
		return diff;
	}
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 * 
	 * This method is to override the back button on the phone
	 * to prevent users from navigating back in to the quiz
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		switch (keyCode)
		{
		case KeyEvent.KEYCODE_BACK :
			return true;
		}
		
		return super.onKeyDown(keyCode, event);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.finishBtn :
			finish();
			break;
		case R.id.share:
			Share.share(result+comment, this);
			break;
		case R.id.answerBtn :
			Intent i = new Intent(this, AnswersActivity.class);
			startActivityForResult(i, Constants.PLAYBUTTON);
			break;
		}
	}

}
