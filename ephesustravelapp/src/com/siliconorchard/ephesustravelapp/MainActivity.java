package com.siliconorchard.ephesustravelapp;

import info.androidhive.slidingmenu.adapter.NavDrawerListAdapter;
import info.androidhive.slidingmenu.model.NavDrawerItem;

import java.io.IOException;
import java.util.ArrayList;

import player.DBHelper;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;

import com.atomix.kurowiz.supports.ConstantValues;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.siliconorchard.ephesustravelapp.R;

public class MainActivity extends Activity {
	
	
//	private APIFactory apiFactory;
	public static Context c=null;
	
	
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
	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	static public TypedArray navMenuIcons;
	public static ImageLoader imageLoader = ImageLoader.getInstance();
	public static ArrayList<NavDrawerItem> navDrawerItems;
	static public NavDrawerListAdapter adapter;
	static public  WebView w = null;
	 public static DisplayImageOptions defaultOptions;
	 public static ImageLoader im;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	/*	ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
		.threadPoolSize(3)
		.threadPriority(Thread.NORM_PRIORITY - 2)
		.memoryCacheSize(1500000) // 1.5 Mb
		.denyCacheImageMultipleSizesInMemory()
		.discCacheFileNameGenerator(new Md5FileNameGenerator())
		.enableLogging() // Not necessary in common
		.build();
	// Initialize ImageLoader with configuration.
	ImageLoader.getInstance().init(config);*/
		
		
		mTitle = mDrawerTitle = getTitle();
	
		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        linear=(LinearLayout)findViewById(R.id.list);
		navDrawerItems = new ArrayList<NavDrawerItem>();

		

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
		 ConstantValues.CategoryName=ConstantValues.Learn_on_Ephesus;
		ConstantValues.citynamelist.clear();
		ConstantValues.citynamelist.addAll(DBHelper.getfavouriteset(ConstantValues.Learn_on_Ephesus));
		myDbHelper.close();
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
//*/
		c=this;
		 defaultOptions = new DisplayImageOptions.Builder()
			.cacheInMemory(true)
			.imageScaleType(ImageScaleType.EXACTLY)
			.displayer(new FadeInBitmapDisplayer(300)).build();

	ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
			getApplicationContext())
			.defaultDisplayImageOptions(defaultOptions)
			.memoryCache(new WeakMemoryCache()).diskCacheSize(100 * 1024 * 1024).build();

	im=ImageLoader.getInstance();im.init(config);
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
		
			navDrawerItems.clear();
			navDrawerItems.add(new NavDrawerItem("Welcome", navMenuIcons.getResourceId(0, -1)));
			//navDrawerItems.add(new NavDrawerItem("Recent Events", navMenuIcons.getResourceId(1, -1)));
		
				if(ConstantValues.citynamelist != null)
				{
					for(int i = 0; i < ConstantValues.citynamelist.size(); i++)
					{
						navDrawerItems.add(new NavDrawerItem(ConstantValues.citynamelist.get(i).Cityname, navMenuIcons.getResourceId(1, -1),ConstantValues.citynamelist.get(i).Cityname));
						
					}
				
        
				adapter.notifyDataSetChanged();
				}
			
	}
	
	
	@Override
	protected void onStart() {
		
		// TODO Auto-generated method stub

		
				
		
		super.onStart();
	}

	@Override
		public void onBackPressed() {
			// TODO Auto-generated method stub
		//	super.onBackPressed();
		AlertDialog.Builder splash = new AlertDialog.Builder(this);
        splash.setIcon(R.drawable.icon)
                .setTitle(" ")
                .setMessage(
                        "Do u want to Exit")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                    int id) {
                                dialog.cancel();
                                finish();
                            }
                        })
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                    int id) {
                              
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = splash.create();
        alert.show();
		}
	/**
	 * Slide menu item click listener
	 * */
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
			 Fragment f;
	            f = new FavouriteFragment();
	            FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.frame_container, f).commit();
	     
			return true;
		case R.id.exit:
			finish();
	     
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(GravityCompat.START);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new HomeFragment();
			setTitle("Home");
			break;
	
		
		default:
	       NavDrawerItem t = navDrawerItems.get(position);
	       
	        ConstantValues.currentcityname=t.getTitle();
			setTitle(t.getTitle());
			DBHelper.geteventinfoistSet();
			switch ( ConstantValues.CategoryName) {
			case ConstantValues.tours_to_ephesus:
				fragment = new PagesFragment2();
				break;
			case ConstantValues.deals_and_discounts_around_ephesus:
				fragment = new PagesFragment2();
				break;

			default:
				fragment = new PagesFragment();
				break;
			}
		
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

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

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
	
	
}




