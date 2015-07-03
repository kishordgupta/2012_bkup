package com.cosplay.costumeshow.fantasydress;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lilakhelait.kishor.resource.Imageurl;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;



public class CategoryListActivity extends BaseActivity {

	private String[] imageUrls;
	private String [] categoryNames;

	private DisplayImageOptions options;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_image_list);

		Bundle bundle = getIntent().getExtras();
		//--imageUrls = bundle.getStringArray(Extra.IMAGES);
		//==
		imageUrls = new String[Imageurl.newyearsvalues.size()];
		for(int i=0; i<Imageurl.newyearsvalues.size(); i++)
		{
			/*ModelWPCategory modelWPCategory = new ModelWPCategory();
			modelWPCategory = wpCategories.get(i);*/
		//	categoryNames[i]= Imageurl.newyearsvalues.get(i);
			imageUrls[i] = Imageurl.newyearsvalues.get(i);;
		}
		//--

		options = new DisplayImageOptions.Builder()
			.showStubImage(R.drawable.stub_image)
			.cacheInMemory()
			.cacheOnDisc()
			.displayer(new RoundedBitmapDisplayer(30))
			.build();

		ListView listView = (ListView) findViewById(android.R.id.list);
		listView.setAdapter(new ItemAdapter());
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				startImageGalleryActivity(position);
			}
		});
	}

	@Override
	protected void onStop() {
		imageLoader.stop();
		super.onStop();
	}

	private void startImageGalleryActivity(int position) {
		Intent intent = new Intent(this, ImagePagerActivity.class);
		intent.putExtra(Extra.IMAGES, imageUrls);
		intent.putExtra(Extra.IMAGE_POSITION, position);
		startActivity(intent);
	}

	class ItemAdapter extends BaseAdapter {

		private class ViewHolder {
			public TextView text;
			public ImageView image;
		}

		@Override
		public int getCount() {
			return imageUrls.length;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			View view = convertView;
			final ViewHolder holder;
			if (convertView == null) {
				view = getLayoutInflater().inflate(R.layout.item_list_image, null);
				holder = new ViewHolder();
				holder.text = (TextView) view.findViewById(R.id.text_item_list);
				holder.image = (ImageView) view.findViewById(R.id.image_item_list);
				view.setTag(holder);
			} else
				holder = (ViewHolder) view.getTag();

			holder.text.setText("Cosplay"+position+" from www.cosplay.com");

			imageLoader.displayImage(imageUrls[position], holder.image, options);

			return view;
		}
	}
}