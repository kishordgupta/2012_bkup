package com.lilait;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import com.siliconorchard.hoteldealsfinder.CitiesActivity;




import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class RecordedFiles_player extends Fragment {

	 private MediaPlayer _mp3Player;
	  public static String URI=null;
	    // Create Instance of Play and Stop Buttons
	    private ImageButton _btnPlayMp3;
	    private ImageButton _btnPauseMP3;
	    ImageButton _btnStopMp3;
	    public int mFileDuration;
	    private CitiesActivity hm;
	    // Create Status Label TextView
	    private TextView _statusLabel;
	    private static boolean isPlaying=false; 
	 
	    // Create Instance of ProgressBar to display progress of playing song
	    private ProgressBar _mediaProgress;
	 
	    // Create TimerTask, Handler, and Timer to get status of player and update the ProgressBar
	    private TimerTask _progressTask;
	    private Handler _progressHandler;
	    private Timer _progressTimer;
	    public static boolean pause=false;
	    public static int seekTime; 
	    public TextView duration,timer_record_tv;
	    
	    long startTime = 0;
	    long pauseMod=0;
	    long saveTimeinPause=0;
		long millis=0;
		public static int seconds=0;
		public static int minutes=0;
	    
	    Handler timerHandler;
	    Runnable timerRunnable = new Runnable() {

	        public void run() {
	        	millis =pauseMod+ System.currentTimeMillis() - startTime;
	            seconds = (int) (millis / 1000);
	             minutes = seconds / 60;
	            seconds = seconds % 60;
	            
	             timer_record_tv.setText(String.format("%d:%02d", minutes, seconds));

	            timerHandler.postDelayed(this, 1000);
	        }
	    };

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
		View view = null;//inflater.inflate(R.layout.recorded_files_player, null);
		/*
	    _mp3Player = new MediaPlayer();
        hm=(CitiesActivity) getActivity();
        _btnPlayMp3 = (ImageButton) (view.findViewById(R.id.btn_play_recorder));
        _btnPlayMp3.setEnabled(true);
        _btnStopMp3 = (ImageButton)view.findViewById(R.id.button_stop_recorder);
        _btnStopMp3.setEnabled(false);
       _btnPauseMP3=(ImageButton)view.findViewById(R.id.btn_pause_recorder);    
       _btnPauseMP3.setEnabled(false);
        _mediaProgress = (ProgressBar)view.findViewById(R.id.progressBar1);
        duration=(TextView)view.findViewById(R.id.duration);
        timer_record_tv=(TextView)view.findViewById(R.id.Timer_Record_tv);
        _statusLabel = (TextView)view.findViewById(R.id.tv_title_Recorder);
        _statusLabel.setText("Stopped");
 
        // Set the OnClick event Listener for the Play Button
    _btnPlayMp3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        	if(URI!=null){
            play();
            _btnPlayMp3.setEnabled(false);
        	}
        }
    });
 
        // Set the OnClick event Listener for the Play Button
    _btnStopMp3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        	if(URI!=null)
            stop();
        }
    });
    
    _btnPauseMP3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(_mp3Player.isPlaying()){
            	_mp3Player.pause();
            	
            	timerHandler.removeCallbacks(timerRunnable);
            	timerHandler=null;
            	pauseMod=millis;
            	_btnPlayMp3.setEnabled(true);
            	seekTime=_mp3Player.getCurrentPosition();
            	isPlaying=false;
            	pause=true;
            }
        }
    });
      
 
	
	
	 // When Media Player is prepared start playing music, start progress
	  //reoccurring handler
	  _mp3Player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
	      @Override
	      public void onPrepared(MediaPlayer mp) {
	          _mediaProgress.setMax(_mp3Player.getDuration());
	          mFileDuration= _mp3Player.getDuration();
	          setDurationTime(mFileDuration);
	          _mp3Player.start();
	          timerHandler= new Handler();
	          startTime = System.currentTimeMillis();
	          timerHandler.postDelayed(timerRunnable, 0); 
	           isPlaying=true;
	          _btnStopMp3.setEnabled(true);
	          _btnPauseMP3.setEnabled(true);
	          
	           _statusLabel.setText("Playing");
	                
	           _progressHandler = new Handler();
	               _progressTask = new TimerTask(){
	   
	                  @Override
	                  public void run() {
	                  _progressHandler.post(new Runnable(){
	                      @Override
	                      public void run() {
	                              _mediaProgress.setProgress(_mp3Player.getCurrentPosition());
	                      }
	                  });
	                  }
	                       
	          };	                
	           _progressTimer = new Timer();
	           _progressTimer.schedule(_progressTask, 1000, 1000);
	      }
	  });
	   
	  // When Media Player is buffering set Status label accordingly.  Used if streaming remote file only.
	  _mp3Player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
	           
	      @Override
	      public void onBufferingUpdate(MediaPlayer mp, int percent) {
	          _statusLabel.setText("Buffering " + percent + " %");
	               
	      }
	  });
	   
	  // When song is finished playing call the stop method to clean up
	  _mp3Player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
	           
	      @Override
	      public void onCompletion(MediaPlayer mp) {
	          stop();
	      }
	  });
	   
	  // When MediaPlayer sends info updates set status label accordingly
	  _mp3Player.setOnInfoListener(new MediaPlayer.OnInfoListener() {
	           
	      @Override
	      public boolean onInfo(MediaPlayer mp, int what, int extra) {
	               
	          if (what == MediaPlayer.MEDIA_INFO_BUFFERING_START)
	          {
	              _statusLabel.setText("Buffering Started ");
	          }
	          else if (what == MediaPlayer.MEDIA_INFO_BUFFERING_END)
	          {
	              _statusLabel.setText("Buffering Stopped ");
	          }
	               
	          return false;
	      }
	  });   
	 
	  */
	  return view;
	  
	    }
	///////////////////////** END of onCreate**////////////////////////////////////
	
    public void setDurationTime(int time){
	 int seconds = (int) (time / 1000) % 60 ;
	 int minutes = (int) ((time / (1000*60)) % 60);
	 int hours   = (int) ((time / (1000*60*60)) % 24);
	 
	 duration.setText(String.valueOf(hours)+":"+String.valueOf(minutes)+":"+
	                  String.valueOf(seconds));
	 
	    }
    
    public boolean isRecordPlaying(){
    	return isPlaying;
    }
      
      public void setURI(String uri){
    	  URI=uri;
      }
      
 
	    	    
	    public void play()
	    {	
	    	/*if(_mp3Player!=null && pause==true){
        	  _mp3Player.seekTo(seekTime);
        	  if(hm.isPlaying()){
        		  hm.justStop();
        	  }
        	 _mp3Player.start();
        	 isPlaying=true;
        	  pause=false;
        	  timerHandler=new Handler(); //creating the handler again
        	  startTime=System.currentTimeMillis();
        	  timerHandler.postDelayed(timerRunnable, 0);
          }else{
	        try {
	              
	         _mp3Player.setDataSource(getActivity(),
	         Uri.parse(URI));
	         if(hm.isPlaying()){
       		  hm.justStop();
       	  }      
	         // where mFileDuration is mMediaPlayer.getDuration();
	        _mp3Player.prepareAsync();
	               
	        _statusLabel.setText("Preparing Player");
	                 
	        } catch (IllegalArgumentException e) {
	                 
	            e.printStackTrace();
	        } catch (SecurityException e) {
	                 
	            e.printStackTrace();
	        } catch (IllegalStateException e) {
	                 
	            e.printStackTrace();
	        } catch (IOException e) {
	                 
	            e.printStackTrace();
	        }
          }*/
	    }
	     
	    // When the stop method is called cancel the progress timer and reset the MediaPlayer
	    public void stop()
	    {
	        /*// Make sure this is null before canceling
	            if (_progressTimer != null)
	            {
	                _progressTimer.cancel();
	            }
	        
	            pauseMod=0; // as no need to save it after the song is stopped
	          _progressHandler = null;
	          _progressTask = null;
	          if(timerHandler!=null)
	                  timerHandler.removeCallbacks(timerRunnable);
	          timer_record_tv.setText("");
	            _mp3Player.stop();
	            isPlaying=false;
	            _mp3Player.reset();
	             
	            _statusLabel.setText("Stopped");
	        _btnPlayMp3.setEnabled(true);
	        _btnStopMp3.setEnabled(false);
	         pause=false;
	         _btnPauseMP3.setEnabled(false);
	         duration.setText("");*/
	    }
	     
	    // Calling stop method above right before the app is shutdown ensures that the MediaPlayer is shutdown
	    // if it is currently playing the mp3.
	    @Override
		public void onDestroy() {
	        stop();     
	        super.onDestroy();
	    }
	
	
	
}
