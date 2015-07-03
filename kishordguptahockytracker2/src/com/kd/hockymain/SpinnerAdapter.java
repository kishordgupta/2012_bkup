package com.kd.hockymain;

import java.util.ArrayList;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


public class SpinnerAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    
    private ArrayList<String> dataList = new ArrayList<String>();

    public SpinnerAdapter(Context context,ArrayList<String> dataList) {
        // Cache the LayoutInflate to avoid asking for a new one each time.
        mInflater = LayoutInflater.from(context);
        this.dataList = dataList;
       
    }
    public int getCount() {
      //  return DATA.length;
    	return dataList.size();
    	//return numberOfRow;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.spinner_adapter_list_item, null);
            holder = new ViewHolder();
            
            holder.observer = (TextView) convertView.findViewById(R.id.textViewObserver);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.observer.setText(dataList.get(position));
        return convertView;
    }

    static class ViewHolder {
      TextView observer;
    }
}