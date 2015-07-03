package com.iqrasys.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.iqrasys.callblocker.model.PhoneNumberContainer;
import com.iqrasys.db.MyFile;
import com.iqrasys.helper.*;
import com.iqrasys.resource.PhoneNumberModel;
import com.kd.phonecall.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class StackParser extends Activity {
	/** Called when the activity is first created. */
	public String url = "http://project.iqrasys.se/communify/";
	public Context context = null;
	public static String datas = "";

	private ProgressDialog pd;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stack_parse_bg);
		context = this;

		if (GetNetworkStatus.isNetworkAvailable(context)) {
			pd = ProgressDialog.show(StackParser.this, "Var god vänta  ",
					"Databasen uppdateras", true, false);
			new ParseSite().execute(urlmaker(datas));
		} else {
			finish();
			Toast.makeText(context, R.string.msgNetworkNotAvailable,
					Toast.LENGTH_LONG).show();
		}
	}

	public String urlmaker(String data) {
		String Data = data.replace("  ", " ");
		Data = data.replace(" ", "+");
		Data = url + Data;

		return Data;
	}

	private class ParseSite extends AsyncTask<String, Void, List<String>> {

		@Override
		protected List<String> doInBackground(String... arg) {
			List<String> output = new ArrayList<String>();
			try {
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(arg[0]);
				ResponseHandler<String> resHandler = new BasicResponseHandler();
				String page = httpClient.execute(httpGet, resHandler);

				/************** Read XML *************/

				BufferedReader br = new BufferedReader(new StringReader(page));
				InputSource is = new InputSource(br);

				/************ Parse XML **************/

				XMLParser parser = new XMLParser();
				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser sp = null;
				try {
					sp = factory.newSAXParser();
				} catch (ParserConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SAXException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				XMLReader reader = null;
				try {
					reader = sp.getXMLReader();
				} catch (SAXException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				reader.setContentHandler(parser);
				try {
					reader.parse(is);
				} catch (SAXException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				/************* Get Parse data in a ArrayList **********/

				MyFile.listValues.clear();
				// int i=0;
				try {
					for (PhoneNumberModel numberMdl : parser.list) {
						String strValue = numberMdl.getTitleText() + ","
								+ numberMdl.getNumberText();
						MyFile.listValues.add(strValue);
						Log.d("urls", strValue);
					}

				} catch (ArrayIndexOutOfBoundsException e) {
					StackParser.datas = "Currently service unavailble";
				}

			} catch (ClientProtocolException e) {
				e.printStackTrace();
				pd.dismiss();

			} catch (IOException e) {
				e.printStackTrace();
				pd.dismiss();

			}

			return output;
		}

		@Override
		protected void onPostExecute(List<String> output) {
			pd.dismiss();
			// Imageurl.newyearsvalues=(ArrayList<String>) output;
			MyFile f = new MyFile(context);
			f.writeToSD("");
			PhoneNumberContainer.serverNumber.clear();

			String s = f.readFromSD();
			String[] lines = s.split("\n");

			for (String string : lines) {
				String[] listContact = string.split(",");
				if (listContact.length == 0)
					continue;

				PhoneNumberModel item = new PhoneNumberModel();

				if (listContact.length == 1) {
					item.setTitleText("Säljare");
					item.setNumberText(listContact[0]);
				} else if (listContact.length == 2) {
					item.setTitleText(listContact[0]);
					item.setNumberText(listContact[1]);
				}

				PhoneNumberContainer.serverNumber.add(item);
			}

			finish();
		}
	}
}