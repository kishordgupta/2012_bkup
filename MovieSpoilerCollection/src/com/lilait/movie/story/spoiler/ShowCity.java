package com.lilait.movie.story.spoiler;

import java.util.ArrayList;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.lilait.movie.story.spoiler.R;
import com.lilakhelait.kishor.helper.Email;
import com.lilakhelait.kishor.helper.Share;
import com.lilakhelait.kishor.helper.Sms;
import com.lilakhelait.kishor.helper.service.ReviewParser;
import com.lilakhelait.kishor.helper.service.StackParser;
import com.lilakhelait.kishor.listview.Happynewyearlist;
import com.lilakhelait.kishor.listview.movielist;

import com.lilakhelait.kishor.resource.Constants;
import com.lilakhelait.kishor.resource.Wish;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ShowCity extends Activity{
	movielist mv=null;
	private WebView webView;
  	TextView Title;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  Bundle extras = getIntent().getExtras();
	  final String city = extras.getString("city_name");
	  final String Date = extras.getString("date");
	  ReviewParser.url=Date;
	  ReviewParser.name=city;
	   startActivityForResult(new Intent(this, ReviewParser.class),123);
      setContentView(R.layout.list);
      AdRequest ad = new AdRequest();
  	//    ad.setTesting(true);
  	    AdView adView = (AdView)findViewById(R.id.ad);
  	    adView.loadAd(ad);
     
      final Context    context=this;
      
  	Button sms;
  	Button share;
  	Button email;
      Title = (TextView)findViewById(R.id.textView0);
		
     share = (Button)findViewById(R.id.bpass);
      sms = (Button)findViewById(R.id.bsms);
      email= (Button)findViewById(R.id.bemail);
   
      share.setTextColor(Color.RED);
      sms.setTextColor(Color.RED);
      email.setTextColor(Color.RED);
 
      share.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Share.share(ReviewParser.datas,context);
				Button t=(Button)v;
				t.setTextColor(Color.RED);
			}
		});
    
      sms.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Sms.sms(ReviewParser.datas,context);
				Button t=(Button)v;
				t.setTextColor(Color.RED);
			}
		});
      
      email.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Email.email(context, "Review" , ReviewParser.datas);
				Button t=(Button)v;
				t.setTextColor(Color.RED);
			}
		});
    
		
			Title.setText(Date);
			Title.setTextColor(Color.RED);
//			playerrank.setText(playerdata.playernumber);
//			isactive.setChecked(playerdata.isactive);
			/*webView = (WebView)findViewById(R.id.webView1);
			 webView.getSettings().setAppCacheEnabled(true);
			 webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
			   webView.getSettings().setJavaScriptEnabled(true);
			   webView.getSettings().setLoadWithOverviewMode(true);
			 
			   webView.setWebViewClient(new WebViewClient() {
			        @Override
			        public boolean shouldOverrideUrlLoading(WebView view, String url) {

				       
			            view.loadUrl(url);
						return false;
				       
			        }
			        @Override
			        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
		                
			        	       }
			        
			        @Override
			        public void onPageFinished(WebView view, String url) {
			        // TODO Auto-generated method stub
			        super.onPageFinished(view, url);
			        }
			        
			  
			    });
			   webView.getSettings().setBuiltInZoomControls(true);
			    webView.getSettings().setSupportZoom(true);     
			    webView.getSettings().setLoadWithOverviewMode(true);
			     webView.getSettings().setUseWideViewPort(true);
			    webView.setPadding(0, 0, 0, 0);
			    webView.setBackgroundColor(Color.WHITE);*/
			
			  //  String s ="http://marsapparel.co.jp/app";
				// webView.loadUrl("");
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	//	 webView.loadData(ReviewParser.datas, null, null);
		Title.setText(ReviewParser.datas);
	}
}
