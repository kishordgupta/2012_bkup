package com.techreviewsandhelp.com.carteretcountyhistoryguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	Button Help;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainactivity);
		Help = (Button) findViewById(R.id.Help);
		
		
		Help.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent Alantic = new Intent("com.techreviewsandhelp.carteretcountyhistoryguide.HELP");
				startActivity(Alantic);
				
			}
			
		});
		

} }
