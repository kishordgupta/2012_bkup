package com.kd.hockymain;

import java.util.ArrayList;




import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SiteSpinner extends Spinner implements
		DialogInterface.OnClickListener {
	
	public Context mContext;
	public String[] mDataList;
	ArrayAdapter<String> adapter;
	ArrayList<String> observerList = new ArrayList<String>();
	String[] items;
	ArrayList<String> facilityTitle = new ArrayList<String>();
	SimpleCursorAdapter cs=null;
	ArrayAdapter<String> observerArrayAdapter;
	public SiteSpinner(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
	
	
	      
		
		
		facilityTitle.add("");
		observerArrayAdapter = new ArrayAdapter<String>(
				context,
				android.R.layout.simple_spinner_item, 
				facilityTitle);
		this.setAdapter(observerArrayAdapter);
		//this.setAdapter(cs);
		;
	}

	@Override
	public boolean performClick() {
		boolean handled = false;
		if (!handled) {
			handled = true;
			ObserverSpinnerDialog observerSpinnerDialog = new ObserverSpinnerDialog(mContext);
			observerSpinnerDialog.setTitle("Select Option");
			observerList.clear();
		
			observerList.add("Site1");
			observerList.add("Site2");
			observerSpinnerDialog.show();
			
			
		}
		return handled;
	}
	public void p(String string)
	{	observerArrayAdapter.notifyDataSetChanged();
	//Spinnertitle.Sector=string;
		facilityTitle.clear();
		facilityTitle.add(string);
	
		observerArrayAdapter.notifyDataSetChanged();
		observerArrayAdapter = new ArrayAdapter<String>(
				this.mContext,
				android.R.layout.simple_spinner_item, 
				facilityTitle);
		this.setAdapter(observerArrayAdapter);
		observerArrayAdapter.notifyDataSetChanged();
		refreshDrawableState();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		observerList.clear();
		observerList.add(items[which]);
		observerArrayAdapter.notifyDataSetChanged();
		dialog.dismiss();
		
	}
	@Override
	public void setOnItemSelectedListener(
		OnItemSelectedListener listener) {
		
		super.setOnItemSelectedListener(listener);
	}
	
	
	class ObserverSpinnerDialog extends Dialog implements OnItemClickListener{
		ListView observerListView;
		private Context context;
		public ObserverSpinnerDialog(Context context) {
			super(context);
			this.context = context;
			// TODO Auto-generated constructor stub
		}
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.observer_dialog);
			observerListView = (ListView)findViewById(R.id.listView_observer_select);
			observerListView.setAdapter(new SpinnerAdapter(context,observerList));
			//observerListView.setAdapter(new SpinnerAdapter(context,observerList));
			observerListView.setOnItemClickListener(this);
		}
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			//Toast.makeText(context, "abcd",Toast.LENGTH_SHORT).show();
			//AUDITDATA.OBSERVERNAME=	observerList.get(arg2);
	//	Log.d("OBSERVERNAME", AUDITDATA.OBSERVERNAME);
		p(observerList.get(arg2));
			this.dismiss();
			
		}
		
	}

}