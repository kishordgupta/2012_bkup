package com.siliconorchard.ephesustravelapp;

import java.util.ArrayList;

import player.DBHelper;

import com.atomix.kurowiz.supports.CityInfo;
import com.atomix.kurowiz.supports.ConstantValues;
import com.atomix.kurowiz.supports.MyScrollView;
import com.atomix.kurowiz.xmlparser.ImageAdapter;
import com.atomix.kurowiz.xmlparser.ListimgAdaptor;
import com.siliconorchard.ephesustravelapp.R;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class PhotosFragment extends Fragment {
	
	public PhotosFragment(){}
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
    static public ImageAdapter imgada = null;
	static public ArrayList<CityInfo> arrayList = new ArrayList<CityInfo>();
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_photos, container, false);
        getActivity().getActionBar().setTitle(ConstantValues.curreantcategoryname + "  - " + ConstantValues.currentcityname);
        gridView = (ListView) rootView.findViewById(R.id.gridView1);
        ConstantValues.eventinfolist.clear();
        ConstantValues.eventinfolist.addAll(DBHelper.geteventinfoistSet());
        imgada = new ImageAdapter(MainActivity.c, ConstantValues.eventinfolist) ;
   //   Toast.makeText(MainActivity.c, ""+ConstantValues.eventinfolist.size(),Toast.LENGTH_LONG).show();
		   gridView.setAdapter(imgada);
		   imgada.notifyDataSetChanged();
        return rootView;
    }
}
