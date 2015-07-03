package com.mom.lilait.mothersday.ma.wish;



import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.lilakhelait.kishor.listview.Poem;
import com.lilakhelait.kishor.resource.Wish;
import com.lilakhelait.kishor.resource.event.Happynewyearresource;
import com.mom.lilait.mothersday.ma.wish.R;

public class Mainwindowactivity extends Activity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		 Bundle extras = getIntent().getExtras();
	  	String s = extras.getString("city_name");
	  	 getParent().setTitle(s);
		ConstructResources();
		s=s.toLowerCase()+".txt";
    	ArrayList<Wish> w = Happynewyearresource.getnewyearfiles(this, s);
    	Poem.newyearsvalues=w;
		ListView listViewwish = (ListView) findViewById(R.id.list_of_wishes);
		
		Poem happynewyearlist = new Poem(this);
	
		listViewwish.setAdapter(happynewyearlist);
		
	}

	private void ConstructResources() {
		// TODO Auto-generated method stub
		
	}
}
