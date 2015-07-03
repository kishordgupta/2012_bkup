package com.iqrasys.callblocker.activity;

import com.iqrasys.callblocker.activity.DataInputActivity;
import com.iqrasys.callblocker.model.PhoneNumberContainer;
import com.iqrasys.db.DBHelper;
import com.iqrasys.listview.*;
import com.kd.phonecall.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class BlockNumberListActivity extends Activity implements
		OnClickListener {

	ListView listViewLocalNumber = null;
	ListView listViewServerNumber = null;

	LocalNumberList listLocalNumber = null;
	ServerNumberList listServerNumber = null;

	final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.call_list);
		Button add = (Button) findViewById(R.id.btnAddNumber);
		add.setOnClickListener(this);

		// My Number List View
		listViewLocalNumber = (ListView) findViewById(R.id.list_of_mine);
		listLocalNumber = new LocalNumberList(this);

		LocalNumberList.listValues = PhoneNumberContainer.localNumber;

		listViewLocalNumber.setAdapter(listLocalNumber);
		listLocalNumber.notifyDataSetChanged();

		// DB Number List View
		listViewServerNumber = (ListView) findViewById(R.id.list_of_db_number);
		listServerNumber = new ServerNumberList(this);

		ServerNumberList.listValues = PhoneNumberContainer.serverNumber;
		listViewServerNumber.setAdapter(listServerNumber);
		listServerNumber.notifyDataSetChanged();

		// Log.d("On Create", "My Num Cnt: " + listLocalNumber.getCount());
		// Log.d("On Create", "DB Num Cnt: " + listServerNumber.getCount());

		listViewServerNumber.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				final int arg = arg2;
				// TODO Auto-generated method stub
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						context);
				alertDialogBuilder.setTitle("Radera "
						+ ServerNumberList.listValues.get(arg).getTitleText());

				// set dialog message
				alertDialogBuilder
						.setMessage(
								"Radera "
										+ ServerNumberList.listValues.get(arg)
												.getNumberText())
						.setCancelable(false)
						.setPositiveButton(R.string.msgDelete,
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int id) {
										// if this button is clicked,
										// BlockHappynewyearlist.newyearsvalues.remove(arg);//.remove(index-1);
										PhoneNumberContainer.serverNumber
												.remove(arg);
										ServerNumberList.listValues = PhoneNumberContainer.serverNumber;
										listServerNumber.notifyDataSetChanged();

										dialog.dismiss();
									}
								})
						.setNegativeButton(R.string.msgCancel,
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int id) {
										dialog.cancel();
									}
								});

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();
			}
		});

		listViewLocalNumber.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				final int arg = arg2;
				// TODO Auto-generated method stub
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						context);
				alertDialogBuilder.setTitle("Radera "
						+ LocalNumberList.listValues.get(arg).getTitleText());

				// set dialog message
				alertDialogBuilder
						.setMessage(
								"Radera "
										+ LocalNumberList.listValues.get(arg)
												.getNumberText())
						.setCancelable(false)
						.setPositiveButton(R.string.msgDelete,
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int id) {
										// if this button is clicked,
										DBHelper myDbHelper = new DBHelper(
												context);

										try {
											myDbHelper.openDataBase();
										} catch (SQLException sqle) {
											throw sqle;
										}
										myDbHelper
												.deleteEntry(LocalNumberList.listValues
														.get(arg));
										myDbHelper.close();
										// BlockHappynewyearlist.newyearsvalues.remove(arg);//.remove(index-1);
										PhoneNumberContainer.localNumber
												.remove(arg);
										LocalNumberList.listValues = PhoneNumberContainer.localNumber;
										listLocalNumber.notifyDataSetChanged();

										dialog.cancel();
									}
								})
						.setNegativeButton(R.string.msgCancel,
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int id) {

										dialog.cancel();

									}
								});

				// Create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// Show it
				alertDialog.show();

			}
		});

		// Log.d("On Create", "My Num Cnt: " + listLocalNumber.getCount());
		// Log.d("On Create", "DB Num Cnt: " + listServerNumber.getCount());

		Button btnMyNumber = (Button) findViewById(R.id.btnMyNumberHeader);
		btnMyNumber.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (listViewLocalNumber.getVisibility() != View.GONE)
					listViewLocalNumber.setVisibility(View.GONE);
				else
					listViewLocalNumber.setVisibility(View.VISIBLE);
			}

		});

		Button btnAllDBNumber = (Button) findViewById(R.id.btnDBBlockedNumberHeader);
		btnAllDBNumber.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (listViewServerNumber.getVisibility() != View.GONE) {
					listViewServerNumber.setVisibility(View.GONE);
				} else {
					listViewServerNumber.setVisibility(View.VISIBLE);
				}
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		// Log.d("onStart", "My Num Cnt: " + listLocalNumber.getCount());
		// Log.d("onStart", "DB Num Cnt: " + listServerNumber.getCount());
		final SharedPreferences settings = getSharedPreferences("ph", 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean("incommingcall", false);
		editor.commit();

		listServerNumber.notifyDataSetChanged();
		listLocalNumber.notifyDataSetChanged();

		//Toast.makeText(this, "NumList:Start", Toast.LENGTH_LONG).show();
		super.onStart();
	}
	
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
		
		//Toast.makeText(this, "NumList:Pause", Toast.LENGTH_LONG).show();
		super.onPause();
	}

	@Override
	protected void onStop() {
		// Toast.makeText(this, "NumberList:Stop", Toast.LENGTH_LONG).show();
		SharedPreferences settings = getSharedPreferences("ph", 0);
		SharedPreferences.Editor editor = settings.edit();
		if (settings.getBoolean("firstTimeLoading", false) == true) {
			editor.putBoolean("firstTimeLoading", false);
			editor.commit();
			Toast.makeText(this, "NumList:First Loading", Toast.LENGTH_LONG).show();
		} else {
			
				this.finish();
		}

		//Toast.makeText(this, "NumList:Stop", Toast.LENGTH_LONG).show();
		super.onStop();
	}

	@Override
	protected void onResume() 
	{
		listServerNumber.notifyDataSetChanged();
		listLocalNumber.notifyDataSetChanged();
		
		//Toast.makeText(this, "NumList:Resume", Toast.LENGTH_LONG).show();
		super.onResume();

		// Log.d("onResume", "My Num Cnt: " + listLocalNumber.getCount());
		// Log.d("onResume", "DB Num Cnt: " + listServerNumber.getCount());
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
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			startActivityForResult(new Intent(getApplicationContext(),
					DataInputActivity.class), 123);

			break;
		default:
			return false;
		}
		return false;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 123) {
			// Log.d("onActivityResult", "My Num Cnt: " +
			// listLocalNumber.getCount());
			// Log.d("onActivityResult", "DB Num Cnt: " +
			// listServerNumber.getCount());

			LocalNumberList.listValues = PhoneNumberContainer.localNumber;
			listLocalNumber.notifyDataSetChanged();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnAddNumber:
			startActivityForResult(new Intent(getApplicationContext(),
					DataInputActivity.class), 123);
			break;

		default:
			break;
		}
	}

}
