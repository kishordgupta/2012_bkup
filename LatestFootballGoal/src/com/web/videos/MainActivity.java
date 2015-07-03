package com.web.videos;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lilait.live.football.goal.uefa.fifa.championsleague.video.latest.R;

public class MainActivity extends Activity  {
	private VideoEnabledWebView  webView;
	 int pagenumber=1;
	  EditText ed;
	  GestureDetector mGestureDetector;
	 // private LinearLayout mContentView;
	  private RelativeLayout mContentView;
	  public static String id="";
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
		String data="";
		try {
        android.content.res.Resources res = c.getResources();
        java.io.InputStream in_s = res.openRawResource(R.raw.feed);

        byte[] b = new byte[in_s.available()];
        in_s.read(b);
     data= ""+new String(b);
       data=data.replace("xid", id);
     
    } catch (Exception e) {
        // e.printStackTrace();
      //  txtHelp.setText("Error: can't show help.");
       
    }
		 setContentView(R.layout.activity_main);
		// mContentView=(RelativeLayout)findViewById(R.id.activity_main);
		  //mCustomViewContainer = (FrameLayout) findViewById(R.id.fullscreen_custom_content);
		
		  webView =(VideoEnabledWebView) findViewById(R.id.webView1);
		 webView.getSettings().setAppCacheEnabled(true);
		 webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		   webView.getSettings().setJavaScriptEnabled(true);
		   webView.getSettings().setLoadWithOverviewMode(true);
		   webView.getSettings().setPluginsEnabled(true);
		   webView.getSettings().setAllowContentAccess(true);
		   webView.getSettings().setPluginState(PluginState.ON);
		   View nonVideoLayout = findViewById(R.id.activity_main); // Your own view, read class comments
		    ViewGroup videoLayout = (ViewGroup) findViewById(R.id.fullscreen_custom_content); // Your own view, read class comments
		    View loadingView = getLayoutInflater().inflate(R.layout.activity_main, null); 
		  v = new VideoEnabledWebChromeClient(nonVideoLayout, videoLayout, loadingView, webView,c) // See all available constructors...
		    {
		        // Subscribe to standard events, such as onProgressChanged()...
		    	@Override
		    	public void onRequestFocus(WebView view) {
		    		// TODO Auto-generated method stub
		    		super.onRequestFocus(view);
		    	}
		        @Override
		        public void onProgressChanged(WebView view, int progress)
		        {
		            // Your code..
		       /* 	try{
		                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
		                startActivity(intent);                 
		                }catch (ActivityNotFoundException ex){
		                    Intent intent=new Intent(Intent.ACTION_VIEW, 
		                    Uri.parse("http://www.youtube.com/watch?v="+id));
		                    startActivity(intent);
		                }*/
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
	        	
	        	
	        	if(!GetNetworkStatus.isNetworkAvailable(c))
	        	{
	        		Toast.makeText(c, "Need working internet to use this app", Toast.LENGTH_LONG);
	        	finish();
	        	}
		        
		        else
		        {
	            view.loadUrl(url);
		        }
	            return false;
	        }
	        @Override
	        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                
	        	/*Toast.makeText(c, webView.getUrl() + description,Toast.LENGTH_SHORT).show();
	     
	    	   webView.loadUrl("http://gdata.youtube.com/feeds/api/videos?v=2&q=Football+goal&key=AI39si5szZ9ybeMUGMYzzXsZ8dv5bcJpH_2AW2Q2rr_ung6BKC6FHQa9kPlitCM9erFAm7jobamHHw5-kIiC593R7U7xwrQycA&orderby=published");
            */
	        	finish();
	        	
	        		StackParser.EP1_ID=id;
	                Intent intent = new Intent(c,StackParser.class);
	                c.startActivity(intent);                 
	              
              // Toast.makeText(c, "Sorry, You need net Connection to get full itemlist", Toast.LENGTH_LONG).show();
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
		    webView.setBackgroundColor(Color.BLACK);
		//webView.loadUrl("http://www.youtube.com/embed/dlFWcF2aPBg?feature=oembed");
		  //  String s ="http://marsapparel.co.jp/app";
	//webView.loadUrl("file:///android_asset/index.html");
			 //webView.loadUrl("file:///android_asset/Spun   Just another Caroline Moore site.htm");
		// webView.loadDataWithBaseURL(null,data,"text/html; charset=UTF-8",Encoding.UTF_8,null);
		//	Toast.makeText(c,a.getHeight()+"_"+a.getWidth(), 5000).show();
	//webView.loadUrl("http://cdn.livestream.com/embed/aguascalientes?layout=4&height=360&width=640&autoplay=true");
		//Spanned dataa=Html.fromHtml(data);
		
		    webView.loadData(data,"text/html; charset=UTF-8",null);
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

	
	

}
