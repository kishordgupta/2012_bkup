package com.atomix.kurowiz.xmlparser;

import info.androidhive.slidingmenu.PhotosFragment;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import com.model.Instancevalues;
import com.model.Vendor;
import com.siliconorchard.com.ecoupon.cards.free.ListActivity;
import com.siliconorchard.com.ecoupon.cards.free.Mapactivity;
import com.siliconorchard.com.ecoupon.cards.free.R;


/// Used for fetured pvendors

public class ListAdapter extends BaseAdapter {
	private Context context;
	public static final ArrayList<Vendor> arraylist = new ArrayList<Vendor>();

	public ListAdapter(Context context, ArrayList<Vendor> arraylist1) {
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
			textView.setText(arraylist.get(position).getVendorname());

			// set image based on selected text

			String S = "<html> <head> <title>banner</title> </head><body>   <img src=";
			S = S
					+ "\""
					+ arraylist.get(position).getVendorlogo()
					+ "\""
					+ "alt=\"\"width=\"100%\" height=\"100%\"/></a></body></html>";

			WebView w = (WebView) gridView.findViewById(R.id.img);
			w.setBackgroundColor(Color.parseColor("#EFF8FB"));	
			if ("0".contains(arraylist.get(position).getVendorlogo()))
				arraylist.get(position).setVendorlogo(
						"http://savings2phone.com/mobsite/images/logo.gif");
			w.loadUrl(arraylist.get(position).getVendorlogo());
			// w.loadData(S, "text/html", "UTF-8");
			w.setVerticalScrollBarEnabled(false);
			w.setHorizontalScrollBarEnabled(false);
			w.setTag(position);

			w.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub

				//	int id = (Integer) v.getTag();
					switch (event.getAction()) {

					case MotionEvent.ACTION_HOVER_ENTER:
					//	v.setBackgroundColor(Color.RED);
						break;
					case MotionEvent.ACTION_CANCEL:
						int k = (Integer) v.getTag();

						// set color back to default
						/*if (k % 2 != 0) {
							v.setBackgroundColor(Color.parseColor("#F5BCA9"));
						} else
							v.setBackgroundColor(Color.parseColor("#F2F2F2"));*/
						break;
					case MotionEvent.ACTION_HOVER_EXIT:
						/*int j = (Integer) v.getTag();

						// set color back to default
						if (j % 2 != 0) {
							v.setBackgroundColor(Color.parseColor("#F5BCA9"));
						} else
							v.setBackgroundColor(Color.parseColor("#F2F2F2"));*/
						break;
					case MotionEvent.ACTION_DOWN:
						//v.setBackgroundColor(Color.RED);
						break;
					case MotionEvent.ACTION_UP:
						int i = (Integer) v.getTag();
						try {
							Instancevalues.currentVendor = Instancevalues.featurevendorlist
									.get(i);
							Instancevalues.Lat = Instancevalues.featurevendorlist
									.get(i).getLat() + "";
							Instancevalues.lon = Instancevalues.featurevendorlist
									.get(i).getLon() + "";
							Instancevalues.currentvendor = Instancevalues.featurevendorlist
									.get(i).getVendorname() + "";
							Fragment f;
							f = new PhotosFragment();
							FragmentTransaction ft = ((Activity) ListActivity.c)
									.getFragmentManager().beginTransaction();
							ft.replace(R.id.container, f);
							ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
							ft.addToBackStack(null);
							ft.commit();

						} catch (Exception e) {
							Toast.makeText(ListActivity.c, "Server not found",
									Toast.LENGTH_LONG).show();
						}

						break;

					}

					return false;
				}
			});
			final ImageView fav = (ImageView) gridView
					.findViewById(R.id.favourite);
			fav.setTag(position);
			fav.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					int i = (Integer) v.getTag();

					try {
						Instancevalues.Currentadress = Instancevalues.featurevendorlist
								.get(i).getVendoradress() + "";
						Instancevalues.Lat = Instancevalues.featurevendorlist
								.get(i).getLat() + "";
						Instancevalues.lon = Instancevalues.featurevendorlist
								.get(i).getLon() + "";
						Instancevalues.currentvendor = Instancevalues.featurevendorlist
								.get(i).getVendorname() + "";
						Intent ia = new Intent(ListActivity.c,
								Mapactivity.class);

						ListActivity.c.startActivity(ia);
					} catch (Exception e) {
						Toast.makeText(ListActivity.c, "Server not found",
								Toast.LENGTH_LONG).show();
					}

				}
			});
			
			gridView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					int i = (Integer) v.getTag();

					try {
						Instancevalues.Currentadress = Instancevalues.featurevendorlist
								.get(i).getVendoradress() + "";
						Instancevalues.Lat = Instancevalues.featurevendorlist
								.get(i).getLat() + "";
						Instancevalues.lon = Instancevalues.featurevendorlist
								.get(i).getLon() + "";
						Instancevalues.currentvendor = Instancevalues.featurevendorlist
								.get(i).getVendorname() + "";
						Intent ia = new Intent(ListActivity.c,
								Mapactivity.class);

						ListActivity.c.startActivity(ia);
					} catch (Exception e) {
						Toast.makeText(ListActivity.c, "Server not found",
								Toast.LENGTH_LONG).show();
					}

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