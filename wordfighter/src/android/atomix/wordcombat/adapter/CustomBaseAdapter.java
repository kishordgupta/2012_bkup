package android.atomix.wordcombat.adapter;

import java.util.ArrayList;

import android.atomix.wordcombat.R;
import android.atomix.wordcombat.supports.MyCustomTextView;
import android.atomix.wordcombat.supports.WordCombatApp;
import android.atomix.wordcombat.supports.WordInfo;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CustomBaseAdapter extends BaseAdapter {

	private static ArrayList<WordInfo> allWordList;
	private LayoutInflater mInflater;
	private int currentLanguageIndex = 0;

	public CustomBaseAdapter(Context context, ArrayList<WordInfo> wordList) 
	{
		allWordList = wordList;
		mInflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() 
	{
		return allWordList.size();
	}

	@Override
	public Object getItem(int position) 
	{
		return allWordList.get(position);
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
			convertView = mInflater.inflate(R.layout.show_word_list_raw, null);

			holder = new ViewHolder();
			holder.wordName = (MyCustomTextView) convertView.findViewById(R.id.txt_view_name);
			holder.wordMeaning = (MyCustomTextView) convertView.findViewById(R.id.txt_view_meaning);
			holder.languageName = (MyCustomTextView) convertView.findViewById(R.id.txt_view_language_name);

			convertView.setTag(holder);
			
		} 
		else 
		{
			holder = (ViewHolder) convertView.getTag();
		}
		
		if(position == 0)
		{
			currentLanguageIndex = position;
			holder.languageName.setVisibility(View.VISIBLE);
			holder.languageName.setText(WordCombatApp.getInstance().getWordInfosList().get(position).getLanguageName());
			holder.languageName.setTextColor(Color.rgb(70, 70, 170));
		}
		else
		{
			if(!WordCombatApp.getInstance().getWordInfosList().get(currentLanguageIndex).getLanguageName().equals(WordCombatApp.getInstance().getWordInfosList().get(position).getLanguageName()))
			{
				currentLanguageIndex = position;
				holder.languageName.setVisibility(View.VISIBLE);
				holder.languageName.setText(WordCombatApp.getInstance().getWordInfosList().get(position).getLanguageName());
				holder.languageName.setTextColor(Color.rgb(70, 70, 170));
			}
			
			else
			{
				holder.languageName.setVisibility(View.GONE);
			}
		}
		
		

		holder.wordName.setText(allWordList.get(position).getWordName());
		holder.wordMeaning.setText(allWordList.get(position).getWordMeaning());

		return convertView;
	}
	
	static class ViewHolder 
	{
		MyCustomTextView wordName;
		MyCustomTextView wordMeaning;
		MyCustomTextView languageName;
	}

}
