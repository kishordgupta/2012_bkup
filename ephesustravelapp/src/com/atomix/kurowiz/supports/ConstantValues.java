package com.atomix.kurowiz.supports;

import java.util.ArrayList;

public class ConstantValues {

	
	public static boolean isBottomReached = true;
	public static boolean isScrolling = true;
	public static String APP_VERSION = "20131108";
	
	public final static String Learn_on_Ephesus="Learn on Ephesus";
	public final static String special_articles="special articles";
	public final static String tours_to_ephesus="tours to ephesus";
	public final static String deals_and_discounts_around_ephesus="deals and discounts around ephesus";
	public final static String attractions_around_ephesus="attractions around ephesus";
	public final static String about_us="about us";
	
	
	public static String currentcityname="";
	public static String currentreventname="";
	public static String curreantcategoryname="";
	
	public static ArrayList<CityInfo> eventinfolist = new ArrayList<CityInfo>();
	public static ArrayList<CityName> citynamelist = new ArrayList<CityName>();
	public static ArrayList<CategoryName> categoryNamelist = new ArrayList<CategoryName>();
	public static boolean isRecording = false;
	public static CityInfo curreantevent;
	public static String CategoryName;
}
