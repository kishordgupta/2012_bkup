package com.iqrasys.listview;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.iqrasys.resource.PhoneNumberModel;
import com.kd.phonecall.R;

public class LocalNumberList extends ArrayAdapter<PhoneNumberModel> {
	private final Context context;
	public static ArrayList<PhoneNumberModel> listValues = new ArrayList<PhoneNumberModel>();
	public int size = 0;

	public LocalNumberList(Context context) {
		super(context, R.layout.list_item, listValues);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		ViewItemProperty viewItem;
		if (convertView == null) {
			final View row = inflater
					.inflate(R.layout.list_item, parent, false);
			// final int index = position;

			viewItem = new ViewItemProperty();
			viewItem.title = (TextView) row.findViewById(R.id.name);
			viewItem.number = (TextView) row.findViewById(R.id.number);

			viewItem.removeImgBtn = (ImageButton) row.findViewById(R.id.remove);
			viewItem.removeImgBtn.setFocusable(false);

			row.setTag(viewItem);
			convertView = row;
		}

		viewItem = (ViewItemProperty) convertView.getTag();
		PhoneNumberModel String = listValues.get(position);

		if (String != null) {
			viewItem.title.setTextSize(22);
			viewItem.title.setText(String.getTitleText());
			viewItem.title.setTextColor(Color.DKGRAY);

			viewItem.number.setTextSize(15);
			viewItem.number.setText(String.getNumberText());
			viewItem.number.setTextColor(Color.GRAY);
		}

		if (position % 2 == 1) {
			convertView.setBackgroundColor(Color.parseColor("#f2f2f2"));
		} else {
			convertView.setBackgroundColor(Color.parseColor("#dfdfdf"));
		}

		return convertView;
	}

}

class ViewItemProperty {
	TextView title;
	TextView number;

	ImageButton removeImgBtn;
}