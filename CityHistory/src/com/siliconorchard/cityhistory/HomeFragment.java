package com.siliconorchard.cityhistory;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


import com.atomix.kurowiz.supports.ConstantValues;
import com.siliconorchard.cityhistory.R;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;

public class HomeFragment extends Fragment {
	
	public HomeFragment(){}
	Button button=null;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

			    Button b = (Button)rootView.findViewById(R.id.favourite);
			    b.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						 Fragment f;
				            f = new FavouriteFragment();
				            FragmentTransaction ft = getFragmentManager().beginTransaction();
				       
				            ft.replace(R.id.frame_container, f);
				            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				            ft.addToBackStack(null);
				            ft.commit();
					}
				});
			   
			    Button c = (Button)rootView.findViewById(R.id.chat);
			    c.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String s ="http://techreviewsandhelp.com/";
						//	webView.loadUrl(s);
							Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
							startActivity(browserIntent);
					}
				});
	            
			    Button d = (Button)rootView.findViewById(R.id.social);
			    d.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
					 LinearLayout mDrawer = (LinearLayout) getActivity().findViewById(R.id.list); 
						DrawerLayout mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
						 mDrawerLayout.openDrawer(mDrawer);
					}
				});
	            
			    Button e = (Button)rootView.findViewById(R.id.contact);
			    e.setOnClickListener(new OnClickListener() {
					
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
	
	
}
