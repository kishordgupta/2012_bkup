package com.example.swipeuiforupclose;



import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class WebFragment extends Fragment {
	//Constant
	private static final String TAG = "WebFragment";
	// Member variables
	private String mUrl;
	private boolean mIsLoadFinish = false;

	// View Member Variables
	private WebView mWebView;
	private WebViewClient mWebViewClient;
	private Button mBtnBack;
	private Button mBtnForward;
	private Button mBtnRefresh;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_web, null);

		// Init WebView
		initWebView(view);

		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		mWebView.loadUrl(mUrl);
		enableControllerButton();
	}

	// Property access
	public void setUrl(String url) {
		mUrl = url;
	}

	/*
	 * Internal functions This section contain private and protected level
	 * functions which function is only used inside this class or its subclass
	 */

	// Init webView, website use javascript, so enable javascript
	// is a must
	@SuppressLint("SetJavaScriptEnabled")
	private void initWebView(View view) {
		// Buttons setups
		mBtnBack = (Button) view.findViewById(R.id.btn_back);
		mBtnForward = (Button) view.findViewById(R.id.btn_forward);
		mBtnRefresh = (Button) view.findViewById(R.id.btn_refresh);
		// Setup for button controller
		mBtnRefresh.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mWebView.reload();
				enableControllerButton();
			}
		});
		mBtnBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mWebView.goBack();
				enableControllerButton();
			}
		});
		mBtnForward.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mWebView.goForward();
				enableControllerButton();
			}
		});
		
		initClient();
		mWebView = (WebView) view.findViewById(R.id.webView);
		WebSettings settings = mWebView.getSettings();
		settings.setJavaScriptEnabled(true);
		mWebView.setWebViewClient(mWebViewClient);
	}

	/*
	 * Internal functions
	 */
	private void enableControllerButton() {
		if (mIsLoadFinish) {
			mBtnRefresh.setEnabled(true);
			if (mWebView.canGoBack()) {
				mBtnBack.setEnabled(true);
			} else {
				mBtnBack.setEnabled(false);
			}
			if (mWebView.canGoForward()) {
				mBtnForward.setEnabled(true);
			} else {
				mBtnForward.setEnabled(false);
			}
		} else {
			mBtnBack.setEnabled(false);
			mBtnForward.setEnabled(false);
		}
	}

	private void initClient() {
		mWebViewClient = new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				mIsLoadFinish = true;
				enableControllerButton();
				Log.d(TAG, "Finish loading");
			}
		};
	}

}
