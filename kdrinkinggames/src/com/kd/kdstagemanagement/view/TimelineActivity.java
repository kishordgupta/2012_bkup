package com.kd.kdstagemanagement.view;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.kd.dasinnovation.movie.chickflicksurvivalguide.R;



public class TimelineActivity extends BaseActivity implements OnClickListener{
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	      
	        Button create =(Button)findViewById(R.id.load);
	        create.setText("Search For Movie");
	        
	        create.setOnClickListener(this);
	        Button help =(Button)findViewById(R.id.create);
	        help.setText("Browse Movie Catalogue");
	        help.setOnClickListener(this);
	        Button Load =(Button)findViewById(R.id.help);
	        Load.setText("Settings");
	        Load.setOnClickListener(this);
	        Button Loadd =(Button)findViewById(R.id.edit);
	        Loadd.setVisibility(View.GONE);
	    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.create:
			startActivity(new Intent(this, Browseforcatalogue.class));	
			break;
		case R.id.help:
			startActivity(new Intent(this, Settings.class));		
			break;
		case R.id.load:
			startActivity(new Intent(this, Searchbyname.class));	
			break;
		default:
			break;
		}
	}


}
