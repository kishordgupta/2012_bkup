package com.mom.lilait.mothersday.ma.wish;

import java.io.IOException;

import com.lilakhelait.kishor.helper.Email;
import com.lilakhelait.kishor.helper.Share;
import com.lilakhelait.kishor.helper.Sms;
import com.mom.lilait.mothersday.ma.wish.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class ShowCity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.data);
	  ScrollView l = (ScrollView)findViewById(R.id.add);
	  Bundle extras = getIntent().getExtras();
	  String city = extras.getString("city_names");
	   MyAndroidAppActivity.Title=city;
	   getParent().setTitle(city);
	  final String cityd = extras.getString("city_name");
		final Context c = this;
		String s = city.toLowerCase().replace(" ", "");
		s=s+".jpg";
	
		try {
			Drawable d = Drawable.createFromStream(this.getAssets().open(s), null);
		l.setBackgroundDrawable(d);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  final TextView tv = (TextView)findViewById(R.id.textView1);
      tv.setText(cityd);
      tv.setTextColor(Color.BLACK);
      
      Button bigb =(Button)findViewById(R.id.bsms);
      bigb.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Sms.sms(cityd, c);
		}
	});
      Button sb =(Button)findViewById(R.id.bemail);
      sb.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Email.email(c, "Happy Mothers day",cityd);
		}
	});
      
      Button ssb =(Button)findViewById(R.id.bpass);
      ssb.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		Share.share(cityd, c);
		}
	});
//      setContentView(tv);
      
	}
}
