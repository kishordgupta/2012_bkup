package com.example.mars;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends Activity implements OnClickListener {
	TextView txtEmail, txtName;
	Button btnInbox, btnWishList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		txtEmail = (TextView)findViewById(R.id.txtEmail);
		txtName = (TextView)findViewById(R.id.txtName);
		btnInbox = (Button)findViewById(R.id.btnInbox);
		btnInbox.setOnClickListener(this);
		btnWishList = (Button)findViewById(R.id.btnWishList);
		btnWishList.setOnClickListener(this);
		
		try {
			txtEmail.setText(String.format("Email: %s", ApiProxy.user.getString("email")));
			txtName.setText(String.format("Name: %s %s", 
					ApiProxy.user.getString("fname"),
					ApiProxy.user.getString("lname")));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}

	public void onClick(View sender) {
		if(sender == btnInbox){
			Intent i = new Intent(this, InboxActivity.class);
			startActivity(i);
		}
		else if(sender == btnWishList){
			Intent i = new Intent(this, WishListActivity.class);
			startActivity(i);
		}
	}
	

}
