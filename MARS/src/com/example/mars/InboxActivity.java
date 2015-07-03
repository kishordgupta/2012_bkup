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

public class InboxActivity extends Activity {
	ListView listView;
	JSONArray inbox;
	InboxAdapter inboxAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		listView = (ListView)findViewById(R.id.listview);
		inboxAdapter = new InboxAdapter(this);
		listView.setAdapter(inboxAdapter);
		
		new InboxDownloader().execute();
	}
	
	class InboxDownloader extends AsyncTask<Void, Void, JSONArray>{
		private final ProgressDialog dialog = new ProgressDialog(InboxActivity.this);
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog.setMessage("Downloading inbox messages, please wait...");
			dialog.show();
		}
		
		@Override
		protected JSONArray doInBackground(Void... params) {
			return ApiProxy.GetInbox();
		}
		
		@Override
		protected void onPostExecute(JSONArray result) {
			super.onPostExecute(result);
			inbox = result;
			if(dialog.isShowing()){
				dialog.dismiss();
			}
			inboxAdapter.notifyDataSetChanged();
		}
		
	}
	
	class InboxAdapter extends BaseAdapter{
		private final Context context;
		
		public InboxAdapter(Context context){
			this.context = context;
		}
		
		public long getItemId(int position) {
			return position;
		}
		
		public int getCount() {
			if(inbox != null){
				return inbox.length();
			}
			return 0;
		}
		
		public JSONObject getItem(int position) {
			if(inbox != null){
				try {
					return inbox.getJSONObject(position);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
			View rowView = inflater.inflate(R.layout.row_menu, parent, false); 
			JSONObject data = getItem(position);
			try{
				TextView txtName = (TextView)rowView.findViewById(R.id.txtName);
				txtName.setText(data.getString("subject"));
				
				TextView txtTitle = (TextView)rowView.findViewById(R.id.txtTitle);
				txtTitle.setText(String.format("%s", data.getString("msg")));
				
				TextView txtDescription = (TextView)rowView.findViewById(R.id.txtDescription);
				txtDescription.setText(data.getString("submit_date"));
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			return rowView;
		}
		
	}
	
	

}
