package com.kd.kdstagemanagement.list;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kd.dasinnovation.movie.chickflicksurvivalguide.R;
import com.kd.kdstagemanagement.model.Leveline;
import com.kd.kdstagemanagement.model.Movies;

public class Drinklevellist extends ArrayAdapter<Leveline> {
	  private final Context context;
	  public static  ArrayList<Leveline> newyearsvalues=new ArrayList<Leveline>();
public int size=0;
//public Typeface mFace ;
	  public Drinklevellist(Context context) {
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
		ViewHol holder;

		
		 holder = new ViewHol();
		 holder.Movies = (TextView)row.findViewById(R.id.loadshowtitle);
       
         row.setTag(holder);

		holder = (ViewHol) row.getTag();
		Leveline Movies = newyearsvalues.get(index);
		if(Movies!=null)
		{
			holder.Movies.setText(Movies.level);
			
//			holder.playerrank.setText(playerdata.playernumber);
//			holder.isactive.setChecked(playerdata.isactive);
			
		}
		return row;
	}
	} 

class ViewHol {
	
	TextView Movies;

	
}