package com.iqrasys.callblocker.activity;

import com.kd.phonecall.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.webkit.WebView;
import android.widget.Toast;

public class InfoViewActivity extends Activity {
	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_view);

		final Context c = this;
		webView = (WebView) findViewById(R.id.webView1);

		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		webView.getSettings().setAppCacheEnabled(true);
		webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setLoadWithOverviewMode(true);

		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {

				if (url.contains("close")) {
					// Toast.makeText(c,
					// webView.getUrl(),Toast.LENGTH_SHORT).show();
					view.goBack();

					finish();

				} else {
					view.loadUrl(url);
				}
				return false;
			}

			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {

				Toast.makeText(c, webView.getUrl() + description,
						Toast.LENGTH_SHORT).show();
				webView.loadUrl("file:///android_asset/index.html");

				Toast.makeText(c,
						"Sorry, You need net Connection to get full itemlist",
						Toast.LENGTH_LONG).show();
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
			}

		});

		String origin = ""; // how to get origin in correct format?
		webView.loadUrl("file:///android_asset/info.html");
	}

}
