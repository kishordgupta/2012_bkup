package com.lilait;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class RecordThread extends Thread{
   
	public static boolean state_record;
    URL url_record;
    Context ctx_record;
    InputStream input;
    OutputStream output ;
    File cacheDir;
    File f;
    public static String st_name=null;
    public static String url_to_rec;
   
	public RecordThread(boolean state) {
	    this.state_record=state;
	}
	public RecordThread(){}

	
	public void setURl(String uRl,String station_name){
		url_to_rec=uRl;
		st_name=station_name;
	}
	
	public String getStaionfromRecord(){
		return st_name;
	}
	public String getURLfromRecord(){
		return url_to_rec;
	}
	
	public void stopThread(){
		state_record=true;
	}
	
	@Override
	public void run() {
		
	//	while(!state_record){
			//Log.v("I am here", "SVX");
			try{
	               // checq_external_storage_mount(); 
	    		
	              //  if(mExternalStorageWriteable){
		    		cacheDir=new File(android.os.Environment.getExternalStorageDirectory(),"AllPanjabiRadio");
		    		if(!cacheDir.exists())
		    		   cacheDir.mkdirs();

		    		f=new File(cacheDir,"AllPanjabi.mp3");
		    		if(url_to_rec!=null){
		    		URL url = new URL(url_to_rec); 

		    		            input = new BufferedInputStream(url.openStream());
		    		            output = new FileOutputStream(f);

		    		            byte data[] = new byte[1024];
		    		            long total = 0;
		    		            int count=0;
		    		            while ((count = input.read(data)) != -1) {
		    		                total++;
		    		                Log.e("while","A"+total);
		    		                
	                                if(!state_record){
		    		                output.write(data, 0, count);
	                                }else{
	                                    output.flush();
	         	    		    		output.close();
	         	    		    	    input.close();
	         	    		    	   Log.e("Imdone","A"+total);
	                                	break;
	                                }
	                          
		    		            }
		    		            }
		    		           
	                }       
		    		//}
		    		catch(Exception e){e.printStackTrace();}
	         
		}
	}


