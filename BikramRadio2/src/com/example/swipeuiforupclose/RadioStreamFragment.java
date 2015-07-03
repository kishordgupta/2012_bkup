package com.example.swipeuiforupclose;



import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import backgroundTasks.ImagePagerAdapter;
import backgroundTasks.Imageurl;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class RadioStreamFragment extends Fragment {
	//Constant
	private static final String TAG = "RadioStreamFragment";
	
	//Member variables
	private String mTitle = "";
	private Bitmap mBitmap;
	private boolean mIsPlaying = false;
	private int mMaxVolume;
	private int mVolume;
	private OnSeekBarChangeListener mListener;
	private AudioManager mAudioManager;
	
	//View member variables
	private TextView mTVTitle;
	private ImageView mImageView;
	public static  Button mButton;
	private SeekBar mSeekBar;
	private AutoScrollViewPager pager;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mAudioManager = (AudioManager) getActivity()
				.getSystemService(Context.AUDIO_SERVICE);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_radio, null);
		
		//Init view
		initView(view);
		
		return view;
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (mTitle != null)
			setTitleText(mTitle);
		if (mBitmap != null)
			setImageCover(mBitmap);
		
		//TODO
		//Create seekBar state
		MainActivity activity = (MainActivity) getActivity();
		mMaxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		mVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		
		mSeekBar.setMax(mMaxVolume);
		mSeekBar.setProgress(mVolume);
		if (mListener != null)
			mSeekBar.setOnSeekBarChangeListener(mListener);
		else {
			mSeekBar.setOnSeekBarChangeListener(activity.mSeekBarListener);
		}
		Log.d(TAG, "Volume : " + mVolume + "/" + mMaxVolume);
		
		//Button state
		setButtonState(mIsPlaying);
		
		Log.d(TAG, "Resumed");
	}
	
	/* Property access functions
	 * 
	 */
	public void setTitleText(String title) {
		mTitle = MainActivity.Songtitle;
		mTVTitle.setText(MainActivity.Songtitle);
	}
	public void setImageCover(Bitmap bitmap) {
		mBitmap = bitmap;
		mImageView.setImageBitmap(bitmap);
	}
	public void setButtonState(boolean isPlaying) {
		mIsPlaying = isPlaying;
		/*if (isPlaying) {
			mButton.setImageResource(R.drawable.btn_pause_radio);
		} else {
			mButton.setImageResource(R.drawable.btn_play_radio);
		}*/
	}
	public void setMaxVolume(int value) {
		mMaxVolume = value;
	}
	public void setVolume(int value) {
		mVolume = value;
	}
	public void setOnSeekBarChangeListener(OnSeekBarChangeListener listener) {
		mListener = listener;
	}
	public int getMaxVolume() {
		return mMaxVolume;
	}
	public int getVolume() {
		return mVolume;
	}
	public SeekBar getVolumeSeekBar() {
		return mSeekBar;
	}
	public String getTitleText() {
		return mTitle;
	}
	
	/* Internal Functions
	 * 
	 */
    private Handler handler;
	private void initView(View view) {
		final int sdk = android.os.Build.VERSION.SDK_INT;
		mTVTitle = (TextView) view.findViewById(R.id.tv_title);
		mImageView = (ImageView) view.findViewById(R.id.im_radio_image);
		mButton = (Button) view.findViewById(R.id.btn_play);	
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				doPlay(v);
			}
		});
		mSeekBar = (SeekBar) view.findViewById(R.id.sb_volume);
		mTVTitle.setText(MainActivity.Songtitle);
		String[] imageUrls = new String[Imageurl.newyearsvalues.size()];
		for(int i=0; i<Imageurl.newyearsvalues.size(); i++)
		{
			/*ModelWPCategory modelWPCategory = new ModelWPCategory();
			modelWPCategory = wpCategories.get(i);*/
		//	categoryNames[i]= Imageurl.newyearsvalues.get(i);
			imageUrls[i] = Imageurl.newyearsvalues.get(i);;
		}
		//int pagerPosition = bundle.getInt(Extra.IMAGE_POSITION, 0);

		ImagePagerAdapter im = new ImagePagerAdapter(imageUrls);
		pager = (AutoScrollViewPager) view.findViewById(R.id.pagera);
		pager.setAdapter(im);
		pager.setCurrentItem(0);
		pager.startAutoScroll();
	
		pager.setScrollbarFadingEnabled(true);
		Animation anim = AnimationUtils.loadAnimation(MainActivity.c, R.anim.fade_out);
		anim.setRepeatMode(Animation.REVERSE);
		
	    pager.startAnimation(anim);
	    anim.start();
		/*   handler = new Handler();
		   handler.postDelayed(new Runnable() {
		        public void run() {
		        	int i = pager.getCurrentItem();
		        	if(i>=(pager.getAdapter().getCount()-1))
		        		i=0;
		        	else
		        		i++;
		        	pager.setCurrentItem(i);
		        	pager.getAdapter().notifyDataSetChanged();
		        }
		    },5000);*/
		   
	//	mTVTitle.setText("Radio Station");
	
	}
	public static boolean as=false;
	public void doPlay(View view) {
		final int sdk = android.os.Build.VERSION.SDK_INT;
		if (!mIsPlaying && view.getId() == R.id.btn_play) {
			MainActivity.playRadio();
			if(as){
			try{
			if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
				view.setBackgroundDrawable(getResources()
						.getDrawable(R.drawable.btn_play_radio));
			} else {
				view.setBackground(getResources()
						.getDrawable(R.drawable.btn_play_radio));
			}
			}catch(Exception e)
			{}
			as=false;
			}
			else
			{as=true;
			try{
				if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
					view.setBackgroundDrawable(getResources()
								.getDrawable(R.drawable.btn_pause_radio));
					} else {
						view.setBackground(getResources()
								.getDrawable(R.drawable.btn_pause_radio));
					}
				}catch(Exception e)
				{}
			}
		} else  {
			MainActivity.stopRadio();
			if(as){
				try{
				if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
					view.setBackgroundDrawable(getResources()
							.getDrawable(R.drawable.btn_play_radio));
				} else {
					view.setBackground(getResources()
							.getDrawable(R.drawable.btn_play_radio));
				}
				}catch(Exception e)
				{}
				as=false;
				}
				else
				{as=true;
				try{
					if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
						view.setBackgroundDrawable(getResources()
									.getDrawable(R.drawable.btn_pause_radio));
						} else {
							view.setBackground(getResources()
									.getDrawable(R.drawable.btn_pause_radio));
						}
					}catch(Exception e)
					{}
				}
		}
		
			
	}

}
