package com.iqrasys.utility;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.format.DateFormat;
import android.widget.Toast;

public class ApplicationSecurityHandler {

	private Date stringToDate(String aDate) {

		if (aDate == null)
			return null;

		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss'Z'");
		try {
			date = format.parse(aDate);
			System.out.println(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;

	}

	private Date convertToExpDate(String dateString) {
		java.text.DateFormat dateFormat = java.text.DateFormat
				.getDateInstance();
		Date newDate = null;
		try {
			newDate = dateFormat.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Date newDate = stringToDate(dateString);
		if (newDate == null)
			return null;
		// Date newDate = new Date(lMilSec);
		int nYear = newDate.getYear();
		nYear += 1;
		newDate.setYear(nYear);

		// int nHour = newDate.getHours();
		// nHour += 1;
		// newDate.setHours(nHour);

		return newDate;
	}

	public boolean isValidationExpired(Context context) {
		SharedPreferences settings = context.getSharedPreferences("ph", 0);
		// Checking authentication time.
		Date dtToday = Calendar.getInstance().getTime();
		String mydate = java.text.DateFormat.getDateTimeInstance().format(
				dtToday);
		String strAuthDate = settings.getString("authenticationDate", mydate);
		Date dtExpire = convertToExpDate(strAuthDate);

		if (dtExpire == null) {
			Toast.makeText(context, "Datum format kan inte konverteras.",
					Toast.LENGTH_LONG).show();
			return true;
		}

		int nFlag = dtToday.compareTo(dtExpire);
		if (nFlag == 1) {
			return true;
		}

		return false;
	}
}
