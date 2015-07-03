package com.atomix.kurowiz.xmlparser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.util.Log;

import com.atomix.kurowiz.supports.ConstantValues;
import com.atomix.kurowiz.supports.GiftInfo;
import com.atomix.kurowiz.supports.SingleToneClass;
import com.siliconorchard.hoteldealsfinder.CitiesActivity;
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
		NodeList nodeList = document.getElementsByTagName("item");
		

	


		if (nodeList != null && nodeList.getLength() > 0) 
		{	//Log.d("else nodesize ",nodeList.getLength()+"");
			ConstantValues.isScrolling = true;
			for (int i = 0; i < nodeList.getLength(); i++) 
			{
					
				Node result = nodeList.item(i);
				giftInfo = new GiftInfo();

				//Log.d("noe ",i+"");
				if (result.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element ele = (Element) result;
					
					if(ele.getElementsByTagName("title").item(0).getTextContent() != null)
					{
						
						giftInfo.setHotel_address(ele.getElementsByTagName("title").item(0).getTextContent());

				/*		Log.d("else ", giftInfo.getHotel_address());
						

						Log.d("else ",i+"");*/
				
					}
					
					/*if(ele.getElementsByTagName("link").item(0).getTextContent() != null)
					{
						giftInfo.setHotel_city(ele.getElementsByTagName("link").item(0).getTextContent());
					}
					*/
					/*if(ele.getElementsByTagName("title").item(0).getTextContent() != null)
					{
						giftInfo.setHotel_description(ele.getElementsByTagName("title").item(0).getTextContent());
					}
					*/
					if(ele.getElementsByTagName("link").item(0).getTextContent() != null)
					{
						giftInfo.setHotel_link(ele.getElementsByTagName("link").item(0).getTextContent());
					}
					if(ele.getElementsByTagName("title").item(0).getTextContent() != null)
					{
						giftInfo.setHotel_name(ele.getElementsByTagName("title").item(0).getTextContent());
					}
					if(ele.getElementsByTagName("title").item(0).getTextContent() != null)
					{
						giftInfo.setHotel_star(ele.getElementsByTagName("title").item(0).getTextContent());
						
					}
					if(ele.getElementsByTagName("img").item(0).getTextContent() != null)
					{
						giftInfo.setImages(ele.getElementsByTagName("img").item(0).getTextContent());
					//	Log.d("elseasd ",i+"");
					}
					/*if(ele.getElementsByTagName("prices_from").item(0).getTextContent() != null)
					{
						giftInfo.setPrices_from(ele.getElementsByTagName("prices_from").item(0).getTextContent());
					}
					*/
					
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



	public void parseAPI29(InputStream xml, boolean isMore) {
		// TODO Auto-generated method stub

		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			document = documentBuilder.parse(xml);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		document.getDocumentElement().normalize();
	//	SingleToneClass.giftInfoList 
		NodeList nodeList = document.getElementsByTagName("item");
		

	


		if (nodeList != null && nodeList.getLength() > 0) 
		{	Log.d("else nodesize ",nodeList.getLength()+"");
			ConstantValues.isScrolling = true;
			for (int i = 0; i < nodeList.getLength(); i++) 
			{
					
				Node result = nodeList.item(i);
				giftInfo = new GiftInfo();

				Log.d("noe ",i+"");
				if (result.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element ele = (Element) result;
					
					if(ele.getElementsByTagName("title").item(0).getTextContent() != null)
					{
						
						giftInfo.setHotel_address(ele.getElementsByTagName("title").item(0).getTextContent());

				/*		Log.d("else ", giftInfo.getHotel_address());
						

						Log.d("else ",i+"");*/
				
					}
					
					/*if(ele.getElementsByTagName("link").item(0).getTextContent() != null)
					{
						giftInfo.setHotel_city(ele.getElementsByTagName("link").item(0).getTextContent());
					}
					*/
					/*if(ele.getElementsByTagName("title").item(0).getTextContent() != null)
					{
						giftInfo.setHotel_description(ele.getElementsByTagName("title").item(0).getTextContent());
					}
					*/
					if(ele.getElementsByTagName("link").item(0).getTextContent() != null)
					{
						giftInfo.setHotel_link(ele.getElementsByTagName("link").item(0).getTextContent());
					}
					if(ele.getElementsByTagName("title").item(0).getTextContent() != null)
					{
						giftInfo.setHotel_name(ele.getElementsByTagName("title").item(0).getTextContent());
					}
					if(ele.getElementsByTagName("title").item(0).getTextContent() != null)
					{
						giftInfo.setHotel_star(ele.getElementsByTagName("title").item(0).getTextContent());
						
					}
					if(ele.getElementsByTagName("img").item(0).getTextContent() != null)
					{
						giftInfo.setImages(ele.getElementsByTagName("img").item(0).getTextContent());
						Log.d("elseasd ",i+"");
					}
					/*if(ele.getElementsByTagName("prices_from").item(0).getTextContent() != null)
					{
						giftInfo.setPrices_from(ele.getElementsByTagName("prices_from").item(0).getTextContent());
					}
					*/
					
					//SingleToneClass.giftInfoList.add(giftInfo);
					CitiesActivity.arrayList.add(giftInfo);
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
