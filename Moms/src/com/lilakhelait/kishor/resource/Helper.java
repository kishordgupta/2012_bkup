package com.lilakhelait.kishor.resource;

import java.util.ArrayList;

import android.util.Log;

public class Helper {

	public static ArrayList<Wish> helper(String resource) {

		ArrayList<Wish> list = new ArrayList<Wish>();
		resource = resource.replace("*", "");
		resource = resource.replace("  ", "");
		String[] trimed = resource.split("7#7#");

		for (String s : trimed) {

			if (s.length() >= 2) {
				Wish wish = new Wish();
				wish.setWishtext(s);

				list.add(wish);
			}
		}

		return list;
	}

	public static ArrayList<Wish> helperof(String resource) {
		// TODO Auto-generated method stub
		ArrayList<Wish> list = new ArrayList<Wish>();
		resource = resource.replace("*", "");
		resource = resource.replace("  ", "");
		String[] trimed = resource.split("7#7#");

		for (String s : trimed) {

			if (s.contains("8#8#x")) {
				String[] h = s.split("8#8#x");
				Wish wish = new Wish();
				wish.setTitle(h[0]);
				wish.setWishtext(h[1]);

				list.add(wish);
			}
		}

		return list;
	}
}
