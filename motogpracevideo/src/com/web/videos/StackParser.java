package com.web.videos;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.lilait.motogp.race.video.racing.valintinorossi.motoracer.R;


public class StackParser extends YouTubeBaseActivity 
implements OnInitializedListener {
	public static String EP1_ID = "f5wLSoydthk";
	
	
	
	Button playButton;
	ImageButton pauseButton;
	
	
	YouTubeThumbnailView ep1View;	
	YouTubeThumbnailView ep2View;	
	YouTubeThumbnailView ep3View;
	
	ThumbnailListener thumbListner;
	
	private YouTubePlayerView playerView; 
	private YouTubePlayer playa;   //hollla
	
	public StackParser(){}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_you_tube_it);
		
		playButton = (Button)findViewById(R.id.watch);
		playButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try{
		            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + EP1_ID));
		            finish();
		            startActivity(intent);                 
		            }catch (ActivityNotFoundException ex){
		                Intent intent=new Intent(Intent.ACTION_VIEW, 
		                Uri.parse("http://www.youtube.com/watch?v="+EP1_ID));
		                finish();
		               startActivity(intent);
		            }	
			}
		});/*
		pauseButton = (ImageButton)findViewById(R.id.pause_button);
		pauseButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				playButton.setVisibility(View.VISIBLE);
				pauseButton.setVisibility(View.GONE);
				playa.pause();
			}
		});*/
		
	/*	ep1View = (YouTubeThumbnailView)findViewById(R.id.youtube_view1);
		ep1View.initialize(DeveloperKey.DEVELOPER_KEY, new ThumbnailListener(EP1_ID));
		*/
		
		playerView = (YouTubePlayerView) findViewById(R.id.youtube_player_view);
		playerView.initialize(DeveloperKey.DEVELOPER_KEY, this);
		playa = null;
		
	}

	
	/**
	 * YouTubePlayerView init listeners
	 */
	@Override
	public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult error)
	{
		
		try{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + EP1_ID));
            finish();
            startActivity(intent);                 
            }catch (ActivityNotFoundException ex){
                Intent intent=new Intent(Intent.ACTION_VIEW, 
                Uri.parse("http://www.youtube.com/watch?v="+EP1_ID));
                finish();
               startActivity(intent);
            }	
		
	}

	@Override
	public void onInitializationSuccess(YouTubePlayer.Provider provider,
			YouTubePlayer player, boolean wasRestored) {
		playa = player;
		playa.setPlaybackEventListener(new MHPlaybackEventListener());
		playa.setPlayerStateChangeListener(new MHPlayerStateChangeListener());
		playa.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
		playa.cueVideo(EP1_ID);
	}
	/**
	 * Thumbnail Listener
	 * @author  <- .. -> squint hedgehog
	 *
	 */
	private class ThumbnailListener implements YouTubeThumbnailView.OnInitializedListener{
		
		String videoID;
		
		public ThumbnailListener(String vidId){
			videoID = vidId;
		}

		@Override
		public void onInitializationFailure(YouTubeThumbnailView thumbView,
				YouTubeInitializationResult initResult) {
			
		}

		@Override
		public void onInitializationSuccess(YouTubeThumbnailView thumbView,
				YouTubeThumbnailLoader thumbLoader) {
			thumbLoader.setVideo(videoID);
			thumbView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					playa.cueVideo(videoID);
				}
			});
		}
		
	}
	
	/**
	 * Listener classes
	 * @author Robot King Zed
	 *
	 */
	private class MHPlaybackEventListener implements PlaybackEventListener {

		@Override
		public void onBuffering(boolean arg0) {
			Toast.makeText(StackParser.this, "onBuffering video...", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onPaused() {
			Toast.makeText(StackParser.this, "onPaused video...", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onPlaying() {
			Toast.makeText(StackParser.this, "onPlaying video...", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onSeekTo(int arg0) {
			Toast.makeText(StackParser.this, "onSeekTo video...", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onStopped() {
			Toast.makeText(StackParser.this, "onStopped video...", Toast.LENGTH_SHORT).show();
		}
	}

	private class MHPlayerStateChangeListener implements PlayerStateChangeListener  {

		@Override
		public void onAdStarted() {
			Toast.makeText(StackParser.this, "onAdStarted video...", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onError(ErrorReason arg0) {
			Toast.makeText(StackParser.this, "onError video...", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onLoaded(String arg0) {
			playa.play();
		//	pauseButton.setVisibility(View.VISIBLE);
			
		}

		@Override
		public void onLoading() {
			Toast.makeText(StackParser.this, "onLoading video...", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onVideoEnded() {
			Toast.makeText(StackParser.this, "onVideoEnded video...", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onVideoStarted() {
			Toast.makeText(StackParser.this, "onVideoStarted video...", Toast.LENGTH_SHORT).show();
		}

	}

}
class DeveloperKey {

	  /**
	   * Please replace this with a valid API key which is enabled for the 
	   * YouTube Data API v3 service. Go to the 
	   * <a href="https://code.google.com/apis/console/">Google APIs Console</a> to
	   * register a new developer key.
	   */
	  public static final String DEVELOPER_KEY = "AI39si5szZ9ybeMUGMYzzXsZ8dv5bcJpH_2AW2Q2rr_ung6BKC6FHQa9kPlitCM9erFAm7jobamHHw5-kIiC593R7U7xwrQycA";

	}
