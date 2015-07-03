package com.kishordgupta.prototype;

import com.kishordgupta.model.Event;
import com.kishordgupta.model.Events;
import com.kishordgupta.prototype.R.id;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ResultActivity extends Activity implements OnClickListener{
	 String Result="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		
		
		for(Event e:Events.eventlist)
		{
			Result = Result+e.getTime() +"?"+e.getName()+"\n";
			
		}
		
		Button share = (Button)findViewById(R.id.share);
		share.setOnClickListener(this);
		
		EditText Resulttext =(EditText)findViewById(R.id.Resulttext);
		Resulttext.setText(Result);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.share)
		{
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT, Result);
			sendIntent.setType("text/plain");
			startActivity(sendIntent);
		}
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Events.eventlist.clear();
	}
}
