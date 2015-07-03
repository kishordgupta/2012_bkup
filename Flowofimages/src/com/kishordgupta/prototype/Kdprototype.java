package com.kishordgupta.prototype;



import com.kishordgupta.helper.Timestamp;
import com.kishordgupta.model.Event;
import com.kishordgupta.model.Events;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Kdprototype extends Activity implements OnClickListener{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button Goalstart = (Button)findViewById(R.id.game_clock_start);
		Button game_clock_stop = (Button)findViewById(R.id.game_clock_stop);
		Button goalagainst = (Button)findViewById(R.id.goalagainst);
		Button goalfor = (Button)findViewById(R.id.goalfor);
		Button goalscored = (Button)findViewById(R.id.goalscored);
		
		Button penalty = (Button)findViewById(R.id.penalty);
		Button result = (Button)findViewById(R.id.result);
		Button shiftend = (Button)findViewById(R.id.shiftend);
		
		Button shiftstart = (Button)findViewById(R.id.shiftstart);
		Button shottaken = (Button)findViewById(R.id.shottaken);
		Button turnover = (Button)findViewById(R.id.turnover);
		
		Goalstart.setOnClickListener(this);
		game_clock_stop.setOnClickListener(this);
		goalagainst.setOnClickListener(this);
		goalfor.setOnClickListener(this);
		goalscored.setOnClickListener(this);
		
		penalty.setOnClickListener(this);
		result.setOnClickListener(this);
		shiftend.setOnClickListener(this);
		
		shiftstart.setOnClickListener(this);
		shottaken.setOnClickListener(this);
		turnover.setOnClickListener(this);
		
		
		
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Events.eventlist.clear();
		finish();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.game_clock_start:
            Timestamp.entryevent("Game Clock Start");
            
			break;
		case R.id.game_clock_stop:

			Timestamp.entryevent("Game Clock Stop");
			break;
		case R.id.goalagainst:
			Timestamp.entryevent("Goal Against");
			break;
		case R.id.goalfor:
			Timestamp.entryevent("Goal For");
			break;
		case R.id.goalscored:
			Timestamp.entryevent("Goal Scored");
			break;
		case R.id.penalty:
			Timestamp.entryevent("Penalty");
			break;
		case R.id.result:
			startActivity(new Intent(this, ResultActivity.class));
			break;
		case R.id.shiftend:
			Timestamp.entryevent("Shift End");
			break;
		case R.id.shiftstart:
			Timestamp.entryevent("Shift Start");
			break;
		case R.id.shottaken:
			Timestamp.entryevent("Shot Taken");
			break;
		case R.id.turnover:
			Timestamp.entryevent("Turn Over");
			break;

		default:
			break;
		}
	}
}
