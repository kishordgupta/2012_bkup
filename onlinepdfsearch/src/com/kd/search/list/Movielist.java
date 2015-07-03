package com.kd.search.list;

import java.util.ArrayList;

import com.kd.search.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class Movielist extends ArrayAdapter<String> {
	  private final Context context;
	  public static  ArrayList<String> newyearsvalues=new ArrayList<String>();
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
		 holder.Movies = (WebView)row.findViewById(R.id.loadshowtitle);
       
         row.setTag(holder);

		holder = (ViewHolder) row.getTag();
        String Movies = newyearsvalues.get(index);
		if(Movies!=null)
		{
			holder.Movies.loadData(Movies,"text/html; charset=UTF-8",null);//setText(Movies);
			
//			holder.playerrank.setText(playerdata.playernumber);
//			holder.isactive.setChecked(playerdata.isactive);
			
		}
		return row;
	}
	} 

class ViewHolder {
	
	WebView Movies;

	
}