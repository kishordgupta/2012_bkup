package com.demo.pagol;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class FileArrayAdapter extends ArrayAdapter<Option> {
	private Context context;
	private int id;
	private List<Option> items;

	public FileArrayAdapter(Context context, int textViewResourceId,
			List<Option> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		this.context=context;
		id= textViewResourceId;
		items = objects;
	}
	@Override
	public Option getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View v = convertView;
		if(v==null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(id, null);
		}
		final Option option = items.get(position);
		if(option !=null){
			TextView t1 = (TextView) v.findViewById(R.id.TextView01);
			TextView t2 = (TextView) v.findViewById(R.id.TextView02);
			if(t1!=null)
				t1.setText(option.getName());
			if(t2!=null)
				t2.setText(option.getData());
		}
		return v;
		// TODO Auto-generated method stub
		//return super.getView(position, convertView, parent);
	}

}
