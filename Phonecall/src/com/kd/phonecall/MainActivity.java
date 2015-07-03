package com.kd.phonecall;

import com.lilakhelait.kishor.listview.Happynewyearlist;
import com.lilakhelait.kishor.model.CallNumber;
import com.lilakhelait.kishor.utility.MyFile;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	ListView listViewwish =null;
			Happynewyearlist happynewyearlist =null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 MyFile f = new MyFile(this);
		 CallNumber.values.clear();
         String s=	 f.readFromSD();
			String[] lines = s.split("\n");
			for (String string : lines) {
				if(string.length()>6)
				{
				CallNumber.values.add(string);
				}
			}
		setContentView(R.layout.activity_main);
         Button add =(Button)findViewById(R.id.button1);
         add.setOnClickListener(this);
        
         Happynewyearlist.newyearsvalues=CallNumber.values;
		listViewwish = (ListView) findViewById(R.id.list_of_wishes);
		 happynewyearlist = new Happynewyearlist(this);
		 
		listViewwish.setAdapter(happynewyearlist);
		happynewyearlist.notifyDataSetChanged();
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			String Text = ((EditText)findViewById(R.id.editText1)).getText().toString();
			if(Text.length()>6)
			{
			CallNumber.values.add(Text);
			 MyFile f = new MyFile(this);
			 f.writeToSD("");
			 Happynewyearlist.newyearsvalues=CallNumber.values;
			 happynewyearlist.notifyDataSetChanged();
			 ((EditText)findViewById(R.id.editText1)).clearComposingText();
			 ((EditText)findViewById(R.id.editText1)).setText("");
			 Toast.makeText(this, "Contact added", Toast.LENGTH_LONG).show();
			}
			else
			{
				Toast.makeText(this, "Not a phone number", Toast.LENGTH_LONG).show();
			}
			break;

		default:
			break;
		}
	}

}
