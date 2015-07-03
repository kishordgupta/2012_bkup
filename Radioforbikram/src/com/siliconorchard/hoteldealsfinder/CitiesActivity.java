package com.siliconorchard.hoteldealsfinder;

import java.io.IOException;
import java.util.ArrayList;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.OnWheelScrollListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.AbstractWheelTextAdapter;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.atomix.kurowiz.supports.GiftInfo;
import com.atomix.kurowiz.supports.MyScrollView;
import com.lilait.MusicPlayerService;
import com.lilait.RecordedFiles_player;

public class CitiesActivity extends FragmentActivity {
	// Scrolling flag
	private static boolean scrolling = false;
	
	// private APIFactory apiFactory;
	public static Context c = null;
	
	static public String gifttype = "";
	static public int giftid = 0;
	
	static public ArrayList<GiftInfo> arrayList = new ArrayList<GiftInfo>();
	

	// Constant
	

	public String[] mstations;
	public static String asyncUrl = "";
	// Flag variable
	public MediaPlayer mediaPlayer=null;
	public MediaPlayer player=null;

	// Non-View member variables

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cities_layout);
		c = this;
		IntentFilter filter = new IntentFilter();
		filter.addAction("android.intent.action.MEDIA_BUTTON");
		
	
       
	
		/*
		 * View rootView = inflater.inflate(R.layout.cities_layout, container,
		 * false);
		 */
		String countries[] = new String[arrayList.size()];
		// Countries flags
		String flags[] = new String[arrayList.size()];
		int i = 0;
		mstations = new String[arrayList.size()];
		for (GiftInfo g : arrayList) {

			countries[i] = g.getHotel_name();
			flags[i] = g.getImages();
			mstations[i] = g.getHotel_link();

			i++;

		}
		initializeMediaPlayer(mstations[0]);
		final WheelView country = (WheelView) findViewById(R.id.country);
		country.setVisibleItems(3);
		country.setViewAdapter(new CountryAdapter(this, countries, flags));

	

		country.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				if (!scrolling) {
					// updateCities(city, cities, newValue);
					//mediaPlayer = MediaPlayer.create(c, Uri.parse(mstations[newValue]));
					//mediaPlayer.start();
				}
			}
		});

		country.addScrollingListener(new OnWheelScrollListener() {
			public void onScrollingStarted(WheelView wheel) {
				scrolling = true;
			}

			public void onScrollingFinished(WheelView wheel) {
				scrolling = false;
				stopPlaying(mstations[country.getCurrentItem()]);
				startPlaying(mstations[country.getCurrentItem()]);
				 Toast.makeText(c, mstations[country.getCurrentItem()], Toast.LENGTH_LONG).show();
			/*	if(mediaPlayer!=null)
					mediaPlayer.stop();
				try{
				mediaPlayer = MediaPlayer.create(c, Uri.parse(mstations[country.getCurrentItem()]));
				mediaPlayer.prepare();
				mediaPlayer.start();
				}
				catch(Exception e)
				{
					
				}*/
				// updateCities(city, cities, country.getCurrentItem());
			}
		});

		country.setCurrentItem(1);
		
		// setContentView(R.layout.cities_layout);

	}

	 private void startPlaying(String station) {
	      
            try {
            	  player.prepareAsync();
			} catch (IllegalStateException e) {
				// TODO: handle exception
				//player.reset();
				
			}
	      

	        player.setOnPreparedListener(new OnPreparedListener() {

	            public void onPrepared(MediaPlayer mp) {
	                player.start();
	            }
	        });

	    }

	    private void stopPlaying(String station) {
	        if (player.isPlaying()) {
	            player.stop();
	            player.reset();
	            initializeMediaPlayer(station);
	        }

	    
	    }

	    private void initializeMediaPlayer(String station) {
	        player = new MediaPlayer();
	        try {
	            player.setDataSource(c,Uri.parse(station));
	            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
	        
	            
	        } catch (IllegalArgumentException e) {
	            e.printStackTrace();
	        } catch (IllegalStateException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        Log.d("Buffering", "" + station);
	        player.setOnBufferingUpdateListener(new OnBufferingUpdateListener() {

	            public void onBufferingUpdate(MediaPlayer mp, int percent) {
	               // playSeekBar.setSecondaryProgress(percent);
	                Log.d("Buffering", "" + percent);
	                //Toast.makeText(c, "Buffering...", Toast.LENGTH_SHORT).show();
	            }
	        });
	    }

	    @Override
	    protected void onPause() {
	        super.onPause();
	        if (player.isPlaying()) {
	            player.stop();
	        }
	    }
	/*private class PhoneCallListener extends PhoneStateListener {
		// Constants
		private static final String TAG = "PhoneCallListener";

		// Handling when phone call happen
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			super.onCallStateChanged(state, incomingNumber);

			if (state == TelephonyManager.CALL_STATE_RINGING) {
				// stopRecordedPlayer_toStartanotherFile();
				stopRadio();
				Log.d(TAG, "Incoming phone call {" + incomingNumber
						+ "}, stop the radio");
			}

			if (state == TelephonyManager.CALL_STATE_OFFHOOK) {
				stopRadio();
				// stopRecordedPlayer_toStartanotherFile();
				Log.d(TAG, "Phone offhook, stop the radio");
			}

			if (state == TelephonyManager.CALL_STATE_IDLE) {
				if (mServiceStated)
					// playRadio();
					Log.d(TAG, "Call end, restart the radio");
			}
		}

	}*/

	public class CountryAdapter extends AbstractWheelTextAdapter {
		// Countries names
		private String countries[] = new String[] {};
		// Countries flags
		private String flags[] = new String[] {};

		public CountryAdapter(Context context) {

			super(context, R.layout.country_layout, NO_RESOURCE);

			setItemTextResource(R.id.country_name);
		}

		/**
		 * Constructor
		 */
		public CountryAdapter(Context context, String[] con, String[] flag) {
			super(context, R.layout.country_layout, NO_RESOURCE);
			countries = con;
			flags = flag;

			setItemTextResource(R.id.country_name);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);
			WebView img = (WebView) view.findViewById(R.id.flag);
			String S = "<html> <head> <title>banner</title> </head><body>   <img src=";
			S = S
					+ "\""
					+ flags[index]
					+ "\""
					+ "alt=\"\"width=\"100%\" height=\"100%\"/></a></body></html>";
			img.getSettings().setAppCacheEnabled(true);
			img.getSettings().setLoadWithOverviewMode(true);
			img.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
			img.getSettings().setUseWideViewPort(true);
			img.loadData(S, "text/html", "UTF-8");

			return view;
		}

		@Override
		public int getItemsCount() {
			return countries.length;
		}

		@Override
		protected CharSequence getItemText(int index) {
			return countries[index];
		}
	}

}
