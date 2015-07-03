/**
 * 
 */
package com.quiz.pretest.exam.s3a1;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.tmm.android.chuck.quiz.GamePlay;
import com.tmm.android.chuck.quiz.Question;
import com.tmm.android.chuck.util.Utility;

/**
 * @author robert.hinds
 *
 */
public class QuestionActivity extends Activity implements OnClickListener{

	private Question currentQ;
	private GamePlay currentGame;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        
        /**
         * Configure current game and get question
         */
        AdView adView = new AdView(this);
	    adView.setAdSize(AdSize.BANNER);
	    adView.setAdUnitId(" ca-app-pub-3919172384518296/3643745562");

	    // Add the AdView to the view hierarchy. The view will have no size
	    // until the ad is loaded.
	    LinearLayout layout = (LinearLayout) findViewById(R.id.a);
	    layout.addView(adView);

	    // Create an ad request. Check logcat output for the hashed device ID to
	    // get test ads on a physical device.
	    AdRequest adRequest = new AdRequest.Builder()
	        .build();

	    // Start loading the ad in the background.
	    adView.loadAd(adRequest);
    

        currentGame = ((ChuckApplication)getApplication()).getCurrentGame();
        try{
        	if(currentGame.getNumRounds()<2)
        		currentGame.setNumRounds(2);//2;
        currentQ = currentGame.getNextQuestion();
		
        }
        catch(NullPointerException e )
        {
        	Intent i = new Intent(this, SplashActivity.class);
			startActivity(i);
			finish();
        }
        Button nextBtn = (Button) findViewById(R.id.nextBtn);
		nextBtn.setOnClickListener(this);
	if(!SplashActivity.practicemode)
		nextBtn.setVisibility(View.GONE);
        RadioGroup r =(RadioGroup)findViewById(R.id.group1);
        r.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(!SplashActivity.practicemode)
				lick();
				else
					checkAnswer();
				
					
			}
		});
        /**
         * Update the question and answer options..
         */
        try{
        setQuestions();
        }
        catch(NullPointerException e )
        {
        	Intent i = new Intent(this, SplashActivity.class);
			startActivity(i);
			finish();
        }
    }


	/**
	 * Method to set the text for the question and answers from the current games
	 * current question
	 */
	private void setQuestions() {
		//set the question text from current question
		String question = Utility.capitalise(currentQ.getQuestion()) +" ";
        TextView qText = (TextView) findViewById(R.id.question);
        qText.setText( Html.fromHtml( currentGame.getRound() + "  "+ question));
       
        //set the available options
        List<String> answers = currentQ.getQuestionOptions();
        TextView option1 = (TextView) findViewById(R.id.answer1);
        option1.setText( Html.fromHtml(answers.get(0)));
        
        TextView option2 = (TextView) findViewById(R.id.answer2);
        option2.setText( Html.fromHtml(answers.get(1)));
        
        TextView option3 = (TextView) findViewById(R.id.answer3);
        option3.setText( Html.fromHtml(answers.get(2)));
        
        TextView option4 = (TextView) findViewById(R.id.answer4);
        option4.setText( Html.fromHtml(answers.get(3)));
	}
    
	

	@Override
	public void onClick(View arg0) {
		//Log.d("Questions", "Moving to next question");
		
		/**
		 * validate a checkbox has been selected
		 */
		if (!checkAnswer()) return;

		
		/**
		 * check if end of game
		 */
		if (currentGame.isGameOver()){
			//Log.d("Questions", "End of game! lets add up the scores..");
			//Log.d("Questions", "Questions Correct: " + currentGame.getRight());
			//Log.d("Questions", "Questions Wrong: " + currentGame.getWrong());
			if(!SplashActivity.practicemode){
			Intent i = new Intent(this, EndgameActivity.class);
			startActivity(i);
			finish();
			}
			else
			{
				Intent i = new Intent(this, SplashActivity.class);
				startActivity(i);
				finish();
			}
		}
		else{
			Intent i = new Intent(this, QuestionActivity.class);
			startActivity(i);
			finish();
		}
	}
	
	
	public void lick() {
		//Log.d("Questions", "Moving to next question");
		
		/**
		 * validate a checkbox has been selected
		 */
		if (!checkAnswer()) return;

		
		/**
		 * check if end of game
		 */
		if (currentGame.isGameOver()){
			//Log.d("Questions", "End of game! lets add up the scores..");
			//Log.d("Questions", "Questions Correct: " + currentGame.getRight());
			//Log.d("Questions", "Questions Wrong: " + currentGame.getWrong());
			if(!SplashActivity.practicemode){
				Intent i = new Intent(this, EndgameActivity.class);
				startActivity(i);
				finish();
				}
				else
				{
					Intent i = new Intent(this, SplashActivity.class);
					startActivity(i);
					finish();
				}
		}
		else{
			Intent i = new Intent(this, QuestionActivity.class);
			startActivity(i);
			finish();
		}
	}
	
	
/*	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		switch (keyCode)
		{
		case KeyEvent.KEYCODE_BACK :
			return true;
		}
		
		return super.onKeyDown(keyCode, event);
	}

*/
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent i = new Intent(this, SplashActivity.class);
		startActivity(i);
		finish();
		
	}
	/**
	 * Check if a checkbox has been selected, and if it
	 * has then check if its correct and update gamescore
	 */
	private boolean checkAnswer() {
		String answer = getSelectedAnswer();
		 currentQ.setUseranswer(answer);
		if (answer==null){
			//Log.d("Questions", "No Checkbox selection made - returning");
			return false;
		}
		else {
			
			//Log.d("Questions", "Valid Checkbox selection made - check if correct");
			if (currentQ.getAnswer().equalsIgnoreCase(answer))
			{   if(SplashActivity.practicemode)
				greenSelectedAnswer(true);
			   
				//Log.d("Questions", "Correct Answer!");
				currentGame.incrementRightAnswers();
			}
			else{
				  if(SplashActivity.practicemode)
				greenSelectedAnswer(false);
				//Log.d("Questions", "Incorrect Answer!");
				currentGame.incrementWrongAnswers();
			}
			return true;
		}
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
	/**
	 * 
	 */
	private String getSelectedAnswer() {
		RadioButton c1 = (RadioButton)findViewById(R.id.answer1);
		RadioButton c2 = (RadioButton)findViewById(R.id.answer2);
		RadioButton c3 = (RadioButton)findViewById(R.id.answer3);
		RadioButton c4 = (RadioButton)findViewById(R.id.answer4);
		if (c1.isChecked())
		{
			return c1.getText().toString();
		}
		if (c2.isChecked())
		{
			return c2.getText().toString();
		}
		if (c3.isChecked())
		{
			return c3.getText().toString();
		}
		if (c4.isChecked())
		{
			return c4.getText().toString();
		}
		
		return null;
	}
	
	private  void greenSelectedAnswer(boolean ga) {
		
		RadioButton c1 = (RadioButton)findViewById(R.id.answer1);
		RadioButton c2 = (RadioButton)findViewById(R.id.answer2);
		RadioButton c3 = (RadioButton)findViewById(R.id.answer3);
		RadioButton c4 = (RadioButton)findViewById(R.id.answer4);
		if(ga){
		
		if (c1.isChecked())
		{
			c1.setTextColor(Color.GREEN);
		}
		if (c2.isChecked())
		{
			c2.setTextColor(Color.GREEN);
		}
		if (c3.isChecked())
		{
			c3.setTextColor(Color.GREEN);
		}
		if (c4.isChecked())
		{
			c4.setTextColor(Color.GREEN);
		}
	
		}
		else
		{
			if (c1.isChecked())
			{
				if (currentQ.getAnswer().equalsIgnoreCase(c1.getText().toString()))
				{
					//Log.d("Questions", "Correct Answer!");
					c1.setTextColor(Color.GREEN);
				}
				else{
					//Log.d("Questions", "Incorrect Answer!");
					if (c1.isChecked())
					{
						c1.setTextColor(Color.RED);
					}
				}
			
			}
			if (c2.isChecked())
			{
				if (currentQ.getAnswer().equalsIgnoreCase(c2.getText().toString()))
				{
					//Log.d("Questions", "Correct Answer!");
					c2.setTextColor(Color.GREEN);
				}
				else{
					//Log.d("Questions", "Incorrect Answer!");
					if (c2.isChecked())
					{
						c2.setTextColor(Color.RED);
					}
				}
			}
			if (c3.isChecked())
			{
				if (currentQ.getAnswer().equalsIgnoreCase(c3.getText().toString()))
				{
					//Log.d("Questions", "Correct Answer!");
					c3.setTextColor(Color.GREEN);
				}
				else{
					//Log.d("Questions", "Incorrect Answer!");
					if (c3.isChecked())
					{
						c3.setTextColor(Color.RED);
					}
				}
			}
			if (c4.isChecked())
			{
				if (currentQ.getAnswer().equalsIgnoreCase(c4.getText().toString()))
				{
					//Log.d("Questions", "Correct Answer!");
					c4.setTextColor(Color.GREEN);
				}
				else{
					//Log.d("Questions", "Incorrect Answer!");
					if (c4.isChecked())
					{
						c4.setTextColor(Color.RED);
					}
				}
			}
		}
	}
	
}
