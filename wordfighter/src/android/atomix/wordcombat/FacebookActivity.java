package android.atomix.wordcombat;

import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.atomix.wordcombat.facebook.AsyncFacebookRunner;
import android.atomix.wordcombat.facebook.BaseRequestListener;
import android.atomix.wordcombat.facebook.DialogError;
import android.atomix.wordcombat.facebook.Facebook;
import android.atomix.wordcombat.facebook.Facebook.DialogListener;
import android.atomix.wordcombat.facebook.FacebookError;
import android.atomix.wordcombat.facebook.SessionStore;
import android.atomix.wordcombat.supports.ConstantValues;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.Toast;

public class FacebookActivity extends Activity {
	private Facebook mFacebook;
	private CheckBox mFacebookBtn;
	private ProgressDialog mProgress;

	private static final String[] PERMISSIONS = new String[] { "publish_stream", "read_stream", "offline_access" };
	
	private Handler mRunOnUi = new Handler();
	
	private static final String APP_ID = "603597719692780";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.facebook);

		mFacebookBtn = (CheckBox) findViewById(R.id.cb_facebook);

		mProgress = new ProgressDialog(this);
		mFacebook = new Facebook(APP_ID);

		SessionStore.restore(mFacebook, this);

		if (mFacebook.isSessionValid()) 
		{
			mFacebookBtn.setChecked(true);
			ConstantValues.isfacebookLogin = true;

			String name = SessionStore.getName(this);
			name = (name.equals("")) ? "Unknown" : name;

			mFacebookBtn.setText("  Facebook (" + name + ")");
			mFacebookBtn.setTextColor(Color.BLACK);
		}
		
		//onFacebookClick();
		
		mFacebookBtn.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				onFacebookClick();
			}
		});
	}

	private void onFacebookClick() 
	{
		if (mFacebook.isSessionValid()) 
		{
			final AlertDialog.Builder builder = new AlertDialog.Builder(this);
			
			builder.setMessage("Delete current Facebook connection?")
			       .setCancelable(false)
			       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			        	   fbLogout();
			           }
			       })
			       .setNegativeButton("No", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			                dialog.cancel();
			                
			                mFacebookBtn.setChecked(true);
			           }
			       });
			
			final AlertDialog alert = builder.create();
			
			alert.show();
		} 
		else 
		{
			mFacebook.authorize(this, PERMISSIONS, -1, new FbLoginDialogListener());
		}
	}
	
	private void fbLogout() {
		mProgress.setMessage("Disconnecting from Facebook");
		mProgress.show();
			
		new Thread() {
			@Override
			public void run() {
				SessionStore.clear(FacebookActivity.this);
		        	   
				int what = 1;
					
		        try {
		        	mFacebook.logout(FacebookActivity.this);
		        		 
		        	what = 0;
		        } catch (Exception ex) {
		        	ex.printStackTrace();
		        }
		        	
		        mHandler.sendMessage(mHandler.obtainMessage(what));
			}
		}.start();
	}

	private final class FbLoginDialogListener implements DialogListener 
	{
		public void onComplete(Bundle values) 
		{
			SessionStore.save(mFacebook, FacebookActivity.this);

			mFacebookBtn.setText("  Facebook (No Name)");
			mFacebookBtn.setChecked(true);
			mFacebookBtn.setTextColor(Color.BLACK);

			getFbName();
		}

		public void onFacebookError(FacebookError error) 
		{
			Toast.makeText(FacebookActivity.this, "Facebook connection failed", Toast.LENGTH_SHORT).show();

			mFacebookBtn.setChecked(false);
		}

		public void onError(DialogError error) 
		{
			Toast.makeText(FacebookActivity.this, "Facebook connection failed", Toast.LENGTH_SHORT).show();

			mFacebookBtn.setChecked(false);
		}

		public void onCancel() 
		{
			mFacebookBtn.setChecked(false);
		}
	}

	private void getFbName() 
	{
		mProgress.setMessage("Finalizing ...");
		mProgress.show();

		new Thread() 
		{
			@Override
			public void run() 
			{
				String name = "";
				int what = 1;

				try 
				{
					String me = mFacebook.request("me");

					JSONObject jsonObj = (JSONObject) new JSONTokener(me).nextValue();
					name = jsonObj.getString("name");
					what = 0;
				} 
				catch (Exception ex) 
				{
					ex.printStackTrace();
				}

				mFbHandler.sendMessage(mFbHandler.obtainMessage(what, name));
			}
		}.start();
	}

	private Handler mFbHandler = new Handler() 
	{
		@Override
		public void handleMessage(Message msg) 
		{
			mProgress.dismiss();

			if (msg.what == 0) {
				String username = (String) msg.obj;
				username = (username.equals("")) ? "No Name" : username;

				SessionStore.saveName(username, FacebookActivity.this);

				mFacebookBtn.setText(username);
				
				Toast.makeText(FacebookActivity.this, "Connected to Facebook as " + username, Toast.LENGTH_SHORT).show();
			} 
			else 
			{
				Toast.makeText(FacebookActivity.this, "Connected to Facebook", Toast.LENGTH_SHORT).show();
			}
		}
	};

	private Handler mHandler = new Handler() 
	{
		@Override
		public void handleMessage(Message msg) 
		{
			mProgress.dismiss();

			if (msg.what == 1) 
			{
				Toast.makeText(FacebookActivity.this, "Facebook logout failed", Toast.LENGTH_SHORT).show();
			} 
			else 
			{
				//mFacebookBtn.setChecked(false);
				mFacebookBtn.setText("  Facebook (Not connected)");
				mFacebookBtn.setTextColor(Color.GRAY);

				Toast.makeText(FacebookActivity.this, "Disconnected from Facebook", Toast.LENGTH_SHORT).show();
			}
		}
	};
	
	
	public void postToFacebook(String review) 
	{	
		mProgress.setMessage("Posting ...");
		mProgress.show();
		
		AsyncFacebookRunner mAsyncFbRunner = new AsyncFacebookRunner(mFacebook);
		
		Bundle params = new Bundle();
    		
		params.putString("message", review);
		
		mAsyncFbRunner.request("me/feed", params, "POST", new WallPostListener());
	}
	
	public final class WallPostListener extends BaseRequestListener 
	{
        public void onComplete(final String response) 
        {
        	mRunOnUi.post(new Runnable() 
        	{
        		public void run() 
        		{
        			mProgress.cancel();
        			
        			Toast.makeText(FacebookActivity.this, "Posted to Facebook", Toast.LENGTH_SHORT).show();
        			finish();
        		}
        	});
        }
    }
}
