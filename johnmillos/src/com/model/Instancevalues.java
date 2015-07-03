package com.model;

import java.util.ArrayList;

public class Instancevalues {

	static public User currentuser = null;
	static public ArrayList<Category> categorylist = null;
	static public ArrayList<Vendor> currentvendorlist =null;
	static public ArrayList<Vendor> featurevendorlist =null;
	static public ArrayList<Offer> VendorOfferlist =null;
	static public ArrayList<Vendor> VendorDistancelist =null;
	static public String currentcategoryname ="";
	static public String Error_message="";
	static public String Currentadress="";
	static public boolean isErrormessage=false;
	static public boolean firstrun=true;
	static public String Lat="";
	static public String lon="";
	static public String currentvendor="";
	static public Vendor currentVendor =null;
	static public boolean user=false;
	static public String searchtext="";
	
	static public Double userLat=41.815665;
	static public Double userlon=-87.9480885;
	
	public static void initialize()
	{
		
		currentuser = new User();
		currentuser.UserID=112+"";
		categorylist = new ArrayList<Category>();
		
		currentvendorlist=new ArrayList<Vendor>();
		
		featurevendorlist=new ArrayList<Vendor>();
		
	    VendorOfferlist =new ArrayList<Offer>();
	    
	    VendorDistancelist=new ArrayList<Vendor>();
	}
}
