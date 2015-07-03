package com.lilakhelait.kishordgupta.mainscreenactivity;



import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.lilakhelait.kishor.listview.Happynewyearlist;
import com.lilakhelait.kishordgupta.mainscreenactivity.R;
import com.lilakhelait.kishor.resource.event.Happynewyearresource;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Mainwindowactivity extends Activity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
        
		ConstructResources();
		AdRequest ad = new AdRequest();
	  //  ad.setTesting(true);
	    AdView adView = (AdView)findViewById(R.id.ad);
	    adView.loadAd(ad);
		ListView listViewwish = (ListView) findViewById(R.id.list_of_wishes);
		Happynewyearlist happynewyearlist = new Happynewyearlist(this);

		listViewwish.setAdapter(happynewyearlist);
		
	}

	private void ConstructResources() {
		// TODO Auto-generated method stub
		Happynewyearresource.getnewyearfiles(this);
	}
}
