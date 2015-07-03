package com.iqrasys.utility;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.iqrasys.resource.*;

public class XMLParser extends DefaultHandler {

	ArrayList<PhoneNumberModel> list = null;

	// string builder acts as a buffer
	StringBuilder builder;
	PhoneNumberModel numberValues = null;

	// Initialize the arraylist
	// @throws SAXException
	@Override
	public void startDocument() throws SAXException {

		/******* Create ArrayList To Store XmlValuesModel object ******/
		list = new ArrayList<PhoneNumberModel>();
	}

	// Initialize the temp XmlValuesModel object which will hold the parsed info
	// and the string builder that will store the read characters
	// @param uri
	// @param localName ( Parsed Node name will come in localName )
	// @param qName
	// @param attributes
	// @throws SAXException
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		/**** When New XML Node initiating to parse this function called *****/

		// Create StringBuilder object to store xml node value
		builder = new StringBuilder();

		if (localName.equals("channel")) {

			// Skip
		} else if (localName.equals("item")) {

			/********** Create Model Object *********/
			numberValues = new PhoneNumberModel();
		}
	}

	// Finished reading the login tag, add it to arraylist
	// @param uri
	// @param localName
	// @param qName
	// @throws SAXException

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (localName.equals("item")) {

			/** finished reading a job xml node, add it to the arraylist **/
			list.add(numberValues);

		} else if (localName.equalsIgnoreCase("title")) {
			numberValues.setTitleText(builder.toString());
		} else if (localName.equalsIgnoreCase("number")) {

			numberValues.setNumberText(builder.toString());
		}

	}

	// Read the value of each xml NODE
	// @param ch
	// @param start
	// @param length
	// @throws SAXException

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		/****** Read the characters and append them to the buffer ******/
		String tempString = new String(ch, start, length);
		builder.append(tempString);
	}

}
