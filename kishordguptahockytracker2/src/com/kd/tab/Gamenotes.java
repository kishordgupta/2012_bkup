package com.kd.tab;



import java.util.ArrayList;

import com.kd.hockymain.Extra;
import com.kd.hockymain.Listplayeradapter;
import com.kd.hockymain.PeriodSpinner;
import com.kd.hockymain.PlayerSpinner;
import com.kd.hockymain.R;
import com.kd.hockymain.ScorerData;
import com.kd.hockymain.TypeSpinner;





import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Gamenotes extends Activity {
	public int sectinonumber=0;
	/** Called when the activity is first created. */
	 
	  private String GetTextFromEditText(int id, View v)
	    {
	    	return ((EditText)v.findViewById(id)).getText().toString();
	    }
	    
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	
		setContentView(R.layout.gamenotes);
	
		EditText ed = (EditText)findViewById(R.id.gamenotes);
		ed.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				Extra.Notes=s.toString();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				Extra.Notes=s.toString();
			}
		});
	}
	
	

}
