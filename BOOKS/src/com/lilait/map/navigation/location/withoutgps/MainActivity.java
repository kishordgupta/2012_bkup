package com.lilait.map.navigation.location.withoutgps;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.View;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebSettings.ZoomDensity;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener,OnGestureListener {
	private WebView webView;
	 int pagenumber=1;
	  EditText ed;
	  GestureDetector mGestureDetector;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 setContentView(R.layout.activity_main);
		 SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
		 pagenumber = sharedPreferences.getInt("pagenumber", 1);
		  webView = (WebView)findViewById(R.id.webView1);
		   webView.setInitialScale(1);
		   webView.getSettings().setJavaScriptEnabled(true);
		   webView.getSettings().setLoadWithOverviewMode(true);
		   getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		   webView.getSettings().setBuiltInZoomControls(true);
		    webView.getSettings().setSupportZoom(true);     
		    webView.getSettings().setLoadWithOverviewMode(true);
		     webView.getSettings().setUseWideViewPort(true);
		    webView.setPadding(0, 0, 0, 0);
		    webView.setBackgroundColor(Color.WHITE);
		
		 //   webView.setGestureDetector(new GestureDetector(new CustomeGestureDetector()));
		    String s ="file:///android_asset/page-"+pagenumber+".html";
			 webView.loadUrl(s);
	mGestureDetector = new GestureDetector(this);
		//  webView.loadUrl("<iframe width=\"555\" height=\"915\" src=\"http://www.diffen.com/difference/Barcelona_vs_Real_Madrid?embed?embed\"></iframe>");
		   Button button = (Button) findViewById(R.id.back);
		    button.setOnClickListener(this);
		    button = (Button) findViewById(R.id.next);
		    button.setOnClickListener(this);
		    button = (Button) findViewById(R.id.fast);
		    button.setOnClickListener(this);
		    button = (Button) findViewById(R.id.end);
		    button.setOnClickListener(this);
		 ed=(EditText)findViewById(R.id.pagenumber);
		    ed.setText(pagenumber+"");
		    ed.setEnabled(false);
		    EditText	 ed1=(EditText)findViewById(R.id.pagenumbertogo);
			    ed1.setText(pagenumber+"");
			    ed1.addTextChangedListener(new TextWatcher() {
					
					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						// TODO Auto-generated method stub
					
					}
					
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count,
							int after) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						if(s.toString().length()>=1){
						int i=Integer.parseInt(s.toString().replace(" ", ""));
						returnfile(i);
						ed.setText(pagenumber+"");}
					}
				});
	}
	
	@Override
	public void onBackPressed() {
		SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
	    SharedPreferences.Editor editor = sharedPreferences.edit();
	    editor.putInt("pagenumber", pagenumber);
	    editor.commit();
		// TODO Auto-generated method stub
		super.onBackPressed();
	}
	@Override
	public boolean dispatchTouchEvent(MotionEvent e){
	    super.dispatchTouchEvent(e);
	    return mGestureDetector.onTouchEvent(e);
	}
	void returnfile(int page)
	{
		if(page<1)
			page=1;
		if(page>143)
			page=143;
			
		pagenumber=page;

		String s ="file:///android_asset/page-"+pagenumber+".html";
		 webView.loadUrl(s);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.next:
			returnfile(pagenumber+1);
			ed.setText(pagenumber+"");
			break;
		case R.id.back:
			returnfile(pagenumber-1);
			ed.setText(pagenumber+"");
			break;
		case R.id.fast:
			returnfile(0);
			ed.setText(pagenumber+"");
			break;
		case R.id.end:
			returnfile(143);
			ed.setText(pagenumber+"");
			break;
		default:
			break;
		}
	}
	private int getScale(){
	    Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay(); 
	    int width = display.getWidth();
	    Double val = new Double(width)/new Double(webView.getRight()-webView.getLeft());
	    val = val / 10d;
	    return val.intValue();
	}
	
	
	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	 private static final int SWIPE_MIN_DISTANCE = 120;
	    private static final int SWIPE_MAX_OFF_PATH = 250;
	    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		  try {
              if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                  return false;
              // right to left swipe
              if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                 returnfile(pagenumber+1);
                 ed.setText(pagenumber+"");
              }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
            	  returnfile(pagenumber-1);
                  ed.setText(pagenumber+"");
              }
          } catch (Exception e) {
              // nothing
          }
          return false;
	
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	
	

}
