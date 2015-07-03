package com.example.mars;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class ProductDetailActivity extends Activity {
	String pid;
	JSONObject product_detail;
	TextView txtName;
	TextView txtPrice;
	TextView txtFeatured;
	TextView txtStatus;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_details);
		pid = getIntent().getExtras().getString("pid");
		
		txtName = (TextView)findViewById(R.id.txtName);
		txtPrice = (TextView)findViewById(R.id.txtPrice);
		txtFeatured = (TextView)findViewById(R.id.txtFeatured);
		txtStatus = (TextView)findViewById(R.id.txtStatus);
		
		new ProductDetailDownloader().execute();
	}
	
	class ProductDetailDownloader extends AsyncTask<Void, Void, JSONObject>{
		private final ProgressDialog dialog = new ProgressDialog(ProductDetailActivity.this);
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			this.dialog.setMessage("Retrieving data, please wait...");
    	    this.dialog.show();
		}
		
		@Override
		protected JSONObject doInBackground(Void... params) {
			return ApiProxy.GetProductDetails(pid);
		}
		
		@Override
		protected void onPostExecute(JSONObject result) {
			product_detail = result;
			displayProductDetail();
			if (this.dialog.isShowing()) {
		        this.dialog.dismiss();
		     }
			super.onPostExecute(result);
		}
	}
	
	
	void displayProductDetail(){
		if(product_detail != null){
			try {
				txtName.setText(product_detail.getString("pname"));
				txtPrice.setText(String.format("$%s", product_detail.getString("price")));
				txtFeatured.setText(String.format("Featured: %s", product_detail.getString("featured")));
				txtStatus.setText(String.format("Status: %s", product_detail.getString("status")));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
	
	

}
