package com.Fakeposter.fakeposter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class showPoster extends Activity {

	
	ImageView poster;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.showposter);
		poster=(ImageView) findViewById(R.id.imageView1);
//		 Bundle bundle=   getIntent().getExtras();
//		
//		 Bitmap bmp=(Bitmap) bundle.get("poster");
//		 poster.setImageBitmap(bmp);
		 
	}

}
