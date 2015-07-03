package info.androidhive.slidingmenu;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import com.atomix.kurowiz.xmlparser.ListAdapter;
import com.model.Instancevalues;
import com.siliconorchard.com.ecoupon.cards.free.ListActivity;
import com.siliconorchard.com.ecoupon.cards.free.R;

public class SearchTextFragment extends Fragment {

	public SearchTextFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.pagesfragment,
				container, false);
	 final EditText searchView = (EditText) rootView.findViewById(R.id.name);
		
	 Button bu=(Button) rootView.findViewById(R.id.submit);

	 bu.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(searchView.getText().toString()!="")
				// TODO Auto-generated method stub
				{	Instancevalues.searchtext = searchView.getText().toString();
				
				Instancevalues.VendorOfferlist.clear();
				Fragment f;
				f = new SearchOfferFragment();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.container, f);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.addToBackStack(null);
				ft.commit();
				}
		}
	}); 


		return rootView;
	}

}
