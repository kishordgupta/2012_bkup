package com.example.mars;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SubCategoryActivity extends Activity implements OnItemClickListener {
	
	ListView listView;
	JSONArray subcategory;
	String cat_id;
	SubCategoryAdapter subCategoryAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		cat_id = getIntent().getExtras().getString("cat_id");
		listView = (ListView)findViewById(R.id.listview);
		subCategoryAdapter = new SubCategoryAdapter(this);
		listView.setAdapter(subCategoryAdapter);
		listView.setOnItemClickListener(this);
		
		new SubCategoryDownloader().execute();
	}
	
	class SubCategoryDownloader extends AsyncTask<Void, Void, JSONArray>{
		private final ProgressDialog dialog = new ProgressDialog(SubCategoryActivity.this);
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			this.dialog.setMessage("Retrieving data, please wait...");
    	    this.dialog.show();
		}
		
		@Override
		protected JSONArray doInBackground(Void... params) {
			return ApiProxy.GetSubCategory(cat_id);
		}
		
		@Override
		protected void onPostExecute(JSONArray result) {
			subcategory = result;
			subCategoryAdapter.notifyDataSetChanged();
			if (this.dialog.isShowing()) {
		        this.dialog.dismiss();
		     }
			super.onPostExecute(result);
		}
	}
	
	class SubCategoryAdapter extends BaseAdapter{
		private final Context context;
		
		public SubCategoryAdapter(Context context){
			this.context = context;
		}
		
		public long getItemId(int position) {
			return position;
		}
		
		public int getCount() {
			if(subcategory != null){
				return subcategory.length();
			}
			return 0;
		}
		
		public JSONObject getItem(int position) {
			try {
				return subcategory.getJSONObject(position);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		public View getView(int position, View convertView, ViewGroup parent){
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
			View rowView = inflater.inflate(R.layout.row_menu, parent, false); 
			JSONObject data = getItem(position);
			try{
				TextView txtName = (TextView)rowView.findViewById(R.id.txtName);
				txtName.setText(data.getString("cat_name"));
				
				TextView txtTitle = (TextView)rowView.findViewById(R.id.txtTitle);
				txtTitle.setText(data.getString("m_title"));
				
				TextView txtDescription = (TextView)rowView.findViewById(R.id.txtDescription);
				txtDescription.setText(data.getString("m_des"));
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			return rowView;
		}
		
	}
	

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent i = new Intent(this, ProductActivity.class);
		try {
			i.putExtra("cat_id", subCategoryAdapter.getItem(position).getString("cat_id"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		startActivity(i);
	}
}
