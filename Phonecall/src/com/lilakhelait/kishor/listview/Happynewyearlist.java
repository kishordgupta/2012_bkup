package com.lilakhelait.kishor.listview;

import java.util.ArrayList;
import java.util.List;

import com.kd.phonecall.R;




import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Happynewyearlist extends ArrayAdapter<String> {
	  private final Context context;
	  public static  ArrayList<String> newyearsvalues=new ArrayList<String>();
public int size=0;
	  public Happynewyearlist(Context context) {
	    super(context, R.layout.list, newyearsvalues);
	    this.context = context;
	//    this.values = values;
	  
	  }

	 

	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	   final View row = inflater.inflate(R.layout.list, parent, false);
		final int index = position;
		ViewHolder holder;

		
		 holder = new ViewHolder();
		 holder.wtring = (TextView)row.findViewById(R.id.textView1);
        
         row.setTag(holder);

		holder = (ViewHolder) row.getTag();
        String String = newyearsvalues.get(index);
		if(String!=null)
		{
			holder.wtring.setText(String);
			
//			holder.playerrank.setText(playerdata.playernumber);
//			holder.isactive.setChecked(playerdata.isactive);
			
		}
		return row;
	}
	} 

class ViewHolder {
	
	TextView wtring;
	Button sms;
	Button share;
	Button email;
	
}