package info.androidhive.slidingmenu;
/*package info.androidhive.slidingmenu;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

;

import com.atomix.kurowiz.supports.ConstantValues;

import com.atomix.kurowiz.supports.InternetConnectivity;
import com.atomix.kurowiz.supports.MyScrollView;

import com.atomix.kurowiz.supports.SingleToneClass;

import com.atomix.kurowiz.xmlparser.DomParser;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.database.SQLException;
import android.graphics.Color;
import android.media.AudioManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private static ProgressDialog progressDialog;
	public static ProgressDialog progressDialog_stat;
//	private APIFactory apiFactory;
	public static Context c=null;
	private static LinearLayout linearLayout;
	static public String gifttype="";
	static public int giftid=0;
	private static MyScrollView scrollViewExchange;
	private static int pageId = 1;
	private static boolean isMore = false;
	static public ArrayList<GiftInfo> arrayList = new ArrayList<GiftInfo>();
	static public ArrayList<Radio> history = new ArrayList<Radio>();
	static public ArrayList<Radio> favourite = new ArrayList<Radio>();
	
	private String giftMsg = "";
	
	
	/////////////////
	static public Button radio;
	private DrawerLayout mDrawerLayout;
	static public ListView mDrawerList;
	static public LinearLayout linear;
	private ActionBarDrawerToggle mDrawerToggle;
	static public boolean firstrun = true;
	static public boolean isplaying = true;
	// nav drawer title
	private CharSequence mDrawerTitle;
	static public 	RecordThread rt =null;
	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private static TypedArray navMenuIcons;

	private static ArrayList<NavDrawerItem> navDrawerItems;
	static public NavDrawerListAdapter adapter;
	static public  WebView w = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTitle = mDrawerTitle = getTitle();
	rt = new RecordThread(false);
		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        linear=(LinearLayout)findViewById(R.id.list);
		navDrawerItems = new ArrayList<NavDrawerItem>();

		// adding nav drawer items to array
		// Home
		
		// Find People
			// Communities, Will add a counter here
//		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "22"));
//		// Pages
//		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
//		// What's hot, We  will add a counter here
//		navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1), true, "50+"));
//		
//
		c=this;
		// Recycle the typed array
		navMenuIcons.recycle();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, //nav menu toggle icon
				R.string.app_name, // nav drawer open - description for accessibility
				R.string.app_name // nav drawer close - description for accessibility
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}
		 w = (WebView)findViewById(R.id.imageView1);
		String S= 	"<html> <head> <title>banner</title> </head><body>   <audio width=\"300\" height=\"32\" controls=\"controls\" autoplay=\"autoplay\" src=\"http://public-radio1.internode.on.net:8002/137\"></audio>";
		//S=S+ "\""+arraylist.get(position).getImages()+"\"" +"alt=\"\"width=\"100%\" height=\"100%\"/></a></body></html>";

			//WebView w = (WebView)gridView.findViewById(R.id.img);
			w.loadData(S,  "text/html", "UTF-8");
			
			radio = (Button)findViewById(R.id.radio);
			radio.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				
					if(!isplaying)
					{
						MainActivity.loadinwebview("http://public-radio1.internode.on.net:8002/137","https://fbstatic-a.akamaihd.net/rsrc.php/v2/yZ/r/0a3UTOK0lYN.png","Default");
						
					}
					else
					{
						MainActivity.closewebview("");
						
					}
					
				}
			});
	//	w.loadUrl("<audio width="300" height="32" controls="controls" autoplay="autoplay" src="http://insertyourstreamhere.com:0000/;"></audio>http://public-radio1.internode.on.net:8002/137");
		ImageButton im =(ImageButton)findViewById(R.id.playpause);
		im.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), 				
						MyMediaPlayerService.class);
				intent.putExtra(MyMediaPlayerService.START_PLAY, true);
				startService(intent);
			}
		});
		
	}
	
	public static void loadinwebview(String D,String i,String n)
	{
		ConstantValues.currentradiostream=D;
		ConstantValues.curreantradioname=n;
		ConstantValues.currentradioimag=i;
		try{
		String S= 	"<html> <head> <title>banner</title> </head><body>   <audio width=\"100%\" height=\"100%\" controls=\"controls\" autoplay=\"autoplay\" src=\""+D+"\"></audio>";
		MainActivity.w.loadData(S,  "text/html", "UTF-8");
		MainActivity.isplaying=true;
		
		ProgressDialog.show(MainActivity.c,"Buffering","Stream loading",false,true);
	
	
		Toast.makeText(MainActivity.c, "Radio is playing",5000).show();}
		catch(Exception e)
	{
			
		}
	}
	public static void closewebview(String D)
	{
		try{
		String S= 	"<html> <head> <title>banner</title> </head><body>   <audio width=\"100%\" height=\"100%\" controls=\"controls\" autoplay=\"autoplay\" src=\""+D+"\"></audio>";
		MainActivity.w.loadUrl("about:blank");
		MainActivity.w.clearView();
		Toast.makeText(MainActivity.c, "Radio is Stopping",Toast.LENGTH_LONG).show();
		MainActivity.isplaying=false;
		}
		catch(Exception e )
		{
			
		}
	}
	ThreeDService service;
	@Override
	protected void onStart() {
		new player.MyNotification(this);
		// TODO Auto-generated method stub
		if(firstrun){
			MainActivity.arrayList.clear();
			new APITask(this).execute();
		
				player.DBHelper myDbHelper = new player.DBHelper(this);
				try {
					myDbHelper.createDataBase();
				} catch (IOException ioe) {
					throw new Error("Unable to create database");
				}
				try {
					myDbHelper.openDataBase();
				}catch(SQLException sqle){
					throw sqle;
				}
				myDbHelper.close();
				firstrun=false;
		}
		else if(MainActivity.navDrawerItems!=null){
		if(MainActivity.navDrawerItems.size()<=1)
		{
			new APITask(this).execute();
			
			player.DBHelper myDbHelper = new player.DBHelper(this);
			try {
				myDbHelper.createDataBase();
			} catch (IOException ioe) {
				throw new Error("Unable to create database");
			}
			try {
				myDbHelper.openDataBase();
			}catch(SQLException sqle){
				throw sqle;
			}
			myDbHelper.close();
			firstrun=false;
		}
		}
		super.onStart();
	}

	
	*//**
	 * Slide menu item click listener
	 * *//*
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	 *
	 * Called when invalidateOptionsMenu() is triggered
	 
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(GravityCompat.START);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	*//**
	 * Diplaying fragment view for selected nav drawer list item
	 * *//*
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new HomeFragment();
			setTitle("Home");
			break;
		case 1:
			fragment = new WhatsHotFragment();
			setTitle("History");
			break;
		
		case 2:
			fragment = new CommunityFragment();
			setTitle("Favourite");
			break;
		case 3:
			fragment = new ChatFragment();
			setTitle("Chat");
			break;
		default:
	       NavDrawerItem t = navDrawerItems.get(position);
	       gifttype=t.getUrl()+"";
			Urlmaker.urlmaker(Uri.parse(gifttype).toString());
			setTitle(t.getTitle());
			fragment = new FindPeopleFragment();
			break;
		}

		if (fragment != null) {
			
		
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			mDrawerLayout.isDrawerOpen(GravityCompat.START);
			mDrawerLayout.closeDrawer(GravityCompat.START);
		//	mDrawerLayout.closeDrawer(mDrawerList);
			
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	*//**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 *//*

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
		firstrun=false;
	}
	
	
	private static class APITask extends AsyncTask<Void, Void, String> 
	{
		private String RESULT = "OK";
		  public Context ca=null;
		@Override
		protected void onPreExecute() 
		{
			Log.d("f", "preexecute");
			progressDialog = ProgressDialog.show(getActivity(), "", "Loading...", true, false);
			ConstantValues.isBottomReached = false;
		}
		 private APITask(Context con)
	        {
	        	ca=con;
	        }
		private Context getActivity() {
			// TODO Auto-generated method stub
			
			return ca;
		}

		@Override
		protected String doInBackground(Void... arg0) 
		{
			try 
			{
				if (InternetConnectivity.isConnectedToInternet(getActivity())) 
				{
					InputStream xml;
					DomParser domParser = new DomParser();
					
					//ArrayList<NameValuePair> nvp28 = apiFactory.get_buy_gift_list(ConstantValues.FUNCTION_ID_28, "5", Integer.toString(pageId));
					xml = SingleToneClass.getInstance().getcateeFromServer();
					domParser.parseAPI28(xml, isMore);
					
			ArrayList<NameValuePair> nvp31 = apiFactory.get_gift_ticket_info(ConstantValues.FUNCTION_ID_31,  DomParser.userInfo.getUserId());
					xml = SingleToneClass.getInstance().getResponseFromServer(nvp31);
					domParser.parseAPI31(xml);
					
					return RESULT;
				} 
				else 
				{
					SingleToneClass.getInstance().openInternetSettingsActivity(getActivity());
					return RESULT;
				}
			} 
			catch (Exception ex) 
			{
				return RESULT;
			}
		}

		@Override
		protected void onPostExecute(String result) 
		{if(progressDialog!=null){
			if (progressDialog.isShowing())
			{
				progressDialog.dismiss();
				progressDialog = null;
				isMore = false;
				ConstantValues.isBottomReached = true;
			}
		}
		//	linearLayout.removeAllViews();
		//	LayoutInflater inflater = LayoutInflater.from(getActivity());
		navDrawerItems.clear();
		navDrawerItems.add(new NavDrawerItem("Welcome", navMenuIcons.getResourceId(0, -1)));
		navDrawerItems.add(new NavDrawerItem("History", navMenuIcons.getResourceId(1, -1)));
		// Photos
		navDrawerItems.add(new NavDrawerItem("Favourites", navMenuIcons.getResourceId(2, -1)));
		navDrawerItems.add(new NavDrawerItem("Chat", navMenuIcons.getResourceId(2, -1)));
			if(arrayList != null)
			{
				for(int i = 0; i < arrayList.size(); i++)
				{
					navDrawerItems.add(new NavDrawerItem(arrayList.get(i).getHotel_name(), navMenuIcons.getResourceId(i, -1),arrayList.get(i).getHotel_link()));
					
				}
			

			adapter.notifyDataSetChanged();
		}
			
		
			
		}
	}




}
*/