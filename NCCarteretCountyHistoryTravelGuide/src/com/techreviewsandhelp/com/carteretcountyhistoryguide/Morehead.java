package com.techreviewsandhelp.com.carteretcountyhistoryguide;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Morehead extends Activity{

	Button Back, Directions, Street, Play;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.beachcity);
		Back = (Button) findViewById(R.id.Back);
		Directions = (Button) findViewById(R.id.Directions);
		Street = (Button) findViewById(R.id.Street);
		Play = (Button) findViewById(R.id.Play);
		
		Back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent Back = new Intent("com.techreviewsandhelp.carteretcountyhistoryguide.MAINACTIVITY");
				startActivity(Back);
				
			}
			
		});
		

} }