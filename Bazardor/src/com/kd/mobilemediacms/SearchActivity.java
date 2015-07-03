package com.kd.mobilemediacms;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.kd.mobilemediacms.list.Listproductstock;

public class SearchActivity extends BaseActivity {

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
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vendor);

		ListView listViewPlayer = (ListView) findViewById(R.id.list_of_homes);
		Listproductstock listCatalogue = new Listproductstock(this);

		// Toast.makeText(this, Listofdb.productlist.size()+"",
		// Toast.LENGTH_LONG).show();
		listViewPlayer.setAdapter(listCatalogue);
		listCatalogue.notifyDataSetChanged();

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}

}
