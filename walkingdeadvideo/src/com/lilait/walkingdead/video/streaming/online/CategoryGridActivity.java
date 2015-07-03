package com.lilait.walkingdead.video.streaming.online;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lilait.walkingdead.video.streaming.online.R;
import com.lilakhelait.kishor.helper.Dialoge;
import com.lilakhelait.kishor.resource.Imageurl;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.peakcoders.backgroundTasks.ConstantValues;


public class CategoryGridActivity extends Activity {

	
	private String[] imageUrls;
	ImageLoader imageLoader = ImageLoader.getInstance();
	
	static class ViewHolder {
		ImageView imageView;
		WebView weView;
		TextView textView;
	}
String Html = "<img src=\"ximgurl\" class=\"img_title\" title=\"title\" width=\"xW\" height=\"xH\" />";
	GridView gridView;
	private ImageLoaderConfiguration config;// --
	private File cacheDir;// --
	private DisplayImageOptions options;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_grid);
		Dialoge.runDialog(5,this);
		Bundle bundle = getIntent().getExtras();
		// imageUrls = bundle.getStringArray(Extra.IMAGES);
		// ---imageUrls = JsonToElement.getImageUrlById();

	//	pCategories = new ArrayList<ModelWPCategory>();
		imageUrls = new String[Imageurl.newyearsvalues.size()];
		for(int i=0; i<Imageurl.newyearsvalues.size(); i++)
		{
			/*ModelWPCategory modelWPCategory = new ModelWPCategory();
			modelWPCategory = wpCategories.get(i);*/
		//	categoryNames[i]= Imageurl.newyearsvalues.get(i);
			imageUrls[i] = Imageurl.newyearsvalues.get(i);;
		}
			


		cacheDir = new File(Environment.getExternalStorageDirectory(),
				ConstantValues.CACHE_DIR); // --
		// Create configuration for ImageLoader
		config = new ImageLoaderConfiguration.Builder(this)
				.discCache(new UnlimitedDiscCache(cacheDir))
				.memoryCache(new UsingFreqLimitedMemoryCache(2000000))
				.denyCacheImageMultipleSizesInMemory()
				.threadPriority(Thread.MIN_PRIORITY + 2).threadPoolSize(5)
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
				.build(); // --
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.stub_image)
				.showImageForEmptyUri(R.drawable.image_for_empty_url)
				.cacheInMemory().cacheOnDisc().build();

		imageLoader.init(config);
		gridView = (GridView) findViewById(R.id.gridview_category);
		gridView.setAdapter(new ImageAdapter());
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
		
				startBasicGridActivity(position);
			}
		});
	
	}

	@Override
	protected void onStop() {
	//	imageLoader.stop();
		super.onStop();
	}



	private void startImageGalleryActivity(int position) {
		Intent intent = new Intent(this, ImagePagerActivity.class);
		intent.putExtra(Extra.IMAGES, imageUrls);
		intent.putExtra(Extra.IMAGE_POSITION, position);
	//	Log.d("kd", "mydddddddddddn");
		startActivity(intent);
	}
	
	private void startBasicGridActivity(int position) {
		Intent intent = new Intent(this, ImagePagerActivity.class);
		intent.putExtra(Extra.IMAGE_POSITION,position);
		startActivity(intent);
	}

	public class ImageAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			// return imageUrls.length;
			return imageUrls.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			
			final View aView;
			
			if (convertView == null) {
				final ViewHolder viewHolder = new ViewHolder();
				aView		=	getLayoutInflater().inflate(R.layout.item_category_grid, parent, false);
				
				viewHolder.imageView 	= 	(ImageView) aView.findViewById(R.id.image_item_category);//(ImageView) getLayoutInflater().inflate(R.layout.item_grid_image, parent, false);
				viewHolder.weView 	= 	(WebView) aView.findViewById(R.id.web);//(ImageView) getLayoutInflater().inflate(R.layout.item_grid_image, parent, false);
				
				int ww =viewHolder.imageView.getLayoutParams().width = (ConstantValues.DEVICE_WIDTH/3) -3 ;
				int wh = viewHolder.imageView.getLayoutParams().height = (int) (((ConstantValues.DEVICE_WIDTH/2)*0.75) -2);
				
				String h = Html.replace("ximgurl", imageUrls[position]).replace("xW", ww+"").replace("xH", wh+"");
				viewHolder.weView.loadData(h, "text/html; charset=UTF-8",null);
				
				viewHolder.textView	=	(TextView) aView.findViewById(R.id.text_item_cat_desc);
				viewHolder.textView.setText(Imageurl.title.get(position));
				//viewHolder.imageView.setMaxWidth(150);
				imageLoader.displayImage(imageUrls[position], viewHolder.imageView, options,
						new SimpleImageLoadingListener() {
							@Override
							public void onLoadingComplete(Bitmap loadedImage) {
								Animation anim = AnimationUtils.loadAnimation(
										CategoryGridActivity.this, R.anim.fade_in);
								viewHolder.imageView.setAnimation(anim);
								anim.start();
							}
						});
				
			} else {
				aView = convertView;
				
			}
			
			return aView;
		}
	}
}