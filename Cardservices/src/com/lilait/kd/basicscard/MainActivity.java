package com.lilait.kd.basicscard;

import com.lilait.kd.basicscard.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.YuvImage;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends Activity  {
	private VideoEnabledWebView  webView;
	 int pagenumber=1;
	 String url="";
	  EditText ed;
	  GestureDetector mGestureDetector;
	 // private LinearLayout mContentView;
	  private RelativeLayout mContentView;
	  VideoEnabledWebChromeClient v ;
	  //private FrameLayout mCustomViewContainer;
	  private FrameLayout mCustomViewContainer;
	  private WebChromeClient.CustomViewCallback mCustomViewCallback;
	  FrameLayout.LayoutParams COVER_SCREEN_GRAVITY_CENTER = new FrameLayout.LayoutParams(
	          ViewGroup.LayoutParams.WRAP_CONTENT,
	          ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Context c=this;
		 setContentView(R.layout.activity_main);
	final ProgressDialog   progressDialog = ProgressDialog.show(c, "Loading...", "");
	  		 
			   
		//	 setContentView(R.layout.activity_main);
			   new Thread(new Runnable(){
		            public void run(){
		                try {
		                            Thread.sleep(8* 1000);
		                    progressDialog.dismiss();
		                } catch (InterruptedException e) {
		                    e.printStackTrace();
		                }
		            }
		        }).start();
		// mContentView=(RelativeLayout)findViewById(R.id.activity_main);
		  //mCustomViewContainer = (FrameLayout) findViewById(R.id.fullscreen_custom_content);
	       url=getResources().getString(R.string.url);
		  webView =(VideoEnabledWebView) findViewById(R.id.webView1);
		 webView.getSettings().setAppCacheEnabled(true);
		 webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
/*webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);*/
		   webView.getSettings().setJavaScriptEnabled(true);
		 //  webView.getSettings().setLoadWithOverviewMode(true);
		   webView.getSettings().setAllowContentAccess(true);
		   //webView.getSettings().setAllowFileAccessFromFileURLs(true);
		   webView.getSettings().setAllowFileAccess(true);
		   webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		   webView.getSettings().setPluginsEnabled(true);
		   webView.getSettings().setPluginState(PluginState.ON);
		   View nonVideoLayout = findViewById(R.id.activity_main); // Your own view, read class comments
		    ViewGroup videoLayout = (ViewGroup) findViewById(R.id.fullscreen_custom_content); // Your own view, read class comments
		    View loadingView = getLayoutInflater().inflate(R.layout.activity_main, null); 
		  v = new VideoEnabledWebChromeClient(nonVideoLayout, videoLayout, loadingView, webView,c) // See all available constructors...
		    {
		        // Subscribe to standard events, such as onProgressChanged()...
		    	
		        @Override
		        public void onProgressChanged(WebView view, int progress)
		        {
		            // Your code...
		        }
		    };
		    v.setOnToggledFullscreen(new VideoEnabledWebChromeClient.ToggledFullscreenCallback()
		    {
		        @Override
		        public void toggledFullscreen(boolean fullscreen)
		        {
		        	/*if(fullscreen)
		             onBackPressed();*/
		        	// Your code to handle the full-screen change, for example showing and hiding the title bar
		        }
		    });
		    webView.setWebChromeClient(v);
		   webView.setWebViewClient(new WebViewClient() {
			
		            private View mCustomView;
		          
		        @Override
		        public boolean shouldOverrideUrlLoading(WebView view, String url) {
		        	final ProgressDialog   progressDialog = ProgressDialog.show(c, "Loading...", "");
			  		
		        	new Thread(new Runnable(){
			            public void run(){
			                try {
			                            Thread.sleep(2 * 1000);
			                    progressDialog.dismiss();
			                } catch (InterruptedException e) {
			                    e.printStackTrace();
			                }
			            }
			        }).start();
		        	 if(url.contains("dt"))
				      {
				    	  view.loadUrl(url);
				    	/*  String s=url.replace("https://www.share.com/?resulr=", "").replace("%", " ");
				    	  //Toast.makeText(c, s, Toast.LENGTH_LONG).show();
				    	  Share.share(s, c);*/
				    	 
				      }
		        	if(!GetNetworkStatus.isNetworkAvailable(c))
		        	{
		        		Toast.makeText(c, "Need working internet to use this app", Toast.LENGTH_LONG);
		        	}
			     
			        else
			        {
		            view.loadUrl(url);
			        }
		            return true;
		        }
		        @Override
		        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
	                
		        //	Toast.makeText(c, webView.getUrl() + description+errorCode,Toast.LENGTH_SHORT).show();
		        
		        	//webView.loadUrl("http://calculator.lilait.com/calculus/derivative_1.html");
	            
                  // Toast.makeText(c, "Sorry, You need net Connection to get full itemlist", Toast.LENGTH_LONG).show();
	            }
		        
		        @Override
		        public void onPageFinished(WebView view, String url) {
		        // TODO Auto-generated method stub
		        	//webView.loadUrl("javascript:HTMLOUT.processHTML(document.documentElement.outerHTML);");//       super.onPageFinished(view, url);
		        }
		        
		  
		    });
		//  webView.getSettings().setBuiltInZoomControls(true);
		    //webView.getSettings().setSupportZoom(true);     
		 //  webView.getSettings().setLoadWithOverviewMode(true);
		//   webView.getSettings().setUseWideViewPort(true);
		    webView.setPadding(0, 0, 0, 0);
		    webView.setBackgroundColor(Color.MAGENTA);
		    //webView.loadUrl("http://www.youtube.com");
		  //  String s ="http://marsapparel.co.jp/app";
		//  webView.loadUrl("http://ci.plusmo.com");
	    //  	webView.loadUrl("http://www.calculator.lilait.com/calculus/derivative.html");
		    webView.loadUrl("file:///android_asset/index.html");
		//	webView.requestFocus(View.FOCUS_DOWN);
			/*webView.setOnTouchListener(new View.OnTouchListener() {
			    @Override
			    public boolean onTouch(View v, MotionEvent event) {
			        switch (event.getAction()) {
			            case MotionEvent.ACTION_DOWN:
			            case MotionEvent.ACTION_UP:
			                if (!v.hasFocus()) {
			                    v.requestFocus();
			                }
			                break;
			        }
			        return false;
			    }
			});*/
		
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
	public void onBackPressed()
	{
		webView.clearHistory();
	    // Notify the VideoEnabledWebChromeClient, and handle it ourselves if it doesn't handle it
	    if (!v.onBackPressed())
	    {
	        if (webView.canGoBack())
	        {
	            webView.goBack();
	        }
	        else
	        {
	            // Close app (presumably)
	      //      super.onBackPressed();
	        	finish();
	        }
	    }
	}
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
		

			       case R.id.home:
			    	   webView.loadUrl("file:///android_asset/index.html");
			    	   return true;//7f080004;
			    	
			     /*      case R.id.a:
			             webView.loadUrl("http://www.picgeni.com/search.php");return true;//News:>
			            
			        */  
			              
			            
				
			
			default:
				return false;
		}
	}
	
	

}
