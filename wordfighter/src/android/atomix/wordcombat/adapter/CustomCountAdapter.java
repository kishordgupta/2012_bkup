package android.atomix.wordcombat.adapter;

import java.util.ArrayList;

import android.atomix.wordcombat.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class CustomCountAdapter extends BaseAdapter {

	private static ArrayList<Integer> allImageList;
	private LayoutInflater mInflater;

	public CustomCountAdapter(Context context, ArrayList<Integer> imageList) 
	{
		allImageList = imageList;
		mInflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() 
	{
		return allImageList.size();
	}

	@Override
	public Object getItem(int position) 
	{
		return allImageList.get(position);
	}

	@Override
	public long getItemId(int position) 
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		ViewHolder holder;

		if (convertView == null) 
		{
			convertView = mInflater.inflate(R.layout.counter_row, null);

			holder = new ViewHolder();
			holder.imageView = (ImageView) convertView.findViewById(R.id.img_view_counter);

			convertView.setTag(holder);
			
		} 
		else 
		{
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.imageView.setBackgroundResource(allImageList.get(position));

		return convertView;
	}
	
	static class ViewHolder 
	{
		ImageView imageView;
	}

}
