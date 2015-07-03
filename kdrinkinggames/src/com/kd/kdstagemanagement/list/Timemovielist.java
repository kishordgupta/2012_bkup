package com.kd.kdstagemanagement.list;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;



import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.kd.dasinnovation.movie.chickflicksurvivalguide.R;
import com.kd.kdstagemanagement.model.String;
import com.kd.kdstagemanagement.model.Timeline;

public class Timemovielist extends ArrayAdapter<Timeline> {
	  private final Context context;
	  public static  ArrayList<Timeline> newyearsvalues=new ArrayList<Timeline>();
public int size=0;
//public Typeface mFace ;
	  public Timemovielist(Context context) {
	    super(context, R.layout.run_list_item, newyearsvalues);
	    this.context = context;
	//    this.values = values;
	//    mFace = Typeface.createFromAsset(context.getAssets(),"SIYAMRUPALI_1_01.TTF");
	  }

	
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {		
		  LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			if (convertView == null) {
				convertView = vi.inflate(R.layout.run_list_item, null);
			}

			Timeline myData = newyearsvalues.get(position);

			if (myData != null) {
				TextView text = (TextView) convertView.findViewById(R.id.time);
				if (text != null) {
					text.setText(myData.time);
				}
				TextView textq = (TextView) convertView.findViewById(R.id.title);
				if (textq != null) {
					textq.setText(myData.Type);
				}
				TextView counter = (TextView) convertView.findViewById(R.id.countdowntime);
				if (counter != null) {
					counter.setText(myData.getCountAsString());				
				}
			}			

			return convertView;
		}		
//	@Override
//	  public View getView(int position, View convertView, ViewGroup parent) {
//	    LayoutInflater inflater = (LayoutInflater) context
//	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//	    if(convertView==null){
//	   final View row = inflater.inflate(R.layout.run_list_item, parent, false);
//		final int index = position;
//		final ViewHolder12 holder;
//
//		
//		 holder = new ViewHolder12();
//		 holder.time = (TextView)row.findViewById(R.id.time);
//		 holder.Title = (TextView)row.findViewById(R.id.title);
//		 holder.countdown=(TextView)row.findViewById(R.id.countdowntime);
//       
//         row.setTag(holder);
//
//		//holder = (ViewHolder12) row.getTag();
//		Timeline Movies = newyearsvalues.get(index);
//		if(Movies!=null)
//		{
//			holder.Title.setText(Movies.Type);
//			holder.time.setText(Movies.time);
//			holder.countdown.setText(Movies.getCountAsString());
//
//			
//		}
//		convertView=row;
//	    }
//	    else
//	    {
//	    	
//	    }
//		return convertView;
//	}
	} 

class ViewHolder12 {
	
	TextView time;
	TextView countdown;
	TextView Title;

	
}