package com.kd.trick.troll.friend.phone;

import com.lilakhelait.kishor.listview.Happynewyearlist;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements  OnItemClickListener{
    private String[] cities = new String[] {"beauty","belly dance","boxing","buy outlet","camping","car buying","career","college life","culinary","dating","digital filmmaking","divorce","diy","driving","fashion","financial","flirting","furniture buying","gambling","gardening","golf","hairs","health","hiking","house owner","house repair","insurance","investment","job search","life","love","matrimony","mortgage","parenting","party planning","personal finances","pet care","photography","public speaking","relationships","sales training","sex","skin care","sleep","sports coaching","texting","travel","video production","weight loss","working at home",
};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		for (String a : cities) {
			Happynewyearlist.newyearsvalues.add(a);
		}
		ListView listViewwish = (ListView) findViewById(R.id.list_of_wishes);
		Happynewyearlist happynewyearlist = new Happynewyearlist(this);
		listViewwish.setAdapter(happynewyearlist);
		listViewwish.setOnItemClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage( getBaseContext().getPackageName() );
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		startActivityForResult(new Intent(getApplicationContext(), Imageactivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP), 1);
	}

}
