package com.kd.kdstagemanagement.helper;

public class Timereturn {

	public static int timereturn(String s)
	{
		int time=0;
		String[] spil=s.split(":");
		 time=Integer.parseInt(spil[0])*60;
		 time = time + (int)Double.parseDouble(spil[1]);;
		return time;
	}
	
	public static int lengtheturn(String s)
	{
		int time=0;
		String[] spil=s.split(":");
		 time=Integer.parseInt(spil[0])*60;
		 time = time + Integer.parseInt(spil[1])*60;;
		 time = time + (int)Double.parseDouble(spil[2]);
		return time;
	}
}
