package com.kd.hockymain;

import java.util.ArrayList;
import java.util.List;




import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

public class Listplayernameadapter extends ArrayAdapter<Playerdata> {
	  private final Context context;
	  public static final  ArrayList<Playerdata> values=new ArrayList<Playerdata>();

	  public Listplayernameadapter(Context context) {
	    super(context, R.layout.view_for_add_player, values);
	    this.context = context;
	//    this.values = values;
	  }

	 

	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View row = inflater.inflate(R.layout.view_for_add_player, parent, false);
		final int positionplayer = position;
		ViewHolder holder;

		 holder = new ViewHolder();
		 holder.playername = (EditText)row.findViewById(R.id.player_name);
         holder.playerrank = (EditText)row.findViewById(R.id.player_rank);
         holder.isactive = (CheckBox)row.findViewById(R.id.isactive);
         
         holder.playername.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				values.get(positionplayer).playername=s+"";
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
		
		});
         holder.playerrank.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				values.get(positionplayer).playernumber=s+"";
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		 holder.isactive.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				values.get(positionplayer).isactive=isChecked;
			}
		});
         row.setTag(holder);

		holder = (ViewHolder) row.getTag();
        Playerdata playerdata = values.get(positionplayer);
		if(playerdata!=null)
		{
			holder.playername.setText(playerdata.playername);
			holder.playerrank.setText(playerdata.playernumber);
			holder.isactive.setChecked(playerdata.isactive);
			
		}
		return row;
	}
	} 

class ViewHolder {
	
	EditText playerrank;
	EditText playername;
	CheckBox isactive;
	
}