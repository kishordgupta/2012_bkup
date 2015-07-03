package com.shishir.insult.creator;


import java.util.Locale;

import com.shishir.insult.creator.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity implements OnInitListener  {
	 private TextToSpeech tts;
	String insult = "You Know what? you are nothing but a ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextToSpeech tts = new TextToSpeech(this, this);
        tts.setLanguage(Locale.US);
       // tts.speak("Text to say aloud", TextToSpeech.QUEUE_ADD, null);
        Toast.makeText(this, "Please , wait ................", Toast.LENGTH_LONG).show();
        final WebView webView =(WebView)findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
		 webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		   webView.getSettings().setJavaScriptEnabled(true);
		   webView.getSettings().setLoadWithOverviewMode(true);
		   webView.setScrollbarFadingEnabled(false);
		   webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		   final Context c=this;
		   webView.setWebViewClient(new WebViewClient() {
		        @Override
		        public boolean shouldOverrideUrlLoading(WebView view, String url) {

			        if(url.contains("share.com"))
			        {
			        	String s =url.replace("www.share.com/","");
			        s =url.replace("https://","");
			        s =s.replace("www.share.com/","");
			       s =s.replace("http://","");
			       s=s.replace("%"," ");
			       s=s.replace("20"," ");
			        	//Toast.makeText(c, webView.getUrl(),Toast.LENGTH_SHORT).show();
			         	//view.goBack();
			         	
			         Share.share(s, c) ;
			     //   
			         return true;
			        }
			        else if(url.contains("shout.com"))
			        {
			        	String s =url.replace("www.shout.com/","");
			        s =url.replace("https://","");
			        s =s.replace("www.shout.com/","");
			       s =s.replace("http://","");
			       s=s.replace("%"," ");
			       s=s.replace("20"," ");
			        	//Toast.makeText(c, webView.getUrl(),Toast.LENGTH_SHORT).show();
			         //	view.goBack();
			         	 tts.speak(s, TextToSpeech.QUEUE_ADD, null);
			     //   
			         	 return true;
			        }
			        else   if(url.contains("rate_us"))
			        {
			        	String s =url.replace("www.share.com/","");
			        s =url.replace("https://","");
			       s =url.replace("http://","");
			       s=s.replace("%","");
			        	//https://play.google.com/store/apps/details?id=details?id=com.shishir.insult.creatorToast.makeText(c, webView.getUrl(),Toast.LENGTH_SHORT).show();
			         //	view.goBack();
			         	
			       //  Share.share(insult+s, c) ;
			     //   
			     
			        }
			        else  if(url.contains("exit"))
			        {
			        	view.goBack();
			        	finish();
			       /* 	//Toast.makeText(c, webView.getUrl(),Toast.LENGTH_SHORT).show();
			        AlertDialog.Builder builder = new AlertDialog.Builder(c);
			        	builder.setMessage("Are you sure to exit?").setPositiveButton("Yes", dialogClickListener)
			        	    .setNegativeButton("No", dialogClickListener).show();*/
			      
			        }
			        else
			        {
		            view.loadUrl(url);
			        }
		            return false;
		        }
		        @Override
		        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
	                
		        	Toast.makeText(c, webView.getUrl() + description,Toast.LENGTH_SHORT).show();
		        	//webView.loadUrl("file://android_asset/image.html#98.jpg");
	                
	            
                  Toast.makeText(c, description+failingUrl, Toast.LENGTH_LONG).show();
	            }
		        
		        @Override
		        public void onPageFinished(WebView view, String url) {
		        // TODO Auto-generated method stub
		       super.onPageFinished(view, url);
		       // String id = "98.jpg";
		      //  webView.loadUrl("javascript:as(" + id + ");");
		        }
		        
		  
		    });
		   webView.loadUrl("file:///android_asset/index.html");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		
	}

	   @Override
	    public void onDestroy() {
	        // Don't forget to shutdown tts!
	        if (tts != null) {
	            tts.stop();
	            tts.shutdown();
	        }
	        super.onDestroy();
	    }

    
}
