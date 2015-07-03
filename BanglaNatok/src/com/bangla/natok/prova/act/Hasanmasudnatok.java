package com.bangla.natok.prova.act;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bangla.natok.prova.GetNetworkStatus;
import com.bangla.natok.prova.R;
import com.bangla.natok.prova.VideoEnabledWebChromeClient;
import com.bangla.natok.prova.VideoEnabledWebView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class Hasanmasudnatok extends Activity {
	private VideoEnabledWebView webView;
	InterstitialAd mInterstitialAd;
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
	private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                  .build();

        mInterstitialAd.loadAd(adRequest);
    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Context c = this;
		setContentView(R.layout.activity_main);
		 mInterstitialAd = new InterstitialAd(this);
	        mInterstitialAd.setAdUnitId("ca-app-pub-2884314377778347/4720323513");
	        requestNewInterstitial();
		
	
		 if (!GetNetworkStatus.isNetworkAvailable(this)) {
              
                 Toast.makeText(this, "Need working internet", Toast.LENGTH_LONG).show();
                 finish();
			} 
		
		/* MobileCore.setAdUnitEventListener(new AdUnitEventListener() {
			
			@Override
			public void onAdUnitEvent(AD_UNITS arg0, EVENT_TYPE arg1) {
			
				// TODO Auto-generated method stub
				 MobileCore.showStickee((Activity) c);
			}
		})*/; /*{
			    @Override
			    public void onReady(AD_UNITS adUnit) {
				if (adUnit == AD_UNITS.STICKEEZ) {
				    MobileCore.showStickee((Activity) c);
				}
			    }
			});*/
		final ProgressDialog progressDialog = ProgressDialog.show(c,
				"Loading...", "");
		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(9 * 1000);
					progressDialog.dismiss();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

		webView = (VideoEnabledWebView) findViewById(R.id.webView1);
		webView.getSettings().setAppCacheEnabled(true);
		webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		// webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setRenderPriority(RenderPriority.HIGH);
		try {
			webView.getSettings().setAllowContentAccess(true);
			// webView.getSettings().setAllowFileAccessFromFileURLs(true);
			webView.getSettings().setAllowFileAccess(true);
		} catch (Exception e) {
		}
		webView.getSettings().setDomStorageEnabled(true);
		webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		webView.getSettings().setDomStorageEnabled(true);
		//webView.getSettings().setPluginsEnabled(true);
		webView.getSettings().setPluginState(PluginState.ON);
		// webView.setClickable(false);

		View nonVideoLayout = findViewById(R.id.activity_main); // Your own
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
				/*
				 * if(fullscreen) onBackPressed();
				 */
				// Your code to handle the full-screen change, for example
				// showing and hiding the title bar
			}
		});

		webView.setWebChromeClient(v);
		webView.setWebViewClient(new WebViewClient() {

			private View mCustomView;

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
			//	  Toast.makeText(c, "sf"+url, Toast.LENGTH_LONG).show();
				final ProgressDialog progressDialog = ProgressDialog.show(c,
						"Loading...", "");
				new Thread(new Runnable() {
					public void run() {
						try {
							Thread.sleep(3 * 1000);
							progressDialog.dismiss();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}).start();
				/* if(url.contains("exit.com"))
			      {
	        		  finish();
			      }
			      
	        	  else*/ if(url.contains("nativeplay.com"))
			      {
	        		  String text =url.replace("http://www.nativeplay.com/?v_id=", "");
	        		 // view.goBack();
	        		//  Toast.makeText(c, "sf"+url, Toast.LENGTH_LONG).show();
	        		  try{
	                      Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + text));
	                      startActivity(intent);                 
	                      }catch (ActivityNotFoundException ex){
	                          Intent intent=new Intent(Intent.ACTION_VIEW, 
	                          Uri.parse("http://www.youtube.com/watch?v="+text));
	                          startActivity(intent);
	                      }
			      
			      }
	        /*	  else if(url.contains("setaswallpaper.com"))
			      {
	        	
	        		 // http://www.nativeplay.com/?v_id=iwAYR986h8k
	        	    
			    	    	String text =url.replace("http://www.setaswallpaper.com/?url==", "");
					    	
					  	  Startload=true;
					    		 DownloadImageByUrl downloadImageByUrl = new DownloadImageByUrl();
					    		 downloadImageByUrl.downloadImage(text,2,c);
				    			 Bitmap b =null;
				    		 long time=0;
				    	
					    	
					  
					      
					 		//   Wallpaper.wall(c, b);
					 		    
				    		 Toast.makeText(c, "Preparing for wallpaper ", 10000).show();	
					 		//Share.share(output, c,url);
			    view.goBack();
			      }
	        	  else  if(url.contains("download.com"))
		      {
		    	
       	    	
		    	//  http://www.download.com/?url=http://sphotos-g.ak.fbcdn.net/hphotos-ak-frc3/t1/1486595_10152138183407164_1981820731_n.jpg
				    	String text =url.replace("http://www.download.com/?url=", "");
				    		// Picture picture = view.capturePicture();
				    		//	text =text.replace("http://www.share.com?winner=", "");
				    	Startload=true;
			    		 DownloadImageByUrl downloadImageByUrl = new DownloadImageByUrl();
			    			 downloadImageByUrl.downloadImage(text,1,c);
			    			 Bitmap b =null;
			    		
				     
				 			Toast.makeText(c, "Downloading in External sdcard ", 10000).show();
		    	view.goBack();
		      }
		      else if(url.contains("share.com"))
		      {
		    	
 	    
		      	String text =url.replace("http://www.share.com/?url=", "");
			    	
			    	Startload=true;
		    		 DownloadImageByUrl downloadImageByUrl = new DownloadImageByUrl();
		    		 downloadImageByUrl.downloadImage(text,3,c);
	    			 Bitmap b =null;
	    		 long time=0;
	    		 
			     
	    		 Toast.makeText(c, "Preparing for share ", 10000).show();	
			 		  
			 		    
	    	view.goBack();
	    	
		    	  Intent intent;

				    try {
				        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.lilait.math.calculus.Calculator.Differentiation.Integral.Integration.Calculation.Derivative"));
				        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				        c.startActivity(intent);
				    } catch (Exception e) {
				     
				    }
		    	 
		      }*/
		        else
		        {
	            view.loadUrl(url);
		        }
	            return true;
				
			}

			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {

			//	webView.loadUrl("file:///android_asset/index.html?Use_Id=cricket");
			}

			@Override
			public void onPageFinished(WebView view, String url) {
			}

		});

		webView.setPadding(0, 0, 0, 0);
		webView.setBackgroundColor(Color.BLACK);

		webView.loadUrl("file:///android_asset/hasanmasood.html");
		
		AdView adView = (AdView)this.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder()
		    .build();
		adView.loadAd(adRequest);

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
		final Context c= this;
	
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// Check if the key event was the Back button and if there's history
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
			webView.goBack();

			return true;
		}
		if ((keyCode == KeyEvent.KEYCODE_BACK) && (webView.canGoBack() != true)) {
			 if (mInterstitialAd.isLoaded()) {
                 mInterstitialAd.show();
             } 
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
			 if (mInterstitialAd.isLoaded()) {
                 mInterstitialAd.show();
             } 
			finish();
			return true;

		case R.id.home:
			webView.loadUrl("file:///android_asset/hasanmasood.html");
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

}
