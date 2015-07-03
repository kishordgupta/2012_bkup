package com.lilait.tamago.tap.golden.egg.game;

import xoxo.sound.SoundManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.Paint.Style;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class SampleView extends View implements OnTouchListener {
	public Context c = null;
	boolean flag = true;
	static int n = 0;
	int r;
	Typeface fontToSet =null;
	int CentreX =0;// (this.getWidth() / 2);
	int CentreY = 0;// (this.getHeight() / 2);
	int image_on=0;
	int image_off=0;
	String color="";
	 Paint foreground=null;
	 static int width = 320;
		static int height =480;
		static Double ratio =(double) (240/320);
		Bitmap bitmapegg=null;
	// CONSTRUCTOR
	public SampleView(Context context) {
		super(context);
		setFocusable(true);
		c = context;
		getScreenSizePixels();
		
		if(MainActivity.ratio==0){
		 bitmapegg = BitmapFactory.decodeResource(getResources(),
				R.drawable.off);
		 image_on=R.drawable.on;
		 image_off=R.drawable.off;
		 color="#F5A9A9";
		}
		if(MainActivity.ratio==1){
			 bitmapegg = BitmapFactory.decodeResource(getResources(),
					R.drawable.off);
			 image_on=R.drawable.boff;
			 image_off=R.drawable.bon;
			 color="#F8E6E0";}
		if(MainActivity.ratio==2){
			 bitmapegg = BitmapFactory.decodeResource(getResources(),
					R.drawable.off);
			 image_on=R.drawable.red0ff;
			 image_off=R.drawable.redon;
			 color="#F8E6E0"; 
		}
		 fontToSet = Typeface.createFromAsset(getContext().getAssets(),
		            "DK Crayon Crumble.ttf");
		 CentreX = (width / 2);
		 CentreY = (height / 2);
	     	foreground = new Paint(Paint.ANTI_ALIAS_FLAG);
		    foreground.setColor(Color.RED);
		    foreground.setStyle(Style.FILL);
		    foreground.setTypeface(fontToSet);
		    foreground.setTextSize(MainActivity.height * 0.1f);
		    foreground.setTextScaleX(MainActivity.width / MainActivity.height);
		    foreground.setTextAlign(Paint.Align.LEFT);
r=1;
	}
	
	public void getScreenSizePixels()
	{
	    Resources resources = getResources();
	    Configuration config = resources.getConfiguration();
	    DisplayMetrics dm = resources.getDisplayMetrics();
	    // Note, screenHeightDp isn't reliable
	    // (it seems to be too small by the height of the status bar),
	    // but we assume screenWidthDp is reliable.
	    // Note also, dm.widthPixels,dm.heightPixels aren't reliably pixels
	    // (they get confused when in screen compatibility mode, it seems),
	    // but we assume their ratio is correct.
		Display display = ((WindowManager)c.getSystemService(Context.WINDOW_SERVICE))
				.getDefaultDisplay();
				height=display.getHeight();
				width= display.getWidth();
				 r= height/width;
			Double	ratio =(double) (240/320);
				 ratio =r/ratio;
				r = ratio.intValue();
				if (r < 1)
					r = 1;
	/*    double screenWidthInPixels = (double)config.screenWidthDp * dm.density;
	    double screenHeightInPixels = screenWidthInPixels * dm.heightPixels / dm.widthPixels;
	    width = (int)(screenWidthInPixels + .5);
	    height = (int)(screenHeightInPixels + .5);*/
	 //   Toast.makeText(c,width + " "+height, Toast.LENGTH_LONG).show();
	}

	void drawclick(Canvas canvas)
	{
		 fontToSet = Typeface.createFromAsset(getContext().getAssets(),
	            "DK Crayon Crumble.ttf");
	   
		canvas.drawColor(Color.parseColor(color));
		
		canvas.drawText(""+m, 35,80, foreground);
	}
	
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		drawclick(canvas);
		Bitmap bitmap = bitmapegg;
		Rect source = null;
		if(math==1)
		{bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.friedegg);
			source = new Rect(20 * r, 80 * r, width - 20 * r,
					height * r - 80 * r);

			Rect bitmapRect = new Rect(0, 0, bitmap.getWidth(),
					bitmap.getHeight());
			canvas.drawBitmap(bitmap, bitmapRect, source, new Paint());
			n=0;
		}
		else{
		if (flag) {
			bitmap = BitmapFactory.decodeResource(getResources(),
					image_off);
				source = new Rect(20 * r, 80 * r, width - 20 * r,
						height * r - 80 * r);

				Rect bitmapRect = new Rect(0, 0, bitmap.getWidth(),
						bitmap.getHeight());
				canvas.drawBitmap(bitmap,  width/2-bitmap.getWidth()/2 , height/2-bitmap.getHeight()/2, new Paint());
				n=0;
		}
		else
		{
			bitmap = BitmapFactory.decodeResource(getResources(),
					image_on);
				source = new Rect(20 * r, 80 * r, width - 20 * r,
						height * r - 80 * r);

				Rect bitmapRect = new Rect(0, 0, bitmap.getWidth(),
						bitmap.getHeight());
				canvas.drawBitmap(bitmap,  width/2-bitmap.getWidth()/2,  height/2-bitmap.getHeight()/2, new Paint());
				n=0;
		}
		}
	}
int math=0;
int m=0;
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		SoundManager.playSound(0, 1);
		if(flag)
			flag=false;
		else
			flag=true;
		invalidate();
		return true;
	}
@Override
	public boolean onTouchEvent(MotionEvent e) {
		// TODO Auto-generated method stub
 // Toast.makeText(c,flag+"sdfsfsdfsdf", Toast.LENGTH_LONG).show();
/*	String TAG="";
	  // RIGHT SIDE SCREEN
    if(e.getX()> (width*0.7)){
        Log.d(TAG, "RIGHT SIDE");
        if(e.getY()> height*0.7){         
            Log.d(TAG, "right down on screen");
        }else if(e.getY()> (height*0.45)){ 
            Log.d(TAG, "right middle on screen   ");
        }
    }
    // LEFT SIDE SCREEN
    if(e.getX()< (width*0.3)){
        Log.d(TAG, "LEFT SIDE");
        if(e.getY()> height*0.7){ 
            Log.d(TAG, "Left middle on screen  ");
        }else if(e.getY()> (viewHeight*0.45)){
            Log.d(TAG, "Left down on screen ");
        }
    }
    return true;*/
 m++;
	if(flag){
		flag=false;}
	else{
		flag=true;
		 n++;
	}
	if(n>10000)
	{
		Share.share("", c);
	n=0;
	
		math=1;
	}
	else{
	invalidate();
	}
	SoundManager.playSound(0, 1);
		return super.onTouchEvent(e);
	}
}