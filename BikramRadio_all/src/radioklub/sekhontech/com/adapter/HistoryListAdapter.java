package radioklub.sekhontech.com.adapter;

import java.util.List;

import player.DBHelper;

import radioklub.sekhontech.com.entity.Station;

import com.androidquery.AQuery;
import com.example.swipeuiforupclose.MainActivity;
import com.example.swipeuiforupclose.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
//import android.widget.ImageView;
import android.widget.TextView;

public class HistoryListAdapter extends ArrayAdapter<Station> {
	//Member variables
	private LayoutInflater mInflater;
	private AQuery mQuery;
	
	//Constuctor
	public HistoryListAdapter(Context context, int resource,
			List<Station> objects) {
		super(context, resource, objects);
		mInflater = LayoutInflater.from(context);
	}
	
	//Lifecycle
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		 ViewHolder holder; 
		View view = convertView;
		
		if (view == null) {
			view = mInflater.inflate(R.layout.view_hisrory_row, null);
			holder = new ViewHolder();   
		mQuery = new AQuery(view);
		mQuery.id(R.id.iv_station_icon).image(getItem(position).getIcon(), true, true);
		
		holder.name = (TextView) view.findViewById(R.id.tv_station_name);

		holder.name.setText(getItem(position).getmName());
		holder.name.setTextColor(Color.WHITE);
		
		
		holder.fav = (ImageView) view.findViewById(R.id.favourite);
		holder.fav.setTag(position);
		holder.fav.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int id = (Integer) v.getTag();
				DBHelper.deleteTitle(getItem(id).getmName());
				MainActivity.mStations.clear();
				MainActivity.mStations.addAll(DBHelper.getfavouriteset());
				notifyDataSetChanged();
				
			}
		});
		view.setTag(holder);      
		
		}
		else {                
		    // Get the ViewHolder back to get fast access to the TextView                
		    // and the ImageView.
		
			  

		    holder = (ViewHolder) view.getTag();

		   }
		holder.name.setText(getItem(position).getmName()); 
		return view;
	}
	static class ViewHolder {            
	    TextView name;            
	    ImageView fav;        
	} 
}
