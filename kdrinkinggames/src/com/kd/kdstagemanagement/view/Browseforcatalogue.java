package com.kd.kdstagemanagement.view;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.kd.dasinnovation.movie.chickflicksurvivalguide.R;
import com.kd.kdstagemanagement.helper.CurrentStates;
import com.kd.kdstagemanagement.helper.Helperdata;
import com.kd.kdstagemanagement.list.Movielist;
import com.kd.kdstagemanagement.model.KdList;

public class Browseforcatalogue extends BaseActivity implements OnItemClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browse);
		
		Movielist.newyearsvalues= KdList.Movieslist;
	
		ListView listViewwish = (ListView) findViewById(R.id.list_of_movies);
		Movielist happynewyearlist = new Movielist(this);

		listViewwish.setAdapter(happynewyearlist);
		listViewwish.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Helperdata.selectedmovie=Movielist.newyearsvalues.get(arg2);
		switch (CurrentStates.STATE) {
		case CurrentStates.STATE_DRINKINGGAMES:
			startActivity(new Intent(this,DrinkGame.class));
			break;
        case CurrentStates.STATE_TIMELINE:
        	startActivity(new Intent(this,TimelinezGame.class));
			break;
        case CurrentStates.STATE_TRIVIANTIPS:
        	startActivity(new Intent(this,Triviantips.class));
			break;
		default:
			break;
		}
		
	}
	
	
}
