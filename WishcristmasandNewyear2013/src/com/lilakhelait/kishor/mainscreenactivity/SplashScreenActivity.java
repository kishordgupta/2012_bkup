package com.lilakhelait.kishor.mainscreenactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;



public class SplashScreenActivity extends Activity implements OnClickListener{
   private Button buttonStart;
   private Button buttonExit;
   private Button duat;
   private Button ssst;
   public Context con;

   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
        con=this;
          buttonStart = (Button)findViewById(R.id.Ask_for_duas);
        buttonExit = (Button)findViewById(R.id.btn_exit);
	//	Toast.makeText(this, "Wellcome in Islamic Duas",Toast.LENGTH_LONG).show();
        buttonStart.setOnClickListener(this);
        buttonExit.setOnClickListener(this);
       
        buttonStart.setTextSize(25);
       
        buttonExit.setTextSize(25);
        
        duat = (Button)findViewById(R.id.Topix);
        ssst = (Button)findViewById(R.id.btn_start);
	//	Toast.makeText(this, "Wellcome in Islamic Duas",Toast.LENGTH_LONG).show();
        duat.setOnClickListener(this);
        ssst.setOnClickListener(this);
      
        duat.setTextSize(25);
    
        ssst.setTextSize(25);
    }
   
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.Ask_for_duas:
			startActivity(new Intent(getApplicationContext(), Mainwindowactivity.class));
			break;
        case R.id.btn_exit:
			finish();
			break;
        case R.id.btn_start:
        	startActivity(new Intent(getApplicationContext(), Mainwindowactivity.class));
			break;
        case R.id.Topix:
        	startActivity(new Intent(getApplicationContext(), Mainwindowactivity.class));
			break;
		default:
			break;
		}
	}


	
	
}