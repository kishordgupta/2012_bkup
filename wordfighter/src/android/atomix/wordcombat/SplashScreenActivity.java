package android.atomix.wordcombat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

public class SplashScreenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		
		creatingSplashScreen();
	}
	
	private void creatingSplashScreen() 
	{
		new CountDownTimer(3000, 1000) 
		{
			@Override
			public void onTick(long millisUntilFinished) 
			{
				
			}
			
			@Override
			public void onFinish() 
			{
				startActivity(new Intent(SplashScreenActivity.this, SettingActivity.class));
				finish();
			}
		}.start();
	}

}
