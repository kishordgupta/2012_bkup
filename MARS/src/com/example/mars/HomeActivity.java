package com.example.mars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends Activity implements OnClickListener {
	
	Button btnMenu;
	Button btnLogin;
	Button btnSignup;
	Button btnForgotPass;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		btnMenu = (Button)findViewById(R.id.btnMenu);
		btnMenu.setOnClickListener(this);
		
		btnLogin = (Button)findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(this);
		
		btnSignup = (Button)findViewById(R.id.btnSignup);
		btnSignup.setOnClickListener(this);
		
		btnForgotPass = (Button)findViewById(R.id.btnForgotPass);
		btnForgotPass.setOnClickListener(this);
	}


	public void onClick(View sender) {
		if(sender == btnMenu){
			Intent i = new Intent(this, MainActivity.class);
			startActivity(i);
		}
		else if(sender == btnLogin){
			Intent i = new Intent(this, LoginActivity.class);
			startActivity(i);
		}
		else if(sender == btnSignup){
			Intent i = new Intent(this, SignupActivity.class);
			startActivity(i);
		}
		else if(sender == btnForgotPass){
			Intent i = new Intent(this, ForgotPassActivity.class);
			startActivity(i);
		}
	}
	

}
