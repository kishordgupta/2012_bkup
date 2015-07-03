package com.ekushevashaandolon.motherlanguageday.myandroid;


import com.ekushevashaandolon.motherlanguageday.myandroid.R;
import com.lilakhelait.kishor.helper.Email;
import com.lilakhelait.kishor.helper.Share;
import com.lilakhelait.kishor.helper.Sms;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ShowCity extends Activity{
	protected int grop1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  Bundle extras = getIntent().getExtras();
	  final String city = extras.getString("city_name");
	  String ti = extras.getString("til");
	  grop1=extras.getInt("grp");
	  Log.d("sdf",grop1+"");
	  setContentView(R.layout.city_row);
	final Context con=this;
	  TextView tv = (TextView)findViewById(R.id.textvie2);
      
    Typeface  mFace = Typeface.createFromAsset(this.getAssets(),"Siyamrupali_1_01.ttf");
    tv.setTypeface(mFace,Typeface.BOLD);
    tv.setTextColor(Color.RED);

    tv.setText(city);
    
    TextView tv1 = (TextView)findViewById(R.id.textvie1);
    
   
    tv1.setTypeface(mFace,Typeface.BOLD);
    tv1.setTextColor(Color.BLUE);
 
    tv1.setText(ti);
    
	Button sms;
  	Button share;
  	Button email;
     
      sms = (Button)findViewById(R.id.bsms);
      email= (Button)findViewById(R.id.bemail);
   
   
      
 
  
    
      sms.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Share.share(city,con);
				Button t=(Button)v;
				t.setTextColor(Color.WHITE);
			}
		});
      
      email.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(grop1==1){
		FirstGroup1.group.back();
				}
				else
				{
					FirstGroup.group.back();
				
				}
			}
		});
    
		
	}
@Override
public void onBackPressed() {
	// TODO Auto-generated method stub

}
}
