package com.freegiftcard.payperclick.ptc.earncard.onlinemoney.freecard.earn.money;



import game.PasswActivity;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.NameValuePair;

import sponsorpay.Sponsorpayappstemplate;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;

import com.atomix.kurowiz.supports.ConstantValues;
import com.atomix.kurowiz.supports.InternetConnectivity;
import com.atomix.kurowiz.supports.SingleToneClass;
import com.atomix.kurowiz.xmlparser.APIFactory;
import com.atomix.kurowiz.xmlparser.DomParser;
import com.freegiftcard.payperclick.ptc.earncard.onlinemoney.freecard.earn.money.R;


/**
 * @author Adil Soomro
 *
 */


public class TabSample extends  FragmentActivity implements OnCheckedChangeListener  {
	/** Called when the activity is first created. */
	private RadioGroup radioGroup;
	private RadioButton radioBtnTop, radioBtnExchange, radioBtnMyPage, radioBtnGacha;
	public static RadioButton radioBtnSave;
	
	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;
	
	public static String  deviceId = "";
	private ProgressDialog progressDialog;
	
	private APIFactory apiFactory;
	
	
	
	// For GCM
	
	public static final String EXTRA_MESSAGE = "message";
    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    
    String SENDER_ID = "785263536103";
    
    static final String TAG = "MainActivity";
   
    private RelativeLayout relativeAd;
    	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		WindowManager.LayoutParams attrs = getWindow().getAttributes(); 
		attrs.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN; 
		getWindow().setAttributes(attrs);
		/*
		relativeAd = (RelativeLayout) findViewById(R.id.relative_layout_admob);
		adView = new AdstirWebView(MainActivity.this, "MEDIA-7b0a2083", 1, 30);
		relativeAd.addView(adView);*/
		
		apiFactory = new APIFactory();
		
		
		
		fragmentManager = getSupportFragmentManager();
		
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		radioGroup.setOnCheckedChangeListener(this);
		
		radioBtnTop = (RadioButton) findViewById(R.id.radio_btn_top);
		//radioBtnTop.setButtonDrawable(R.drawable.p);
		radioBtnExchange = (RadioButton) findViewById(R.id.radio_btn_exchange);
		radioBtnGacha = (RadioButton) findViewById(R.id.radio_btn_gacha);
		radioBtnSave = (RadioButton) findViewById(R.id.radio_btn_save);
	//	radioBtnMyPage = (RadioButton) findViewById(R.id.radio_btn_mypage);
		
		deviceId = "" + android.provider.Settings.Secure.getString(this.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
		
		if (!GetNetworkStatus.isNetworkAvailable(this))
		finish();
		
		new APITask().execute();
	}
	String regid;
	 private class APITask extends AsyncTask<Void, Void, String> 
		{
			private String RESULT = "OK";
			
			@Override
			protected void onPreExecute() 
			{
				progressDialog = ProgressDialog.show(TabSample.this, "", "Loading...", true, false);
				
//				progressDialog = new ProgressDialog(MainActivity.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
//				progressDialog.setCancelable(false);
//				progressDialog.show();
//				
//				TextView tv1 = (TextView) progressDialog.findViewById(android.R.id.message);  
//				tv1.setTextSize(20);
//				tv1.setTextColor(android.graphics.Color.rgb(38, 17, 0));
//				tv1.setText("Loading......");
			}

			@Override
			protected String doInBackground(Void... arg0) 
			{
				try 
				{
					if (InternetConnectivity.isConnectedToInternet(TabSample.this)) 
					{
						InputStream xml;
						DomParser domParser = new DomParser();
						
						ArrayList<NameValuePair> nvp2 = apiFactory.user_info_regist(ConstantValues.FUNCTION_ID_2, "com.packages", deviceId, deviceId, "3");
						xml = SingleToneClass.getInstance().getResponseFromServer(nvp2);
						domParser.parseAPI2(xml);
						
						ArrayList<NameValuePair> nvp1 = apiFactory.get_user_info(ConstantValues.FUNCTION_ID_1, deviceId, SingleToneClass.getInstance().getDeviceInfoList().get(0).getUserId());
						xml = SingleToneClass.getInstance().getResponseFromServer(nvp1);
						domParser.parseAPI1(xml);
						
						
						/*ArrayList<NameValuePair> nvp32 = apiFactory.set_device_token(ConstantValues.FUNCTION_ID_32, SingleToneClass.getInstance().getDeviceInfoList().get(0).getUserId(), regid);
						xml = SingleToneClass.getInstance().getResponseFromServer(nvp32);
						
						ArrayList<NameValuePair> nvp46 = apiFactory.get_new_ad(ConstantValues.FUNCTION_ID_46, SingleToneClass.getInstance().getUserInfoList().get(0).getUserId());
						xml = SingleToneClass.getInstance().getResponseFromServer(nvp46);
						domParser.parseAPI46(xml);
						
						ArrayList<NameValuePair> nvp35 = apiFactory.get_app_maintenance(ConstantValues.FUNCTION_ID_35, SingleToneClass.getInstance().getUserInfoList().get(0).getUserId());
						xml = SingleToneClass.getInstance().getResponseFromServer(nvp35);
						domParser.parseAPI35(xml)*/;
						
						return RESULT;
					}
					else 
					{
						SingleToneClass.getInstance().openInternetSettingsActivity(TabSample.this);
						return RESULT;
					}
				}
				catch (Exception ex) 
				{
					//Log.e("APITask:", ex.getMessage());
					return RESULT;
				}
			}

			@Override
			protected void onPostExecute(String result) 
			{
				if(progressDialog.isShowing())
					progressDialog.dismiss();
		
				
				fragmentTransaction = fragmentManager.beginTransaction();
				ArrowsActivity topFragment = new ArrowsActivity();
				fragmentTransaction.replace(R.id.relative_layout_main_fragment_area, topFragment);
				fragmentTransaction.commitAllowingStateLoss();
				
//				try
//				{
//					int badgeValue = Integer.parseInt(SingleToneClass.getInstance().getBadgeViewValue());
//					badgeViewRadioButton.setText(""+badgeValue);
//					badgeViewRadioButton.show();
//				}
//				catch(NumberFormatException exception)
//				{
//					Log.i("Number format exception", "_____________");
//				}
			}
		}
	 
	 @Override
		public void onCheckedChanged(RadioGroup group, int checkedId) 
		{
			switchTab();
		}

		private void switchTab() 
		{
			int selectedTab = radioGroup.getCheckedRadioButtonId();
			fragmentTransaction = fragmentManager.beginTransaction();
			
			switch (selectedTab) 
			{
				case R.id.radio_btn_top:
					radioBtnTop.setButtonDrawable(R.drawable.gift_active);
					radioBtnExchange.setButtonDrawable(R.drawable.order_inactive);
					radioBtnGacha.setButtonDrawable(R.drawable.news_inactive);
					radioBtnSave.setButtonDrawable(R.drawable.game_inactive);
				//	radioBtnMyPage.setButtonDrawable(R.drawable.butoom_mypage_off);
					
					ArrowsActivity topFragment = new ArrowsActivity();
					fragmentTransaction.replace(R.id.relative_layout_main_fragment_area, topFragment);
					fragmentTransaction.commit();
					
					break;
					
				case R.id.radio_btn_exchange:
					radioBtnTop.setButtonDrawable(R.drawable.gift_inactive);
					radioBtnExchange.setButtonDrawable(R.drawable.order_active);
					radioBtnGacha.setButtonDrawable(R.drawable.news_inactive);
					radioBtnSave.setButtonDrawable(R.drawable.game_inactive);
				//	radioBtnMyPage.setButtonDrawable(R.drawable.butoom_mypage_off);*/
				//	radioBtnTop.setButtonDrawable(R.drawable.p);
					OptionsActivity exchangeFragment = new OptionsActivity();
					fragmentTransaction.replace(R.id.relative_layout_main_fragment_area, exchangeFragment);
					fragmentTransaction.commit();
					break;
					
				case R.id.radio_btn_gacha:
					radioBtnTop.setButtonDrawable(R.drawable.gift_inactive);
					radioBtnExchange.setButtonDrawable(R.drawable.order_inactive);
					radioBtnGacha.setButtonDrawable(R.drawable.news_active);
					radioBtnSave.setButtonDrawable(R.drawable.game_inactive);
					GiftCodeActivity gachaFragment = new GiftCodeActivity();
					fragmentTransaction.replace(R.id.relative_layout_main_fragment_area, gachaFragment);
					fragmentTransaction.commit();
					break;
					
				case R.id.radio_btn_save:
					sponsorpay.Sponsorpayappstemplate sp = new Sponsorpayappstemplate(
							this);

					if (GetNetworkStatus.isNetworkAvailable(this))
						sp.onLaunchOfferwallClick();
					else {
						Toast.makeText(this, "Network need ", Toast.LENGTH_LONG)
								.show();
					}
					radioBtnTop.setButtonDrawable(R.drawable.gift_inactive);
					radioBtnExchange.setButtonDrawable(R.drawable.order_inactive);
					radioBtnGacha.setButtonDrawable(R.drawable.news_inactive);
					radioBtnSave.setButtonDrawable(R.drawable.game_active);
					/*PasswActivity saveFragment = new PasswActivity();
					fragmentTransaction.replace(R.id.relative_layout_main_fragment_area, saveFragment);
					fragmentTransaction.commit();*/
					break;
				
		
		
				default:
					break;
			}
		}
	/*private void setTabs()
	{
		addTab("Home", R.drawable.tab_home, ArrowsActivity.class);
		addTab("Search", R.drawable.tab_search, OptionsActivity.class);
		
		addTab("Home", R.drawable.tab_home, ArrowsActivity.class);
		addTab("Search", R.drawable.tab_search, OptionsActivity.class);
	}
	
	private void addTab(String labelId, int drawableId, Class<?> c)
	{
		TabHost tabHost = getTabHost();
		Intent intent = new Intent(this, c);
		TabHost.TabSpec spec = tabHost.newTabSpec("tab" + labelId);	
		
		View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, getTabWidget(), false);
		TextView title = (TextView) tabIndicator.findViewById(R.id.title);
		title.setText(labelId);
		ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
		icon.setImageResource(drawableId);
		
		spec.setIndicator(tabIndicator);
		spec.setContent(intent);
		tabHost.addTab(spec);
	}*/
		private boolean isPlay = false, isAnimationResult = false;

		

		private String gachaAnimationPoint = "";

		private class APITask11 extends AsyncTask<Void, Void, String> 
		{
			private String RESULT = "OK";

			@Override
			protected void onPreExecute() 
			{
				progressDialog = ProgressDialog.show(getActivity(), "", "Loading...", true, false);
			}

			private Context getActivity() {
				// TODO Auto-generated method stub
				return null;
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

						if (isAnimationResult) 
						{
							ArrayList<NameValuePair> nvp24 = apiFactory.gacha_play(ConstantValues.FUNCTION_ID_24, SingleToneClass.getInstance().getUserInfoList().get(0).getUserId());
							xml = SingleToneClass.getInstance().getResponseFromServer(nvp24);
							gachaAnimationPoint = domParser.parseAPI24(xml);
						} 
						else 
						{
							ArrayList<NameValuePair> nvp23 = apiFactory.gacha_info(ConstantValues.FUNCTION_ID_23, SingleToneClass.getInstance().getUserInfoList().get(0).getUserId());
							xml = SingleToneClass.getInstance().getResponseFromServer(nvp23);
							isPlay = domParser.parseAPI23(xml);
						}

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
					Log.e("APITask:", ex.getMessage());
					return RESULT;
				}
			}

			@Override
			protected void onPostExecute(String result) 
			{
				if (progressDialog.isShowing())
					progressDialog.dismiss();

				if (isAnimationResult) 
				{
					//Log.i("Point is ", "__________" + gachaAnimationPoint);
				//	dialog.dismiss();

					isAnimationResult = false;
					///showGachaResult();
				} 
				else 
				{
					if (!isPlay) 
					{
					//	relativeLayoutWebview.setVisibility(View.VISIBLE);
						//relativeLayoutAnimaiton.setVisibility(View.INVISIBLE);
					}
				}

			}
		}
}