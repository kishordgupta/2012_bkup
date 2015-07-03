package com.kd.kdstagemanagement.view;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;

import com.kd.dasinnovation.movie.chickflicksurvivalguide.R;
import com.kd.kdstagemanagement.list.Filtermovielist;
import com.kd.kdstagemanagement.model.KdList;
import com.kd.kdstagemanagement.settings.SettingsHelp;

public class Settings extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainforbankname);
	   final Context context =this;
		Filtermovielist.newyearsvalues= KdList.Filterslist;
		
		CheckBox c =(CheckBox)findViewById(R.id.vibration);
		c.setChecked(LoadPreferences(SettingsHelp.Warning.Data));
		c.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				((BaseActivity) context).SavePreferences(SettingsHelp.Warning.Data, isChecked);
			}
		});
		CheckBox ca =(CheckBox)findViewById(R.id.movisesummer);
		ca.setChecked(LoadPreferences(SettingsHelp.Summery.Data));
		ca.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				((BaseActivity) context).SavePreferences(SettingsHelp.Summery.Data, isChecked);
			}
		});
		
		ListView listViewwish = (ListView) findViewById(R.id.list_of_movies);
		Filtermovielist happynewyearlist = new Filtermovielist(this);

		listViewwish.setAdapter(happynewyearlist);
		
	}

	

	
}
