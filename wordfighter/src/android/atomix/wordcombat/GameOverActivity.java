package android.atomix.wordcombat;

import android.app.Activity;
import android.atomix.wordcombat.supports.ConstantValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends Activity implements OnClickListener {
	
	private Button btnNewgame, btnRestart;
	private TextView txtGameMsg, txtViewLevel;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_over);
		
		btnNewgame = (Button) findViewById(R.id.btn_new_game);
		btnNewgame.setOnClickListener(this);
		
		btnRestart = (Button) findViewById(R.id.btn_restart);
		btnRestart.setOnClickListener(this);
		
		txtViewLevel = (TextView) findViewById(R.id.txt_view_level);
		
		txtGameMsg = (TextView) findViewById(R.id.txt_view_msg);
		
		if(ConstantValues.isGameWin)
		{
			txtGameMsg.setText("Win");
		}
		else
		{
			txtGameMsg.setText("Loss");
		}
		
		txtViewLevel.setText("You have completed level "+ConstantValues.levelCount);
	}
	@Override
	public void onClick(View v) 
	{
		switch (v.getId()) 
		{
			case R.id.btn_new_game:
				ConstantValues.isNewGame = true;
				startActivity(new Intent(GameOverActivity.this, SettingActivity.class));
				finish();
				break;
				
			case R.id.btn_restart:
				startActivity(new Intent(GameOverActivity.this, WordCombatActivity.class));
				finish();
				break;
		
			default:
				break;
		}
	}
	
	@Override
	public void onBackPressed() 
	{
		super.onBackPressed();
		ConstantValues.reset();
		finish();
	}

}
