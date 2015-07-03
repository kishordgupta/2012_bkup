package com.kd.tab;




import com.kd.hockymain.Listawayadapter;
import com.kd.hockymain.Listplayeradapter;
import com.kd.hockymain.Listplayernameadapter;
import com.kd.hockymain.R;
import com.kd.tab.Shark_period;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabsActivity extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);
        setTitle("SHARKS");
        /* TabHost will have Tabs */
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        //tabHost.setup();
        
        /* TabSpec used to create a new tab. 
         * By using TabSpec only we can able to setContent to the tab.
         * By using TabSpec setIndicator() we can set name to tab. */
        
        /* tid1 is firstTabSpec Id. Its used to access outside. */
        TabSpec firstTabSpec = tabHost.newTabSpec("tid1");
        TabSpec secondTabSpec = tabHost.newTabSpec("tid2");
        
        /* TabSpec setContent() is used to set content for a particular tab. */
        Intent t1 = new Intent(this,Home.class);
        t1.putExtra("Tab", "1");
        firstTabSpec.setContent(t1);
        firstTabSpec.setIndicator("SHARKS");
        
        Intent t2 = new Intent(this,Away.class);
        t2.putExtra("Tab", "2");
        secondTabSpec.setContent(t2);       
        secondTabSpec.setIndicator("AWAY");
        
        /* Add tabSpec to the TabHost to display. */
        tabHost.addTab(firstTabSpec);
        tabHost.addTab(secondTabSpec);
        
        TabSpec firstTabSpec1 = tabHost.newTabSpec("tid11");
        TabSpec secondTabSpec1 = tabHost.newTabSpec("tid21");
        
        /* TabSpec setContent() is used to set content for a particular tab. */
        Intent t11 = new Intent(this,Goalies.class);
        t11.putExtra("Tab", "3");
        firstTabSpec1.setContent(t11);       
        firstTabSpec1.setIndicator("GOALIES");
        
        Intent t21 = new Intent(this,Penalties.class);
        t21.putExtra("Tab", "4");
        secondTabSpec1.setContent(t21);
        secondTabSpec1.setIndicator("PENALTIES");
        
        /* Add tabSpec to the TabHost to display. */
        tabHost.addTab(firstTabSpec1);
        tabHost.addTab(secondTabSpec1);
        
        TabSpec firstTabSpec2 = tabHost.newTabSpec("tid12");
        TabSpec secondTabSpec2 = tabHost.newTabSpec("tid22");
        
        /* TabSpec setContent() is used to set content for a particular tab. */
        Intent t12 = new Intent(this,PlayerStat.class);
        t12.putExtra("Tab", "5");
        firstTabSpec2.setContent(t12);
        firstTabSpec2.setIndicator("PLAYER STATS");
        
        Intent t22 = new Intent(this,Shark_period.class);
        t22.putExtra("Tab", "6");
        secondTabSpec2.setContent(t22);
        secondTabSpec2.setIndicator("SHARKS NET PERIODS");
        
        /* Add tabSpec to the TabHost to display. */
        tabHost.addTab(firstTabSpec2);
        tabHost.addTab(secondTabSpec2);
        
        TabSpec firstTabSpec13 = tabHost.newTabSpec("tid113");
  //      TabSpec secondTabSpec13 = tabHost.newTabSpec("tid213");
        
        /* TabSpec setContent() is used to set content for a particular tab. */
        Intent t113 = new Intent(this,Away_period.class);
        t113.putExtra("Tab", "7");
        firstTabSpec13.setContent(t113);
        firstTabSpec13.setIndicator("AWAY NET PERIOD");
        
       // Intent t213 = new Intent(this,PlayerStat.class);
     //   t213.putExtra("Tab", "8");
     //   secondTabSpec13.setContent(t213);
       // secondTabSpec13.setIndicator("SEC 8");
        
        /* Add tabSpec to the TabHost to display. */
        tabHost.addTab(firstTabSpec13);
       // tabHost.addTab(secondTabSpec13);
        
        
        
        
        TabSpec firstTabSpecx = tabHost.newTabSpec("tid1x");
      
        /* TabSpec setContent() is used to set content for a particular tab. */
        Intent t1x = new Intent(this,Gamenotes.class);
        t1x.putExtra("Tab", "9");
        firstTabSpecx.setContent(t1x);
        firstTabSpecx.setIndicator("NOTES");
        tabHost.addTab(firstTabSpecx);
      
        
    }
    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	super.onBackPressed();
    	Listplayernameadapter.values.clear();
    	Listplayeradapter.values.clear();
    	Listawayadapter.values.clear();
    	finish();
    	
    }
}