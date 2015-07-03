package com.kd.trick.troll.friend.phone;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;

import android.app.Activity;
import android.content.Intent;
import android.drm.DrmManagerClient.OnErrorListener;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class Imageactivity extends Activity implements OnTouchListener, ErrorListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
	}
	/*  @Override
	    protected void onNewIntent(Intent intent) {
	        super.onNewIntent(intent);
	        if (Intent.ACTION_MAIN.equals(intent.getAction())) {
	            Log.i("MyLauncher", "onNewIntent: HOME Key");

	        }
	    }
*/
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
	
	}
	@Override
	protected void onPause() {
		startActivityForResult(new Intent(getApplicationContext(), Imageactivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP), 1);
		super.onPause();
		
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void error(TransformerException exception)
			throws TransformerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fatalError(TransformerException exception)
			throws TransformerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warning(TransformerException exception)
			throws TransformerException {
		// TODO Auto-generated method stub
		
	}
}
