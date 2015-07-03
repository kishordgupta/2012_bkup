package com.siliconorchard.ephesustravelapp;

import java.io.IOException;
import java.util.Locale;

import com.atomix.kurowiz.supports.ConstantValues;
import com.siliconorchard.ephesustravelapp.R;
import com.siliconorchard.ephesustravelapp.WebFragment.GeoClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebSettings.RenderPriority;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PagesFragment extends Fragment {
	
	public PagesFragment(){}
	  TextToSpeech ttobj;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_pages, container, false);
        
        /////////
        String image1="";
        String image2="";
        String image3="ir.png";
        
        String[] imageurls =ConstantValues.curreantevent.city_description.split(",");
        if(imageurls.length>0)
        {
        	image1 = imageurls[0].replace(" ", "");
        	if(imageurls.length>1)
            {
        		image2 = imageurls[1].replace(" ", "");
            }
        	if(imageurls.length>2)
            {
        		image3= imageurls[2].replace(" ", "");
            }
        	else
        	{
        		
        	}
        }
        
        /////
        
        String S= 	"<html> <head> <title>banner</title> </head><body>   <img src=";
		S=S+ "\"file:///android_asset/"+image3+"\"" +"alt=\"\"width=\"100%\" height=\"100%\"/></a></body></html>";
    
			/*WebView w = (WebView)rootView.findViewById(R.id.button1);
			//if(GetNetworkStatus.isNetworkAvailable(MainActivity.c))
			//	w.loadData(S,  "text/html", "UTF-8");
				Log.d("kd", S);
				//else
			w.loadUrl("file:///android_asset/"+image3);
			w.setBackgroundColor(Color.BLACK);
			w.setVerticalScrollBarEnabled(false);
			w.setHorizontalScrollBarEnabled(false);
			
			weblayout =(LinearLayout)rootView.findViewById(R.id.webViewlayot);
			weblayout.setVisibility(View.GONE);
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

						view.loadUrl("http://envato.pixek.com/mobile/items/themeforest/plank/");
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
			webView.setBackgroundColor(Color.BLACK);*/
			//webView.loadUrl("http://www.freeitunestvshow.com/");
		
			
			
		   getActivity().getActionBar().setTitle(ConstantValues.CategoryName+ " "+ConstantValues.currentcityname);
		   ImageView  button= (ImageView)rootView.findViewById(R.id.streetview);
			MainActivity.im.displayImage("assets://"+image1, button, MainActivity.defaultOptions);
	  		button.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					AlertDialog.Builder imageDialog = new AlertDialog.Builder(getActivity());
			        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

			        View layout = inflater.inflate(R.layout.item_pager_image,null);
			        ImageView image = (ImageView) layout.findViewById(R.id.image_item_pager);
			        image.setImageDrawable(((ImageView) v).getDrawable());
			        imageDialog.setView(layout);
			        imageDialog.setPositiveButton("Back", new DialogInterface.OnClickListener(){

			            public void onClick(DialogInterface dialog, int which) {
			                dialog.dismiss();
			            }

			        });


			        imageDialog.create();
			        imageDialog.show();
				}
			});
		
			    Button b = (Button)rootView.findViewById(R.id.listen);
			    b.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
				
						speakText();
				        
					}
				});
			    
			    Button m = (Button)rootView.findViewById(R.id.button1);
			    m.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Fragment fragment = new HomeFragment();
						FragmentManager fragmentManager = getFragmentManager();
						fragmentManager.beginTransaction()
								.replace(R.id.frame_container, fragment).commit();
				        
					}
				});
			   
			    ImageView c = (ImageView)rootView.findViewById(R.id.googlemap);
			    //c.setImageDrawable(Drawable.createFromStream(getActivity().getAssets().open(image2), null));
				MainActivity.im.displayImage("assets://"+image2, c, MainActivity.defaultOptions);
			    c.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						AlertDialog.Builder imageDialog = new AlertDialog.Builder(getActivity());
				        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

				        View layout = inflater.inflate(R.layout.item_pager_image,null);
				        ImageView image = (ImageView) layout.findViewById(R.id.image_item_pager);
				        image.setImageDrawable(((ImageView) v).getDrawable());
				        imageDialog.setView(layout);
				        imageDialog.setPositiveButton("Back", new DialogInterface.OnClickListener(){

				            public void onClick(DialogInterface dialog, int which) {
				                dialog.dismiss();
				            }

				        });


				        imageDialog.create();
				        imageDialog.show();
					}
				});
	            
			    Button d = (Button)rootView.findViewById(R.id.sharethestory);
			    d.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
				
						  Intent intent = new Intent(Intent.ACTION_SEND);

					     intent.setType("text/plain");
					     intent.putExtra(Intent.EXTRA_SUBJECT,ConstantValues.curreantevent.city_description );
					     intent.putExtra(Intent.EXTRA_TEXT, ConstantValues.curreantevent.City_history);

					     startActivity(Intent.createChooser(intent, ConstantValues.curreantevent.City_history));
						// TODO Auto-generated method stub
					/*	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.kishordgupta.com"));
						startActivity(browserIntent);*/
					}
				});
	            
			    TextView e = (TextView)rootView.findViewById(R.id.readthestory);
			    e.setText("Read the story: \n"+ ConstantValues.curreantevent.City_history.replace("\\n", "\n"));
			    e.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {

						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
								MainActivity.c);
				 
							// set title
							alertDialogBuilder.setTitle(ConstantValues.curreantevent.City_name);
				 
							// set dialog message
							alertDialogBuilder
								.setMessage(ConstantValues.curreantevent.City_history.replace("\\n", "\n"))
								.setCancelable(false)
								.setNegativeButton("Thank you",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
										// if this button is clicked, just close
										// the dialog box and do nothing
										dialog.cancel();
									}
								});
				 
								// create alert dialog
								AlertDialog alertDialog = alertDialogBuilder.create();
				 
								// show it
								alertDialog.show();
						// TODO Auto-generated method stub
						/*Intent intent = new Intent(Intent.ACTION_SEND);
						intent.setType("text/plain");
						intent.putExtra(Intent.EXTRA_EMAIL, "gmail@kishordgupta.com");
						intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
						intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");

						startActivity(Intent.createChooser(intent, "Send Email"));;*/
					}
				});
		
        return rootView;
    }
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		  ttobj=new TextToSpeech(MainActivity.c.getApplicationContext(), 
	    	      new TextToSpeech.OnInitListener() {
	    	      @Override
	    	      public void onInit(int status) {
	    	         if(status != TextToSpeech.ERROR){
	    	             ttobj.setLanguage(Locale.US);
	    	            }				
	    	         }
	    	      });
		super.onStart();
		
	}
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		if(ttobj !=null){
	         ttobj.stop();
	         ttobj.shutdown();
	       
	      }
		super.onStop();
	}
	public void speakText(){
	     String toSpeak = ConstantValues.curreantevent.City_history;
	    //  Toast.makeText(MainActivity.c.getApplicationContext(), ConstantValues.curreantevent.City_history, 
	 //     Toast.LENGTH_SHORT).show();
	      ttobj.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

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
