package com.peakcoders.peakwallpapers.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;

import com.lilakhelait.kishor.resource.Imageurl;
import com.movie.ironman.wallpaper.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FakeBitmapDisplayer;

/**
 * Example widget provider
 * 
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class UILWidgetProvider extends AppWidgetProvider {

	private static DisplayImageOptions optionsWithFakeDisplayer;

	static {
		optionsWithFakeDisplayer = new DisplayImageOptions.Builder()
				.displayer(new FakeBitmapDisplayer())
				.build();
	}
	

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		// Initialize ImageLoader with configuration.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPoolSize(3)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.memoryCacheSize(1500000) // 1.5 Mb
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.enableLogging() // Not necessary in common
				.build();
		ImageLoader.getInstance().init(config);

		final int N = appWidgetIds.length;
		for (int i = 0; i < N; i++) {
			int appWidgetId = appWidgetIds[i];
			updateAppWidget(context, appWidgetManager, appWidgetId);
		}
	}

	static void updateAppWidget(Context context, final AppWidgetManager appWidgetManager, final int appWidgetId) {
		final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);

		String[] imageUrls = new String[Imageurl.newyearsvalues.size()];
		for(int i=0; i<Imageurl.newyearsvalues.size(); i++)
		{
			/*ModelWPCategory modelWPCategory = new ModelWPCategory();
			modelWPCategory = wpCategories.get(i);*/
		//	categoryNames[i]= Imageurl.newyearsvalues.get(i);
			imageUrls[i] = Imageurl.newyearsvalues.get(i);;
		}

		ImageSize minImageSize = new ImageSize(70, 70); // 70 - approximate size of ImageView in widget
		ImageLoader.getInstance().loadImage(context, imageUrls[0], minImageSize, optionsWithFakeDisplayer, new SimpleImageLoadingListener() {
			@Override
			public void onLoadingComplete(Bitmap loadedImage) {
				views.setImageViewBitmap(R.id.image_left, loadedImage);
				appWidgetManager.updateAppWidget(appWidgetId, views);
			}
		});
		ImageLoader.getInstance().loadImage(context, imageUrls[1], minImageSize, optionsWithFakeDisplayer, new SimpleImageLoadingListener() {
			@Override
			public void onLoadingComplete(Bitmap loadedImage) {
				views.setImageViewBitmap(R.id.image_right, loadedImage);
				appWidgetManager.updateAppWidget(appWidgetId, views);
			}
		});
	}
}
