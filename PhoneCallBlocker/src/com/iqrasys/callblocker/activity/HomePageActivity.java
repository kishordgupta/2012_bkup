package com.iqrasys.callblocker.activity;

import java.util.Calendar;

import com.iqrasys.helper.GetNetworkStatus;
import com.iqrasys.utility.StackParser;
import com.kd.phonecall.R;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomePageActivity extends Activity {

	Context c = null;
	boolean bViewInfo = false;

	@Override
	protected void onPause() {
		final SharedPreferences settings = getSharedPreferences("ph", 0);
		if (settings.getBoolean("incommingcall", false) == true) {
			SharedPreferences.Editor editor = settings.edit();
			editor.putBoolean("incommingcall", false);
			editor.commit();

			this.finish();
		}

		//Toast.makeText(this, "Home:Pause", Toast.LENGTH_LONG).show();
		super.onPause();
	}

	@Override
	protected void onStop() {
		SharedPreferences settings = getSharedPreferences("ph", 0);
		SharedPreferences.Editor editor = settings.edit();
		if (settings.getBoolean("firstTimeLoading", false) == true) {
			editor.putBoolean("firstTimeLoading", false);
			editor.commit();
			
			//Toast.makeText(this, "Home:First Loading", Toast.LENGTH_LONG).show();
		} else {
			
			if (!bViewInfo) {
				this.finish();
			}

			bViewInfo = false;
		}

		//Toast.makeText(this, "Home:Stop", Toast.LENGTH_LONG).show();
		super.onStop();
	}

	@Override
	protected void onStart() {
		final SharedPreferences settings = getSharedPreferences("ph", 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean("incommingcall", false);
		editor.commit();

		// Toast.makeText(this, "Home:Start", Toast.LENGTH_LONG).show();
		super.onStart();
	}

	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);

		c = this;
		
		Button btnUpdateData;
		final TextView t = (TextView) findViewById(R.id.updatetime);

		final SharedPreferences settings = getSharedPreferences("ph", 0);
		t.setText("Senaste uppdaterad: "
				+ settings.getString("time", "Inte uppdaterats ännut"));

		btnUpdateData = (Button) findViewById(R.id.btnUpdateData);

		btnUpdateData.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				b.setTextColor(Color.RED);
				// Toast.makeText(c, "Where is network . bro?",
				// Toast.LENGTH_SHORT).show();
				try {
					if (GetNetworkStatus.isNetworkAvailable(c)) {
						startActivity(new Intent(c, StackParser.class));

						SharedPreferences.Editor editor = settings.edit();
						editor.putBoolean("silentMode", true);

						String mydate = java.text.DateFormat
								.getDateTimeInstance().format(
										Calendar.getInstance().getTime());
						editor.putString("time", mydate);
						editor.commit();

						t.setText("Senaste uppdaterad: "
								+ settings.getString("time",
										"Inte uppdaterats ännut"));
						// Toast.makeText(c,
						// "Where is network.?",
						// Toast.LENGTH_LONG).show();
					} else {
						Toast.makeText(c, R.string.msgNetworkNotAvailable,
								Toast.LENGTH_LONG).show();
					}
				} catch (Exception e) {
				}
				// Toast.makeText(c, "Where is network.?",
				// Toast.LENGTH_LONG).show();
			}
		});

		Button btnInfo = (Button) findViewById(R.id.info);
		btnInfo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				bViewInfo = true;
				startActivity(new Intent(c, InfoViewActivity.class));

				// For check
				// startActivity(new Intent(c, LogInActivity.class));
				// startActivity(new Intent(c, DateExpireNotifyActivity.class));
				// startActivity(new Intent(c, IncomingCallScreen.class));
			}
		});

	}

	@Override
	public void onBackPressed() {
		this.finish();

		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		startActivity(intent);
	}

}
