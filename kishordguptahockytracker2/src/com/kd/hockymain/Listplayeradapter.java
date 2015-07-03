package com.kd.hockymain;

import java.util.ArrayList;
import java.util.List;





import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Listplayeradapter extends ArrayAdapter<ScorerData> {
	  private final Context context;
	  public static   ArrayList<ScorerData> values=new ArrayList<ScorerData>();

	  public Listplayeradapter(Context context) {
	    super(context, R.layout.view_for_home_player,values);
	    this.context = context;
	//   this.values = values;
	  }
	    private String GetTextFromEditText(int id, View v)
	    {
	    	return ((EditText)v.findViewById(id)).getText().toString();
	    }
	    
	    private String GetTextFromPeriodSpinner(int id, View v)
	    {
	    	return ((PeriodSpinner)v.findViewById(id)).selectedvalue;
	    }
	    
	    private String GetTextFromTypeSpinner(int id, View v)
	    {
	    	return ((TypeSpinner)v.findViewById(id)).selectedvalue;
	    }
	 
	    private String GetTextFromPlayerSpinner(int id, View v)
	    {
	    	return ((PlayerSpinner)v.findViewById(id)).selectedvalue;
	    }

	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    final int positionplayer = position;
		ViewHolderaway1 holder;
	    if (convertView != null)
	    {
	    	holder = new ViewHolderaway1();
			
			 values.get(positionplayer).period= GetTextFromPeriodSpinner(R.id.PeriodSpinner1,convertView);
	         values.get(positionplayer).A1=GetTextFromPlayerSpinner(R.id.A1, convertView);
	         values.get(positionplayer).A2=GetTextFromPlayerSpinner(R.id.A2, convertView);
	         values.get(positionplayer).goal=GetTextFromPlayerSpinner(R.id.Goal, convertView);
	         values.get(positionplayer).type=GetTextFromTypeSpinner(R.id.Spinnertype1, convertView);
	         values.get(positionplayer).plus=GetTextFromEditText(R.id.Plus, convertView);
	         values.get(positionplayer).time=GetTextFromEditText(R.id.time, convertView);
	    	return convertView;
	    }
	    else { 
	    	 View row = inflater.inflate(R.layout.view_for_home_player, parent, false);
	    
		

		 holder = new ViewHolderaway1();
		 holder.period = (PeriodSpinner)row.findViewById(R.id.PeriodSpinner1);
         holder.playerrank = (EditText)row.findViewById(R.id.player_rank);
      
         holder.playerrank = (EditText)row.findViewById(R.id.player_rank);

         holder.playerrank = (EditText)row.findViewById(R.id.player_rank);
        
         
      
	
         row.setTag(holder);

		holder = (ViewHolderaway1) row.getTag();
      ScorerData playerdata = values.get(positionplayer);
		if(playerdata!=null)
		{
		//	holder.playername.setText(playerdata.playername);
			holder.playerrank.setText(playerdata.playerrank);
		//	holder.isactive.setChecked(playerdata.isactive);
			
		}
		return row;
	    }
	}


	} 



class ViewHolderaway1 {
	
	EditText playerrank;
	PeriodSpinner period;
	EditText time;
	EditText minus;
	EditText type;

	
}