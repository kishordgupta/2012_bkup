package com.iqrasys.callblocker.activity;

import com.iqrasys.callblocker.model.PhoneCallReceiver;
import com.kd.phonecall.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;
import android.widget.TextView;

public class CallBlockSettingsActivity extends Activity {

	static int flag = 0;

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		final SharedPreferences settings = getSharedPreferences("ph", 0);
		if (settings.getBoolean("incommingcall", false) == true) {
			SharedPreferences.Editor editor = settings.edit();
			editor.putBoolean("incommingcall", false);
			editor.commit();

			this.finish();
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		final SharedPreferences settings = getSharedPreferences("ph", 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean("incommingcall", false);
		editor.commit();

		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		final SharedPreferences settings = getSharedPreferences("ph", 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean("incommingcall", false);
		editor.commit();

		super.onStart();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.callblock_options);
		ScrollView s = (ScrollView) findViewById(R.id.f);
		// s.setBackgroundDrawable(getResources().getDrawable(R.drawable.vsp6yf));
		final Context con = this;
		TextView t = (TextView) findViewById(R.id.button1);
		t.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final CharSequence[] items = { "Normal", "Vibration",
						"Total blockering" };

				AlertDialog.Builder builder = new AlertDialog.Builder(con);
				builder.setTitle("Välj läge");
				builder.setSingleChoiceItems(items, flag,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int itemIndex) {
								flag = itemIndex;
								// dialog.dismiss();
								PhoneCallReceiver.call = itemIndex;
								/*
								 * if(itemIndex == 0) PhoneCallReceiver.call =
								 * 1; else PhoneCallReceiver.call=0;
								 */

								dialog.dismiss();
							}
						});

				AlertDialog alert = builder.create();
				alert.show();
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

	@Override
	protected void onStop() {
		// Toast.makeText(this, "Home:Stop", Toast.LENGTH_LONG).show();
		SharedPreferences settings = getSharedPreferences("ph", 0);
		SharedPreferences.Editor editor = settings.edit();
		if (settings.getBoolean("firstTimeLoading", false) == true) {
			editor.putBoolean("firstTimeLoading", false);
			editor.commit();
		} else {
			//Toast.makeText(this, "Home:Stop", Toast.LENGTH_LONG).show();
				this.finish();
		}

		super.onStop();
	}

}
