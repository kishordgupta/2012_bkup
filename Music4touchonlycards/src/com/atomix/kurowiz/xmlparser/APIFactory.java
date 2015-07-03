package com.atomix.kurowiz.xmlparser;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class APIFactory {
	
	static ArrayList<NameValuePair> nameValuePairs;

	public ArrayList<NameValuePair> get_user_info(String funcId, String uid, String user_id) 
	{
		nameValuePairs = new ArrayList<NameValuePair>(3);
		
		nameValuePairs.add(new BasicNameValuePair("func_id", funcId));
		nameValuePairs.add(new BasicNameValuePair("uid", uid));
		nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
		
		return nameValuePairs;
	}
	
	public ArrayList<NameValuePair> user_info_regist(String funcId, String bundle, String uid, String uuid, String platformId) 
	{	
		nameValuePairs = new ArrayList<NameValuePair>(5);
		
		nameValuePairs.add(new BasicNameValuePair("func_id", funcId));
		nameValuePairs.add(new BasicNameValuePair("bundle", bundle));
		nameValuePairs.add(new BasicNameValuePair("uid", uid));
		nameValuePairs.add(new BasicNameValuePair("UIID", uuid));
		nameValuePairs.add(new BasicNameValuePair("platform_id", platformId));
		
		return nameValuePairs;
	}
	
	public ArrayList<NameValuePair> get_scratch_game_info(String funcId, String user_id) 
	{
		nameValuePairs = new ArrayList<NameValuePair>();
		
		nameValuePairs.add(new BasicNameValuePair("func_id", funcId));
		nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
		
		return nameValuePairs;
	}
	
	public ArrayList<NameValuePair> get_daily_chance_info(String funcId, String user_id) 
	{
		nameValuePairs = new ArrayList<NameValuePair>();
		
		nameValuePairs.add(new BasicNameValuePair("func_id", funcId));
		nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
		
		return nameValuePairs;
	}
	
	public ArrayList<NameValuePair> daily_chance_click(String funcId, String user_id) 
	{
		nameValuePairs = new ArrayList<NameValuePair>();
		
		nameValuePairs.add(new BasicNameValuePair("func_id", funcId));
		nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
		
		return nameValuePairs;
	}
	
	public ArrayList<NameValuePair> sns_cooperation(String user_id,String sns_id) 
	{
		nameValuePairs = new ArrayList<NameValuePair>();
		
		nameValuePairs.add(new BasicNameValuePair("func_id", "8"));
		nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
		nameValuePairs.add(new BasicNameValuePair("sns_id", sns_id));
		return nameValuePairs;
	}
	
	
	public ArrayList<NameValuePair> get_ad_info(String funcId, String user_id, String page, String ad_ids, String platform_id, String version) 
	{
		nameValuePairs = new ArrayList<NameValuePair>(7);
		
		nameValuePairs.add(new BasicNameValuePair("func_id", funcId));
		nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
		//nameValuePairs.add(new BasicNameValuePair("category_id", category_id));
		nameValuePairs.add(new BasicNameValuePair("page", page));
		nameValuePairs.add(new BasicNameValuePair("ad_ids", ad_ids));
		nameValuePairs.add(new BasicNameValuePair("platform_id", platform_id));
		nameValuePairs.add(new BasicNameValuePair("version", version));
		
		return nameValuePairs;
	}
	
	public ArrayList<NameValuePair> ad_user_entry(String funcId, String user_id, String ad_id) 
	{
		nameValuePairs = new ArrayList<NameValuePair>(3);
		
		nameValuePairs.add(new BasicNameValuePair("func_id", funcId));
		nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
		nameValuePairs.add(new BasicNameValuePair("ad_id", ad_id));
		
		return nameValuePairs;
	}
	
	public ArrayList<NameValuePair> set_guerrilla_group(String user_id, String group_id) 
	{
		nameValuePairs = new ArrayList<NameValuePair>();
		
		nameValuePairs.add(new BasicNameValuePair("func_id", "11"));
		nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
		nameValuePairs.add(new BasicNameValuePair("group_id", group_id));
		
		return nameValuePairs;
	}

	public ArrayList<NameValuePair> user_get_point_history(String funcId, String user_id, String page) 
	{
		nameValuePairs = new ArrayList<NameValuePair>(3);
		
		nameValuePairs.add(new BasicNameValuePair("func_id", funcId));
		nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
		nameValuePairs.add(new BasicNameValuePair("page", page));
		
		return nameValuePairs;
	}
	
	public ArrayList<NameValuePair> get_news(String funcId, String user_id) 
	{
		nameValuePairs = new ArrayList<NameValuePair>();
		
		nameValuePairs.add(new BasicNameValuePair("func_id", funcId));
		nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
		
		return nameValuePairs;
	}
	
	public ArrayList<NameValuePair> user_push_settings(String funcId, String user_id, String push_status) 
	{
		nameValuePairs = new ArrayList<NameValuePair>();
		
		nameValuePairs.add(new BasicNameValuePair("func_id", funcId));
		nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
		nameValuePairs.add(new BasicNameValuePair("push_status", push_status));
		
		return nameValuePairs;
	}
	
	public ArrayList<NameValuePair> get_help(String funcId, String user_id) 
	{
		nameValuePairs = new ArrayList<NameValuePair>();
		
		nameValuePairs.add(new BasicNameValuePair("func_id", funcId));
		nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
		
		return nameValuePairs;
	}

	public ArrayList<NameValuePair> get_buy_gift_list(String funcId, String user_id, String page) 
	{
		nameValuePairs = new ArrayList<NameValuePair>(3);
		
		nameValuePairs.add(new BasicNameValuePair("func_id", funcId));
		nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
		nameValuePairs.add(new BasicNameValuePair("page", page));
		
		return nameValuePairs;
	}

	public ArrayList<NameValuePair> check_urlscheme(String funcId, String user_id, String page, String platform_id) 
	{
		nameValuePairs = new ArrayList<NameValuePair>();
		
		nameValuePairs.add(new BasicNameValuePair("func_id", funcId));
		nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
		nameValuePairs.add(new BasicNameValuePair("page", page));
		//nameValuePairs.add(new BasicNameValuePair("category_id", category_id));
		nameValuePairs.add(new BasicNameValuePair("platform_id", platform_id));
		
		return nameValuePairs;
	}
	
	public ArrayList<NameValuePair> user_gift_code_purchase(String funcId, String user_id, String gift_type) 
	{
		nameValuePairs = new ArrayList<NameValuePair>(3);
		
		nameValuePairs.add(new BasicNameValuePair("func_id", funcId));
		nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
		nameValuePairs.add(new BasicNameValuePair("gift_type", gift_type));
		
		return nameValuePairs;
	}

	
	public ArrayList<NameValuePair> get_gift_ticket_info(String funcId, String user_id) 
	{
		nameValuePairs = new ArrayList<NameValuePair>();
		
		nameValuePairs.add(new BasicNameValuePair("func_id", funcId));
		nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
		
		return nameValuePairs;
	}
	
	public ArrayList<NameValuePair> set_device_token(String funcId, String user_id, String device_token) 
	{
		nameValuePairs = new ArrayList<NameValuePair>(3);
		
		nameValuePairs.add(new BasicNameValuePair("func_id", funcId));
		nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
		nameValuePairs.add(new BasicNameValuePair("device_token", device_token));
		
		return nameValuePairs;
	}

	public ArrayList<NameValuePair> get_app_version(String user_id, String version) 
	{
		nameValuePairs = new ArrayList<NameValuePair>();
		
		nameValuePairs.add(new BasicNameValuePair("func_id", "34"));
		nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
		nameValuePairs.add(new BasicNameValuePair("version", version));
		
		return nameValuePairs;
	}
	
	public ArrayList<NameValuePair> get_app_maintenance(String funcId, String user_id) 
	{
		nameValuePairs = new ArrayList<NameValuePair>();
		
		nameValuePairs.add(new BasicNameValuePair("func_id", funcId));
		nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
		
		return nameValuePairs;
	}

	public ArrayList<NameValuePair> get_invite_display_flag(String funcId, String user_id, String version) 
	{
		nameValuePairs = new ArrayList<NameValuePair>();
		
		nameValuePairs.add(new BasicNameValuePair("func_id", funcId));
		nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
		nameValuePairs.add(new BasicNameValuePair("version", version));
		
		return nameValuePairs;
	}
	
	public ArrayList<NameValuePair> gacha_info(String funcId, String user_id) 
	{
		nameValuePairs = new ArrayList<NameValuePair>();
		
		nameValuePairs.add(new BasicNameValuePair("func_id", funcId));
		nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
		
		return nameValuePairs;
	}

	public ArrayList<NameValuePair> gacha_play(String funcId, String user_id) 
	{
		nameValuePairs = new ArrayList<NameValuePair>();
		
		nameValuePairs.add(new BasicNameValuePair("func_id", funcId));
		nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
		
		return nameValuePairs;
	}
	
	public ArrayList<NameValuePair> get_new_ad(String funcId, String user_id) 
	{
		nameValuePairs = new ArrayList<NameValuePair>();
		
		nameValuePairs.add(new BasicNameValuePair("func_id", funcId));
		nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
		
		return nameValuePairs;
	}
	
	public ArrayList<NameValuePair> get_menu_setting(String funcId, String version, String platform_id) 
	{
		nameValuePairs = new ArrayList<NameValuePair>(3);
		
		nameValuePairs.add(new BasicNameValuePair("func_id", funcId));
		nameValuePairs.add(new BasicNameValuePair("version", version));
		nameValuePairs.add(new BasicNameValuePair("platform_id", platform_id));
		
		return nameValuePairs;
	}
	
	public ArrayList<NameValuePair> get_category_list(String funcId) 
	{
		nameValuePairs = new ArrayList<NameValuePair>(1);
		
		nameValuePairs.add(new BasicNameValuePair("func_id", funcId));
		
		return nameValuePairs;
	}
	
	public ArrayList<NameValuePair> get_countdown_time(String funcId, String user_id) 
	{
		nameValuePairs = new ArrayList<NameValuePair>(2);
		
		nameValuePairs.add(new BasicNameValuePair("func_id", funcId));
		nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
		
		return nameValuePairs;
	}
	
}//end of main class