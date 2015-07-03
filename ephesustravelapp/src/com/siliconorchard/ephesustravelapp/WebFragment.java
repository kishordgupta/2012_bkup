package com.siliconorchard.ephesustravelapp;

import com.siliconorchard.ephesustravelapp.R;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.FrameLayout;



public class WebFragment extends Fragment {
	private WebView webView;

	public static String url = "";
	EditText ed;
	
	
	// private FrameLayout mCustomViewContainer;
	
	FrameLayout.LayoutParams COVER_SCREEN_GRAVITY_CENTER = new FrameLayout.LayoutParams(
			ViewGroup.LayoutParams.WRAP_CONTENT,
			ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.webview, container, false);
        webView = (WebView) rootView.findViewById(R.id.webView1);
		webView.getSettings().setAppCacheEnabled(true);
		webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setRenderPriority(RenderPriority.HIGH);

		try {
			webView.getSettings().setAllowContentAccess(true);
			
			webView.getSettings().setAllowFileAccess(true);
		} catch (Exception e) {
		}
		webView.getSettings().setDomStorageEnabled(true);
		webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        
		webView.getSettings().setPluginState(PluginState.ON);
		// webView.setClickable(false);



		webView.setWebChromeClient(new GeoClient() );
		webView.setWebViewClient(new WebViewClient() {

			private View mCustomView;

			
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// Toast.makeText(c, "sf"+url, Toast.LENGTH_LONG).show();
				if (url.endsWith("pdf")) {

					/*view.loadUrl("http://envato.pixek.com/mobile/items/themeforest/plank/");
				      //   startActivity(intentUrl);
*/
				        }
				else{
					view.loadUrl(url);}
				
				return false;

			}

			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {

				 webView.loadUrl("file:///android_asset/index.html");
			}

			@Override
			public void onPageFinished(WebView view, String url) {
			}

		});

		webView.setPadding(0, 0, 0, 0);
		webView.setBackgroundColor(Color.BLACK);
		webView.loadUrl("http://www.freeitunestvshow.com/");
	
        return rootView;
    }

	/*
	 * @Override public void onConfigurationChanged(Configuration newConfig) {
	 * // TODO Auto-generated method stub webView.loadUrl(webView.getUrl());
	 * super.onConfigurationChanged(newConfig); }
	 */



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
