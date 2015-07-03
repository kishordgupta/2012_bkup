package com.siliconorchard.com.ecoupon.cards.free;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.NameValuePair;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.atomix.kurowiz.supports.ConstantValues;
import com.atomix.kurowiz.supports.InternetConnectivity;
import com.atomix.kurowiz.supports.SingleToneClass;
import com.atomix.kurowiz.xmlparser.APIFactory;
import com.atomix.kurowiz.xmlparser.DomParser;
import com.model.Instancevalues;
import com.model.Vendor;
import com.siliconorchard.com.ecoupon.cards.free.R.id;
import com.siliconorchard.com.ecoupon.cards.free.util.SystemUiHider;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class SignupActivity extends Activity implements OnClickListener {


	private static ProgressDialog progressDialog;
	private static EditText firstname = null;
	private static EditText pass =null;
	private static EditText lastname = null;
	private static EditText email =null;
	private static DatePicker date = null;
	private static CheckBox rem = null;
	private static Button submit = null;
	private static APIFactory apiFactory;
	public static Context c =null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*requestWindowFeature(Window.FEATURE_ACTION_BAR);
	       getWindow().setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND,
	                WindowManager.LayoutParams.FLAG_DIM_BEHIND);
	        LayoutParams params =getWindow().getAttributes(); 
	       Point p = new Point();
	       getWindow().getWindowManager().getDefaultDisplay().getSize(p);
	       params.height = 380;
			params.width = 340; // fixed width//fixed width
	        params.alpha = 1.0f;
	        params.dimAmount = 0.5f;
	     getWindow().setAttributes((android.view.WindowManager.LayoutParams) params); */
			  // setContentView(R.layout.map_activity);
		       getActionBar().setTitle("Sign UP");
		       getActionBar().show();
		       getActionBar().setHomeButtonEnabled(true);
		setContentView(R.layout.activity_signup);

	
          firstname =(EditText)findViewById(R.id.firstname);
          lastname=(EditText)findViewById(R.id.lastname);
          email=(EditText)findViewById(R.id.email);
          pass=(EditText)findViewById(R.id.pass);
          date = (DatePicker)findViewById(R.id.bday);
          submit= (Button)findViewById(R.id.submit);
          
          submit.setOnClickListener(this);
          apiFactory = new APIFactory();
c=this;
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.signup, menu);
		return true;
	}
@Override
public void onBackPressed() {
	// TODO Auto-generated method stub
finish();
	super.onBackPressed();
}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            // app icon in action bar clicked; go home
	            finish();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.submit:
			if(email.getText().toString()==""||pass.getText().toString()=="")
			{Toast.makeText(this, "fill the data please", Toast.LENGTH_LONG).show();}
			else{
			new APITask().execute();
			}
			break;

		default:
			break;
		}
	}
	private static class APITask extends AsyncTask<Void, Void, String> {
		private String RESULT = "Login";

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

					ArrayList<NameValuePair> getcategory = apiFactory.userregistration(email.getText().toString(), pass.getText().toString(), firstname.getText().toString(), lastname.getText().toString(), date.getYear()+"", (date.getMonth()+1)+"", "on");//(name.getText().toString(), pass.getText().toString());
					xml = SingleToneClass.getInstance().getResponseFromServer(
							getcategory);
					domParser.parseuseregistratopn(xml);

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
				if(Instancevalues.isErrormessage)
				{
					
					Toast.makeText(getActivity(), "Same email adress  "+ result, Toast.LENGTH_LONG).show();
				
				}
				else
				{   Instancevalues.user=true;
					Toast.makeText(getActivity(), "Registration  Successful ", Toast.LENGTH_LONG).show();
				  ((Activity) c).finish();
				    
				}
				ConstantValues.isBottomReached = true;
			}

		}
	}

}
