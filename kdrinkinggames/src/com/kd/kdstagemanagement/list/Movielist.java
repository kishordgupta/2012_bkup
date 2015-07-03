package com.kd.kdstagemanagement.list;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kd.dasinnovation.movie.chickflicksurvivalguide.R;
import com.kd.kdstagemanagement.model.Movies;

public class Movielist extends ArrayAdapter<Movies> {
	  private final Context context;
	  public static  ArrayList<Movies> newyearsvalues=new ArrayList<Movies>();
public int size=0;
//public Typeface mFace ;
	  public Movielist(Context context) {
	    super(context, R.layout.load_list_item, newyearsvalues);
	    this.context = context;
	//    this.values = values;
	//    mFace = Typeface.createFromAsset(context.getAssets(),"SIYAMRUPALI_1_01.TTF");
	  }

	 

	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	   final View row = inflater.inflate(R.layout.load_list_item, parent, false);
		final int index = position;
		ViewHolder holder;

		
		 holder = new ViewHolder();
		 holder.Movies = (TextView)row.findViewById(R.id.loadshowtitle);
       
         row.setTag(holder);

		holder = (ViewHolder) row.getTag();
        Movies Movies = newyearsvalues.get(index);
		if(Movies!=null)
		{
			holder.Movies.setText(Movies.Movietitle);
			
//			holder.playerrank.setText(playerdata.playernumber);
//			holder.isactive.setChecked(playerdata.isactive);
			
		}
		return row;
	}
	} 

class ViewHolder {
	
	TextView Movies;

	
}