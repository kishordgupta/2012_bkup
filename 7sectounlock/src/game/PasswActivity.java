package game;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.OnWheelScrollListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.NumericWheelAdapter;
import android.app.Activity;
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
import app.tabsample.R;

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
	{
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
    	 final TextView text = (TextView) view.findViewById(R.id.pwd_status);
    	 final Button mix = (Button)view.findViewById(R.id.btn_mix);
    	 mix.setText("Play");
    	 tr = new Runnable()
     	{int tie=7;
     	    public void run() 
     	    {
     	    	mix.setText(tie + "Sec to unlock");
     	    	tie--;
     	    	if(tie==0)
     	    	{
     	    		tie=7;
     	    		mix.setText("You Lost, Play again");
     	    		  initWheelff(R.id.passw_1);
                      
                      initWheelff(R.id.passw_2);
                    
                      initWheelff(R.id.passw_3);
                   
                      initWheelff(R.id.passw_4);
                      mix.setEnabled(true);
     	    	}
     	    	else if(text.getText().toString().contains("Congratulation!"))
     	    	{  mix.setEnabled(true);
     	    	tie=7;
     	    		mix.setText("You Win, Play again");
     	    	}
     	    	else
     	    		timea.postDelayed(this, 1000);
     	    }
     	};
       
        mix.setOnClickListener(new OnClickListener() {
        
            public void onClick(View v) {
            	handler.removeCallbacks(r);
            	  initWheelon(R.id.passw_1);
                  
                  initWheelon(R.id.passw_2);
                
                  initWheelon(R.id.passw_3);
               
                  initWheelon(R.id.passw_4);
            	String t = ""+(int)(Math.random() * 10000);
            	if(t.length()==3)
            		t=t+8;
            	timea = new Handler();
            	timea.postDelayed(tr, 1000);
            	pub=t;
            	 TextView text = (TextView) view.findViewById(R.id.head);
            	 text.setText(t);
            	 v.setEnabled(false);
            	updateStatus();
            	
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
            text.setText("Congratulation!");
        } else {
            text.setText("Invalid PIN");
        }
        int ia=0;
      //  Log.d("sdf",testPin(0,10,20, 30));
      
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
       wheel.setActivated(false);
       wheel.setEnabled(false);
    }
    private void initWheelon(int id) {
        WheelView wheel = getWheel(id);
      
        wheel.setActivated(true);
        wheel.setEnabled(true);
      
    }
    private void initWheelff(int id) {
        WheelView wheel = getWheel(id);
        wheel.setEnabled(false);
        wheel.setActivated(false);
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
