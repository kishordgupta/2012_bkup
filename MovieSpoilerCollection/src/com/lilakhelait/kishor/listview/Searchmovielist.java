package com.lilakhelait.kishor.listview;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.lilait.movie.story.spoiler.R;
import com.lilakhelait.kishor.resource.Wish;



public class Searchmovielist extends ArrayAdapter<Wish> {
	  private final Context context;
	  public static  ArrayList<Wish> newyearsvalues=new ArrayList<Wish>();
public int size=0;
//public Typeface mFace ;
	  public Searchmovielist(Context context) {
	    super(context, R.layout.load_list_checkitem, newyearsvalues);
	    this.context = context;
	//    this.values = values;
	//    mFace = Typeface.createFromAsset(context.getAssets(),"SIYAMRUPALI_1_01.TTF");
	  }

	 

	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    if(convertView==null){
	   final View row = inflater.inflate(R.layout.load_list_checkitem, parent, false);
		final int index = position;
		ViewHolde holder;

		
		 holder = new ViewHolde();
		 holder.Movies = (CheckBox)row.findViewById(R.id.loadshowtitle);
       
         row.setTag(holder);

		holder = (ViewHolde) row.getTag();
        final Wish Movies = newyearsvalues.get(index);
		if(Movies!=null)
		{
			holder.Movies.setText(Movies.getWishtext());
			
//			holder.playerrank.setText(playerdata.playernumber);
//			holder.isactive.setChecked(playerdata.isactive);
			
		}
		convertView=row;
	    }
	    else
	    {
	    	
	    }
		return convertView;
	}
	} 

class ViewHolde {
	
	CheckBox Movies;

	
}