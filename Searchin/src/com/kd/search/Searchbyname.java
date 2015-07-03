package com.kd.search;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.kd.search.list.Helperdata;
import com.kd.search.list.KdList;
import com.kd.search.list.Movielist;
import com.kd.search.swipe.SimpleGestureListener;
import com.kd.search.webscrapper.StackParser;

public class Searchbyname extends BaseActivity implements OnClickListener , SimpleGestureListener{
	private swipe detector;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchresult);
		
           setTitle(Helperdata.type);
           detector = new swipe(this,this);
		Movielist.newyearsvalues= KdList.Searchlist;
		ListView listViewwish = (ListView) findViewById(R.id.list_of_movies);
		Movielist happynewyearlist = new Movielist(this);
		final String[] parray =new String[KdList.Searchlist.size()];
		int i=0;
		for (String string : KdList.Searchlist) {
			parray[i]=string;
			i++;
		}
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, parray);
		final AutoCompleteTextView ed = (AutoCompleteTextView) findViewById(R.id.bank);
		ed.setAdapter(adapter);
		ed.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				int selection = 0;
				for (String string : parray) {
					selection++;
					
					if (string.contains(ed.getText())) {
				
					
						adapter.notifyDataSetChanged();
						
					
						break;
					}
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				int selection = 0;
				for (String string : parray) {
					selection++;
				
					if (string.contains(ed.getText())) {
					
					
						adapter.notifyDataSetChanged();
						
						selection = 0;
						break;
					}
				}
			}
		});
		
		listViewwish.setAdapter(happynewyearlist);
		Button b= (Button)findViewById(R.id.search);
		b.setOnClickListener(this);
	}
	
	
@Override
public void onBackPressed() {
	// TODO Auto-generated method stub
	
	super.onBackPressed();
}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.search:
			
			Helperdata.SearchText=GetTextFromEditText(R.id.bank).replace("  ", "");
			  finish();
			startActivity(new Intent(this, StackParser.class));
			break;

		default:
			break;
		}
	}

	@Override
    public boolean dispatchTouchEvent(MotionEvent me){
        // Call onTouchEvent of SimpleGestureFilter class
         this.detector.onTouchEvent(me);
       return super.dispatchTouchEvent(me);
    }
	
	boolean flag =false;
    @Override
     public void onSwipe(int direction) {
      String str = "";
      if(!flag){
      switch (direction) {
      
      case swipe.SWIPE_RIGHT : str = "Swipe Right";
                                           if(Helperdata.i==1)
                                        	   Helperdata.i=2;
                                           else     if(Helperdata.i==0)
                                        	   Helperdata.i=1;
                                           else  if(Helperdata.i==2)
                                        	   Helperdata.i=0;
                                           typeset(Helperdata.i);
                                         //  Helperdata.SearchText=GetTextFromEditText(R.id.bank).replace("  ", "");
                                           finish();
                                           startActivity(new Intent(this, StackParser.class));
                                               break;
                                              
      case swipe.SWIPE_LEFT :  str = "Swipe Left";
      if(Helperdata.i==1)
    	  Helperdata.i=2;
      else   if(Helperdata.i==0)
    	  Helperdata.i=1;
      else  if(Helperdata.i==2)
    	  Helperdata.i=0;
      typeset(Helperdata.i);
      
     // Helperdata.SearchText=GetTextFromEditText(R.id.bank).replace("  ", "");
      finish();
		startActivity(new Intent(this, StackParser.class));
		break;
    /*                                                 break;
      case SimpleGestureListener.SWIPE_DOWN :  str = "Swipe Down";
                                                     break;
      case SimpleGestureListener.SWIPE_UP :    str = "Swipe Up";
                                                     break;*/
      
      }}
      else
    	  flag=false;
       Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
     }
      
     @Override
     public void onDoubleTap() {
    	 if(Helperdata.i==1)
    		 Helperdata.i=2;
    	 else if(Helperdata.i==0)
    		 Helperdata.i=1;
    	 else if(Helperdata.i==2)
    		 Helperdata.i=0;
    	      typeset(Helperdata.i);
    	      flag=true;
    	      finish();
              startActivity(new Intent(this, StackParser.class));
        Toast.makeText(this, Helperdata.i+"Double Tap", Toast.LENGTH_SHORT).show();
     }
	
         public void typeset(int i)
         {
        	 Toast.makeText(this,i+ "D", Toast.LENGTH_SHORT).show();
        	 if(i==0)
        		 Helperdata.type=Helperdata.type_web;
        	 if(i==1)
        		 Helperdata.type=Helperdata.type_images;
        	 if(i==2)
        		 Helperdata.type=Helperdata.type_news;
         }
	
}
