package com.lilait.kd.calculator2;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.kd.kdstagemanagement.xl.CurrentActlist;
import com.kd.kdstagemanagement.xl.Kdexcel;
import com.kd.kdstagemanagement.xl.List;

import com.lilait.kd.calulatorrecorder.R;
import com.lilakhelait.kishor.helper.Email;
import com.lilakhelait.kishor.helper.Share;
import com.lilakhelait.kishor.helper.Sms;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class Kdprototype extends Activity implements OnClickListener{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.match);
		final Context context=this;
		Button    share = (Button)findViewById(R.id.bpass);
		Button  sms = (Button)findViewById(R.id.bsms);
		Button  email= (Button)findViewById(R.id.bemail);
		Button  edit= (Button)findViewById(R.id.edit);
		
		
		String alu="";
		for(List e:CurrentActlist.currentactlists)
		{
			alu = alu+e.getData() +" = "+e.getResult()+"\n";
			
		}
		final String Result=alu;
		
		final EditText Resulttext =(EditText)findViewById(R.id.Resulttext);
		
		Resulttext.setVisibility(View.GONE);
	edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Resulttext.setVisibility(View.VISIBLE);
				Resulttext.setText(Result);
			}
		});
	         share.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Share.share(Resulttext.getText().toString(), context);
					Resulttext.setVisibility(View.GONE);
				}
			});
	         sms.setOnClickListener(new OnClickListener() {
	  			
	  			@Override
	  			public void onClick(View v) {
	  				// TODO Auto-generated method stub
	  				Sms.sms(Resulttext.getText().toString(), context);
	  				Resulttext.setVisibility(View.GONE);
	  			}
	  		});
	          
	          email.setOnClickListener(new OnClickListener() {
	   			
	   			@Override
	   			public void onClick(View v) {
	   				// TODO Auto-generated method stub
	   				Email.email(context, "Result",Resulttext.getText().toString());
	   				Resulttext.setVisibility(View.GONE);
	   			
	   			}
	   		});
	       
	//	AdRequest ad = new AdRequest();

	//	    AdView adView = (AdView)findViewById(R.id.ad);
		//    adView.loadAd(ad);
		
		Button b = (Button)findViewById(R.id.Clearkd);
		b.setOnClickListener(this);
		
		Button c = (Button)findViewById(R.id.Savekd);
		c.setOnClickListener(this);
		
		
	}
	  private String GetTextFromEditText(int id)
	    {
	    	String s= ((EditText)findViewById(id)).getText().toString();
	    
	    	return s;
	    }
       
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	switch (v.getId()) {
	case R.id.Clearkd:
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				this);
 
			// set title
			alertDialogBuilder.setTitle("Delete Confirmation");
 
			// set dialog message
			alertDialogBuilder
				.setMessage("You want to delete past records")
				.setCancelable(false)
				.setPositiveButton("Proceed",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, close
						// current activity
						CurrentActlist.currentactlists.clear();
						EditText Resulttext =(EditText)findViewById(R.id.Resulttext);
						Resulttext.setText("");
					
					}
				  })
				.setNegativeButton("Back",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
					}
				});
 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
		
		
		
		break;
	case R.id.Savekd:
		Context context=this;
		Kdexcel kd = new Kdexcel(this);
		kd.getfile();
		
         break;
	default:
		break;
	}
		
	}
}
