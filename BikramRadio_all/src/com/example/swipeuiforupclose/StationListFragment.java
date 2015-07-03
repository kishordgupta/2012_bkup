package com.example.swipeuiforupclose;

import java.util.ArrayList;

import player.DBHelper;

import radioklub.sekhontech.com.adapter.HistoryListAdapter;
import radioklub.sekhontech.com.adapter.StationListAdapter;
import radioklub.sekhontech.com.entity.Station;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class StationListFragment extends Fragment {

	//Constant
	private static final String TAG = "StationListFragment";
	
	//Member variables
	private HistoryListAdapter mAdapter;
	private ArrayList<Station> mStations;
	private OnItemClickListener mListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			//TODO Refactor
			Station station = mAdapter.getItem(position);
			MainActivity activity = (MainActivity) getActivity();
			activity.setStation(station);
			activity.justStop();
			activity.justPlay();
			 ViewPager pager2 = (ViewPager) activity.findViewById(R.id.pager); 
			 pager2.setCurrentItem(1);
		}
	};
	//View member variables
	private ListView mListView;
	
	/* Lifecycle functions
	 * (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (mStations == null)
			mStations = new ArrayList<Station>();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_stations, null);
		MainActivity.mStations.clear();
		MainActivity.mStations.addAll(DBHelper.getfavouriteset());
		//ListView init
		mListView = (ListView) view.findViewById(R.id.lv_stations);
		mAdapter = new HistoryListAdapter(getActivity(), R.layout.view_station_row, MainActivity.mStations);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(mListener);
		
		return view;
	}
	
	/* Public property access function
	 * 
	 */
	public void setStations(ArrayList<Station> stations) {
		mStations = stations;
	    if (mAdapter != null) {
	    	mAdapter.clear();
	    	mAdapter.addAll(mStations);
	    	mAdapter.notifyDataSetChanged();
	    }
	}
	


}
