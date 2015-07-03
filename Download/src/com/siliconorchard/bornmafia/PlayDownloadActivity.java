package com.siliconorchard.bornmafia;



import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import com.siliconorchard.bornmafia.R;


import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.DownloadManager.Request;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Toast;

public class PlayDownloadActivity extends Activity {
    private long enqueue;
    private DownloadManager dm;
    public static String audiofile="";
   // public static WebView w = null;
    public static LinearLayout wa = null;
    ProgressDialog pd = null;
    MediaPlayer mPlayer;
    Handler handler;
    SeekBar seekBar;
    private int mediaPos;
    private int mediaMax;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		setContentView(R.layout.playdownload);
		 getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	//	getActionBar().setTitle("Listen and Download");
		 this.setFinishOnTouchOutside(false);
		/* BroadcastReceiver receiver = new BroadcastReceiver() {
	            @Override
	            public void onReceive(Context context, Intent intent) {
	                String action = intent.getAction();
	                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
	                    long downloadId = intent.getLongExtra(
	                            DownloadManager.EXTRA_DOWNLOAD_ID, 0);
	                    Query query = new Query();
	                    query.setFilterById(enqueue);
	                    Cursor c = dm.query(query);
	                    if (c.moveToFirst()) {
	                        int columnIndex = c
	                                .getColumnIndex(DownloadManager.COLUMN_STATUS);
	                        if (DownloadManager.STATUS_SUCCESSFUL == c
	                                .getInt(columnIndex)) {
	 
	                            ImageView view = (ImageView) findViewById(R.id.imageView1);
	                            String uriString = c
	                                    .getString(c
	                                            .getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
	                            view.setImageURI(Uri.parse(uriString));
	                        }
	                    }
	                }
	            }
	        };
	 
	        registerReceiver(receiver, new IntentFilter(
	                DownloadManager.ACTION_DOWNLOAD_COMPLETE));
		
	*/
	/* w = (WebView)findViewById(R.id.playweb);
	 w.setVisibility(View.INVISIBLE);
	*/ wa = (LinearLayout)findViewById(R.id.ain);
	 wa.setVisibility(View.GONE);
	 Button b =(Button)findViewById(R.id.dl);
	 b.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Download();
		}
	});
	 
       seekBar = (SeekBar)findViewById(R.id.prog);
       seekBar.setVisibility(View.GONE);
	 
	 final Context c =this;
	 Button p = (Button)findViewById(R.id.playlisten);
	 Button pa = (Button)findViewById(R.id.paus);
	 pa.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			try{
				if(mPlayer!=null && mPlayer.isPlaying()){
		            mPlayer.pause();
		            v.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.btn_shuffle_focused));
		          //  mPlayer.release();
		          //  wa.setVisibility(View.GONE);
		        }
				else
				{  v.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.btn_pause));
				      if(mPlayer!=null)
				    	  mPlayer.start();
				}
				/*if(mPlayer!=null && mPlayer.isPlaying()==false){
					 wa.setVisibility(View.VISIBLE);
						Toast.makeText(c, "Please wait to load the stream ",Toast.LENGTH_LONG).show();
						if(mPlayer!=null && mPlayer.isPlaying()){
				            mPlayer.stop();
				            
				        }
						if(mPlayer!=null)
						{
							mPlayer.reset();
						}
						mPlayer = new MediaPlayer();
				        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
				        try {
				            mPlayer.setDataSource(audiofile);
				            
				        } catch (IllegalArgumentException e) {
				            Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
				        } catch (SecurityException e) {
				            Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
				        } catch (IllegalStateException e) {
				            Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
				        } catch (IOException e) {
				            e.printStackTrace();
				        }
				        
				        try {
				            mPlayer.prepare();
				        } catch (IllegalStateException e) {
				            Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
				        } catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				        
				        mPlayer.start();
						Toast.makeText(c, "Please wait to load the stream "  ,Toast.LENGTH_LONG).show();
		        }
				*/}
				catch(Exception e)
				{}
		}
	});
	 
	
		p.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)  {
				// TODO Auto-generated method stub
				//String S= 	"<html> <head> <title>banner</title> </head><body>   <audio width=\"100%\" height=\"100%\" controls=\"controls\" autoplay=\"autoplay\" src=\""+audiofile+"\"></audio>";
				//w.loadData(S,  "text/html", "UTF-8");
				 wa.setVisibility(View.VISIBLE);
				
				Toast.makeText(c, "Please wait to load the stream ",Toast.LENGTH_LONG).show();
			try{
				if(mPlayer!=null && mPlayer.isPlaying()){
		            mPlayer.stop();
		            
		        }
				if(mPlayer!=null)
				{
					mPlayer.reset();
				}
			}
			catch(Exception e)
			{
				
			}
				mPlayer = new MediaPlayer();
		        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		        try {
		            mPlayer.setDataSource(audiofile);
		            
		        } catch (IllegalArgumentException e) {
		            Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
		        } catch (SecurityException e) {
		            Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
		        } catch (IllegalStateException e) {
		            Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        pd = new ProgressDialog(c);
		        pd.setMessage("Stream loading please wait few second to turn on the player");
		        pd.setCancelable(true);
		        try {
		            mPlayer.prepareAsync();
		            pd.show();//c,"Buffering","Stream loading please wait few second to turn on the player",false,true);
		    		
		            
		        } catch (IllegalStateException e) {
		            Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
		        }
		        mPlayer.setOnPreparedListener(new OnPreparedListener() {  
		                      @Override  
		        	                 public void onPrepared(MediaPlayer mp) {  
		                    	  if(pd!=null)
		                    	  {
		                    		  if(pd.isShowing())
		                    		  pd.dismiss();
		                    	  }
		                    	  handler = new Handler(); 
		                    	  // Now dismis progress dialog, Media palyer will start playing  
		                    	  mPlayer.start();  
		                    	  seekBar.setVisibility(View.VISIBLE);
		                    	  seekBar.setOnSeekBarChangeListener(seekBarOnSeekChangeListener);

		                       

		                          mediaPos = mp.getCurrentPosition();

		                          mediaMax = mp.getDuration();

		                          seekBar.setMax(mediaMax); // Set the Maximum range of the
		                                                      // seekBar.setProgress(mediaPos);// set
		                                                      // current progress to song's
		                          seekBar.setProgress(mediaPos);// set current progress to song's

		                          handler.removeCallbacks(moveSeekBarThread);
		                          handler.postDelayed(moveSeekBarThread, 100);

		        	               }  
		        	          });  
		        
			//	Toast.makeText(c, "Please wait to load the stream "  ,Toast.LENGTH_LONG).show();
				
			}
		});
		registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
	// loadinwebview();	
		
	}
	BroadcastReceiver onComplete=new BroadcastReceiver() {
	    public void onReceive(Context ctxt, Intent intent) {
	        // Do Something
	    	   Intent i = new Intent();
	           i.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
	           startActivity(i);
	    }
	};
	 public void Download() {
		dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);

		Request request = new Request(Uri.parse(audiofile));
		String s = (Calendar.getInstance().getTime()).toString().replace(".",
				"")
				+ ".mp3";
		request.setDestinationInExternalFilesDir(this,
				Environment.DIRECTORY_MUSIC, s);
		request.setDescription("Downloadin audiofile " + audiofile);
		request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
		enqueue = dm.enqueue(request);
		Toast.makeText(this, "Download started ", Toast.LENGTH_LONG).show();
	 
	    }
	/*public void loadinwebview()
	{
	
		try{
		String S= 	"<html> <head> <title>banner</title> </head><body>   <audio width=\"100%\" height=\"100%\" controls=\"controls\" autoplay=\"autoplay\" src=\""+audiofile+"\"></audio>";
		w.loadData(S,  "text/html", "UTF-8");
		
		
		//ProgressDialog.show(this,"Buffering","Stream loading please wait few second to turn on the player",false,true);
	
	
		Toast.makeText(this, "Please wait to load the stream ",Toast.LENGTH_LONG).show();}
		catch(Exception e)
	{
			
		}
	}*/
	  private Runnable moveSeekBarThread = new Runnable() {

	        public void run() {
	        try{	
	            if (mPlayer.isPlaying()) {

	                int mediaPos_new = mPlayer.getCurrentPosition();
	                int mediaMax_new = mPlayer.getDuration();

	                seekBar.setMax(mediaMax_new);
	                seekBar.setProgress(mediaPos_new);

	                handler.postDelayed(this, 100); // Looping the thread after 0.1
	                                                // seconds
	            }
	        }
	        catch(Exception e)
	        {}
	            /*else {
	                int mediaPos_new = mPlayer.getCurrentPosition();
	                int mediaMax_new = mPlayer.getDuration();

	                seekBar.setMax(mediaMax_new);
	                seekBar.setProgress(mediaPos_new);

	                handler.postDelayed(this, 100); // Looping the thread after 0.1
	                                                // seconds
	            }*/
	        }
	    };
	    
	    SeekBar.OnSeekBarChangeListener seekBarOnSeekChangeListener = new SeekBar.OnSeekBarChangeListener() {

	        @Override
	        public void onStopTrackingTouch(SeekBar seekBar) {
	            // TODO Auto-generated method stub

	        }

	        @Override
	        public void onStartTrackingTouch(SeekBar seekBar) {
	            // TODO Auto-generated method stub

	        }

	        @Override
	        public void onProgressChanged(SeekBar seekBar, int progress,
	                boolean fromUser) {
	            // TODO Auto-generated method stub
	        	  try{	
	  	           
	            if (fromUser) {
	            	mPlayer.seekTo(progress);
	                seekBar.setProgress(progress);
	            }
	        	  }   catch(Exception e)
	  	        {}
	        	  
	        }
	    };


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
	//	w.loadUrl("about:blank");
		/*if(mPlayer!=null && mPlayer.isPlaying()){
            mPlayer.stop();
        }*/
		try{
		if(mPlayer!=null && mPlayer.isPlaying()){
            mPlayer.stop();
            mPlayer.release();
        }
		}
		catch(Exception e)
		{}
		
		try{
			 handler.removeCallbacks(moveSeekBarThread);
			}
			catch(Exception e)
			{}
		super.onDestroy();
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		try{	if(mPlayer!=null && mPlayer.isPlaying()){
            mPlayer.stop();
            mPlayer.release();
        }
		
		
	}
	catch(Exception e)
	{}
		try{
		 handler.removeCallbacks(moveSeekBarThread);
		}
		catch(Exception e)
		{}
			
		super.onBackPressed();
	}
}
