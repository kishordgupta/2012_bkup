package com.Fakeposter.fakeposter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Typeface;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.View;
import android.widget.ImageView;



	


public class drawView extends View {
		
	
	
	float pos_x=0,pos_y=0;
	int counter=0;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			
			pos_x=event.getX();
			pos_y=event.getY();
			
			invalidate();
			
			
			break;
		
		default:
			break;
		}
		
		
		
		
		
		
		
		return super.onTouchEvent(event);
	}

	Bitmap  imageBackground,imageForground,poster;
	String Actor1,Actor2,Movie1,Movie2,Drector;
	private ScaleGestureDetector mScaleDetector;
	Typeface moveName,Actorname,tf;
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		
		
		
		Paint paint=new Paint();
		
		paint.setAlpha(100);
		
		paint.setTypeface(moveName);
		paint.setTextSize(40f);
		canvas.drawText("   "+Movie1+"  "+Movie2+"", pos_x+10, pos_y+20, paint);
		
		paint.setTypeface(Actorname);
		paint.setTextSize(10f);
		canvas.drawText("Actor   "+Actor1+"&   "+Actor2, pos_x+10,pos_y+ 60, paint);
	
		
		paint.setTypeface(tf);
		paint.setTextSize(10f);
		
		
		canvas.drawText("Derector   "+Drector,pos_x+ 10,pos_y+ 80, paint);
		canvas.drawBitmap(imageBackground, 0, 0, paint);
		
		paint.setAlpha(70);
		
		canvas.drawBitmap(imageForground, 0, 0, paint);
		canvas.save();
		
		super.onDraw(canvas);
	}
	
	public drawView(Context context,Bitmap background,Bitmap foreground,String actor1,String actor2,String movie1,String movie2,String director) {
		super(context);
		this.imageBackground=background;
		this.imageForground=foreground;
		this.Actor1=actor1;
		this.Actor2=actor2;
		this.Movie1=movie1;
		this.Movie2=movie2;
		this.Drector=director;
		
		
		 tf = Typeface.createFromAsset(getContext().getAssets(), "ab.otf");
		 moveName=Typeface.createFromAsset(getContext().getAssets(), "move.ttf");
		 Actorname=Typeface.createFromAsset(getContext().getAssets(), "actor.ttf");
		
	}
		
	public drawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public drawView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

}
