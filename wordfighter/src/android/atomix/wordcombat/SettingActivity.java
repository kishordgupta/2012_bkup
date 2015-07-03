package android.atomix.wordcombat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.atomix.wordcombat.adapter.CustomLanguageAdapter;
import android.atomix.wordcombat.facebook.Facebook;
import android.atomix.wordcombat.facebook.SessionStore;
import android.atomix.wordcombat.supports.ConstantValues;
import android.atomix.wordcombat.supports.WordCombatApp;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

public class SettingActivity extends Activity implements OnClickListener, OnDrawerCloseListener, OnDrawerOpenListener {
	
	private Button btnExplore, btnResume, btnWordList, btnLanguage, btnDrawer, btnSound, btnFacebook, btnTwitter, btnHelp;
	private ArrayList<String> wordListArray;
	private ProgressDialog progressDialog;
	private ArrayList<String> randomNumberList;
	
	private int wordLength;
	
	private HashMap<String, String> hashMap;
	
	private SlidingDrawer slidingDrawer;
	
	private MediaPlayer mediaPlayer;
	private boolean isSoundPlaying = false;
	
	private static final String APP_ID = "603597719692780";
	private Facebook mFacebook;
	
	private static SharedPreferences mSharedPreferences;
	static final String PREF_KEY_TWITTER_LOGIN = "isTwitterLogedIn";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_screen);
		
		mFacebook = new Facebook(APP_ID);
		SessionStore.restore(mFacebook, getApplicationContext());
		
		mSharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
		
		btnExplore = (Button) findViewById(R.id.btn_explore);
		btnExplore.setOnClickListener(this);
		
		btnResume = (Button) findViewById(R.id.btn_resume);
		btnResume.setOnClickListener(this);
		
		btnWordList = (Button) findViewById(R.id.btn_word_list);
		btnWordList.setOnClickListener(this);
		
		btnLanguage = (Button) findViewById(R.id.btn_language);
		btnLanguage.setOnClickListener(this);
		
		Random random = new Random();
		wordLength = random.nextInt(5 - 4 + 1) + 4;
		
		slidingDrawer = (SlidingDrawer) findViewById(R.id.sliding_drawer);
		slidingDrawer.setOnDrawerCloseListener(this);
		slidingDrawer.setOnDrawerOpenListener(this);
		
		btnDrawer = (Button) findViewById(R.id.handle);
		
		btnSound = (Button) findViewById(R.id.btn_sound);
		btnSound.setOnClickListener(this);
		
		btnFacebook = (Button) findViewById(R.id.btn_facebook);
		btnFacebook.setOnClickListener(this);
		
		if(mFacebook.isSessionValid())
		{
			btnFacebook.setBackgroundResource(R.drawable.fb_over);
		}
		
		btnTwitter = (Button) findViewById(R.id.btn_twitter);
		btnTwitter.setOnClickListener(this);
		
		boolean isLoggedin = mSharedPreferences.getBoolean(PREF_KEY_TWITTER_LOGIN, false);
		if(isLoggedin)
		{
			btnTwitter.setBackgroundResource(R.drawable.tw_over);
		}
		
		btnHelp = (Button) findViewById(R.id.btn_help);
		btnHelp.setOnClickListener(this);
		
		if(ConstantValues.isNewGame)
		{
			GetWordListDataTask task = new GetWordListDataTask();
			progressDialog = ProgressDialog.show(SettingActivity.this, "Loading", "Please wait...", true, false);
			task.execute();
		}
	}

	@Override
	public void onClick(View v) 
	{
		switch (v.getId()) 
		{
			case R.id.btn_explore:
				GetWordListDataTask task = new GetWordListDataTask();
				progressDialog = ProgressDialog.show(SettingActivity.this, "Loading", "Please wait...", true, false);
				task.execute();
				break;
				
			case R.id.btn_resume:
				if(ConstantValues.isResume)
				{
					startActivity(new Intent(SettingActivity.this, WordCombatActivity.class));
					finish();
				}
				
				break;
				
			case R.id.btn_word_list:
				ConstantValues.archive = "25";
				startActivity(new Intent(SettingActivity.this, ShowWordListActivity.class));
				break;
				
			case R.id.btn_language:
				processLanguagePopUp();
				break;
				
			case R.id.btn_sound:
				processSound();
				break;
				
			case R.id.btn_facebook:
				processFacebook();
				break;
				
			case R.id.btn_twitter:
				processTwitter();
				break;
				
			case R.id.btn_help:
				processHelp();
				break;
	
			default:
				break;
		}
	}
	
	private void processHelp() 
	{
		startActivity(new Intent(SettingActivity.this, HelpActivity.class));
	}

	private void processTwitter() 
	{
		startActivity(new Intent(SettingActivity.this, TwitterActivity.class));
	}

	private void processFacebook() 
	{
		startActivity(new Intent(SettingActivity.this, FacebookActivity.class));
	}

	private void processSound() 
	{
		
		if(isSoundPlaying)
		{
			mediaPlayer.stop();
			isSoundPlaying = false;
		}
		else
		{
			mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.main);
			mediaPlayer.start();
			isSoundPlaying = true;
		}
		
	}

	private void processLanguagePopUp() 
	{
		final ArrayList<String> languageList = new ArrayList<String>();
		languageList.add("English");
		languageList.add("Chinese");
		languageList.add("German");
		languageList.add("Japanese");
		languageList.add("Korean");
		languageList.add("Russian");
		
		final Dialog dialog = new Dialog(SettingActivity.this);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.language_pop_up);
		dialog.setCancelable(true);
		ListView lstView = (ListView) dialog.findViewById(R.id.lst_view_language);
		lstView.setAdapter(new CustomLanguageAdapter(SettingActivity.this, languageList));
		
		lstView.setOnItemClickListener(new OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
			{
				ConstantValues.languageName = languageList.get(arg2).toString();
				ConstantValues.languagePosition = arg2;
				dialog.cancel();
			}
		});
		
		Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
		btnCancel.setOnClickListener(new OnClickListener() 
		{	
			@Override
			public void onClick(View v) 
			{
				dialog.cancel();
			}
		});
		dialog.show();
	}

	public class GetWordListDataTask extends AsyncTask<Void, Void, Void> 
	{
		@Override
		protected Void doInBackground(Void... params) 
		{
			wordListArray = new ArrayList<String>();
			randomNumberList = new ArrayList<String>();
			hashMap = new HashMap<String, String>();
			
			try 
			{
				InputStream in = null;
				if(ConstantValues.languageName.equals("English"))
				{
					in = getResources().openRawResource(R.raw.english_dictionary);
					ConstantValues.languageName = "English";
					ConstantValues.languagePosition = 0;
				}
				else if(ConstantValues.languageName.equals("Chinese"))
				{
					in = getResources().openRawResource(R.raw.chinese_dictionary);
					ConstantValues.languageName = "Chinese";
					ConstantValues.languagePosition = 1;
				}
				else if(ConstantValues.languageName.equals("German"))
				{
					in = getResources().openRawResource(R.raw.german_dictionary);
					ConstantValues.languageName = "German";
					ConstantValues.languagePosition = 2;
				}
				else if(ConstantValues.languageName.equals("Japanese"))
				{
					in = getResources().openRawResource(R.raw.japanese_dictionary);
					ConstantValues.languageName = "Japanese";
					ConstantValues.languagePosition = 3;
				}
				else if(ConstantValues.languageName.equals("Korean"))
				{
					in = getResources().openRawResource(R.raw.korean_dictionary);
					ConstantValues.languageName = "Korean";
					ConstantValues.languagePosition = 4;
				}
				
				else if(ConstantValues.languageName.equals("Russian"))
				{
					in = getResources().openRawResource(R.raw.russian_dictionary);
					ConstantValues.languageName = "Russian";
					ConstantValues.languagePosition = 5;
				}
				
				else
				{
					in = getResources().openRawResource(R.raw.english_dictionary);
					ConstantValues.languageName = "English";
					ConstantValues.languagePosition = 0;
				}
				
				BufferedReader br = new BufferedReader(new InputStreamReader(in));

				String line;
				while ((line = br.readLine()) != null) 
				{
					String array[] = line.split(" ");
					hashMap.put(array[0].toString(), array[1].toString());
					if(array[0].toString().length() == wordLength)
					{
						wordListArray.add(array[0].toString());
					}
					
					else
					{
						
					}
					
				}
				
				in.close();
				br.close();
				WordCombatApp.getInstance().setMappingWordList(hashMap);
				getRandomNumber();
				
			} 
			catch (IOException ex) 
			{
				ex.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) 
		{
			super.onPostExecute(result);
			if(progressDialog.isShowing())
			{
				progressDialog.dismiss();
				progressDialog.cancel();
			}
				
			startActivity(new Intent(SettingActivity.this, WordCombatActivity.class));
			finish();
		}
		
	}
	
	public void getRandomNumber()
	{
		Random random = new Random();
		
		while(randomNumberList.size() < 5)
		{
			int index = random.nextInt(wordListArray.size());
			randomNumberList.add(wordListArray.get(index));
		}
		
		WordCombatApp.getInstance().setRandomWordName(randomNumberList);
	}
	
	@Override
	public void onBackPressed() 
	{
		super.onBackPressed();
		ConstantValues.reset();
		finish();
	}
	
	@Override
	protected void onDestroy() 
	{
		super.onDestroy();
		finish();
	}

	@Override
	public void onDrawerClosed() 
	{
		btnDrawer.setBackgroundResource(R.drawable.menu_selector);
	}

	@Override
	public void onDrawerOpened() 
	{
		btnDrawer.setBackgroundResource(R.drawable.close_selector);
	}

}
