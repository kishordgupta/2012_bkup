package com.babu.dailystar.cartoon;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.JsResult;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.babu.dailystar.cartoon.R;
import com.ironsource.mobilcore.CallbackResponse;
import com.ironsource.mobilcore.MobileCore;
import com.ironsource.mobilcore.OnReadyListener;
import com.ironsource.mobilcore.CallbackResponse.TYPE;
import com.ironsource.mobilcore.MobileCore.AD_UNITS;
import com.ironsource.mobilcore.MobileCore.LOG_TYPE;

public class MainActivity extends Activity {
	private WebView webView;
	
	String url = "";
	EditText ed;
	 public  static	Bitmap finalload=null;
	 public  static Boolean Startload = false;
		 int pagenumber=1;
	private RelativeLayout mContentView;
	VideoEnabledWebChromeClient v;
	
	// private FrameLayout mCustomViewContainer;
	private FrameLayout mCustomViewContainer;
	private WebChromeClient.CustomViewCallback mCustomViewCallback;
	FrameLayout.LayoutParams COVER_SCREEN_GRAVITY_CENTER = new FrameLayout.LayoutParams(
			ViewGroup.LayoutParams.WRAP_CONTENT,
			ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
	 
	@Override @JavascriptInterface 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Context c = this;
	
          webView = new WebView(this){
        	@Override
        	public void postUrl(String url, byte[] postData) {
        		// TODO Auto-generated method stub
        		Log.d("kd", url + postData.toString());
        		super.postUrl(url, postData);
        	}  
          };
      	setContentView(webView);
	//	webView = (VideoEnabledWebView) findViewById(R.id.webView1);
		webView.getSettings().setAppCacheEnabled(true);
		webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		// webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setRenderPriority(RenderPriority.HIGH);
		try {
			webView.getSettings().setAllowContentAccess(true);
			 //webView.getSettings().setAllowFileAccessFromFileURLs(true);
			webView.getSettings().setAllowFileAccess(true);
		} catch (Exception e) {
		}
		webView.getSettings().setDomStorageEnabled(true);
		//webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
	//	webView.getSettings().setPluginsEnabled(true);
		webView.getSettings().setPluginState(PluginState.ON);
		// webView.setClickable(false);
		
		webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		webView.setWebChromeClient(new MyWebChromeClient());
		webView.addJavascriptInterface(new Object()
		{
			  public void doSomething(String theName, String theAddress)
			  {
				  Log.d("kda", theName+theAddress );
			    // Deal with a click on the OK button
			  }
			}, "Android");
		webView.setWebViewClient(new WebViewClient() {
      
			
         
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
			
	            return false;
				
			}
       
		
		});

		webView.setPadding(0, 0, 0, 0);
		webView.setBackgroundColor(Color.WHITE);
	//	http://videofeeds.lilait.com/game/
	//	webView.loadUrl("file:///android_asset/index.html");
		String S = "<!DOCTYPEhtmlPUBLIC\"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n<html>\n<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n<title>customer support</title>\n</head>\n<body>\n<form name=\"myForm\">\n<div>\nContact Subject\n<input type=\"text\" id=\"name\" value=\"subject\"><br />\n<textarea id=\"details\">\ndetails\n</textarea><br /><br />\n</div>\n<div><input type=\"submit\" value=\"submit\"onClick=\"callDoSomething()\" ></div>\n</form>\n</body>\n</html>";
		//webView.loadUrl("http://videofeeds.lilait.com/game/");
		
		String DS="<script type=\"text/javascript\"> function callDoSomething() { var theName = document.getElementById('name').value; var theAddress = document.getElementById('details').value; var result = Android.doSomething(theName, theAddress); }</script></body>";
		S = S.replace("</body>",DS);
	//	webView.loadDataWithBaseURL("file:///android_asset/index.html",S, "text/html", null,"file:///android_asset/index.html");
		webView.loadData(S, "text/html", null);
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
		webView.clearHistory();
		// Notify the VideoEnabledWebChromeClient, and handle it ourselves if it
		// doesn't handle it
		if (!v.onBackPressed()) {
			if (webView.canGoBack()) {
				webView.goBack();
			} else {
				// Close app (presumably)
				// super.onBackPressed();
				final Context c= this;
				MobileCore.showOfferWall((Activity) c, new CallbackResponse() {
					@Override
					public void onConfirmation(TYPE arg0) {
						((Activity) c).finish();
					}
					});
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
		if ((keyCode == KeyEvent.KEYCODE_BACK) && (webView.canGoBack() != true)) {
			finish();
			return true;
		}
		// If it wasn't the Back key or there's no web page history, bubble up
		// to the default
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
			
			return true;// 7f080004;

			/*
			 * case R.id.a:
			 * webView.loadUrl("http://www.picgeni.com/search.php");return
			 * true;//News:>
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
	 private class MyWebChromeClient extends WebChromeClient {
	      
		  //display alert message in Web View
		  @Override
		     public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
		         Log.d("kda", message);
		         String[] a = message.split("#");
		         Intent intent = new Intent(Intent.ACTION_SEND);
					intent.setType("text/plain");
					intent.putExtra(Intent.EXTRA_EMAIL,new String[] { "ephesus@turkeytravelapps.com" }); // "craigbennettii@techreviewsandhelp.com");
					intent.putExtra(Intent.EXTRA_SUBJECT, a[1] );
					intent.putExtra(Intent.EXTRA_TEXT, ""+ a[0]);
                    
					startActivity(Intent.createChooser(intent, "Send Email"));;
					view.reload();
					;
		/*         new AlertDialog.Builder(view.getContext())
		          .setMessage(message).setCancelable(true).show();
		         result.confirm();*/
		         return false;
		     }
		  
		 
		 }
	public class JavaScriptInterface {
	     Context mContext;
	 
	     // Instantiate the interface and set the context 
	     JavaScriptInterface(Context c) {
	         mContext = c;
	     }
	     public boolean doSomething(String name, String address) {
	    	 Log.d("kda", name+address );
	         return true;
	     }
	     //using Javascript to call the finish activity
	     public void closeMyActivity() {
	         finish();
	     }
	}
}
