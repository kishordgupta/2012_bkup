package dataprocess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import radioklub.sekhontech.com.entity.Djphotos;
import radioklub.sekhontech.com.entity.Station;

import backgroundTasks.Imageurl;

import com.example.swipeuiforupclose.Firstactivity;
import com.example.swipeuiforupclose.MainActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.URLUtil;
import android.widget.Toast;

public class XMLLoadAsync extends AsyncTask<String, Void, String> {
		String TAG="XMLPROC";
		@Override
		protected String doInBackground(String... params) {
			Log.d(TAG, "XMLLoadAsync creating...");
			String url = "https://dl.dropboxusercontent.com/s/0fw59o1mayew6iw/djimages.xml";
			String content = null;
			String line = null;
			try {
				if (URLUtil.isValidUrl(url)) {
					Log.d(TAG, "Url is valid");
					URL urlPage = new URL(url);
					HttpURLConnection connection = (HttpURLConnection) urlPage
							.openConnection();
					InputStream inputStream = connection.getInputStream();
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(inputStream));
					StringBuffer stringBuffer = new StringBuffer();
	
					while ((line = bufferedReader.readLine()) != null) {
						stringBuffer.append(line);
						Log.d(TAG, line);
					}
					content = stringBuffer.toString();
					connection.disconnect();
					bufferedReader.close();
					inputStream.close();
				} else {
					Log.d(TAG, "Invalid url");
					content = url;
				}
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return content;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			try{
			if (result == null) {
				Toast.makeText(MainActivity.c, "Get Ad list failed...", Toast.LENGTH_LONG)
				.show();
			
				return;
			}
			
			//Process content of XML fileString[] mXMLContent = result;
		
			String text = "";
			
			
			XmlPullParserFactory factory = null;
			XmlPullParser parser = null;
			
			
		
			
		
			
			ArrayList<Djphotos> djphotosarray = new ArrayList<Djphotos>();
			Djphotos djs = null;
			
			try {
				factory = XmlPullParserFactory.newInstance();
				factory.setNamespaceAware(true);
				parser = factory.newPullParser();
				
				parser.setInput(new StringReader(result));
				
				int eventType = parser.getEventType();
				while (eventType != XmlPullParser.END_DOCUMENT) {
					String tagName = parser.getName();
					switch (eventType) {
					case XmlPullParser.START_TAG:
						if (tagName.equalsIgnoreCase("imageurl")) {
							djs = new Djphotos();
						}
						break;
					case XmlPullParser.TEXT:
						text = parser.getText();
						break;
					case XmlPullParser.END_TAG:
						if (tagName.equalsIgnoreCase("imageurl")) {
							djs.setIcon(text);
							djphotosarray.add(djs);
						} 
						break;
					default:
						break;
					}
					eventType = parser.next();
				}
			} catch (XmlPullParserException e) {
				e.printStackTrace();
				Log.d(TAG, "XML parser failed " + e.getMessage());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			Imageurl.newyearsvalues.clear();
		//	Imageurl.newyearsvalues.add("https://scontent-a-sin.xx.fbcdn.net/hphotos-xpa1/v/t1.0-9/s720x720/10645082_522883727842063_5603471596769743905_n.jpg?");
			for (Djphotos djphotos2 : djphotosarray) {
				Imageurl.newyearsvalues.add(djphotos2.getIcon().trim());
				Log.d("KD", djphotos2.getIcon()+"");
			}
			//Load the list and notify adapterhttps://dl.dropboxusercontent.com/s/4xen3lqdg6deq6b/DJ2.jpg
		
			}
			catch(Exception e)
			{
			
			}
			//mStationsFragment.setStations(mStations);
		}
	

}
