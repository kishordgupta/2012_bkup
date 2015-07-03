package com.quiz.pretest.exam.b4;

import com.quiz.pretest.exam.b4.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

public class SplashScreen extends Activity {
	private CountDownTimer countDownTimer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		creatingSplashScreen();
	}

	private void creatingSplashScreen() {
		countDownTimer = new CountDownTimer(1000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {

			}

			@Override
			public void onFinish() {
				startActivity(new Intent(SplashScreen.this, SplashActivity.class));
				finish();
			}
		};
		countDownTimer.start();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		if (countDownTimer != null) {
			countDownTimer.cancel();
		}
	}
}
