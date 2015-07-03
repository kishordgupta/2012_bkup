package game;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;

import com.qwerjk.better_text.BorderedTextView;
import com.unlock.briefcase.password.GetNetworkStatus;
import com.unlock.briefcase.password.R;

import sponsorpay.User;
import xoxo.sound.SoundManager;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.OnWheelScrollListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.NumericWheelAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class PasswActivity extends Fragment {
	View view =null;
	Handler handler = null;
	Handler timea = null;
	Runnable r =null;
	Runnable tr =null;
	 Random rgen ;
	String pub="";
	public static ArrayList<String> as = new ArrayList<String>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{	final Context c = getActivity();
	   // super.onCreate(savedInstanceState);
    	 view = inflater.inflate(R.layout.passw_layout, container, false);
       // setContentView(R.layout.passw_layout);
    	 as = new ArrayList<String>();
    	as.clear();
        initWheel(R.id.passw_1);
        
        initWheel(R.id.passw_2);
      
        initWheel(R.id.passw_3);
     
        initWheel(R.id.passw_4);
         r = new Runnable()
    	{
    	    public void run() 
    	    {
    	    	   mixWheel(R.id.passw_1);
                   mixWheel(R.id.passw_2);
                   mixWheel(R.id.passw_3);
                   mixWheel(R.id.passw_4);
    	        handler.postDelayed(this, 1000);
    	    }
    	};
    	 final TextView textcoun = (TextView) view.findViewById(R.id.Count);
    	 final TextView text = (TextView) view.findViewById(R.id.pwd_status);
    	 final Button mix = (Button)view.findViewById(R.id.btn_mix);
    	 mix.setText("Play");
    	 tr = new Runnable()
     	{int tie=6;
     	    public void run() 
     	    {	textcoun.setText(" " + tie + "  Sec to unlock");
     	    	mix.setText(" " + tie + " "+ " Sec to unlock");
     	    	tie--;
     	    	if(tie==0)
     	    	{  SoundManager.playSound(1, 1);
     	    		tie=6;
     	    		mix.setText("You Lost, Play again");
     	    		textcoun.setText("Time End and You lost");
     	    		  initWheelff(R.id.passw_1);
                      
                      initWheelff(R.id.passw_2);
                    
                      initWheelff(R.id.passw_3);
                   
                      initWheelff(R.id.passw_4);
                      mix.setEnabled(true);
     	    	}
     	    	else if(testPin(0, 10, 20,30).equalsIgnoreCase(pub))
     	    	{  mix.setEnabled(true);
     	    	tie=6;
     	    	  SoundManager.playSound(2, 1);
     	    	textcoun.setText("!!!!!!! Damn you unlock it 100 point bonus");
     	    		mix.setText("You Win, Play again");
     	    		User.Coins_points =User.Coins_points+100;
     	    	}
     	    	else
     	    		timea.postDelayed(this, 1000);
     	    }
     	};
       
        mix.setOnClickListener(new OnClickListener() {
        
            public void onClick(View v) {
            	if(User.Coins_points<20)
            	{
            		AlertDialog alertDialog = new AlertDialog.Builder(c).create();

                    

                	alertDialog.setTitle("You Dont Have enough Point to Play");

             

                	alertDialog.setMessage("You need at least 20 points to play, earn point and come back to play");


                	alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, ":( sorry", new DialogInterface.OnClickListener() {
                	  public void onClick(DialogInterface dialog, int which) {
                		  SoundManager.playSound(1, 1);
                	   //here you can add functions

                	} });
                	
                	alertDialog.show();
            	}
            	else{
            		User.Coins_points =User.Coins_points-10;
            	handler.removeCallbacks(r);
            	as = new ArrayList<String>();
            	as.clear();
            	 initWheel(R.id.passw_1);
                 
                 initWheel(R.id.passw_2);
               
                 initWheel(R.id.passw_3);
              
                 initWheel(R.id.passw_4);
            	  initWheelon(R.id.passw_1);
                  
                  initWheelon(R.id.passw_2);
                
                  initWheelon(R.id.passw_3);
               
                  initWheelon(R.id.passw_4);
                  mixWheel(R.id.passw_1);
                  mixWheel(R.id.passw_2);
                  mixWheel(R.id.passw_3);
                  mixWheel(R.id.passw_4);
            	String t = ""+(int)(Math.random() * 10000);
            	if(t.length()==3)
            		t=t+8;
            	
            	
            	AlertDialog alertDialog = new AlertDialog.Builder(c).create();

            

            	alertDialog.setTitle("Code is " + t);

         

            	alertDialog.setMessage("Unlock it by wheel the lock"+ " Only 7 seconds will be given");


            	alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Start ", new DialogInterface.OnClickListener() {
            	  public void onClick(DialogInterface dialog, int which) {
            		  timea = new Handler();
                  	timea.postDelayed(tr, 1000);
                 
                  	updateStatus();
            	   //here you can add functions

            	} });
             	pub=t;
             	BorderedTextView text = (BorderedTextView) view.findViewById(R.id.head);
             	 text.setText(t);
             	 v.setEnabled(false);

            	alertDialog.show();
            	
            	}
            }
        });
      
        
        return view;
    }
  
    @Override
    public void onStart() {
    	super.onStart();
    	// TODO Auto-generated method stub
    	handler=new Handler();
    	

    	handler.postDelayed(r, 1000);
    	
    }
    // Wheel scrolled flag
    private boolean wheelScrolled = false;
    
    // Wheel scrolled listener
    OnWheelScrollListener scrolledListener = new OnWheelScrollListener() {
        public void onScrollingStarted(WheelView wheel) {
            wheelScrolled = true;
        }
        public void onScrollingFinished(WheelView wheel) {
            wheelScrolled = false;
            updateStatus();
        }
    };
    
    // Wheel changed listener
    private OnWheelChangedListener changedListener = new OnWheelChangedListener() {
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            if (!wheelScrolled) {
                updateStatus();
            }
        }
    };
    
    /**
     * Updates entered PIN status
     */
    private void updateStatus() {
        TextView text = (TextView) view.findViewById(R.id.pwd_status);
        if (testPin(0, 10, 20,30).equalsIgnoreCase(pub)) {
        	SoundManager.playSound(2, 1);
            text.setText("Congratulation!");
            
        } else {
            text.setText("Invalid PIN");
            Button mix = (Button)view.findViewById(R.id.btn_mix);
            if(!mix.isEnabled()){
            GetNetworkStatus.vibrare(getActivity(), 10);
          
            }
        }
        int ia=0;
        Log.d("sdf",testPin(0,10,20, 30) + " " +pub);
      
    }

    /**
     * Initializes wheel
     * @param id the wheel widget Id
     */
    private void initWheel(int id) {
        WheelView wheel = getWheel(id);
        wheel.setViewAdapter(new NumericWheelAdapter(getActivity(), 0, 9));
        wheel.setCurrentItem((int)(Math.random() * 10));
        
        wheel.addChangingListener(changedListener);
        wheel.addScrollingListener(scrolledListener);
       wheel.setCyclic(true);
       wheel.setInterpolator(new AnticipateOvershootInterpolator());
     //  wheel.setActivated(false);
       wheel.setEnabled(false);
    }
    private void initWheelon(int id) {
        WheelView wheel = getWheel(id);
      
   //     wheel.setActivated(true);
        wheel.setEnabled(true);
      
    }
    private void initWheelff(int id) {
        WheelView wheel = getWheel(id);
        wheel.setEnabled(false);
     //   wheel.setActivated(false);
        wheel.setInterpolator(new AnticipateOvershootInterpolator());
    }
    /**
     * Returns wheel by Id
     * @param id the wheel Id
     * @return the wheel with passed Id
     */
    private WheelView getWheel(int id) {
    	return (WheelView) view.findViewById(id);
    }
    
    /**
     * Tests entered PIN
     * @param v1
     * @param v2
     * @param v3
     * @param v4
     * @return true 
     */
    private String testPin(int v1, int v2, int v3, int v4) {
    	return ""+testWheelValue(R.id.passw_1, v1) +""+ testWheelValue(R.id.passw_2, v2)  +""+
    		testWheelValue(R.id.passw_3, v3) +""+testWheelValue(R.id.passw_4, v4);
    }
    
    /**
     * Tests wheel value
     * @param id the wheel Id
     * @param value the value to test
     * @return true if wheel value is equal to passed value
     */
    private String testWheelValue(int id, int value) {
    	int i = getWheel(id).getCurrentItem()+value;
    	String s="";
    	try{
    	s=PasswActivity.as.get(i).toString();
    	}
    	catch(Exception e)
    	{
    		s="";
    	}
    	return s+"";
    }
    
    /**
     * Mixes wheel
     * @param id the wheel id
     */
    private void mixWheel(int id) {
        WheelView wheel = getWheel(id);
        wheel.scroll(-25 + (int)(Math.random() * 50), 2000);
    }
}
