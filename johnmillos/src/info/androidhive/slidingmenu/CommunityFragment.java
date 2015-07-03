package info.androidhive.slidingmenu;



import com.atomix.kurowiz.supports.MyScrollView;
import com.atomix.kurowiz.xmlparser.FavouriteListAdapter;
import com.atomix.kurowiz.xmlparser.HistoryListAdapter;
import com.siliconorchard.com.ecoupon.cards.free.R;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

public class CommunityFragment extends Fragment {
	
	public CommunityFragment(){}
	private static ProgressDialog progressDialog;
//	private APIFactory apiFactory;
	public static Context c=null;
	private static LinearLayout linearLayout;
	static public String gifttype="";
	static public int giftid=0;
	private static MyScrollView scrollViewExchange;
	private static int pageId = 1;
	private static boolean isMore = false;
    static public GridView gridView=null;
    static public FavouriteListAdapter imgada = null;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_community, container, false);
        gridView = (GridView) rootView.findViewById(R.id.gridView1);
        
      /*  MainActivity.favourite.clear();
        MainActivity.favourite.addAll(DBHelper.getfavouriteset());
        imgada = new FavouriteListAdapter(MainActivity.c,  MainActivity.) ;
        Toast.makeText(MainActivity.c, ""+MainActivity.history.size(),Toast.LENGTH_LONG).show();
		gridView.setAdapter(imgada);
   */   
 return rootView;
    }
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		imgada.notifyDataSetChanged();
		super.onStart();
	}
}
