package com.atomix.kurowiz.xmlparser;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.util.Log;

import com.atomix.kurowiz.supports.ConstantValues;
import com.atomix.kurowiz.supports.GiftInfo;
import com.atomix.kurowiz.supports.SingleToneClass;
import com.siliconorchard.hoteldealsfinder.ListActivity;


public class DomParser {

	private DocumentBuilderFactory documentBuilderFactory;
	private DocumentBuilder documentBuilder;
	private Document document;

	

	 public GiftInfo giftInfo;


	
	public void parseAPI28(InputStream xml, boolean isMore) throws Exception 
	{
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();
	//	SingleToneClass.giftInfoList 
		NodeList nodeList = document.getElementsByTagName("hotel");
		

	


		if (nodeList != null && nodeList.getLength() > 0) 
		{	Log.d("else ",nodeList.getLength()+"");
			ConstantValues.isScrolling = true;
			for (int i = 0; i < nodeList.getLength(); i++) 
			{
					
				Node result = nodeList.item(i);
				giftInfo = new GiftInfo();

				Log.d("elsejjj ",i+"");
				if (result.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element ele = (Element) result;
					
					if(ele.getElementsByTagName("hotel_address").item(0).getTextContent() != null)
					{
						
						giftInfo.setHotel_address(ele.getElementsByTagName("hotel_address").item(0).getTextContent());

				/*		Log.d("else ", giftInfo.getHotel_address());
						

						Log.d("else ",i+"");*/
				
					}
					
					if(ele.getElementsByTagName("hotel_city").item(0).getTextContent() != null)
					{
						giftInfo.setHotel_city(ele.getElementsByTagName("hotel_city").item(0).getTextContent());
					}
					
					if(ele.getElementsByTagName("hotel_description").item(0).getTextContent() != null)
					{
						giftInfo.setHotel_description(ele.getElementsByTagName("hotel_description").item(0).getTextContent());
					}
					
					if(ele.getElementsByTagName("hotel_link").item(0).getTextContent() != null)
					{
						giftInfo.setHotel_link(ele.getElementsByTagName("hotel_link").item(0).getTextContent());
					}
					if(ele.getElementsByTagName("hotel_name").item(0).getTextContent() != null)
					{
						giftInfo.setHotel_name(ele.getElementsByTagName("hotel_name").item(0).getTextContent());
					}
					if(ele.getElementsByTagName("hotel_star").item(0).getTextContent() != null)
					{
						giftInfo.setHotel_star(ele.getElementsByTagName("hotel_star").item(0).getTextContent());
						
					}
					if(ele.getElementsByTagName("images").item(0).getTextContent() != null)
					{
						giftInfo.setImages(ele.getElementsByTagName("images").item(0).getTextContent());
						Log.d("elseasd ",i+"");
					}
					if(ele.getElementsByTagName("prices_from").item(0).getTextContent() != null)
					{
						giftInfo.setPrices_from(ele.getElementsByTagName("prices_from").item(0).getTextContent());
					}
					
					
					//SingleToneClass.giftInfoList.add(giftInfo);
					ListActivity.arrayList.add(giftInfo);
				;
					
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
	

}
