package com.unlock.briefcase.password;

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
import com.unlock.briefcase.password.R;


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
public class OptionsActivity extends Fragment implements OnClickListener{
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
		
		
	//	imgBtnTop = (ImageButton) view.findViewById(R.id.img_btn_top);
	//	imgBtnTop.setOnClickListener(this);
		
		linearLayout = (LinearLayout) view.findViewById(R.id.linear_layout_footer);
		
		txtViewExchangePoint = (TextView) view.findViewById(R.id.txt_view_exchange);
		txtViewExchangePoint.setText(DomParser.userInfo.getUserPoint()+ " pt");
		
		//imgBtnExchange = (ImageButton) view.findViewById(R.id.img_btn_exchange);
		//imgBtnExchange.setOnClickListener(this);
		
		scrollViewExchange = (MyScrollView) view.findViewById(R.id.scrollView_exchange);
		
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
					
					//ArrayList<NameValuePair> nvp28 = apiFactory.get_buy_gift_list(ConstantValues.FUNCTION_ID_28, DomParser.userInfo.getUserId(), Integer.toString(pageId));
				//	xml = SingleToneClass.getInstance().getResponseFromServer(nvp28);
				//	domParser.parseAPI28(xml, isMore);
					
					ArrayList<NameValuePair> nvp31 = apiFactory.get_gift_ticket_info(ConstantValues.FUNCTION_ID_31,  DomParser.userInfo.getUserId());
					xml = SingleToneClass.getInstance().getResponseFromServer(nvp31);
					domParser.parseAPI31(xml);
					
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
			if(SingleToneClass.giftTicketInfoList != null)
			{
			//Log.d("sdf", "1");	
				ConstantValues.isBottomReached = true;
				for(int i = 0; i < SingleToneClass.giftTicketInfoList.size(); i++)
				{//Log.d("sdf", "2");	
					View item = inflater.inflate(R.layout.exchange_gift_row, null);
					item.setTag(i);
					
					TextView txtViewKey = (TextView) item.findViewById(R.id.txt_view_key);
					txtViewKey.setText(SingleToneClass.getInstance().getGiftTicketInfoList().get(i).getTicketName());
					
					TextView txtViewDate = (TextView) item.findViewById(R.id.txt_view_date);
					txtViewDate.setText("Required Point: "+SingleToneClass.getInstance().getGiftTicketInfoList().get(i).getGiftPoint());
					
					TextView txtViewPoint = (TextView) item.findViewById(R.id.txt_view_point);
					txtViewPoint.setText("Place Order");
					
					item.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							int i = (Integer) arg0.getTag();
							gifttype=SingleToneClass.getInstance().getGiftTicketInfoList().get(i).getGiftType()+"";
							giftid=i;
							showGiftMsgPopUp();
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
	private void showGiftMsgPopUp() 
	{
		AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
		builder1.setCancelable(false);
		if(Integer.parseInt(DomParser.userInfo.getUserPoint()) < Integer.parseInt(SingleToneClass.getInstance().getGiftTicketInfoList().get(giftid).getGiftPoint()))
		{
			builder1.setMessage("Exchange do not have enough points.");
			builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() 
	        {
	            public void onClick(DialogInterface dialog, int id) 
	            {
	                dialog.cancel();
	            }
	        });
		}
		else
		{
			builder1.setMessage("Do you want to request the issue?");
			builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() 
	        {
	            public void onClick(DialogInterface dialog, int id) 
	            {
	                dialog.cancel();
	                new APITask30().execute();
	            }
	        });
	        
	        builder1.setNegativeButton("No", new DialogInterface.OnClickListener() 
	        {
	            public void onClick(DialogInterface dialog, int id) 
	            {
	                dialog.cancel();
	            }
	        });
		}
        
        builder1.show();
	}
	
	private class APITask30 extends AsyncTask<Void, Void, String> 
	{
		private String RESULT = "OK";

		@Override
		protected void onPreExecute() 
		{
			progressDialog = ProgressDialog.show(getActivity(), "", "Loading...", true, false);
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
					
					ArrayList<NameValuePair> nvp30 = apiFactory.user_gift_code_purchase(ConstantValues.FUNCTION_ID_30, SingleToneClass.getInstance().getDeviceInfoList().get(0).getUserId(), gifttype);
					xml = SingleToneClass.getInstance().getResponseFromServer(nvp30);
					giftMsg = domParser.parseAPI30(xml);
				//Log.d("sdf", ""+ConstantValues.FUNCTION_ID_1+TabSample.deviceId+ DomParser.deviceInfo.getUserId());
					ArrayList<NameValuePair> nvp1 = apiFactory.get_user_info(ConstantValues.FUNCTION_ID_1,TabSample.deviceId, DomParser.deviceInfo.getUserId());
					xml = SingleToneClass.getInstance().getResponseFromServer(nvp1);
					domParser.parseAPI1(xml);
					
					return giftMsg;
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
			}
			txtViewExchangePoint.setText(DomParser.userInfo.getUserPoint());
			
			if(result=="")
				result="Order Placed";
			AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
			builder1.setCancelable(true);
			
			builder1.setMessage(result);
			builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() 
	        {
	            public void onClick(DialogInterface dialog, int id) 
	            {
	                dialog.cancel();
	            }
	        });
			
			builder1.show();
		}
	}
}
