package com.ekushevashaandolon.motherlanguageday.myandroid;

import java.util.Calendar;

import com.kd.phonecall.R;
import com.lilakhelait.kishor.helper.GetNetworkStatus;
import com.lilakhelait.kishor.helper.Share;
import com.lilakhelait.kishor.utility.StackParser;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Start extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.start);
     
    	final Context c = this;
        
    	Button sms;
      	Button share;
      	Button email;
         TextView t =(TextView)findViewById(R.id.updatetime);

         
         final SharedPreferences settings = getSharedPreferences("ph", 0);
         t.setText(t.getText()+settings.getString("time", "  no update yet "));
         
          sms = (Button)findViewById(R.id.bsms);
        
          sms.setOnClickListener(new OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    				// TODO Auto-generated method stub
    			Button b =(Button)v;
    			b.setTextColor(Color.RED);
    			//Toast.makeText(c, "Where is network . bro?", Toast.LENGTH_SHORT).show();
    			try{
    				if(GetNetworkStatus.isNetworkAvailable(c))
    			        {startActivity(new Intent(c, StackParser.class));
    			        SharedPreferences.Editor editor = settings.edit();
    				      editor.putBoolean("silentMode", true);
    				      String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
    				      editor.putString("time",mydate);
    				      editor.commit();
    			     //   Toast.makeText(c, "sdsssssssssssssWhere is network . bro?", Toast.LENGTH_LONG).show();
    			        }
    				 else
    				 {
    					 Toast.makeText(c, "Where is network ?", Toast.LENGTH_LONG).show();
    				 }
    				}catch (Exception e) {
    					// TODO: handle exception
    				}
    		//	Toast.makeText(c, "Where is sdfsdfsdfsdfnetwork . bro?", Toast.LENGTH_LONG).show();
    			}
    		});
          

          Button     smsa = (Button)findViewById(R.id.info);
     
      
        
          smsa.setOnClickListener(new OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    				startActivity(new Intent(c, MaindActivity.class));
    				// TODO Auto-generated method stub
    		/*	Button b =(Button)v;
    			b.setTextColor(Color.RED);
    			//Toast.makeText(c, "Where is network . bro?", Toast.LENGTH_SHORT).show();
    			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
    					c);
    	 
    				// set title
    				alertDialogBuilder.setTitle("info");
    	 
    				// set dialog message
    				alertDialogBuilder
    					.setMessage(c.getText(R.string.info))
    					.setCancelable(false)
    					.setPositiveButton("ok",new DialogInterface.OnClickListener() {
    						public void onClick(DialogInterface dialog,int id) {
    							// if this button is clicked, close
    							// current activity
    						dialog.dismiss();
    						}
    					  });
    	 
    					// create alert dialog
    					AlertDialog alertDialog = alertDialogBuilder.create();
    	 
    					// show it
    					alertDialog.show();*/
    		//	Toast.makeText(c, "Where is sdfsdfsdfsdfnetwork . bro?", Toast.LENGTH_LONG).show();
    			}
    		});
          
    }

}
