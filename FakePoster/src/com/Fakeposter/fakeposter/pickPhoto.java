package com.Fakeposter.fakeposter;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class pickPhoto extends Activity {
		
		Button pickPoster,submit;
		Editor store;
		ImageView posterView;
		String folder,selectedImagePath;
		SharedPreferences pref; 
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.poster);
		
		pickPoster=(Button) findViewById(R.id.button1);
		submit=(Button) findViewById(R.id.button2);
		posterView=(ImageView)findViewById(R.id.imageView1);
		pref = this.getApplicationContext().getSharedPreferences("any_prefname", MODE_WORLD_READABLE);
		
		
		folder=Environment.getExternalStorageDirectory().getPath();
		
		pickPoster.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent=new Intent();
				
				intent.setAction(android.content.Intent.ACTION_GET_CONTENT);
		        intent.setDataAndType((Uri.parse(folder)),"image/*");
                startActivityForResult(Intent.createChooser(intent,"Choose Your Image" ),1);
				
				
				
				
			}
		});
		
		
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(posterView.getDrawable()!=null){
					;
					Intent i=new Intent(pickPhoto.this,poster.class);
					//store path
					store=pref.edit();
					store.putString("Poster", selectedImagePath);
					
					store.commit();
					
					
					
					
					
//					i.putExtra("Actor",actor.toString() );
//					i.putExtra("Director",director.toString() );
//					i.putExtra("Imagepath",selectedImagePath );
					startActivity(i);	
					
					
				}
				
			}
			
		});
		
		
		
	}
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
//		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                System.out.println("Image Path : " + selectedImagePath);
                //view.setImageURI(selectedImageUri);
                posterView.setImageBitmap(null);
                Bitmap bmp=BitmapFactory.decodeFile(selectedImagePath, null);
                posterView.setImageBitmap(bmp);
            }
        }

	}
	
	public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
	
}
