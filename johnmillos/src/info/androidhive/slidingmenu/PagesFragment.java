package info.androidhive.slidingmenu;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.atomix.kurowiz.xmlparser.ListAdapter;
import com.model.Instancevalues;
import com.siliconorchard.com.ecoupon.cards.free.ListActivity;
import com.siliconorchard.com.ecoupon.cards.free.R;

public class PagesFragment extends Fragment {

	public PagesFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_whats_hot,
				container, false);
		ListView gridView = (ListView) rootView.findViewById(R.id.gridView1);

		if (Instancevalues.featurevendorlist != null) {
			ListAdapter imgada = new ListAdapter(ListActivity.c,
					Instancevalues.featurevendorlist);

			gridView.setAdapter(imgada);
		}

		return rootView;
	}

}
