package com.siliconorchard.ephesustravelapp;





import com.siliconorchard.ephesustravelapp.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		final Context c = this;
	
					Intent openStartingPoint = new Intent(c,MainActivity.class);
					startActivity(openStartingPoint);
		finish();
	}

	
}
