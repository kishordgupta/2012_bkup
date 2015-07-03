package com.kd.hockymain;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class Kdexcel {
	
	private static final String TAG = "s";
	public Context context;
	
	
	
	public Kdexcel(Context ctx) {
		context =ctx;	// TODO Auto-generated constructor stub
	}

	public void getfile()
	{Workbook workbook = null;
	copyfiles();
		
		  File root=null;  
		//  

		    root = Environment.getExternalStorageDirectory();  
	        try {  
	        	try {
	       		 workbook = Workbook.getWorkbook(new File(root.getAbsolutePath()+"/ans.xls"));
	       		//  String[] g= workbook.getSheetNames();
	       		//  Log.d(TAG, g[0]);
	      //sheet =  workbook.getSheet(g[0]);
	      
	       		} catch (BiffException e) {
	       			// TODO Auto-generated catch block
	       			e.printStackTrace();
	       		} catch (IOException e) {
	       			// TODO Auto-generated catch block
	       			e.printStackTrace();
	       		}
	        	
	        	 if (root.canWrite()){  
	        		 
		                File fileDir = new File(root.getAbsolutePath()+"/Hockyfiles/");  
		                fileDir.mkdirs();
		            
		              //  File file1= new File(fileDir, context.getAssets()..open("sharksscorecard2.xls"));  
	        	WritableWorkbook copy = Workbook.createWorkbook(new File(fileDir, "output.xls"), workbook);
	      //  	WritableSheet sheet1 = copy.createSheet("First Sheet", 0); 
	   //	copy.write();
	   	String[] g= workbook.getSheetNames();
	   	WritableSheet sheet = copy.getSheet(g[0]);
	    //WritableCellFeatures cellFeatures = new WritableCellFeatures();
	  
	    Label label = new Label(8, 9, "ABCD");
	  //  label.setCellFeatures(cellFeatures);
	   	try {
			sheet.addCell(label);
		} catch (RowsExceededException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	 	copy.write();
	        	//copy.removeSheet(0);
	        //	copy.importSheet("sheet",0, sheet);
	        	try {
	        		
	        		workbook.close();
					copy.close();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        //	WritableSheet sheet = copy.createSheet("First Sheet", 0); 
	        	 }
	            // check for SDcard   
	        
	  
	  
	            Log.d(TAG,"path.." +root.getAbsolutePath());  
	  
	  
//	            //check sdcard permission  
//	            if (root.canWrite()){  
//	                File fileDir = new File(root.getAbsolutePath()+"/fun/");  
//	                fileDir.mkdirs();  
//	                Log.d(TAG,"path cant .." +root.getAbsolutePath());  
//	                File file= new File(fileDir, "itisfun.txt");  
//	                FileWriter filewriter = new FileWriter(file);  
//	                BufferedWriter out = new BufferedWriter(filewriter);  
//	                out.write("I m enjoying......dude");  
//	                out.close();  
//	            }  
	        } catch (IOException e) {  
	            Log.e("ERROR:---", "Could not write file to SDCard" + e.getMessage());  
	        }  
	  
	}
	
	void copyfiles()
	{String destFile = Environment.getExternalStorageDirectory().toString().concat("/ans.xls");
try {

        File f2 = new File(destFile);
        InputStream in = context.getAssets().open("sharksscorecard2.xls");
        OutputStream out = new FileOutputStream(f2);

        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
        System.out.println("File copied.");
    } catch (FileNotFoundException ex) {
        System.out
                .println(ex.getMessage() + " in the specified directory.");
        System.exit(0);
    } catch (IOException e) {
        System.out.println(e.getMessage());
    }}

}
