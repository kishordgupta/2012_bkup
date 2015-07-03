package info.androidhive.slidingmenu;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.NameValuePair;

import com.atomix.kurowiz.supports.ConstantValues;
import com.atomix.kurowiz.supports.InternetConnectivity;
import com.atomix.kurowiz.supports.MyScrollView;
import com.atomix.kurowiz.supports.SingleToneClass;
import com.atomix.kurowiz.supports.MyScrollView.OnScrollStoppedListener;
import com.atomix.kurowiz.xmlparser.APIFactory;
import com.atomix.kurowiz.xmlparser.DomParser;
import com.model.Instancevalues;
import com.siliconorchard.com.ecoupon.cards.free.CouponActivity;
import com.siliconorchard.com.ecoupon.cards.free.ListActivity;
import com.siliconorchard.com.ecoupon.cards.free.LoginActivity;
import com.siliconorchard.com.ecoupon.cards.free.OfferListActivity;
import com.siliconorchard.com.ecoupon.cards.free.R;
import com.siliconorchard.com.ecoupon.cards.free.SignupActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.util.Xml.Encoding;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PhotosFragment extends Fragment {
	private static ProgressDialog progressDialog;
	private static APIFactory apiFactory;
	public static Context c = null;
	private static LinearLayout linearLayout;
	static public String gifttype = "";
	static public int giftid = 0;
	private static MyScrollView scrollViewExchange;
	private static int pageId = 1;
	private static boolean isMore = false;
	private String giftMsg = "";
//    public LinearLayout lin ;
    public boolean streatview=false;
    public WebView webView;
	public PhotosFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.vendorfragmentlist,
				container, false);
		apiFactory = new APIFactory();
		ActionBar actionBar = ((Activity) ListActivity.c).getActionBar();
	//	lin=(LinearLayout)rootView.findViewById(R.id.topa);
	//	lin.setVisibility(View.GONE);
		webView = (WebView) rootView.findViewById(R.id.img);
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

					/*view.loadUrl("http://envato.pixek.com/mobile/items/themeforest/plank/");
				      //   startActivity(intentUrl);
*/
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
		webView.setBackgroundColor(Color.WHITE);
		try {

			if("0".contains(Instancevalues.currentVendor.getVendorlogo()))
				Instancevalues.currentVendor.setVendorlogo("http://savings2phone.com/mobsite/images/logo.gif");
			  String S= 	"<html> <head> <title>banner</title> </head><body>   <img src=";
				S=S+ "\""+Instancevalues.currentVendor.getVendorlogo()+"\"" +"alt=\"\"width=\"100%\" height=\"100%\"/></a></body></html>";
		    
			/*String S= 	"<html> <head></head><body>   <img src=";
			S=S+ "\""+Instancevalues.currentVendor.getVendorlogo()+"\"" +"alt=\"\"width=\"100%\" height=\"100%\"/></a></body></html>";
*/	    
				webView.loadData(S, "text/html", null);
			actionBar.setTitle("" + Instancevalues.currentVendor.getVendorname()
					+ "    " + Instancevalues.currentVendor.getVendorphone());
		} catch (Exception e) {
		}
		
		
		Button  button= (Button)rootView.findViewById(R.id.streetmapview);
  		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
			/*	if(lin.getVisibility()==View.VISIBLE&& streatview==true)
					{//lin.setVisibility(View.GONE);
					streatview=false;
					}
				else
				{*///lin.setVisibility(View.GONE);
				//	"http://maps.google.com/maps?q=&layer=c&cbll="+ConstantValues.curreantevent.City_lat","+ConstantValues.curreantevent.City_lon+"&cbp=11,0,0,0,0"
					String s ="http://maps.google.com/maps?q=&layer=c&cbll="+Instancevalues.currentVendor.getLat()+","+Instancevalues.currentVendor.getLon()+"&cbp=11,0,0,0,0";
					webView.loadUrl(s);
					streatview=true;//}
				}
				catch(Exception e)
				{
					
				}
			}
		});
	
		 
		    Button ca = (Button)rootView.findViewById(R.id.webmapview);
		    ca.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					try{
						/*if(lin.getVisibility()==View.VISIBLE && streatview==false)
					{	lin.setVisibility(View.GONE);
					
					
					}
					else*/
				//	lin.setVisibility(View.VISIBLE);
					String s ="http://maps.google.com/maps?q="+Instancevalues.currentVendor.getLat()+","+Instancevalues.currentVendor.getLon();
					webView.loadUrl(s);
					}
					catch(Exception e)
					{
						
					}
				
				}
			});
		
		actionBar.setHomeButtonEnabled(true);
	/*	WebView w = (WebView) rootView.findViewById(R.id.img);
		w.getSettings().setAppCacheEnabled(true);
		w.getSettings().setLoadWithOverviewMode(true);
	    w.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		w.getSettings().setUseWideViewPort(true);
		w.setVerticalScrollBarEnabled(false);
		w.setHorizontalScrollBarEnabled(false);
		
		w.setBackgroundColor(Color.WHITE);
		w.setVerticalScrollBarEnabled(false);
		w.setHorizontalScrollBarEnabled(false);*/
		
		
		try {
		 LinearLayout mDrawer = (LinearLayout) getActivity().findViewById(R.id.list); 
			DrawerLayout mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
			 mDrawerLayout.closeDrawer(mDrawer);
		} catch (Exception e) {
		}
		c = ListActivity.c;
		linearLayout = (LinearLayout) rootView
				.findViewById(R.id.linear_layout_footer);

		scrollViewExchange = (MyScrollView) rootView
				.findViewById(R.id.scrollView_exchange);

		scrollViewExchange
				.setOnScrollStoppedListener(new OnScrollStoppedListener() {
					public void onScrollStopped() {
						isMore = true;
						pageId++;

						if (ConstantValues.isScrolling)
							new APITask().execute();
						else {
							Toast.makeText(c, "no more result",
									Toast.LENGTH_LONG).show();
						}
					}
				});

		new APITask().execute();
		return rootView;
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
			startActivityForResult(new Intent(ListActivity.c,
					LoginActivity.class), 101);
			return true;
		}

		if (id == R.id.register) {
			startActivityForResult(new Intent(ListActivity.c,
					SignupActivity.class), 102);
			return true;
		}
		switch (item.getItemId()) {
		case android.R.id.home:
			// app icon in action bar clicked; go home
			((CouponActivity) ListActivity.c).onBackPressed();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private static class APITask extends AsyncTask<Void, Void, String> {
		private String RESULT = "OK";

		@Override
		protected void onPreExecute() {
			Log.d("f", "preexecute");
			progressDialog = ProgressDialog.show(getActivity(), "",
					"Loading...", true, false);
			ConstantValues.isBottomReached = false;
		}

		private Context getActivity() {
			// TODO Auto-generated method stub

			return ListActivity.c;
		}

		@Override
		protected String doInBackground(Void... arg0) {
			try {
				if (InternetConnectivity.isConnectedToInternet(getActivity())) {
					InputStream xml;
					DomParser domParser = new DomParser();

					ArrayList<NameValuePair> getcategory = apiFactory
							.getofferlist(
									Instancevalues.currentuser.getUserID(),
									pageId,
									Instancevalues.currentVendor.getVendorId());// (Instancevalues.currentcategoryname,
																				// Instancevalues.currentuser.getUserID(),pageId);
					xml = SingleToneClass.getInstance().getResponseFromServer(
							getcategory);
					domParser.parsegetofferlist(xml, isMore);

					RESULT = Instancevalues.Error_message;

					return RESULT;
				} else {
					SingleToneClass.getInstance().openInternetSettingsActivity(
							getActivity());
					return RESULT;
				}
			} catch (Exception ex) {
				return RESULT;
			}
		}

		@Override
		protected void onPostExecute(String result) {
			if (progressDialog.isShowing()) {
				progressDialog.dismiss();
				progressDialog = null;
				isMore = false;
				ConstantValues.isBottomReached = true;
			}

			linearLayout.removeAllViews();
			LayoutInflater inflater = LayoutInflater.from(getActivity());

			if (Instancevalues.VendorOfferlist != null) {

				ConstantValues.isBottomReached = true;
				for (int i = 0; i < Instancevalues.VendorOfferlist.size(); i++) {
					View item = inflater.inflate(R.layout.offerfragment, null);
					item.setTag(i);

					TextView txtViewKey = (TextView) item
							.findViewById(R.id.hotelname);
					txtViewKey.setText(Instancevalues.VendorOfferlist.get(i)
							.getShort_desc());

					TextView txtViewDate = (TextView) item
							.findViewById(R.id.city);
					txtViewDate.setText(""
							+ Instancevalues.VendorOfferlist.get(i)
									.getVendor_name());

					TextView txtViewPoint = (TextView) item
							.findViewById(R.id.rating);
					txtViewPoint.setText("Expire at "
							+ Instancevalues.VendorOfferlist.get(i)
									.getExpire_date()
							+ " remaining:"
							+ Instancevalues.VendorOfferlist.get(i)
									.getRemaining_redem());
					WebView w = (WebView) item.findViewById(R.id.img);

					w.setVisibility(View.GONE);
					txtViewKey.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Intent ia = new Intent(getActivity(),
									CouponActivity.class);

							getActivity().startActivity(ia);
						}
					});
					txtViewDate.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Intent ia = new Intent(getActivity(),
									CouponActivity.class);

							getActivity().startActivity(ia);
						}
					});
					
					txtViewPoint.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Intent ia = new Intent(getActivity(),
									CouponActivity.class);

							getActivity().startActivity(ia);
						}
					});
					
					item.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Intent ia = new Intent(getActivity(),
									CouponActivity.class);

							getActivity().startActivity(ia);
						}
					});
					item.setOnLongClickListener(new OnLongClickListener() {
						
						@Override
						public boolean onLongClick(View v) {
							// TODO Auto-generated method stub
							Intent ia = new Intent(getActivity(),
									CouponActivity.class);

							getActivity().startActivity(ia);
							return false;
						}
					});
				/*	item.setOnTouchListener(new View.OnTouchListener() {

						@Override
						public boolean onTouch(View v, MotionEvent event) {
							// TODO Auto-generated method stub
							int i = (Integer) v.getTag();
							switch (event.getAction()) {
							
							case MotionEvent.ACTION_DOWN:
								
								Intent ia = new Intent(getActivity(),
										CouponActivity.class);

								getActivity().startActivity(ia);
								break;
							case MotionEvent.ACTION_UP:

								// Instancevalues.currentVendor=Instancevalues.VendorOfferlist.get(i);//).getVendoradress()+"";
								
							
								 
						
								break;

							case MotionEvent.ACTION_MOVE:
							
								break;
							case MotionEvent.ACTION_CANCEL:
							
								break;
							}
							return true;
						}
					});
*/
					if (i % 2 != 0) {
						item.setBackgroundColor(Color.parseColor("#F5BCA9"));
					} else
						item.setBackgroundColor(Color.parseColor("#F2F2F2"));
					linearLayout.addView(item);
				}
			}

			else {
				ConstantValues.isBottomReached = false;

			}

		}
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
