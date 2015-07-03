/**
 * 
 */
package com.quiz.pretest.exam.b4;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.tmm.android.chuck.quiz.Constants;
import com.tmm.android.chuck.quiz.GamePlay;
import com.tmm.android.chuck.quiz.Question;
import com.tmm.android.chuck.util.Utility;
import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.quiz.pretest.exam.b4.R;

/**
 * @author robert.hinds
 *
 */
public class AnswersActivity extends Activity implements OnClickListener {

int annswrcount=0;
 GamePlay currentGame=null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.answers);
		 currentGame = ((ChuckApplication)getApplication()).getCurrentGame();
		
			AdRequest ad = new AdRequest();
		  	  //  ad.setTesting(true);
		  	    AdView adView = (AdView)findViewById(R.id.ad);
		  	    adView.loadAd(ad);
	//	results.setText(answers);
		
		//handle button actions
		Button finishBtn = (Button) findViewById(R.id.finishBtn);
		finishBtn.setOnClickListener(this);
		try{
		getAnswers(0);
		}
		catch(Exception e)
		{}
	}
	
	public  void getAnswers(int il) {
		int question = 1;
		Question a = currentGame.getQuestions().get(il);
		TextView results = (TextView)findViewById(R.id.answers);
		results.setText(a.getQuestion());
		if(!a.getAnswer().equalsIgnoreCase(a.getUseranswer())){
		
			TextView	resultsa = (TextView)findViewById(R.id.ra);
			resultsa.setText(a.getUseranswer());
			resultsa.setVisibility(View.VISIBLE);
			
			resultsa.setTextColor(Color.RED);
			
			TextView resultsb = (TextView)findViewById(R.id.wa);
			resultsb.setText(a.getAnswer());
			resultsb.setTextColor(Color.GREEN);
		}
	else
	{
		TextView resultsc = (TextView)findViewById(R.id.ra);
		resultsc.setVisibility(View.GONE);
		
		TextView resultsd = (TextView)findViewById(R.id.wa);
		resultsd.setText(a.getAnswer());
		resultsd.setTextColor(Color.GREEN);
	}
		if(annswrcount<currentGame.getQuestions().size())
		annswrcount++;
		
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 * 
	 * This method is to override the back button on the phone
	 * to prevent users from navigating back in to the quiz
	 */
	/*@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		switch (keyCode)
		{
		case KeyEvent.KEYCODE_BACK :
			return true;
		}
		
		return super.onKeyDown(keyCode, event);
	}*/
@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();
	Intent i = new Intent(this, SplashActivity.class);
	startActivityForResult(i, Constants.PLAYBUTTON);
	finish();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.finishBtn :
			if(annswrcount<currentGame.getQuestions().size())
			getAnswers(annswrcount);
			else
				{	Intent i = new Intent(this, SplashActivity.class);
				startActivityForResult(i, Constants.PLAYBUTTON);
				finish();
				}
			break;
			
		}
	}

}
