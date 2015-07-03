package com.latenightnews.bd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.ironsource.mobilcore.CallbackResponse;
import com.ironsource.mobilcore.MobileCore;
import com.ironsource.mobilcore.CallbackResponse.TYPE;
import com.ironsource.mobilcore.MobileCore.LOG_TYPE;
import com.latenightnews.bd.R;
import com.lilakhelait.kishor.helper.GetNetworkStatus;
import com.lilakhelait.kishor.helper.MyFile;
import com.lilakhelait.kishor.resource.Imageurl;
import com.peakcoders.backgroundTasks.Adderparser;
import com.peakcoders.backgroundTasks.ConstantValues;
import com.peakcoders.backgroundTasks.CustomeUrlProcessor;
import com.web.videos.StackParser;




public class HomeActivity extends BaseActivity {
	public static final String PREFS_NAME = "MyPrefsFile";
	private String[] imageUrls;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_home);
		   MobileCore.init(this,"8YKF89U9YHXZUIMHFEQ0UDC1T9TOY", LOG_TYPE.DEBUG);
			 MobileCore.getSlider().setContentViewWithSlider(this, R.layout.ac_home);
		if(GetNetworkStatus.isNetworkAvailable(this))
		{
		startActivityForResult(new Intent(this, Adderparser.class),111);
		}
		else
		{
			 Toast.makeText(this, "Where is network . bro?", Toast.LENGTH_LONG).show();
			finish();
		}
		
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
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
	final Context c=this;
		MobileCore.showOfferWall((Activity) c, new CallbackResponse() {
			@Override
			public void onConfirmation(TYPE arg0) {
				((Activity) c).finish();
			}
			});
		//super.onBackPressed();
	}

 @Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO Auto-generated method stub
	super.onActivityResult(requestCode, resultCode, data);
	if(requestCode==111)
	{
		if(Imageurl.newyearsvalues.size()<1)
		{
			 Toast.makeText(this, "Server down  , try after 5 minutes , sorry  . bro?", Toast.LENGTH_LONG).show();
			finish();
		}
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
		//load();
		Intent intent = new Intent(this, CategoryGridActivity.class);
		intent.putExtra(Extra.IMAGES, imageUrls);
	
		startActivity(intent);
	}
	
	public void onChat(View view) {
		//load();
		Intent intent = new Intent(this, com.lilait.chat.MainActivity.class);
		intent.putExtra(Extra.IMAGES, imageUrls);
	
		startActivity(intent);
	}
	public void onImageListClick(View view) {
		Intent intent = new Intent(this, CategoryListActivity.class);
		intent.putExtra(Extra.IMAGES, imageUrls);
		startActivity(intent);
	}
	public void onImagePagerClick(View view) {
		//load();
		Intent intent = new Intent(this, ImagePagerActivity.class);
		intent.putExtra(Extra.IMAGES, imageUrls);
		startActivity(intent);
	}
	public void	onLoadnewtroll(View view) {
		try{
		 if(GetNetworkStatus.isNetworkAvailable(this))
	        {startActivityForResult(new Intent(this, Adderparser.class),111);}
		 else
		 {
			 Toast.makeText(this, "Where is network . bro?", Toast.LENGTH_LONG).show();
		 }
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	

	
	public void setDeviceResulation()
	{
		Display d = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		ConstantValues.DEVICE_WIDTH = d.getWidth();
		ConstantValues.DEVICE_HEIGHT = d.getHeight();
	}
}