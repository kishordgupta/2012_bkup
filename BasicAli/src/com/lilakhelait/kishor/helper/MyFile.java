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
	  
	                File file= new File(fileDir, "basic.txt");  
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
		File file = new File(sdcard,"basic.txt");
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
				
		    	   MyFile f = new MyFile(context);
		    	   f.writeToSD("");
		    	return   readFromSD();
			}
			return "";
	}

	@SuppressLint("WorldReadableFiles")
	@SuppressWarnings("static-access")
	public Boolean writeToSandBox(String text){
		Boolean write_successful = false;
		try{
			FileOutputStream fOut = context.openFileOutput("samplefile.txt",
					context.MODE_WORLD_READABLE);
			OutputStreamWriter osw = new OutputStreamWriter(fOut); 
			osw.write(text);
			osw.flush();
			osw.close();
		}catch(Exception e){
			write_successful = false;
		}
		return write_successful;
	}
	public String readFromSandBox(){
		String str ="";
		String new_str = "";
		try{
			FileInputStream fIn = context.openFileInput("samplefile.txt");
            InputStreamReader isr = new InputStreamReader(fIn);
            BufferedReader br=new BufferedReader(isr);
           
			while((str=br.readLine())!=null)
            {
				new_str +=str;
				System.out.println(new_str);
            }            
		}catch(Exception e)
		{			
		}
		return new_str;
	}
}
