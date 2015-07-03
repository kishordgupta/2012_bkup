package com.ssc.bd2014.result.exam;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class APIFactory {
	
	static ArrayList<NameValuePair> nameValuePairs;



	public static ArrayList<NameValuePair> get_buy_gift_list(String string,
			String string2) {
		// TODO Auto-generated method stub
        nameValuePairs = new ArrayList<NameValuePair>(6);
		
		nameValuePairs.add(new BasicNameValuePair("sr", "2"));
		nameValuePairs.add(new BasicNameValuePair("et", "2"));
		nameValuePairs.add(new BasicNameValuePair("exam", "ssc"));
		nameValuePairs.add(new BasicNameValuePair("year", "2014"));
		nameValuePairs.add(new BasicNameValuePair("board", string));
		nameValuePairs.add(new BasicNameValuePair("roll", string2));
		
		return nameValuePairs;
	
	}

}//end of main class