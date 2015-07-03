package xoxo.sound;

import java.util.HashMap;

import com.unlock.briefcase.password.R;


import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundManager {

	static private SoundManager _instance;
	private static SoundPool mSoundPool;
	private static HashMap<Integer, Integer> mSoundPoolMap;
	private static AudioManager mAudioManager;
	private static Context mContext;
	public static boolean musicFlag = true;

	private SoundManager() {
	}

	/**
	 * Requests the instance of the Sound Manager and creates it if it does not
	 * exist.
	 * 
	 * @return Returns the single instance of the SoundManager
	 */
	static synchronized public SoundManager getInstance() {
		if (_instance == null)
			_instance = new SoundManager();
		return _instance;
	}

	/**
	 * Initialises the storage for the sounds
	 * 
	 * @param theContext
	 *            The Application context
	 */
	public static void initSounds(Context theContext) {
		mContext = theContext;
		mSoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
		mSoundPoolMap = new HashMap<Integer, Integer>();
		mAudioManager = (AudioManager) mContext
				.getSystemService(Context.AUDIO_SERVICE);
		loadSounds();
	}

	/**
	 * Add a new Sound to the SoundPool
	 * 
	 * @param Index
	 *            - The Sound Index for Retrieval
	 * @param SoundID
	 *            - The Android ID for the Sound asset.
	 */
	public static void addSound(int Index, int SoundID) {
		mSoundPoolMap.put(Index, mSoundPool.load(mContext, SoundID, 1));
	}

	/**
	 * Loads the various sound assets Currently hardcoded but could easily be
	 * changed to be flexible.
	 */
	public static void loadSounds() {
		// bubble 1
		// bubble_destroy 2
		// button_click 3
		// coffin_enter 4
		// destroy 5
		// door_mover 6
		// falling_star 7
		// level_completion 8
		// menu 9
		// play 10
		// rope_cut 11
		// rope_cutter_cloud 12
		// star_collect 13
		// start_stage 14

		// SoundManager.playSound(1, 1);

		mSoundPoolMap.put(0, mSoundPool.load(mContext, R.raw.key_lock, 1));
		mSoundPoolMap.put(1, mSoundPool.load(mContext, R.raw.destroy, 3));
		mSoundPoolMap.put(2, mSoundPool.load(mContext, R.raw.levelcompletion, 2));
	
	}

	/**
	 * Plays a Sound
	 * 
	 * @param index
	 *            - The Index of the Sound to be played
	 * @param speed
	 *            - The Speed to play not, not currently used but included for
	 *            compatibility
	 */
	public static void playSound(int index, float speed) {
		if (musicFlag) {
			float streamVolume = mAudioManager
					.getStreamVolume(AudioManager.STREAM_MUSIC);
			streamVolume = streamVolume
					/ mAudioManager
							.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
			streamVolume = 1.0f;
			mSoundPool.play(mSoundPoolMap.get(index), streamVolume,
					streamVolume, 1, 0, speed);
			
		}
	}

	/**
	 * Stop a Sound
	 * 
	 * @param index
	 *            - index of the sound to be stopped
	 */
	public static void stopSound(int index) {
		mSoundPool.stop(mSoundPoolMap.get(index));
	}

	public static void stopAllSound() {
		try {
			for (int i = 0; i < mSoundPoolMap.size(); i++)
				mSoundPool.stop(mSoundPoolMap.get(i));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void cleanup() {
		mSoundPool.release();
		mSoundPool = null;
		mSoundPoolMap.clear();
		mAudioManager.unloadSoundEffects();
		_instance = null;

	}

}