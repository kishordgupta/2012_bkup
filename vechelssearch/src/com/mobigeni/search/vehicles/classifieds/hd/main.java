package com.mobigeni.search.vehicles.classifieds.hd;

import com.mobigeni.search.vehicles.classifieds.hd.R;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class main extends BaseActivity {
	private VideoEnabledWebView webView;
	private VideoEnabledWebChromeClient webChromeClient;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);

	    // Set layout
	    setContentView(R.layout.activity_main);

	    // Save the web view
	    webView = (VideoEnabledWebView) findViewById(R.id.webView);

	   /* // Initialize the VideoEnabledWebChromeClient and set event handlers
	    View nonVideoLayout = findViewById(R.id.nonVideoLayout); // Your own view, read class comments
	    ViewGroup videoLayout = (ViewGroup) findViewById(R.id.videoLayout); // Your own view, read class comments
	    View loadingView = getLayoutInflater().inflate(R.layout.view_loading_video, null); // Your own view, read class comments
	    webChromeClient = new VideoEnabledWebChromeClient(nonVideoLayout, videoLayout, loadingView, webView) // See all available constructors...
	    {
	        // Subscribe to standard events, such as onProgressChanged()...
	    	
	        @Override
	        public void onProgressChanged(WebView view, int progress)
	        {
	            // Your code...
	        }
	    };
	    webChromeClient.setOnToggledFullscreen(new VideoEnabledWebChromeClient.ToggledFullscreenCallback()
	    {
	        @Override
	        public void toggledFullscreen(boolean fullscreen)
	        {
	            // Your code to handle the full-screen change, for example showing and hiding the title bar
	        }
	    });
	    webView.setWebChromeClient(webChromeClient);

	    // Navigate everywhere you want, this classes have only been tested on YouTube's mobile site
	    webView.loadUrl("http://m.youtube.com");*/
	}
	@Override
	public void onBackPressed()
	{
	    // Notify the VideoEnabledWebChromeClient, and handle it ourselves if it doesn't handle it
	    if (!webChromeClient.onBackPressed())
	    {
	        if (webView.canGoBack())
	        {
	            webView.goBack();
	        }
	        else
	        {
	            // Close app (presumably)
	            super.onBackPressed();
	        }
	    }
	}
}
