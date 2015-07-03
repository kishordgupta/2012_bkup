package com.kd.hockymain;

import java.util.ArrayList;
import java.util.List;


import sqlitedb.SQLiteWraper;

import com.kd.tab.TabsActivity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Hockymainactivity extends Activity implements OnClickListener{
	private ListView listViewPlayer;
    private Context ctx;
    ArrayList<Playerdata> values;
	Listplayernameadapter listplayernameadapter ;
	SQLiteWraper sqlitedb;
	 
	  private String GetTextFromEditText(int id)
	    {
	    	return ((EditText)findViewById(id)).getText().toString();
	    }
	    
	    private String GetTextFromPeriodSpinner(int id, View v)
	    {
	    	return ((PeriodSpinner)v.findViewById(id)).selectedvalue;
	    }
	    
	    private String GetTextFromTypeSpinner(int id, View v)
	    {
	    	return ((TypeSpinner)v.findViewById(id)).selectedvalue;
	    }
	 
	    private String GetTextFromPlayerSpinner(int id, View v)
	    {
	    	return ((PlayerSpinner)v.findViewById(id)).selectedvalue;
	    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		
		Button start = (Button)findViewById(R.id.StartScorring);
		start.setOnClickListener(this);
		Button add = (Button)findViewById(R.id.add_player);
		add.setOnClickListener(this);
		ctx=this;
		Kdexcel k = new Kdexcel(ctx);
		k.getfile();
        sqlitedb = new SQLiteWraper(getApplicationContext());
		
	
		values = new ArrayList<Playerdata>();
		Playerdata playerdata = new Playerdata();
		Listplayernameadapter.values.add(playerdata);
		DBgetplayer();
		listViewPlayer = (ListView) findViewById(R.id.list_of_players);
		 listplayernameadapter = new Listplayernameadapter(ctx
				);
	
		listViewPlayer.setAdapter(listplayernameadapter);
		
	}
	private void DBgetplayer() {
		// TODO Auto-generated method stub
		Cursor cur = sqlitedb.getrow();
		Toast.makeText(this, ""+cur.getCount()+"d"+cur.getColumnCount(),Toast.LENGTH_LONG).show();
		cur.moveToPosition(0);
		int i=0;
				while (cur.isAfterLast() == false) {
					Playerdata playerdata = new Playerdata();
					playerdata.playername=cur.getString(0);
					playerdata.playernumber=cur.getString(1);
					i++;
					Listplayernameadapter.values.add(playerdata);
					cur.moveToNext();
				}
				

				cur.close();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		Listplayernameadapter.values.clear();
    	Listplayeradapter.values.clear();
    	Listawayadapter.values.clear();
    	DBgetplayer();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.add_player:
			Playerdata playerdata = new Playerdata();
			Listplayernameadapter.values.add(playerdata);
			listplayernameadapter.notifyDataSetChanged();
			break;
      case R.id.StartScorring:
    	
    	  if(Listplayernameadapter.values.size()>3)
    	  {
    	   Extra.Type=GetTextFromEditText(R.id.type);
    	   Extra.location=GetTextFromEditText(R.id.location);
    	   startActivity(new Intent(ctx,TabsActivity.class));
    	   Dbmanage();
    	   }
    	  else
    		  Toast.makeText(this, "Add few more players before start scoring", Toast.LENGTH_LONG).show();
    	  break;
		default:
			break;
		}
	}
	private void Dbmanage() {
		// TODO Auto-generated method stub
		
		sqlitedb.deleteall("PlayerInfo");
		for(Playerdata p: Listplayernameadapter.values)
		{
		ContentValues cValues = new ContentValues();
		cValues.put("PlayerName",p.playername );
		cValues.put("PlayerID", p.playernumber);
		
		long insertId = sqlitedb.Insert("PlayerInfo", "", cValues);
		
		
		
		}
		
		
	}
	private void loadlist() {
		Playerdata p = new Playerdata();
		// TODO Auto-generated method stub
		
	}
}
