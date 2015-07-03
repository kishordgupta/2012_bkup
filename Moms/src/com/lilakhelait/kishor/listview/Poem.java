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
import android.widget.TextView;

import com.lilakhelait.kishor.helper.Email;
import com.lilakhelait.kishor.helper.Share;
import com.lilakhelait.kishor.helper.Sms;
import com.lilakhelait.kishor.resource.Wish;
import com.mom.lilait.mothersday.ma.wish.R;

public class Poem extends ArrayAdapter<Wish> {
	  private final Context context;
	  public static  ArrayList<Wish> newyearsvalues=new ArrayList<Wish>();
public int size=0;
//public Typeface mFace ;
	  public Poem(Context context) {
	    super(context, R.layout.wishlist, newyearsvalues);
	    this.context = context;
	//    this.values = values;
	//    mFace = Typeface.createFromAsset(context.getAssets(),"SIYAMRUPALI_1_01.TTF");
	  }

	 

	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	   final View row = inflater.inflate(R.layout.wishlist, parent, false);
		final int index = position;
		ViewHolder holder;

		
		 holder = new ViewHolder();
		 holder.Wish = (TextView)row.findViewById(R.id.textView1);
        holder.share = (Button)row.findViewById(R.id.bpass);
         holder.sms = (Button)row.findViewById(R.id.bsms);
         holder.email= (Button)row.findViewById(R.id.bemail);
        // holder.Wish.setTypeface(mFace);
         row.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			//	v.setBackgroundColor(Color.DKGRAY);
			}
		});
         holder.Wish.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TextView t=(TextView)v;
				Typeface tf=Typeface.create(Typeface.SERIF, Typeface.BOLD_ITALIC);

				//row.setBackgroundColor(Color.DKGRAY);	// TODO Auto-generated method stub
				t.setTypeface(tf);
	//		t.setText("দফগদফগদফ");
			
			}
		});
         
         holder.share.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Share.share(newyearsvalues.get(index).getWishtext(), context);
				Button t=(Button)v;
				t.setTextColor(Color.WHITE);
			}
		});
       
         holder.sms.setOnClickListener(new OnClickListener() {
 			
 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 				Sms.sms(newyearsvalues.get(index).getWishtext(), context);
 				Button t=(Button)v;
				t.setTextColor(Color.WHITE);
 			}
 		});
         
         holder.email.setOnClickListener(new OnClickListener() {
  			
  			@Override
  			public void onClick(View v) {
  				// TODO Auto-generated method stub
  				Email.email(context, "Happy new year", newyearsvalues.get(index).getWishtext());
  				Button t=(Button)v;
  				t.setTextColor(Color.WHITE);
  			}
  		});
         
         row.setTag(holder);

		holder = (ViewHolder) row.getTag();
        Wish wish = newyearsvalues.get(index);
		if(wish!=null)
		{
			holder.Wish.setText(wish.getWishtext());
			holder.Wish.setTextColor(Color.RED);
			holder.Wish.setShadowLayer(1, 1, 1, Color.WHITE);
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