package info.androidhive.slidingmenu;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.NameValuePair;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.atomix.kurowiz.supports.ConstantValues;
import com.atomix.kurowiz.supports.InternetConnectivity;
import com.atomix.kurowiz.supports.MyScrollView;
import com.atomix.kurowiz.supports.SingleToneClass;
import com.atomix.kurowiz.xmlparser.APIFactory;
import com.atomix.kurowiz.xmlparser.DomParser;
import com.atomix.kurowiz.xmlparser.ImageAdapter;
import com.model.Instancevalues;
import com.siliconorchard.com.ecoupon.cards.free.ListActivity;
import com.siliconorchard.com.ecoupon.cards.free.R;

public class SearchOfferFragment extends Fragment {
	private static ProgressDialog progressDialog;
	// private APIFactory apiFactory;
	public static Context c = null;
	private static LinearLayout linearLayout;
	static public String gifttype = "";
	static public int giftid = 0;
	private static MyScrollView scrollViewExchange;
	private static int pageId = 1;
	private static boolean isMore = false;
	static public ListView gridView = null;
	static APIFactory apifactory;
	static public ImageAdapter imgada = null;

	
	public SearchOfferFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_find_people,
				container, false);
		gridView = (ListView) rootView.findViewById(R.id.gridView1);

		apifactory = new APIFactory();
		new APITask().execute();

		return rootView;
	}

	private static class APITask extends AsyncTask<Void, Void, String> {
		private String RESULT = "OK";

		@Override
		protected void onPreExecute() {
			Log.d("f", "preexecute");
			progressDialog = ProgressDialog.show(getActivity(), "",
					"Loading...", true, false);
			ConstantValues.isBottomReached = false;
		}

		private Context getActivity() {
			// TODO Auto-generated method stub

			return ListActivity.c;
		}

		@Override
		protected String doInBackground(Void... arg0) {
			try {
				if (InternetConnectivity.isConnectedToInternet(getActivity())) {
					InputStream xml;
					DomParser domParser = new DomParser();
					Instancevalues.VendorOfferlist.clear();
					ArrayList<NameValuePair> getcategory = apifactory
							.getsearchlist(
									Instancevalues.currentuser.getUserID(), 1);
					xml = SingleToneClass.getInstance().getResponseFromServer(
							getcategory);
					domParser.parsegetofferlist(xml, isMore);

					RESULT = Instancevalues.Error_message;

					return RESULT;
				} else {
					SingleToneClass.getInstance().openInternetSettingsActivity(
							getActivity());
					return RESULT;
				}
			} catch (Exception ex) {
				return RESULT;
			}
		}

		@Override
		protected void onPostExecute(String result) {
			if (progressDialog.isShowing()) {
				progressDialog.dismiss();
				progressDialog = null;
				isMore = false;
				ConstantValues.isBottomReached = true;
			}

			/*
			 * linearLayout.removeAllViews(); LayoutInflater inflater =
			 * LayoutInflater.from(getActivity());
			 */
			Log.d("vendor", "2 " + Instancevalues.VendorOfferlist.size() + " ");
			if (Instancevalues.VendorOfferlist != null) {
				Log.d("sdf", "2 " + Instancevalues.VendorOfferlist.size() + " ");
				ConstantValues.isBottomReached = true;
				imgada = new ImageAdapter(ListActivity.c,
						Instancevalues.VendorOfferlist);
				gridView.setAdapter(imgada);
				imgada.notifyDataSetChanged();

			}

			else {
				
				ConstantValues.isBottomReached = false;

			}

		}
	}
}
