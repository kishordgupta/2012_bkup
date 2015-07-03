package com.siliconorchard.ephesustravelapp;

import java.util.Locale;

import com.atomix.kurowiz.supports.ConstantValues;
import com.siliconorchard.ephesustravelapp.R;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_community, container, false);
        getActivity().getActionBar().setTitle("About us");
        
        Button b = (Button)rootView.findViewById(R.id.lishelp);
        b.setText("Make A Call");
        b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String	posted_by = "00905321685216";

				 String uri = "tel:" + posted_by.trim() ;
				 Intent intent = new Intent(Intent.ACTION_CALL);
				 intent.setData(Uri.parse(uri));
				 startActivity(intent);
			}
		});
        
        Button c = (Button)rootView.findViewById(R.id.email);
        c.setText("Send an SMS");
        c.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				
				Uri uri = Uri.parse("smsto:00905321685216");   
				Intent it = new Intent(Intent.ACTION_SENDTO, uri);   
				it.putExtra("sms_body", "Tours on ephesus");   
				startActivity(it); 
			}
		});
        
        Button e = (Button)rootView.findViewById(R.id.email1);
        e.setText("Send an Email");
        e.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_EMAIL,new String[] { "ephesus@turkeytravelapps.com" }); // "craigbennettii@techreviewsandhelp.com");
				intent.putExtra(Intent.EXTRA_SUBJECT, "Tours on ephesus " );
				intent.putExtra(Intent.EXTRA_TEXT, "");

				startActivity(Intent.createChooser(intent, "Send Email"));;
		
			}
		});
        return rootView;
    }
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		
		super.onStop();
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		 
		super.onStart();
	}
}
