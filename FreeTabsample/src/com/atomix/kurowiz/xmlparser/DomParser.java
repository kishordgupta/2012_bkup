package com.atomix.kurowiz.xmlparser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.util.Log;

import com.atomix.kurowiz.supports.AnnouncementInfo;
import com.atomix.kurowiz.supports.CategoryItemInfo;
import com.atomix.kurowiz.supports.ConstantValues;
import com.atomix.kurowiz.supports.DeviceInfo;
import com.atomix.kurowiz.supports.FaqInfo;
import com.atomix.kurowiz.supports.GiftInfo;
import com.atomix.kurowiz.supports.GiftTicketInfo;
import com.atomix.kurowiz.supports.MenuInfo;
import com.atomix.kurowiz.supports.PointHistoryInfo;
import com.atomix.kurowiz.supports.SingleToneClass;
import com.atomix.kurowiz.supports.TimerInfo;
import com.atomix.kurowiz.supports.UrlschemeInfo;
import com.atomix.kurowiz.supports.UserInfo;

public class DomParser {

	private DocumentBuilderFactory documentBuilderFactory;
	private DocumentBuilder documentBuilder;
	private Document document;

	
	static public UserInfo userInfo;
	static public DeviceInfo deviceInfo;
	static public FaqInfo faqInfo;
	static public AnnouncementInfo announcementInfo;
	static public MenuInfo menuInfo;
	static public CategoryItemInfo categoryItemInfo;
	static public PointHistoryInfo pointHistoryInfo;
	 public GiftInfo giftInfo;
	public GiftTicketInfo giftTicketInfo;
	static public TimerInfo timerInfo;
	static public UrlschemeInfo urlschemeInfo;
	
	public void parseAPI1(InputStream xml) throws Exception 
	{
		userInfo = new UserInfo();
		ArrayList<UserInfo> arrayList = new ArrayList<UserInfo>();
		
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();

		Node user_id = document.getElementsByTagName("user_id").item(0);
		if(user_id.getTextContent() != null) 
		{
			userInfo.setUserId(user_id.getTextContent());
			Log.d("sdf", user_id.getTextContent());
		}
		
		Node point = document.getElementsByTagName("point").item(0);
		if(point.getTextContent() != null) 
		{
			userInfo.setUserPoint(point.getTextContent());
			Log.d("sdf", point.getTextContent());
		}
		
		Node push_status = document.getElementsByTagName("push_status").item(0);
		if(push_status.getTextContent() == null || push_status.equals("") ) 
		{
			userInfo.setPushStatus("0");
		}
		else
		{
			userInfo.setPushStatus(push_status.getTextContent());
		}
		
		Node invite_code = document.getElementsByTagName("invite_code").item(0);
		if(invite_code.getTextContent() != null) 
		{
			userInfo.setInviteCode(invite_code.getTextContent());
		}
		
		Node zip_no = document.getElementsByTagName("zip_no").item(0);
		if(zip_no.getTextContent() != null) 
		{
			userInfo.setZipCode(zip_no.getTextContent());
		}
		
		Node tel_no = document.getElementsByTagName("tel_no").item(0);
		if(tel_no.getTextContent() != null) 
		{
			userInfo.setTelephoneNo(tel_no.getTextContent());
		}
		
		Node address = document.getElementsByTagName("address").item(0);
		if(address.getTextContent() != null) 
		{
			userInfo.setUserAddress(address.getTextContent());
		}
		
		Node name = document.getElementsByTagName("name").item(0);
		if(name.getTextContent() != null) 
		{
			userInfo.setUserName(name.getTextContent());
		}
		
		Node mail = document.getElementsByTagName("mail").item(0);
		if(mail.getTextContent() != null) 
		{
			userInfo.setMailId(mail.getTextContent());
		}
		
		Node group_id = document.getElementsByTagName("group_id").item(0);
		if(group_id.getTextContent() == null || group_id.getTextContent().equals("") ) 
		{
			userInfo.setGroupId("0");
		}
		else
		{
			userInfo.setGroupId(group_id.getTextContent());	
		}
		
		arrayList.add(userInfo);
		
		SingleToneClass.getInstance().setUserInfoList(arrayList);
	}
	
	public void parseAPI9(InputStream xml, boolean isMore) throws Exception 
	{
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();
		
		Element rootElement = document.getDocumentElement();
		
		Node ableChance = rootElement.getElementsByTagName("scratch_point").item(0);
		
		SingleToneClass.getInstance().setScratchPoint(ableChance.getTextContent());
		
		NodeList nodeList = document.getElementsByTagName("item");
		
		ArrayList<CategoryItemInfo> arrayList = new ArrayList<CategoryItemInfo>();

		if (nodeList != null && nodeList.getLength() > 0) 
		{
			ConstantValues.isScrolling = true;
			for (int i = 0; i < nodeList.getLength(); i++) 
			{
				Node result = nodeList.item(i);
				categoryItemInfo = new CategoryItemInfo();
				
				if (result.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element ele = (Element) result;
					
					if(ele.getElementsByTagName("ad_id").item(0).getTextContent() != null)
					{
						categoryItemInfo.setAdId(ele.getElementsByTagName("ad_id").item(0).getTextContent());
					}
					
					if(ele.getElementsByTagName("name").item(0).getTextContent() != null)
					{
						categoryItemInfo.setName(ele.getElementsByTagName("name").item(0).getTextContent());
					}
					
					if(ele.getElementsByTagName("condition").item(0).getTextContent() != null)
					{
						categoryItemInfo.setCondition(ele.getElementsByTagName("condition").item(0).getTextContent());
					}
					
					if(ele.getElementsByTagName("description").item(0).getTextContent() != null)
					{
						categoryItemInfo.setDescription(ele.getElementsByTagName("description").item(0).getTextContent());
					}
					
					if(ele.getElementsByTagName("link_url").item(0).getTextContent() != null)
					{
						categoryItemInfo.setLinkUrl(ele.getElementsByTagName("link_url").item(0).getTextContent());
					}
					
					if(ele.getElementsByTagName("image_url").item(0).getTextContent() != null)
					{
						categoryItemInfo.setImageUrl(ele.getElementsByTagName("image_url").item(0).getTextContent());
					}
					
					if(ele.getElementsByTagName("app_id").item(0).getTextContent() != null)
					{
						categoryItemInfo.setAppId(ele.getElementsByTagName("app_id").item(0).getTextContent());
					}
					
					if(ele.getElementsByTagName("url_scheme").item(0).getTextContent() != null)
					{
						categoryItemInfo.setUrlScheme(ele.getElementsByTagName("url_scheme").item(0).getTextContent());
					}
					
					if(ele.getElementsByTagName("category_id").item(0).getTextContent() != null)
					{
						categoryItemInfo.setCategoryId(ele.getElementsByTagName("category_id").item(0).getTextContent());
					}
					
					if(ele.getElementsByTagName("platform_name").item(0).getTextContent() != null)
					{
						categoryItemInfo.setPlatformName(ele.getElementsByTagName("platform_name").item(0).getTextContent());
					}
					
					if(ele.getElementsByTagName("point").item(0).getTextContent() != null)
					{
						categoryItemInfo.setPoint(ele.getElementsByTagName("point").item(0).getTextContent());
					}
					if(isMore)
						SingleToneClass.getInstance().getCategoryItemInfoList().add(categoryItemInfo);
					else
						arrayList.add(categoryItemInfo);
				}
				
				else
				{
					Log.i("else ", "Condition");
				}
				
			}
			
			if(!isMore)
				SingleToneClass.getInstance().setCategoryItemInfoList(arrayList);
			else
				Log.i("More button clicked ", "____________");
		}
		
		else
		{
			ConstantValues.isScrolling = false;
			if(!isMore)
				SingleToneClass.getInstance().getCategoryItemInfoList().removeAll(SingleToneClass.getInstance().getCategoryItemInfoList());
		}
	}
	
	public void parseAPI12(InputStream xml, boolean isScroll) throws Exception 
	{
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();
		
		NodeList nodeList = document.getElementsByTagName("item");
		
		ArrayList<PointHistoryInfo> arrayList = new ArrayList<PointHistoryInfo>();

		if (nodeList != null && nodeList.getLength() > 0) 
		{
			for (int i = 0; i < nodeList.getLength(); i++) 
			{
				Node result = nodeList.item(i);
				pointHistoryInfo = new PointHistoryInfo();
				
				if (result.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element ele = (Element) result;
					
					if(ele.getElementsByTagName("point_type_name").item(0).getTextContent() != null)
					{
						pointHistoryInfo.setPointTypeName(ele.getElementsByTagName("point_type_name").item(0).getTextContent());
					}
					
					if(ele.getElementsByTagName("point").item(0).getTextContent() != null)
					{
						pointHistoryInfo.setPoint(ele.getElementsByTagName("point").item(0).getTextContent());
					}
					
					if(ele.getElementsByTagName("regist_date").item(0).getTextContent() != null)
					{
						pointHistoryInfo.setRegisteredDate(ele.getElementsByTagName("regist_date").item(0).getTextContent());
					}
					
					if(isScroll)
						SingleToneClass.getInstance().getPointHistoryInfoList().add(pointHistoryInfo);
					else
						arrayList.add(pointHistoryInfo);
				}
				
				else
				{
					Log.i("else ", "Condition");
				}
				
			}
			if(!isScroll)	
				SingleToneClass.getInstance().setPointHistoryInfoList(arrayList);
		}
	}
	
	public String parseAPI30(InputStream xml) throws Exception 
	{
		String msg = "";
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();
		
		Element rootElement = document.getDocumentElement();
		Node ableChance = rootElement.getElementsByTagName("error_msg").item(0);
		
	if(ableChance != null)
		{
			return msg = ableChance.getTextContent();
		}
		return msg;
	}
	
	public boolean parseAPI21(InputStream xml) throws Exception 
	{
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();
		

		Element rootElement = document.getDocumentElement();
		Node ableChance = rootElement.getElementsByTagName("result").item(0);
		
		if(ableChance != null)
		{
			Log.i("Able Chance:", ableChance.getTextContent());
			boolean b = Boolean.parseBoolean(ableChance.getTextContent());
			return b;
		}
		return false;
	}
	
	public boolean parseAPI42(InputStream xml) throws Exception 
	{
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();

		Element rootElement = document.getDocumentElement();
		Node isMenuShow = rootElement.getElementsByTagName("result").item(0);
		
		if(isMenuShow != null)
		{
			boolean b = Boolean.parseBoolean(isMenuShow.getTextContent());
			return b;
		}
		return false;
	}
	
	public boolean parseAPI45(InputStream xml) throws Exception 
	{
		timerInfo = new TimerInfo();
		ArrayList<TimerInfo> arrayList = new ArrayList<TimerInfo>();
		
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();

		Element rootElement = document.getDocumentElement();
		
		Node hours = document.getElementsByTagName("h").item(0);
		if(hours.getTextContent() != null) 
		{
			timerInfo.setHours(hours.getTextContent());
		}
		
		Node minutes = document.getElementsByTagName("i").item(0);
		if(minutes.getTextContent() != null) 
		{
			timerInfo.setMinutes(minutes.getTextContent());
		}
		
		Node seconds = document.getElementsByTagName("s").item(0);
		if(seconds.getTextContent() != null) 
		{
			timerInfo.setSeconds(seconds.getTextContent());
		}
		
		Node imageUrl = document.getElementsByTagName("image_url").item(0);
		if(imageUrl.getTextContent() != null) 
		{
			timerInfo.setImageUrl(imageUrl.getTextContent());
		}
		
		arrayList.add(timerInfo);
		SingleToneClass.getInstance().setTimerInfoList(arrayList);
		
		Node displayFlag = rootElement.getElementsByTagName("display_flag").item(0);
		
		if(displayFlag != null)
		{
			boolean b = Boolean.parseBoolean(displayFlag.getTextContent());
			return b;
		}
		return false;
	}
	
	public void parseAPI46(InputStream xml) throws Exception 
	{
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();

		Element rootElement = document.getDocumentElement();
		Node badgeValue = rootElement.getElementsByTagName("result").item(0);
		
		SingleToneClass.getInstance().setBadgeViewValue(badgeValue.getTextContent());
	}

	public void parseAPI22(InputStream xml) throws Exception 
	{
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();

		NodeList nodeList = document.getElementsByTagName("item");
		
		ArrayList<FaqInfo> arrayList = new ArrayList<FaqInfo>();

		if (nodeList != null && nodeList.getLength() > 0) 
		{
			for (int i = 0; i < nodeList.getLength(); i++) 
			{
				Node result = nodeList.item(i);
				faqInfo = new FaqInfo();
				
				if (result.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element ele = (Element) result;
					
					faqInfo.setTitle(ele.getElementsByTagName("title").item(0).getTextContent());
					faqInfo.setContent(ele.getElementsByTagName("content").item(0).getTextContent());
					
					arrayList.add(faqInfo);
				}
				
			}
			
			SingleToneClass.getInstance().setFaqInfoList(arrayList);
		}
	}
	
	public void parseAPI20(InputStream xml) throws Exception 
	{
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();
         SingleToneClass.announcementInfoList=new ArrayList<AnnouncementInfo>();
		NodeList nodeList = document.getElementsByTagName("item");
		
		ArrayList<AnnouncementInfo> arrayList = new ArrayList<AnnouncementInfo>();

		if (nodeList != null && nodeList.getLength() > 0) 
		{
			for (int i = 0; i < nodeList.getLength(); i++) 
			{
				Node result = nodeList.item(i);
				announcementInfo = new AnnouncementInfo();
				
				if (result.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element ele = (Element) result;
					
					announcementInfo.setTitle(ele.getElementsByTagName("title").item(0).getTextContent());
					announcementInfo.setContent(ele.getElementsByTagName("content").item(0).getTextContent());
					announcementInfo.setRegisteredDate(ele.getElementsByTagName("regist_date").item(0).getTextContent());
					 SingleToneClass.announcementInfoList.add(announcementInfo);
					arrayList.add(announcementInfo);
				}
				
			}
			
			SingleToneClass.getInstance().setAnnouncementInfoList(arrayList);
		}
	}
	
	public void parseAPI28(InputStream xml, boolean isMore) throws Exception 
	{
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();
	//	SingleToneClass.giftInfoList 
		NodeList nodeList = document.getElementsByTagName("item");
		
		ArrayList<GiftInfo> arrayList = new ArrayList<GiftInfo>();

		if (nodeList != null && nodeList.getLength() > 0) 
		{
			ConstantValues.isScrolling = true;
			for (int i = 0; i < nodeList.getLength(); i++) 
			{
				Node result = nodeList.item(i);
				giftInfo = new GiftInfo();
				
				if (result.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element ele = (Element) result;
					
					if(ele.getElementsByTagName("gift_code").item(0).getTextContent() != null)
					{
						giftInfo.setGiftCode(ele.getElementsByTagName("gift_code").item(0).getTextContent());
						//Log.i("else ", ele.getElementsByTagName("gift_code").item(0).getTextContent());
					}
					
					if(ele.getElementsByTagName("point").item(0).getTextContent() != null)
					{
						giftInfo.setPoint(ele.getElementsByTagName("point").item(0).getTextContent());
					}
					
					if(ele.getElementsByTagName("limite_date").item(0).getTextContent() != null)
					{
						giftInfo.setLimitDate(ele.getElementsByTagName("limite_date").item(0).getTextContent());
					}
					
					if(ele.getElementsByTagName("regist_date").item(0).getTextContent() != null)
					{
						giftInfo.setRegisteredDate(ele.getElementsByTagName("regist_date").item(0).getTextContent());
					}
					SingleToneClass.giftInfoList.add(giftInfo);
					/*if(isMore)
						SingleToneClass.giftInfoList.add(giftInfo);
					else
						arrayList.add(giftInfo);*/
				}
				
				else
				{
					Log.i("else ", "Condition");
				}
				
			}
			
			/*if(!isMore)
				SingleToneClass.getInstance().setGiftInfoList(arrayList);*/
		}
		
		else
		{
			ConstantValues.isScrolling = false;
		}
	}
	
	public void parseAPI29(InputStream xml) throws Exception 
	{
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();

		NodeList nodeList = document.getElementsByTagName("item");
		
		ArrayList<UrlschemeInfo> arrayList = new ArrayList<UrlschemeInfo>();

		if (nodeList != null && nodeList.getLength() > 0) 
		{
			for (int i = 0; i < nodeList.getLength(); i++) 
			{
				Node result = nodeList.item(i);
				urlschemeInfo = new UrlschemeInfo();
				
				if (result.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element ele = (Element) result;
					
					urlschemeInfo.setAddId(ele.getElementsByTagName("ad_id").item(0).getTextContent());
					urlschemeInfo.setUrlScheme(ele.getElementsByTagName("url_scheme").item(0).getTextContent());
					
					arrayList.add(urlschemeInfo);
				}
			}
			
			SingleToneClass.getInstance().setUrlschemeInfoList(arrayList);
		}
	}
	
	public void parseAPI31(InputStream xml) throws Exception 
	{
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();

		NodeList nodeList = document.getElementsByTagName("item");
		SingleToneClass.giftTicketInfoList = new  ArrayList<GiftTicketInfo>();;
		ArrayList<GiftTicketInfo> arrayList = new ArrayList<GiftTicketInfo>();

		if (nodeList != null && nodeList.getLength() > 0) 
		{
			for (int i = 0; i < nodeList.getLength(); i++) 
			{
				Node result = nodeList.item(i);
				giftTicketInfo = new GiftTicketInfo();
				
				if (result.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element ele = (Element) result;
					
				//	giftTicketInfo.setGiftName(ele.getElementsByTagName("gift_name").item(0).getTextContent());
					giftTicketInfo.setGiftType(ele.getElementsByTagName("gift_type").item(0).getTextContent());
					giftTicketInfo.setGiftPoint(ele.getElementsByTagName("gift_point").item(0).getTextContent());
					giftTicketInfo.setTicketName(ele.getElementsByTagName("ticket_name").item(0).getTextContent());
					SingleToneClass.giftTicketInfoList.add(giftTicketInfo);
					Log.d("sdf", giftTicketInfo.getGiftName());
					arrayList.add(giftTicketInfo);
				}
				
			}
			
		//	SingleToneClass.getInstance().setGiftTicketInfoList(arrayList);
		}
	}
	
	public void parseAPI44(InputStream xml) throws Exception 
	{
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();
		
		NodeList nodeList = document.getElementsByTagName("item");
		
		ArrayList<MenuInfo> arrayList = new ArrayList<MenuInfo>();

		if (nodeList != null && nodeList.getLength() > 0) 
		{
			for (int i = 0; i < nodeList.getLength(); i++) 
			{
				Node result = nodeList.item(i);
				menuInfo = new MenuInfo();
				
				if (result.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element ele = (Element) result;
					
					menuInfo.setCategoryId(ele.getElementsByTagName("category_id").item(0).getTextContent());
					menuInfo.setCategoryName(ele.getElementsByTagName("category_name").item(0).getTextContent());
					
					arrayList.add(menuInfo);
				}
				
			}
			
			SingleToneClass.getInstance().setMenuInfoList(arrayList);
		}
	}
	

	public void parseAPI2(InputStream xml) throws Exception 
	{
		deviceInfo = new DeviceInfo();
		ArrayList<DeviceInfo> arrayList = new ArrayList<DeviceInfo>();
		
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();

		Element rootElement = document.getDocumentElement();

		Node user_id = rootElement.getElementsByTagName("user_id").item(0);
		if (user_id != null) 
		{
			Log.i("API_2: user_id = ", user_id.getTextContent());
			deviceInfo.setUserId(user_id.getTextContent());
		}

		Node device_identifier = rootElement.getElementsByTagName("device_identifier").item(0);
		if (device_identifier != null) 
		{
			deviceInfo.setDeviceIdentifier(device_identifier.getTextContent());
		}
		
		arrayList.add(deviceInfo);
		
		SingleToneClass.getInstance().setDeviceInfoList(arrayList);
	}
	
	public boolean parseAPI4(InputStream xml) throws Exception 
	{
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();
		
		NodeList nodeList = document.getElementsByTagName("item");
		
		if (nodeList != null && nodeList.getLength() > 0) 
		{
			for (int i = 0; i < nodeList.getLength(); i++) 
			{
				Node result = nodeList.item(i);
				
				if (result.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element ele = (Element) result;
					
					SingleToneClass.getInstance().setDailyGamePoint(ele.getElementsByTagName("point").item(0).getTextContent().toString());
					String ableGame = ele.getElementsByTagName("able_game").item(0).getTextContent().toString();
					
					if(ableGame != null)
					{
						boolean b = Boolean.parseBoolean(ableGame);
						return b;
					}
					
				}
				
			}
			
		}
		
		return false;
		
	}

	public boolean parseAPI5(InputStream xml) throws Exception 
	{
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();
		
		Element rootElement = document.getDocumentElement();
		
		Node ableChance = rootElement.getElementsByTagName("able_chance").item(0);
				
		if(ableChance != null)
		{
			boolean b = Boolean.parseBoolean(ableChance.getTextContent());
			return b;
		}
		
		return false;
	}
//	
	public boolean parseAPI7(InputStream xml) throws Exception 
	{
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();
		
		Element rootElement = document.getDocumentElement();
		
		Node chanceClickedResult = rootElement.getElementsByTagName("result").item(0);
		Node chanceClickedPoint = rootElement.getElementsByTagName("point").item(0);
		
		if(chanceClickedResult != null)
		{
			boolean b = Boolean.parseBoolean(chanceClickedResult.getTextContent());
			SingleToneClass.getInstance().setDailyChancePoint(chanceClickedPoint.getTextContent());
			return b;
		}
		
		return false;
	}
	
	public boolean parseAPI33(InputStream xml) throws Exception 
	{
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();
		
		Element rootElement = document.getDocumentElement();
		
		Node inviteFlag = rootElement.getElementsByTagName("invite_display").item(0);
		Node inviteImage = rootElement.getElementsByTagName("image_url").item(0);
		
		if(inviteFlag != null)
		{
			boolean b = Boolean.parseBoolean(inviteFlag.getTextContent());
			SingleToneClass.getInstance().setSetInviteDisplayImage(inviteImage.getTextContent());
			return b;
		}
		
		return false;
	}
	
	public void parseAPI35(InputStream xml) throws Exception 
	{
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();
		
		Element rootElement = document.getDocumentElement();
		
		Node result = rootElement.getElementsByTagName("result").item(0);
				
		if(result != null)
		{
			SingleToneClass.getInstance().setAppMaintenance(Boolean.parseBoolean(result.getTextContent()));
		}
		
		else
			SingleToneClass.getInstance().setAppMaintenance(false);
	}
	
	public boolean parseAPI23(InputStream xml) throws Exception 
	{
		boolean returnResult = false;
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();
		
		Element rootElement = document.getDocumentElement();

		Node result = rootElement.getElementsByTagName("able_game").item(0);
		if(result != null) 
		{
			if(result.getTextContent().equalsIgnoreCase("true"))
				returnResult = true;
			else
				returnResult = false;
			return returnResult;
		}
		
		return returnResult;
	}
	
	public String parseAPI24(InputStream xml) throws Exception 
	{
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();
		
		Element rootElement = document.getDocumentElement();

		Node result = rootElement.getElementsByTagName("point").item(0);
		if(result != null) 
		{
			return result.getTextContent();
		}
		
		return "no_point";
	}
}
