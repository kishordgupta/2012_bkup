package common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GetDate {

	public static String get(){ 
	SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
	String newtime =  sdfDateTime.format(new Date(System.currentTimeMillis()));
	return newtime;
	}
}
