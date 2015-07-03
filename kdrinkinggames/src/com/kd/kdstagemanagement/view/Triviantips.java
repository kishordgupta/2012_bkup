package com.kd.kdstagemanagement.view;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kd.dasinnovation.movie.chickflicksurvivalguide.R;
import com.kd.kdstagemanagement.helper.Helperdata;
import com.kd.kdstagemanagement.helper.Timereturn;
import com.kd.kdstagemanagement.list.Movielist;
import com.kd.kdstagemanagement.list.Timemovielist;
import com.kd.kdstagemanagement.list.Trivialist;
import com.kd.kdstagemanagement.model.Constracttimelist;
import com.kd.kdstagemanagement.model.KdList;
import com.kd.kdstagemanagement.model.Timeline;

public class Triviantips extends BaseActivity implements OnClickListener{
	Trivialist happynewyearlist=null;

	CountDownTimer   cdt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timeline);
		SetTextToTextView(R.id.title, Helperdata.selectedmovie.Movietitle);
		Trivialist.newyearsvalues= Constracttimelist.constructtrivialist(Helperdata.selectedmovie.Movieid);
	
		ListView listViewwish = (ListView) findViewById(R.id.list_of_movies);
        happynewyearlist = new Trivialist(this);

		listViewwish.setAdapter(happynewyearlist);
		final ProgressBar bar= (ProgressBar)findViewById(R.id.progressBar1);
		final TextView t =(TextView)findViewById(R.id.youridgoeshere);
		bar.setMax(Timereturn.lengtheturn(Helperdata.selectedmovie.Movielength));
		// 2 minutes in milli seconds

final int lo=Timereturn.lengtheturn(Helperdata.selectedmovie.Movielength);
	    
	  cdt = new CountDownTimer(lo*1000, 1000) { 

	        public void onTick(long millisUntilFinished) {
	        	int total=0;
	            total =(int)((lo*1000) -(int) (millisUntilFinished))/1000;
	            int h=total/60;
	            int ik=total%60;
	            Log.d("dfg", total+" "+lo+"  "+millisUntilFinished);
	            bar.setProgress((int)(total));
	            t.setText(Helperdata.selectedmovie.Movielength+":"+h+"-"+ik);
	        }

	        public void onFinish() {
	             // DO something when 2 minutes is up
	        }
	    };
		
		cdt.start();
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		cdt.cancel();
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.search:
			Helperdata.SearchText=GetTextFromEditText(R.id.bank);
			//startActivity(new Intent(this, Result.class));
			break;

		default:
			break;
		}
	}
}
