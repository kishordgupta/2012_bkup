package com.asolab.jazzplaceradio;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import com.asolab.jazzplaceradio.R;
import com.google.ads.AdRequest;
import com.google.ads.AdView;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;

import android.app.Activity;
import android.app.ProgressDialog;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener, OnTouchListener, OnCompletionListener, OnBufferingUpdateListener{
	
	private ImageButton buttonPlayPause;
	private ImageView imageViewRadioIcon;
	private SeekBar seekBarProgress;
	private TextView textViewCurrentSong;
	//private ListView programListView;
	
	private MediaPlayer mediaPlayer;
	private int mediaFileLengthInMilliseconds; // this value contains the song duration in milliseconds. Look at getDuration() method in MediaPlayer class
	
	private boolean isEverClicked=false;
	
	private final Handler handler = new Handler();
	private Timer currentSongTimer;
	AdView adView;
	//streaming url for radio, supports NSV
	private String streamingUrl="";
	private String currentSongUrl="http://www.jazzplaceradio.it/sections/currentSong";
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        
        AdRequest adRequest = new AdRequest();
		adView= (AdView)findViewById(R.id.adView);
		adView.loadAd(adRequest);
		streamingUrl="http://94.23.67.172:9156/;?"+getCurrentUNIXTime()+".mp3";		
		//programListView.setAdapter(new MyCustomBaseAdapter(getApplicationContext(),getPreparedProgramData()));
		//textViewCurrentSong.setText("Now playing: Streaming is not active");
    }
    
    private ArrayList<ProgramDefinition> getPreparedProgramData(){
    	
    	ArrayList<ProgramDefinition> programArrayList = new ArrayList<ProgramDefinition>(); 
    	Resources res = getResources();
    	String[] programs = res.getStringArray(R.array.programs);

    	for(int i=0;i<programs.length;i=i+2){
    		programArrayList.add(new ProgramDefinition(programs[i],programs[i+1]));
    	}
    	
    	//Toast.makeText(getApplicationContext(), "size"+programArrayList.size(), Toast.LENGTH_LONG).show();
    	
    	return programArrayList;
    	
    }
        
    /** This method initialise all the views in project*/
    private void initView() {
		buttonPlayPause = (ImageButton)findViewById(R.id.ButtonTestPlayPause);
		buttonPlayPause.setOnClickListener(this);
		
		seekBarProgress = (SeekBar)findViewById(R.id.SeekBarTestPlay);	
		seekBarProgress.setMax(99); // It means 100% .0-99
		seekBarProgress.setOnTouchListener(this);
		
		mediaPlayer = new MediaPlayer();
		mediaPlayer.setOnBufferingUpdateListener(this);
		mediaPlayer.setOnCompletionListener(this);
		
		//imageViewRadioIcon = (ImageView) findViewById(R.id.imageView1);
		//imageViewRadioIcon.setImageDrawable(getResources().getDrawable(R.drawable.logo_radio));
		
		//programListView = (ListView) findViewById(R.id.programList);
		
		textViewCurrentSong = (TextView) findViewById(R.id.currentSongView);		
	}
    
    
    
    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	super.onBackPressed();
    	finish();
    }

	/** Method which updates the SeekBar primary progress by current song playing position*/
    private void primarySeekBarProgressUpdater() {
    	seekBarProgress.setProgress((int)(((float)mediaPlayer.getCurrentPosition()/mediaFileLengthInMilliseconds)*100)); // This math construction give a percentage of "was playing"/"song length"
		if (mediaPlayer.isPlaying()) {
			Runnable notification = new Runnable() {
		        public void run() {
		        	primarySeekBarProgressUpdater();
				}
		    };
		    handler.postDelayed(notification,1000);
    	}
    }
    private long getCurrentUNIXTime(){
    	return (int) (System.currentTimeMillis());
    }

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.ButtonTestPlayPause){
			if(haveNetworkConnection()){
				 /** ImageButton onClick event handler. Method which start/pause mediaplayer playing */
				streamingUrl= "http://94.23.67.172:9156/;?"+getCurrentUNIXTime()+".mp3";
				if(isEverClicked==false)
					{
						new LongOperation().execute("");
						currentSongTimer= new Timer("com.asolab.jazzplaceradio", true);
						currentSongTimer.scheduleAtFixedRate(new PerodicCurrentSongTask(),1000,30000);
						isEverClicked=true;
					}
				else{
					mediaFileLengthInMilliseconds = mediaPlayer.getDuration(); // gets the song length in milliseconds from URL
					
					if(!mediaPlayer.isPlaying()){
						mediaPlayer.start();
						buttonPlayPause.setImageResource(R.drawable.button_pause);
					}else {
						mediaPlayer.pause();
						buttonPlayPause.setImageResource(R.drawable.button_play);
					}
					
					primarySeekBarProgressUpdater();
				}

			}
			else{
				Toast.makeText(getApplicationContext(), "No active internet connection", Toast.LENGTH_LONG).show();
			}
			
		}
	}
	
	@SuppressLint("NewApi") private class LongOperation extends AsyncTask<String, Void, String>{
		
		ProgressDialog progressDialog;

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			startNews();
			return null;
		}
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			progressDialog = ProgressDialog.show(MainActivity.this,"Loading","Loading time may vary depending on your internet speed.",true,false,null);
			super.onPreExecute();
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			progressDialog.dismiss();
			mediaFileLengthInMilliseconds = mediaPlayer.getDuration(); // gets the song length in milliseconds from URL
			
			if(!mediaPlayer.isPlaying()){
				mediaPlayer.start();
				buttonPlayPause.setImageResource(R.drawable.button_pause);
			}else {
				mediaPlayer.pause();
				buttonPlayPause.setImageResource(R.drawable.button_play);
			}
			
			primarySeekBarProgressUpdater();
		}
		
	}
	
	
	
	public void startNews(){
		try {
			mediaPlayer.setDataSource(streamingUrl); // setup song from http://www.hrupin.com/wp-content/uploads/mp3/testsong_20_sec.mp3 URL to mediaplayer data source
			mediaPlayer.prepare(); // you must call this method after setup the datasource in setDataSource method. After calling prepare() the instance of MediaPlayer starts load data from URL to internal buffer. 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(v.getId() == R.id.SeekBarTestPlay){
			/** Seekbar onTouch event handler. Method which seeks MediaPlayer to seekBar primary progress position*/
			if(mediaPlayer.isPlaying()){
		    	SeekBar sb = (SeekBar)v;
				int playPositionInMillisecconds = (mediaFileLengthInMilliseconds / 100) * sb.getProgress();
				mediaPlayer.seekTo(playPositionInMillisecconds);
			}
		}
		return false;
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		 /** MediaPlayer onCompletion event handler. Method which calls then song playing is complete*/
		buttonPlayPause.setImageResource(R.drawable.button_play);
	}

	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent) {
		/** Method which updates the SeekBar secondary progress by current song loading from URL position*/
		seekBarProgress.setSecondaryProgress(percent);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		mediaPlayer.stop();
		super.onDestroy();
	}
	private class PerodicCurrentSongTask extends TimerTask {
		
	    public void run(){
	    	new GetCurrentSongAsyncTask().execute(currentSongUrl);
	    }

	}
	
	private class GetCurrentSongAsyncTask extends AsyncTask<String, Void, String>{

		InputStream is = null;
		String result = "";
		JSONObject jsonObject = null;

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			try {
				// HTTP
				HttpClient httpclient = new DefaultHttpClient(); // for port 80 requests!
				HttpPost httppost = new HttpPost(params[0]);
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				is = entity.getContent();
				// Read response to string	    	
				BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"),8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();
				result = sb.toString();
				return result;
			} catch(Exception e) {
				Log.e("jazz place radio", "1st null");
				return null;
			}
			
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			// Convert string to object
			if(result!=null){
				try {
					jsonObject = new JSONObject(result);
					textViewCurrentSong.setText(jsonObject.get("title").toString());
				} catch(JSONException e) {
					Log.e("jazz place radio", "JSON EXCEPTION\n"+e.getMessage());			
				}
			}
		}	
	}
	private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }


	
	
}
