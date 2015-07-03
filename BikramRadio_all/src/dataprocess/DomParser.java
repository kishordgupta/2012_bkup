package dataprocess;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.util.Log;



public class DomParser {

	private DocumentBuilderFactory documentBuilderFactory;
	private DocumentBuilder documentBuilder;
	private Document document;



	public void parseuserlogin(String xml) throws Exception {
/*		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xml);
		document.getDocumentElement().normalize();*/
		// SingleToneClass.giftInfoList
		  try {
			  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			    DocumentBuilder db = dbf.newDocumentBuilder();
			    Document doc = db.parse(xml);
			    doc.getDocumentElement().normalize();

			    NodeList nodeList = doc.getElementsByTagName("item");

			    final List<String> urlList = new ArrayList<String>();

			    for (int i = 0; i < nodeList.getLength(); i++) {

			    Node node = nodeList.item(i);



			    Element fstElmnt = (Element) node;
			    NodeList nameList = fstElmnt.getElementsByTagName("urlslide");
			    Element nameElement = (Element) nameList.item(0);
			    nameList = nameElement.getChildNodes();

			    urlList.add(((Node) nameList.item(0)).getNodeValue());

			    } } catch (SAXException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          } catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }

			}

			/*
			 * if(!isMore)
			 * SingleToneClass.getInstance().setGiftInfoList(arrayList);
			 */
		

		
	}
 
	

