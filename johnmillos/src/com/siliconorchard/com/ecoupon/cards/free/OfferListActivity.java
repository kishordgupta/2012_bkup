package com.siliconorchard.com.ecoupon.cards.free;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.NameValuePair;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atomix.kurowiz.supports.ConstantValues;

import com.atomix.kurowiz.supports.InternetConnectivity;
import com.atomix.kurowiz.supports.MyScrollView;
import com.atomix.kurowiz.supports.MyScrollView.OnScrollStoppedListener;
import com.atomix.kurowiz.supports.SingleToneClass;

import com.atomix.kurowiz.xmlparser.APIFactory;
import com.atomix.kurowiz.xmlparser.DomParser;
import com.model.Constants;
import com.model.Instancevalues;

public class OfferListActivity extends Activity {
	
	private static ProgressDialog progressDialog;
	private static APIFactory apiFactory;
	public static Context c=null;
	private static LinearLayout linearLayout;
	static public String gifttype="";
	static public int giftid=0;
	private static MyScrollView scrollViewExchange;
	private static int pageId = 1;
	private static boolean isMore = false;
	private String giftMsg = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vendorfragmentlist);
	  
	    apiFactory = new APIFactory();
	    ActionBar actionBar = getActionBar();
	    actionBar.setTitle(""+Instancevalues.currentcategoryname);
	    actionBar.setHomeButtonEnabled(true);
    	/*if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}*/
	/*	if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		*/
	//	apiFactory = new APIFactory();
		
    	 c=this;
    	 linearLayout = (LinearLayout) findViewById(R.id.linear_layout_footer);
			//imgBtnExchange = (ImageButton) view.findViewById(R.id.img_btn_exchange);
			//imgBtnExchange.setOnClickListener(this);
			
		/*	scrollViewExchange = (MyScrollView)findViewById(R.id.scrollView_exchange);
			
			scrollViewExchange.setOnScrollStoppedListener(new OnScrollStoppedListener()
			{
				public void onScrollStopped() 
				{
					isMore = true;
					pageId++;
					
					if(ConstantValues.isScrolling)
				   new  APITask().execute();
					else
					{Toast.makeText(c, "no more result", Toast.LENGTH_LONG).show();}
		        }
			});*/
		//	imgBtnTop = (ImageButton) view.findViewById(R.id.img_btn_top);
		//	imgBtnTop.setOnClickListener(this);
			
		
			
			new APITask().execute();
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		if (id == R.id.action_login) {
			startActivityForResult(new Intent(this,LoginActivity.class),101);
			return true;
		}
		
		if (id == R.id.register) {
			startActivityForResult(new Intent(this,SignupActivity.class),102);
			return true;
		}
		 switch (item.getItemId()) {
	        case android.R.id.home:
	            // app icon in action bar clicked; go home
	        	onBackPressed();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);}
	}
	public boolean onPrepareOptionsMenu (Menu menu) {    
	    menu.clear();    
	    if (Instancevalues.user) {
	        
	        getMenuInflater().inflate(R.menu.login, menu);
	    }
	    else {
	       
	        getMenuInflater().inflate(R.menu.main, menu);
	    }    
	    return super.onPrepareOptionsMenu(menu);
	}
	/**
	 * A placeholder fragment containing a simple view.
	 */
	/*public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.exchange_fragment, container,
					false);
			linearLayout = (LinearLayout) rootView.findViewById(R.id.linear_layout_footer);
			//imgBtnExchange = (ImageButton) view.findViewById(R.id.img_btn_exchange);
			//imgBtnExchange.setOnClickListener(this);
			
			scrollViewExchange = (MyScrollView) rootView.findViewById(R.id.scrollView_exchange);
			
			scrollViewExchange.setOnScrollStoppedListener(new OnScrollStoppedListener()
			{
				public void onScrollStopped() 
				{
					isMore = true;
					pageId++;
					
					if(ConstantValues.isScrolling)
				   new  APITask().execute();
					else
					{Toast.makeText(c, "no more result", Toast.LENGTH_LONG).show();}
		        }
			});
			return rootView;
		}
	}
	
	*/
	private static class APITask extends AsyncTask<Void, Void, String> 
	{
		private String RESULT = "OK";

		@Override
		protected void onPreExecute() 
		{
			Log.d("f", "preexecute");
			progressDialog = ProgressDialog.show(getActivity(), "", "Loading...", true, false);
			ConstantValues.isBottomReached = false;
		}

		private Context getActivity() {
			// TODO Auto-generated method stub
			
			return OfferListActivity.c;
		}

		@Override
		protected String doInBackground(Void... arg0) 
		{
			try 
			{
				if (InternetConnectivity.isConnectedToInternet(getActivity())) 
				{
					InputStream xml;
					DomParser domParser = new DomParser();
					
					ArrayList<NameValuePair> getcategory = apiFactory.getofferlist(Instancevalues.currentuser.getUserID(), pageId, Instancevalues.currentVendor.getVendorId());//(Instancevalues.currentcategoryname, Instancevalues.currentuser.getUserID(),pageId);
					xml = SingleToneClass.getInstance().getResponseFromServer(getcategory);
					domParser.parsegetofferlist(xml, isMore);
					
					RESULT=Instancevalues.Error_message;
				
					return RESULT;
				} 
				else 
				{
					SingleToneClass.getInstance().openInternetSettingsActivity(getActivity());
					return RESULT;
				}
			} 
			catch (Exception ex) 
			{
				return RESULT;
			}
		}

		@Override
		protected void onPostExecute(String result) 
		{
			if (progressDialog.isShowing())
			{
				progressDialog.dismiss();
				progressDialog = null;
				isMore = false;
				ConstantValues.isBottomReached = true;
			}
			
			linearLayout.removeAllViews();
			LayoutInflater inflater = LayoutInflater.from(getActivity());
			
			if(Instancevalues.VendorOfferlist != null)
			{
		Log.d("sdf", "2 "+Instancevalues.VendorOfferlist.size()+" " );	
				ConstantValues.isBottomReached = true;
				for(int i = 0; i < Instancevalues.VendorOfferlist.size(); i++)
				{Log.d("sdf", "3");	
					View item = inflater.inflate(R.layout.offerfragment, null);
					item.setTag(i);
					
					TextView txtViewKey = (TextView) item.findViewById(R.id.hotelname);
					txtViewKey.setText(Instancevalues.VendorOfferlist.get(i).getShort_desc());
					
					TextView txtViewDate = (TextView) item.findViewById(R.id.city);
					txtViewDate.setText(""+Instancevalues.VendorOfferlist.get(i).getVendor_name());
					
					TextView txtViewPoint = (TextView) item.findViewById(R.id.rating);
					txtViewPoint.setText("Star: "+Instancevalues.VendorOfferlist.get(i).getExpire_date()+" remaining:"+ Instancevalues.VendorOfferlist.get(i).getRemaining_redem());
				String S= 	"<html> <head> <title>banner</title> </head><body>   <img src=";
				S=S+ "\""+Instancevalues.currentvendorlist.get(i).getVendorlogo()+"\"" +"alt=\"\"width=\"100%\" height=\"100%\"/></a></body></html>";

					WebView w = (WebView)item.findViewById(R.id.img);
					w.getSettings().setAppCacheEnabled(true);
					w.getSettings().setLoadWithOverviewMode(true);
					w.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
					w.getSettings().setUseWideViewPort(true);
					w.loadData(S,  "text/html", "UTF-8");
					
				/*	item.setOnFocusChangeListener(new OnFocusChangeListener() {
						
						@Override
						public void onFocusChange(View v, boolean hasFocus) {
							// TODO Auto-generated method stub
							if(hasFocus)
								v.setBackgroundColor(Color.BLACK);
							else
							{int i = (Integer)v.getTag();
								if(i%2!=0)
								{
									v.setBackgroundColor(Color.parseColor("#F5BCA9"));
								}
								else
									v.setBackgroundColor(Color.parseColor("#F2F2F2"));
							}
						}
					});*/
					
					item.setOnTouchListener( new View.OnTouchListener()
				    {

				        @Override
				        public boolean onTouch(View v, MotionEvent event) {
				            // TODO Auto-generated method stub
				            switch(event.getAction())
				            {
				        case MotionEvent.ACTION_DOWN:
				            v.setBackgroundColor(Color.RED);
				            break;
				            case MotionEvent.ACTION_UP:
				            	int i = (Integer) v.getTag();
						//	Instancevalues.currentVendor=Instancevalues.VendorOfferlist.get(i);//).getVendoradress()+"";
							/*	Instancevalues.Lat=Instancevalues.VendorOfferlist.get(i).getLat()+"";
								Instancevalues.lon=Instancevalues.VendorOfferlist.get(i).getLon()+"";
								Instancevalues.currentvendor=Instancevalues.VendorOfferlist.get(i).getVendorname()+"";
							*/	Intent ia = new Intent(getActivity(),CouponActivity.class);
								
							getActivity().startActivity(ia);
							//	ia.setData(Uri.parse(gifttype));
							////	getActivity().startActivity(ia);
				            //set color back to default
								if(i%2!=0)
								{
									v.setBackgroundColor(Color.parseColor("#F5BCA9"));
								}
								else
									v.setBackgroundColor(Color.parseColor("#F2F2F2"));
				            break;
				            }
				            return true;        
				        }
				    });
					
				//item.setBackground(getActivity().getResources().getDrawable(R.drawable.button_selector));
					
			/*		item.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							//arg0.setBackgroundColor(Color.parseColor("#A5DF00"));
							int i = (Integer) arg0.getTag();
							gifttype=Instancevalues.categorylist.get(i).getHotel_link()+"";
							giftid=i;
							Intent ia = new Intent(Intent.ACTION_VIEW);
							ia.setData(Uri.parse(gifttype));
							getActivity().startActivity(ia);
							
							
						}
					});*/
					
					if(i%2!=0)
					{
						item.setBackgroundColor(Color.parseColor("#F5BCA9"));
					}
					else
						item.setBackgroundColor(Color.parseColor("#F2F2F2"));
					linearLayout.addView(item);
				}
			}
			
			else
			{
				ConstantValues.isBottomReached = false;
			/*	Button btn = new Button(getActivity());
				btn.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
				btn.setText("There is no history");
				*/
			//	linearLayout.addView(btn);
			}
			
		}
	}

	

}
