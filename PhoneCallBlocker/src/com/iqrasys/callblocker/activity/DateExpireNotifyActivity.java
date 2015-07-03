package com.iqrasys.callblocker.activity;

import com.iqrasys.callblocker.model.PhoneCallReceiver;
import com.iqrasys.callblocker.model.PhoneNumberContainer;
import com.iqrasys.db.MyFile;
import com.kd.phonecall.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DateExpireNotifyActivity extends Activity {

	Button btnOK;
	public Context context = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.expire_date_layout);
		context = this;

		btnOK = (Button) findViewById(R.id.btn_ok);
		// error = (TextView) findViewById(R.id.tv_error);
		btnOK.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				appExit();
			}

		});
	}

	@Override
	public void onBackPressed() {
		appExit();
	}

	public void appExit() {
		SharedPreferences settings = getSharedPreferences("ph", 0);
		SharedPreferences.Editor editor = settings.edit();

		editor.putBoolean("authenticationRequire", true);
		editor.commit();

		PhoneNumberContainer.localNumber.clear();
		PhoneNumberContainer.serverNumber.clear();

		MyFile f = new MyFile(context);
		f.clearFileContent();

		this.finish();

		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		startActivity(intent);

		int pid = android.os.Process.myPid();
		android.os.Process.killProcess(pid);
	}

}
