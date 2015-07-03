package com.kd.search;

import com.androidhive.jsonparsing.JSONParser;
import com.kd.search.R.id;
import com.kd.search.list.Helperdata;
import com.kd.search.webscrapper.StackParser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends BaseActivity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button sButton = (Button)findViewById(R.id.search);
		sButton.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.search:
			Helperdata.SearchText=GetTextFromEditText(R.id.bank).replace("  ", "");
			Helperdata.SearchText=GetTextFromEditText(R.id.bank).replace("  ", "");
			String url="https://itunes.apple.com/search?term="+Helperdata.SearchText.replace(" ","+" )+"&entity="+Helperdata.type;//'//"&q="+Helperdata.SearchText.replace(" ","+" );
			
			JSONParser p = new JSONParser(this, url);
			

			break;

		default:
			break;
		}
	}

}
