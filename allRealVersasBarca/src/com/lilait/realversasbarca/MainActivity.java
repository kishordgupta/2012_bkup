package com.lilait.realversasbarca;

import com.lilakhelait.kishor.resource.Imageurl;
import com.peakcoders.backgroundTasks.StackParser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;

public class MainActivity extends Activity {
	private WebView webView;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		   webView = (WebView) findViewById(R.id.webView1);
		   WebSettings webViewSettings = webView.getSettings();
		   webViewSettings.setJavaScriptCanOpenWindowsAutomatically(true);
		   webViewSettings.setJavaScriptEnabled(true);
		   webViewSettings.setPluginsEnabled(true);
		   webViewSettings.setBuiltInZoomControls(true);
		   webViewSettings.setPluginState(PluginState.ON);
		   String customHtml =Imageurl.values; ///*"<html><body>"+*/"<iframe width=\"555\" height=\"915\" src=\""+Imageurl.url+"?embed\"></iframe>";
		   webView.loadData(customHtml, "text/html", "UTF-8");
		 //  webView.loadUrl("<iframe width=\"555\" height=\"915\" src=\""+Imageurl.url+"?embed\"></iframe>");
	 
		 
	}
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO Auto-generated method stub
	super.onActivityResult(requestCode, resultCode, data);
	
	//if(requestCode==123)
	{
		Log.d("ddf",Imageurl.values );
		//   String customHtml = "<html><body><h1>Hello, WebView</h1></body></html>";
		  
	}
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
