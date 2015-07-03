package common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GetDateTime {

	public static String get(){ 
	SimpleDateFormat sdfDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US);
	String newtime =  sdfDateTime.format(new Date(System.currentTimeMillis()));
	return newtime;
	}
}
