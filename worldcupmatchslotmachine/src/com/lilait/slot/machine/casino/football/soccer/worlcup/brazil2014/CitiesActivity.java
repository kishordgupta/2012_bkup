package com.lilait.slot.machine.casino.football.soccer.worlcup.brazil2014;

import com.lilait.chat.GetNetworkStatus;
import com.lilait.chat.MainActivity;
import com.lilait.slot.machine.casino.football.soccer.worlcup.brazil2014.R;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.OnWheelScrollListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.AbstractWheelTextAdapter;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;
import kankan.wheel.widget.adapters.NumericWheelAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CitiesActivity extends Activity {
    // Scrolling flag
    private boolean scrolling = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cities_layout);
                
        final WheelView country = (WheelView) findViewById(R.id.country);
        country.setVisibleItems(3);
        country.setViewAdapter(new CountryAdapter(this));

        final String cities[][] = new String[][] {
        		new String[] {"New York", "Washington", "Chicago", "Atlanta", "Orlando"},
        		new String[] {"Ottawa", "Vancouver", "Toronto", "Windsor", "Montreal"},
        		new String[] {"Kiev", "Dnipro", "Lviv", "Kharkiv"},
        		new String[] {"Paris", "Bordeaux"},
        		};
        
        final WheelView city = (WheelView) findViewById(R.id.city);
     //   city.setVisibleItems(5);
        city.setViewAdapter(new CountryAdapter(this));
        country.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
			    if (!scrolling) {
			     //  updateCities(city, cities, newValue);
			    }
			}
		});
        
        country.addScrollingListener( new OnWheelScrollListener() {
            public void onScrollingStarted(WheelView wheel) {
                scrolling = true;
            }
            public void onScrollingFinished(WheelView wheel) {
                scrolling = false;
               // updateCities(city, cities, country.getCurrentItem());
            }
        });
  final Context c=this;
        country.setCurrentItem(3);
        city.setCurrentItem(3);
        
        Button share = (Button)findViewById(R.id.btn_share);
        share.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	 TextView text = (TextView) findViewById(R.id.pwd_status);
                 String t= text.getText()+"";
               Share.share("I predict" + t +" going to happen in worldcup football 2014 ........#despicableapps", c);
            }
        });
        Button shae = (Button)findViewById(R.id.btn_shout);
        shae.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	if(GetNetworkStatus.isNetworkAvailable(c))
            	startActivity(new Intent(c, MainActivity.class));
            	else
            		Toast.makeText(c, "You know something called internet? thats need to use it", Toast.LENGTH_LONG).show();
             //  Share.share("I have" + Totalscore +"coins in Anime Big Three slot ........#despicableapps", c);
            }
        });
        
        initWheel(R.id.passw_1);
      //  initWheel(R.id.passw_2);
      //  initWheel(R.id.passw_3);
        initWheel(R.id.passw_4);
        
        Button mix = (Button)findViewById(R.id.btn_mix);
        mix.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mixWheel(R.id.passw_1);
             //   mixWheel(R.id.passw_2);
             //   mixWheel(R.id.passw_3);
                mixWheel(R.id.passw_4);
            }
        });
        
    
    }
    
    // Wheel scrolled flag
    private boolean wheelScrolled = false;
    
    // Wheel scrolled listener
    OnWheelScrollListener scrolledListener = new OnWheelScrollListener() {
        public void onScrollingStarted(WheelView wheel) {
            wheelScrolled = true;
            updateStatus();
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
    private void initWheel(int id) {
        WheelView wheel = getWheel(id);
        wheel.setViewAdapter(new NumericWheelAdapter(this, 0, 9));
        wheel.setCurrentItem((int)(Math.random() * 10));
        
        wheel.addChangingListener(changedListener);
        wheel.addScrollingListener(scrolledListener);
        wheel.setCyclic(true);
        wheel.setEnabled(false);
        wheel.setInterpolator(new AnticipateOvershootInterpolator());
    }
    
    private void updateStatus() {
        TextView text = (TextView) findViewById(R.id.pwd_status);
     
      CountryAdapter cd = new CountryAdapter(this);
      String t1=""+cd.getItemText(getWheel(R.id.country).getCurrentItem());
      String t2=""+cd.getItemText(getWheel(R.id.city).getCurrentItem());
      NumericWheelAdapter nw = new NumericWheelAdapter(this);
      String r1=""+nw.getItemText(getWheel(R.id.passw_1).getCurrentItem());
      String r2=""+nw.getItemText(getWheel(R.id.passw_4).getCurrentItem());
            text.setText(t1+":"+r1+"-"+r2+":"+t2);
       
    }

    /**
     * Updates the city wheel
     */
    private void updateCities(WheelView city, String cities[][], int index) {
        ArrayWheelAdapter<String> adapter =
            new ArrayWheelAdapter<String>(this, cities[index]);
        adapter.setTextSize(18);
        city.setViewAdapter(adapter);
        city.setCurrentItem(cities[index].length / 2);        
    }
    private void mixWheel(int id) {
        WheelView wheel = getWheel(id);
        wheel.scroll(-25 + (int)(Math.random() * 50), 2000);
    }
    private WheelView getWheel(int id) {
    	return (WheelView) findViewById(id);
    }
    
    /**
     * Adapter for countries
     */
    private class CountryAdapter extends AbstractWheelTextAdapter {
        // Countries names
        private String countries[] =
            new String[] {"Algeria",
        		"Argentina",
        		"Australia",
        		"Belgium",
        		"Bosnia",
        		"Brazil",
        		"Cameroon",
        		"Chile",
        		"Colombia",
        		"CostaRica",
        		"CôtedIvoire",
        		"Croatia",
        		"Ecuador",
        		"England",
        		"France",
        		"Germany",
        		"Ghana",
        		"Greece",
        		"Honduras",
        		"Iran",
        		"Italy",
        		"Japan",
        		"Korea",
        		"Mexico",
        		"Netherlands",
        		"Nigeria",
        		"Portugal",
        		"Russia",
        		"Spain",
        		"Switzerland",
        		"Uruguay",
        		"USA"};
        // Countries flags
        private int flags[] =
            new int[] {R.drawable.dz,
        		    R.drawable.ar,
        		    R.drawable.au,
        		    R.drawable.be,
        		    R.drawable.ba, 
        		    R.drawable.br,
        		    R.drawable.cm,
        		    R.drawable.cl
        		,R.drawable.co,
        		R.drawable.cr,
        		R.drawable.ci,
        		R.drawable.hr,
        		R.drawable.ec,
        		R.drawable.en,R.drawable.fr,R.drawable.de,R.drawable.gh,R.drawable.gr,
        		R.drawable.hn,R.drawable.ir,
        		R.drawable.it,R.drawable.jp,R.drawable.kr,R.drawable.mx,R.drawable.lu,R.drawable.ng
        		,R.drawable.pt,
        		R.drawable.ru,R.drawable.es,R.drawable.ch,R.drawable.uy,R.drawable.us};
        
        /**
         * Constructor
         */
        protected CountryAdapter(Context context) {
            super(context, R.layout.country_layout, NO_RESOURCE);
            
            setItemTextResource(R.id.country_name);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            ImageView img = (ImageView) view.findViewById(R.id.flag);
            img.setImageResource(flags[index]);
            return view;
        }
        
        @Override
        public int getItemsCount() {
            return countries.length;
        }
        
        @Override
        protected CharSequence getItemText(int index) {
            return countries[index];
        }
    }
}
