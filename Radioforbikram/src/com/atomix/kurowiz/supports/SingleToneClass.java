package com.atomix.kurowiz.supports;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Html;
import android.util.Log;
import android.util.Xml.Encoding;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;

public class SingleToneClass {

	public static SingleToneClass instance;
	
	public static  String SERVER_URL = "http://xmlfeed.laterooms.com/index.aspx?aid=5854&rtype=4&kword=london&lim=20&night=2&sdate=2014-05-15&pag=1";
	
	public static ArrayList<GiftInfo> giftInfoList;
	public static ArrayList<GiftTicketInfo> giftTicketInfoList;
	
	public static String dailyChancePoint = "";
	public static String dailyGamePoint = "";
	public static String setInviteDisplayImage = "";
	public static String scratchPoint = "";
	public static String badgeViewValue = "";
	public boolean appMaintenance = false;
	
	private ImageButton imgBtnRightArrow, imgBtnLeftArrow;
	private int pageCurrentIndex = 0, arrayLenght = 0;
	private ViewPager viewPager;
	
	private boolean isScrolling = false;
	private int cursorIndex = 0;
	
	private SingleToneClass() 
	{
		
	}

	public static SingleToneClass getInstance() 
	{
		if (instance == null) 
		{
			instance = new SingleToneClass();
		}
		return instance;
	}

	public static void destroyInstance() 
	{
		instance = null;
	}
	
	public void openInternetSettingsActivity(final Context context)
	{
		final AlertDialog.Builder alert = new AlertDialog.Builder(context);
		alert.setTitle("Internet Problem");
		alert.setMessage("No internet connection. Please connect to a network first.");
		alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) 
			{
				context.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
			}
		});

		alert.show();
	}
	
	
	public InputStream getResponseFromServer()
	{	 
		HttpClient httpClient = new DefaultHttpClient();
		
	/*	HttpPost postRequest = new HttpPost(SERVER_URL);
		postRequest.setEntity(new UrlEncodedFormEntity(postData));
		
		HttpResponse httpResponse = httpClient.execute(postRequest);
		int statusCode = httpResponse.getStatusLine().getStatusCode();

      
		if(statusCode == HttpStatus.SC_OK) 
		{
			HttpEntity entity = httpResponse.getEntity();
			
			if(entity != null) 
			{
				return entity.getContent();
			}
		}*/
		  
//Urlmaker.urlmaker();
        Log.d("dsdf",Urlmaker.Url+"");
	            // Call long running operations here (perform background computation)
	            // NOTE: Don't call UI Element here.
	             
	            // Server url call by GET method
		
	            HttpGet httpget = new HttpGet(Urlmaker.Url);
	            ResponseHandler<String> responseHandler = new BasicResponseHandler();
	            String S = null;
				try {
					S = httpClient.execute(httpget, responseHandler);
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.d("sdaaaaf", S+"");
	            return new ByteArrayInputStream(S.getBytes());
	    
	}

	

	public String getDailyChancePoint() 
	{
		return dailyChancePoint;
	}

	public void setDailyChancePoint(String dailyChancePoint) 
	{
		this.dailyChancePoint = dailyChancePoint;
	}

	public String getSetInviteDisplayImage() 
	{
		return setInviteDisplayImage;
	}

	public void setSetInviteDisplayImage(String setInviteDisplayImage) 
	{
		this.setInviteDisplayImage = setInviteDisplayImage;
	}

	

	public String getScratchPoint() 
	{
		return scratchPoint;
	}

	public void setScratchPoint(String scratchPoint) 
	{
		this.scratchPoint = scratchPoint;
	}



	public ArrayList<GiftInfo> getGiftInfoList() 
	{
		return giftInfoList;
	}

	public void setGiftInfoList(ArrayList<GiftInfo> giftInfoList) 
	{
		Collections.copy(this.giftInfoList,giftInfoList);
	}

	public String getBadgeViewValue() 
	{
		return badgeViewValue;
	}

	public void setBadgeViewValue(String badgeViewValue) 
	{
		this.badgeViewValue = badgeViewValue;
	}
	
	public void showPopUpTutorial(final Context context, int[] imageArray)
	{/*
		final Dialog dialog = new Dialog(context);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.tutorial_pop_up);
		dialog.setCancelable(false);
		
		arrayLenght = imageArray.length - 1;
		
		
		imgBtnLeftArrow = (ImageButton) dialog.findViewById(R.id.img_btn_left_arrow);
		imgBtnLeftArrow.setOnClickListener(new OnClickListener() 
		{	
			@Override
			public void onClick(View v) 
			{
				pageCurrentIndex--;
				viewPager.setCurrentItem(pageCurrentIndex);
				arrowVisibleOrNot();
			}
		});
		imgBtnRightArrow = (ImageButton) dialog.findViewById(R.id.img_btn_right_arrow);
		imgBtnRightArrow.setOnClickListener(new OnClickListener() 
		{	
			@Override
			public void onClick(View v) 
			{
				pageCurrentIndex++;
				viewPager.setCurrentItem(pageCurrentIndex);
				arrowVisibleOrNot();
			}
		});
		
		viewPager = (ViewPager) dialog.findViewById(R.id.view_pager);
		viewPager.setAdapter(new ExchangeTutorialPageAdapter(context, imageArray, dialog));
		
		viewPager.setOnPageChangeListener(new OnPageChangeListener() 
		{	
			@Override
			public void onPageSelected(int arg0) 
			{
				if(cursorIndex > arg0)
					isScrolling = true;
				else
					isScrolling = false;
				cursorIndex = arg0;
			}	
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) 
			{
				pageCurrentIndex = arg0;
				arrowVisibleOrNot();
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) 
			{
				if(arg0 == ViewPager.SCROLL_STATE_IDLE && imgBtnRightArrow.getVisibility() == 4)
				{
					if(isScrolling)
					{
						AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
						builder1.setCancelable(false);
						
						builder1.setMessage("ãƒ�ãƒ¥ãƒ¼ãƒˆãƒªã‚¢ãƒ«ã‚’çµ‚äº†ã�—ã�¾ã�™ã€‚");
						
						builder1.setNegativeButton("ã�„ã�„ã�ˆ", new DialogInterface.OnClickListener() 
					    {
							public void onClick(DialogInterface dialog1, int id) 
							{
								dialog1.cancel();
							}
					    });
						builder1.setPositiveButton("ã�¯ã�„", new DialogInterface.OnClickListener() 
				        {
				            public void onClick(DialogInterface dialog1, int id) 
				            {
				                dialog.dismiss();
				            }
				        });
				        
				        builder1.show();
					}
					
					else
					{
						isScrolling = true;
					}
					
				}
			}
		});
		
		dialog.show();*/
	}
	
	public void arrowVisibleOrNot()
	{
		if(pageCurrentIndex == 0)
		{
			imgBtnLeftArrow.setVisibility(View.INVISIBLE);
		}
		else
		{
			imgBtnLeftArrow.setVisibility(View.VISIBLE);
		}
		
		if(pageCurrentIndex == arrayLenght)
		{
			imgBtnRightArrow.setVisibility(View.INVISIBLE);
		}
		else
		{
			imgBtnRightArrow.setVisibility(View.VISIBLE);
		}
	}

	public ArrayList<GiftTicketInfo> getGiftTicketInfoList() 
	{
		return giftTicketInfoList;
	}

	public void setGiftTicketInfoList(ArrayList<GiftTicketInfo> giftTicketInfoList) 
	{
		Collections.copy(this.giftTicketInfoList ,giftTicketInfoList);
	}

	public String getDailyGamePoint() 
	{
		return dailyGamePoint;
	}

	public void setDailyGamePoint(String dailyGamePoint) 
	{
		this.dailyGamePoint = dailyGamePoint;
	}



	public boolean isAppMaintenance() 
	{
		return appMaintenance;
	}

	public void setAppMaintenance(boolean appMaintenance) 
	{
		this.appMaintenance = appMaintenance;
	}
	
	public void openDialog(String err_msg, Context context)
	{
		err_msg = Html.fromHtml(err_msg).toString();
		final AlertDialog.Builder alert = new AlertDialog.Builder(context);
		alert.setMessage(err_msg);
		alert.setCancelable(false);

		alert.setPositiveButton("OK", new DialogInterface.OnClickListener() 
		{
			public void onClick(DialogInterface dialog, int whichButton) 
			{
				dialog.cancel();
			}
		});

		alert.show();
	}

}
