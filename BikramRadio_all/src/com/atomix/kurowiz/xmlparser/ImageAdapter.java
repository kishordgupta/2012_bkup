package com.atomix.kurowiz.xmlparser;


import java.util.ArrayList;

import player.DBHelper;

import com.atomix.kurowiz.supports.GiftInfo;
import com.example.swipeuiforupclose.R;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

 
public class ImageAdapter extends BaseAdapter {
	private Context context;
	public static final ArrayList<GiftInfo> arraylist = new ArrayList<GiftInfo>();
 
	public ImageAdapter(Context context, ArrayList<GiftInfo> arraylist1) {
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
			textView.setText(arraylist.get(position).getHotel_name());
 
			// set image based on selected text
		
			String S= 	"<html> <head> <title>banner</title> </head><body>   <img src=";
			S=S+ "\""+arraylist.get(position).getImages()+"\"" +"alt=\"\"width=\"100%\" height=\"100%\"/></a></body></html>";

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
					       MainActivity.loadinwebview(arraylist.get(id).getHotel_link());
					}
				});*/
				
				w.setOnTouchListener(new OnTouchListener() {
					
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						int id = (Integer) v.getTag();
						switch (event.getAction()) {
						case MotionEvent.ACTION_DOWN:
							 DBHelper.addRowhistoy(arraylist.get(id));
							// MainActivity.loadinwebview(arraylist.get(id).getHotel_link(),arraylist.get(id).getImages(),arraylist.get(id).getHotel_name());
								
							    break;
							  case MotionEvent.ACTION_MOVE:
							
							    break;
							  case MotionEvent.ACTION_UP:
							   
							    break;

						default:
							break;
						}
					
						return true;
					}
				});
				
				final ImageView fav = (ImageView)gridView.findViewById(R.id.favourite);
				fav.setTag(position);
				fav.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						int id = (Integer) v.getTag();
						DBHelper.addRowfavourite(arraylist.get(id));
						fav.setImageResource(android.R.drawable.star_big_on);
					}
				});
			/*	gridView.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
				       int id = (Integer) v.getTag();
				       DBHelper.addRowhistoy(arraylist.get(id));
				       Log.d("dfdf", ""+id);
				       MainActivity.loadinwebview(arraylist.get(id).getHotel_link());
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
			gridView = (View) convertView;
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