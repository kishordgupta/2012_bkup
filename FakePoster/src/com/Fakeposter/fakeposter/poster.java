package com.Fakeposter.fakeposter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class poster extends Activity implements OnTouchListener {

	Bitmap poster;
	drawView draw;
	Editor store;
	LinearLayout frame1,frame2;
	TextView Actor,director;
	ImageView imge;
	SharedPreferences pref ;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		pref = this.getApplicationContext().getSharedPreferences("any_prefname", MODE_WORLD_READABLE);
	
		Bitmap backGround=BitmapFactory.decodeFile(pref.getString("Photo", "orin"), null);
		Bitmap Foreground=BitmapFactory.decodeFile(pref.getString("Poster", "orin"), null);
		
		
		Display  display= ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		int width = display.getWidth(); 
		int height = display.getHeight(); 
		
		android.util.Log.d("Text ",""+pref.getString("Poster", "orin")+"  width      "+width+"  hiegt  "+height+" ");
		
		
		backGround=Bitmap.createScaledBitmap(backGround, width, height, true);
		Foreground=Bitmap.createScaledBitmap(Foreground, width, height, true);
		
		
		draw=new drawView(this, backGround,Foreground,pref.getString("Actor1", "orin"),pref.getString("Actor2", "orin"),pref.getString("Movie1", "orin"),pref.getString("Movie2", "orin"),pref.getString("Director", "orin"));
		
		android.util.Log.d("Text "," x    Y"+draw.counter+" ");
	    if(draw.counter%2==1){
	    	draw.setDrawingCacheEnabled(true);
			draw.buildDrawingCache();
		    poster=draw.getDrawingCache();
		    
		    
		    Intent i =new Intent(poster.this,showPoster.class);
			i.putExtra("poster", poster);
			startActivity(i);
	    }
		
		setContentView(draw);
		
		
		
	
		
	}


	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		
		
		
		
		
		return true;
	}

	

}
