package com.kishordgupta.helper;

import java.util.Calendar;

import com.kishordgupta.model.Event;
import com.kishordgupta.model.Events;

public class Timestamp {

	public static void entryevent(String name)
	{
		Event event = new Event(); 
		event.setName(name);
		event.setTime(getcurrenttime());
		Events.eventlist.add(event);
	}
	
	private static String getcurrenttime()
	{
		Calendar cal = Calendar.getInstance(); 

		  int millisecond = cal.get(Calendar.MILLISECOND);
		  int second = cal.get(Calendar.SECOND);
		  int minute = cal.get(Calendar.MINUTE);
		        //12 hour format
		  int hour = cal.get(Calendar.HOUR);
		        //24 hour format
		  int hourofday = cal.get(Calendar.HOUR_OF_DAY);
		  
		  int day=cal.get(Calendar.DAY_OF_MONTH);
		  int month = cal.get(Calendar.MONTH);
		  int year = cal.get(Calendar.YEAR);
		  
		  String Datetimestamp=day+"-"+month+"-"+year+" "+hour+":"+minute+":"+second+"."+millisecond+" ";
			  return Datetimestamp;
	}
}
