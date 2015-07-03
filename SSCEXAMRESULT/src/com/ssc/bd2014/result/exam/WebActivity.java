package com.ssc.bd2014.result.exam;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ironsource.mobilcore.CallbackResponse;
import com.ironsource.mobilcore.MobileCore;

public class WebActivity extends Activity {
	public static String result="";
	WebView webView=null;
	String url = "";
	EditText ed;
	public static Bitmap finalload = null;
	public static Boolean Startload = false;
	int pagenumber = 1;
	private RelativeLayout mContentView;
	

	// private FrameLayout mCustomViewContainer;
	private FrameLayout mCustomViewContainer;
	private WebChromeClient.CustomViewCallback mCustomViewCallback;
	FrameLayout.LayoutParams COVER_SCREEN_GRAVITY_CENTER = new FrameLayout.LayoutParams(
			ViewGroup.LayoutParams.WRAP_CONTENT,
			ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Context c = this;
//		setContentView(R.layout.activity_main);
		// MobileCore.init(this,"8YKF89U9YHXZUIMHFEQ0UDC1T9TOY",
		// LOG_TYPE.DEBUG);
		// MobileCore.getSlider().setContentViewWithSlider(this,R.layout.activity_main);
		if (!GetNetworkStatus.isNetworkAvailable(this)) {

			Toast.makeText(this, "Need working internet", Toast.LENGTH_LONG)
					.show();
			finish();
		}
		/*
		 * MobileCore.setStickeezReadyListener(new OnReadyListener() {
		 * 
		 * @Override public void onReady(AD_UNITS adUnit) { if (adUnit ==
		 * AD_UNITS.STICKEEZ) { MobileCore.showStickee((Activity) c); } } });
		 */
		
		webView = new WebView(this);// findViewById(R.id.webView1);
		webView.getSettings().setAppCacheEnabled(true);
		webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
	//	webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setRenderPriority(RenderPriority.HIGH);
		webView.setDownloadListener(new DownloadListener() {
			
			@Override
			public void onDownloadStart(String url, String userAgent,
					String contentDisposition, String mimetype, long contentLength) {
				// TODO Auto-generated method stub
				Uri uri = Uri.parse(url);
	            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
	            startActivity(intent);
			}
		});
		try {
			webView.getSettings().setAllowContentAccess(true);
			// webView.getSettings().setAllowFileAccessFromFileURLs(true);
			webView.getSettings().setAllowFileAccess(true);
		} catch (Exception e) {
		}
		webView.getSettings().setDomStorageEnabled(true);
		webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
	
		webView.getSettings().setPluginState(PluginState.ON);
		// webView.setClickable(false);

						// class
																							// comments
	
		webView.setWebViewClient(new WebViewClient() {

			private View mCustomView;

			/* (non-Javadoc)
			 * @see android.webkit.WebViewClient#shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String)
			 */
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// Toast.makeText(c, "sf"+url, Toast.LENGTH_LONG).show();
				if (url.endsWith("pdf")) {

					view.loadUrl("http://docs.google.com/gview?embedded=true&url=" + url);
				      //   startActivity(intentUrl);

				        }
				else{
					view.loadUrl(url);}
				
				return false;

			}

			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {

				// webView.loadUrl("file:///android_asset/index.html");
			}

			@Override
			public void onPageFinished(WebView view, String url) {
			}

		});

		webView.setPadding(0, 0, 0, 0);
		webView.setBackgroundColor(Color.BLACK);
		webView.loadData(result, "text/html; charset=UTF-8",null);
	//	webView.loadUrl("http://lilait.com/squid/Austindo/buildConstruction.html");//http://mobile2.gameassists.co.uk/MobileWebGames/game/3_13_0?lobbyURL=http%3A%2F%2Fmobile2.gameassists.co.uk%2FMobileWebLobby%2F%3F&moduleID=10008&clientID=40301&gameName=5ReelDrive&gameTitle=5+Reel+Drive&LanguageCode=en&clientTypeID=40&casinoID=2258&lobbyName=Betway&loginType=FullUPE&bankingURL=http%3A%2F%2Fmobile2.gameassists.co.uk%2FMobileWebLobby%2F%3F&xmanEndPoints=https%3A%2F%2Fmobile2.gameassists.co.uk%2FXMan%2Fx.x&routerEndPoints=&disablePoweredBy=false&currencyFormat=&isPracticePlay=true");
		setContentView(webView);
	}

	DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case DialogInterface.BUTTON_POSITIVE:
				// Yes button clicked
				// webView.clearView();
				dialog.dismiss();
				finish();
				break;

			case DialogInterface.BUTTON_NEGATIVE:
				// No button clicked
				dialog.dismiss();
				break;
			}
		}
	};

	/*
	 * @Override public void onConfigurationChanged(Configuration newConfig) {
	 * // TODO Auto-generated method stub webView.loadUrl(webView.getUrl());
	 * super.onConfigurationChanged(newConfig); }
	 */

	

	@Override
	public void onBackPressed() {
		final Context c = this;
		MobileCore.showOfferWall((Activity) c, new CallbackResponse() {
			@Override
			public void onConfirmation(TYPE arg0) {
				((Activity) c).finish();
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// Check if the key event was the Back button and if there's history
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
			webView.goBack();

			return true;
		}
		if ((keyCode == KeyEvent.KEYCODE_BACK) && (webView.canGoBack() != true)) {
			finish();
			return true;
		}
		// If it wasn't the Back key or there's no web page history, bubble up
		// to the default
		// system behavior (probably exit the activity)
		return super.onKeyDown(keyCode, event);
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
