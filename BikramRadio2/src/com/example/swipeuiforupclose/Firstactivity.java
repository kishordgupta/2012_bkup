package com.example.swipeuiforupclose;

import backgroundTasks.MyNotification;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class Firstactivity extends Activity {

	static public Context con;
	public static final String PREFS_NAME = "MyPrefsFile";
	protected static final String XML_URL = "http://radioboxplayer.net/station_xml.php?code=";
	public static String facebook="";
	public static String twitter="";
	public static String web="";
	public static String admobid="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		con = this;
		final SharedPreferences prefs = this.getSharedPreferences(
				"com.example.app", Context.MODE_PRIVATE);
		new MyNotification(con);
		MainActivity.Radio_tag = prefs.getString("Radio_tag", "0");
		/*
		 * SharedPreferences.Editor editor = prefs.edit();
		 * editor.putInt("counter", ++counter); editor.commit();
		 */
		if ("0".contains(MainActivity.Radio_tag)) {
			AlertDialog.Builder alert = new AlertDialog.Builder(this);

			alert.setTitle("Radio Code");
			alert.setMessage("Write the Radio code");

			// Set an EditText view to get user input
			final EditText input = new EditText(this);
			alert.setView(input);

			alert.setNegativeButton("Ok", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					if (input.getText().toString() != "") {
						// MainActivity.Radio_tag=input.getText().toString() ;
						SharedPreferences.Editor editor = prefs.edit();
						editor.putString("Radio_tag", input.getText()
								.toString());
						editor.commit();
						new dataprocess.XMLLoadAsync().execute(XML_URL
								+ input.getText().toString());
						
						dialog.dismiss();
					} else {
						Toast.makeText(con,
								"WRITE THE CODE PLEASE NO NULL STRING",
								Toast.LENGTH_LONG).show();
					}
				}
			});

			alert.show();
		}
		else
		{
			new dataprocess.XMLLoadAsync().execute(XML_URL
					+ MainActivity.Radio_tag);
		}
		/*
		 * } else { ((Activity) con).runOnUiThread(new Runnable() { public void
		 * run() { Toast.makeText( ((Activity) con),
		 * "Please, Provide working internet connection to run this app",
		 * Toast.LENGTH_SHORT).show(); } }); ((Activity) con).finish();
		 */
		// }

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

}
