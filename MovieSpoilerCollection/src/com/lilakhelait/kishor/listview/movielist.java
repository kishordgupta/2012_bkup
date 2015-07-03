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
import com.lilakhelait.kishor.resource.Constants;
import com.lilakhelait.kishor.resource.Wish;

public class movielist extends ArrayAdapter<Constants> {
	  private final Context context;
	 
public int size=0;

public static ArrayList<Constants> nes=new ArrayList<Constants>();
	  public movielist(Context context, int cityRow) {
	    super(context, R.layout.city_row, nes);
	    this.context = context;
	 
	     Log.d("sdcfvvf", nes.size()+"");
	  
	  }

	 

	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	  
	    final int index = position;
		ViewHolder1 holder;
	    if(convertView==null){
	  
		
	   LayoutInflater inflater = (LayoutInflater) context
       .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	   final View row = inflater.inflate(R.layout.city_row, parent, false);
		 Log.d("sdf", position+"");
		 holder = new ViewHolder1();
		 holder.Title = (TextView)row.findViewById(R.id.textViewdate);
		
         
         row.setTag(holder);
         convertView=row;
	    }
	    else
	    {
	    	
	    }
		holder = (ViewHolder1)  convertView.getTag();
		Constants wish = nes.get(index);
		if(wish!=null)
		{
			
			holder.Title.setText(wish.s);
			holder.Title.setTypeface(Typeface.DEFAULT_BOLD);
			holder.Title.setTextColor(Color.RED);
			//holder.Title.setVisibility(View.GONE);
		
//			holder.playerrank.setText(playerdata.playernumber);
//			holder.isactive.setChecked(playerdata.isactive);
			
		}
		return  convertView;
	}
	} 

class ViewHolder1 {
	
	TextView Wish;
	TextView Title;
	Button sms;
	Button share;
	Button email;
	
}