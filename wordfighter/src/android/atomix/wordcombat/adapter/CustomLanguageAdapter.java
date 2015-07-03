package android.atomix.wordcombat.adapter;

import java.util.ArrayList;

import android.atomix.wordcombat.R;
import android.atomix.wordcombat.supports.ConstantValues;
import android.atomix.wordcombat.supports.MyCustomTextView;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CustomLanguageAdapter extends BaseAdapter {

	private static ArrayList<String> allLanguageList;
	private LayoutInflater mInflater;

	public CustomLanguageAdapter(Context context, ArrayList<String> LanguageList) 
	{
		allLanguageList = LanguageList;
		mInflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() 
	{
		return allLanguageList.size();
	}

	@Override
	public Object getItem(int position) 
	{
		return allLanguageList.get(position);
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
			convertView = mInflater.inflate(R.layout.language_row, null);

			holder = new ViewHolder();
			holder.languageName = (MyCustomTextView) convertView.findViewById(R.id.txt_view_language_name);

			convertView.setTag(holder);
			
		} 
		else 
		{
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.languageName.setText(allLanguageList.get(position).toString());
		
		if(ConstantValues.languagePosition == position)
		{
			holder.languageName.setTextColor(Color.rgb(70, 70, 170));
		}
		
		else
		{
			holder.languageName.setTextColor(R.color.black);
		}

		return convertView;
	}
	
	static class ViewHolder 
	{
		MyCustomTextView languageName;
	}

}
