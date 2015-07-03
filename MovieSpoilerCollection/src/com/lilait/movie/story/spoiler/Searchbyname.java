package com.lilait.movie.story.spoiler;


import com.lilakhelait.kishor.helper.Helperdata;
import com.lilakhelait.kishor.listview.Searchmovielist;
import com.lilakhelait.kishor.resource.Wish;
import com.lilakhelait.kishor.resource.event.Happynewyearresource;

import android.app.Activity;
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



public class Searchbyname extends BaseActivity implements OnClickListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		
		Happynewyearresource.getnewyearfilesarchive(this);
	
		ListView listViewwish = (ListView) findViewById(R.id.list_of_movies);
		Searchmovielist happynewyearlist = new Searchmovielist(this);
		final String[] parray =new String[Searchmovielist.newyearsvalues.size()];
		int i=0;
		for (Wish string : Searchmovielist.newyearsvalues) {
			parray[i]=string.getWishtext();
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
			String s=GetTextFromEditText(R.id.bank).toLowerCase().replace(" ", "");
			Wish h=null;
			for (Wish string : Searchmovielist.newyearsvalues) {
				if(s.equals(string.getWishtext().toLowerCase().replace(" ", "")));
				h=string;
				 
			}
			
			if(h!=null){
				 Intent i = new Intent(this, ShowCity.class);
			        String city_name = h.getDate();
				i.putExtra("city_name", h.getWishtext());
		        i.putExtra("date",h.getDate());
			startActivity(i);
			
			}
			break;

		default:
			break;
		}
	}
}
