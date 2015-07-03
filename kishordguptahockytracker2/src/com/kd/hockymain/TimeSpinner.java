package com.kd.hockymain;

import java.util.ArrayList;
import java.util.Calendar;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;


public class TimeSpinner extends Spinner implements
		DialogInterface.OnClickListener {
	
	public Context mContext;
	public String[] mDataList;
	ArrayAdapter<String> adapter;
	ArrayList<String> observerList = new ArrayList<String>();
	String[] items;
	ArrayAdapter<String> observerArrayAdapter;
	private int mHour=-1;
	private int mMinute=-1;
	    
	    
	public TimeSpinner(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		
		observerList.add("SELECT TIME");
		observerArrayAdapter = new ArrayAdapter<String>(
				context,
				android.R.layout.simple_spinner_item, 
				observerList);
		this.setAdapter(observerArrayAdapter);
		 final Calendar c = Calendar.getInstance();
	        mHour = c.get(Calendar.HOUR_OF_DAY);
	        mMinute = c.get(Calendar.MINUTE);
   
		
	}
	public void p(String a)
	{	observerArrayAdapter.notifyDataSetChanged();
		
	observerList.clear();
		Extra.Date=a;
	observerList.add(a);
		observerArrayAdapter.notifyDataSetChanged();
		observerArrayAdapter = new ArrayAdapter<String>(
				this.mContext,
				android.R.layout.simple_spinner_item, 
				observerList);
		this.setAdapter(observerArrayAdapter);
		observerArrayAdapter.notifyDataSetChanged();
		refreshDrawableState();
	}

	@Override
	public boolean performClick() {
		boolean handled = false;
		if (!handled) {
			handled = true;
			TimePickerDialog timePickerDialog = new TimePickerDialog(mContext,
                    mTimeSetListener, mHour, mMinute, false);
			timePickerDialog.show();
			
		}
		return handled;
	}
	 private TimePickerDialog.OnTimeSetListener mTimeSetListener =
	            new TimePickerDialog.OnTimeSetListener() {

	                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
	                    mHour = hourOfDay;
	                   mMinute = minute;
	                   String a;
	                  
	                   if(mHour>9)
	                   {
	                	   a=mHour+"-";
	                   }
	                   else
	                   {
	                	   a=0+""+mHour+"-";
	                   }
	                   if(mMinute>9)
	                   {
	                	   a=a+mMinute;
	                   }
	                   else
	                   {
	                	   a=a+ 0+""+mMinute;
	                   }
	                   
	                   if(mHour>12)
	                	   a=a+" " +"PM";
	                   else
	                	   a=a+" " +"AM";
	              //     AUDITDATA.Hourmininute=a;
	                   p(a);
	                   
	                   	                }
	            };

	@Override
	public void onClick(DialogInterface dialog, int which) {
		observerList.clear();
		observerList.add(items[which]);
		observerArrayAdapter.notifyDataSetChanged();
		dialog.dismiss();
		
	}
}