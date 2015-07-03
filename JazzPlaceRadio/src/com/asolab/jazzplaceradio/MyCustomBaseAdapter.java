package com.asolab.jazzplaceradio;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.asolab.jazzplaceradio.R;


public class MyCustomBaseAdapter extends BaseAdapter {
	
	
	private static ArrayList<ProgramDefinition> programList;
	private LayoutInflater mInflater;
	
	
	public MyCustomBaseAdapter(Context context, ArrayList<ProgramDefinition> programList) {
		// TODO Auto-generated constructor stub
		MyCustomBaseAdapter.programList = programList;
		mInflater = LayoutInflater.from(context);
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return programList.size();
	}

	@Override
	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return programList.get(index);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		ViewHolder holder;
		
		if(convertView==null){
			convertView = mInflater.inflate(R.layout.custom_row_view, null);
			holder = new ViewHolder();
			holder.txtName = (TextView) convertView.findViewById(R.id.name);
			holder.txtPhone = (TextView) convertView.findViewById(R.id.phone);
			convertView.setTag(holder);			
		}
		else{
			holder =(ViewHolder) convertView.getTag();
		}
		
		ProgramDefinition temp =programList.get(position); 
		
		holder.txtName.setText(temp.getProgramName());
		holder.txtPhone.setText(temp.getProgramTime());		
		
		return convertView;
	}
	static class ViewHolder {
		  TextView txtName;
		  TextView txtPhone;
		 }

}
