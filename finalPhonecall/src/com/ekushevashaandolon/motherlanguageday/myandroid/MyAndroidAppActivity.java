package com.ekushevashaandolon.motherlanguageday.myandroid;



import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

import com.kd.phonecall.MainActivity;
import com.kd.phonecall.R;
import com.lilakhelait.kishor.helper.GetNetworkStatus;
import com.lilakhelait.kishor.listview.BlockHappynewyearlist;
import com.lilakhelait.kishor.listview.Happynewyearlist;
import com.lilakhelait.kishor.model.CallNumber;
import com.lilakhelait.kishor.resource.Helper;
import com.lilakhelait.kishor.resource.Resource;
import com.lilakhelait.kishor.resource.Wish;
import com.lilakhelait.kishor.utility.StackParser;
import com.tmm.android.chuck.db.DBHelper;
import com.tmm.android.chuck.db.MyFile;

public class MyAndroidAppActivity extends android.app.TabActivity{

	public TabHost tabHost;
	private boolean mIsBound = false;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	   
	    SharedPreferences settings = getSharedPreferences("ph", 0);
		 if(GetNetworkStatus.isNetworkAvailable(this)&&settings.getBoolean("silentMode", false)==false)
        { startActivity(new Intent(this, StackParser.class));
    //    waitstart();
        SharedPreferences.Editor editor = settings.edit();
	      editor.putBoolean("silentMode", true);
	      Calendar calendar = Calendar.getInstance();
	      String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
	      editor.putString("time",mydate);
	      editor.commit();}
	    CallNumber.valuesdb.clear();
        MyFile f = new MyFile(this);
      
        String s=	 f.readFromSD();
		String[] lines = s.split("\n");
		for (String string : lines) {
			Wish w = new Wish();
			w.setTitle("Telefon säljare");
			w.setWishtext(string);
			   CallNumber.valuesdb.add(w);
		}
		 CallNumber.values.clear();
		 DBHelper myDbHelper = new DBHelper(this);
			try {
				myDbHelper.createDataBase();
			} catch (IOException ioe) {
				throw new Error("Unable to create database");
			}
			try {
				myDbHelper.openDataBase();
			}catch(SQLException sqle){
				throw sqle;
			}
			CallNumber.values= myDbHelper.getQuestionSet();
			myDbHelper.close();
		
			Happynewyearlist.newyearsvalues=	CallNumber.values;
			BlockHappynewyearlist.newyearsvalues=	CallNumber.valuesdb;
			Log.d("dsfsd", "aaaaaaaasdf"+CallNumber.valuesdb.size());
	    setContentView(R.layout.tabs);
	 
            // Get the tabHost
	    this.tabHost = getTabHost();

	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch the first Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, Start.class);

	    // Initialize a TabSpec for the first tab and add it to the TabHost
	    spec = tabHost.newTabSpec("FirstGroup").setIndicator("Hem",
	    		getResources().getDrawable(R.drawable.home)) // Replace null with R.drawable.your_icon to set tab icon
	    				.setContent(intent);
	    tabHost.addTab(spec);

            // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, MainActivity.class);

	    // Initialize a TabSpec for the second tab and add it to the TabHost
	    spec = tabHost.newTabSpec("FirstGroup1").setIndicator("Lista",
	    		getResources().getDrawable(R.drawable.list)) // Replace null with R.drawable.your_icon to set tab icon
	    				.setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, SecondActivityGroup.class);

	    // Initialize a TabSpec for the second tab and add it to the TabHost
	    spec = tabHost.newTabSpec("SecondGroup").setIndicator("Inställningar",
	    		getResources().getDrawable(R.drawable.settings)) // Replace null with R.drawable.your_icon to set tab icon
	    				.setContent(intent);
	    tabHost.addTab(spec);
	    
	 

	    tabHost.setCurrentTab(0);
	    tabHost.setOnTabChangedListener(new OnTabChangeListener() {

	        @Override
	        public void onTabChanged(String arg0) {

	            setTabColor(tabHost);
	        }
	         });
	         setTabColor(tabHost);
	}

	private void setTabColor(TabHost tabhost11) {
		// TODO Auto-generated method stub
		for(int i=0;i<tabhost11.getTabWidget().getChildCount();i++)
        tabhost11.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#19479B")); //unselected

    if(tabhost11.getCurrentTab()==0)
           tabhost11.getTabWidget().getChildAt(tabhost11.getCurrentTab()).setBackgroundColor(Color.parseColor("#000000")); //1st tab selected
    else
           tabhost11.getTabWidget().getChildAt(tabhost11.getCurrentTab()).setBackgroundColor(Color.parseColor("#000000")); //2nd tab selected #1c4ea8

	}
}
