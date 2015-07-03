package com.kd.kdstagemanagement.view;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

import com.kd.dasinnovation.movie.chickflicksurvivalguide.R;
import com.kd.kdstagemanagement.helper.CurrentStates;
import com.kd.kdstagemanagement.helper.Helperdata;
import com.kd.kdstagemanagement.list.Movielist;
import com.kd.kdstagemanagement.model.KdList;
import com.kd.kdstagemanagement.model.Movies;

public class Result extends BaseActivity implements OnClickListener, OnItemClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchresult);
		SetTextToTextView(R.id.bank, Helperdata.SearchText);
	Movielist.newyearsvalues= KdList.Movieslist;
		
		ListView listViewwish = (ListView) findViewById(R.id.list_of_movies);
		Movielist happynewyearlist = new Movielist(this);
		listViewwish.setOnItemClickListener(this);
		listViewwish.setAdapter(happynewyearlist);
		Button b= (Button)findViewById(R.id.search);
		b.setOnClickListener(this);
		final String[] parray =new String[KdList.Movieslist.size()];
		int i=0;
		for (Movies string : KdList.Movieslist) {
			parray[i]=string.Movietitle;
			i++;
		}
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, parray);
		final AutoCompleteTextView ed = (AutoCompleteTextView) findViewById(R.id.bank);
		ed.setAdapter(adapter);
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
			break;}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.search:
			Helperdata.SearchText=GetTextFromEditText(R.id.bank);
			startActivity(new Intent(this, Result.class));
			break;

		default:
			break;
		}
	}
}
