package com.atomix.kurowiz.xmlparser;


import info.androidhive.slidingmenu.PhotosFragment;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.model.Instancevalues;
import com.model.Offer;
import com.model.Vendor;
import com.siliconorchard.com.ecoupon.cards.free.ListActivity;
import com.siliconorchard.com.ecoupon.cards.free.R;

 
///for search offer

public class ImageAdapter extends BaseAdapter {
	private Context context;
	public static final ArrayList<Offer> arraylist = new ArrayList<Offer>();
 
	public ImageAdapter(Context context, ArrayList<Offer> arraylist1) {
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
			gridView = inflater.inflate(R.layout.favourite_gift_row, null);
			gridView.setTag(position);
			// set value into textview
			TextView textView = (TextView) gridView
					.findViewById(R.id.grid_item_label);
			textView.setText(arraylist.get(position).getVendor_name());
 
			// set image based on selected text
		
			String S= 	"<html> <head> <title>banner</title> </head><body>   <img src=";
			S=S+ "\""+arraylist.get(position).vendorlogo+"\"" +"alt=\"\"width=\"100%\" height=\"100%\"/></a></body></html>";

				WebView w = (WebView)gridView.findViewById(R.id.img);
				w.loadUrl(arraylist.get(position).vendorlogo);
		
				w.setVerticalScrollBarEnabled(false);
				w.setHorizontalScrollBarEnabled(false);
				w.setBackgroundColor(Color.parseColor("#EFF8FB"));			
				w.setTag(position);
				
				gridView.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						int id = (Integer) v.getTag();
						// TODO Auto-generated method stub
						try{
							Instancevalues.currentvendor=arraylist.get(id).vendor_name;
							Instancevalues.currentVendor=new Vendor();
							Instancevalues.currentVendor.setVendorname(Instancevalues.currentvendor);
							Instancevalues.currentVendor.setVendorlogo(arraylist.get(id).getVendorlogo());//=arraylist.get(id).
							 Fragment f;
					            f = new PhotosFragment();
					            FragmentTransaction ft = ((Activity) ListActivity.c).getFragmentManager().beginTransaction();
					            ft.replace(R.id.container, f);
					            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
					            ft.addToBackStack(null);
					            ft.commit();
							}
							catch(Exception e)
							{
								
							}
					}
				});
				
				w.setOnTouchListener(new OnTouchListener() {
					
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						int id = (Integer) v.getTag();
						switch (event.getAction()) {
						case MotionEvent.ACTION_DOWN:
							try{
							Instancevalues.currentvendor=arraylist.get(id).vendor_name;
							Instancevalues.currentVendor=new Vendor();
							Instancevalues.currentVendor.setVendorname(Instancevalues.currentvendor);
							Instancevalues.currentVendor.setVendorlogo(arraylist.get(id).getVendorlogo());//=arraylist.get(id).
							 Fragment f;
					            f = new PhotosFragment();
					            FragmentTransaction ft = ((Activity) ListActivity.c).getFragmentManager().beginTransaction();
					            ft.replace(R.id.container, f);
					            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
					            ft.addToBackStack(null);
					            ft.commit();
							}
							catch(Exception e)
							{
								
							}
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
				fav.setVisibility(View.INVISIBLE);
				fav.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
					//	int id = (Integer) v.getTag();
				
					}
				});
			
 
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