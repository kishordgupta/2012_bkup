package com.lilakhelait.kishor.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.lilakhelait.kishor.resource.Happynewyearresource;
import com.lilakhelait.kishor.resource.Imageurl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class MyFile {

	String TAG = "MyFile";
	Context context;
	public MyFile(Context context){
		this.context = context;
	}
	
	public Boolean writeToSD(String text){
		Boolean write_successful = false;
		 File root=null;  
	        try {  
	            // check for SDcard   
	            root = Environment.getExternalStorageDirectory();  
	            Log.i(TAG,"path.." +root.getAbsolutePath());  
	  
	            //check sdcard permission  
	            if (root.canWrite()){  
	                File fileDir = new File(root.getAbsolutePath());  
	                fileDir.mkdirs();  
	  
	                File file= new File(fileDir, "m.txt");  
	                FileWriter filewriter = new FileWriter(file);  
	                BufferedWriter out = new BufferedWriter(filewriter);  
	                for (String string : Imageurl.newyearsvalues) {
	                	   out.write(string+"\n");
	   			}
	                out.write(text);  
	                out.close();  
	                write_successful = true;
	            }  
	        } catch (IOException e) {  
	            Log.e("ERROR:---", "Could not write file to SDCard" + e.getMessage());  
	            write_successful = false;
	        }  
		return write_successful;
	}
	
	public String readFromSD(){
		File sdcard = Environment.getExternalStorageDirectory();
		File file = new File(sdcard,"phdcomics.txt");
		if(file.exists()){
		StringBuilder text = new StringBuilder();
		try {
		    BufferedReader br = new BufferedReader(new FileReader(file));
		    String line;
		    while ((line = br.readLine()) != null) {
		        text.append(line);
		        text.append('\n');
		    }
		    return text.toString();
		
	
		}	
		catch (IOException e) {
		}
		
		}
		else
		{
			 Happynewyearresource.getnewyearfiles(context);
	    	   MyFile f = new MyFile(context);
	    	   f.writeToSD("");
	    	return   readFromSD();
		}
		return "";

		
	}

	
}
