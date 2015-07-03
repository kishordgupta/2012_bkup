package com.lilait.savingstophone.coupons.ecoupons;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity implements  GeolocationPermissions.Callback {
	private WebView webView;
	static String ur="http://savings2phone.com/mobsite/index.php";
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Context c=this;
	/*	AdRequest ad = new AdRequest();
		AdView adView = (AdView)findViewById(R.id.ad);
  	    adView.loadAd(ad);*/
	//	final ProgressDialog   progressDialog = ProgressDialog.show(c, "Loading...", "");
/*  		 
        new Thread(new Runnable(){
            public void run(){
                try {
                            Thread.sleep(3 * 1000);
                    progressDialog.dismiss();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/
		   webView =new WebView(this);// (WebView) findViewById(R.id.webView1);
		   setContentView(webView);
		    webView.getSettings().setJavaScriptEnabled(true);
		    webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		    webView.getSettings().setGeolocationEnabled(true);  //seems like if i set this, the webview should prompt when I call navigator.geolocation.getCurrentPosition
		  //  GeolocationPermissions geoPerm = new GeolocationPermissions(); //added in API Level 5 but no methods exposed until API level 7
		    GeoClient geo = new GeoClient();
		    webView.setWebChromeClient(geo);        
		    String origin = ""; //how to get origin in correct format?
		    geo.onGeolocationPermissionsShowPrompt(origin, this);  //obviously not how this is meant to be used but expected usage not documented
		 //   webView.loadUrl(geoWebsiteURL);     
		   webView.loadUrl(ur);
		//  webView.loadUrl("<iframe width=\"555\" height=\"915\" src=\"http://www.diffen.com/difference/Barcelona_vs_Real_Madrid?embed?embed\"></iframe>");
		
		  
		   webView.setWebViewClient(new WebViewClient() {
				
	            private View mCustomView;
	          
	       
			@Override
	        public boolean shouldOverrideUrlLoading(WebView view, String url) {
	        	
				ur=url;;
	        	
		       
		        {
	            view.loadUrl(url);
		        }
	            return false;
	        }
	        @Override
	        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
               
	        	//Toast.makeText(c, webView.getUrl() + description,Toast.LENGTH_SHORT).show();
	     
	    	//   webView.loadUrl(url);
           
             // Toast.makeText(c, "Sorry, You need net Connection to get full itemlist", Toast.LENGTH_LONG).show();
           }
	
	        @Override
	        public void onPageFinished(WebView view, String url) {
	        // TODO Auto-generated method stub
	     //   
	        
	        super.onPageFinished(view, url);
	        
	    //    webView.refreshDrawableState();
	       		        }
	        
	  
	    });
		 
	}
 @Override
public void onConfigurationChanged(Configuration newConfig) {
	// TODO Auto-generated method stub
	//ur=webView.getUrl();
	super.onConfigurationChanged(newConfig);
	//
}
 
	@Override
	public void onBackPressed()
	{
	    // Notify the VideoEnabledWebChromeClient, and handle it ourselves if it doesn't handle it
	     if (webView.canGoBack())
	        {
	            webView.goBack();
	        }
	        else
	        {
	        	finish();
	            // Close app (presumably)
	      //      super.onBackPressed();
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


}
