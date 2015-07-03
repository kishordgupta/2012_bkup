package com.example.swipeuiforupclose;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;

public class SleepOut extends Activity {
	static int interval = 0;
	static Timer timer;
	static MyTimerTask myTimerTask;
	static TextView te;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.time);
		final NumberPicker num = (NumberPicker) findViewById(R.id.numberPicker1);
		num.setMaxValue(240);
		num.setMinValue(1);
		num.setValue(1);
		te = (TextView) findViewById(R.id.timertext);
		Switch sw = (Switch) findViewById(R.id.switch1);
		if (timer == null) {

			te.setText("Remaining Time " + num.getValue());
			sw.setChecked(false);
		} else {
			int min = interval / 60;
			int s = interval % 60;
			te.setText("Remaining time " + min + ":" + s);
			sw.setChecked(true);
		}

		sw.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (buttonView.isChecked()) {
					if (timer != null) {
						timer.cancel();

					}
					if (myTimerTask != null) {
						myTimerTask = null;
					}
					// re-schedule timer here
					// otherwise, IllegalStateException of
					// "TimerTask is scheduled already"
					// will be thrown
					timer = new Timer();
					myTimerTask = new MyTimerTask(num.getValue() * 60);

					timer.schedule(myTimerTask, 1000, 1000);

				} else {
					if (timer != null) {
						timer.cancel();

						timer = null;

					}
					if (myTimerTask != null) {
						myTimerTask.cancel();
						myTimerTask = null;
					}
					num.setValue(10);
					te.setText("Remaining Time " + num.getValue());
				}
			}
		});

	}

	class MyTimerTask extends TimerTask {

		int interval = 0;

		public MyTimerTask(int i) {
			// TODO Auto-generated constructor stub
			this.interval = i;
		}

		@Override
		public void run() {

			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					int min = interval / 60;
					int s = interval % 60;
					te.setText("Remaining time " + min + ":" + s);
					interval--;
					if (interval == 0) {
						timer.cancel();
						timer = null;
						if (myTimerTask != null) {
							myTimerTask.cancel();
							myTimerTask = null;
						}
						NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
						notificationManager.cancel(548853);
						MainActivity.justStop();

						System.exit(0);
					}
				}
			});
		}

	}
}
