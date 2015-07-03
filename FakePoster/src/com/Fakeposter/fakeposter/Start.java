package com.Fakeposter.fakeposter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Start extends Activity {

	
	Button choosePic,submit,takePhoto;
	drawView draw,draw1;
	EditText  actor1,actor2,move1,move2,director;
	Editor store;
	File allfile[];
	gallaryscan gl;
	ImageView  view;
	private String mCameraFileName;
	String selectedImagePath;
	SharedPreferences pref;
	 Uri outuri;
	
	
	
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.start);
			final String folder=Environment.getExternalStorageDirectory().getPath();
	//		allfile=folder.listFiles();
				
			actor1=(EditText) findViewById(R.id.EditText03);
			actor2=(EditText) findViewById(R.id.EditText01);
			move1=(EditText) findViewById(R.id.EditText02);
			move2=(EditText) findViewById(R.id.editText1);
			director=(EditText) findViewById(R.id.editText2);
			choosePic=((Button) findViewById(R.id.button1));
			submit=(Button)findViewById(R.id.button2);
			takePhoto=(Button) findViewById(R.id.button3);
			view=(ImageView) findViewById(R.id.imageView1);
				
			pref = this.getApplicationContext().getSharedPreferences("any_prefname", MODE_WORLD_READABLE);
			
			
			
			takePhoto.setOnClickListener(new OnClickListener() {
				
				

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
					
////				   
				    
					startActivityForResult(i, 2);
				}
			});
			
			
			
				choosePic.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub;
								
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
						
						if(move1.getText().length()!=0&&actor1.getText().length()!=0&&director.getText().length()!=0&&view.getDrawable()!=null){
							android.util.Log.d("Text ",""+actor1.getText().toString()+"      "+director.getText().toString()+"");
							
							// store 
							
							store=pref.edit();
							store.putString("Photo", selectedImagePath);
							android.util.Log.d("Text ",""+actor2.getText().toString()+"      "+director.getText().toString()+"");
							
							
							
							store.putString("Actor1", actor1.getText().toString());
							store.putString("Actor2", actor2.getText().toString());
							store.putString("Movie1", move1.getText().toString());
							store.putString("Movie2", move2.getText().toString());
							store.putString("Director", director.getText().toString());
							store.commit();
							
							Intent i=new Intent(Start.this,pickPhoto.class);
//							
//							i.putExtra("Actor",actor.toString() );
//							i.putExtra("Director",director.toString() );
//							i.putExtra("Imagepath",selectedImagePath );
							startActivity(i);	
							
							
						}
						
					}
				});
						
						
						
						
		   
			
		}
		
		
		
		
		
		Uri uri = null;

		@Override
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
		
//			 Toast.makeText(getApplicationContext(), "Activity 1 returned OK", Toast.LENGTH_LONG).show();
			
//			switch(requestCode){
//		        case 1:
//		           if(resultCode == RESULT_OK)
//		              Toast.makeText(getApplicationContext(), "Activity 1 returned OK", Toast.LENGTH_LONG).show();
//		           break;
//		        case 3:
//		           if(resultCode == RESULT_OK)
//		              Toast.makeText(getApplicationContext(), "Activity 2 returned OK", Toast.LENGTH_LONG).show();
//		           break;}
			if (resultCode == RESULT_OK) {
	            if (requestCode == 1) {
	                Uri selectedImageUri = data.getData();
	                selectedImagePath = getPath(selectedImageUri);
	                System.out.println("Image Path : " + selectedImagePath);
	                //view.setImageURI(selectedImageUri);
	                Bitmap bmp=BitmapFactory.decodeFile(selectedImagePath, null);
	                view.setImageBitmap(bmp);
	            }
			}
////	            android.util.Log.d("Text ",""+actor1.getText().toString()+"      "+director.getText().toString()+"");
//	           
//	            
//	           
//	        }
			  android.util.Log.d("Text "," "+00+"");
			
	               // android.util.Log.d("Text "," "+mCameraFileName.toString()+"");
	        
	            
	                
	            
//			  android.util.Log.d("Text "," "+outuri.toString()+"");
			
			if(requestCode==2&&resultCode==RESULT_OK){
//				
    			Bundle but=data.getExtras();
    			Bitmap bmp1=(Bitmap) but.get("data");
    			String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
    		    OutputStream outStream = null;
    		    File file = new File(extStorageDirectory, "er.PNG");
    		    try {
    		     outStream = new FileOutputStream(file);
    		     bmp1.compress(Bitmap.CompressFormat.PNG, 100, outStream);
    		     selectedImagePath=file.getPath();
    		     outStream.flush();
    		     outStream.close();
    		    }
    		    catch(Exception e)
    		    {}
    			
                view.setImageBitmap(bmp1);
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
