package com.atomix.kurowiz.supports;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;

public class SingleToneClass {

	public static SingleToneClass instance;
	
	public static  String SERVER_URL = "http://androidcard.despicableapps.com/api/index";
	public static ArrayList<DeviceInfo> deviceInfoList;
	public static ArrayList<UserInfo> userInfoList ;
	public static ArrayList<FaqInfo> faqInfoList;
	public static ArrayList<AnnouncementInfo> announcementInfoList;
	public static ArrayList<MenuInfo> menuInfoList;
	public static ArrayList<CategoryItemInfo> categoryItemInfoList;
	public static ArrayList<PointHistoryInfo> pointHistoryInfoList;
	public static ArrayList<GiftInfo> giftInfoList;
	public static ArrayList<GiftTicketInfo> giftTicketInfoList;
	public static ArrayList<TimerInfo> timerInfoList;
	public static ArrayList<UrlschemeInfo> urlschemeInfoList;
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
	
	
	public InputStream getResponseFromServer(ArrayList<NameValuePair> postData) throws Exception 
	{
		HttpClient httpClient = new DefaultHttpClient();
		
		HttpPost postRequest = new HttpPost(SERVER_URL);
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
		}
	
		return null;
	}

	public ArrayList<DeviceInfo> getDeviceInfoList() 
	{
		return deviceInfoList;
	}

	public void setDeviceInfoList(ArrayList<DeviceInfo> deviceInfoList) 
	{
		this.deviceInfoList = deviceInfoList;
	}

	public ArrayList<UserInfo> getUserInfoList() 
	{
		return userInfoList;
	}

	public void setUserInfoList(ArrayList<UserInfo> userInfoList) 
	{
		Collections.copy(this.userInfoList,userInfoList);
		//this.userInfoList = userInfoList;
	}

	public ArrayList<FaqInfo> getFaqInfoList() 
	{
		return faqInfoList;
	}

	public void setFaqInfoList(ArrayList<FaqInfo> faqInfoList) 
	{
		this.faqInfoList = faqInfoList;
	}

	public ArrayList<AnnouncementInfo> getAnnouncementInfoList() 
	{
		return announcementInfoList;
	}

	public void setAnnouncementInfoList(ArrayList<AnnouncementInfo> announcementInfoList) 
	{
		this.announcementInfoList = announcementInfoList;
	}

	public ArrayList<MenuInfo> getMenuInfoList() 
	{
		return menuInfoList;
	}

	public void setMenuInfoList(ArrayList<MenuInfo> menuInfoList) 
	{
		this.menuInfoList = menuInfoList;
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

	public ArrayList<CategoryItemInfo> getCategoryItemInfoList() 
	{
		return categoryItemInfoList;
	}

	public void setCategoryItemInfoList(ArrayList<CategoryItemInfo> categoryItemInfoList) 
	{
		this.categoryItemInfoList = categoryItemInfoList;
	}

	public String getScratchPoint() 
	{
		return scratchPoint;
	}

	public void setScratchPoint(String scratchPoint) 
	{
		this.scratchPoint = scratchPoint;
	}

	public ArrayList<PointHistoryInfo> getPointHistoryInfoList() 
	{
		return pointHistoryInfoList;
	}

	public void setPointHistoryInfoList(ArrayList<PointHistoryInfo> pointHistoryInfoList) 
	{
		this.pointHistoryInfoList = pointHistoryInfoList;
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

	public ArrayList<TimerInfo> getTimerInfoList() 
	{
		return timerInfoList;
	}

	public void setTimerInfoList(ArrayList<TimerInfo> timerInfoList) 
	{
		this.timerInfoList = timerInfoList;
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

	public ArrayList<UrlschemeInfo> getUrlschemeInfoList() 
	{
		return urlschemeInfoList;
	}

	public void setUrlschemeInfoList(ArrayList<UrlschemeInfo> urlschemeInfoList) 
	{
		this.urlschemeInfoList = urlschemeInfoList;
	}
}
