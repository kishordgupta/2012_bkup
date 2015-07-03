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
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class SampleView extends View {
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
		OnFlingGestureListener gdt=null;
	// CONSTRUCTOR
	
	public SampleView(Context context) {
		super(context);
		setFocusable(true);
		c = context;
		getScreenSizePixels();
		m=1;
		
this.setOnTouchListener(new OnTouchListener() {
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		
		Vibrator va = (Vibrator) c.getSystemService(Context.VIBRATOR_SERVICE);
		if(m==2)
			m=1;
			else
				m=2;
		// Vibrate for 300 milliseconds
		va.vibrate(50);
		invalidate();

		SoundManager.playSound(0, 1);
		return gdt.onTouch(v, event);
	}
});



	 gdt = new OnFlingGestureListener() {
		
		@Override
		public void onTopToBottom() {
			// TODO Auto-generated method stub
	
		}
		
		@Override
		public void onRightToLeft() {
			// TODO Auto-generated method stub
		
		}
		
		@Override
		public void onLeftToRight() {
			// TODO Auto-generated method stub
		
		}
		
		@Override
		public void onBottomToTop() {
			// TODO Auto-generated method stub
		
		}
	};	
		
		/*if(MainActivity.ratio==0){
		 bitmapegg = BitmapFactory.decodeResource(getResources(),
				R.drawable.a1);}
		if(MainActivity.ratio==1){
			 bitmapegg = BitmapFactory.decodeResource(getResources(),
					R.drawable.a2);}
		if(MainActivity.ratio==2)*/{
			 bitmapegg = BitmapFactory.decodeResource(getResources(),
					R.drawable.t2);}
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
	{n++;
	if(n%10==0)
	{

		Vibrator va = (Vibrator) c.getSystemService(Context.VIBRATOR_SERVICE);
		
		// Vibrate for 300 milliseconds
		va.vibrate(500);
	}
		 fontToSet = Typeface.createFromAsset(getContext().getAssets(),
	            "DK Crayon Crumble.ttf");
	
		  // canvas.drawColor(Color.RED);
		canvas.drawText(""+n, 35,80, foreground);
	}
	
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		
		Bitmap bitmap = bitmapegg;
		if(m==1)
		bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.t2);
		if(m==2)
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.t3);
		
		
		Rect bitmapRect = new Rect(0, 0, bitmap.getWidth(),
				bitmap.getHeight());
		Rect source = new Rect(0, 0, width,
				height );
	
			  
			canvas.drawBitmap(bitmap, bitmapRect, source, new Paint());
			drawclick(canvas);
			
		
	}
int math=0;
static int m=0;

}

