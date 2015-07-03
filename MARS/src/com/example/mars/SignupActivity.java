package com.example.mars;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends Activity implements OnClickListener, android.content.DialogInterface.OnClickListener {
	
	EditText txtFirstName, txtLastName, txtEmail, txtPassword;
	Button btnSignup;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		
		txtFirstName = (EditText)findViewById(R.id.txtFirstName);
		txtLastName = (EditText)findViewById(R.id.txtLastName);
		txtEmail = (EditText)findViewById(R.id.txtEmail);
		txtPassword = (EditText)findViewById(R.id.txtPassword);
		
		btnSignup = (Button)findViewById(R.id.btnSignup);
		btnSignup.setOnClickListener(this);
	}

	void showMessage(String message){
		AlertDialog alert = new AlertDialog.Builder(this).create();
		alert.setMessage(message);
		alert.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", this);
		alert.show();
	}
	
	
	public void onClick(View v) {
		
		String fname = txtFirstName.getText().toString().trim();
		String lname = txtLastName.getText().toString().trim();
		String email = txtEmail.getText().toString().trim();
		String pass = txtPassword.getText().toString().trim();
		
		if(fname.length() < 1 || lname.length() < 1 || email.length() < 1 || pass.length() < 1){
			showMessage("You must enter all information");
		}
		else{
			new SignupAction().execute();
		}
	}
	
	void showLogin(){
		finish();
		Intent i = new Intent(this, LoginActivity.class);
		startActivity(i);
	}
	

	class SignupAction extends AsyncTask<Void, Void, String>{
		private final ProgressDialog dialog = new ProgressDialog(SignupActivity.this);
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog.setMessage("Signing up, please wait...");
			dialog.show();
		}
		
		@Override
		protected String doInBackground(Void... params) {
			return ApiProxy.Signup(txtFirstName.getText().toString().trim(), 
					txtLastName.getText().toString().trim(), 
					txtEmail.getText().toString().trim(), 
					txtPassword.getText().toString().trim());
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			dialog.dismiss();
			
			if("Success".equalsIgnoreCase(result)){
				showLogin();
			}
			else{
				showMessage(result);
			}
		}
	}
	
	
	public void onClick(DialogInterface dialog, int which) {
		dialog.dismiss();	
	}

}
