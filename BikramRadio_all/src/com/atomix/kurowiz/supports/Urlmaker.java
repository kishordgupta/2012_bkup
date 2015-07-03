package com.atomix.kurowiz.supports;

import java.text.SimpleDateFormat;

public class Urlmaker {
	
	public static String Url  ="http://dl.dropboxusercontent.com/s/fqtibveljab1z0c/main_categories.xml";//http:";//xmlfeed.laterooms.com/index.aspx?aid=5854&rtype=4&kword=london&lim=20&night=2&sdate=2014-05-15&pag=1";
	public static String keyword  ="dhaka";
	public static String date  ="2017-05-15";
	public static String night ="2";
	public static  int pag =1;
	
	public static void urlmaker()
	{
		Url="http://dl.dropboxusercontent.com/s/fqtibveljab1z0c/main_categories.xml";
	//	Url = "http://xmlfeed.laterooms.com/index.aspx?aid=5854&rtype=4&kword="+keyword+"&lim=20&night="+night+"&sdate="+date+"&pag="+pag;
	}
	
	public static void urlmaker(String utl)
	{
		Url=utl;
	//	Url = "http://xmlfeed.laterooms.com/index.aspx?aid=5854&rtype=4&kword="+keyword+"&lim=20&night="+night+"&sdate="+date+"&pag="+pag;
	}
	

}
