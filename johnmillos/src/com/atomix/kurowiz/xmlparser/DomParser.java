package com.atomix.kurowiz.xmlparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.util.Log;

import com.atomix.kurowiz.supports.ConstantValues;

import com.model.Category;
import com.model.Constants;
import com.model.Instancevalues;
import com.model.Offer;
import com.model.Vendor;

public class DomParser {

	private DocumentBuilderFactory documentBuilderFactory;
	private DocumentBuilder documentBuilder;
	private Document document;



	public void parseuserlogin(InputStream xml) throws Exception {
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();
		// SingleToneClass.giftInfoList
		Instancevalues.isErrormessage=false;
		NodeList nodeList = document
				.getElementsByTagName(Constants.userparsetag);

		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {

				Node result = nodeList.item(i);
				

			
				if (result.getNodeType() == Node.ELEMENT_NODE) {
					Element ele = (Element) result;
					
					if (ele.getElementsByTagName(Constants.Errormessage).item(0)
							.getTextContent() != null) {

						Instancevalues.Error_message=ele.getElementsByTagName(Constants.Errormessage).item(0)
							.getTextContent();
						Instancevalues.isErrormessage=true;
                        break;
					}
					else
					{
						Instancevalues.Error_message="login";
							Instancevalues.isErrormessage=false;
					}
					if (ele.getElementsByTagName(Constants.userid).item(0)
							.getTextContent() != null) {

						Instancevalues.currentuser.setUserID(ele.getElementsByTagName(Constants.userid).item(0)
							.getTextContent());

					}

					if (ele.getElementsByTagName(Constants.Firstname).item(0)
							.getTextContent() != null) {
						Instancevalues.currentuser.setFirstname(ele.getElementsByTagName(Constants.Firstname).item(0)
								.getTextContent());
					}

					if (ele.getElementsByTagName(Constants.LastName).item(0)
							.getTextContent() != null) {
						Instancevalues.currentuser.setLastName(ele.getElementsByTagName(Constants.LastName).item(0)
								.getTextContent());
					}
					
					if (ele.getElementsByTagName(Constants.activated).item(0)
							.getTextContent() != null) {
						Instancevalues.currentuser.setActivated(ele.getElementsByTagName(Constants.activated).item(0)
								.getTextContent());
					}
					
					if (ele.getElementsByTagName(Constants.emailadress).item(0)
							.getTextContent() != null) {
						Instancevalues.currentuser.setEmail(ele.getElementsByTagName(Constants.emailadress).item(0)
								.getTextContent());
					}
					

				} else {
					Log.i("else ", "Condition");
				}

			}

			/*
			 * if(!isMore)
			 * SingleToneClass.getInstance().setGiftInfoList(arrayList);
			 */
		}

		
	}
 
	public void parseuseregistratopn(InputStream xml) throws Exception {
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();
		// SingleToneClass.giftInfoList
		Instancevalues.isErrormessage=false;
		NodeList nodeList = document
				.getElementsByTagName(Constants.userparseregtag);

		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {

				Node result = nodeList.item(i);
				

			
				if (result.getNodeType() == Node.ELEMENT_NODE) {
					Element ele = (Element) result;
					
					if (ele.getElementsByTagName(Constants.Errormessage).item(0)
							.getTextContent() != null) {

						Instancevalues.Error_message=ele.getElementsByTagName(Constants.Errormessage).item(0)
							.getTextContent();
						Instancevalues.isErrormessage=true;
                        break;
					}
					else
					{
						Instancevalues.Error_message="login";
							Instancevalues.isErrormessage=false;
					}
					if (ele.getElementsByTagName(Constants.userid).item(0)
							.getTextContent() != null) {

						Instancevalues.currentuser.setUserID(ele.getElementsByTagName(Constants.userid).item(0)
							.getTextContent());

					}

					if (ele.getElementsByTagName(Constants.Name).item(0)
							.getTextContent() != null) {
						Instancevalues.currentuser.setFirstname(ele.getElementsByTagName(Constants.Firstname).item(0)
								.getTextContent());
					}

					if (ele.getElementsByTagName(Constants.LastName).item(0)
							.getTextContent() != null) {
						Instancevalues.currentuser.setLastName(ele.getElementsByTagName(Constants.LastName).item(0)
								.getTextContent());
					}
					
				
					
					if (ele.getElementsByTagName(Constants.emailadress).item(0)
							.getTextContent() != null) {
						Instancevalues.currentuser.setEmail(ele.getElementsByTagName(Constants.emailadress).item(0)
								.getTextContent());
					}
					

				} else {
					Log.i("else ", "Condition");
				}

			}

			/*
			 * if(!isMore)
			 * SingleToneClass.getInstance().setGiftInfoList(arrayList);
			 */
		}

		
	}

	
	public void parsegetcategorylist(InputStream xml, boolean isMore) throws Exception {
		
	
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();
		// SingleToneClass.giftInfoList
		NodeList nodeList = document.getElementsByTagName(Constants.categoryparser);

		if (nodeList != null && nodeList.getLength() > 0) {
			
			ConstantValues.isScrolling = true;
			for (int i = 0; i < nodeList.getLength(); i++) {

				Node result = nodeList.item(i);
				Category  giftInfo= new Category();

			
				if (result.getNodeType() == Node.ELEMENT_NODE) {
					Element ele = (Element) result;

					if (ele.getElementsByTagName(Constants.Name).item(0)
							.getTextContent() != null) {

						giftInfo.setName(ele
								.getElementsByTagName(Constants.Name).item(0)
								.getTextContent());

					}
					
					if (ele.getElementsByTagName(Constants.Imglink).item(0)
							.getTextContent() != null) {
						giftInfo.setImglink(ele
								.getElementsByTagName(Constants.Imglink).item(0)
								.getTextContent());
					}

					// SingleToneClass.giftInfoList.add(giftInfo);
					Instancevalues.categorylist.add(giftInfo);

				} else {
					Log.i("else ", "Condition");
				}

			}

			/*
			 * if(!isMore)
			 * SingleToneClass.getInstance().setGiftInfoList(arrayList);
			 */
		}

		else {
			ConstantValues.isScrolling = false;
		}
	}
	
	public void parsegetvendorlist(InputStream xml, boolean isMore) throws Exception {
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();
		// SingleToneClass.giftInfoList
		NodeList nodeList = document.getElementsByTagName(Constants.vendorparse);

		if (nodeList != null && nodeList.getLength() > 0) {
			
			ConstantValues.isScrolling = true;
			for (int i = 0; i < nodeList.getLength(); i++) {

				Node result = nodeList.item(i);
				Vendor  giftInfo= new Vendor();

			
				if (result.getNodeType() == Node.ELEMENT_NODE) {
					Element ele = (Element) result;

					if (ele.getElementsByTagName(Constants.Vendorname).item(0)
							.getTextContent() != null) {

						giftInfo.setVendorname(ele
								.getElementsByTagName(Constants.Vendorname).item(0)
								.getTextContent());

						
					}

					if (ele.getElementsByTagName(Constants.Vendorlogo).item(0)
							.getTextContent() != null) {
						giftInfo.setVendorlogo(ele
								.getElementsByTagName(Constants.Vendorlogo).item(0)
								.getTextContent());
					}
					if (ele.getElementsByTagName(Constants.Vendorname).item(0)
							.getTextContent() != null) {

						giftInfo.setVendorname(ele
								.getElementsByTagName(Constants.Vendorname).item(0)
								.getTextContent());

						
					}

					if (ele.getElementsByTagName(Constants.VendorId).item(0)
							.getTextContent() != null) {
						giftInfo.setVendorId(ele
								.getElementsByTagName(Constants.VendorId).item(0)
								.getTextContent());
					}
					if (ele.getElementsByTagName(Constants.Featured).item(0)
							.getTextContent() != null) {

						giftInfo.setFeatured(ele
								.getElementsByTagName(Constants.Featured).item(0)
								.getTextContent());

						
					}

					if (ele.getElementsByTagName(Constants.Vendoradress).item(0)
							.getTextContent() != null) {
						giftInfo.setVendoradress(ele
								.getElementsByTagName(Constants.Vendoradress).item(0)
								.getTextContent());
					}
					
					if (ele.getElementsByTagName(Constants.Account).item(0)
							.getTextContent() != null) {
						giftInfo.setAccount(ele
								.getElementsByTagName(Constants.Account).item(0)
								.getTextContent());
					}
					
					if (ele.getElementsByTagName(Constants.lat).item(0)
							.getTextContent() != null) {
						giftInfo.setLat(ele
								.getElementsByTagName(Constants.lat).item(0)
								.getTextContent());
					}
					
					if (ele.getElementsByTagName(Constants.lon).item(0)
							.getTextContent() != null) {
						giftInfo.setLon(ele
								.getElementsByTagName(Constants.lon).item(0)
								.getTextContent());
					}
					// SingleToneClass.giftInfoList.add(giftInfo);
					Instancevalues.currentvendorlist.add(giftInfo);

				} else {
					Log.i("else ", "Condition");
				}

			}

			/*
			 * if(!isMore)
			 * SingleToneClass.getInstance().setGiftInfoList(arrayList);
			 */
		}

		else {
			ConstantValues.isScrolling = false;
		}
	}
	public void parsegetfeaturevendorlist(InputStream xml, boolean isMore) throws Exception {
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();
		// SingleToneClass.giftInfoList
		NodeList nodeList = document.getElementsByTagName(Constants.faturedvendorparse);

		if (nodeList != null && nodeList.getLength() > 0) {
			
			ConstantValues.isScrolling = true;
			for (int i = 0; i < nodeList.getLength(); i++) {

				Node result = nodeList.item(i);
				Vendor  giftInfo= new Vendor();

			
				if (result.getNodeType() == Node.ELEMENT_NODE) {
					Element ele = (Element) result;

					if (ele.getElementsByTagName(Constants.Vendorname).item(0)
							.getTextContent() != null) {

						giftInfo.setVendorname(ele
								.getElementsByTagName(Constants.Vendorname).item(0)
								.getTextContent());

						
					}

					if (ele.getElementsByTagName(Constants.Vendorlogo).item(0)
							.getTextContent() != null) {
						giftInfo.setVendorlogo(ele
								.getElementsByTagName(Constants.Vendorlogo).item(0)
								.getTextContent());
					}
					if (ele.getElementsByTagName(Constants.Vendorname).item(0)
							.getTextContent() != null) {

						giftInfo.setVendorname(ele
								.getElementsByTagName(Constants.Vendorname).item(0)
								.getTextContent());

						
					}

					if (ele.getElementsByTagName(Constants.VendorId).item(0)
							.getTextContent() != null) {
						giftInfo.setVendorId(ele
								.getElementsByTagName(Constants.VendorId).item(0)
								.getTextContent());
					}
				/*	if (ele.getElementsByTagName(Constants.Featured).item(0)
							.getTextContent() != null) {

						giftInfo.setFeatured(ele
								.getElementsByTagName(Constants.Featured).item(0)
								.getTextContent());

						
					}*/

				/*	if (ele.getElementsByTagName(Constants.Vendoradress).item(0)
							.getTextContent() != null) {
						giftInfo.setVendoradress(ele
								.getElementsByTagName(Constants.Vendoradress).item(0)
								.getTextContent());
					}*/
					
					if (ele.getElementsByTagName(Constants.Account).item(0)
							.getTextContent() != null) {
						giftInfo.setAccount(ele
								.getElementsByTagName(Constants.Account).item(0)
								.getTextContent());
					}
					
					if (ele.getElementsByTagName(Constants.lat).item(0)
							.getTextContent() != null) {
						giftInfo.setLat(ele
								.getElementsByTagName(Constants.lat).item(0)
								.getTextContent());
					}
					
					if (ele.getElementsByTagName(Constants.lon).item(0)
							.getTextContent() != null) {
						giftInfo.setLon(ele
								.getElementsByTagName(Constants.lon).item(0)
								.getTextContent());
					}
					// SingleToneClass.giftInfoList.add(giftInfo);
					Instancevalues.featurevendorlist.add(giftInfo);

				} else {
					Log.i("else ", "Condition");
				}

			}

			/*
			 * if(!isMore)
			 * SingleToneClass.getInstance().setGiftInfoList(arrayList);
			 */
		}

		else {
			ConstantValues.isScrolling = false;
		}
	}

	public void parsegetofferlist(InputStream xml, boolean isMore) throws Exception {
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();
		// SingleToneClass.giftInfoList
		Instancevalues.VendorOfferlist.clear();
		NodeList nodeList = document.getElementsByTagName(Constants.Vendorofferparse);

		if (nodeList != null && nodeList.getLength() > 0) {
			
			ConstantValues.isScrolling = true;
			for (int i = 0; i < nodeList.getLength(); i++) {

				Node result = nodeList.item(i);
				Offer giftInfo= new Offer();

			
				if (result.getNodeType() == Node.ELEMENT_NODE) {
					Element ele = (Element) result;

					if (ele.getElementsByTagName(Constants.Couponid).item(0)
							.getTextContent() != null) {

						giftInfo.setCoupon_id(ele
								.getElementsByTagName(Constants.Couponid).item(0)
								.getTextContent());

						
					}

					if (ele.getElementsByTagName(Constants.ShortDesc).item(0)
							.getTextContent() != null) {
						giftInfo.setShort_desc(ele
								.getElementsByTagName(Constants.ShortDesc).item(0)
								.getTextContent());
					}
					if (ele.getElementsByTagName(Constants.VendorID).item(0)
							.getTextContent() != null) {

						giftInfo.setVendor_id(ele
								.getElementsByTagName(Constants.VendorID).item(0)
								.getTextContent());

						
					}


					if (ele.getElementsByTagName(Constants.Vendorlogo).item(0)
							.getTextContent() != null) {
						giftInfo.setVendorlogo(ele
								.getElementsByTagName(Constants.Vendorlogo).item(0)
								.getTextContent());
					}
					if (ele.getElementsByTagName(Constants.Expiredate).item(0)
							.getTextContent() != null) {
						giftInfo.setExpire_date(ele
								.getElementsByTagName(Constants.Expiredate).item(0)
								.getTextContent());
					}
					if (ele.getElementsByTagName(Constants.RemainingReedm).item(0)
							.getTextContent() != null) {

						giftInfo.setRemaining_redem(ele
								.getElementsByTagName(Constants.RemainingReedm).item(0)
								.getTextContent());

						
					}

					if (ele.getElementsByTagName(Constants.Vendornameoffer).item(0)
							.getTextContent() != null) {

						giftInfo.setVendor_name(ele
								.getElementsByTagName(Constants.Vendornameoffer).item(0)
								.getTextContent());

						
					}

				
					// SingleToneClass.giftInfoList.add(giftInfo);
					Instancevalues.VendorOfferlist.add(giftInfo);

				} else {
					Log.i("else ", "Condition");
				}

			}

			/*
			 * if(!isMore)
			 * SingleToneClass.getInstance().setGiftInfoList(arrayList);
			 */
		}

		else {
			ConstantValues.isScrolling = false;
		}
	}

	public void parsegetfeaturevendorlistbyDistance(InputStream xml,
			boolean isMore) throws Exception {
		// TODO Auto-generated method stub
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();
		// SingleToneClass.giftInfoList
		NodeList nodeList = document.getElementsByTagName(Constants.vendorparse);
		Instancevalues.VendorDistancelist.clear();
		if (nodeList != null && nodeList.getLength() > 0) {
			
			ConstantValues.isScrolling = true;
			for (int i = 0; i < nodeList.getLength(); i++) {

				Node result = nodeList.item(i);
				Vendor  giftInfo= new Vendor();

			
				if (result.getNodeType() == Node.ELEMENT_NODE) {
					Element ele = (Element) result;

					if (ele.getElementsByTagName(Constants.Vendorname).item(0)
							.getTextContent() != null) {

						giftInfo.setVendorname(ele
								.getElementsByTagName(Constants.Vendorname).item(0)
								.getTextContent());

						
					}

					if (ele.getElementsByTagName(Constants.Vendorlogo).item(0)
							.getTextContent() != null) {
						giftInfo.setVendorlogo(ele
								.getElementsByTagName(Constants.Vendorlogo).item(0)
								.getTextContent());
					}
					if (ele.getElementsByTagName(Constants.Vendoradress).item(0)
							.getTextContent() != null) {

						giftInfo.setVendoradress(ele
								.getElementsByTagName(Constants.Vendoradress).item(0)
								.getTextContent());

						
					}
					if (ele.getElementsByTagName("phone").item(0)
							.getTextContent() != null) {

						giftInfo.setVendorphone(ele
								.getElementsByTagName("phone").item(0)
								.getTextContent());

						
					}
					if (ele.getElementsByTagName(Constants.VendorId).item(0)
							.getTextContent() != null) {
						giftInfo.setVendorId(ele
								.getElementsByTagName(Constants.VendorId).item(0)
								.getTextContent());
					}
				/*	if (ele.getElementsByTagName(Constants.Featured).item(0)
							.getTextContent() != null) {

						giftInfo.setFeatured(ele
								.getElementsByTagName(Constants.Featured).item(0)
								.getTextContent());

						
					}*/

				/*	if (ele.getElementsByTagName(Constants.Vendoradress).item(0)
							.getTextContent() != null) {
						giftInfo.setVendoradress(ele
								.getElementsByTagName(Constants.Vendoradress).item(0)
								.getTextContent());
					}*/
					
					if (ele.getElementsByTagName(Constants.Account).item(0)
							.getTextContent() != null) {
						giftInfo.setAccount(ele
								.getElementsByTagName(Constants.Account).item(0)
								.getTextContent());
					}
					
					if (ele.getElementsByTagName(Constants.lat).item(0)
							.getTextContent() != null) {
						giftInfo.setLat(ele
								.getElementsByTagName(Constants.lat).item(0)
								.getTextContent());
					}
					
					if (ele.getElementsByTagName(Constants.lon).item(0)
							.getTextContent() != null) {
						giftInfo.setLon(ele
								.getElementsByTagName(Constants.lon).item(0)
								.getTextContent());
					}
					// SingleToneClass.giftInfoList.add(giftInfo);
					Instancevalues.VendorDistancelist.add(giftInfo);

				} else {
					Log.i("else ", "Condition");
				}

			}

			/*
			 * if(!isMore)
			 * SingleToneClass.getInstance().setGiftInfoList(arrayList);
			 */
		}

		else {
			ConstantValues.isScrolling = false;
		}		documentBuilderFactory = DocumentBuilderFactory.newInstance();
			}

}
