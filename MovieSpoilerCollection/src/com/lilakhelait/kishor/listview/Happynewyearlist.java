package com.lilakhelait.kishor.listview;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.lilait.movie.story.spoiler.R;
import com.lilakhelait.kishor.helper.Email;
import com.lilakhelait.kishor.helper.Share;
import com.lilakhelait.kishor.helper.Sms;
import com.lilakhelait.kishor.resource.Wish;

public class Happynewyearlist extends ArrayAdapter<Wish> {
	  private final Context context;
	  public static  ArrayList<Wish> newyearsvalues=new ArrayList<Wish>();
public int size=0;
	  public Happynewyearlist(Context context, int cityRow) {
	    super(context, R.layout.city_row, newyearsvalues);
	    this.context = context;
	     
	  
	  }

	 

	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	   final View row = inflater.inflate(R.layout.city_row, parent, false);
		final int index = position;
		ViewHolder holder;

		
		 holder = new ViewHolder();
		 holder.Title = (TextView)row.findViewById(R.id.textViewdate);
		
         
         row.setTag(holder);

		holder = (ViewHolder) row.getTag();
        Wish wish = newyearsvalues.get(index);
		if(wish!=null)
		{
		
			holder.Title.setText(wish.getWishtext());
			holder.Title.setTypeface(Typeface.DEFAULT_BOLD);
			holder.Title.setTextColor(Color.RED);
			//holder.Title.setVisibility(View.GONE);
		
//			holder.playerrank.setText(playerdata.playernumber);
//			holder.isactive.setChecked(playerdata.isactive);
			
		}
		return row;
	}
	} 

class ViewHolder {
	
	TextView Wish;
	TextView Title;
	Button sms;
	Button share;
	Button email;
	
}