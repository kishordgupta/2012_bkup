package com.iqrasys.callblocker.activity;

import com.kd.phonecall.R;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class IncomingCallScreen extends Activity {
	// ITelephony telephonyService;
	// TelephonyManager telephonyManager;
	final Context c = this;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
		getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);// keep
																			// the
																			// activity
																			// running
																			// under
																			// lock
																			// screen..

		setContentView(R.layout.call_screen);

		// Get Intent data
		Bundle extras = getIntent().getExtras();
		if (extras == null) {
			return;
		}

		String strCallerTitle = extras.getString("caller_title");

		if (strCallerTitle != null) {
			TextView txtTitle = (TextView) findViewById(R.id.callerTitle);
			txtTitle.setText(strCallerTitle);
		}

		String strCallerNumber = extras.getString("caller_number");

		if (strCallerTitle != null) {
			TextView txtNumber = (TextView) findViewById(R.id.callerNumber);
			txtNumber.setText(strCallerNumber);
		}

		// Option Button Handler
		Button btnReject = (Button) findViewById(R.id.btnConfirmCall);
		btnReject.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}

	// Unlock screen if screen locked.
	void unlockScreen() {
		KeyguardManager keyGdMgr = (KeyguardManager) c
				.getSystemService(Context.KEYGUARD_SERVICE);
		if (keyGdMgr.isKeyguardLocked()) {
			// OnKeyguardExitResult bResult = null;
			// keyGdMgr.exitKeyguardSecurely(bResult);

			final KeyguardManager.KeyguardLock keyLock = keyGdMgr
					.newKeyguardLock("MyKeyguardLock");
			keyLock.disableKeyguard();

			PowerManager pm = (PowerManager) c
					.getSystemService(Context.POWER_SERVICE);
			WakeLock wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK
					| PowerManager.ACQUIRE_CAUSES_WAKEUP
					| PowerManager.ON_AFTER_RELEASE, "MyWakeLock");
			wakeLock.acquire();
		}

	}

}
