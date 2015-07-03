package com.kd.kdstagemanagement.list;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.kd.dasinnovation.movie.chickflicksurvivalguide.R;
import com.kd.kdstagemanagement.model.String;
import com.kd.kdstagemanagement.settings.Getdata;
import com.kd.kdstagemanagement.settings.SettingsHelp;
import com.kd.kdstagemanagement.view.BaseActivity;

public class Searchmovielist extends ArrayAdapter<String> {
	  private final Context context;
	  public static  ArrayList<String> newyearsvalues=new ArrayList<String>();
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
        final String Movies = newyearsvalues.get(index);
		if(Movies!=null)
		{
			holder.Movies.setText(Movies.Filters);
			holder.Movies.setChecked(Getdata.LoadPreferences(context,"search_"+Movies.Filters));
			holder.Movies.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if(isChecked)
					{
						Getdata.SavePreferences(context,"search_"+Movies.Filters, isChecked);
					}
				}
			});
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