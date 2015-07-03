package com.lilait.movie.story.spoiler;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.lilait.movie.story.spoiler.R;
import com.lilakhelait.kishor.listview.Happynewyearlist;
import com.lilakhelait.kishor.resource.event.Happynewyearresource;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


public class SearchMovies extends Activity implements OnItemClickListener{

   Context c;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cities_layout);
      
        fillData();
        c=this;
    }
    Happynewyearlist happynewyearlist = null;
    // Fill the list with some data, this can be anything really
    public void fillData() {
    	//ArrayAdapter citiesAdapter = new ArrayAdapter(this, R.layout.city_row, cities);
    	Happynewyearresource.getnewyearfilesarchive(this);
    	
       ListView listViewwish = (ListView) findViewById(R.id.list);
	    happynewyearlist = new Happynewyearlist(this, R.layout.city_row);
		listViewwish.setAdapter(happynewyearlist);
		listViewwish.setOnItemClickListener(this);
	
    }

  
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		 Intent i = new Intent(this, ShowCity.class);
	        String city_name = (String) happynewyearlist.getItem(arg2).getDate();
	        Log.d("sdf", city_name);
	        i.putExtra("city_name", happynewyearlist.getItem(arg2).getWishtext());
	        i.putExtra("date",city_name);
	        // Create the view using FirstGroup's LocalActivityManager
	        c.startActivity(i );
	}
}

