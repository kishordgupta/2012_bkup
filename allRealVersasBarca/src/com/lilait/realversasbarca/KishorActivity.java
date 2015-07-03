package com.lilait.realversasbarca;


import com.lilakhelait.kishor.resource.Happynewyearresource;
import com.lilakhelait.kishor.resource.Imageurl;
import com.peakcoders.backgroundTasks.StackParser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

public class KishorActivity extends Activity implements OnClickListener{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
        Happynewyearresource.getnewyearfiles(this);
        Log.d("df", Imageurl.newyearsvalues.size()+"");
   
		Button b = (Button)findViewById(R.id.df1); 
		b.setOnClickListener(this);
		Button bw = (Button)findViewById(R.id.df); 
		bw.setOnClickListener(this);
		final Spinner spinner2 =  (Spinner) findViewById(R.id.spinner2);
	
		final Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
			
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
				
			}
		});
		
		spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
			
			
				
		
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
		final Context c = this;
	/*	String[] aparray = new String[Imageurl.newyearsvalues.size()];
		int  i =0;
		for (String string :  Imageurl.newyearsvalues) {
			aparray[i]=string;
			i++;
		}
		aparray =  Imageurl.newyearsvalues.toArray(aparray);*/
		final String[] parray =  Imageurl.newyearsvalues.toArray(new String[Imageurl.newyearsvalues.size()]);
         
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, parray);
		final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, parray);
		final ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, parray);
		final ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, parray);
		spinner1.setAdapter(adapter3);
		spinner2.setAdapter(adapter4);
		final AutoCompleteTextView ed = (AutoCompleteTextView) findViewById(R.id.edi2);
		ed.setAdapter(adapter);
		final AutoCompleteTextView ed1 = (AutoCompleteTextView) findViewById(R.id.edi3);
		ed1.setAdapter(adapter1);

		

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.df1:
		Spinner pinner1 =	 (Spinner) findViewById(R.id.spinner2);
		String a = pinner1.getSelectedItem().toString();//.replace(" ","_");
		Spinner pinner =	 (Spinner) findViewById(R.id.spinner1);
		String b = pinner.getSelectedItem().toString();//.replace(" ","_");
		Imageurl.url =	Imageurl.url + a+" vs "+b;

	startActivityForResult(new Intent(this, StackParser.class), 123);
	//	startActivityForResult(new Intent(this, MainActivity.class), 123);
		
		break;

		default:
			break;
		}
	}

	
	
}