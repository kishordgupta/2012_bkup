package com.unlock.briefcase.password;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.NameValuePair;

import sponsorpay.Sponsorpayappstemplate;
import sponsorpay.User;
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
import com.unlock.briefcase.password.R;

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

		t.setText("Current Point  : ");

		TextView at = (TextView) view.findViewById(R.id.textView2);
		// SingleToneClass.getInstance().getUserInfoList().get(0).getUserPoint()
		try {
			at.setText(User.Coins_points+" "+"pt");
		} catch (NullPointerException e) {
		}
		

		
		
		//imgBtnTop = (ImageButton) view.findViewById(R.id.img_btn_top);
	//	imgBtnTop.setOnClickListener(this);
		
		linearLayout = (LinearLayout) view.findViewById(R.id.linear_layout_footer);
		
	
	
		return view;
	}
@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}