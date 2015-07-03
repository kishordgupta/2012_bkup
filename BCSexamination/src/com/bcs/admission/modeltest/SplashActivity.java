package com.bcs.admission.modeltest;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.bcs.helper.Constants;
import com.bcs.helper.GamePlay;
import com.bcs.helper.Helper;
import com.bcs.helper.Question;
import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.ironsource.mobilcore.CallbackResponse;
import com.ironsource.mobilcore.MobileCore;
import com.ironsource.mobilcore.OnReadyListener;
import com.ironsource.mobilcore.CallbackResponse.TYPE;
import com.ironsource.mobilcore.MobileCore.AD_UNITS;
import com.ironsource.mobilcore.MobileCore.LOG_TYPE;
import com.tmm.android.chuck.db.DBHelper;

public class SplashActivity extends Activity implements OnClickListener{

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		 MobileCore.init(this,"8YKF89U9YHXZUIMHFEQ0UDC1T9TOY", LOG_TYPE.DEBUG);
		 MobileCore.getSlider().setContentViewWithSlider(this,R.layout.welcome);
		 final Context c=this;
		 MobileCore.setStickeezReadyListener(new OnReadyListener() {
			    @Override
			    public void onReady(AD_UNITS adUnit) {
				if (adUnit == AD_UNITS.STICKEEZ) {
				    MobileCore.showStickee((Activity) c);
				}
			    }
			});
			
		AdRequest ad = new AdRequest();
	  	  //  ad.setTesting(true);
	  	    AdView adView = (AdView)findViewById(R.id.ad);
	  	    adView.loadAd(ad);
		//////////////////////////////////////////////////////////////////////
		//////// GAME MENU  /////////////////////////////////////////////////
		Button playBtn = (Button) findViewById(R.id.playBtn);
		playBtn.setOnClickListener(this);
		Button settingsBtn = (Button) findViewById(R.id.settingsBtn);
		settingsBtn.setOnClickListener(this);
		Button rulesBtn = (Button) findViewById(R.id.rulesBtn);
		rulesBtn.setOnClickListener(this);
		Button exitBtn = (Button) findViewById(R.id.exitBtn);
		exitBtn.setOnClickListener(this);
		final Context con=this;
		
	}


	/**
	 * Listener for game menu
	 */
	@Override
	public void onClick(View v) {
		Intent i;
		
		switch (v.getId()){
		case R.id.playBtn :
			//once logged in, load the main page
			//Log.d("LOGIN", "User has started the game");
			
			//Get Question set //
			List<Question> questions = getQuestionSetFromDb();

			//Initialise Game with retrieved question set ///
			GamePlay c = new GamePlay();
			c.setQuestions(questions);
			c.setNumRounds(getNumQuestions());
			((ChuckApplication)getApplication()).setCurrentGame(c);  

			//Start Game Now.. //
			i = new Intent(this, QuestionActivity.class);
			startActivityForResult(i, Constants.PLAYBUTTON);
			break;
			
		case R.id.rulesBtn :
			i = new Intent(this, RulesActivity.class);
			startActivityForResult(i, Constants.RULESBUTTON);
			break;
			
		case R.id.settingsBtn :
			i = new Intent(this, SettingsActivity.class);
			startActivityForResult(i, Constants.SETTINGSBUTTON);
			break;
			
		case R.id.exitBtn :
			final Context ca= this;
			MobileCore.showOfferWall((Activity) ca, new CallbackResponse() {
				@Override
				public void onConfirmation(TYPE arg0) {
					((Activity) ca).finish();
				}
				});
			break;
		}

	}
	@Override
	public void onBackPressed() {
		
		// TODO Auto-generated method stub
		//super.onBackPressed();
		final Context c= this;
		MobileCore.showOfferWall((Activity) c, new CallbackResponse() {
			@Override
			public void onConfirmation(TYPE arg0) {
				((Activity) c).finish();
			}
			});
//		MobileCore.showOfferWall(*Your Activity*, new CallbackResponse() {@Override public void onConfirmation(TYPE arg0) { .finish(); } });
	}
		

	/**
	 * Method that retrieves a random set of questions from
	 * the database for the given difficulty
	 * @return
	 * @throws Error
	 */
	private List<Question> getQuestionSetFromDb() throws Error {
		int diff = getDifficultySettings();
		int numQuestions = getNumQuestions();
		DBHelper myDbHelper = new DBHelper(this);
		try {
			myDbHelper.createDataBase();
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}
		try {
			myDbHelper.openDataBase();
		}catch(SQLException sqle){
			throw sqle;
		}
		List<Question> questions = myDbHelper.getQuestionSet(diff, numQuestions);
		myDbHelper.close();
		return questions;
	}


	/**
	 * Method to return the difficulty settings
	 * @return
	 */
	private int getDifficultySettings() {
		SharedPreferences settings = getSharedPreferences(Constants.SETTINGS, 0);
		int diff = settings.getInt(Constants.DIFFICULTY, Constants.MEDIUM);
		return diff;
	}

	/**
	 * Method to return the number of questions for the game
	 * @return
	 */
	private int getNumQuestions() {
		SharedPreferences settings = getSharedPreferences(Constants.SETTINGS, 0);
		int numRounds = settings.getInt(Constants.NUM_ROUNDS, 20);
		return numRounds;
	}

}