package com.lilakhelait.kishor.listview;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.lilakhelait.kishor.helper.Email;
import com.lilakhelait.kishor.helper.Share;
import com.lilakhelait.kishor.helper.Sms;

import com.lilakhelait.kishor.resource.Wish;
import com.mom.lilait.mothersday.ma.wish.R;




import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
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

public class Poriched1 extends ArrayAdapter<Wish> {
	  private final Context context;
	  public  ArrayList<Wish> newyearsvalues=new ArrayList<Wish>();
public int size=0;

//public Typeface mFace ;
	  public Poriched1(Context context, ArrayList<Wish> newyearsvalues) {
	    super(context, R.layout.list, newyearsvalues);
	    this.context = context;
    
	   this.newyearsvalues = newyearsvalues;
	//    mFace = Typeface.createFromAsset(context.getAssets(),"SIYAMRUPALI_1_01.TTF");
	  }

	 

	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	   final View row = inflater.inflate(R.layout.list, parent, false);
		final int index = position;
		ViewHoldee22r holder;

		
		 holder = new ViewHoldee22r();
		 holder.Wish = (TextView)row.findViewById(R.id.textView1);
		 holder.im = (ImageView)row.findViewById(R.id.dd);
      

//         row.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				
//				v.setBackgroundColor(Color.DKGRAY);
//			}
//		});
//         holder.Wish.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				TextView t=(TextView)v;
//			
//				row.setBackgroundColor(Color.DKGRAY);	// TODO Auto-generated method stub
//			
//	//		t.setText("দফগদফগদফ");
//			
//			}
//		});
//         
//         holder.share.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Share.share(newyearsvalues.get(index).getWishtext(), context);
//				Button t=(Button)v;
//				t.setTextColor(Color.WHITE);
//			}
//		});
//       
//         holder.sms.setOnClickListener(new OnClickListener() {
// 			
// 			@Override
// 			public void onClick(View v) {
// 				// TODO Auto-generated method stub
// 				Sms.sms(newyearsvalues.get(index).getWishtext(), context);
// 				Button t=(Button)v;
//				t.setTextColor(Color.WHITE);
// 			}
// 		});
//         
//         holder.email.setOnClickListener(new OnClickListener() {
//  			
//  			@Override
//  			public void onClick(View v) {
//  				// TODO Auto-generated method stub
//  				Email.email(context, "Happy new year", newyearsvalues.get(index).getWishtext());
//  				Button t=(Button)v;
//  				t.setTextColor(Color.WHITE);
//  			}
//  		});
         
         row.setTag(holder);

		holder = (ViewHoldee22r) row.getTag();
        Wish wish = newyearsvalues.get(index);
		if(wish!=null)
		{
			holder.Wish.setText(wish.getTitle());
			holder.Wish.setTextColor(Color.BLACK);
			String s = wish.getTitle().toLowerCase().replace(" ", "");
			s=s+".jpg";
			try {
				Drawable d = Drawable.createFromStream(context.getAssets().open(s), null);
				holder.im.setBackgroundDrawable(d);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return row;
	}
	} 

class ViewHoldee22r {
	
	TextView Wish;
    ImageView im;
	
}