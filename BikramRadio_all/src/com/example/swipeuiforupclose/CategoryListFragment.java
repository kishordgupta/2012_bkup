package com.example.swipeuiforupclose;

import java.io.InputStream;
import java.util.ArrayList;

import com.atomix.kurowiz.supports.ConstantValues;
import com.atomix.kurowiz.supports.GiftInfo;
import com.atomix.kurowiz.supports.InternetConnectivity;
import com.atomix.kurowiz.supports.SingleToneClass;
import com.atomix.kurowiz.supports.Urlmaker;
import com.atomix.kurowiz.xmlparser.DomParser;
import com.atomix.kurowiz.xmlparser.ImageAdapter;

import radioklub.sekhontech.com.adapter.CategoryListAdapter;
import radioklub.sekhontech.com.adapter.StationListAdapter;
import radioklub.sekhontech.com.entity.Station;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class CategoryListFragment extends Fragment {
	private static ProgressDialog progressDialog;
//	private APIFactory apiFactory;
	public static Context c=null;
	//Constant
	private static final String TAG = "StationListFragment";
	static TextView back = null;
	//Member variables
	private CategoryListAdapter mAdapter;
	private ArrayList<GiftInfo> mStations;
	private OnItemClickListener mListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			//TODO Refactor
			GiftInfo station = mAdapter.getItem(position);
			Urlmaker.urlmaker(station.getHotel_link());
		
			MainActivity activity = (MainActivity) getActivity();
			new APITask1(activity).execute();
		/*	MainActivity activity = (MainActivity) getActivity();
			activity.setStation(station);
			activity.justStop();
			activity.justPlay();
			 ViewPager pager2 = (ViewPager) activity.findViewById(R.id.pager); 
			 pager2.setCurrentItem(1);*/
		}
	};
	//View member variables
	private static ListView mListView_s;
	
	//Member variables
	private static StationListAdapter mAdapter_s;
	private ArrayList<Station> mStations_s;
	private OnItemClickListener mListener_s = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			//TODO Refactor
			Station station = mAdapter_s.getItem(position);
			MainActivity activity = (MainActivity) getActivity();
		
			activity.setStation(station);
			activity.justStop();
			activity.justPlay();
			 ViewPager pager2 = (ViewPager) activity.findViewById(R.id.pager); 
			 pager2.setCurrentItem(1);
		}
	};
	//View member variables
	private static ListView mListView;
	
	/* Lifecycle functions
	 * (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (mStations == null)
			mStations = new ArrayList<GiftInfo>();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_stations, null);
		back=(TextView)view.findViewById(R.id.lv_back);
		
		//ListView init
		mListView = (ListView) view.findViewById(R.id.lv_stations);
		mAdapter = new CategoryListAdapter(getActivity(), R.layout.view_station_row, MainActivity.arrayList);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(mListener);
		
		mListView_s = (ListView) view.findViewById(R.id.lv_categories);
		mAdapter_s = new StationListAdapter(getActivity(), R.layout.view_category_row, MainActivity.mStations);
		mListView_s.setAdapter(mAdapter_s);
		mListView_s.setOnItemClickListener(mListener_s);
		
        back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mListView_s.setVisibility(View.GONE);
				mListView.setVisibility(View.VISIBLE);
				v.setVisibility(View.GONE);
			}
		});
		
		
		return view;
	}
	

	/* Public property access function
	 * 
	 */
/*	public void setStations(ArrayList<Station> stations) {
		mStations = stations;
	    if (mAdapter != null) {
	    	mAdapter.clear();
	    	mAdapter.addAll(mStations);
	    	mAdapter.notifyDataSetChanged();
	    }
	}*/
	
	private static class APITask1 extends AsyncTask<Void, Void, String> 
	{
		private String RESULT = "OK";
         public Context ca=null;
		@Override
		protected void onPreExecute() 
		{MainActivity.mStations.clear();
	
			Log.d("f", "preexecute");
			progressDialog = ProgressDialog.show(getActivity(), "", "Loading...", true, false);
			ConstantValues.isBottomReached = false;
		}
        private APITask1(Context con)
        {
        	ca=con;
        }
		private Context getActivity() {
			// TODO Auto-generated method stub
			
			return MainActivity.c;
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
					
					xml = SingleToneClass.getInstance().getResponseFromServer();
					domParser.parseAPI29(xml, false);
		
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
		{
			if (progressDialog.isShowing())
			{
				progressDialog.dismiss();
				progressDialog = null;
			
			}
			//ListView init
			mAdapter_s.notifyDataSetChanged();
			mListView_s.setVisibility(View.VISIBLE);
			mListView.setVisibility(View.GONE);
			back.setVisibility(View.VISIBLE);
			//linearLayout.removeAllViews();
		//	LayoutInflater inflater = LayoutInflater.from(getActivity());
			
			}
			
		
			
		
	}

}
