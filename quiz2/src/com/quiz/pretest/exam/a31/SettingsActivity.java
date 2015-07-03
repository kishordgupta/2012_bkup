package com.quiz.pretest.exam.a31;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

import com.tmm.android.chuck.quiz.Constants;
import com.tmm.android.chuck.quiz.Initializeoptions;
import com.iswitch.MySwitch;
import com.quiz.pretest.exam.a31.R;

/**
 * @author robert.hinds
 *
 */
// (TextView)findViewById(R.id.ft);
public class SettingsActivity extends Activity implements OnClickListener{

	//TextView ta =null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        
        /**
         * set listener on update button
         */
        Button updateBtn = (Button) findViewById(R.id.nextBtn);
		updateBtn.setOnClickListener(this);
	final	TextView ta = (TextView)findViewById(R.id.ids);
          ta.setText("Question limit");
	//	 ta = (TextView)findViewById(R.id.ids);
		//ta.setText("");
		/**
		 * Set selected button if saved
		 */
		updateButtonWithPreferences();
		
		SeekBar	volumeControl = (SeekBar) findViewById(R.id.volume_bar);
		volumeControl.setProgress(Initializeoptions.numberofq);
		 
        volumeControl.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            int progressChanged = 0;
            
            
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                progressChanged = progress;
               // seekBar.set
                Initializeoptions.numberofq=progress;
                ta.setText("Question limit"+ " "+Initializeoptions.numberofq);
            }
 
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
 
            public void onStopTrackingTouch(SeekBar seekBar) {
            	//Toast t = Toast.makeText(SettingsActivity.this,""+progressChanged, 3000);
            //	t.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
            //	t.setText("seek bar progress:"+progressChanged);
            	//t.setDuration(5000);
            //    t.show();
                ta.setText("Question limit"+ " "+Initializeoptions.numberofq);
                Initializeoptions.savePreferences();
              //  TextView ta = (TextView)findViewById(R.id.ft);
        		
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	// TODO Auto-generated method stub
    	 switch (item.getItemId()) {
         case android.R.id.home:
        	 updatevalues();
        		Intent i = new Intent(this, SplashActivity.class);
        		startActivity(i);
        		finish();
         }
    	return super.onOptionsItemSelected(item);
    }
	/**
	 * Method to update default check box
	 */
	private void updateButtonWithPreferences() {
	
		
		Initializeoptions.S1.viewsw = (MySwitch)findViewById(R.id.s1);		
		Initializeoptions.S2.viewsw = (MySwitch)findViewById(R.id.s2);
		Initializeoptions.S3.viewsw = (MySwitch)findViewById(R.id.s3);
		Initializeoptions.S4.viewsw = (MySwitch)findViewById(R.id.s4);
		Initializeoptions.S5.viewsw = (MySwitch)findViewById(R.id.s5);
		Initializeoptions.S6.viewsw = (MySwitch)findViewById(R.id.s6);
		Initializeoptions.S7.viewsw = (MySwitch)findViewById(R.id.s7);
		Initializeoptions.S8.viewsw = (MySwitch)findViewById(R.id.s8);
		Initializeoptions.S9.viewsw = (MySwitch)findViewById(R.id.s9);
		Initializeoptions.S10.viewsw = (MySwitch)findViewById(R.id.s10);
		Initializeoptions.S11.viewsw = (MySwitch)findViewById(R.id.s11);
		Initializeoptions.S12.viewsw = (MySwitch)findViewById(R.id.s12);
		Initializeoptions.S13.viewsw = (MySwitch)findViewById(R.id.s13);
		Initializeoptions.S14.viewsw = (MySwitch)findViewById(R.id.s14);
		Initializeoptions.S15.viewsw = (MySwitch)findViewById(R.id.s15);
		
		Initializeoptions.S1.viewsw.setChecked(Initializeoptions.S1.shouldadd);
		Initializeoptions.S2.viewsw.setChecked(Initializeoptions.S2.shouldadd);
		Initializeoptions.S3.viewsw.setChecked(Initializeoptions.S3.shouldadd);
		Initializeoptions.S4.viewsw.setChecked(Initializeoptions.S4.shouldadd);
		Initializeoptions.S5.viewsw.setChecked(Initializeoptions.S5.shouldadd);
		Initializeoptions.S6.viewsw.setChecked(Initializeoptions.S6.shouldadd);
		Initializeoptions.S7.viewsw.setChecked(Initializeoptions.S7.shouldadd);
		Initializeoptions.S8.viewsw.setChecked(Initializeoptions.S8.shouldadd);
		Initializeoptions.S9.viewsw.setChecked(Initializeoptions.S9.shouldadd);
		Initializeoptions.S10.viewsw.setChecked(Initializeoptions.S10.shouldadd);
		Initializeoptions.S11.viewsw.setChecked(Initializeoptions.S11.shouldadd);
		Initializeoptions.S12.viewsw.setChecked(Initializeoptions.S12.shouldadd);
		Initializeoptions.S13.viewsw.setChecked(Initializeoptions.S13.shouldadd);
		Initializeoptions.S14.viewsw.setChecked(Initializeoptions.S14.shouldadd);
		Initializeoptions.S15.viewsw.setChecked(Initializeoptions.S15.shouldadd);
		
		
	}

   
   public void updatevalues()
   {
	   Initializeoptions.S1.shouldadd=Initializeoptions.S1.viewsw.isChecked();
	   Initializeoptions.S2.shouldadd=Initializeoptions.S2.viewsw.isChecked();
	   Initializeoptions.S3.shouldadd=Initializeoptions.S3.viewsw.isChecked();
	   Initializeoptions.S4.shouldadd=Initializeoptions.S4.viewsw.isChecked();
	   Initializeoptions.S5.shouldadd=Initializeoptions.S5.viewsw.isChecked();
	   Initializeoptions.S6.shouldadd=Initializeoptions.S6.viewsw.isChecked();
	   Initializeoptions.S7.shouldadd=Initializeoptions.S7.viewsw.isChecked();
	   Initializeoptions.S8.shouldadd=Initializeoptions.S8.viewsw.isChecked();
	   Initializeoptions.S9.shouldadd=Initializeoptions.S9.viewsw.isChecked();
	   Initializeoptions.S10.shouldadd=Initializeoptions.S10.viewsw.isChecked();
	   Initializeoptions.S11.shouldadd=Initializeoptions.S11.viewsw.isChecked();
	   Initializeoptions.S12.shouldadd=Initializeoptions.S12.viewsw.isChecked();
	   Initializeoptions.S13.shouldadd=Initializeoptions.S13.viewsw.isChecked();
	   Initializeoptions.S14.shouldadd=Initializeoptions.S14.viewsw.isChecked();
	   Initializeoptions.S15.shouldadd=Initializeoptions.S15.viewsw.isChecked();
	
	   Initializeoptions.savePreferences(Initializeoptions.S1);
	   Initializeoptions.savePreferences(Initializeoptions.S2);
	   Initializeoptions.savePreferences(Initializeoptions.S3);
	   Initializeoptions.savePreferences(Initializeoptions.S4);
	   Initializeoptions.savePreferences(Initializeoptions.S5);
	   Initializeoptions.savePreferences(Initializeoptions.S6);
	   Initializeoptions.savePreferences(Initializeoptions.S7);
	   Initializeoptions.savePreferences(Initializeoptions.S8);
	   Initializeoptions.savePreferences(Initializeoptions.S9);
	   Initializeoptions.savePreferences(Initializeoptions.S10);
	   Initializeoptions.savePreferences(Initializeoptions.S11);
	   Initializeoptions.savePreferences(Initializeoptions.S12);
	   Initializeoptions.savePreferences(Initializeoptions.S13);
	   Initializeoptions.savePreferences(Initializeoptions.S14);
	   Initializeoptions.savePreferences(Initializeoptions.S15);
	   
	   Initializeoptions.savePreferences();
   }

@Override
public void onBackPressed() {
	// TODO Auto-generated method stub
	updatevalues();
	super.onBackPressed();
}
	@Override
	public void onClick(View arg0) {
		/**
		 * check which settings set and return to menu
		 */
		updatevalues();
		finish();
	/*	if (!checkSelected())
		{
			return;
		}
		else
		{
			SharedPreferences settings = getSharedPreferences(Constants.SETTINGS, 0);
			Editor e = settings.edit();
			e.putInt(Constants.DIFFICULTY, getSelectedSetting());
			e.commit();
			finish();
		}*/
		
	}


	/**
	 * Method to check that a checkbox is selected
	 * 
	 * @return boolean
	 */

	
}
