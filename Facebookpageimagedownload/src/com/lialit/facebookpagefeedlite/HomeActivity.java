package com.lialit.facebookpagefeedlite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.lialit.facebookpagefeedlite.R;
import com.lilakhelait.kishor.helper.GetNetworkStatus;
import com.lilakhelait.kishor.helper.MyFile;
import com.lilakhelait.kishor.resource.Imageurl;
import com.peakcoders.backgroundTasks.ConstantValues;
import com.peakcoders.backgroundTasks.CustomeUrlProcessor;
import com.peakcoders.backgroundTasks.killerparser;



public class HomeActivity extends BaseActivity {
	public static final String PREFS_NAME = "MyPrefsFile";
	private String[] imageUrls;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_home);
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		  boolean silent = settings.getBoolean("silentMode", false);
	      
		
		setDeviceResulation();
	imageUrls = new String[Imageurl.newyearsvalues.size()];
		for(int i=0; i<Imageurl.newyearsvalues.size(); i++)
		{
			/*ModelWPCategory modelWPCategory = new ModelWPCategory();
			modelWPCategory = wpCategories.get(i);*/
		//	categoryNames[i]= Imageurl.newyearsvalues.get(i);
			imageUrls[i] = Imageurl.newyearsvalues.get(i);;
		}
	}

	public void load()
	{
		 Imageurl.newyearsvalues.clear();
         MyFile f = new MyFile(this);
       
         String s=	 f.readFromSD();
			String[] lines = s.split("\n");
			Log.d("size  ", lines.length+" ");
			for (String string : lines) {
				Imageurl.newyearsvalues.add(string);
			}
	}

	public void onImageGridClick(View view) {
		load();
		Intent intent = new Intent(this, CategoryGridActivity.class);
		intent.putExtra(Extra.IMAGES, imageUrls);
	
		startActivity(intent);
	}

	public void onImagePagerClick(View view) {
		load();
		Intent intent = new Intent(this, ImagePagerActivity.class);
		intent.putExtra(Extra.IMAGES, imageUrls);
		startActivity(intent);
	}
	public void	onLoadnewtroll(View view) {
		try{
		 if(GetNetworkStatus.isNetworkAvailable(this))
	        {
			 Intent i = new Intent(getApplicationContext(),killerparser.class);
			
			startActivityForResult(i,567);}
		 else
		 {
			 Toast.makeText(this, "Where is network . bro?", Toast.LENGTH_LONG).show();
		 }
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
	}
	
	public void setDeviceResulation()
	{
		Display d = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		ConstantValues.DEVICE_WIDTH = d.getWidth();
		ConstantValues.DEVICE_HEIGHT = d.getHeight();
	}
}