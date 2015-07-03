package radioklub.sekhontech.com.service;

import java.io.IOException;

import radioklub.sekhontech.com.utils.ParserM3UToURL;
import radioklub.sekhontech.com.utils.Utils;

import com.spoledge.aacdecoder.MultiPlayer;
import com.spoledge.aacdecoder.PlayerCallback;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MusicPlayerService extends Service {
	//Constant
	public static final String NOTIFICATION = "com.vg.intent.notification.musicplayer";
	public static final String STATUS = "STATUS";
	public static final String STATUS_PLAYING = "Playing";
	public static final String STATUS_STOPPED = "Stopped";
	public static final String STATUS_BUFFERING = "Buffering";
	public static final String STATUS_SERVICE_STARTED = "ServiceStarted";
	
	public static final String PLAY_THIS_ONE = "PlayThisOne";

	//Member variables
	private static final String TAG = "MusicPlayerSevices";
	private StreamBinder mBinder;
	private MediaPlayer mMediaPlayer;
	private MultiPlayer mPlayer;
	private PlayerCallback mPlayerCallback;
	private Handler mHandler;
	private boolean mIsMP3Pause = false;
	
	//Radio state variables
	private String mRadioTitle;
	private boolean mIsPlaying = false;
	
	/* Service Lifecycle Event Handler
	 * (non-Javadoc)
	 * @see android.app.Service#onCreate()
	 */
	@Override
	public void onCreate() {
		mBinder = new StreamBinder();
		initMusicPlayer();
		super.onCreate();
		sendNotification(STATUS, STATUS_SERVICE_STARTED);
		mHandler = new Handler();
		Log.d(TAG, "onCreate complete");
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (mBinder == null)
			mBinder = new StreamBinder();
		if (mPlayer == null)
			initMusicPlayer();
		if (mMediaPlayer == null)
			mMediaPlayer = new MediaPlayer();
			
		handlingRequest(intent);
		Log.d(TAG, "onStartCommand complete");
		return Service.START_NOT_STICKY; //START_NOT_STICKY still work
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}
	/* MusicPlayerSevice functions
	 * 
	 */
	public void playRadio(final String url) {
		if (mIsMP3Pause) {
			mMediaPlayer.start();
			mIsMP3Pause = false;
		} else {
			//TODO
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
			        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
			        // Connectivity issue, we quit
			        if (networkInfo == null || networkInfo.getState() != NetworkInfo.State.CONNECTED) {
			            return;
			        }
	
					String newUrl = "";
					if (url.contains(".m3u")) {
						newUrl = ParserM3UToURL.parse(url);
					} else {
						newUrl = url;
					}
					final String finalUrl = newUrl;
					mHandler.post(new Runnable() {
						@Override
						public void run() {
							mIsPlaying = true;
							if (finalUrl.endsWith(".mp3")) {
								//TODO
								//Create media player to play instead
								Log.d(TAG, "Start media player");
								mPlayer.stop();
								try {
									mMediaPlayer.setDataSource(finalUrl);
									mMediaPlayer.prepareAsync();
								} catch (IllegalArgumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (SecurityException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalStateException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								mMediaPlayer.start();
								mIsMP3Pause = false;
							} else {
								Log.d(TAG, "Start multi player");
								mPlayer.playAsync(finalUrl);
							}
						}
					});
				}
			});
			thread.start();
		}
	}
	public void stopRadio() {
		mIsPlaying = false;
		if (mMediaPlayer.isPlaying()) {
			Log.d(TAG, "Stop media player");
			mMediaPlayer.stop();
		} else {
			Log.d(TAG, "Stop multi player");
			mPlayer.stop();
		}
	}
	public void pauseRadio() {
		mIsPlaying = false;
		if (mMediaPlayer.isPlaying()) {
			Log.d(TAG, "Pause media player");
			mIsMP3Pause = true;
			mMediaPlayer.pause();
		} else {
			Log.d(TAG, "Stop multi player");
			mPlayer.stop();
		}
	}
	public String getRadioTitle() {
		return mRadioTitle;
	}
	public boolean isPlaying() {
		return mIsPlaying;
	}
	
	//Internal function
	private void initMusicPlayer() {
		if (mPlayer == null) {
			mPlayerCallback = new PlayerCallback() {	
				
				@Override
				public void playerStopped(int perf) {
					sendNotification(STATUS, STATUS_STOPPED);
				}
				
				@Override
				public void playerStarted() {
					sendNotification(STATUS, STATUS_PLAYING);
				}
				
				@Override
				public void playerPCMFeedBuffer(boolean isPlaying, int bufSizeMs, int bufCapacityMs) {
					if (!isPlaying) {
						sendNotification(STATUS, STATUS_BUFFERING);
					}
				}
				
				@Override
				public void playerMetadata(String key, String value) {
					if (key != null && key.equals("StreamTitle")) {
						mRadioTitle = Utils.stripHtml(value);
						sendNotification(STATUS, mRadioTitle);
					}
				}
				
				@Override
				public void playerException(Throwable throwable) {
					final Throwable finalThrow = throwable;
					mHandler.post(new Runnable() {
						@Override
						public void run() {
							stopRadio();
							Toast.makeText(getApplicationContext(), finalThrow.getMessage()
									, Toast.LENGTH_LONG).show();
							sendNotification(STATUS, STATUS_STOPPED);
						}
					});
				}			
				@Override
				public void playerAudioTrackCreated(AudioTrack arg0) {}
			};
			//Workaround
		    try {
		        java.net.URL.setURLStreamHandlerFactory( new java.net.URLStreamHandlerFactory(){
		            public java.net.URLStreamHandler createURLStreamHandler( String protocol ) {
		                Log.d( TAG, "Asking for stream handler for protocol: '" + protocol + "'" );
		                if ("icy".equals( protocol )) return new com.spoledge.aacdecoder.IcyURLStreamHandler();
		                return null;
		            }
		        });
		    }
		    catch (Throwable t) {
		        Log.w( TAG, "Cannot set the ICY URLStreamHandler - maybe already set ? - " + t );
		    }
			mPlayer = new MultiPlayer(mPlayerCallback);
		}
	}
	private void handlingRequest(Intent intent) {
		Bundle bundle = intent.getExtras();
		if (bundle != null) {
			String url = bundle.getString(PLAY_THIS_ONE);
			if (url != null) {
				playRadio(url);
				Log.d(TAG, "Receive playing request : " + url);
			}
			else {
				stopRadio();
				Log.d(TAG, "Receive stop request");
			}
		}
	}
	private void sendNotification(String key, String value) {
		Intent intent = new Intent(NOTIFICATION);
		intent.putExtra(key, value);
		sendBroadcast(intent);
	}

	// Nested class
	public class StreamBinder extends Binder {
		public MusicPlayerService getService() {
			return MusicPlayerService.this;
		}
	}

}
