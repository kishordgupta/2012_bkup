package com.lilait.tamago.tap.golden.egg.game;

import xoxo.sound.SoundManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.lilait.tamago.tap.golden.egg.game.R;

import android.view.WindowManager;

public class MainActivity extends Activity implements OnTouchListener{
	
	static int width = 320;
	static int height =480;
	static int ratio =0;//(double) (240/320);
	SampleView s =null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//DisplayMetrics display = getWindowManager().DisplayMetrics();
		//Point size = new Point();
		//display.getSize(size);
	// Toast.makeText(this,width + " "+height, Toast.LENGTH_LONG).show();
		//setContentView(R.layout.activity_main);
		SoundManager.initSounds(this);
		AdView admobView = new AdView(this, AdSize.BANNER, "a152925a5e90465");
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
		    RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		admobView.setLayoutParams(lp);

		RelativeLayout layout = new RelativeLayout(this);
		layout.addView(new SampleView(this));
		layout.addView(admobView);
		admobView.loadAd(new AdRequest());

		setContentView(layout);
		//height=layout.getHeight(); //display.getHeight();
		//width= layout.getWidth();
		/*ampleView s = (SampleView) findViewById(R.id.gameScreen);
		s=new SampleView(this);
		 getScreenSizePixels();*/
		
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		finish();
		super.onBackPressed();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		v.invalidate();
		return true;
	}

	
}