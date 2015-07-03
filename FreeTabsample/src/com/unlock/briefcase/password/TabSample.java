package com.unlock.briefcase.password;



import game.PasswActivity;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.NameValuePair;

import sponsorpay.Sponsorpayappstemplate;
import sponsorpay.User;
import xoxo.sound.SoundManager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
import com.ironsource.mobilcore.CallbackResponse;
import com.ironsource.mobilcore.MobileCore;
import com.ironsource.mobilcore.CallbackResponse.TYPE;
import com.ironsource.mobilcore.MobileCore.AD_UNITS;
import com.ironsource.mobilcore.MobileCore.LOG_TYPE;
import com.ironsource.mobilcore.OnReadyListener;
import com.unlock.briefcase.password.R;


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

	    MobileCore.init(this,"8YKF89U9YHXZUIMHFEQ0UDC1T9TOY", LOG_TYPE.DEBUG);
		 MobileCore.getSlider().setContentViewWithSlider(this, R.layout.activity_main);
		 final Context c=this;
		 MobileCore.setStickeezReadyListener(new OnReadyListener() {
			    @Override
			    public void onReady(AD_UNITS adUnit) {
				if (adUnit == AD_UNITS.STICKEEZ) {
				    MobileCore.showStickee((Activity) c);
				}
			    }
			});
			
		WindowManager.LayoutParams attrs = getWindow().getAttributes(); 
		attrs.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN; 
		getWindow().setAttributes(attrs);
		/*
		relativeAd = (RelativeLayout) findViewById(R.id.relative_layout_admob);
		adView = new AdstirWebView(MainActivity.this, "MEDIA-7b0a2083", 1, 30);
		relativeAd.addView(adView);*/
		//SoundManager s= new ;
		SoundManager.initSounds(this);
		apiFactory = new APIFactory();
		
		usercoinupdate();
		sponsorpayupdate();
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
		
		fragmentTransaction = fragmentManager.beginTransaction();
		PasswActivity topFragment = new PasswActivity();
		fragmentTransaction.replace(R.id.relative_layout_main_fragment_area, topFragment);
		fragmentTransaction.commitAllowingStateLoss();
		radioBtnSave.setChecked(true);
		
		
	}
	String regid;
	
				
	  private void usercoinupdate() {
			// TODO Auto-generated method stub
			  SharedPreferences prefs = this.getSharedPreferences(
		    	      "com.example.app", Context.MODE_PRIVATE);
		   User.Coins_points= prefs.getLong("userpoint", 120);
		
		}
		  private void usercoincomit() {
				// TODO Auto-generated method stub
				  SharedPreferences prefs = this.getSharedPreferences(
			    	      "com.example.app", Context.MODE_PRIVATE);
			Editor ed=prefs.edit();
			ed.putLong("userpoint",User.Coins_points);
			ed.commit();
			   
			}
		private void sponsorpayupdate() {
			// TODO Auto-generated method stub
			  final sponsorpay.Sponsorpayappstemplate sp = new Sponsorpayappstemplate(this);
			  sp.onRequestNewCoinsClick();
			  usercoincomit() ;
			  
		}
			
@Override
public void onBackPressed() {
	usercoincomit();
	// TODO Auto-generated method stub
	//super.onBackPressed();
	final Context c= this;
	MobileCore.showOfferWall((Activity) c, new CallbackResponse() {
		@Override
		public void onConfirmation(TYPE arg0) {
			((Activity) c).finish();
		}
		});
//	MobileCore.showOfferWall(*Your Activity*, new CallbackResponse() {@Override public void onConfirmation(TYPE arg0) { .finish(); } });
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
					sponsorpayupdate() ;
					ArrowsActivity topFragment = new ArrowsActivity();
					fragmentTransaction.replace(R.id.relative_layout_main_fragment_area, topFragment);
					fragmentTransaction.commit();
					
					break;
					
				case R.id.radio_btn_exchange:
					sponsorpay.Sponsorpayappstemplate sp = new Sponsorpayappstemplate(
							this);

					if (GetNetworkStatus.isNetworkAvailable(this))
						sp.onLaunchOfferwallClick();
					else {
						Toast.makeText(this, "Network need ", Toast.LENGTH_LONG)
								.show();
					}
				/*	radioBtnTop.setButtonDrawable(R.drawable.gift_inactive);
					radioBtnExchange.setButtonDrawable(R.drawable.order_active);
					radioBtnGacha.setButtonDrawable(R.drawable.news_inactive);
					radioBtnSave.setButtonDrawable(R.drawable.game_inactive);
					OptionsActivity exchangeFragment = new OptionsActivity();
					fragmentTransaction.replace(R.id.relative_layout_main_fragment_area, exchangeFragment);
					fragmentTransaction.commit();*/
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
					radioBtnTop.setButtonDrawable(R.drawable.gift_inactive);
					radioBtnExchange.setButtonDrawable(R.drawable.order_inactive);
					radioBtnGacha.setButtonDrawable(R.drawable.news_inactive);
					radioBtnSave.setButtonDrawable(R.drawable.game_active);
					PasswActivity saveFragment = new PasswActivity();
					fragmentTransaction.replace(R.id.relative_layout_main_fragment_area, saveFragment);
					fragmentTransaction.commit();
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