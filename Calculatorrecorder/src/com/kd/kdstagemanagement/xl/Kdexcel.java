package com.kd.kdstagemanagement.xl;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


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

	public void getfile() {
		WritableWorkbook workbook = null;
		//copyfiles();

		File root = null;
		SimpleDateFormat sdfDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US);
		 newtime =  "//"+sdfDateTime.format(new Date(System.currentTimeMillis())).replace("/", "_")+".xls";

		root = Environment.getExternalStorageDirectory();
		try {
			

			if (root.canWrite()) {

				File fileDir = new File(root.getAbsolutePath() + "/Calculatorsavefiles/");
				fileDir.mkdirs();

				// File file1= new File(fileDir,
				// context.getAssets()..open("sharksscorecard2.xls"));
				WritableWorkbook copy = Workbook.createWorkbook(new File(
						fileDir, newtime));

				copy.createSheet("YouCalculatorhistory", 0);
				WritableSheet sheet = copy.getSheet(0);

			

				try {
					Label label ;
					int r = 1;
					int c = 1;
					for (List a : CurrentActlist.currentactlists) {
						label = new Label(1, r, a.time);
						sheet.addCell(label);
						label = new Label(2, r, a.Data);
						sheet.addCell(label);
						label = new Label(3, r, a.Result);
						sheet.addCell(label);
					
						r++;
					}

				} catch (RowsExceededException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (WriteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				copy.write();

				try {

		
					copy.close();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			Log.d(TAG, "path.." + root.getAbsolutePath());

		} catch (IOException e) {
			Log.e("ERROR:---",
					"Could not write file to SDCard" + e.getMessage());
		}

	}
	String newtime ="t";
	
}
