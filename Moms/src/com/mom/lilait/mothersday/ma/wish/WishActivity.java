package com.mom.lilait.mothersday.ma.wish;


import java.util.ArrayList;

import com.lilakhelait.kishor.listview.Happynewyearlist;
import com.lilakhelait.kishor.listview.Poriched;
import com.lilakhelait.kishor.listview.Poriched1;
import com.lilakhelait.kishor.resource.Wish;
import com.lilakhelait.kishor.resource.event.Happynewyearresource;
import com.mom.lilait.mothersday.ma.wish.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class WishActivity extends Activity implements OnItemClickListener{

       // Data to put in the ListAdapter
       private String[] cities = new String[] {"SMS","Qoutes","Poems","Party Ideas","Gift Idea","Games","Tips"};
       private ListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cities_layout);
        fillData();
    }

    // Fill the list with some data, this can be anything really
public	Poriched1 citiesAdapter =null;
    public void fillData() {
    
    	
    	//Happynewyearlist.newyearsvalues
    	int i=0;
    	ArrayList<Wish> w = new ArrayList<Wish>();
    	for (String s : cities) {
			Wish e = new Wish();
			e.setTitle(s);
			w.add(e);
		}
    	citiesAdapter = new Poriched1(this,w );
        ListView l = (ListView)findViewById(R.id.list);
        
        l.setOnItemClickListener(this);
        l.setAdapter(citiesAdapter);
        citiesAdapter.notifyDataSetChanged();
       
    }
@Override
public void onBackPressed() {
	// TODO Auto-generated method stub
	finish();
}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		if(arg2>2)
		{
		 Intent i = new Intent(this, CitiesActivity1.class);
	        String city_name = citiesAdapter.newyearsvalues.get(arg2).getTitle();
	        i.putExtra("city_name",city_name);
	        getParent().setTitle(city_name);
	        // Create the view using FirstGroup's LocalActivityManager
	        View view = FirstGroup.group.getLocalActivityManager()
	        .startActivity("show_city", i
	        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
	        .getDecorView();

	        // Again, replace the view
	        FirstGroup.group.replaceView(view);
		}
		else{Intent i = new Intent(this, Mainwindowactivity.class);
        String city_name = citiesAdapter.newyearsvalues.get(arg2).getTitle();
        i.putExtra("city_name",city_name);
        getParent().setTitle(city_name);
        // Create the view using FirstGroup's LocalActivityManager
        View view = FirstGroup.group.getLocalActivityManager()
        .startActivity("show_city", i
        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        .getDecorView();

        // Again, replace the view
        FirstGroup.group.replaceView(view);}
		
	}
}

