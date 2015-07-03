package com.lilakhelait.kishor.listview;

import java.util.ArrayList;
import java.util.List;

import com.lilakhelait.kishor.helper.Email;
import com.lilakhelait.kishor.helper.Share;
import com.lilakhelait.kishor.helper.Sms;
import com.lilakhelait.kishor.mainscreenactivity.R;
import com.lilakhelait.kishor.resource.Wish;




import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

public class Happynewyearlist extends ArrayAdapter<Wish> {
	  private final Context context;
	  public static  ArrayList<Wish> newyearsvalues=new ArrayList<Wish>();

	  public Happynewyearlist(Context context) {
	    super(context, R.layout.list, newyearsvalues);
	    this.context = context;
	//    this.values = values;
	  }

	 

	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	   View row = inflater.inflate(R.layout.list, parent, false);
		final int index = position;
		ViewHolder holder;

		 holder = new ViewHolder();
		 holder.Wish = (TextView)row.findViewById(R.id.textView1);
        holder.share = (Button)row.findViewById(R.id.bpass);
         holder.sms = (Button)row.findViewById(R.id.bsms);
         holder.email= (Button)row.findViewById(R.id.bemail);
         
         row.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				//notifyDataSetChanged();
				v.setBackgroundColor(Color.DKGRAY);
				
				return false;
			}
		});
         
         holder.share.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Share.share(newyearsvalues.get(index).getWishtext(), context);
			}
		});
       
         holder.sms.setOnClickListener(new OnClickListener() {
 			
 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 				Sms.sms(newyearsvalues.get(index).getWishtext(), context);
 			}
 		});
         
         holder.email.setOnClickListener(new OnClickListener() {
  			
  			@Override
  			public void onClick(View v) {
  				// TODO Auto-generated method stub
  				Email.email(context, "Happy new year", newyearsvalues.get(index).getWishtext());
  			}
  		});
         
         row.setTag(holder);

		holder = (ViewHolder) row.getTag();
        Wish wish = newyearsvalues.get(index);
		if(wish!=null)
		{
			holder.Wish.setText(wish.getWishtext());
//			holder.playerrank.setText(playerdata.playernumber);
//			holder.isactive.setChecked(playerdata.isactive);
			
		}
		return row;
	}
	} 

class ViewHolder {
	
	TextView Wish;
	Button sms;
	Button share;
	Button email;
	
}