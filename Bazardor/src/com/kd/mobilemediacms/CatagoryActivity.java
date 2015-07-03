package com.kd.mobilemediacms;


import com.kd.mobilemediacms.list.ListCatagory;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;


public class CatagoryActivity extends BaseActivity {


	ListView listViewPlayer=null;


	Context context=null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_catagory);
		context = this;

		listViewPlayer = (ListView) findViewById(R.id.list_of_homes);
		ListCatagory listCatagory= new ListCatagory (this);

		listViewPlayer.setAdapter(listCatagory);
		listCatagory.notifyDataSetChanged();

    }

    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.mainmenu, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub

		return Menuitem.onMenuItemSelected(item.getItemId(), this);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();

	}
	
    
  
}
