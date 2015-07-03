package com.kd.hockymain;

import java.util.ArrayList;
import java.util.List;




import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Listawayadapter extends ArrayAdapter<AwayData> {
	  private final Context context;
	  public static   ArrayList<AwayData> values=new ArrayList<AwayData>();

	  public Listawayadapter(Context context) {
	    super(context, R.layout.view_for_away_player,values);
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
		ViewHolderaway holder;
	    if (convertView != null)
	    {
	    	holder = new ViewHolderaway();
			
			 values.get(positionplayer).period= GetTextFromPeriodSpinner(R.id.ppinner,convertView);
	      
	         values.get(positionplayer).playerrank=GetTextFromEditText(R.id.player_rank, convertView);
	         values.get(positionplayer).type=GetTextFromTypeSpinner(R.id.facilitySpinner, convertView);
	         values.get(positionplayer).minus=GetTextFromEditText(R.id.minus, convertView);
	         values.get(positionplayer).time=GetTextFromEditText(R.id.time, convertView);
	    	return convertView;
	    }
	    else { 
	    	 View row = inflater.inflate(R.layout.view_for_away_player, parent, false);
         
      
	    	 holder = new ViewHolderaway();
         row.setTag(holder);

		holder = (ViewHolderaway) row.getTag();
        AwayData playerdata = values.get(positionplayer);
		if(playerdata!=null)
		{
		//	holder.playername.setText(playerdata.playername);
		//	holder.playerrank.setText(playerdata.playernumber);
		//	holder.isactive.setChecked(playerdata.isactive);
			
		}
		return row;
	}
	} 
}

class ViewHolderaway {
	
	EditText playerrank;
	EditText period;
	EditText time;
	EditText minus;
	EditText type;

	
}