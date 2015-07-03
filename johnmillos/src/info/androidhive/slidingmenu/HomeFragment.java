package info.androidhive.slidingmenu;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.atomix.kurowiz.supports.ConstantValues;
import com.model.Instancevalues;
import com.siliconorchard.com.ecoupon.cards.free.ListActivity;
import com.siliconorchard.com.ecoupon.cards.free.R;
import com.siliconorchard.com.ecoupon.cards.free.SignupActivity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
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
	//IcyStreamMeta	streamMeta = new IcyStreamMeta();
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        String S= 	"<html> <head> <title>banner</title> </head><body>   <img src=";
		S=S+ "\""+"http://savings2phone.com/mobsite/images/logo.gif"+"\"" +"alt=\"\"width=\"100%\" height=\"100%\"/></a></body></html>";
    
			Button w = (Button)rootView.findViewById(R.id.button1);
		/*	w.loadData(S,  "text/html", "UTF-8");
			w.setVerticalScrollBarEnabled(false);
			w.setHorizontalScrollBarEnabled(false);*/
			w.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					try {
						 LinearLayout mDrawer = (LinearLayout) getActivity().findViewById(R.id.list); 
							DrawerLayout mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
							 mDrawerLayout.openDrawer(mDrawer);
						} catch (Exception e) {
						}
				}
			});
			
            button= (Button)rootView.findViewById(R.id.button2);
			
			    Button b = (Button)rootView.findViewById(R.id.favourite);
			    b.setText("Fetured Vendor");
			    b.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						 Fragment f;
				            f = new PagesFragment();
				            FragmentTransaction ft = getFragmentManager().beginTransaction();
				            ft.replace(R.id.container, f);
				            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				            ft.addToBackStack(null);
				            ft.commit();
					}
				});
			   
			    Button c = (Button)rootView.findViewById(R.id.button2);
			  //  c.setText("History");
			    c.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						startActivity(new Intent(ListActivity.c,SignupActivity.class));
					/*	if (Instancevalues.VendorDistancelist != null) {
							startActivity(new Intent(ListActivity.c, MapactivityFragment.class));
				
					}*/}
				});
	            
			    Button d = (Button)rootView.findViewById(R.id.social);
			   // c.setText("History");
			    d.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						//startActivity(new Intent(ListActivity.c,SignupActivity.class));
						if (Instancevalues.VendorDistancelist != null) {
							startActivityForResult(new Intent(ListActivity.c, MapactivityFragment.class),123);
				
					}}
				});
	            
			    Button e = (Button)rootView.findViewById(R.id.chat);
				   // c.setText("History");
				    e.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							//startActivity(new Intent(ListActivity.c,SignupActivity.class));
							 Fragment f;
					            f = new SearchTextFragment();
					            FragmentTransaction ft = getFragmentManager().beginTransaction();
					            ft.replace(R.id.container, f);
					            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
					            ft.addToBackStack(null);
					            ft.commit();
						}
					});
		
        return rootView;
    }
@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO Auto-generated method stub
	super.onActivityResult(requestCode, resultCode, data);
	if(requestCode==123)
	{
		Fragment f;
        f = new PhotosFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container, f);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
	}
}	
	
}
