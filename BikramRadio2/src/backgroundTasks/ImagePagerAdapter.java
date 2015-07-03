package backgroundTasks;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.WallpaperManager;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.swipeuiforupclose.MainActivity;
import com.example.swipeuiforupclose.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;



public class ImagePagerAdapter extends PagerAdapter {
	private final static String stpos="";
	private String[] images;
	private LayoutInflater inflater;

	public ImagePagerAdapter(String[] images) {
		this.images = images;
		inflater = ((Activity) MainActivity.c).getLayoutInflater();
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
		Animation anim = AnimationUtils.loadAnimation(MainActivity.c, R.anim.fade_out);
		anim.setRepeatMode(Animation.REVERSE);
		imageView.setAnimation(anim);
		imageView.startAnimation(anim);
		anim.start();
		
		/*if(imageUrl=="")
		imageUrl = images[position];
		
		Log.d("vcvcb",imageUrl+""+this.getItemPosition(position));*/
		DisplayImageOptions options = new DisplayImageOptions.Builder()
		.showImageForEmptyUri(R.drawable.radio)
		.cacheOnDisc()
		.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
		.build();
		MainActivity.imageLoader.displayImage(images[position], imageView, options, new ImageLoadingListener() {
		
		
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
				//Toast.makeText(ImagePagerActivity.this, message, Toast.LENGTH_SHORT).show();

				spinner.setVisibility(View.GONE);
				imageView.setImageResource(android.R.drawable.ic_delete);
			}

			@Override
			public void onLoadingComplete(Bitmap loadedImage) {
				spinner.setVisibility(View.GONE);
				Animation anim = AnimationUtils.loadAnimation(MainActivity.c, R.anim.fade_out);
				imageView.setAnimation(anim);
				anim.start();
				
				
			//	Log.v(TAG,"onLoading Called");
			}

			@Override
			public void onLoadingCancelled() {
				// Do nothing
			}
		});

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

