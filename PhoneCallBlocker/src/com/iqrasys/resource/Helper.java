package com.iqrasys.resource;

import java.util.ArrayList;

import com.iqrasys.callblocker.model.PhoneNumberContainer;

import android.util.Log;

public class Helper {

	public static ArrayList<PhoneNumberModel> helper(String resource) {

		ArrayList<PhoneNumberModel> list = new ArrayList<PhoneNumberModel>();
		String[] trimed = resource.split("\n");

		for (String s : trimed) {
			String[] k = s.split(",");
			if (k.length >= 2) {
				PhoneNumberModel wish = new PhoneNumberModel();
				wish.setTitleText(k[0].replace("  ", ""));
				wish.setNumberText(k[1].replace("  ", ""));
				PhoneNumberContainer.serverNumber.add(wish);
				Log.d("ddf", wish.getTitleText() + wish.getNumberText());
			}
		}

		return list;
	}
}
