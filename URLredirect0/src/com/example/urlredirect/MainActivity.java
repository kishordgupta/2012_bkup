package com.example.urlredirect;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.GeolocationPermissions.Callback;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final WebView w = (WebView) findViewById(R.id.web);
		final Context c = this;
		final CharSequence[] items = { " Customer Profile' ",
				" Operations and Quality Control Profile' ", " Debug Profile ",
				" Cancel " };
		AlertDialog levelDialog;
		final Intent i = getIntent();
		/*
		 * Toast.makeText(this, i.getAction(), Toast.LENGTH_LONG).show();
		 */
		Toast.makeText(this, i.getDataString(), Toast.LENGTH_LONG).show();
		// Creating and Building the Dialog
		AlertDialog.Builder builder = new AlertDialog.Builder(c);
		
		builder.setTitle("View As");
		builder.setIcon(getResources().getDrawable(R.drawable.logo));
		builder.setSingleChoiceItems(items, -1,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {

						switch (item) {
						case 0:
							w.getSettings().setUserAgentString("");
							try {
								w.loadUrl(i.getDataString());
							} catch (Exception e) {
							}
							Toast.makeText(c,
									"wait site loading with no useragent",
									Toast.LENGTH_LONG).show();
							// Your code when first option seletced
							break;
						case 1:
							w.getSettings().setUserAgentString("");
							try {
								w.loadUrl(i.getDataString().replace("rmep.co",
										"qa.rmeps.com"));
							} catch (Exception e) {
							}
							// Your code when 2nd option seletced
							Toast.makeText(c, "wait site loading with QA",
									Toast.LENGTH_LONG).show();

							break;
						case 2:
							w.getSettings().setUserAgentString("RMEP-DEBUG");
							try {
								w.loadUrl(i.getDataString());
							} catch (Exception e) {
							}
							Toast.makeText(c,
									"wait site loading with useragent",
									Toast.LENGTH_LONG).show();
							// Your code when 3rd option seletced
							break;
						case 3:
							// Your code when 4th option seletced
							break;

						}
						dialog.dismiss();
					}
				});
		levelDialog = builder.create();
		levelDialog.show();

		w.loadUrl(i.getDataString());

		w.getSettings().setJavaScriptEnabled(true);
		w.getSettings().setDomStorageEnabled(true);

		// w.loadData(i.getDataString(), "text/html", null);
		// w.setWebViewClient(new WebViewClient());
		w.setWebChromeClient(new GeoClient());
		w.setWebViewClient(new WebViewClient() {

			private View mCustomView;

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * android.webkit.WebViewClient#shouldOverrideUrlLoading(android
			 * .webkit.WebView, java.lang.String)
			 */
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {

				view.loadUrl(url);

				return false;

			}

			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {

			}

			@Override
			public void onPageFinished(WebView view, String url) {
			}

		});

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
