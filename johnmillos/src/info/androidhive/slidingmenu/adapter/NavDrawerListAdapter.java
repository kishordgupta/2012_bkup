package info.androidhive.slidingmenu.adapter;


import info.androidhive.slidingmenu.model.NavDrawerItem;

import java.util.ArrayList;

import com.siliconorchard.com.ecoupon.cards.free.ListActivity;
import com.siliconorchard.com.ecoupon.cards.free.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NavDrawerListAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<NavDrawerItem> navDrawerItems;
	
	public NavDrawerListAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems){
		this.context = context;
		this.navDrawerItems = navDrawerItems;
	}

	@Override
	public int getCount() {
		return navDrawerItems.size();
	}

	@Override
	public Object getItem(int position) {		
		return navDrawerItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_list_item, null);
        }
         
        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
        TextView txtCount = (TextView) convertView.findViewById(R.id.counter);
        WebView w = (WebView) convertView.findViewById(R.id.img);
		w.getSettings().setAppCacheEnabled(true);
		// w.getSettings().setLoadWithOverviewMode(true);
		w.getSettings().setCacheMode(
				WebSettings.LOAD_CACHE_ELSE_NETWORK);
		// w.getSettings().setUseWideViewPort(true);
		w.setBackgroundColor(Color.TRANSPARENT);
		// w.loadData(S, "text/html", "UTF-8");
		//w.loadUrl(navDrawerItems.get(position).getUrl());
		w.setVisibility(View.GONE);
	//	if(navDrawerItems.get(position).getIcon()>1)
        imgIcon.setImageResource(navDrawerItems.get(1).getIcon());  
        
        txtTitle.setText(navDrawerItems.get(position).getTitle());
        
        // displaying count
        // check whether it set visible or not
        if(navDrawerItems.get(position).getCounterVisibility()){
        	txtCount.setText(navDrawerItems.get(position).getCount());
        }else{
        	// hide the counter view
        	txtCount.setVisibility(View.GONE);
        }
        convertView.setTag(position);
        convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ListActivity.displayView((Integer) v.getTag());
			}
		});
        return convertView;
	}

}
