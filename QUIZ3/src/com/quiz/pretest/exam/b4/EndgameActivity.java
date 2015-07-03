/**
 * 
 */
package com.quiz.pretest.exam.b4;

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
import com.tmm.android.chuck.quiz.Constants;
import com.tmm.android.chuck.quiz.GamePlay;
import com.tmm.android.chuck.quiz.Helper;
import com.quiz.pretest.exam.b4.R;

/**
 * @author robert.hinds
 *
 */
public class EndgameActivity extends Activity implements OnClickListener {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.endgame);
		try{
		GamePlay currentGame = ((ChuckApplication)getApplication()).getCurrentGame();
		String result = "Exam Completed";//"You'r Score " + currentGame.getRight() + " out of " + currentGame.getNumRounds() + ".. ";
		String comment ="You'r Score " + currentGame.getRight() + " out of " + currentGame.getNumRounds() + ".. ";
			//	AdRequest ad = new AdRequest();
	  	  //  ad.setTesting(true);
		TextView results = (TextView)findViewById(R.id.endgameResult);
		results.setText(result +"\n"+ comment);
		}
		catch(Exception e){}
		
		
		
	//	int image = Helper.getResultImage(currentGame.getRight(), currentGame.getNumRounds(), getDifficultySettings());
		//ImageView resultImage = (ImageView)findViewById(R.id.resultPage);
		//resultImage.setImageResource(image);
		
		//handle button actions
		Button finishBtn = (Button) findViewById(R.id.finishBtn);
		finishBtn.setOnClickListener(this);
		Button answerBtn = (Button) findViewById(R.id.answerBtn);
		answerBtn.setOnClickListener(this);
		
		AdRequest ad = new AdRequest();
	  	  //  ad.setTesting(true);
	  	 //   AdView adView = (AdView)findViewById(R.id.ad);
	
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
			Intent i = new Intent(this, SplashActivity.class);
			startActivityForResult(i, Constants.PLAYBUTTON);
			finish();
			break;
			
		case R.id.answerBtn :
			Intent ia = new Intent(this, AnswersActivity.class);
			startActivityForResult(ia, Constants.PLAYBUTTON);
			finish();
			break;
		}
	}

}
