package com.lilait.football.toptenteams;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.lilait.football.toptenteams.R;
import com.lilakhelait.kishor.helper.Share;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class SecondActivityGroup extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.city_row);
  
        ScrollView s =(ScrollView)findViewById(R.id.f);
       s.setBackgroundDrawable(getResources().getDrawable(R.drawable.vsp6yf));
       ImageView im =(ImageView)findViewById(R.id.imageView1);
       im.setVisibility(View.GONE);
    	final Context con=this;
    	  TextView tv = (TextView)findViewById(R.id.textvie2);
          
        Typeface  mFace = Typeface.DEFAULT_BOLD;
        tv.setTypeface(mFace, Typeface.BOLD);
        tv.setTextColor(Color.RED);

        tv.setVisibility(View.GONE);
        
        final TextView tv1 = (TextView)findViewById(R.id.textvie1);
        
       
        tv1.setTypeface(mFace, Typeface.BOLD);
        tv1.setTextColor(Color.RED);
     
        tv1.setText(getResources().getString(R.string.share));
       
  		  Button buwall=(Button)findViewById(R.id.wallpaper);
  		  buwall.setVisibility(View.GONE);
    	Button sms;
      	Button share;
      	Button email;
         
          sms = (Button)findViewById(R.id.bsms);
          email= (Button)findViewById(R.id.bemail);
       
       
          
     
      
        
          sms.setOnClickListener(new OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    				// TODO Auto-generated method stub
    				Share.share(tv1.getText().toString(),con);
    				Button t=(Button)v;
    				t.setTextColor(Color.WHITE);
    			}
    		});
          
          email.setVisibility(View.GONE);
    }

}
