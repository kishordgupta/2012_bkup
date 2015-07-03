package com.atomix.kurowiz.xmlparser;

import java.util.ArrayList;
import java.util.Currency;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.model.Constants;
import com.model.Instancevalues;

public class APIFactory {
	
	static ArrayList<NameValuePair> nameValuePairs;

	public ArrayList<NameValuePair> userlogin(String email, String password) 
	{
		nameValuePairs = new ArrayList<NameValuePair>(4);
		
		nameValuePairs.add(new BasicNameValuePair(Constants.apiname, Constants.user_login));
		nameValuePairs.add(new BasicNameValuePair(Constants.outputtpe, Constants.outputtypevalue));
		
		nameValuePairs.add(new BasicNameValuePair(Constants.Email, email));
		nameValuePairs.add(new BasicNameValuePair(Constants.password, password));
		
		return nameValuePairs;
	}
	
	public ArrayList<NameValuePair> getfeaturevendo() 
	{
		nameValuePairs = new ArrayList<NameValuePair>(3);
		
		nameValuePairs.add(new BasicNameValuePair(Constants.apiname, Constants.get_featured_vendor_list));
		nameValuePairs.add(new BasicNameValuePair(Constants.outputtpe, Constants.outputtypevalue));
		
		nameValuePairs.add(new BasicNameValuePair(Constants.userid, Instancevalues.currentuser.getUserID()));
	
		
		return nameValuePairs;
	}
	
	public ArrayList<NameValuePair> userregistration(String email, String password , String fname, String lastname, String birthyear, String birthmoth,String notifyemail) 
	{
		nameValuePairs = new ArrayList<NameValuePair>(9);
		
		nameValuePairs.add(new BasicNameValuePair(Constants.apiname, Constants.user_registration));
		nameValuePairs.add(new BasicNameValuePair(Constants.outputtpe, Constants.outputtypevalue));
		
		nameValuePairs.add(new BasicNameValuePair(Constants.Email, email));
		nameValuePairs.add(new BasicNameValuePair(Constants.password, password));
		nameValuePairs.add(new BasicNameValuePair(Constants.Firstname, fname));
		nameValuePairs.add(new BasicNameValuePair(Constants.LastName, lastname));
		nameValuePairs.add(new BasicNameValuePair(Constants.birthyear, birthyear));
		nameValuePairs.add(new BasicNameValuePair(Constants.Birthmonth, birthmoth));
		nameValuePairs.add(new BasicNameValuePair(Constants.NotifyEmail, notifyemail));
		
		return nameValuePairs;
	}
	
	public ArrayList<NameValuePair> getcategorylist(String uid,int page) 
	{
		nameValuePairs = new ArrayList<NameValuePair>(3);
		
		nameValuePairs.add(new BasicNameValuePair(Constants.apiname, Constants.get_category_list));
		nameValuePairs.add(new BasicNameValuePair(Constants.outputtpe, Constants.outputtypevalue));
		nameValuePairs.add(new BasicNameValuePair(Constants.pagenumber, page+""));
		
		nameValuePairs.add(new BasicNameValuePair(Constants.userid, uid));
		
		return nameValuePairs;
	}
	
	
	public ArrayList<NameValuePair> getofferlist(String uid,int page,String currentvendorid) 
	{
		nameValuePairs = new ArrayList<NameValuePair>(3);
		
		nameValuePairs.add(new BasicNameValuePair(Constants.apiname, Constants.get_offer_list));
		nameValuePairs.add(new BasicNameValuePair(Constants.outputtpe, Constants.outputtypevalue));
		nameValuePairs.add(new BasicNameValuePair(Constants.pagenumber, page+""));
		nameValuePairs.add(new BasicNameValuePair(Constants.VendorID, currentvendorid));
		nameValuePairs.add(new BasicNameValuePair(Constants.userid, uid));
		
		return nameValuePairs;
	}
	
	public ArrayList<NameValuePair> getsearchlist(String uid,int page) 
	{
		nameValuePairs = new ArrayList<NameValuePair>(3);
		
		nameValuePairs.add(new BasicNameValuePair(Constants.apiname, Constants.get_offer_list));
		nameValuePairs.add(new BasicNameValuePair(Constants.outputtpe, Constants.outputtypevalue));
		nameValuePairs.add(new BasicNameValuePair(Constants.pagenumber, page+""));
		nameValuePairs.add(new BasicNameValuePair(Constants.searchterm, Instancevalues.searchtext));
		nameValuePairs.add(new BasicNameValuePair(Constants.userid, uid));
		
		return nameValuePairs;
	}
	
	
	public ArrayList<NameValuePair> getvendorlist(String category, String uid ,int page) 
	{
		nameValuePairs = new ArrayList<NameValuePair>(5);
		
		nameValuePairs.add(new BasicNameValuePair(Constants.apiname, Constants.get_vendor_list));
		nameValuePairs.add(new BasicNameValuePair(Constants.outputtpe, Constants.outputtypevalue));
		nameValuePairs.add(new BasicNameValuePair(Constants.pagenumber, page+""));
		
		nameValuePairs.add(new BasicNameValuePair(Constants.category, category));
		nameValuePairs.add(new BasicNameValuePair(Constants.userid, uid));

		
		return nameValuePairs;
	}
	
	public ArrayList<NameValuePair> getvendorlistbydistance(String radius, String lat ,String lng, int page) 
	{
		nameValuePairs = new ArrayList<NameValuePair>(5);
		
		nameValuePairs.add(new BasicNameValuePair(Constants.apiname, Constants.get_vendor_distance_list));
		nameValuePairs.add(new BasicNameValuePair(Constants.lat,lat));
		nameValuePairs.add(new BasicNameValuePair(Constants.pagenumber, page+""));

		nameValuePairs.add(new BasicNameValuePair(Constants.outputtpe, Constants.outputtypevalue));
		nameValuePairs.add(new BasicNameValuePair(Constants.lon, lng));
		nameValuePairs.add(new BasicNameValuePair(Constants.radious, radius));

		
		return nameValuePairs;
	}
	
	
}//end of main class