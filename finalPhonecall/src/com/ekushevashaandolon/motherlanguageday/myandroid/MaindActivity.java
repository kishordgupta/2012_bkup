package com.ekushevashaandolon.motherlanguageday.myandroid;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.kd.phonecall.R;


import android.os.Bundle;
import android.os.Environment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.GeolocationPermissions.Callback;
import android.widget.Button;
import android.widget.Toast;

public class MaindActivity extends Activity {
	private WebView webView;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final Context c=this;
		   webView = (WebView) findViewById(R.id.webView1);
		  
		    webView.getSettings().setJavaScriptEnabled(true);
		    webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		//     GeolocationPermissions geoPerm = new GeolocationPermissions(); //added in API Level 5 but no methods exposed until API level 7
		    webView.getSettings().setAppCacheEnabled(true);
			 webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
			   webView.getSettings().setJavaScriptEnabled(true);
			   webView.getSettings().setLoadWithOverviewMode(true);
			 
			   webView.setWebViewClient(new WebViewClient() {
			        @Override
			        public boolean shouldOverrideUrlLoading(WebView view, String url) {

				        if(url.contains("close"))
				        {
				        	//Toast.makeText(c, webView.getUrl(),Toast.LENGTH_SHORT).show();
				         	view.goBack();
				         
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
		   
		    String origin = ""; //how to get origin in correct format?
		 //obviously not how this is meant to be used but expected usage not documented
		 //   webView.loadUrl(geoWebsiteURL);     
		   webView.loadUrl("file:///android_asset/info.html");
		//  webView.loadUrl("<iframe width=\"555\" height=\"915\" src=\"http://www.diffen.com/difference/Barcelona_vs_Real_Madrid?embed?embed\"></iframe>");
		/*
		   Button button = (Button) findViewById(R.id.finishBtn);
		    button.setOnClickListener(new View.OnClickListener() {
		        public void onClick(View v) {finish();}              
		        });*/
		 
		 
	}
 
	


}
