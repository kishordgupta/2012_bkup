package com.kd.kdstagemanagement.view;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.kd.dasinnovation.movie.chickflicksurvivalguide.R;
import com.kd.kdstagemanagement.database.Createdb;
import com.kd.kdstagemanagement.database.adapter.Init;
import com.kd.kdstagemanagement.helper.CurrentStates;



public class MainActivity extends BaseActivity implements OnClickListener{
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	   
	        Button create =(Button)findViewById(R.id.create);
	        create.setOnClickListener(this);
	        Button help =(Button)findViewById(R.id.help);
	        help.setOnClickListener(this);
	        Button Load =(Button)findViewById(R.id.load);
	        Load.setOnClickListener(this);
	        Button edit =(Button)findViewById(R.id.edit);
	        edit.setOnClickListener(this);
	        
	    }

	
	  
	  
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.create:
			CurrentStates.STATE=CurrentStates.STATE_DRINKINGGAMES;
			startActivity(new Intent(this, DrinkingActivity.class));
			break;
		case R.id.help:
			finish();		
			break;
		case R.id.load:
			CurrentStates.STATE=CurrentStates.STATE_TIMELINE;
			startActivity(new Intent(this, TimelineActivity.class));	
			break;
		case R.id.edit:
			CurrentStates.STATE=CurrentStates.STATE_TRIVIANTIPS;
			startActivity(new Intent(this, DrinkingActivity.class));	
			break;
		default:
			break;
		}
	}


}
