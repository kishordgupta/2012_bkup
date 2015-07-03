package com.atomix.kurowiz.xmlparser;


import java.util.ArrayList;

import com.atomix.kurowiz.supports.CityInfo;
import com.siliconorchard.ephesustravelapp.FindPeopleFragment;
import com.siliconorchard.ephesustravelapp.HomeFragment;
import com.siliconorchard.ephesustravelapp.MainActivity;
import com.siliconorchard.ephesustravelapp.R;

import player.DBHelper;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

 
public class FavouriteListAdapter extends BaseAdapter {
	private Context context;
	public static final ArrayList<CityInfo> arraylist = new ArrayList<CityInfo>();
 
	public FavouriteListAdapter(Context context, ArrayList<CityInfo> arraylist1) {
		this.context = context;
		arraylist.clear();
		arraylist.addAll(arraylist1);
		
	}
 
	public View getView(int position, View convertView, ViewGroup parent) {
 
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View gridView;
 
		if (convertView == null) {
 
			gridView = new View(context);
 
			// get layout from mobile.xml
			gridView = inflater.inflate(R.layout.exchange_gift_row, null);
			gridView.setTag(position);
			// set value into textview
			TextView textView = (TextView) gridView
					.findViewById(R.id.grid_item_label);
			textView.setText(arraylist.get(position).City_name);
 
			// set image based on selected text
		
			String S= 	"<html> <head> <title>banner</title> </head><body>   <img src=";
			S=S+ "\""+arraylist.get(position).city_imageurl+"\"" +"alt=\"\"width=\"100%\" height=\"100%\"/></a></body></html>";
        
				WebView w = (WebView)gridView.findViewById(R.id.img);
				w.loadData(S,  "text/html", "UTF-8");
				w.setVerticalScrollBarEnabled(false);
				w.setHorizontalScrollBarEnabled(false);
				w.setTag(position);
				/*w.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						  int id = (Integer) v.getTag();
					       MainActivity.loadinwebview(arraylist.get(id).steam);
					}
				});
				*/
				
				w.setOnTouchListener(new OnTouchListener() {
					
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						
							int id = (Integer) v.getTag();
							switch (event.getAction()) {
							case MotionEvent.ACTION_DOWN:
							// MainActivity.loadinwebview(arraylist.get(id).steam,arraylist.get(id).image,arraylist.get(id).CityInfoname);
							 default:
								break;
							}
					
						return false;
					}
				});
				
				final ImageView fav = (ImageView)gridView.findViewById(R.id.favourite);
				fav.setImageResource(android.R.drawable.star_big_on);
				fav.setTag(position);
				fav.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						int id = (Integer) v.getTag();
					//	DBHelper.deleteTitle(arraylist.get(id).City_name);
						arraylist.remove(id);
					
						notifyDataSetChanged();
						
					}
				});
				/*gridView.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
				       int id = (Integer) v.getTag();
				       MainActivity.loadinwebview(arraylist.get(id).steam);
					}
				});*/
				//w.loadUrl(arraylist.get(position).getImages());
			//String mobile = arraylist.get(position).getImages();
 /*
			if (mobile.equals("Windows")) {
				imageView.setImageResource(R.drawable.windows_logo);
			} else if (mobile.equals("iOS")) {
				imageView.setImageResource(R.drawable.ios_logo);
			} else if (mobile.equals("Blackberry")) {
				imageView.setImageResource(R.drawable.blackberry_logo);
			} else {
				imageView.setImageResource(R.drawable.android_logo);
			}*/
 
		} else {
			//gridView = new View(context);
			 
			// get layout from mobile.xml
			gridView = inflater.inflate(R.layout.exchange_gift_row, null);
			gridView.setTag(position);
			// set value into textview
			TextView textView = (TextView) gridView
					.findViewById(R.id.grid_item_label);
			textView.setText(arraylist.get(position).City_name);//City_name;
 
			// set image based on selected text
		
			String S= 	"<html> <head> <title>banner</title> </head><body>   <img src=";
			S=S+ "\""+arraylist.get(position).city_imageurl+"\"" +"alt=\"\"width=\"100%\" height=\"100%\"/></a></body></html>";
        
				WebView w = (WebView)gridView.findViewById(R.id.img);
				w.loadData(S,  "text/html", "UTF-8");
				w.setVerticalScrollBarEnabled(false);
				w.setHorizontalScrollBarEnabled(false);
				w.setTag(position);
				/*w.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						  int id = (Integer) v.getTag();
					       MainActivity.loadinwebview(arraylist.get(id).steam);
					}
				});
				*/
				
				w.setOnTouchListener(new OnTouchListener() {
					
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						
							int id = (Integer) v.getTag();
							switch (event.getAction()) {
							case MotionEvent.ACTION_DOWN:
						//	 MainActivity.loadinwebview(arraylist.get(id).steam,arraylist.get(id).image,arraylist.get(id).CityInfoname);
							 default:
								break;
							}
						return false;
					}
				});
				
				final ImageView fav = (ImageView)gridView.findViewById(R.id.favourite);
				fav.setImageResource(android.R.drawable.star_big_on);
				fav.setTag(position);
				fav.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						int id = (Integer) v.getTag();
						//DBHelper.deleteTitle(arraylist.get(id).City_name);
						arraylist.remove(id);
					
						notifyDataSetChanged();
						
					}
				});
			
		}
 
		return gridView;
	}
 
	@Override
	public int getCount() {
		return arraylist.size();
	}
 
	@Override
	public Object getItem(int position) {
		return null;
	}
 
	@Override
	public long getItemId(int position) {
		return 0;
	}
 
}