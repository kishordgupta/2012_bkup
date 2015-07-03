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

public class ForgotPassActivity extends Activity implements OnClickListener, android.content.DialogInterface.OnClickListener {
	EditText txtEmail;
	Button btnForgotPass;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgotpass);
		
		txtEmail = (EditText)findViewById(R.id.txtEmail);
		btnForgotPass = (Button)findViewById(R.id.btnForgotPass);
		btnForgotPass.setOnClickListener(this);
	}

	void showMessage(String message){
		AlertDialog alert = new AlertDialog.Builder(this).create();
		alert.setMessage(message);
		alert.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", this);
		alert.show();
	}
	
	
	public void onClick(View sender) {
		if(sender == btnForgotPass){
			String email = txtEmail.getText().toString().trim();
			if(email.length() < 1){
				showMessage("Enter your email to recover password");
			}
			else{
				new ForgotPasswordAction().execute();
			}
		}
	}

	class ForgotPasswordAction extends AsyncTask<Void, Void, String>{
		private final ProgressDialog dialog = new ProgressDialog(ForgotPassActivity.this);
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog.setMessage("Submitting request, please wait...");
			dialog.show();
		}
		
		@Override
		protected String doInBackground(Void... params) {
			return ApiProxy.ForgotPass(txtEmail.getText().toString().trim());
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			
			if(dialog.isShowing()){
				dialog.dismiss();
			}
			
			showMessage(result);
		}
		
	}
	
	
	public void onClick(DialogInterface dialog, int whichButton) {
		dialog.dismiss();
	}
	
	

}
