
package com.example.mars;

import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class HttpUtils {

	public static final int HTTP_TIMEOUT = 60 * 1000;

	private static HttpClient mHttpClient;

	/**
	 * Calling this function will get the default HttpClient. This is a
	 * SingleClientConnectionManager.
	 * 
	 * @return The DefaultHttpClient object
	 */
	private static HttpClient getHttpClient() {
		if (mHttpClient == null) {
			mHttpClient = new DefaultHttpClient();
			final HttpParams params = mHttpClient.getParams();
			HttpConnectionParams.setConnectionTimeout(params, HTTP_TIMEOUT);
			HttpConnectionParams.setSoTimeout(params, HTTP_TIMEOUT);
			ConnManagerParams.setTimeout(params, HTTP_TIMEOUT);
		}
		return mHttpClient;
	}

	/**
	 * Downloads the content from given url and returns as a String. This
	 * function is synchronised which means at a time only one thread can
	 * download data using this client. Subsequent calls will block the calling
	 * thread untill the current one been finished.
	 * 
	 * @param url
	 *            <String> The url from where data to be fetched.
	 * @return The content of the page which url is given
	 */
	public synchronized static String DownloadUtf8String(String url) {
		Log.e("Http Request", url);
		String ret = null;
		try {
			//HttpClient client = getHttpClient();
			HttpClient client = new DefaultHttpClient();
			HttpParams params = client.getParams();
			HttpConnectionParams.setConnectionTimeout(params, HTTP_TIMEOUT);
			HttpConnectionParams.setSoTimeout(params, HTTP_TIMEOUT);
			ConnManagerParams.setTimeout(params, HTTP_TIMEOUT);
						
			HttpGet request = new HttpGet(url.replace(" ", "%20"));
			HttpResponse response = client.execute(request);
			ret = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}


	
	public static String executeHttpPostAsString(String url,
			List<NameValuePair> postParameters) throws Exception {
		HttpClient client = getHttpClient();
		HttpPost request = new HttpPost(url);
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
				postParameters);
		request.setEntity(formEntity);
		HttpResponse response = client.execute(request);
		String result = EntityUtils.toString(response.getEntity());
		String str = "";
		for (int i = 0; i < postParameters.size(); i++) {
			str = str + "&" + postParameters.get(i).getName() + "="
					+ postParameters.get(i).getValue();
		}
		Log.i("DREG", "PARAM=" + str);
		Log.i("DREG", "RESULT" + result);
		return result;
	}
	
	
}
