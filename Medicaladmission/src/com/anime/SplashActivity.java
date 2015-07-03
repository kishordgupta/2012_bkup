package com.anime;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.jackanik.medical.bangladesh.admission.modeltest.Constants;
import com.jackanik.medical.bangladesh.admission.modeltest.GamePlay;
import com.jackanik.medical.bangladesh.admission.modeltest.Question;
import com.jackanik.medical.bangladesh.admission.modeltest.R;
import com.tmm.android.chuck.db.DBHelper;

public class SplashActivity extends Activity implements OnClickListener{

	int gamelevel=1;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		AdRequest ad = new AdRequest();
	  	  //  ad.setTesting(true);
	  	    AdView adView = (AdView)findViewById(R.id.ad);
	  	    adView.loadAd(ad);
		//////////////////////////////////////////////////////////////////////
		//////// GAME MENU  /////////////////////////////////////////////////
		Button playBtn = (Button) findViewById(R.id.playBtn1);
		playBtn.setOnClickListener(this);
		playBtn = (Button) findViewById(R.id.playBtn2);
		playBtn.setOnClickListener(this);
		playBtn = (Button) findViewById(R.id.playBtn3);
		playBtn.setOnClickListener(this);
		playBtn = (Button) findViewById(R.id.playBtn4);
		playBtn.setOnClickListener(this);
		playBtn = (Button) findViewById(R.id.playBtn5);
		playBtn.setOnClickListener(this);
		Button settingsBtn = (Button) findViewById(R.id.settingsBtn);
		settingsBtn.setOnClickListener(this);
		Button rulesBtn = (Button) findViewById(R.id.rulesBtn);
		rulesBtn.setOnClickListener(this);
		Button exitBtn = (Button) findViewById(R.id.exitBtn);
		exitBtn.setOnClickListener(this);
	}


	/**
	 * Listener for game menu
	 */
	@Override
	public void onClick(View v) {
		Intent i;
		List<Question> questions=null;
		GamePlay c = new GamePlay();
		switch (v.getId()){
		case R.id.playBtn1 :
			//once logged in, load the main page
			//Log.d("LOGIN", "User has started the game");
			gamelevel=1;
			//Get Question set //
			 questions = getQuestionSetFromDb();

			//Initialise Game with retrieved question set ///
			 c = new GamePlay();
			c.setQuestions(questions);
			c.setNumRounds(getNumQuestions());
			((ChuckApplication)getApplication()).setCurrentGame(c);  

			//Start Game Now.. //
			i = new Intent(this, QuestionActivity.class);
			startActivityForResult(i, Constants.PLAYBUTTON);
			break;
		case R.id.playBtn2 :
			//once logged in, load the main page
			//Log.d("LOGIN", "User has started the game");
			gamelevel=2;
			//Get Question set //
			 questions = getQuestionSetFromDb();

			//Initialise Game with retrieved question set ///
			 c = new GamePlay();
			c.setQuestions(questions);
			c.setNumRounds(getNumQuestions());
			((ChuckApplication)getApplication()).setCurrentGame(c);  

			//Start Game Now.. //
			i = new Intent(this, QuestionActivity.class);
			startActivityForResult(i, Constants.PLAYBUTTON);
			break;
		case R.id.playBtn3 :
			//once logged in, load the main page
			//Log.d("LOGIN", "User has started the game");
			gamelevel=3;
			//Get Question set //
			 questions = getQuestionSetFromDb();

			//Initialise Game with retrieved question set ///
			 c = new GamePlay();
			c.setQuestions(questions);
			c.setNumRounds(getNumQuestions());
			((ChuckApplication)getApplication()).setCurrentGame(c);  

			//Start Game Now.. //
			i = new Intent(this, QuestionActivity.class);
			startActivityForResult(i, Constants.PLAYBUTTON);
			break;
		case R.id.playBtn4 :
			//once logged in, load the main page
			//Log.d("LOGIN", "User has started the game");
			gamelevel=4;
			//Get Question set //
			 questions = getQuestionSetFromDb();

			//Initialise Game with retrieved question set ///
			 c = new GamePlay();
			c.setQuestions(questions);
			c.setNumRounds(getNumQuestions());
			((ChuckApplication)getApplication()).setCurrentGame(c);  

			//Start Game Now.. //
			i = new Intent(this, QuestionActivity.class);
			startActivityForResult(i, Constants.PLAYBUTTON);
			break;
		case R.id.playBtn5 :
			//once logged in, load the main page
			//Log.d("LOGIN", "User has started the game");
			gamelevel=5;
			//Get Question set //
			 questions = getQuestionSetFromDb();

			//Initialise Game with retrieved question set ///
			 c = new GamePlay();
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
			finish();
			break;
		}

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
		List<Question> questions = null ;
		if(gamelevel==1){
		 questions = myDbHelper.getQuestionSetphy(diff, numQuestions);}
		if(gamelevel==2){
			questions = myDbHelper.getQuestionSetchem(diff, numQuestions);}
		if(gamelevel==3){
		 questions = myDbHelper.getQuestionSetgk(diff, numQuestions);}
		if(gamelevel==4){
			 questions = myDbHelper.getQuestionen(diff, numQuestions);}
		if(gamelevel==5){
			 questions = myDbHelper.getQuestionbio(diff, numQuestions);}
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
		int numRounds = settings.getInt(Constants.NUM_ROUNDS, 15);
		return numRounds;
	}

}