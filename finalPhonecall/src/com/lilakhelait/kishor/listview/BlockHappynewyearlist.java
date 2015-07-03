package com.lilakhelait.kishor.listview;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kd.phonecall.R;
import com.lilakhelait.kishor.model.CallNumber;
import com.lilakhelait.kishor.resource.Wish;

public class BlockHappynewyearlist extends ArrayAdapter<Wish> {
	  private final Context context;
	  public static  ArrayList<Wish> newyearsvalues=new ArrayList<Wish>();
public int size=0;
	  public BlockHappynewyearlist(Context context) {
	    super(context, R.layout.list, newyearsvalues);
	    this.context = context;
	//    this.values = values;
	  
	  }

	 

	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	   
		ViewHolder1 holder;
		   if(convertView==null){
			   final View row = inflater.inflate(R.layout.list, parent, false);
				final int index = position;
		 holder = new ViewHolder1();
		 holder.wtring = (TextView)row.findViewById(R.id.name);
		 holder.number = (TextView)row.findViewById(R.id.number);
		 holder.email=(ImageButton)row.findViewById(R.id.remove);
		 holder.email.setFocusable(false);
		/* holder.email.setOnClickListener(new OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(index<=newyearsvalues.size()){
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						context);
				alertDialogBuilder.setTitle("Delete "+newyearsvalues.get(index).getTitle());

				// set dialog message
				alertDialogBuilder
						.setMessage(
								"Delete "+newyearsvalues.get(index).getWishtext())
						.setCancelable(false)
						.setPositiveButton("Delete",
								new DialogInterface.OnClickListener() {
									public void onClick(
											DialogInterface dialog,
											int id) {
										// if this button is clicked,
										newyearsvalues.remove(index-1);
										CallNumber.valuesdb.remove(index-1);
										 
										 notifyDataSetChanged();
										 dialog.cancel();
									
									}
								})
						.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(
											DialogInterface dialog,
											int id) {
									
										dialog.cancel();
										notifyDataSetChanged();
									
									}
								});

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				
				alertDialog.show();
				}
			}
		});*/
         row.setTag(holder);
         convertView=row;
		    }
		    else
		    {
		    	
		    }
		holder = (ViewHolder1) convertView.getTag();
		Wish String = newyearsvalues.get(position);
		if(String!=null)
		{

			holder.wtring.setText(String.getTitle());
			holder.wtring.setTextColor(Color.DKGRAY);
			holder.number.setText(String.getWishtext());
			holder.number.setTextColor(Color.GRAY);
			
		}
		if (position % 2 == 1) {
			convertView.setBackgroundColor(Color.parseColor("#f2f2f2"));  
		} else {
			convertView.setBackgroundColor(Color.parseColor("#dfdfdf"));  
		}
		return convertView;
	}
	
	} 

class ViewHolder1 {
	
	TextView wtring;
	ImageButton sms;
	TextView number;
	Button share;
	ImageButton email;
	
}