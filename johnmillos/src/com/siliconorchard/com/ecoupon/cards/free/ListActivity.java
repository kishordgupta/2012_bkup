package com.siliconorchard.com.ecoupon.cards.free;

import info.androidhive.slidingmenu.FindPeopleFragment;
import info.androidhive.slidingmenu.HomeFragment;
import info.androidhive.slidingmenu.MapactivityFragment;
import info.androidhive.slidingmenu.PagesFragment;
import info.androidhive.slidingmenu.PhotosFragment;
import info.androidhive.slidingmenu.SearchOfferFragment;
import info.androidhive.slidingmenu.WhatsHotFragment;
import info.androidhive.slidingmenu.adapter.NavDrawerListAdapter;
import info.androidhive.slidingmenu.model.NavDrawerItem;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.NameValuePair;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

import com.atomix.kurowiz.supports.ConstantValues;
import com.atomix.kurowiz.supports.InternetConnectivity;
import com.atomix.kurowiz.supports.SingleToneClass;
import com.atomix.kurowiz.xmlparser.APIFactory;
import com.atomix.kurowiz.xmlparser.DomParser;
import com.atomix.kurowiz.xmlparser.GPSTracker;
import com.model.Instancevalues;
import com.model.Vendor;

public class ListActivity extends Activity {

	private static ProgressDialog progressDialog;

	private static ProgressDialog progressDialogvendor;
	private static APIFactory apiFactory;
	public static Context c = null;
	static public String gifttype = "";

	static public int giftid = 0;
	private static int pageId = 1;
	private static boolean isMore = false;

	private static DrawerLayout mDrawerLayout;
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
	// private String[] navMenuTitles;
	private static TypedArray navMenuIcons;

	private static ArrayList<NavDrawerItem> navDrawerItems;
	static public NavDrawerListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		GPSTracker gpstracker = new GPSTracker(this);
		if(gpstracker.canGetLocation())
		{
			Instancevalues.userLat=gpstracker.getLatitude();
			Instancevalues.userlon=gpstracker.getLongitude();
		}
		else
		{
			Toast.makeText(this, "Location Cant be Detect ,Please enable gps or wifi location detection unbloc",Toast.LENGTH_LONG).show();
		}
		
		mTitle = mDrawerTitle = getTitle();

		// load slide menu items
		// navMenuTitles =
		// getResources().getStringArray(R.array.nav_drawer_items);

		// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
		linear = (LinearLayout) findViewById(R.id.list);
		navDrawerItems = new ArrayList<NavDrawerItem>();
		// apiFactory = new APIFactory();
		c = this;
		// Recycle the typed array
		navMenuIcons.recycle();

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);
		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				android.R.drawable.ic_menu_agenda, // nav menu toggle icon
				R.string.app_name, // nav drawer open - description for
									// accessibility
				R.string.app_name // nav drawer close - description for
									// accessibility
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

		c = this;

		final SearchView searchView = (SearchView) findViewById(R.id.search);
		searchView.setIconified(true);// )
		searchView.setIconifiedByDefault(true);
		searchView.setSubmitButtonEnabled(true);

		searchView.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE);

		searchView.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String query) {
				// TODO Auto-generated method stub
				Instancevalues.searchtext = searchView.getQuery().toString();
				Instancevalues.VendorOfferlist.clear();
				Fragment f;
				f = new SearchOfferFragment();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.container, f);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.addToBackStack(null);
				ft.commit();
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				// TODO Auto-generated method stub

				return false;
			}
		});

		if (Instancevalues.firstrun) {
			apiFactory = new APIFactory();
			Instancevalues.initialize();
			new APITask().execute();
			new APITaskvendor().execute();
			new APITaskvendorDistance().execute();
		} else {
			Instancevalues.firstrun = false;

		}
		

	}

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
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		if (id == R.id.action_login) {
			startActivityForResult(new Intent(this, LoginActivity.class), 101);
			return true;
		}
		if (id == R.id.register) {
			startActivityForResult(new Intent(this, SignupActivity.class), 102);
			return true;
		}
		if (id == R.id.seefeaturedvendor) {
			finish();
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

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		menu.clear();
		if (Instancevalues.user) {

			getMenuInflater().inflate(R.menu.login, menu);
		} else {

			getMenuInflater().inflate(R.menu.main, menu);
		}
		// boolean drawerOpen = mDrawerLayout.isDrawerOpen(GravityCompat.START);
		// menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	public static void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new HomeFragment();
			((Activity) c).setTitle("Fetured Vendors");
			break;
		case 1:
			
			if (Instancevalues.VendorDistancelist != null) {
				((Activity) c).startActivityForResult(new Intent(c, MapactivityFragment.class),123);
			/*	Fragment f;
				f = new MapactivityFragment();
				FragmentTransaction ft = ((Activity) c).getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.container, f);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.addToBackStack(null);
				ft.commit();*/
			}
			((Activity) c).setTitle("Nearest Vendor");
			break;

		case 2:
			fragment = new PagesFragment();
			((Activity) c).setTitle("Favourite");
			break;

		default:
			NavDrawerItem t = navDrawerItems.get(position);
			gifttype = t.getUrl() + "";
			Instancevalues.currentcategoryname = t.getTitle();
			Instancevalues.currentvendorlist = new ArrayList<Vendor>();
			((Activity) c).setTitle(t.getTitle());
			fragment = new FindPeopleFragment();

			break;
		}

		if (fragment != null) {

			FragmentManager fragmentManager = ((Activity) c)
					.getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.container, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			mDrawerLayout.isDrawerOpen(GravityCompat.START);
			mDrawerLayout.closeDrawer(GravityCompat.START);
			// mDrawerLayout.closeDrawer(mDrawerList);

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
		firstrun = false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//finish();
		// super.onBackPressed();
		 Fragment f = new HomeFragment();
			FragmentManager fragmentManager = ((Activity) c)
					.getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.container, f).commit();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 123) {
          Fragment f = new PhotosFragment();
			FragmentManager fragmentManager = ((Activity) c)
					.getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.container, f).commit();
		}

	}

	private static class APITask extends AsyncTask<Void, Void, String> {
		private String RESULT = "OK";

		@Override
		protected void onPreExecute() {
			Log.d("f", "preexecute");
			progressDialog = ProgressDialog.show(getActivity(), "",
					"Loading...", true, false);
			ConstantValues.isBottomReached = false;
		}

		private Context getActivity() {
			// TODO Auto-generated method stub

			return ListActivity.c;
		}

		@Override
		protected String doInBackground(Void... arg0) {
			try {
				if (InternetConnectivity.isConnectedToInternet(getActivity())) {
					InputStream xml;
					DomParser domParser = new DomParser();

					ArrayList<NameValuePair> getcategory = apiFactory
							.getcategorylist(
									Instancevalues.currentuser.getUserID(),
									pageId);
					xml = SingleToneClass.getInstance().getResponseFromServer(
							getcategory);
					domParser.parsegetcategorylist(xml, isMore);

					RESULT = Instancevalues.Error_message;

					return RESULT;
				} else {
					SingleToneClass.getInstance().openInternetSettingsActivity(
							getActivity());
					return RESULT;
				}
			} catch (Exception ex) {
				return RESULT;
			}
		}

		@Override
		protected void onPostExecute(String result) {
			if (progressDialog.isShowing()) {
				progressDialog.dismiss();
				progressDialog = null;
				isMore = false;
				ConstantValues.isBottomReached = true;
			}

			if (Instancevalues.categorylist != null) {
				Log.d("sdf", "2 " + Instancevalues.categorylist.size() + " ");
				ConstantValues.isBottomReached = true;
				navDrawerItems.clear();
				navDrawerItems.add(new NavDrawerItem("Welcome", navMenuIcons
						.getResourceId(0, -1), ""));
				navDrawerItems.add(new NavDrawerItem("Nearest Vendor",R.drawable.cash, ""));
				// Photos
				navDrawerItems.add(new NavDrawerItem("Favourites",R.drawable.coupon2, ""));
				for (int i = 0; i < Instancevalues.categorylist.size(); i++) {

					navDrawerItems.add(new NavDrawerItem(
							Instancevalues.categorylist.get(i).getName(),
							1,
							Instancevalues.categorylist.get(i).getImglink()));
				}
				adapter.notifyDataSetChanged();
				mDrawerLayout.openDrawer(GravityCompat.START);
			}

			else {
				ConstantValues.isBottomReached = false;

			}

		}
	}

	private static class APITaskvendor extends AsyncTask<Void, Void, String> {
		private String RESULT = "OK";

		@Override
		protected void onPreExecute() {
			Log.d("f", "preexecute");
			progressDialogvendor = ProgressDialog.show(getActivity(), "",
					"Loading...", true, false);
			ConstantValues.isBottomReached = false;
		}

		private Context getActivity() {
			// TODO Auto-generated method stub

			return ListActivity.c;
		}

		@Override
		protected String doInBackground(Void... arg0) {
			try {
				if (InternetConnectivity.isConnectedToInternet(getActivity())) {
					InputStream xml;
					DomParser domParser = new DomParser();

					ArrayList<NameValuePair> getcategory = apiFactory
							.getfeaturevendo();// (Instancevalues.currentcategoryname,
												// Instancevalues.currentuser.getUserID(),pageId);
					xml = SingleToneClass.getInstance().getResponseFromServer(
							getcategory);
					domParser.parsegetfeaturevendorlist(xml, isMore);

					RESULT = Instancevalues.Error_message;

					return RESULT;
				} else {
					SingleToneClass.getInstance().openInternetSettingsActivity(
							getActivity());
					return RESULT;
				}
			} catch (Exception ex) {
				return RESULT;
			}
		}

		@Override
		protected void onPostExecute(String result) {
			if (progressDialogvendor.isShowing()) {
				progressDialogvendor.dismiss();
				progressDialogvendor = null;
				isMore = false;
				ConstantValues.isBottomReached = true;
			}

			if (Instancevalues.featurevendorlist != null) {
		/*		Fragment f;
				f = new PagesFragment();
				FragmentTransaction ft = ((Activity) c).getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.container, f);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.addToBackStack(null);
				ft.commit();*/
			}

			else {
				ConstantValues.isBottomReached = false;

			}

		}
	}
	
	private static class APITaskvendorDistance extends AsyncTask<Void, Void, String> {
		private String RESULT = "OK";

		@Override
		protected void onPreExecute() {
			Log.d("f", "preexecute");
			/*progressDialogvendor = ProgressDialog.show(getActivity(), "",
					"Loading...", true, false);*/
			ConstantValues.isBottomReached = false;
		}

		private Context getActivity() {
			// TODO Auto-generated method stub

			return ListActivity.c;
		}

		@Override
		protected String doInBackground(Void... arg0) {
			try {
				if (InternetConnectivity.isConnectedToInternet(getActivity())) {
					InputStream xml;
					DomParser domParser = new DomParser();

					ArrayList<NameValuePair> getcategory = apiFactory
							.getvendorlistbydistance(20000+"", Instancevalues.userLat+"", Instancevalues.userlon+"", 1);// (Instancevalues.currentcategoryname,
												// Instancevalues.currentuser.getUserID(),pageId);
					xml = SingleToneClass.getInstance().getResponseFromServer(
							getcategory);
					domParser.parsegetfeaturevendorlistbyDistance(xml, isMore);

					RESULT = Instancevalues.Error_message;

					return RESULT;
				} else {
					SingleToneClass.getInstance().openInternetSettingsActivity(
							getActivity());
					return RESULT;
				}
			} catch (Exception ex) {
				return RESULT;
			}
		}

		@Override
		protected void onPostExecute(String result) {
		/*	if (progressDialogvendor.isShowing()) {
				progressDialogvendor.dismiss();
				progressDialogvendor = null;
				isMore = false;
				ConstantValues.isBottomReached = true;
			}*/

			if (Instancevalues.VendorDistancelist != null) {
				/*Fragment f;
				f = new HomeFragment();
				FragmentTransaction ft = ((Activity) c).getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.container, f);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.addToBackStack(null);
				ft.commit();*/
			}

			else {
				ConstantValues.isBottomReached = false;

			}

		}
	}
	
	
}
