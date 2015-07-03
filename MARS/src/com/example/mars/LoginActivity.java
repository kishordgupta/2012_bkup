package com.example.mars;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity implements OnClickListener, android.content.DialogInterface.OnClickListener {
	EditText txtEmail, txtPass;
	Button btnLogin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		txtEmail = (EditText)findViewById(R.id.txtEmail);
		txtPass = (EditText)findViewById(R.id.txtPass);
		btnLogin = (Button)findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(this);
	}
	
	void ShowProfile(){
		Intent i = new Intent(LoginActivity.this, ProfileActivity.class);
		startActivity(i);
	}
	
	void LoginFailed(){
		AlertDialog alert = new AlertDialog.Builder(this).create();
		alert.setMessage("Invalid Email Or Password");
		alert.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", this);
		alert.show();
	}
	

	public void onClick(View sender) {
		if(sender == btnLogin){
			new LoginAction().execute();
		}
	}
	
	class LoginAction extends AsyncTask<Void, Void, Boolean>{
		private final ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			this.dialog.setMessage("Signing in, please wait...");
			this.dialog.show();
		}
		
		@Override
		protected Boolean doInBackground(Void... params) {
			return ApiProxy.Login(txtEmail.getText().toString(), txtPass.getText().toString());
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			if(this.dialog.isShowing()){
				this.dialog.dismiss();
			}
			
			if(result == false){
				LoginFailed();
			}
			else{
				ShowProfile();
			}
			
			super.onPostExecute(result);
		}
	}

	public void onClick(DialogInterface dialog, int which) {
		dialog.dismiss();	
	}
	

}
