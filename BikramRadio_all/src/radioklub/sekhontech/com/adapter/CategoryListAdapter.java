package radioklub.sekhontech.com.adapter;

import java.util.ArrayList;
import java.util.List;

import radioklub.sekhontech.com.entity.Station;

import com.androidquery.AQuery;
import com.atomix.kurowiz.supports.GiftInfo;
import com.example.swipeuiforupclose.MainActivity;
import com.example.swipeuiforupclose.R;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
//import android.widget.ImageView;
import android.widget.TextView;

public class CategoryListAdapter extends ArrayAdapter<GiftInfo> {
	//Member variables
	private LayoutInflater mInflater;
	private AQuery mQuery;
	ImageLoader	im =null;
	public static DisplayImageOptions defaultOptions;
	//Constuctor
	public CategoryListAdapter(Context context, int resource,
			ArrayList<GiftInfo> objects) {
		super(context, resource,objects);
		mInflater = LayoutInflater.from(context);
		defaultOptions = new DisplayImageOptions.Builder().cacheInMemory(true)
				.cacheInMemory(true).imageScaleType(ImageScaleType.EXACTLY)
				.displayer(new FadeInBitmapDisplayer(300)).build();

	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		
		if (view == null) {
			view = mInflater.inflate(R.layout.view_station_row, null);
		}
		mQuery = new AQuery(view);
		String s =  getItem(position).getImages().trim();
		
		
		mQuery.id(R.id.iv_station_icon).image(s, true, true);
	
		
		
		TextView name = (TextView) view.findViewById(R.id.tv_station_name);

		name.setText(getItem(position).getHotel_name());
		name.setTextColor(Color.WHITE);
		return view;
	}

}
