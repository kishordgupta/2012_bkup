package com.siliconorchard.hoteldealsfinder;

import java.text.SimpleDateFormat;

import com.atomix.kurowiz.supports.ConstantValues;
import com.atomix.kurowiz.supports.Urlmaker;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {
  public static Context c=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        c=this;
		/*if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
	/*	int id = item.getItemId();
		if (id == R.id.action_settings) {
		//	startActivity(new Intent(this, ListActivity.class));
			return true;
		}*/
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			
			final EditText e = (EditText) rootView.findViewById(R.id.key);
			final DatePicker d =(DatePicker)rootView.findViewById(R.id.date);
		
			final EditText n =(EditText)rootView.findViewById(R.id.day);
			
			Button sub =(Button)rootView.findViewById(R.id.sub);
			sub.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					int day = d.getDayOfMonth();
					int month = d.getMonth()+1;
					int year = d.getYear();
					String da = "";
					String mo = "";
					ConstantValues.isScrolling=true;
					ListActivity.arrayList.clear();
					Urlmaker.pag=1;
					if (month < 10) {
						mo = "0" + month;
					}
					else
						mo=""+month;
					if (day < 10) {
						da = "0" + day;
					}
					else
						da=day+"";
					Urlmaker.date = year + "-" + mo + "-" + da;
					Urlmaker.keyword = e.getText().toString();
					Urlmaker.night = n.getText().toString();
					Urlmaker.urlmaker();
					startActivity(new Intent(c, ListActivity.class));
				}
			});
					
			return rootView;
		}
	}

}
