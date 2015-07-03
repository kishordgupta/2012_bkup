package com.kd.tab;



import java.util.ArrayList;

import com.kd.hockymain.AwayData;
import com.kd.hockymain.Listawayadapter;
import com.kd.hockymain.Listplayeradapter;
import com.kd.hockymain.Listplayernameadapter;
import com.kd.hockymain.Playerdata;
import com.kd.hockymain.R;
import com.kd.hockymain.ScorerData;





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
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends Activity {
	public int sectinonumber=0;
	private ListView listViewPlayer;
    private Context ctx;
    
	Listplayeradapter listplayernameadapter ;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	
		setContentView(R.layout.home);
		Listplayeradapter.values.clear();
		ctx=this;
	//	setContentView(R.layout.away);
		int number=0;
		//Toast.makeText(this,Listplayernameadapter.values.size()+"" ,Toast.LENGTH_LONG).show();
		for (Playerdata playerdata : Listplayernameadapter.values) {
			ScorerData aw = new ScorerData();
			aw.playerrank=playerdata.playernumber+"";
			Listplayeradapter.values.add(aw);
			number++;
		}
		listViewPlayer = (ListView) findViewById(R.id.list_of_homes);
		 listplayernameadapter = new Listplayeradapter(ctx
				);
	
		listViewPlayer.setAdapter(listplayernameadapter);
	}
	
	


}
