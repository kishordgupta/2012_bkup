package com.kd.mobilemediacms.list;

import java.util.ArrayList;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import com.kd.mobilemediacms.R;
import com.kd.mobilemediacms.PricelistActivity;
import com.kd.model.Catagory;

public class ListCatagory extends ArrayAdapter<Catagory> implements
		OnItemClickListener {
	private final Context context;
	public static ArrayList<Catagory> values = new ArrayList<Catagory>();

	public ListCatagory(Context context) {
		super(context, R.layout.catagorylist, values);
		this.context = context;
		// ListCatalogue.values = Listofdb.Cataloguelist;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final int positionplayer = position;
		ViewHolderaway holder;
		if (convertView != null) {
			holder = new ViewHolderaway();

			return convertView;
		} else {
			View row = inflater.inflate(R.layout.catagorylist, parent, false);

			holder = new ViewHolderaway();

			holder.CatalogueType = (TextView) row
					.findViewById(R.id.CatalogueType);

			holder.CatalogueType.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					context.startActivity(new Intent(context,
							PricelistActivity.class));
				}
			});

			row.setTag(holder);

			holder = (ViewHolderaway) row.getTag();
			Catagory Cataloguedata = values.get(positionplayer);
			if (Cataloguedata != null) {
				holder.CatalogueType.setText(Cataloguedata.getName());

			}
			return row;
		}
	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			context.startActivity(new Intent(context, PricelistActivity.class));
		}
	};

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Catagory cat = values.get(arg2);
		context.startActivity(new Intent(context, PricelistActivity.class));
	}

}

class ViewHolderaway {

	TextView CatalogueType;

}