package com.example.steaming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

public class GetDataOfPage extends AsyncTask<String, String, String> {
	@Override
	protected String doInBackground(String... params) {
		Log.d("AsyncHttpRequestPull", "in background");
		Looper.prepare();
	    try {
	    	HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet("http://www.ardigitalmedia.com/show/southasiantrend");
            HttpResponse response = client.execute(request);

            String html = "";
            InputStream in = response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new     InputStreamReader(in));
            StringBuilder str = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null)
            {
                str.append(line);
            }
            in.close();
            //getting page source
            html = str.toString();              
            Log.i("AsyncHttpRequestPull", html);
            //commented as currently not taking of a div 
            Document doc = Jsoup.parse(html);
//		      Document doc = Jsoup.connect("http://www.ardigitalmedia.com/show/southasiantrend").timeout(30000).get();
//		      Log.i("AsyncHttpRequestPull", "1");
////		      Element content = doc.select("#mp-itn").first();
//		      Log.i("AsyncHttpRequestPull", "2");
//		      return content.text();
//		      textView.setText(content.text());
		      MainActivity.string = html;
//		      Toast.makeText(MainActivity.context, content.text(),
//			          Toast.LENGTH_LONG).show();
//		      Log.i("AsyncHttpRequestPull", content.text());
		    } catch (IOException e) {
		      // Never e.printStackTrace(), it cuts off after some lines and you'll
		      // lose information that's very useful for debugging. Always use proper
		      // logging, like Android's Log class, check out
		      // http://developer.android.com/tools/debugging/debugging-log.html
		      Log.e("AsyncHttpRequestPull", "Failed to load HTML code", e);
		      // Also tell the user that something went wrong (keep it simple,
		      // no stacktraces):
		      Toast.makeText(MainActivity.context, "Failed to load HTML code",
		          Toast.LENGTH_SHORT).show();
		    }
		return null;
	}
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		Log.d("AsyncHttpRequestPull", "Post");
		MainActivity.callAfterValue();
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		Log.d("AsyncHttpRequestPull", "Pre");
	}


}
