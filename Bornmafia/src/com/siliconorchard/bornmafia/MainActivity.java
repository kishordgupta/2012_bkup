package com.siliconorchard.bornmafia;

import com.siliconorchard.bornmafia.R;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
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
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	private WebView webView;

	String url = "";
	EditText ed;
	public static Bitmap finalload = null;
	public static Boolean Startload = false;
	int pagenumber = 1;
	private RelativeLayout mContentView;
	VideoEnabledWebChromeClient v;

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
		setContentView(R.layout.activity_main);
		
	/*	Button exit =(Button)findViewById(R.id.exit);
		Button home =(Button)findViewById(R.id.home);
		Button st =(Button)findViewById(R.id.ss);
*/		// MobileCore.init(this,"8YKF89U9YHXZUIMHFEQ0UDC1T9TOY",
		// LOG_TYPE.DEBUG);
		// MobileCore.getSlider().setContentViewWithSlider(this,R.layout.activity_main);
	/*	if (!GetNetworkStatus.isNetworkAvailable(this)) {

			Toast.makeText(this, "Need working internet", Toast.LENGTH_LONG)
					.show();
			finish();
		}*/
		/*
		 * MobileCore.setStickeezReadyListener(new OnReadyListener() {
		 * 
		 * @Override public void onReady(AD_UNITS adUnit) { if (adUnit ==
		 * AD_UNITS.STICKEEZ) { MobileCore.showStickee((Activity) c); } } });
		 */
		
		webView = (WebView) findViewById(R.id.webView1);
		webView.getSettings().setAppCacheEnabled(true);
		webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
	//	webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setRenderPriority(RenderPriority.HIGH);
		/*webView.setDownloadListener(new DownloadListener() {
			
			@Override
			public void onDownloadStart(String url, String userAgent,
					String contentDisposition, String mimetype, long contentLength) {
				// TODO Auto-generated method stub
				Uri uri = Uri.parse(url);
	            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
	            startActivity(intent);
			}
		});*/
		try {
			webView.getSettings().setAllowContentAccess(true);
			// webView.getSettings().setAllowFileAccessFromFileURLs(true);
			webView.getSettings().setAllowFileAccess(true);
		} catch (Exception e) {
		}
		webView.getSettings().setDomStorageEnabled(true);
		webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		webView.getSettings().setPluginsEnabled(true);
		webView.getSettings().setPluginState(PluginState.ON);
		// webView.setClickable(false);

		/*View nonVideoLayout = findViewById(R.id.activity_main); // Your own
																// view, read
																// class
																// comments
		ViewGroup videoLayout = (ViewGroup) findViewById(R.id.fullscreen_custom_content); // Your
																							// own
																							// view,
																							// read
																							// class
																							// comments
		View loadingView = getLayoutInflater().inflate(R.layout.activity_main,
				null);
		v = new VideoEnabledWebChromeClient(nonVideoLayout, videoLayout,
				loadingView, webView, c) // See all available constructors...
		{
			// Subscribe to standard events, such as onProgressChanged()...

			@Override
			public void onProgressChanged(WebView view, int progress) {
				// Your code...
			}
		};
		v.setOnToggledFullscreen(new VideoEnabledWebChromeClient.ToggledFullscreenCallback() {
			@Override
			public void toggledFullscreen(boolean fullscreen) {
				
				 * if(fullscreen) onBackPressed();
				 
				// Your code to handle the full-screen change, for example
				// showing and hiding the title bar
			}
		});

		webView.setWebChromeClient(v);*/
		webView.setWebViewClient(new WebViewClient() {

			private View mCustomView;

			/* (non-Javadoc)
			 * @see android.webkit.WebViewClient#shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String)
			 */
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// Toast.makeText(c, "sf"+url, Toast.LENGTH_LONG).show();
				/*if (url.endsWith("mp3")) {

					view.loadUrl("http://envato.pixek.com/mobile/items/themeforest/plank/");
				      //   startActivity(intentUrl);
   
					PlayDownloadActivity.audiofile = url;
					startActivity(new Intent(c, PlayDownloadActivity.class));
				        }
				else{*/
					view.loadUrl(url);//}
				
				return true;


			}

			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {

				 webView.loadUrl("https://www.bornmafia.com");
			}

			@Override
			public void onPageFinished(WebView view, String url) {
			}

		});

		webView.setPadding(0, 0, 0, 0);
		webView.setBackgroundColor(Color.BLACK);
		if (GetNetworkStatus.isNetworkAvailable(this)) {

			 webView.loadUrl("https://www.bornmafia.com");
			 
		}
		else
		{
			finish();
			 //webView.loadUrl("file:///android_asset/index.html");
		}
		
	/*	exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!GetNetworkStatus.isNetworkAvailable(c)) {

					 webView.loadUrl("file:///android_asset/index.html");
				}
				else
				{
					webView.loadUrl("http://www.envision-ops.com/demo/index.html");
				}
				
			}
		});*/
		//webView.loadUrl("http://www.freeitunestvshow.com/");
	//	webView.loadUrl("http://lilait.com/squid/Austindo/buildConstruction.html");//http://mobile2.gameassists.co.uk/MobileWebGames/game/3_13_0?lobbyURL=http%3A%2F%2Fmobile2.gameassists.co.uk%2FMobileWebLobby%2F%3F&moduleID=10008&clientID=40301&gameName=5ReelDrive&gameTitle=5+Reel+Drive&LanguageCode=en&clientTypeID=40&casinoID=2258&lobbyName=Betway&loginType=FullUPE&bankingURL=http%3A%2F%2Fmobile2.gameassists.co.uk%2FMobileWebLobby%2F%3F&xmanEndPoints=https%3A%2F%2Fmobile2.gameassists.co.uk%2FXMan%2Fx.x&routerEndPoints=&disablePoweredBy=false&currencyFormat=&isPracticePlay=true");

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

	/*private Image getScreenshotImageObj() throws Exception,
			MalformedURLException, IOException {
		//View v1 = webView.capturePicture();
		Picture picture =webView.capturePicture();

		//v1.setDrawingCacheEnabled(true);
		//Bitmap bitmap = v1.getDrawingCache();
		
		// BitmapDrawable bitmapDrawable = new BitmapDrawable(bm);
		Bitmap bitmap = Bitmap.createBitmap(
				  picture.getWidth(),picture.getHeight(),
				 Bitmap.Config.ARGB_8888);
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		// Bitmap bitmap =
		// BitmapFactory.decodeResource(getBaseContext().getResources(),
		// R.drawable.ic_launcher);
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

		Image screenImg = Image.getInstance(stream.toByteArray());
		return screenImg;
	}

	public void createPDF(String html) {
		Document doc = new Document();

		try {
			String path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
					+ "/PDFDemo";

			File dir = new File(path);
			if (!dir.exists())
				dir.mkdirs();

			Log.d("PDFCreator", "PDF Path: " + path);

			File file = new File(dir, "sample.pdf");
			FileOutputStream fOut = new FileOutputStream(file);

			PdfWriter.getInstance(doc, fOut);

			// open the document
			doc.open();

			Paragraph p1 = new Paragraph(html);
			Font paraFont = new Font(Font.COURIER);
			p1.setAlignment(Paragraph.ALIGN_CENTER);
			p1.setFont(paraFont);

			// add paragraph to document
			doc.add(p1);

			Paragraph p2 = new Paragraph(
					"This is an example of pdf with current screenshot");
			Font paraFont2 = new Font(Font.COURIER, 14.0f, Color.GREEN);
			p2.setAlignment(Paragraph.ALIGN_CENTER);
			p2.setFont(paraFont2);

			doc.add(p2);

			// ByteArrayOutputStream stream = new ByteArrayOutputStream();
			// Bitmap bitmap =
			// BitmapFactory.decodeResource(getBaseContext().getResources(),
			// R.drawable.ic_launcher);
			// bitmap.compress(Bitmap.CompressFormat.JPEG, 100 , stream);

			// Image myImg = Image.getInstance(stream.toByteArray());
			Image myImg = getScreenshotImageObj();

			myImg.setAlignment(Image.MIDDLE);

			// add image to document
			doc.add(myImg);

			// set footer
			Phrase footerText = new Phrase("Hope you liked it :-) ");
			HeaderFooter pdfFooter = new HeaderFooter(footerText, false);
			doc.setFooter(pdfFooter);

		} catch (DocumentException de) {
			Log.e("PDFCreator", "DocumentException:" + de);
		} catch (IOException e) {
			Log.e("PDFCreator", "ioException:" + e);
		} catch (Exception ex) {
			// TODO: handle exception
			Log.e("PDFCreator", "Generic Exception:" + ex);
		} finally {
			doc.close();
		}

	}
*/
	@Override
	public void onBackPressed() {
	//	super.onBackPressed();
	//	final Context c = this;
	//	finish();
	/*	MobileCore.showOfferWall((Activity) c, new CallbackResponse() {
			@Override
			public void onConfirmation(TYPE arg0) {
				((Activity) c).finish();
			}
		});*/
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
		System.exit(0);
			return true;

		case R.id.home:
			 webView.loadUrl("https://www.bornmafia.com");
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
@Override
public void onConfigurationChanged(Configuration newConfig) {
	// TODO Auto-generated method stub
	//super.onConfigurationChanged(newConfig);
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
