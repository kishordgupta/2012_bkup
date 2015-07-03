package com.kd.hockymain;

import java.util.ArrayList;
import java.util.Calendar;



import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;


public class DateSpinner extends Spinner implements
		DialogInterface.OnClickListener {
	
	public Context mContext;
	public String[] mDataList;
	ArrayAdapter<String> adapter;
	ArrayList<String> observerList = new ArrayList<String>();
	String[] items;
	ArrayAdapter<String> observerArrayAdapter;
	 private int mYear;
	 private int mMonth;
	 private int mDay;
	 static final int TIME_DIALOG_ID = 0;
	    static final int DATE_DIALOG_ID = 1;
	    
	    
	public DateSpinner(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		
			observerList.add("SELECT DATE");
		
		observerArrayAdapter = new ArrayAdapter<String>(
				context,
				android.R.layout.simple_spinner_item, 
				observerList);
		this.setAdapter(observerArrayAdapter);
		final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
   
		
	}
	public void p(String a)
	{	observerArrayAdapter.notifyDataSetChanged();
		
	observerList.clear();
		
	observerList.add(a);
	Extra.Time=a;
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
			DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, mDateSetListener, mYear, mMonth,mDay);
			datePickerDialog.show();
			
		}
		return handled;
	}
	 private DatePickerDialog.OnDateSetListener mDateSetListener =
	            new DatePickerDialog.OnDateSetListener() {

	                public void onDateSet(DatePicker view, int year, int monthOfYear,
	                        int dayOfMonth) {
	                    mYear = year;
	                    mMonth = monthOfYear+1;
	                    mDay = dayOfMonth;
	                    String a;
	                    if( mDay>9)
		                   {
		                	   a= mDay+"-";
		                   }
		                   else
		                   {
		                	   a=0+""+ mDay+"-";
		                   }
		                   if(mMonth>9)
		                   {
		                	   a=a+mMonth+"-";
		                   }
		                   else
		                   {
		                	   a=a+ 0+""+mMonth+"-";
		                   }
		                   if( year>9)
		                   {
		                	   a= a+year+"";
		                   }
		                   else
		                   {
		                	   a=0+""+ year;
		                   } 
		                //   AUDITDATA.Datetime=a;
	                   // String a;
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