package com.iqrasys.callblocker.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.iqrasys.helper.GetNetworkStatus;
import com.kd.phonecall.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class LogInActivity extends Activity {

	EditText etKeys;
	TextView error;
	Button ok, btnCancel;

	private ProgressDialog pd;

	public Context context = null;
	public String url = "http://project.iqrasys.se/communify/validate_data.php?";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.login_layout);
		context = this;

		etKeys = (EditText) findViewById(R.id.et_auth_keys);
		ok = (Button) findViewById(R.id.btn_login);
		btnCancel = (Button) findViewById(R.id.btn_cancel);
		// error = (TextView) findViewById(R.id.tv_error);
		btnCancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				appExit();
			}

		});

		ok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String authKeys = etKeys.getText().toString().trim();

				if (authKeys.length() > 0) {
					url = url + "user_keys=" + authKeys;
					if (GetNetworkStatus.isNetworkAvailable(context)) {
						pd = ProgressDialog.show(LogInActivity.this,
								"Var god vänta...",
								"Kontrollera användarnamn och lösenord", true,
								false);

						new ParseSite().execute(url);
					} else {
						// finish();
						Toast.makeText(context,
								R.string.msgNetworkNotAvailable,
								Toast.LENGTH_LONG).show();
						// appExit();
					}
				} else {
					Toast.makeText(context, "Giltig nyckel krävs.",
							Toast.LENGTH_LONG).show();
				}

			}
		});
	}

	@Override
	public void onBackPressed() {
		appExit();
	}

	public void appExit() {
		this.finish();

		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		startActivity(intent);

		int pid = android.os.Process.myPid();
		android.os.Process.killProcess(pid);
	}

	private class ParseSite extends AsyncTask<String, Void, List<String>> {

		@Override
		protected List<String> doInBackground(String... arg) {
			List<String> output = new ArrayList<String>();
			try {
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(arg[0]);
				ResponseHandler<String> resHandler = new BasicResponseHandler();
				String page = httpClient.execute(httpGet, resHandler);

				SharedPreferences settings = getSharedPreferences("ph", 0);
				SharedPreferences.Editor editor = settings.edit();
				if (page.equalsIgnoreCase("1")) {
					editor.putBoolean("authenticationRequire", false);
					// Toast.makeText(context, "Authentication Succeed.",
					// Toast.LENGTH_LONG).show();
				} else {
					editor.putBoolean("authenticationRequire", true);
					// Toast.makeText(context, "Authentication Failed!!!",
					// Toast.LENGTH_LONG).show();
				}
				editor.commit();

			} catch (ClientProtocolException e) {
				e.printStackTrace();

			} catch (IOException e) {
				e.printStackTrace();

			}

			return output;
		}

		@Override
		protected void onPostExecute(List<String> output) {
			SharedPreferences settings = getSharedPreferences("ph", 0);
			SharedPreferences.Editor editor = settings.edit();
			if (settings.getBoolean("authenticationRequire", true) == false) {
				// Date aDate = Calendar.getInstance().getTime();
				// DateFormate dateFormat =
				// java.text.DateFormat.getDateInstance();
				String authenticateDate = java.text.DateFormat
						.getDateTimeInstance().format(
								Calendar.getInstance().getTime());
				editor.putString("authenticationDate", authenticateDate);

				Toast.makeText(context, "Autentisering Framgång.",
						Toast.LENGTH_LONG).show();

				editor.putBoolean("firstTimeLoading", true);
				startActivity(new Intent(context, CallBlockerTabActivity.class));
			} else {
				Toast.makeText(
						context,
						"Autentisering Misslyckades.\nVänligen försök igen med giltiga nycklar.",
						Toast.LENGTH_LONG).show();
				startActivity(new Intent(context, LogInActivity.class));
			}
			editor.commit();

			pd.dismiss();
			finish();
		}
	}
}
