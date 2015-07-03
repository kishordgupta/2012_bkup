package com.kd.phonecall;

import java.io.IOException;

import com.ekushevashaandolon.motherlanguageday.myandroid.Datainput;
import com.lilakhelait.kishor.listview.BlockHappynewyearlist;
import com.lilakhelait.kishor.listview.Happynewyearlist;
import com.lilakhelait.kishor.model.CallNumber;
import com.lilakhelait.kishor.resource.Helper;
import com.lilakhelait.kishor.resource.Resource;
import com.lilakhelait.kishor.resource.Wish;
import com.tmm.android.chuck.db.DBHelper;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	ListView listViewwish =null;
	ListView listViewwish1 =null;
			Happynewyearlist happynewyearlist =null;
			BlockHappynewyearlist happynewyearlist1 =null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
       Button add =(Button)findViewById(R.id.butn1);
         add.setOnClickListener(this);
		
		listViewwish = (ListView) findViewById(R.id.list_of_mine);
		 happynewyearlist = new Happynewyearlist(this);
final Context context=this;
			Happynewyearlist.newyearsvalues=	CallNumber.values;
		listViewwish.setAdapter(happynewyearlist);
		happynewyearlist.notifyDataSetChanged();
		listViewwish1 = (ListView) findViewById(R.id.list_of_wishes);
		 happynewyearlist1 = new BlockHappynewyearlist(this);

			BlockHappynewyearlist.newyearsvalues=	CallNumber.valuesdb;
		listViewwish1.setAdapter(happynewyearlist1);
		happynewyearlist1.notifyDataSetChanged();
		
		listViewwish1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				final int arg=arg2;
				// TODO Auto-generated method stub
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						context);
				alertDialogBuilder.setTitle("Delete "+happynewyearlist1.newyearsvalues.get(arg).getTitle());

				// set dialog message
				alertDialogBuilder
						.setMessage(
								"Delete "+happynewyearlist1.newyearsvalues.get(arg).getWishtext())
						.setCancelable(false)
						.setPositiveButton("Delete",
								new DialogInterface.OnClickListener() {
									public void onClick(
											DialogInterface dialog,
											int id) {
										// if this button is clicked,
										
									//	BlockHappynewyearlist.newyearsvalues.remove(arg);//.remove(index-1);
										CallNumber.valuesdb.remove(arg);
										BlockHappynewyearlist.newyearsvalues=CallNumber.valuesdb;
										happynewyearlist1.notifyDataSetChanged();
										
										 dialog.cancel();
									
									}
								})
						.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(
											DialogInterface dialog,
											int id) {
									
										dialog.cancel();
									
									
									}
								});

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				
				alertDialog.show();
				
			}
		});
		
		listViewwish.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				final int arg=arg2;
				// TODO Auto-generated method stub
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						context);
				alertDialogBuilder.setTitle("Delete "+happynewyearlist.newyearsvalues.get(arg).getTitle());

				// set dialog message
				alertDialogBuilder
						.setMessage(
								"Delete "+happynewyearlist.newyearsvalues.get(arg).getWishtext())
						.setCancelable(false)
						.setPositiveButton("Delete",
								new DialogInterface.OnClickListener() {
									public void onClick(
											DialogInterface dialog,
											int id) {
										// if this button is clicked,
DBHelper myDbHelper = new DBHelper(context);
										
										try {
											myDbHelper.openDataBase();
										}catch(SQLException sqle){
											throw sqle;
										}
										myDbHelper.deleteEntry(Happynewyearlist.newyearsvalues.get(arg));
										myDbHelper.close();
									//	BlockHappynewyearlist.newyearsvalues.remove(arg);//.remove(index-1);
										CallNumber.values.remove(arg);
									    Happynewyearlist.newyearsvalues=CallNumber.values;
										happynewyearlist.notifyDataSetChanged();
										
										 dialog.cancel();
									
									}
								})
						.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(
											DialogInterface dialog,
											int id) {
									
										dialog.cancel();
									
									
									}
								});

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				
				alertDialog.show();
				
			}
		});
		Log.d("dsfsd", "sttttttttdf"+happynewyearlist.getCount());
		Log.d("dsfsd", "stttttttttttdf"+happynewyearlist1.getCount());
	TextView e=(TextView)findViewById(R.id.button1);
	e.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(listViewwish.getVisibility()!=View.GONE)
		listViewwish.setVisibility(View.GONE);	
			else
				listViewwish.setVisibility(View.VISIBLE);
		}
	});
	TextView ed=(TextView)findViewById(R.id.editText2);
	ed.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(listViewwish1.getVisibility()!=View.GONE)
				listViewwish1.setVisibility(View.GONE);	
					else
						listViewwish1.setVisibility(View.VISIBLE);
		}
	});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		Log.d("dsfsd", "sttttttttdf"+happynewyearlist.getCount());
		Log.d("dsfsd", "stttttttttttdf"+happynewyearlist1.getCount());
		happynewyearlist1.notifyDataSetChanged();
		happynewyearlist.notifyDataSetChanged();
		super.onStart();
	}
@Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();

	Log.d("dsfsd", "srrrrrrrrrrdf"+happynewyearlist.getCount());
	Log.d("dsfsd", "srrrrrrrrrdf"+happynewyearlist1.getCount());
	happynewyearlist1.notifyDataSetChanged();
	happynewyearlist.notifyDataSetChanged();
}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_settings:
				startActivityForResult(new Intent(getApplicationContext(), Datainput.class),123);
			
				break;
		default:
				return false;
		}
		return false;
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==123)
		{
			//CallNumber.values.clear();
			 /*DBHelper myDbHelper = new DBHelper(this);
			
				try {
					myDbHelper.openDataBase();
				}catch(SQLException sqle){
					throw sqle;
				}
				CallNumber.values= myDbHelper.getQuestionSet();
				myDbHelper.close();*/
				
			Happynewyearlist.newyearsvalues=	CallNumber.values;
			happynewyearlist.notifyDataSetChanged();
		}
		
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
		case R.id.butn1:
			startActivityForResult(new Intent(getApplicationContext(), Datainput.class),123);
			break;
		

		default:
			break;
		}
	}

}
