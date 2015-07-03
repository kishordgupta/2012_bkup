package com.siliconorchard.hoteldealsfinder;

import java.io.InputStream;
import java.util.ArrayList;

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
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atomix.kurowiz.supports.ConstantValues;
import com.atomix.kurowiz.supports.GiftInfo;
import com.atomix.kurowiz.supports.InternetConnectivity;
import com.atomix.kurowiz.supports.MyScrollView;
import com.atomix.kurowiz.supports.MyScrollView.OnScrollStoppedListener;
import com.atomix.kurowiz.supports.SingleToneClass;
import com.atomix.kurowiz.supports.Urlmaker;
import com.atomix.kurowiz.xmlparser.DomParser;

public class ListActivity extends ActionBarActivity {
	
	private static ProgressDialog progressDialog;
//	private APIFactory apiFactory;
	public static Context c=null;
	private static LinearLayout linearLayout;
	static public String gifttype="";
	static public int giftid=0;
	private static MyScrollView scrollViewExchange;
	private static int pageId = 1;
	private static boolean isMore = false;
	static public ArrayList<GiftInfo> arrayList = new ArrayList<GiftInfo>();
	private String giftMsg = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	    arrayList = new ArrayList<GiftInfo>();
    	if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	/*	if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		*/
	//	apiFactory = new APIFactory();
		
    	 c=this;
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
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

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
					Urlmaker.pag=Urlmaker.pag+1;
					Urlmaker.urlmaker();
					if(ConstantValues.isScrolling)
				   new  APITask().execute();
					else
					{Toast.makeText(c, "no more result", Toast.LENGTH_LONG).show();}
		        }
			});
			return rootView;
		}
	}
	
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
			
			return ListActivity.c;
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
					
					//ArrayList<NameValuePair> nvp28 = apiFactory.get_buy_gift_list(ConstantValues.FUNCTION_ID_28, "5", Integer.toString(pageId));
					xml = SingleToneClass.getInstance().getResponseFromServer();
					domParser.parseAPI28(xml, isMore);
					
	/*		ArrayList<NameValuePair> nvp31 = apiFactory.get_gift_ticket_info(ConstantValues.FUNCTION_ID_31,  DomParser.userInfo.getUserId());
					xml = SingleToneClass.getInstance().getResponseFromServer(nvp31);
					domParser.parseAPI31(xml);
	*/				
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
			
			if(arrayList != null)
			{
		Log.d("sdf", "2 "+arrayList.size()+" " );	
				ConstantValues.isBottomReached = true;
				for(int i = 0; i < arrayList.size(); i++)
				{Log.d("sdf", "3");	
					View item = inflater.inflate(R.layout.exchange_gift_row, null);
					item.setTag(i);
					
					TextView txtViewKey = (TextView) item.findViewById(R.id.hotelname);
					txtViewKey.setText(arrayList.get(i).getHotel_name());
					
					TextView txtViewDate = (TextView) item.findViewById(R.id.city);
					txtViewDate.setText(""+arrayList.get(i).getHotel_address());
					
					TextView txtViewPoint = (TextView) item.findViewById(R.id.rating);
					txtViewPoint.setText("Star: "+arrayList.get(i).getHotel_star()+" price :"+ arrayList.get(i).getPrices_from());
				String S= 	"<html> <head> <title>banner</title> </head><body>   <img src=";
				S=S+ "\""+arrayList.get(i).getImages()+"\"" +"alt=\"\"width=\"100%\" height=\"100%\"/></a></body></html>";

					WebView w = (WebView)item.findViewById(R.id.img);
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
								gifttype=arrayList.get(i).getHotel_link()+"";
								giftid=i;
								Intent ia = new Intent(Intent.ACTION_VIEW);
								ia.setData(Uri.parse(gifttype));
								getActivity().startActivity(ia);
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
							gifttype=arrayList.get(i).getHotel_link()+"";
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
