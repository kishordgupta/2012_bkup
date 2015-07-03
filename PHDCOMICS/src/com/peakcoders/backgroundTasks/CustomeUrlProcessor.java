package com.peakcoders.backgroundTasks;

import java.util.HashMap;

public class CustomeUrlProcessor {

	public CustomeUrlProcessor() {

	}

	/*public class MyCustomeUrl {
		private String fullLenthUrl;
		private HashMap<String, String> parameters = new HashMap<String, String>();
		// private HashMap<String, String> nameValuepair = new HashMap<String,
		// String>();
	}
*/
	/*public MyCustomeUrl getCustomeUrl() {
		return null;
	}*/

	public String getFullUrl(HashMap<String, String> paramsAndValues) {

		String fullUrl = ConstantValues.WEBSERVICE_BASE_URL;

		String[] keys = new String[paramsAndValues.size()];
		String[] values = new String[paramsAndValues.size()];
		int countKeys = 0;
		for (String key : paramsAndValues.keySet()) {
			keys[countKeys] = key;
			countKeys++;
		}
		countKeys = 0;
		for (String value : paramsAndValues.values()) {
			values[countKeys] = value;
			countKeys++;
		}

		fullUrl += "?" + keys[0] + "=" + values[0];
		for (int i = 1; i < keys.length; i++) {
			fullUrl += "&";
			fullUrl += keys[i];
			fullUrl += "=";
			fullUrl += values[i];
		}

		return fullUrl;

	}

}
