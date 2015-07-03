package com.ekushevashaandolon.motherlanguageday.myandroid;

import com.ekushevashaandolon.motherlanguageday.myandroid.R;
import com.lilakhelait.kishor.listview.Happynewyearlist;
import com.lilakhelait.kishor.listview.Poriched;
import com.lilakhelait.kishor.listview.Poriched1;
import com.lilakhelait.kishor.resource.Wish;
import com.lilakhelait.kishor.resource.event.Happynewyearresource;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class CitiesActivity1 extends Activity implements OnItemClickListener{

       // Data to put in the ListAdapter
       private String[] cities =null;// new String[] {"beauty tips","belly dance tips","boxing","buy to let","camping","car buying","career","college life","culinary","dating","digital filmmaking tips","divorce","diy","driving tips","fashion tips","financial tips","flirting tips","furniture buying tips","gambling tips","gardening tips","golf tips","hair tips","halth tips","hiking tips","house owner tips","house repair tips","insurance tips","investment tips","job search tips","life tips","love tips","matrimony tips","mortgage tips","parenting tips","party planning tips","personal finances tips","pet care tips","photography tips","public speaking tips","relationships tips","sales training tips","sextips","skin care tips","sleep tips","sports coaching tips","texting tips","travel tips","video production tips","wight loss tips","working at home tips",
//};
       private ListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cities_layout);
        fillData();
    }

    // Fill the list with some data, this can be anything really
public	Poriched1 citiesAdapter2 =null;
    public void fillData() {
    
    	Happynewyearresource.getnewyearfilesd(this);
    	//Happynewyearlist.newyearsvalues
    	int i=0;
    	
    	Poriched1.newyearsvalues=Happynewyearlist.newyearsvalues;
    	citiesAdapter2 = new Poriched1(this );
        ListView l = (ListView)findViewById(R.id.list);
        
        l.setOnItemClickListener(this);
        l.setAdapter(citiesAdapter2);
        citiesAdapter2.notifyDataSetChanged();
       
    }
@Override
public void onBackPressed() {
	// TODO Auto-generated method stub
finish();	
}
  

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		 Intent i = new Intent(this, ShowCity.class);
	        String city_name = citiesAdapter2.newyearsvalues.get(arg2).getTitle();
	        i.putExtra("city_name",citiesAdapter2.newyearsvalues.get(arg2).getWishtext());
	        i.putExtra("til",citiesAdapter2.newyearsvalues.get(arg2).getTitle());
	        // Create the view using FirstGroup's LocalActivityManager
	        i.putExtra("grp",1);
	        View view = FirstGroup1.group.getLocalActivityManager()
	        .startActivity("show_city", i
	        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
	        .getDecorView();

	        // Again, replace the view
	        FirstGroup1.group.replaceView(view);
	}
}

