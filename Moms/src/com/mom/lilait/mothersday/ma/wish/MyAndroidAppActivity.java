package com.mom.lilait.mothersday.ma.wish;

import com.mom.lilait.mothersday.ma.wish.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MyAndroidAppActivity extends android.app.TabActivity{

	public TabHost tabHost;
public static String Title="Happy mothers day";
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.tabs);

            // Get the tabHost
	    this.tabHost = getTabHost();

	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch the first Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, FirstGroup.class);

	    // Initialize a TabSpec for the first tab and add it to the TabHost
	    spec = tabHost.newTabSpec("FirstGroup").setIndicator("",
	    		getResources().getDrawable(R.drawable.aa)) // Replace null with R.drawable.your_icon to set tab icon
	    				.setContent(intent);
	    tabHost.addTab(spec);

            // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, SecondActivityGroup.class);

	    // Initialize a TabSpec for the second tab and add it to the TabHost
	    spec = tabHost.newTabSpec("idea").setIndicator("",
	    		getResources().getDrawable(R.drawable.tips)) // Replace null with R.drawable.your_icon to set tab icon
	    				.setContent(intent);
	    tabHost.addTab(spec);
	    
	   
	    
	  

	    tabHost.setCurrentTab(0);
	}
	
	@Override
	protected void onChildTitleChanged(Activity childActivity,
			CharSequence title) {
		// TODO Auto-generated method stub
		setTitle(Title);
		super.onChildTitleChanged(childActivity, title);
	}

}
