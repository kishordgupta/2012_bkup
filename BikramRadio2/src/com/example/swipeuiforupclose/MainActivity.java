/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.swipeuiforupclose;

import java.util.ArrayList;



import radioklub.sekhontech.com.entity.Station;

import radioklub.sekhontech.com.service.ImageFetcherService;
import radioklub.sekhontech.com.service.ImageFetcherService.ImageFetcherBinder;
import radioklub.sekhontech.com.service.MusicPlayerService;
import radioklub.sekhontech.com.service.MusicPlayerService.StreamBinder;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.astuetz.PagerSlidingTabStrip.IconTabProvider;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import com.spoledge.aacdecoder.MP3Player;

public class MainActivity extends FragmentActivity {

	private static final String IS_CLICKABLE = "IS_CLICK`ABLE";
	private static final String IS_STARTING = "IS_STARTING";
	private static final String SERVICE_STARTED = "SERVICE_STARTED";
	private static final String CURRENT_POSITION = "CURRENT_POSITION";
	private static final String XML_CONTENT = "XML_CONTENT";


	// Flag variable
	public static  boolean mIsClickable = true;
	// TODO : Replace mIsPlaying by isPlaying()
	public static boolean mIsPlaying = false;
	public static boolean mIsStarting = true;
	public static boolean mServiceStated = false;
	public static boolean mContinue = false;
	private static final Object mPlayerLock = new Object();

	// Non-View member variables
	private String mXMLContent;
	private Station mCurrentStation;
	private static String mCurrentStream = "http://radioboxhd.com:8051/live";
	private BroadcastReceiver mReceiver;
	private ComponentName mMediaResponder;
	private static MusicPlayerService mMPService;
	private ImageFetcherService mIFService;
	private ServiceConnection mMPSConnection;
	private ServiceConnection mIFSConnection;
	private PhoneCallListener mPhoneCallListener;
	private IntentFilter mFilter;
	// volume control
	private int mMaxVolume;
	private int mVolume;
	private AudioManager mAudioManager;
	private MenuItem mShareItem;
	private MenuItem mActionItem;
	// Fragments reference
	private StationListFragment mStationsFragment;
	private RadioStreamFragment mRadioFragment;
	private WebFragment mContactFragment;
	private WebFragment mWebFragment;
	private WebFragment mFacebookFragment;
	private WebFragment mTwitterFragment;

	// ///////
	public static ImageLoader imageLoader = ImageLoader.getInstance();

	private static final int REQUEST_CODE = 1234;

	public static Context c;
	public static ArrayList<Station> mStations = new ArrayList<Station>();

	public static String Radio_tag = "";

	//AppSectionsPagerAdapter mAppSectionsPagerAdapter;

	ViewPager mViewPager;
	EditText AboutMeET;
	Fragment profileFragment;

	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private MyPagerAdapter adapter;
	public static String Songtitle="Song";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		
		
		
		setContentView(R.layout.profile_main);
		
		c = this;
		imageLoader.init(ImageLoaderConfiguration.createDefault(c));
		// Create the adapter that will return a fragment for each of the three
		// primary sections
		// of the app.
		tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
		pager = (ViewPager) findViewById(R.id.pager);
		adapter = new MyPagerAdapter(getSupportFragmentManager());

		pager.setAdapter(adapter);

		final int pageMargin = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
						.getDisplayMetrics());
		pager.setPageMargin(pageMargin);

		tabs.setViewPager(pager);
		tabs.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		
	
	
		
		//Registering class
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.MEDIA_BUTTON");
        filter.addAction("android.media.VOLUME_CHANGED_ACTION");
        getApplicationContext().registerReceiver(new MediaListener(), filter);
        
		//REFACTOR NEED
		//TODO
		if(savedInstanceState != null) {
		//	mXMLContent = savedInstanceState.getString(XML_CONTENT);
			//mStations = loadStations(mXMLContent);
			mCurrentStation = mStations.get(0);
		} else {
		//	mStations = loadStations();
			mCurrentStation = mStations.get(0);
			//new XMLLoadAsync().execute(XML_URL);
		}

		//Restore
		if (savedInstanceState != null) {
			int currentPosition = savedInstanceState.getInt(CURRENT_POSITION);
			mCurrentStation = mStations.get(currentPosition);
		}
		mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		
		//END REFACTOR NEED
		/* Create IntentFilter for receiver
		 * Create BroadcastReceiver
		 * Create Service Connection and start services
		 * Binding services with activity through services connection 
		 * NOTE: the receiver is not registering with IntentFilter yet */	
		
		
		initFilter();
		initReceiver();
		initServices();
		bindServices();
		
		//Restore previous state before init view
		if (savedInstanceState != null) {			
			restore(savedInstanceState);
			
		}
		
		mStationsFragment = new StationListFragment();
		mStationsFragment.setStations(mStations);
		mRadioFragment = new RadioStreamFragment();
		mContactFragment = new WebFragment();
		mContactFragment.setUrl("http://freeitunestvshow.com/");
		mWebFragment = new WebFragment();
		mWebFragment.setUrl(Firstactivity.web);
		mFacebookFragment = new WebFragment();
		mFacebookFragment.setUrl(Firstactivity.facebook);
		mTwitterFragment = new WebFragment();
		mTwitterFragment.setUrl(Firstactivity.twitter);
		
		String Admobid=Firstactivity.admobid;
		LinearLayout lv = (LinearLayout)findViewById(R.id.a);

		//create the AdView (replace MY_BANNER_UNIT_ID with the admob ID of your choice)
		AdView av = new AdView(this, AdSize.BANNER, Admobid);
		lv.addView(av);//add the AdView to your layout

		AdRequest request = new AdRequest();
		av.loadAd(request);
	//	Toast.makeText(c, Firstactivity.facebook+Firstactivity.twitter+Firstactivity.admobid+Firstactivity.web, Toast.LENGTH_LONG).show();
			}
		

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		// see http://androidsnippets.com/prompt-user-input-with-an-alertdialog
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.main, menu);
		mShareItem = menu.findItem(R.id.share);

			
	
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.settings:
	            newGame();
	            return true;
	
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}


	private void newGame() {
		// TODO Auto-generated method stub
		final SharedPreferences prefs = MainActivity.c.getSharedPreferences(
				"com.example.app", Context.MODE_PRIVATE);
    	AlertDialog alert = new AlertDialog.Builder(MainActivity.c).create();

		alert.setTitle("Radio Code");
		alert.setMessage("Write the Radio code");

		// Set an EditText view to get user input 
		final EditText input = new EditText(MainActivity.c);
		input.setText(Radio_tag);
		alert.setView(input);
      
		alert.setButton(AlertDialog.BUTTON_POSITIVE,"OK", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if(input.getText().toString() != ""){
					//MainActivity.Radio_tag=input.getText().toString() ;
				//	new dataprocess.XMLLoadAsync().execute(XML_URL);
					SharedPreferences.Editor editor = prefs.edit();
					editor.putString("Radio_tag", input.getText()
							.toString());
					editor.commit();
					startActivity(new Intent(MainActivity.c,Firstactivity.class));
				    dialog.dismiss();
				   // finish();
				}
				else
				{
					Toast.makeText(MainActivity.c, "WRITE THE CODE PLEASE NO NULL STRING", Toast.LENGTH_LONG).show();
				}
			}
		});
		try{
		alert.setOnDismissListener(new OnDismissListener() {
	        @Override
	        public void onDismiss(DialogInterface dialog) {
	            finish();
	        }
	    });
		}
	catch(Exception e)
	{}
        alert.show();
	}

	


	public class MyPagerAdapter extends FragmentPagerAdapter implements IconTabProvider {

		private final String[] TITLES = { "Channels ", "Currently Playing",
				"Web", "Facebook", "Twitter", "Contact" };
		private final int[] iconsimage = { R.drawable.listicon, R.drawable.musicicon,
				R.drawable.web, R.drawable.facebook, R.drawable.twittericon,R.drawable.chat };

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}
		
 
		@Override
		public int getCount() {
			return TITLES.length;
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				// The first section of the app is the most interesting -- it
				// offers
				// a launchpad into the other demonstrations in this example
				// application.
			//	Fragment profileFragment = new StationListFragment();
				return mStationsFragment;

			case 1:
				
				return mRadioFragment;

			case 2:
			
				return mWebFragment;
			case 3:
				
				return mFacebookFragment;
			case 4:
				
				return mTwitterFragment;
			case 5:
			
				return mContactFragment;
			default:
				
				return mContactFragment;
			}
		}

		@Override
		public int getPageIconResId(int position) {
			// TODO Auto-generated method stub
			return iconsimage[position];
		}

	}

	private void initServices() {

		mMPSConnection = new ServiceConnection() {
			@Override
			public void onServiceDisconnected(ComponentName name) {

			}

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				mMPService = ((StreamBinder) service).getService();
			}
		};
		mIFSConnection = new ServiceConnection() {
			@Override
			public void onServiceDisconnected(ComponentName name) {
			}

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				mIFService = ((ImageFetcherBinder) service).getService();
			}
		};

		startServices();
		bindServices();
	}

	private void initReceiver() {

		// Create BroadcastReceiver
		mReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				Bundle bundle = intent.getExtras();

				// MusicPlayerService notification
				if (intent.getAction().equals(MusicPlayerService.NOTIFICATION)) {
					String status = bundle.getString(MusicPlayerService.STATUS);
                    
					if (status
							.equals(MusicPlayerService.STATUS_SERVICE_STARTED)) {
						mServiceStated = true;
					} else {
						setRadioInfo(status);
						Songtitle=status;
					}

					if (status.equals(MusicPlayerService.STATUS_PLAYING)
							|| status.equals(MusicPlayerService.STATUS_STOPPED)) {
						synchronized (mPlayerLock) {
							mIsClickable = true;
						}
						setProgressBarIndeterminateVisibility(false);
						if (status.equals(MusicPlayerService.STATUS_PLAYING)) {
							mIsPlaying = true;
						}
						if (status.equals(MusicPlayerService.STATUS_STOPPED)) {
							mIsPlaying = false;
							// TODO optimize
							if (mContinue) {
								mContinue = false;
								playRadio();
							}
						}
						// Update button. Why ? Because sometime play and
						// pause not send from HomeActivity but widget
					}
				}

				// ImageFetcherService notification
				if (intent.getAction().equals(ImageFetcherService.NOTIFICATION)) {
					String status = bundle
							.getString(ImageFetcherService.STATUS);
					if (status.equals(ImageFetcherService.STATUS_DONE)) {
						mRadioFragment.setImageCover(mIFService.getBitmap());
					} else if (status
							.equals(ImageFetcherService.STATUS_NOTFOUND)) {
						// NOT IMPLEMENT YET
					}
				}
			}
		};

		// Phonestate listener (receiver ?)
		try {
			mPhoneCallListener = new PhoneCallListener();
			TelephonyManager telephone = (TelephonyManager) this
					.getSystemService(Context.TELEPHONY_SERVICE);
			telephone.listen(mPhoneCallListener,
					PhoneStateListener.LISTEN_CALL_STATE);
		} catch (Exception e) {
			Toast.makeText(
					getApplicationContext(),
					"Error setup environment. Radio may still playing when phone call happen",
					Toast.LENGTH_LONG).show();
		}

		// Media Responder setup
		mMediaResponder = new ComponentName(getPackageName(),
				MediaListener.class.getName());
	}

	private void startServices() {

		startService(new Intent(this, MusicPlayerService.class));
		startService(new Intent(this, ImageFetcherService.class));

	}

	private void bindServices() {

		// MusicPlayerService
		Intent connectionIntent = new Intent(this, MusicPlayerService.class);
		bindService(connectionIntent, mMPSConnection, Context.BIND_AUTO_CREATE);
		// ImageFetcherService
		Intent imageFetcherIntent = new Intent(this, ImageFetcherService.class);
		bindService(imageFetcherIntent, mIFSConnection,
				Context.BIND_AUTO_CREATE);

	}

	private void unbindServices() {

		unbindService(mMPSConnection);
		if (mIFSConnection != null)
			unbindService(mIFSConnection);

	}

	private void initFilter() {

		mFilter = new IntentFilter();
		mFilter.addAction(MusicPlayerService.NOTIFICATION);
		mFilter.addAction(ImageFetcherService.NOTIFICATION);
	}

	@Override
	protected void onResume() {
		super.onResume();
		registerReceiver(mReceiver, mFilter); // Registering receiver with
												// notifications
		mAudioManager.registerMediaButtonEventReceiver(mMediaResponder);
		/*
		 * if (mMediaResponder == null) Log.w(TAG, "MediaResponder null");
		 */
		// Load AdView
		// mAdView = (AdView) findViewById(R.id.adView);
		// AdRequest request = new AdRequest.Builder().build();

		// After first run, set this to false
		mIsStarting = false;
		setProgressBarIndeterminateVisibility(false); // Android 4.0 workaround
		if (mCurrentStation != null)
			setStation(mCurrentStation);

		final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 8, 0);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putBoolean(IS_CLICKABLE, mIsClickable);
		outState.putBoolean(IS_STARTING, mIsStarting);
		outState.putBoolean(SERVICE_STARTED, mServiceStated);
		outState.putInt(CURRENT_POSITION, mStations.indexOf(mCurrentStation));
		outState.putString(XML_CONTENT, mXMLContent);
	}

	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(mReceiver); // Unregistering receiver on pause
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbindServices();
		mAudioManager.unregisterMediaButtonEventReceiver(mMediaResponder);
	}

	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("RadioBox Closing")
				.setMessage("Are you sure you want to close this app?")
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								justStop();
								finish();
							}

						}).setNegativeButton("No", null).show();
	}

	private void setRadioInfo(String title) {

	try{
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(
				Intent.EXTRA_TEXT,
				"YOU ARE LISTENING " + title + " ON "
						+ mCurrentStation.getmName() + " "
						+ mCurrentStation.getWebsite());

		Songtitle=title;
		ShareActionProvider shareActionProvider = (ShareActionProvider)
		  mShareItem.getActionProvider();
		  
		 shareActionProvider.setShareIntent(intent);
	}
	catch(Exception e)
	{
		
	}
		mRadioFragment.setTitleText(title);
		if (!title.equals(MusicPlayerService.STATUS_PLAYING)
				&& !title.equals(MusicPlayerService.STATUS_STOPPED))
			mIFService.fetchCover(title);

	}

	private void restore(Bundle inState) {
		mIsClickable = inState.getBoolean(IS_CLICKABLE, true);
		mIsStarting = inState.getBoolean(IS_STARTING, true);
		mServiceStated = inState.getBoolean(SERVICE_STARTED, false);
		if (mServiceStated) {
			mIsPlaying = mMPService.isPlaying();
		} else {
			mIsPlaying = false;
		}
	}

	/*
	 * View onClick functions Handler onClick of button on fragment layout
	 */
	/*public void doPlay(View view) {
		final int sdk = android.os.Build.VERSION.SDK_INT;
		if (!mIsPlaying && view.getId() == R.id.btn_play) {
			playRadio();
			try{
			if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
				RadioStreamFragment.mButton.setBackgroundDrawable(getResources()
						.getDrawable(R.drawable.btn_play_radio));
			} else {
				RadioStreamFragment.mButton.setBackground(getResources()
						.getDrawable(R.drawable.btn_play_radio));
			}
			}catch(Exception e)
			{}
		} else  {
			stopRadio();
			try{
			if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
				RadioStreamFragment.mButton.setBackgroundDrawable(getResources()
							.getDrawable(R.drawable.btn_pause_radio));
				} else {
					RadioStreamFragment.mButton.setBackground(getResources()
							.getDrawable(R.drawable.btn_pause_radio));
				}
			}catch(Exception e)
			{}
		}
		
			
	}*/

	public void doPause(View view) {
		stopRadio();
	}

	public void doPrev(View view) {
		gotoPrev();
	}

	public void doNext(View view) {
		gotoNext();
	}

	public static void justPlay() {
		if (mMPService == null)
			return;
		((Activity) c).setProgressBarIndeterminateVisibility(true);
		mMPService.playRadio(mCurrentStream);
		mIsPlaying = true;
	}

	public static void justStop() {
		if (mMPService == null)
			return;
	   
		((Activity) c).setProgressBarIndeterminateVisibility(false);
		mMPService.stopRadio();
		mIsPlaying = false;
	}

	public static void playRadio() {
		synchronized (mPlayerLock) {
			if (!mIsPlaying) {
				((Activity) c).setProgressBarIndeterminateVisibility(true);
				mMPService.playRadio(mCurrentStream);
				mIsPlaying = true;
				
			} else {
				((Activity) c).setProgressBarIndeterminateVisibility(false);
				mMPService.pauseRadio();
				mIsPlaying = false;
			}
			mPlayerLock.notifyAll();
		}
	}

	public  static void stopRadio() {
		synchronized (mPlayerLock) {
			if (mIsPlaying) {
				((Activity) c).setProgressBarIndeterminateVisibility(false);
				mMPService.stopRadio();
			}
			mIsPlaying = false;
			mPlayerLock.notifyAll();
		}

	}

	private void pauseRadio() {
		synchronized (mPlayerLock) {
			if (mIsPlaying) {
				setProgressBarIndeterminateVisibility(false);
				mMPService.pauseRadio();
			}
			mIsPlaying = false;
			mPlayerLock.notifyAll();
		}

	}

	// Property access
	public void setStation(Station station) {
		mCurrentStation = station;
		// REFACTOR
		mCurrentStream = mCurrentStation.getmStreamUrl();

		// END REFACTOR

		// Smooth switch to current play
	/*	if (mViewPager.getCurrentItem() != 1)
			mViewPager.setCurrentItem(1, true);*/
	}

	public int getMaxVolume() {
		return mMaxVolume;
	}

	public int getVolume() {
		return mVolume;
	}

	// TODO
	public void gotoPrev() {
		int current = mStations.indexOf(mCurrentStation);
		if (current > 0) {
			setStation(mStations.get(current - 1));
			mContinue = true;
			justStop();
		}
	}

	public void gotoNext() {
		int current = mStations.indexOf(mCurrentStation);
		if (current < mStations.size()) {
			setStation(mStations.get(current + 1));
			mContinue = true;
			justStop();
		}
	}

	public OnSeekBarChangeListener mSeekBarListener = new OnSeekBarChangeListener() {
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			if (fromUser) {
				final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
				audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
						progress, 0);
			}
		}
	};

	public class MediaListener extends BroadcastReceiver {

		private static final String TAG = "ph";

		@Override
		public void onReceive(Context context, Intent intent) {
			// Media button handling
			// TODO
			try{
			Log.d(TAG, "Receiver action" + intent.getAction());
			if (intent.getAction().equals(Intent.ACTION_MEDIA_BUTTON)) {
				Log.d(TAG, "Receiver media action");
				KeyEvent event = (KeyEvent) intent
						.getParcelableExtra(Intent.ACTION_MEDIA_BUTTON);
				final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
				int volume = mRadioFragment.getVolume();
				int maxVolume = mRadioFragment.getMaxVolume();
				SeekBar volumeBar = (SeekBar) mRadioFragment.getVolumeSeekBar();
				if (event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN) {
					if (volume == 0)
						return;

					audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
							volume + 1, 0);
					mRadioFragment.setVolume(volume + 1);
					if (volumeBar != null)
						volumeBar.setProgress(volume + 1);
				} else if (event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP) {
					if (volume == maxVolume)
						return;

					audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
							volume - 1, 0);
					mRadioFragment.setVolume(volume - 1);
					if (volumeBar != null)
						volumeBar.setProgress(volume - 1);
				}
			}
			if (intent.getAction()
					.equals("android.media.VOLUME_CHANGED_ACTION")) {
				Log.d(TAG, "Change volume");
				int volume = mAudioManager
						.getStreamVolume(AudioManager.STREAM_MUSIC);
				SeekBar volumeBar = mRadioFragment.getVolumeSeekBar();
				if (volumeBar != null) {
					volumeBar.setProgress(volume);
					volumeBar.invalidate();
				} else
					Log.d(TAG, "Volume bar null");
			}
		}
		catch(Exception e){
			
		}}
	}

	private class PhoneCallListener extends PhoneStateListener {
		// Constants
		private static final String TAG = "PhoneCallListener";
		private boolean isPhoneCalling = false;

		// Handling when phone call happen
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			super.onCallStateChanged(state, incomingNumber);

			if (state == TelephonyManager.CALL_STATE_RINGING) {
				stopRadio();
				isPhoneCalling = true;
				Log.d(TAG, "Incoming phone call {" + incomingNumber
						+ "}, stop the radio");
			}

			if (state == TelephonyManager.CALL_STATE_OFFHOOK) {
				stopRadio();
				Log.d(TAG, "Phone offhook, stop the radio");
			}

			if (state == TelephonyManager.CALL_STATE_IDLE) {
				try {if (	isPhoneCalling )
					playRadio();} catch (Exception e) {

					}
				
				isPhoneCalling = false;
				Log.d(TAG, "Call end, restart the radio");
			}
		}

	}
}
