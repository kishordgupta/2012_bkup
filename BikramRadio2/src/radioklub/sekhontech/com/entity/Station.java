package radioklub.sekhontech.com.entity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import radioklub.sekhontech.com.utils.ParserM3UToURL;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

public class Station {
	
	//Member variables
	private String mIcon;
	private String mName;
	private String mStreamUrl;
	private String mWebsite;
	private String mFacebook;
	
	//Constructor
	public Station(){}
	
	public Station(String iconUrl, String name, String streamUrl,
			 String website, String facebook) {
		
		mName = name;
		mStreamUrl = streamUrl;
		mWebsite = website;
		mFacebook = facebook;
	}
	
	//Property access
	public String getIcon(){
		return mIcon;
	}

	public void setIcon(String mIconUrl) {
		this.mIcon = mIconUrl;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmStreamUrl() {
		return mStreamUrl;
	}
	public String getRealUrl(){
		if (mStreamUrl.contains(".m3u")) {
			return ParserM3UToURL.parse(mStreamUrl);
		} else {
			return mStreamUrl;
		}
	}

	public void setmStreamUrl(String mStreamUrl) {
		this.mStreamUrl = mStreamUrl;
	}
	public void setWebsite(String website) {
		mWebsite = website;
	}
	public void setFacebook(String facebook) {
		mFacebook = facebook;
	}
	public String getWebsite(){
		return mWebsite;
	}
	public String getFacebook() {
		return mFacebook;
	}
}
