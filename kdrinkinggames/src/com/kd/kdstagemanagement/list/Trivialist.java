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
import com.kd.kdstagemanagement.model.TripsNtrivia;
import com.kd.kdstagemanagement.model.Trivialline;

public class Trivialist extends ArrayAdapter<Trivialline> {
	  private final Context context;
	  public static  ArrayList<Trivialline> newyearsvalues=new ArrayList<Trivialline>();
public int size=0;
//public Typeface mFace ;
	  public Trivialist(Context context) {
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
		der holder;

		
		 holder = new der();
		 holder.Movies = (TextView)row.findViewById(R.id.loadshowtitle);
       
         row.setTag(holder);

		holder = (der) row.getTag();
		Trivialline Movies = newyearsvalues.get(index);
		if(Movies!=null)
		{
			holder.Movies.setText(Movies.Type);
			
//			holder.playerrank.setText(playerdata.playernumber);
//			holder.isactive.setChecked(playerdata.isactive);
			
		}
		return row;
	}
	} 

class der {
	
	TextView Movies;

	
}