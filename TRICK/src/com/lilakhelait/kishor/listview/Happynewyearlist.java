package com.lilakhelait.kishor.listview;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kd.trick.troll.friend.phone.R;

public class Happynewyearlist extends ArrayAdapter<String> {
	  private final Context context;
	  public static  ArrayList<String> newyearsvalues=new ArrayList<String>();
public int size=0;
//public Typeface mFace ;
	  public Happynewyearlist(Context context) {
	    super(context, R.layout.list, newyearsvalues);
	    this.context = context;
	//    this.values = values;
	//    mFace = Typeface.createFromAsset(context.getAssets(),"SIYAMRUPALI_1_01.TTF");
	  }

	 

	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	   final View row = inflater.inflate(R.layout.list, parent, false);
		final int index = position;
		ViewHolder holder;

		
		 holder = new ViewHolder();
		 holder.String = (TextView)row.findViewById(R.id.textView1);
		 holder.sms = (ImageView)row.findViewById(R.id.imageView1);
        // holder.String.setTypeface(mFace);
      
         
  
         row.setTag(holder);

		holder = (ViewHolder) row.getTag();
        String string = newyearsvalues.get(index);
		if(string!=null)
		{
			holder.String.setText(string);
			holder.String.setTextColor(Color.RED);
			holder.String.setShadowLayer(1, 1, 1, Color.WHITE);

		}
		return row;
	}
	} 

class ViewHolder {
	
	TextView String;
	ImageView sms;
	
	
}