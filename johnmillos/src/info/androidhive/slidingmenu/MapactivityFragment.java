package info.androidhive.slidingmenu;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.maps.MapView;
import com.model.Instancevalues;
import com.model.Vendor;
import com.siliconorchard.com.ecoupon.cards.free.ListActivity;
import com.siliconorchard.com.ecoupon.cards.free.R;

public class MapactivityFragment extends FragmentActivity implements OnMarkerClickListener{
	protected boolean isRouteDisplayed() {
		return false;
	}

	private MapView myMap;
	private Button btnSearch;
	public static Context c = null;
	private Geocoder gc;
	private double lat;
	private double lon;
	private static ProgressDialog pd;
	private List<Address> foundAdresses;
	private Thread searchAdress;
	Routing routing = null;
	GoogleMap map;
	protected LatLng start;
	protected LatLng end;
	private static ProgressDialog progressDialog;
  @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Auto-generated method stub
		c=this;
		setContentView(R.layout.dismap);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map1))
				.getMap();
	/*	map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map1))
				.getMap();*/

		map.setMyLocationEnabled(true);
	
		ArrayList<Marker> marker = new ArrayList<Marker>();
		for (Vendor v : Instancevalues.VendorDistancelist) {
			LatLng sydney = new LatLng(Double.parseDouble(v.getLat()),
					Double.parseDouble(v.getLon()));
		
			/*marker.add(*/map.addMarker(new MarkerOptions().title(v.getVendorname())
			.snippet(v.getVendoradress()+"\n " +v.getVendorphone()).position(sydney)).showInfoWindow()/*)*/;
			
			Log.d("marker", v.getVendorname() + " " + Double.parseDouble(v.getLat()) + " " + Double.parseDouble(v.getLon()));
		}
		LatLng sydney = new LatLng(Instancevalues.userLat,
				Instancevalues.userlon);
		getActionBar().setHomeButtonEnabled(true);
		
/*
		marker.add(map.addMarker(new MarkerOptions().title("Me")
				.snippet("Range").position(sydney)));*/
		
	
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
//	 map.OnMarkerClickListener(this);
	 
		  map.setInfoWindowAdapter(new InfoWindowAdapter() {

			@Override
			public View getInfoContents(Marker arg0) {
				// TODO Auto-generated method stub
				return null;
			}
        
			
			@Override
			public View getInfoWindow(Marker arg0) {
				// TODO Auto-generated method stub
				final String arg = arg0.getTitle();

            	Vendor va=null;
				for (Vendor v : Instancevalues.VendorDistancelist) {
				if(arg.contains(v.getVendorname()))
				{Toast.makeText(c, v.getVendorname(), Toast.LENGTH_LONG).show();
					va=v;
					break;
				}
				
				}
		if(va!=null){
		try{
			Toast.makeText(c, va.getVendorname(), Toast.LENGTH_LONG).show();
			Instancevalues.currentVendor=va;
			Instancevalues.Lat=va.getLat()+"";
			Instancevalues.lon=va.getLon()+"";
			Instancevalues.currentvendor=va.getVendorname()+"";
			//finish();
		
	       //     finish();
			
        	}
        	catch(Exception e)
        	{
        		Toast.makeText(ListActivity.c, "Server not found", Toast.LENGTH_LONG).show();
        	}
	} 
				 map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {          
	                    public void onInfoWindowClick(Marker marker) 
	                    {finish();}  });
				return null;
			}

			
	            // Use default InfoWindow frame
	         

	            // Defines the contents of the InfoWindow
	     
	             });  
		  
				
	}
  
 
/*	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_ACTION_BAR);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND,
				WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		LayoutParams params = getWindow().getAttributes();
		Point p = new Point();
		getWindow().getWindowManager().getDefaultDisplay().getSize(p);
		params.height = 380;
		params.width = 340; // fixed width
		params.alpha = 1.0f;
		params.dimAmount = 0.5f;
		getWindow().setAttributes(
				(android.view.WindowManager.LayoutParams) params);
		// setContentView(R.layout.map_activity);
		getActionBar().setTitle(Instancevalues.currentvendor);
		getActionBar().show();
		getActionBar().setHomeButtonEnabled(true);
		// Get a handle to the Map Fragment
	//	setContentView(R.layout.map);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();

		LatLng sydney = new LatLng(Double.parseDouble(Instancevalues.Lat),
				Double.parseDouble(Instancevalues.lon));

		map.setMyLocationEnabled(true);
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

		map.addMarker(new MarkerOptions().title(Instancevalues.currentvendor)
				.snippet(Instancevalues.Currentadress).position(sydney));
		c = this;
		start = new LatLng(Instancevalues.userLat, Instancevalues.userlon);
		end = new LatLng(Double.parseDouble(Instancevalues.Lat),
				Double.parseDouble(Instancevalues.lon));
		routing = new Routing(Routing.TravelMode.DRIVING);
		routing.registerListener(this);
		
		 * myMap = (MapView) findViewById(R.id.map); // Get map from XML
		 * navigateToLocation(10.0,10.0,myMap);
		 

	}*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_menu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// app icon in action bar clicked; go home
			finish();
			return true;
		case R.id.close:
			// app icon in action bar clicked; go home
			finish();
			return true;
		
		default:
			return super.onOptionsItemSelected(item);
		}
	}
/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// app icon in action bar clicked; go home
			finish();
			return true;
		case R.id.close:
			// app icon in action bar clicked; go home
			finish();
			return true;
		case R.id.distance:
			// app icon in action bar clicked; go home
           try{
			routing.execute(start, end);
           }
           catch(Exception e)
           {}
			return true;
		case R.id.gmap:
			// app icon in action bar clicked; go home
			String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?&daddr=%f,%f (%s)", Double.parseDouble(Instancevalues.Lat), Double.parseDouble(Instancevalues.lon), Instancevalues.currentvendor);
	        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
	        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
	        try
	        {
	            startActivity(intent);
	        }
	        catch(ActivityNotFoundException ex)
	        {
	            try
	            {
	                Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
	                startActivity(unrestrictedIntent);
	            }
	            catch(ActivityNotFoundException innerEx)
	            {
	                Toast.makeText(this, "Please install a maps application", Toast.LENGTH_LONG).show();
	            }
	        }
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		// searchAdress.start();
		super.onResume();
	}

	*//**
	 * Navigates a given MapView to the specified Longitude and Latitude
	 * 
	 * @param latitude
	 * @param longitude
	 * @param mv
	 *//*

	*//**
	 * Navigates a given MapView to the specified Longitude and Latitude
	 * 
	 * @param latitude
	 * @param longitude
	 * @param mv
	 *//*

	@Override
	public void onRoutingFailure() {
		// TODO Auto-generated method stub
		Toast.makeText(c, "Distance Direction fail", Toast.LENGTH_LONG).show();// .
	}

	@Override
	public void onRoutingStart() {
		// TODO Auto-generated method stub
		Toast.makeText(c, "Distance Direction searching", Toast.LENGTH_LONG)
				.show();// .
	}

	@Override
	public void onRoutingSuccess(PolylineOptions mPolyOptions) {
		// TODO Auto-generated method stub
		PolylineOptions polyoptions = new PolylineOptions();
		polyoptions.color(Color.BLUE);
		polyoptions.width(10);
		polyoptions.addAll(mPolyOptions.getPoints());
		map.addPolyline(polyoptions);

		// Start marker
		MarkerOptions options = new MarkerOptions();
		options.position(start);
		options.icon(BitmapDescriptorFactory
				.fromResource(android.R.drawable.ic_menu_mapmode));
		map.addMarker(options);

		// End marker
		options = new MarkerOptions();
		options.position(end);
		options.icon(BitmapDescriptorFactory
				.fromResource(android.R.drawable.ic_menu_directions));
		map.addMarker(options);
	}
}

class HelloItemizedOverlay extends ItemizedOverlay<OverlayItem> {
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context mContext;

	public HelloItemizedOverlay(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
		mContext = context;
	}

	public void addOverlay(OverlayItem overlay) {
		mOverlays.add(overlay);
		populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}

	@Override
	protected boolean onTap(int index) {
		OverlayItem item = mOverlays.get(index);
		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		dialog.setTitle(item.getTitle());
		dialog.setMessage(item.getSnippet());
		dialog.show();
		return true;
	}
*/


	@Override
	public boolean onMarkerClick(Marker arg0) {
		// TODO Auto-generated method stub
		/*Toast.makeText(c, "markerclicked", Toast.LENGTH_LONG).show();
		Vendor va=null;
	for (Vendor v : Instancevalues.VendorDistancelist) {
	if(arg0.getTitle().contains(v.getVendorname()))
	{Toast.makeText(c, "markerfound", Toast.LENGTH_LONG).show();
		va=v;
		break;
	}
	
	}
	
		if(va!=null){
		try{
			Toast.makeText(c, "markerfoundendsefcdsfsd", Toast.LENGTH_LONG).show();
			Instancevalues.currentVendor=va;
			Instancevalues.Lat=va.getLat()+"";
			Instancevalues.lon=va.getLon()+"";
			Instancevalues.currentvendor=va.getVendorname()+"";
			//finish();
			Fragment f;
	            f = new PhotosFragment();
	            FragmentTransaction ft = ((Activity) ListActivity.c).getFragmentManager().beginTransaction();
	            ft.replace(R.id.container, f);
	            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
	            ft.addToBackStack(null);
	            ft.commit();
	            finish();
			
        	}
        	catch(Exception e)
        	{
        		Toast.makeText(ListActivity.c, "Server not found", Toast.LENGTH_LONG).show();
        	}
	}*/
		return false;
	}
}