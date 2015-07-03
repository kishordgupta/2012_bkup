package com.lilait.troll.football.facebookpage.gag.soccar.app.worldcup.manu;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;





import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Picture;
import android.graphics.YuvImage;
import android.graphics.Bitmap.CompressFormat;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
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
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
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
import android.webkit.GeolocationPermissions;

import com.flurry.android.FlurryAdType;
import com.flurry.android.FlurryAds;
import com.flurry.android.FlurryAdSize;
import com.flurry.android.FlurryAgent;
import com.flurry.android.FlurryAdListener;
import com.ironsource.mobilcore.MobileCore;
import com.ironsource.mobilcore.MobileCore.LOG_TYPE;
import com.lilait.troll.football.facebookpage.gag.soccar.app.worldcup.manu.R;
import com.tapjoy.TapjoyConnect;
import com.tapjoy.TapjoyConnectFlag;
import com.tapjoy.TapjoyConnectNotifier;
import com.tapjoy.TapjoyDisplayAdNotifier;

import android.webkit.GeolocationPermissions.Callback;

public class MainActivity extends Activity  implements FlurryAdListener{
	private VideoEnabledWebView  webView;
	
 public  static	Bitmap finalload=null;
 public  static Boolean Startload = false;
	 int pagenumber=1;
	 String url="";
	  EditText ed;
	  FrameLayout mBanner;
      private String adSpace="MediatedBannerTop";
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
		 MobileCore.init(this,"8YKF89U9YHXZUIMHFEQ0UDC1T9TOY", LOG_TYPE.DEBUG);
		 MobileCore.getSlider().setContentViewWithSlider(this, R.layout.activity_main);
		  mBanner = (FrameLayout)findViewById(R.id.banner);
	final ProgressDialog   progressDialog = ProgressDialog.show(c, "Loading...", "");
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
	  
	/*RevMob revmob = RevMob.start(this,"5293987a84bebb9a16000077");
    RevMobAdsListener listener = new RevMobAdsListener() {
        public void onRevMobAdReceived() {  }
        public void onRevMobAdNotReceived(String message) {} // you can hide the More Games Button here
        public void onRevMobAdDisplayed() {}
        public void onRevMobAdDismiss() {}
        public void onRevMobAdClicked() {}
    };
    link = revmob.createAdLink(this, listener);
	/*/		   
	// setContentView(R.layout.activity_main);
			 
		// mContentView=(RelativeLayout)findViewById(R.id.activity_main);
		  //mCustomViewContainer = (FrameLayout) findViewById(R.id.fullscreen_custom_content);
	    //   url=getResources().getString(R.string.url);
		  webView =(VideoEnabledWebView) findViewById(R.id.webView1);
		 webView.getSettings().setAppCacheEnabled(true);
		 webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
//webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		   webView.getSettings().setJavaScriptEnabled(true);
		  webView.getSettings().setRenderPriority(RenderPriority.HIGH);
		  try{ 
		  webView.getSettings().setAllowContentAccess(true);
		   //webView.getSettings().setAllowFileAccessFromFileURLs(true);
		   webView.getSettings().setAllowFileAccess(true);
		  }
		  catch(Exception e)
		  {}
		  webView.getSettings().setDomStorageEnabled(true);
		   webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		   webView.getSettings().setPluginsEnabled(true);
		   webView.getSettings().setPluginState(PluginState.ON);
		 //  webView.setClickable(false);
		/*   webView.setLongClickable(false);
		   webView.setFocusable(false);
		  // webView.setHapticFeedbackEnabled(false);
		   webView.setHovered(false);
		   webView.setFocusableInTouchMode(false);*/
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
		  //  webView.getSettings().setGeolocationEnabled(true);  //seems like if i set this, the webview should prompt when I call navigator.geolocation.getCurrentPosition
			  //  GeolocationPermissions geoPerm = new GeolocationPermissions(); //added in API Level 5 but no methods exposed until API level 7
			   // GeoClient geo = new GeoClient();
			  webView.setWebChromeClient(v);     
		    
		   webView.setWebViewClient(new WebViewClient() {
			
		            private View mCustomView;
		          
		        @Override
		        public boolean shouldOverrideUrlLoading(WebView view, String url) {
		        	  if(url.contains("exit.com"))
				      {
		        		  finish();
				      }
		        	  else if(url.contains("nativeplay.com"))
				      {
		        		  String text =url.replace("http://www.nativeplay.com/?v_id=", "");
		        		  view.goBack();
		        		  try{
		                      Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + text));
		                      startActivity(intent);                 
		                      }catch (ActivityNotFoundException ex){
		                          Intent intent=new Intent(Intent.ACTION_VIEW, 
		                          Uri.parse("http://www.youtube.com/watch?v="+text));
		                          startActivity(intent);
		                      }
				      
				      }
		        	  else if(url.contains("setaswallpaper.com"))
				      {
		        	
		        		 // http://www.nativeplay.com/?v_id=iwAYR986h8k
		        	    
				    	    	String text =url.replace("http://www.setaswallpaper.com/?url==", "");
						    	
						  	  Startload=true;
						    		 DownloadImageByUrl downloadImageByUrl = new DownloadImageByUrl();
						    		 downloadImageByUrl.downloadImage(text,2,c);
					    			 Bitmap b =null;
					    		 long time=0;
					    	
						    	
						  
						      
						 		//   Wallpaper.wall(c, b);
						 		    
					    		 Toast.makeText(c, "Preparing for wallpaper ", 10000).show();	
						 		//Share.share(output, c,url);
				    view.goBack();
				      }
		        	  else  if(url.contains("download.com"))
			      {
			    	
	        	    	
			    	//  http://www.download.com/?url=http://sphotos-g.ak.fbcdn.net/hphotos-ak-frc3/t1/1486595_10152138183407164_1981820731_n.jpg
					    	String text =url.replace("http://www.download.com/?url=", "");
					    		// Picture picture = view.capturePicture();
					    		//	text =text.replace("http://www.share.com?winner=", "");
					    	Startload=true;
				    		 DownloadImageByUrl downloadImageByUrl = new DownloadImageByUrl();
				    			 downloadImageByUrl.downloadImage(text,1,c);
				    			 Bitmap b =null;
				    		
					     
					 			Toast.makeText(c, "Downloading in External sdcard ", 10000).show();
			    	view.goBack();
			      }
			      else if(url.contains("share.com"))
			      {
			    	
      	    
			      	String text =url.replace("http://www.share.com/?url=", "");
				    	
				    	Startload=true;
			    		 DownloadImageByUrl downloadImageByUrl = new DownloadImageByUrl();
			    		 downloadImageByUrl.downloadImage(text,3,c);
		    			 Bitmap b =null;
		    		 long time=0;
		    		 
				     
		    		 Toast.makeText(c, "Preparing for share ", 10000).show();	
				 		  
				 		    
		    	view.goBack();
		    	
			    	 /* Intent intent;

					    try {
					        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.lilait.math.calculus.Calculator.Differentiation.Integral.Integration.Calculation.Derivative"));
					        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					        c.startActivity(intent);
					    } catch (Exception e) {
					     
					    }*/
			    	 
			      }
			        else
			        {
		            view.loadUrl(url);
			        }
		            return false;
		        }
		        @Override
		        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
	                
		        //	Toast.makeText(c, webView.getUrl() + description+errorCode,Toast.LENGTH_SHORT).show();
		        	 webView.loadUrl("file:///android_asset/index.html");
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
			Hashtable<String, String> flags = new Hashtable<String, String>();
			flags.put(TapjoyConnectFlag.ENABLE_LOGGING, "true");
		    String tapjoyAppID = "77d45da5-770f-4472-a230-e99fea1d9898";
			String tapjoySecretKey = "18cjYJrU69jdNE0nkYYg";
			// NOTE: This is the only step required if you're an advertiser.
			TapjoyConnect.requestTapjoyConnect(getApplicationContext(), tapjoyAppID, tapjoySecretKey, flags, new TapjoyConnectNotifier()
			{
				@Override
				public void connectSuccess() {
				
				}

				@Override
				public void connectFail() {
					
				}
			});
			
		
			adLinearLayout = (LinearLayout)findViewById(R.id.AdLinearLayout);
			adLinearLayout.setVisibility(View.GONE);
		
	}
	
	private static View scaleDisplayAd(View adView, int targetWidth)
	{
		int adWidth = adView.getLayoutParams().width;
		int adHeight = adView.getLayoutParams().height;

		// Scale if the ad view is too big for the parent view.
		if (adWidth > targetWidth)
		{
			int scale;
			int width = targetWidth;
			Double val = Double.valueOf(width) / Double.valueOf(adWidth);
			val = val * 100d;
			scale = val.intValue();

			((android.webkit.WebView) (adView)).getSettings().setSupportZoom(true);
			((android.webkit.WebView) (adView)).setPadding(0, 0, 0, 0);
			((android.webkit.WebView) (adView)).setVerticalScrollBarEnabled(false);
			((android.webkit.WebView) (adView)).setHorizontalScrollBarEnabled(false);
			((android.webkit.WebView) (adView)).setInitialScale(scale);

			// Resize banner to desired width and keep aspect ratio.
			LayoutParams layout = new LayoutParams(targetWidth, (targetWidth*adHeight)/adWidth);
			adView.setLayoutParams(layout);
		}

		return adView;
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
	// For the display ad.
		View adView;
		RelativeLayout relativeLayout;
		LinearLayout adLinearLayout;
	/*@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		webView.loadUrl(webView.getUrl());
		super.onConfigurationChanged(newConfig);
	}*/
	 @Override
     public void onStart() {
         super.onStart();
         FlurryAgent.onStartSession(this,"T4MFW5897VX87P38KB8S");
         // get callbacks for ad events
         FlurryAds.setAdListener(this);
        // FlurryAds.enableTestAds(true);
         // fetch and prepare ad for this ad space. won’t render one yet
        // FlurryAds.fetchAd(this, adSpace, mBanner, FlurryAdSize.BANNER_BOTTOM);
         FlurryAds.fetchAd(this, "att", mBanner,FlurryAdSize.BANNER_TOP);
         TapjoyConnect.getTapjoyConnectInstance().enableDisplayAdAutoRefresh(true);
			TapjoyConnect.getTapjoyConnectInstance().getDisplayAd(this, new TapjoyDisplayAdNotifier()
			{
				@Override
				public void getDisplayAdResponseFailed(String error)
				{
					adLinearLayout.setVisibility(View.GONE);
				}
				
				@Override
				public void getDisplayAdResponse(View view)
				{
					// Using screen width, but substitute for the any width.
					int desired_width = adLinearLayout.getMeasuredWidth();
					adLinearLayout.setVisibility(View.VISIBLE);
					// Scale the display ad to fit incase the width is smaller than the display ad width.
					adView = scaleDisplayAd(view, desired_width);
					
					adLinearLayout.addView(view);
				//	updateTextInUI("getDisplayAd success");
				//	reenableButtonInUI(button);
				}
			});
     }

     @Override 
     public void spaceDidReceiveAd(String adSpace) {
         // called when the ad has been prepared, ad can be displayed:
      //   FlurryAds.displayAd(this, "MediatedBannerTop", mBanner);
         if(FlurryAds.isAdReady(adSpace)) {
        //	 Log.d("sdeeeeeeeeeeeeef", "treeeeeeeeeeeee"); 
        	 FlurryAds.displayAd(this, "att", mBanner);
        	} else {
        		FlurryAds.fetchAd(this, "att", mBanner,FlurryAdSize.BANNER_TOP);
        		// Log.d("sdf", "sdfsdf");  
        	}
        
         // instead of displaying the ad here, you can check 
         // FlurryAds.isAdReady(adSpace)
         // and display the ad when ready to do so in your Activity.
     }
     
     @Override  
     public void onStop() {
         super.onStop();
         FlurryAds.removeAd(this, adSpace, mBanner);
         FlurryAgent.onEndSession(this);
     }
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
	

	public void invoke(String origin, boolean allow, boolean remember) {

	}

	final class GeoClient extends WebChromeClient {

	@Override
	public void onGeolocationPermissionsShowPrompt(String origin,
	Callback callback) {
	// TODO Auto-generated method stub
	super.onGeolocationPermissionsShowPrompt(origin, callback);
	callback.invoke(origin, true, false);
	}

	}

	@Override
	public void onAdClicked(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdClosed(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdOpened(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onApplicationExit(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRenderFailed(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRendered(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onVideoCompleted(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean shouldDisplayAd(String arg0, FlurryAdType arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void spaceDidFailToReceiveAd(String arg0) {
		// TODO Auto-generated method stub
		
	}


}
