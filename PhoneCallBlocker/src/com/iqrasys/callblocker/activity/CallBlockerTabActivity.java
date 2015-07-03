package com.iqrasys.callblocker.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;

import com.iqrasys.callblocker.model.PhoneNumberContainer;
import com.iqrasys.db.DBHelper;
import com.iqrasys.db.MyFile;
import com.iqrasys.helper.GetNetworkStatus;
import com.iqrasys.listview.ServerNumberList;
import com.iqrasys.listview.LocalNumberList;
import com.iqrasys.resource.PhoneNumberModel;
import com.iqrasys.utility.ApplicationSecurityHandler;
import com.iqrasys.utility.StackParser;
import com.kd.phonecall.R;

public class CallBlockerTabActivity extends android.app.TabActivity {

	public TabHost tabHost;
	ProgressDialog progressBar;

	private ProgressDialog pd;
	final Context context = this;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		SharedPreferences settings = getSharedPreferences("ph", 0);
		// SharedPreferences.Editor editor = settings.edit();

//		if (settings.getBoolean("authenticationRequire", true) == true) {
//			startActivity(new Intent(this, LogInActivity.class));
//		} 
//		else 
		{
			setContentView(R.layout.tabs);

//			if (new ApplicationSecurityHandler().isValidationExpired(context)) {
//				startActivity(new Intent(this, DateExpireNotifyActivity.class));
//			} 
//			else 
			{
				SharedPreferences.Editor editor = settings.edit();

				if (settings.getBoolean("silentMode", false) == false) {
					Date dtToday = Calendar.getInstance().getTime();
					String mydate = java.text.DateFormat.getDateTimeInstance()
							.format(dtToday);
					editor.putString("time", mydate);
					editor.commit();
				}

				pd = ProgressDialog.show(CallBlockerTabActivity.this,
						"Var god vänta...", "Laddar sparad data", true, false);
				new ProcessData().execute("");

				// Get the tabHost
				this.tabHost = getTabHost();

				TabHost.TabSpec spec; // Reusable TabSpec for each tab
				Intent intent; // Reusable Intent for each tab

				// Create an Intent to launch the first Activity for the tab (to
				// be reused)
				intent = new Intent().setClass(this, HomePageActivity.class);

				// Initialize a TabSpec for the first tab and add it to the
				// TabHost
				spec = tabHost
						.newTabSpec("Home")
						.setIndicator("Hem",
								getResources().getDrawable(R.drawable.home)) // Replace
																				// null
																				// with
																				// R.drawable.your_icon
																				// to
																				// set
																				// tab
																				// icon
						.setContent(intent);
				tabHost.addTab(spec);

				// Create an Intent to launch an Activity for the tab (to be
				// reused)
				intent = new Intent().setClass(this,
						BlockNumberListActivity.class);

				// Initialize a TabSpec for the second tab and add it to the
				// TabHost
				spec = tabHost
						.newTabSpec("NumberList")
						.setIndicator("Lista",
								getResources().getDrawable(R.drawable.list)) // Replace
																				// null
																				// with
																				// R.drawable.your_icon
																				// to
																				// set
																				// tab
																				// icon
						.setContent(intent);
				tabHost.addTab(spec);

				intent = new Intent().setClass(this,
						CallBlockSettingsActivity.class);

				// Initialize a TabSpec for the second tab and add it to the
				// TabHost
				spec = tabHost
						.newTabSpec("Settings")
						.setIndicator("Inställningar",
								getResources().getDrawable(R.drawable.settings)) // Replace
																					// null
																					// with
																					// R.drawable.your_icon
																					// to
																					// set
																					// tab
																					// icon
						.setContent(intent);
				tabHost.addTab(spec);

				tabHost.setCurrentTab(0);
				tabHost.setOnTabChangedListener(new OnTabChangeListener() {

					@Override
					public void onTabChanged(String arg0) {

						setTabColor(tabHost);
					}
				});
				setTabColor(tabHost);
			}

		}

	}

	@Override
	public void onBackPressed() {
		this.finish();

		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		startActivity(intent);
	}

	private void setTabColor(TabHost tabhost11) {
		// TODO Auto-generated method stub
		for (int i = 0; i < tabhost11.getTabWidget().getChildCount(); i++)
			tabhost11.getTabWidget().getChildAt(i)
					.setBackgroundColor(Color.parseColor("#DA7B1F")); // unselected

		if (tabhost11.getCurrentTab() == 0)
			tabhost11.getTabWidget().getChildAt(tabhost11.getCurrentTab())
					.setBackgroundColor(Color.parseColor("#000000")); // 1st tab
																		// selected
		else
			tabhost11.getTabWidget().getChildAt(tabhost11.getCurrentTab())
					.setBackgroundColor(Color.parseColor("#000000")); // 2nd tab
																		// selected
																		// #1c4ea8

	}

	void loadSavedData() {
		SharedPreferences settings = getSharedPreferences("ph", 0);
		SharedPreferences.Editor editor = settings.edit();

		if (GetNetworkStatus.isNetworkAvailable(this)
				&& settings.getBoolean("silentMode", false) == false) {
			startActivity(new Intent(this, StackParser.class));
			editor.putBoolean("silentMode", true);
			editor.commit();
		}

		PhoneNumberContainer.serverNumber.clear();
		MyFile f = new MyFile(this);

		String s = f.readFromSD();
		if (s.length() > 0) {
			String[] lines = s.split("\n");

			int nItemCount = lines.length;
			// progressBar.setMax(nItemCount);

			for (String string : lines) {
				String[] listContact = string.split(",");

				if (listContact.length == 0)
					continue;

				PhoneNumberModel item = new PhoneNumberModel();
				if (listContact.length == 1) {
					item.setTitleText("Säljare");
					item.setNumberText(listContact[0]);
				} else if (listContact.length == 2) {
					item.setTitleText(listContact[0]);
					item.setNumberText(listContact[1]);
				}

				PhoneNumberContainer.serverNumber.add(item);
			}
		}

		PhoneNumberContainer.localNumber.clear();
		DBHelper myDbHelper = new DBHelper(this);

		try {
			myDbHelper.createDataBase();
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}

		try {
			myDbHelper.openDataBase();
		} catch (SQLException sqle) {
			throw sqle;
		}

		PhoneNumberContainer.localNumber = myDbHelper.getDataSet();
		myDbHelper.close();

		LocalNumberList.listValues = PhoneNumberContainer.localNumber;
		ServerNumberList.listValues = PhoneNumberContainer.serverNumber;

		// if (isDebug)
		{
			// Log.d("onCreate ", "My Number Cnt: " +
			// PhoneNumberContainer.localNumber.size());
			// Log.d("onCreate ", "DB Number Cnt: " +
			// PhoneNumberContainer.serverNumber.size());
		}
	}

	private class ProcessData extends AsyncTask<String, Void, List<String>> {
		@Override
		protected List<String> doInBackground(String... arg) {
			List<String> output = new ArrayList<String>();

			loadSavedData();

			return output;
		}

		@Override
		protected void onPostExecute(List<String> output) {
			pd.dismiss();
		}
	}

}
