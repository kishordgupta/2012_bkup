package com.iqrasys.listview;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.iqrasys.resource.PhoneNumberModel;
import com.kd.phonecall.R;

public class ServerNumberList extends ArrayAdapter<PhoneNumberModel> {

	private final Context context;
	public static ArrayList<PhoneNumberModel> listValues = new ArrayList<PhoneNumberModel>();
	public int size = 0;

	public ServerNumberList(Context context) {
		super(context, R.layout.list_item, listValues);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		ViewItem viewItem;
		if (convertView == null) {
			final View row = inflater
					.inflate(R.layout.list_item, parent, false);
			// final int index = position;

			viewItem = new ViewItem();
			viewItem.title = (TextView) row.findViewById(R.id.name);
			viewItem.number = (TextView) row.findViewById(R.id.number);

			viewItem.removeImgBtn = (ImageButton) row.findViewById(R.id.remove);
			viewItem.removeImgBtn.setFocusable(false);

			row.setTag(viewItem);
			convertView = row;
		}

		viewItem = (ViewItem) convertView.getTag();
		PhoneNumberModel numModel = listValues.get(position);

		if (numModel != null) {
			viewItem.title.setTextSize(22);
			viewItem.title.setText(numModel.getTitleText());
			viewItem.title.setTextColor(Color.DKGRAY);
			viewItem.title.setGravity(Gravity.CENTER | Gravity.LEFT);

			viewItem.number.setTextSize(15);
			viewItem.number.setText(numModel.getNumberText());
			viewItem.number.setTextColor(Color.GRAY);
			viewItem.number.setGravity(Gravity.CENTER | Gravity.LEFT);
			// viewItem.number.setLayoutParams(Gravity.LEFT |
			// Gravity.CENTER_VERTICAL);
		}

		// convertView.setTextAlignment(Gravity.CENTER | Gravity.LEFT);

		if (position % 2 == 1) {
			convertView.setBackgroundColor(Color.parseColor("#f2f2f2"));
		} else {
			convertView.setBackgroundColor(Color.parseColor("#dfdfdf"));
		}

		return convertView;
	}

}

class ViewItem {
	TextView title;
	TextView number;

	ImageButton removeImgBtn;
}
