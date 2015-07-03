package app.tabsample;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.NameValuePair;

import com.atomix.kurowiz.supports.ConstantValues;
import com.atomix.kurowiz.supports.InternetConnectivity;
import com.atomix.kurowiz.supports.MyScrollView;
import com.atomix.kurowiz.supports.MyScrollView.OnScrollStoppedListener;
import com.atomix.kurowiz.supports.SingleToneClass;

import com.atomix.kurowiz.xmlparser.APIFactory;
import com.atomix.kurowiz.xmlparser.DomParser;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author Adil Soomro
 *
 */
public class GiftCodeActivity extends Fragment implements OnClickListener{
private TextView txtViewExchangePoint;
	
	private ProgressDialog progressDialog;
	private APIFactory apiFactory;
	
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
		View view = inflater.inflate(R.layout.exchange_fragment, container, false);
		
		apiFactory = new APIFactory();
		
		
	/*	imgBtnTop = (ImageButton) view.findViewById(R.id.img_btn_top);
		imgBtnTop.setOnClickListener(this);
		imgBtnTop.setVisibility(View.GONE);*/
		linearLayout = (LinearLayout) view.findViewById(R.id.linear_layout_footer);
		
		txtViewExchangePoint = (TextView) view.findViewById(R.id.txt_view_exchange);
		txtViewExchangePoint.setText("Sent Us Mail at");
		txtViewExchangePoint = (TextView) view.findViewById(R.id.textView);
		txtViewExchangePoint.setText("News");
		imgBtnExchange = (ImageButton) view.findViewById(R.id.img_btn_exchange);
		imgBtnExchange.setOnClickListener(this);
		imgBtnExchange.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.email));
		scrollViewExchange = (MyScrollView) view.findViewById(R.id.scrollView_exchange);
		
		scrollViewExchange.setOnScrollStoppedListener(new OnScrollStoppedListener()
		{
			public void onScrollStopped() 
			{
				isMore = true;
				pageId++;
				//new APITask().execute();
	        }
		});
		
		new APITask().execute();
	/*	
		if(SingleToneClass.getInstance().isAppMaintenance())
			SingleToneClass.getInstance().openDialog("メンテナンス中", getActivity());
	*/	
		return view;
	}

	@Override
	public void onClick(View v) 
	{
		switch (v.getId()) 
		{
			
			
	
			default:
				break;
		}
	}

	private void showExchangeHowToUse() 
	{
	//	SingleToneClass.getInstance().showPopUpTutorial(getActivity(), howToUseImageArray);
	}

	private void showExchangeTutorial() 
	{
		//SingleToneClass.getInstance().showPopUpTutorial(getActivity(), tutorialImageArray);
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
					ArrayList<NameValuePair> nvp20 = apiFactory.get_news(ConstantValues.FUNCTION_ID_20, DomParser.userInfo.getUserId());
					xml = SingleToneClass.getInstance().getResponseFromServer(nvp20);
					domParser.parseAPI20(xml);

					
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
			//	isMore = false;
				ConstantValues.isBottomReached = true;
			}
			
			linearLayout.removeAllViews();
			LayoutInflater inflater = LayoutInflater.from(getActivity());
			
			//SingleToneClass.getInstance();
			if(SingleToneClass.announcementInfoList != null)
			{
			Log.d("sdf", "1");	
				ConstantValues.isBottomReached = true;
				for(int i = 0; i < SingleToneClass.announcementInfoList.size(); i++)
				{Log.d("sdf", "2");	
					View item = inflater.inflate(R.layout.exchange_gift_row, null);
					item.setTag(i);
					
					TextView txtViewKey = (TextView) item.findViewById(R.id.txt_view_key);
					txtViewKey.setText(SingleToneClass.getInstance().getAnnouncementInfoList().get(i).getTitle());
					
					TextView txtViewDate = (TextView) item.findViewById(R.id.txt_view_date);
					txtViewDate.setText(SingleToneClass.getInstance().getAnnouncementInfoList().get(i).getContent());
					
					TextView txtViewPoint = (TextView) item.findViewById(R.id.txt_view_point);
					//txtViewPoint.setText("$"+SingleToneClass.getInstance().getGiftTicketInfoList().get(i).getGiftPoint());
					txtViewPoint.setVisibility(View.GONE);
				
					linearLayout.addView(item);
				}
			}
			
			else
			{
				ConstantValues.isBottomReached = false;
				Button btn = new Button(getActivity());
				btn.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
				btn.setText("There is no News");
				
				linearLayout.addView(btn);
			}
			
		}
	}

}
