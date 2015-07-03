package com.kd.mobilemediacms;

import com.kd.mobilemediacms.list.Listproductstock;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;


public class PricelistActivity extends BaseActivity {

	Context context = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.readonlyproductlist);

		context = this;

		ListView listViewPlayer = (ListView) findViewById(R.id.list_of_homes);
		Listproductstock listproductstock = new Listproductstock(this);

		listViewPlayer.setAdapter(listproductstock);
		listproductstock.notifyDataSetChanged();

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

}
