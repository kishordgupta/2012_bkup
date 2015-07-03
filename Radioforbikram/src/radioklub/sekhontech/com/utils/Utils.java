package radioklub.sekhontech.com.utils;

import android.text.Html;

public class Utils {
	
	public static String stripHtml(String s) {
		if (s != null) {
	    return Html.fromHtml(s).toString().replace('\n', (char) 32)
	        .replace((char) 160, (char) 32).replace((char) 65532, (char) 32).trim();
		} else {
			return "";
		}
	}
}
