package com.ekushevashaandolon.motherlanguageday.myandroid;


import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.kd.phonecall.GMailSender;
import com.kd.phonecall.R;
import com.lilakhelait.kishor.helper.GetNetworkStatus;
import com.lilakhelait.kishor.model.CallNumber;
import com.lilakhelait.kishor.resource.Wish;
import com.lilakhelait.kishor.utility.StackParser;
import com.tmm.android.chuck.db.DBHelper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.SQLException;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Datainput extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.input);
		setTitle("Lägg till nummer");
		setTitleColor(Color.DKGRAY);
		GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[] {Color.WHITE,Color.DKGRAY});
		View title = getWindow().findViewById(android.R.id.title);
		gd.setCornerRadius(0f);
		View titleBar = (View) title.getParent();
		titleBar.setBackgroundDrawable(gd);
		
		/*setTitle("Add a phone");
		setTitleColor(Color.DKGRAY);
		View TitleBar=(View)getWindow().findViewById(android.R.id.title).getParent();
		TitleBar.setBackgroundColor(R.drawable.aaa);*/
		final Context con=this;
		//((EditText)findViewById(R.id.name)).setHint(" Input Name");
		//((EditText)findViewById(R.id.number)).setHint(" Input Number");
		//((EditText)findViewById(R.id.name)).setHintTextColor(Color.parseColor("#EDEDED"));
		//((EditText)findViewById(R.id.number)).setHintTextColor(Color.parseColor("#EDEDED"));
		((EditText)findViewById(R.id.name)).setTextColor(Color.RED);
		((EditText)findViewById(R.id.number)).setTextColor(Color.RED);
		Button b =(Button)findViewById(R.id.add);
		b.setTextColor(Color.DKGRAY);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				String Text = ((EditText)findViewById(R.id.name)).getText().toString();
				String number = ((EditText)findViewById(R.id.number)).getText().toString();
				if(number.length()>6)
				{
					Wish w = new Wish();
					w.setTitle(Text);
					w.setWishtext(number);
				CallNumber.values.add(w);
				DBHelper myDbHelper = new DBHelper(con);
				try {
					myDbHelper.openDataBase();
				}catch(SQLException sqle){
					throw sqle;
				}
			 myDbHelper.insert(w);
				myDbHelper.close();
			if(GetNetworkStatus.isNetworkAvailable(con)){
				ProgressDialog	  pd = ProgressDialog.show(con,"please wait ","connecting to server", true, false);
				 Toast.makeText(con, "Contact added", Toast.LENGTH_LONG).show();
				 GMailSender sender = new GMailSender("support@communify.se", "support4088");        
		            try {
						sender.sendMail("New number",   
								Text + "number" + number,         
						        "support@communify.se",   
						        "support@communify.se");
					} catch (AddressException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Toast.makeText(con, "Ntwork error contact not submitted", Toast.LENGTH_LONG).show();
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Toast.makeText(con, "Ntwork error contact not submitted", Toast.LENGTH_LONG).show();
					} 
			pd.dismiss();			            
		            Toast.makeText(con, "contact submitted", Toast.LENGTH_LONG).show();
			}
			else
				Toast.makeText(con, "Ntwork error contact not submitted", Toast.LENGTH_LONG).show();
				 finish();
				}
				else
				{
					Toast.makeText(con, "Not a phone number", Toast.LENGTH_LONG).show();
				}
			}
		});
	}
}
