package com.siliconorchard.cityhistory;


import java.io.InputStream;
import java.util.ArrayList;

import player.DBHelper;

import com.atomix.kurowiz.supports.ConstantValues;
import com.atomix.kurowiz.supports.CityInfo;
import com.atomix.kurowiz.supports.InternetConnectivity;
import com.atomix.kurowiz.supports.MyScrollView;

import com.atomix.kurowiz.xmlparser.ImageAdapter;
import com.atomix.kurowiz.xmlparser.ListimgAdaptor;
import com.siliconorchard.cityhistory.R;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class FindPeopleFragment extends Fragment {
	private static ProgressDialog progressDialog;
//	private APIFactory apiFactory;
	public static Context c=null;
	private static LinearLayout linearLayout;
	static public String gifttype="";
	static public int giftid=0;
	private static MyScrollView scrollViewExchange;
	private static int pageId = 1;
	private static boolean isMore = false;
    static public ListView gridView=null;
    static public ListimgAdaptor imgada = null;
	static public ArrayList<CityInfo> arrayList = new ArrayList<CityInfo>();
	public FindPeopleFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_find_people, container, false);
        gridView = (ListView) rootView.findViewById(R.id.gridView1);
        
           ConstantValues.categoryNamelist.clear();
           ConstantValues.categoryNamelist.addAll(DBHelper.getcategoryNamelistSet());
           imgada = new ListimgAdaptor(MainActivity.c, ConstantValues.categoryNamelist) ;
      //   Toast.makeText(MainActivity.c, ""+ConstantValues.categoryNamelist.size(),Toast.LENGTH_LONG).show();
   		   gridView.setAdapter(imgada);
   		   imgada.notifyDataSetChanged();
   		   //*/
	/*	gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Toast.makeText(
				   getApplicationContext(),
				   ((TextView) v.findViewById(R.id.grid_item_label))
				   .getText(), Toast.LENGTH_SHORT).show();
 
			}
		});*/
		// MainActivity.closewebview("");
		// arrayList.clear();
       
         
        return rootView;
    }

	}
