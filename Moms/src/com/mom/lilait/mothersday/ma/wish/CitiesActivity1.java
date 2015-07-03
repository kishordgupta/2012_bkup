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

public class CitiesActivity1 extends Activity implements OnItemClickListener{

       // Data to put in the ListAdapter
       private  String city=null;// = new String[] {"beauty","belly dance","boxing","buy outlet","camping","car buying","career","college life","culinary","dating","digital filmmaking","divorce","diy","driving","fashion","financial","flirting","furniture buying","gambling","gardening","golf","hairs","health","hiking","house owner","house repair","insurance","investment","job search","life","love","matrimony","mortgage","parenting","party planning","personal finances","pet care","photography","public speaking","relationships","sales training","sex","skin care","sleep","sports coaching","texting","travel","video production","weight loss","working at home",
//};
       private ListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
  	 city = extras.getString("city_name");
        setContentView(R.layout.cities_layout);
        getParent().setTitle(city);
        MyAndroidAppActivity.Title=city;
        setTitle(city);
        fillData();
    }

    // Fill the list with some data, this can be anything really
public	Poriched citiesAdapter =null;
    public void fillData() {
    
    	
    	//Happynewyearlist.newyearsvalues
    	int i=0;
    	String s =city.replace(" ", "");
		s=s.toLowerCase()+".txt";
    	ArrayList<Wish> w = Happynewyearresource.getnewyearfilesof(this, s);
    	citiesAdapter = new Poriched(this,w );
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
		 Intent i = new Intent(this, ShowCity.class);
	        String city_name = citiesAdapter.newyearsvalues.get(arg2).getWishtext();
	        
	        i.putExtra("city_name",city_name);
	        i.putExtra("city_names",city);
	        // Create the view using FirstGroup's LocalActivityManager
	        View view = FirstGroup.group.getLocalActivityManager()
	        .startActivity("show_city", i
	        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
	        .getDecorView();

	        // Again, replace the view
	        FirstGroup.group.replaceView(view);
	}
}

