package com.example.mars;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiProxy {
	
	public static JSONObject user;
	
	public static String ForgotPass(String email){
		String response = HttpUtils.DownloadUtf8String(String.format("http://marsapparel.co.jp/api/forgot-password.php?email=%s", email));
		try {
			JSONObject obj = new JSONObject(response);
			return obj.getString("notification");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return "Request failed, please try again later";
	}
	
	
	public static String Signup(String fname, String lname, String email, String pass){
		List<NameValuePair> postParams = new ArrayList<NameValuePair>(4);
		postParams.add(new BasicNameValuePair("fname", fname));
		postParams.add(new BasicNameValuePair("lname", lname));
		postParams.add(new BasicNameValuePair("email", email));
		postParams.add(new BasicNameValuePair("pass", pass));
		
		try {
			String response = HttpUtils.executeHttpPostAsString("http://marsapparel.co.jp/api/registration.php", postParams);
			JSONObject obj = new JSONObject(response);
			return obj.getString("reg");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Signup failed!";
	}
	
	
	public static Boolean Login(String email, String pass){
		List<NameValuePair> postParams = new ArrayList<NameValuePair>(2);
		postParams.add(new BasicNameValuePair("email", email));
		postParams.add(new BasicNameValuePair("pass", pass));
        try {
			String response = HttpUtils.executeHttpPostAsString("http://marsapparel.co.jp/api/login.php", postParams);
			JSONObject obj = new JSONObject(response);
			user = obj.getJSONObject("user");
			if(user.has("user_id")){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return false;
	}
	
	
	public static JSONArray GetMenu(){
		String response = HttpUtils.DownloadUtf8String("http://marsapparel.co.jp/api/menu.php");
		JSONArray category = null;
		try {
			JSONObject obj = new JSONObject(response);
			category = obj.getJSONArray("category");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return category;
	}
	
	public static JSONArray GetWishList(){
		JSONArray wishlist = new JSONArray();
		try {
			String response = HttpUtils.DownloadUtf8String(String.format("http://marsapparel.co.jp/api/wishlist.php?uid=%s", user.getString("user_id")));
			JSONObject obj = new JSONObject(response);
			JSONArray wishes = obj.getJSONArray("wishlist");
			for(int i=0; i<wishes.length(); i++){
				JSONObject w = wishes.getJSONObject(i);
				JSONObject p = GetProductDetails(w.getString("pid"));
				wishlist.put(p);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wishlist;
	}
	
	public static JSONArray GetInbox(){
		JSONArray inbox = null;
		try{
			String response = HttpUtils.DownloadUtf8String(String.format("http://marsapparel.co.jp/api/inbox.php?uid=%s", user.getString("user_id")));
			JSONObject obj = new JSONObject(response);
			return obj.getJSONArray("inbox");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return inbox;
	}
	

	public static JSONArray GetCategory(String typeId){
		String response = HttpUtils.DownloadUtf8String(String.format("http://marsapparel.co.jp/api/category.php?typeId=%s", typeId));
		JSONArray category = null;
		try {
			JSONObject obj = new JSONObject(response);
			category = obj.getJSONArray("category");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return category;
	}
	
	public static JSONArray GetSubCategory(String catId){
		String response = HttpUtils.DownloadUtf8String(String.format("http://marsapparel.co.jp/api/subcategory.php?catId=%s", catId));
		JSONArray subcategory = null;
		try {
			JSONObject obj = new JSONObject(response);
			subcategory = obj.getJSONArray("subcategory");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return subcategory;
	}
	
	public static JSONArray GetProduct(String catId){
		String response = HttpUtils.DownloadUtf8String(String.format("http://marsapparel.co.jp/api/product.php?catId=%s", catId));
		JSONArray product = null;
		try {
			JSONObject obj = new JSONObject(response);
			product = obj.getJSONArray("product");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return product;
	}
	
	public static JSONObject GetProductDetails(String pid){
		String response = HttpUtils.DownloadUtf8String(String.format("http://marsapparel.co.jp/api/product-details.php?pid=%s", pid));
		JSONObject product_details = null;
		try{
			JSONObject obj = new JSONObject(response);
			product_details = obj.getJSONObject("product_details");
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		return product_details;
	}
	
}
