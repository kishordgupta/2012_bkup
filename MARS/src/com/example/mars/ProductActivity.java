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

public class ProductActivity extends Activity implements OnItemClickListener {
	
	ListView listView;
	String cat_id;
	JSONArray product;
	ProductAdapter productAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		cat_id = getIntent().getExtras().getString("cat_id");
		listView = (ListView)findViewById(R.id.listview);
		productAdapter = new ProductAdapter(this);
		listView.setAdapter(productAdapter);
		listView.setOnItemClickListener(this);
		
		new ProductDownloader().execute();
	}
	
	class ProductDownloader extends AsyncTask<Void, Void, JSONArray>{
		private final ProgressDialog dialog = new ProgressDialog(ProductActivity.this);
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			this.dialog.setMessage("Retrieving data, please wait...");
    	    this.dialog.show();
		}
		
		@Override
		protected JSONArray doInBackground(Void... params) {
			return ApiProxy.GetProduct(cat_id);
		}
		
		@Override
		protected void onPostExecute(JSONArray result) {
			product = result;
			productAdapter.notifyDataSetChanged();
			if (this.dialog.isShowing()) {
		        this.dialog.dismiss();
		     }
			super.onPostExecute(result);
		}
	}
	
	class ProductAdapter extends BaseAdapter{
		private final Context context;
		public ProductAdapter(Context context){
			this.context = context;
		}
		
		public long getItemId(int position) {
			return position;
		}
		
		public int getCount() {
			if(product != null){
				return product.length();
			}
			return 0;
		}
		
		public JSONObject getItem(int position) {
			try {
				return product.getJSONObject(position);
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
	

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent i = new Intent(this, ProductDetailActivity.class);
		try {
			i.putExtra("pid", productAdapter.getItem(position).getString("pid"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		startActivity(i);
	}
	

}
