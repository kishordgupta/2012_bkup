package com.ekushevashaandolon.motherlanguageday.myandroid;

import com.kd.phonecall.PhoneCallReceiver;
import com.kd.phonecall.R;
import com.lilakhelait.kishor.helper.Share;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

public class SecondActivityGroup extends Activity {
	static int flag=0;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.city_row);
        ScrollView s =(ScrollView)findViewById(R.id.f);
       // s.setBackgroundDrawable(getResources().getDrawable(R.drawable.vsp6yf));
    	final Context con=this;
    	TextView t =(TextView)findViewById(R.id.button1);
    	t.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final CharSequence[] items = {"Normal", "Vibration","Total blockering"};

		        AlertDialog.Builder builder = new AlertDialog.Builder(con);
		        builder.setTitle("Välj läge");
		        builder.setSingleChoiceItems(items, flag, new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int item) {
flag=item;
		            	//dialog.dismiss(); 
		            	if(item==0)
		            	PhoneCallReceiver.call=1;
		            	else
		            		PhoneCallReceiver.call=0;
		            	dialog.dismiss(); 
		            }
		        });

		        AlertDialog alert = builder.create();   
		        alert.show();
			}
		});
    	
    	
    }

}
