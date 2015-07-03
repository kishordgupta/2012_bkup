package com.lilait.kd.calculator2;


import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.lilait.kd.calculator2.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class Kdprototype extends Activity implements OnClickListener{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.match);
		inittext();
		AdRequest ad = new AdRequest();

		    AdView adView = (AdView)findViewById(R.id.ad);
		    adView.loadAd(ad);
		
		Button b = (Button)findViewById(R.id.customize);
		b.setOnClickListener(this);
		b.setVisibility(View.GONE);
		Button c = (Button)findViewById(R.id.back);
		c.setOnClickListener(this);
		
		
	}
	  private String GetTextFromEditText(int id)
	    {
	    	String s= ((EditText)findViewById(id)).getText().toString();
	    
	    	return s;
	    }
        private void inittext(){
        	
        	TrollConstants.Trollmessages = GetTextFromEditText(R.id.trollmessage);
        	try{
	    	TrollConstants.trollend = Integer.parseInt(GetTextFromEditText(R.id.stoptrolling));
	    	TrollConstants.trollstart= Integer.parseInt(GetTextFromEditText(R.id.startrolling));
        	}catch (Exception e) {
				// TODO: handle exception
			}
	    	TrollConstants.TrollState = ((CheckBox)findViewById(R.id.checkBox1)).isChecked();
        };
	    private void GrabText()
	    {
	    	TrollConstants.Trollmessages = GetTextFromEditText(R.id.trollmessage);
	    	try{
	    	TrollConstants.trollend = Integer.parseInt(GetTextFromEditText(R.id.stoptrolling));
	    	TrollConstants.trollstart= Integer.parseInt(GetTextFromEditText(R.id.startrolling));
	    	}catch (Exception e) {
				// TODO: handle exception
			}
	    	TrollConstants.TrollState = ((CheckBox)findViewById(R.id.checkBox1)).isChecked();
            	TrollConstants.Counter=0;
	    	if(TrollConstants.trollstart>TrollConstants.trollend)
	    	{TrollConstants.trollend=TrollConstants.trollstart+3;}
	    	if(TrollConstants.trollend<3)
	    	{TrollConstants.trollend=5;}
	    }
	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		GrabText();
	finish();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	switch (v.getId()) {
	case R.id.back:
		onBackPressed();
		break;
	case R.id.customize:
		Context context=this;
		LayoutInflater li = LayoutInflater.from(context);
		View promptsView = li.inflate(R.layout.result, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		final EditText userInput = (EditText) promptsView
				.findViewById(R.id.Resulttext);
alertDialogBuilder.setTitle("Write the predefined answer's seperated by ','");
		// set dialog message
		alertDialogBuilder
			.setCancelable(false)
			.setPositiveButton("OK",
			  new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog,int id) {
				// get user input and set it to result
				// edit text
			    	TrollConstants.results.clear();
			    	if(userInput.getText().toString().contains(","))
			    	{
				String[] result=userInput.getText().toString().split(",");
			
				for (String string : result) {
					TrollConstants.results.add(string);
				}
			    	}
			    	else
			    	{
			    		TrollConstants.results.add(userInput.getText().toString());
			    	}
				
			    }
			  })
			.setNegativeButton("Cancel",
			  new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog,int id) {
				dialog.cancel();
			    }
			  });

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
         break;
	default:
		break;
	}
		
	}
}
