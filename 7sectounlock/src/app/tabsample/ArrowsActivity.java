package app.tabsample;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.NameValuePair;

import sponsorpay.Sponsorpayappstemplate;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atomix.kurowiz.supports.ConstantValues;
import com.atomix.kurowiz.supports.InternetConnectivity;
import com.atomix.kurowiz.supports.MyScrollView;
import com.atomix.kurowiz.supports.SingleToneClass;
import com.atomix.kurowiz.supports.UserInfo;
import com.atomix.kurowiz.supports.GiftInfo;
import com.atomix.kurowiz.supports.MyScrollView.OnScrollStoppedListener;
import com.atomix.kurowiz.xmlparser.APIFactory;
import com.atomix.kurowiz.xmlparser.DomParser;

/**
 * @author Adil Soomro
 *
 */
public class ArrowsActivity extends Fragment implements OnClickListener {
	private ProgressDialog progressDialog;
	private String deviceId = "";
	private APIFactory apiFactory;
	String regid;
	
	
	private LinearLayout linearLayout;
	static public String gifttype="";
	static public int giftid=0;
	private MyScrollView scrollViewExchange;
private ImageView imgViewHowToUse;
private ImageButton imgBtnTutorial, imgBtnTop, imgBtnExchange;
	private int pageId = 1;
	private boolean isMore = false;
	
	private String giftMsg = "";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		View view = inflater.inflate(R.layout.arrowspage, container, false);

		apiFactory = new APIFactory();
		final Context c = getActivity();// .getApplicationContext();
		Button b = (Button) view.findViewById(R.id.button1);
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				sponsorpay.Sponsorpayappstemplate sp = new Sponsorpayappstemplate(
						c);

				if (GetNetworkStatus.isNetworkAvailable(c))
					sp.onLaunchOfferwallClick();
				else {
					Toast.makeText(c, "Network need ", Toast.LENGTH_LONG)
							.show();
				}
			}
		});
		TextView t = (TextView) view.findViewById(R.id.textView1);

		t.setText("Userid "
				+ SingleToneClass.getInstance().getDeviceInfoList().get(0)
						.getUserId());

		TextView at = (TextView) view.findViewById(R.id.textView2);
		// SingleToneClass.getInstance().getUserInfoList().get(0).getUserPoint()
		try {
			at.setText( DomParser.userInfo.getUserPoint() +"pt");
		} catch (NullPointerException e) {
		}
		

		
		
		//imgBtnTop = (ImageButton) view.findViewById(R.id.img_btn_top);
	//	imgBtnTop.setOnClickListener(this);
		
		linearLayout = (LinearLayout) view.findViewById(R.id.linear_layout_footer);
		
	
		
		scrollViewExchange = (MyScrollView) view.findViewById(R.id.scrollView_exchange);
		 SingleToneClass.giftInfoList = new ArrayList<GiftInfo>();
		scrollViewExchange.setOnScrollStoppedListener(new OnScrollStoppedListener()
		{
			public void onScrollStopped() 
			{  
				isMore = true;
				pageId++;
				new APITask().execute();
	        }
		});
		
		new APITask().execute();
		return view;
	}
	private class APITask extends AsyncTask<Void, Void, String> 
	{
		private String RESULT = "OK";

		@Override
		protected void onPreExecute() 
		{
			progressDialog = ProgressDialog.show(getActivity(), "", "Loading...", true, false);
			ConstantValues.isBottomReached = false;
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
					
					ArrayList<NameValuePair> nvp28 = apiFactory.get_buy_gift_list(ConstantValues.FUNCTION_ID_28, DomParser.userInfo.getUserId(), Integer.toString(pageId));
				    xml = SingleToneClass.getInstance().getResponseFromServer(nvp28);
					domParser.parseAPI28(xml, isMore);
					
				//	ArrayList<NameValuePair> nvp31 = apiFactory.get_gift_ticket_info(ConstantValues.FUNCTION_ID_31,  DomParser.userInfo.getUserId());
					//xml = SingleToneClass.getInstance().getResponseFromServer(nvp31);
					//domParser.parseAPI31(xml);
					
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
			
			//SingleToneClass.getInstance();
			if(SingleToneClass.giftInfoList != null)
			{
			Log.d("sdf", "1");	
				ConstantValues.isBottomReached = true;
				for(int i = 0; i < SingleToneClass.giftInfoList.size(); i++)
				{Log.d("sdf", "2");	
					View item = inflater.inflate(R.layout.exchange_gift_row, null);
					item.setTag(i);
					
					TextView txtViewKey = (TextView) item.findViewById(R.id.txt_view_key);
					txtViewKey.setText("GiftCode : "+SingleToneClass.getInstance().getGiftInfoList().get(i).getGiftCode());
					
					TextView txtViewDate = (TextView) item.findViewById(R.id.txt_view_date);
					txtViewDate.setText(" copy to clipboard");
					
					TextView txtViewPoint = (TextView) item.findViewById(R.id.txt_view_point);
					txtViewPoint.setText(SingleToneClass.getInstance().getGiftInfoList().get(i).getPoint());
					
				item.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							int i = (Integer) arg0.getTag();
						String	stringYouExtracted = SingleToneClass.getInstance().getGiftInfoList().get(i).getGiftCode();
							
							if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
							    android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
							    clipboard.setText(stringYouExtracted);
							} else {
							    android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
							    android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", stringYouExtracted);
							            clipboard.setPrimaryClip(clip);
							}
							Toast.makeText(getActivity(), "Copied to Clipboard", Toast.LENGTH_LONG).show();
						}
					});
					linearLayout.addView(item);
				}
			}
			
			else
			{
				ConstantValues.isBottomReached = false;
				Button btn = new Button(getActivity());
				btn.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
				btn.setText("There is no history");
				
				linearLayout.addView(btn);
			}
			
		}
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}