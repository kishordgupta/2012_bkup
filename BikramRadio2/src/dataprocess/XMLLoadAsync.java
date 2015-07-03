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
			String url = params[0];
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
				Toast.makeText(MainActivity.c, "Get stations list failed...", Toast.LENGTH_LONG)
				.show();
				SharedPreferences prefs = MainActivity.c.getSharedPreferences(
						"com.example.app", Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = prefs.edit();
				editor.putString("Radio_tag","0");
				editor.commit();
				((Activity) Firstactivity.con).finish();
				return;
			}
			
			//Process content of XML file
			String[] mXMLContent = result.split("<channel>");
			String channeltex="<channel>" +  mXMLContent[1].replace("station", "");
			
			String[] mXMLContent1 = mXMLContent[0].split("<djphoto>");
			String Djphotos="<djphoto>" +  mXMLContent1[1].replace("station", "");
			
			ArrayList<Station> stations = new ArrayList<Station>();
			Station station = null;
			String text = "";
			
			
			XmlPullParserFactory factory = null;
			XmlPullParser parser = null;
			
			
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
					
						break;
					case XmlPullParser.TEXT:
						text = parser.getText();
						break;
					case XmlPullParser.END_TAG:
					
						
						if (tagName.equalsIgnoreCase("fblink")) {
							Firstactivity.facebook=text;
						} 
						if (tagName.equalsIgnoreCase("twitter")) {
							Firstactivity.twitter=text;
						}
						if (tagName.equalsIgnoreCase("website")) {
							Firstactivity.web=text;
						}
						if (tagName.equalsIgnoreCase("admobid")) {
							Firstactivity.admobid=text;
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
				Log.d(TAG, "XML content " + Djphotos);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			try {
				factory = XmlPullParserFactory.newInstance();
				factory.setNamespaceAware(true);
				parser = factory.newPullParser();
				
				parser.setInput(new StringReader(channeltex));
				
				int eventType = parser.getEventType();
				while (eventType != XmlPullParser.END_DOCUMENT) {
					String tagName = parser.getName();
					switch (eventType) {
					case XmlPullParser.START_TAG:
						if (tagName.equalsIgnoreCase("item")) {
							station = new Station();
						}
						break;
					case XmlPullParser.TEXT:
						text = parser.getText();
						break;
					case XmlPullParser.END_TAG:
						if (tagName.equalsIgnoreCase("item")) {
							stations.add(station);
						} else if (tagName.equalsIgnoreCase("title")) {
							if (station != null)
								station.setmName(text);
						} else if (tagName.equalsIgnoreCase("link")) {
							station.setmStreamUrl(text);
						} else if (tagName.equalsIgnoreCase("img")) {
							station.setIcon(text);
						}
						
						if (tagName.equalsIgnoreCase("fblink")) {
							Firstactivity.facebook=text;
						} 
						if (tagName.equalsIgnoreCase("twitter")) {
							Firstactivity.twitter=text;
						}
						if (tagName.equalsIgnoreCase("website")) {
							Firstactivity.web=text;
						}
						if (tagName.equalsIgnoreCase("admobid")) {
							Firstactivity.admobid=text;
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
				Log.d(TAG, "XML content " + channeltex);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			ArrayList<Djphotos> djphotosarray = new ArrayList<Djphotos>();
			Djphotos djs = null;
			
			try {
				factory = XmlPullParserFactory.newInstance();
				factory.setNamespaceAware(true);
				parser = factory.newPullParser();
				
				parser.setInput(new StringReader(Djphotos));
				
				int eventType = parser.getEventType();
				while (eventType != XmlPullParser.END_DOCUMENT) {
					String tagName = parser.getName();
					switch (eventType) {
					case XmlPullParser.START_TAG:
						if (tagName.equalsIgnoreCase("item")) {
							djs = new Djphotos();
						}
						break;
					case XmlPullParser.TEXT:
						text = parser.getText();
						break;
					case XmlPullParser.END_TAG:
						if (tagName.equalsIgnoreCase("item")) {
							djphotosarray.add(djs);
						} else if (tagName.equalsIgnoreCase("title")) {
							if (djs != null)
								djs.setmName(text);
						} else if (tagName.equalsIgnoreCase("link")) {
							djs.setmStreamUrl(text);
						} else if (tagName.equalsIgnoreCase("img")) {
							djs.setIcon(text);
						}
						
						if (tagName.equalsIgnoreCase("fblink")) {
							Firstactivity.facebook=text;
						} 
						if (tagName.equalsIgnoreCase("twitter")) {
							Firstactivity.twitter=text;
						}
						if (tagName.equalsIgnoreCase("website")) {
							Firstactivity.web=text;
						}
						if (tagName.equalsIgnoreCase("admobid")) {
							Firstactivity.admobid=text;
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
				Log.d(TAG, "XML content " + Djphotos);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Imageurl.newyearsvalues.clear();
			for (Djphotos djphotos2 : djphotosarray) {
				Imageurl.newyearsvalues.add(djphotos2.getIcon());
			}
			//Load the list and notify adapter
			MainActivity.mStations.clear();
			MainActivity.mStations = stations;
			Firstactivity.con.startActivity(new Intent(Firstactivity.con, MainActivity.class));
			((Activity) Firstactivity.con).finish();
			}
			catch(Exception e)
			{
				SharedPreferences prefs = MainActivity.c.getSharedPreferences(
						"com.example.app", Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = prefs.edit();
				editor.putString("Radio_tag","0");
				editor.commit();
				((Activity) Firstactivity.con).finish();
			}
			//mStationsFragment.setStations(mStations);
		}
	

}
