package com.example.mars;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class WishListActivity extends Activity {
	ListView listView;
	JSONArray wishlist;
	WishListAdapter wishlistAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		listView = (ListView)findViewById(R.id.listview);
		wishlistAdapter = new WishListAdapter(this);
		listView.setAdapter(wishlistAdapter);
		
		new WishListDownloader().execute();
	}
	
	class WishListDownloader extends AsyncTask<Void, Void, JSONArray>{
		private final ProgressDialog dialog = new ProgressDialog(WishListActivity.this);
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog.setMessage("Downloading wish list, please wait...");
			dialog.show();
		}
		
		@Override
		protected JSONArray doInBackground(Void... params) {
			return ApiProxy.GetWishList();
		}
		
		@Override
		protected void onPostExecute(JSONArray result) {
			super.onPostExecute(result);
			wishlist = result;
			if(dialog.isShowing()){
				dialog.dismiss();
			}
			wishlistAdapter.notifyDataSetChanged();
		}
		
	}
	
	class WishListAdapter extends BaseAdapter{
		private final Context context;
		
		public WishListAdapter(Context context){
			this.context = context;
		}
		
		public long getItemId(int position) {
			return position;
		}
		
		public int getCount() {
			if(wishlist != null){
				return wishlist.length();
			}
			return 0;
		}
		
		public JSONObject getItem(int position) {
			try {
				return wishlist.getJSONObject(position);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
			View rowView = inflater.inflate(R.layout.row_menu, parent, false); 
			JSONObject data = getItem(position);
			try{
				TextView txtName = (TextView)rowView.findViewById(R.id.txtName);
				txtName.setText(data.getString("pname"));
				
				TextView txtTitle = (TextView)rowView.findViewById(R.id.txtTitle);
				txtTitle.setText(String.format("$%s", data.getString("price")));
				
				TextView txtDescription = (TextView)rowView.findViewById(R.id.txtDescription);
				//txtDescription.setText(data.getString("m_des"));
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			return rowView;
		}
	}
	
	
}
