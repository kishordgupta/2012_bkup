package com.kd.kdstagemanagement.view;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

import com.kd.dasinnovation.movie.chickflicksurvivalguide.R;
import com.kd.kdstagemanagement.helper.Helperdata;
import com.kd.kdstagemanagement.list.Filtermovielist;
import com.kd.kdstagemanagement.list.Searchmovielist;
import com.kd.kdstagemanagement.model.KdList;
import com.kd.kdstagemanagement.model.Movies;

public class Searchbyname extends BaseActivity implements OnClickListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		

		Searchmovielist.newyearsvalues= KdList.Searchlist;
		ListView listViewwish = (ListView) findViewById(R.id.list_of_movies);
		Searchmovielist happynewyearlist = new Searchmovielist(this);
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
		ed.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				int selection = 0;
				for (String string : parray) {
					selection++;
					
					if (string.contains(ed.getText())) {
				
					
						adapter.notifyDataSetChanged();
						
					
						break;
					}
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				int selection = 0;
				for (String string : parray) {
					selection++;
				
					if (string.contains(ed.getText())) {
					
					
						adapter.notifyDataSetChanged();
						
						selection = 0;
						break;
					}
				}
			}
		});
		
		listViewwish.setAdapter(happynewyearlist);
		Button b= (Button)findViewById(R.id.search);
		b.setOnClickListener(this);
	}
@Override
public void onBackPressed() {
	// TODO Auto-generated method stub
	
	super.onBackPressed();
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
