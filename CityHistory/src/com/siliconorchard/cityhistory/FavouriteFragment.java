package com.siliconorchard.cityhistory;

import java.util.Locale;

import com.atomix.kurowiz.supports.ConstantValues;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class FavouriteFragment extends Fragment {
	
	public FavouriteFragment(){}
	//	private APIFactory apiFactory;
	public static Context c=null;

	  TextToSpeech ttobj;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_community, container, false);
        getActivity().getActionBar().setTitle("Help");
        
        Button b = (Button)rootView.findViewById(R.id.lishelp);
        b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				speakText();
			}
		});
        
        Button c = (Button)rootView.findViewById(R.id.email);
        c.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_EMAIL,new String[] { "craigbennettii@techreviewsandhelp.com" }); // "craigbennettii@techreviewsandhelp.com");
				intent.putExtra(Intent.EXTRA_SUBJECT, "Carteret County Travel Guide");
				intent.putExtra(Intent.EXTRA_TEXT, "");

				startActivity(Intent.createChooser(intent, "Send Email"));;
			}
		});
        return rootView;
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
	     String toSpeak = MainActivity.c.getResources().getString(R.string.hello_world);
	    //  Toast.makeText(MainActivity.c.getApplicationContext(), ConstantValues.curreantevent.City_history, 
	 //     Toast.LENGTH_SHORT).show();
	      ttobj.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

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
}
