package radioklub.sekhontech.com.adapter;

import java.util.List;

import radioklub.sekhontech.com.entity.Station;

import com.androidquery.AQuery;
import com.example.swipeuiforupclose.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
//import android.widget.ImageView;
import android.widget.TextView;

public class StationListAdapter extends ArrayAdapter<Station> {
	//Member variables
	private LayoutInflater mInflater;
	private AQuery mQuery;
	
	//Constuctor
	public StationListAdapter(Context context, int resource,
			List<Station> objects) {
		super(context, resource, objects);
		mInflater = LayoutInflater.from(context);
	}
	
	//Lifecycle
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		
		if (view == null) {
			view = mInflater.inflate(R.layout.view_station_row, null);
		}
		mQuery = new AQuery(view);
		mQuery.id(R.id.iv_station_icon).image(getItem(position).getIcon(), true, true);
		
		TextView name = (TextView) view.findViewById(R.id.tv_station_name);

		name.setText(getItem(position).getmName());
		name.setTextColor(Color.WHITE);
		return view;
	}

}
