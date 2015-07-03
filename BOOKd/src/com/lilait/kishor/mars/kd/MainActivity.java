package com.lilait.kishor.mars.kd;


import com.lilait.kishor.mars.kd.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;

import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity  {
	private WebView webView;
	 int pagenumber=1;
	  EditText ed;
	  GestureDetector mGestureDetector;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Context c=this;
		 setContentView(R.layout.activity_main);
		 SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
		 pagenumber = sharedPreferences.getInt("pagenumber", 1);
		  webView = (WebView)findViewById(R.id.webView1);
		 webView.getSettings().setAppCacheEnabled(true);
		 webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		   webView.getSettings().setJavaScriptEnabled(true);
		   webView.getSettings().setLoadWithOverviewMode(true);
		 
		   webView.setWebViewClient(new WebViewClient() {
		        @Override
		        public boolean shouldOverrideUrlLoading(WebView view, String url) {

			        if(url.contains("share"))
			        {
			        	//Toast.makeText(c, webView.getUrl(),Toast.LENGTH_SHORT).show();
			         	view.goBack();
			         Share.share(webView.getUrl(), c) ;
			         url=webView.getUrl();
			      
			        }
			        if(url.contains("exit"))
			        {
			        	view.goBack();
			        	finish();
			        	//Toast.makeText(c, webView.getUrl(),Toast.LENGTH_SHORT).show();
			        AlertDialog.Builder builder = new AlertDialog.Builder(c);
			        	builder.setMessage("Are you sure to exit?").setPositiveButton("Yes", dialogClickListener)
			        	    .setNegativeButton("No", dialogClickListener).show();
			      
			        }
			        else
			        {
		            view.loadUrl(url);
			        }
		            return false;
		        }
		        @Override
		        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
	                
		        	Toast.makeText(c, webView.getUrl() + description,Toast.LENGTH_SHORT).show();
		        	webView.loadUrl("file:///android_asset/index.html");
	                
	            
                   Toast.makeText(c, "Sorry, You need net Connection to get full itemlist", Toast.LENGTH_LONG).show();
	            }
		        
		        @Override
		        public void onPageFinished(WebView view, String url) {
		        // TODO Auto-generated method stub
		        super.onPageFinished(view, url);
		        }
		        
		  
		    });
		   webView.getSettings().setBuiltInZoomControls(true);
		    webView.getSettings().setSupportZoom(true);     
		    webView.getSettings().setLoadWithOverviewMode(true);
		     webView.getSettings().setUseWideViewPort(true);
		    webView.setPadding(0, 0, 0, 0);
		    webView.setBackgroundColor(Color.WHITE);
		
		  //  String s ="http://marsapparel.co.jp/app";
			 webView.loadUrl("file:///android_asset/index.html");

		
	}
	

	
	DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
	    @Override
	    public void onClick(DialogInterface dialog, int which) {
	        switch (which){
	        case DialogInterface.BUTTON_POSITIVE:
	            //Yes button clicked
	        //	webView.clearView();
	        	dialog.dismiss();
	        	finish();
	            break;

	        case DialogInterface.BUTTON_NEGATIVE:
	            //No button clicked
	        	dialog.dismiss();
	            break;
	        }
	    }
	};
	
	/*@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		webView.loadUrl(webView.getUrl());
		super.onConfigurationChanged(newConfig);
	}*/
	
	
	@Override

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
           
            return true;
        }
        if((keyCode == KeyEvent.KEYCODE_BACK) && (webView.canGoBack()!=true)) 
        {
        	finish();
        	 return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
	/*	
		if(webView.canGoBack())
		{	webView.goBack();
		webView.loadUrl(webView.getUrl());}
		else
			finish();
		//*/
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.exit:
				finish();
				return true;
			case R.id.product:
				webView.loadUrl("file:///android_asset/index.html");
				return true;
			case R.id.mymars:
				webView.loadUrl("http://marsapparel.co.jp/app/mymar.php");
				return true;
			case R.id.support:
				webView.loadUrl("http://marsapparel.co.jp/app/support.php");
				return true;
			case R.id.cart:
				webView.loadUrl("http://marsapparel.co.jp/app/cart.php");
				return true;
			case R.id.wishist:
				webView.loadUrl("http://marsapparel.co.jp/app/wishlist.php");
				return true;
			case R.id.sync:
				webView.loadUrl("http://marsapparel.co.jp/app/index.php");
				return true;
			default:
				return false;
		}
	}
	
	

}
