package com.lilait.map.navigation.location.withoutgps;


import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.lilait.map.navigation.location.withoutgps.R;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.GeolocationPermissions.Callback;
import android.widget.Button;

public class MainActivity extends Activity implements  GeolocationPermissions.Callback {
	private WebView webView;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		AdRequest ad = new AdRequest();
		AdView adView = (AdView)findViewById(R.id.ad);
  	    adView.loadAd(ad);
		   webView = (WebView) findViewById(R.id.webView1);
		  
		    webView.getSettings().setJavaScriptEnabled(true);
		    webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		    webView.getSettings().setGeolocationEnabled(true);  //seems like if i set this, the webview should prompt when I call navigator.geolocation.getCurrentPosition
		  //  GeolocationPermissions geoPerm = new GeolocationPermissions(); //added in API Level 5 but no methods exposed until API level 7
		    GeoClient geo = new GeoClient();
		    webView.setWebChromeClient(geo);        
		    String origin = ""; //how to get origin in correct format?
		    geo.onGeolocationPermissionsShowPrompt(origin, this);  //obviously not how this is meant to be used but expected usage not documented
		 //   webView.loadUrl(geoWebsiteURL);     
		   webView.loadUrl("file:///android_asset/a.htm");
		//  webView.loadUrl("<iframe width=\"555\" height=\"915\" src=\"http://www.diffen.com/difference/Barcelona_vs_Real_Madrid?embed?embed\"></iframe>");
		
		   Button button = (Button) findViewById(R.id.finishBtn);
		    button.setOnClickListener(new View.OnClickListener() {
		        public void onClick(View v) {
		            webView.loadUrl( "javascript:window.location.reload( true )" );             
		        }
		    });
		 
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
