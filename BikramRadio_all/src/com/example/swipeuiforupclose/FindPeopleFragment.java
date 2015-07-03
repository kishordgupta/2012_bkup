package com.example.swipeuiforupclose;

import java.io.InputStream;
import java.util.ArrayList;

import com.atomix.kurowiz.supports.ConstantValues;
import com.atomix.kurowiz.supports.GiftInfo;
import com.atomix.kurowiz.supports.InternetConnectivity;
import com.atomix.kurowiz.supports.MyScrollView;
import com.atomix.kurowiz.supports.SingleToneClass;
import com.atomix.kurowiz.xmlparser.DomParser;
import com.atomix.kurowiz.xmlparser.ImageAdapter;

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
    static public GridView gridView=null;
    static public ImageAdapter imgada = null;
	static public ArrayList<GiftInfo> arrayList = new ArrayList<GiftInfo>();
	public FindPeopleFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_find_people, container, false);
        gridView = (GridView) rootView.findViewById(R.id.gridView1);
     
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
		 arrayList.clear();
        new APITask1(MainActivity.c).execute();
         
        return rootView;
    }

	private static class APITask1 extends AsyncTask<Void, Void, String> 
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
					domParser.parseAPI29(xml, isMore);
		
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
				isMore = false;
				ConstantValues.isBottomReached = true;
			}
			
			//linearLayout.removeAllViews();
		//	LayoutInflater inflater = LayoutInflater.from(getActivity());
			
			if(arrayList != null)
			{	
				   imgada = new ImageAdapter(MainActivity.c, arrayList) ;
					gridView.setAdapter(imgada);
				   imgada.notifyDataSetChanged();
			 }
			}
			
		
			
		
	}
}
