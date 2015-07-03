package com.lilait.tamago.xmass.xmasstree.cristmasstree.kidz.tap.game;



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
		
		/*if(MainActivity.ratio==0){
		 bitmapegg = BitmapFactory.decodeResource(getResources(),
				R.drawable.a1);}
		if(MainActivity.ratio==1){
			 bitmapegg = BitmapFactory.decodeResource(getResources(),
					R.drawable.a2);}
		if(MainActivity.ratio==2)*/{
			 bitmapegg = BitmapFactory.decodeResource(getResources(),
					R.drawable.a1);}
		 fontToSet = Typeface.createFromAsset(getContext().getAssets(),
		            "DK Crayon Crumble.ttf");
		 CentreX = (width / 2);
		 CentreY = (height / 2);
		foreground = new Paint(Paint.ANTI_ALIAS_FLAG);
		    foreground.setColor(Color.MAGENTA);
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
	
		   canvas.drawColor(Color.WHITE);
		canvas.drawText(""+m, 35,80, foreground);
	}
	
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		drawclick(canvas);
		Bitmap bitmap = bitmapegg;
		if(n<100)
		{
		bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.c0);
		}
		else if(n<500)
		{
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.c1);
		}
		else if(n<1000)
		{
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.c2);
		}
		else if(n<2000)
		{
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.c3);
		}
		else if(n<4000)
		{
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.c4);
		}
		else if(n<5000)
		{
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.c5);
		}
		else if(n<6000)
		{
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.c7);
		}
		else if(n<7000)
		{
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.c8);
		}
		else if(n<8000)
		{
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.c9);
		}
		else	if(n<9000)
		{
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.c10);
		}
		else if(n<10000)
		{
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.c11);
		}
		
		else if(n<11000)
		{
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.c12);
		}
		else if(n<12000)
		{
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.c13);
		}
		else if(n<13000)
		{
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.c14);
		}
		else if(n<14000)
		{
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.c15);
		}
		else	if(n<15000)
		{
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.c16);
		}
		else if(n<16000)
		{
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.c17);
		}
		else	if(n<17000)
		{
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.c18);
		}
		
		
		
		Rect bitmapRect = new Rect(0, 0, bitmap.getWidth(),
				bitmap.getHeight());
		Rect source = null;
		if(math==1)
		{bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.a1);
			source = new Rect(20 * r, 80 * r, width - 20 * r,
					height * r - 80 * r);

			   canvas.drawColor(Color.BLACK);
			canvas.drawBitmap(bitmap, bitmapRect, source, new Paint());
			n=0;
		}
		else{
		if (flag) {
			source = new Rect(20 * r, 80 * r, width - 20 * r,
					height * r - 80 * r);

		
			canvas.drawBitmap(bitmap, bitmapRect, source, new Paint());
			
			
		} else {
			source = new Rect(24 * r, 80 * r, width - 16 * r,
					height * r - 80 * r);

			canvas.drawBitmap(bitmap, bitmapRect, source, new Paint());
			
		}
		}
	}
int math=0;
static int m=0;
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
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
 // Toast.makeText(c,flag+"sdfsfsdfsdf", Toast.LENGTH_LONG).show();
 m++;
 n++;
	if(flag){
		flag=false;
		}
	else{
		flag=true;
	
	}
	
	if(n>18000)
	{
		Share.share("", c);
	n=0;
	
		math=1;
	}
	else{
	invalidate();
	}
	SoundManager.playSound(0, 1);
		return super.onTouchEvent(event);
	}
}