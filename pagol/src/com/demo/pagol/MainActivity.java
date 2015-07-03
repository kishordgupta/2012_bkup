package com.demo.pagol;




import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{
	String time, date;
	TextView tvTime, tvDate;
	java.util.Date noteTS;
	private Button filePickerButton; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		filePickerButton = (Button)findViewById(R.id.filePickerButton);
		
		filePickerButton.setOnClickListener(this);
		 tvTime =(TextView)findViewById(R.id.time);
		Thread t = new Thread() {

			  @Override
			  public void run() {
			    try {
			      while (!isInterrupted()) {
			        Thread.sleep(1000);
			        runOnUiThread(new Runnable() {
			          @Override
			          public void run() {
			        	  updateTextView() ;
			          }
			        });
			      }
			    } catch (InterruptedException e) {
			    }
			  }
			};

			t.start();

		
	}
	
	private void updateTextView() {
	    noteTS = Calendar.getInstance().getTime();

	    time = "hh:mm:ss"; // 12:00
	    tvTime.setText(DateFormat.format(time, noteTS));

	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.filePickerButton:
			Intent intent = new Intent(getApplicationContext(), FileChooserActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
		
	}

	

	
}
