package com.lilait.live.football.goal.uefa.fifa.championsleague.video.latest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lilait.live.football.goal.uefa.fifa.championsleague.video.latest.R;
import com.lilakhelait.kishor.helper.Dialoge;
import com.lilakhelait.kishor.helper.Share;
import com.lilakhelait.kishor.helper.Wallpaper;
import com.lilakhelait.kishor.resource.Imageurl;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.peakcoders.backgroundTasks.BitmapSizeHelper;
import com.peakcoders.backgroundTasks.ConstantValues;
import com.peakcoders.backgroundTasks.DownloadImageByUrl;
import com.peakcoders.backgroundTasks.ImageResizer;
import com.web.videos.MainActivity;


public class ImagePagerActivity extends BaseActivity {
	Button imgBtnAcImagerPagerSetAsWp;
	Button wall;
	private ViewPager pager;

	private DisplayImageOptions options;
	private static final String TAG = "ImagepagerActivity";
	private String imageUrl = "";
	 
	private	WebView weView;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_image_pager);
		initComponent();
		
		Bundle bundle = getIntent().getExtras();
		String[] imageUrls = new String[Imageurl.newyearsvalues.size()];
		for(int i=0; i<Imageurl.newyearsvalues.size(); i++)
		{
			/*ModelWPCategory modelWPCategory = new ModelWPCategory();
			modelWPCategory = wpCategories.get(i);*/
		//	categoryNames[i]= Imageurl.newyearsvalues.get(i);
			imageUrls[i] = Imageurl.newyearsvalues.get(i);;
		}
		int pagerPosition = bundle.getInt(Extra.IMAGE_POSITION, 0);

		options = new DisplayImageOptions.Builder()
			.showImageForEmptyUri(R.drawable.image_for_empty_url)
			.cacheOnDisc()
			.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
			.build();
ImagePagerAdapter im = new ImagePagerAdapter(imageUrls);
		pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(im);
		pager.setCurrentItem(pagerPosition);
		Log.v(TAG,"OnCreate Called");
	}

	private void initComponent() {
		imgBtnAcImagerPagerSetAsWp = (Button) findViewById(R.id.img_btn_image_pager_set_as_wp);
		imgBtnAcImagerPagerSetAsWp.setOnClickListener(new ListenerOnClick());
		
		wall = (Button) findViewById(R.id.wall);
		wall .setOnClickListener(new ListenerOnClick());
		
	}

	@Override
	protected void onStop() {
		imageLoader.stop();
		super.onStop();
	}

	private class ImagePagerAdapter extends PagerAdapter {
		private final static String stpos="";
		private String[] images;
		private LayoutInflater inflater;

		ImagePagerAdapter(String[] images) {
			this.images = images;
			inflater = getLayoutInflater();
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView((View) object);
		}

		@Override
		public void finishUpdate(View container) {
		}

		@Override
		public int getCount() {
			return images.length;
		}

	
		@Override
		public Object instantiateItem(View view,  int position) {
			if(view!=null){
			final View imageLayout = inflater.inflate(R.layout.item_pager_image, null);
			final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image_item_pager);
			final ProgressBar spinner = (ProgressBar) imageLayout.findViewById(R.id.progress_loading_item_pager);
			final WebView weView = (WebView) imageLayout.findViewById(R.id.web);
			
			if(imageUrl=="")
			imageUrl = images[position];
		
			weView.loadData(Imageurl.content.get(position), "text/html; charset=UTF-8",null);
		
			Log.d("vcvcb",imageUrl+""+this.getItemPosition(position));
			imageLoader.displayImage(images[position], imageView, options, new ImageLoadingListener() {
			
			
				@Override
				public void onLoadingStarted() {
					spinner.setVisibility(View.VISIBLE);
				}

				@Override
				public void onLoadingFailed(FailReason failReason) {
					String message = null;
					switch (failReason) {
						case IO_ERROR:
							message = "Input/Output error";
							break;
						case OUT_OF_MEMORY:
							message = "Out Of Memory error";
							break;
						case UNKNOWN:
							message = "Unknown error";
							break;
					}
					Toast.makeText(ImagePagerActivity.this, message, Toast.LENGTH_SHORT).show();

					spinner.setVisibility(View.GONE);
					imageView.setImageResource(android.R.drawable.ic_delete);
				}

				@Override
				public void onLoadingComplete(Bitmap loadedImage) {
					spinner.setVisibility(View.GONE);
					Animation anim = AnimationUtils.loadAnimation(ImagePagerActivity.this, R.anim.fade_in);
					imageView.setAnimation(anim);
					anim.start();
					
					
					Log.v(TAG,"onLoading Called");
				}

				@Override
				public void onLoadingCancelled() {
					// Do nothing
				}
			});

			Log.v(TAG,"INstanciate Item Called");
			((ViewPager) view).addView(imageLayout, 0);
			
			return imageLayout;
			}
			else
				return view;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view.equals(object);
		}

		@Override
		public void restoreState(Parcelable state, ClassLoader loader) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View container) {
		}
	}
	
	protected class ListenerOnClick implements OnClickListener
	{
		

		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.img_btn_image_pager_set_as_wp)
			{
				setAsWallPaperClicked();
			}
			if(v.getId() == R.id.wall)
			{
				setAsWallPaper();
			}
			
		}

		
		
	}
	private void setAsWallPaper() {
		// TODO Auto-generated method stub
	/*	try{
	    //wallpaperManager.set
		//wallpaperManager.setBitmap(bitmap);
		imageUrl=Imageurl.newyearsvalues.get(pager.getCurrentItem());
		//Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
		DownloadImageByUrl downloadImageByUrl = new DownloadImageByUrl();
		Bitmap bitmap = downloadImageByUrl.downloadImage(imageUrl);
		Wallpaper.wall(this, bitmap);
		Toast.makeText(this, "Wallpaper set", Toast.LENGTH_LONG).show();
		}catch (Exception e) {
			// TODO: handle exception
		}*/
	MainActivity.id=Imageurl.youtubeurl.get(pager.getCurrentItem());
	startActivity(new Intent(this, MainActivity.class));
	}
	
	private void setAsWallPaperClicked()
	{
		try{
	    //wallpaperManager.set
		//wallpaperManager.setBitmap(bitmap);
		imageUrl=Imageurl.youtubeurl.get(pager.getCurrentItem());
		String d ="http://www.youtube.com/watch?v="+imageUrl;
		d= Imageurl.title.get(pager.getCurrentItem()) + "see in" +d ;
		Share.share(d, this);
		//Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
		/*DownloadImageByUrl downloadImageByUrl = new DownloadImageByUrl();
		Bitmap bitmap = downloadImageByUrl.downloadImage(imageUrl);
		
		
		//Bitmap newBitmap = ImageResizer.getResizedBitmap(bitmap);
		//Bitmap newBitMap = BitmapSizeHelper.createScaledBitmap(bitmap, ConstantValues.DEVICE_WIDTH, ConstantValues.DEVICE_HEIGHT, BitmapSizeHelper.ScalingLogic.FIT);
	
		File dir = Environment.getExternalStorageDirectory();
		 File output = new File(dir, pager.getCurrentItem()+"hp.jpg");
		 FileOutputStream os = null;
		try {
			os = new FileOutputStream(output);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		 bitmap.compress(CompressFormat.JPEG, 100, os);
		    try {
				os.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		Share.share(output, this);*/
	}catch (Exception e) {
		// TODO: handle exception
	}
		
	}
}