package com.lilakhelaittelphonecodes;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;

public class kishorActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
        
 
     Button b =(Button)findViewById(R.id.df);
     b.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}
	});
		
		final Spinner spinner2 =  (Spinner) findViewById(R.id.spinner2);
	
		final Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
			
				
			
				
		spinner2.setSelection(arg2, true);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
				
			}
		});
		
		spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
			
				spinner1.setSelection(arg2, true);
				
		
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
		final Context c = this;
		final String[] parray = c.getResources().getStringArray(
				R.array.phonecode_arrays);
		final String[] carray = c.getResources().getStringArray(
				R.array.country_arrays);
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, carray);
		final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, parray);
		final AutoCompleteTextView ed = (AutoCompleteTextView) findViewById(R.id.edi2);
		ed.setAdapter(adapter);
		final AutoCompleteTextView ed1 = (AutoCompleteTextView) findViewById(R.id.edi3);
		ed1.setAdapter(adapter1);

		ed.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				int selection = 0;
				for (String string : carray) {
					selection++;
					
					if (string.contains(ed.getText())) {
				
						spinner1.setSelection(selection - 1);
						ed1.clearComposingText();
						adapter.notifyDataSetChanged();
						adapter1.notifyDataSetChanged();
					
						break;
					}
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				int selection = 0;
				for (String string : carray) {
					selection++;
				
					if (string.contains(ed.getText())) {
					
						spinner1.setSelection(selection - 1);
						ed1.clearComposingText();
						adapter.notifyDataSetChanged();
						adapter1.notifyDataSetChanged();
						selection = 0;
						break;
					}
				}
			}
		});
		
		ed1.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				int selection = 0;
				for (String string : parray) {
					selection++;
					
					if (string.contains(ed1.getText())) {
					
						spinner2.setSelection(selection - 1);
						ed.setText(carray[selection - 1]);
						adapter.notifyDataSetChanged();
						adapter1.notifyDataSetChanged();
						selection = 0;
						break;
					}
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				int selection = 0;
				for (String string : parray) {
					selection++;
				
					if (string.contains(ed1.getText())) {
				
						spinner1.setSelection(selection - 1);
						ed.setText(carray[selection - 1]);
						adapter.notifyDataSetChanged();
						adapter1.notifyDataSetChanged();
						selection = 0;
						break;
					}
				}
			}
		});

	}

	
	
}