package com.kd.tab;


import com.kd.hockymain.AwayData;
import com.kd.hockymain.Listawayadapter;
import com.kd.hockymain.Listplayeradapter;
import com.kd.hockymain.Listplayernameadapter;
import com.kd.hockymain.Playerdata;
import com.kd.hockymain.R;
import com.kd.hockymain.ScorerData;

import java.util.ArrayList;





import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Away extends Activity {
	public int sectinonumber=0;
	private ListView listViewPlayer;
    private Context ctx;
    
	Listawayadapter listplayernameadapter ;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		Listawayadapter.values.clear();
	ctx=this;
		setContentView(R.layout.away);
		int number=0;
		for (Playerdata playerdata : Listplayernameadapter.values) {
			AwayData aw = new AwayData();
			aw.playerrank=number+"";
			Listawayadapter.values.add(aw);
			number++;
		}
		listViewPlayer = (ListView) findViewById(R.id.list_of_playersaway);
		 listplayernameadapter = new Listawayadapter(ctx
				);
	
		listViewPlayer.setAdapter(listplayernameadapter);
		
	}
	
	

}
