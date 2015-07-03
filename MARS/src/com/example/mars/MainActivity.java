package com.example.mars;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity implements OnItemClickListener {

	ListView listView;
	JSONArray menu;
	MenuAdapter menuAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listview);
        menuAdapter = new MenuAdapter(this);
        listView.setAdapter(menuAdapter);
        listView.setOnItemClickListener(this);
        new MenuDownloader().execute();
        
    }

    class MenuDownloader extends AsyncTask<Void, Void, JSONArray>{
    	private final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
    	
    	@Override
    	protected void onPreExecute() {
    		super.onPreExecute();
    		this.dialog.setMessage("Retrieving data, please wait...");
    	    this.dialog.show();
    	}
    	
		@Override
		protected JSONArray doInBackground(Void... params) {
			return ApiProxy.GetMenu();
		}
		
		@Override
		protected void onPostExecute(JSONArray result) {
			menu = result;
			menuAdapter.notifyDataSetChanged();
			if (this.dialog.isShowing()) {
		        this.dialog.dismiss();
		     }
			super.onPostExecute(result);
		}
    	
    }
    
	class MenuAdapter extends BaseAdapter {
		private final Context context;

		public MenuAdapter(Context context) {
			this.context = context;
		}

		public int getCount() {
			if (menu != null) {
				return menu.length();
			}
			return 0;
		}

		public JSONObject getItem(int position)
				throws IndexOutOfBoundsException {
			try {
				return menu.getJSONObject(position);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		public long getItemId(int position) throws IndexOutOfBoundsException {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
			View rowView = inflater.inflate(R.layout.row_menu, parent, false); 
			JSONObject data = getItem(position);
			try{
				TextView txtName = (TextView)rowView.findViewById(R.id.txtName);
				txtName.setText(data.getString("type_name"));
				
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
		Intent i = new Intent(this, CategoryActivity.class);
		try {
			i.putExtra("type_id", menuAdapter.getItem(position).getString("type_id"));
		} catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		startActivity(i);
	}
    
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
*/
    
}
