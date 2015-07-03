package com.lilait.movie.story.spoiler;

import com.lilait.movie.story.spoiler.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MyAndroidAppActivity extends android.app.TabActivity{

	public TabHost tabHost;

	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.tabs);

            // Get the tabHost
	    this.tabHost = getTabHost();

	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch the first Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, CitiesActivity.class);

	    // Initialize a TabSpec for the first tab and add it to the TabHost
	    spec = tabHost.newTabSpec("CitiesActivity").setIndicator("Archive",
	    		getResources().getDrawable(android.R.drawable.btn_star_big_on)) // Replace null with R.drawable.your_icon to set tab icon
	    				.setContent(intent);
	    tabHost.addTab(spec);

            // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, Searchbyname.class);

	    // Initialize a TabSpec for the second tab and add it to the TabHost
	    spec = tabHost.newTabSpec("Searchbyname").setIndicator("Search",
	    		getResources().getDrawable(android.R.drawable.btn_star_big_off)) // Replace null with R.drawable.your_icon to set tab icon
	    				.setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, NewMovies.class);

	    // Initialize a TabSpec for the second tab and add it to the TabHost
	    spec = tabHost.newTabSpec("SecondGroup").setIndicator("New",
	    		getResources().getDrawable(android.R.drawable.ic_menu_agenda)) // Replace null with R.drawable.your_icon to set tab icon
	    				.setContent(intent);
	    tabHost.addTab(spec);
	    
	   

	    tabHost.setCurrentTab(0);
	}
}
