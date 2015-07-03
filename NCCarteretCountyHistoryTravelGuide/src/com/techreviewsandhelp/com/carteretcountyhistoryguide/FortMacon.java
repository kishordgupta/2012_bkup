package com.techreviewsandhelp.com.carteretcountyhistoryguide;



import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;

public class FortMacon extends Activity{

	Button Back, Directions, Street, Play;
	static final String[] texts={ "Fort Macon State Park is a North Carolina state park in Carteret County, North Carolina, in the United States. Located on Bogue Banks near Atlantic Beach, the park opened in 1936. Fort Macon State Park is the second most visited state park in North Carolina, with an annual visitation of 1.3 million, despite being the third smallest park in North Carolina with 389 acres. The Battle of Fort Macon was fought there during March and April 1862. In addition to the fully restored fort, the park offers visitors both soundside and surf fishing, nature trails, ranger guided tours, a protected swim area, a refreshment stand, and a bathhouse. With the exception of the bathhouse, there are no fees to enjoy the park. The park is open year round and does not charge an admission or parking fee. During the non-summer months the protected swimming area, refreshment stand, and bathhouse are not available. However, you can swim at your own risk and public restrooms are open year round. Fort Macon State Park also completely surrounds United States Coast Guard Base Fort Macon. The main gate is located directly across from the park office and barracks, and visitors can catch a glimpse of one of the Coast Guard Cutters that are moored there."
	};
	TextToSpeech tts;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.beachcity);
		Back = (Button) findViewById(R.id.Back1);
		Directions = (Button) findViewById(R.id.Directions1);
		Street = (Button) findViewById(R.id.Street1);
		Play = (Button) findViewById(R.id.Play1);
		
		// Below is my try at making the TTS work. What should happen is when the user click the play button, the TTS speaks the final String. I didn't try to set this up, but I want it when the user clicks the Go Back, Back, or Home button the TTS stops talking. Based on the TTS in App Inventor, you could do this by having the TTS say "" when one of those buttons are hit.
		tts = new TextToSpeech(FortMacon.this, new TextToSpeech.OnInitListener() {
			public void onInit(int status){
				// TODO Auto-generated method stub
			if (status != TextToSpeech.ERROR){
				tts.setLanguage(Locale.US);
			}
			}
		});
// The go back button should work. I don't remember if I tested it, but it should take you back to the main screen whenever you press it.
		Back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent Back = new Intent("com.techreviewsandhelp.carteretcountyhistoryguide.MAINACTIVITY");
				startActivity(Back);
				
			}
			
		});
		
Directions.setOnClickListener(new View.OnClickListener() {

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
			
				
			}
			
		);

Street.setOnClickListener(new View.OnClickListener() {

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
		
	}
	
);


//The play button needs to play the message in the final string. I was trying to follow a guide in making this, and it obviously didn't work.
Play.setOnClickListener(new View.OnClickListener() {

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	
		
	}
	
);
		
		

}
// I'm not sure if I need the following code. The guide didn't really get into this one that much, but I think it's to keep the TTS from turning on after you come back to the app. Since I can't get the TTS to work, I can't test it out to figure out what exactly it does.
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		if(tts !=null){
			tts.stop();
			tts.shutdown();
		}
		super.onPause();
	} }