package com.atomix.kurowiz.xmlparser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.model.Vendor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.OpenableColumns;
import android.util.Log;


/**
 * @author robert.hinds
 *
 */
public class DBHelper extends SQLiteOpenHelper{

	//The Android's default system path of your application database.
	private static String DB_PATH = "/data/data/info.androidhive.slidingmenu/databases/";
	private static String DB_NAME = "bikramradio.sqlite";
	private static SQLiteDatabase myDataBase; 
	private static Context myContext;

	/**
	 * Constructor
	 * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
	 * @param context
	 */
	public DBHelper(Context context) {
		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}	

	/**
	 * Creates a empty database on the system and rewrites it with your own database.
	 * */
	public void createDataBase() throws IOException{

		boolean dbExist = checkDataBase();
		if(!dbExist)
		{
			//By calling this method and empty database will be created into the default system path
			//of your application so we are gonna be able to overwrite that database with our database.
			this.getReadableDatabase();

			try {
				copyDataBase(); 
			} catch (IOException e) {
				throw new Error("Error copying database");
			}
		}
	}

	/**
	 * Check if the database already exist to avoid re-copying the file each time you open the application.
	 * @return true if it exists, false if it doesn't
	 */
	private boolean checkDataBase(){
		SQLiteDatabase checkDB = null;
		try{
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		}catch(SQLiteException e){
			//database does't exist yet.
		}
		if(checkDB != null){
			checkDB.close();
		}

		return checkDB != null ? true : false;
	}

	/**
	 * Copies your database from your local assets-folder to the just created empty database in the
	 * system folder, from where it can be accessed and handled.
	 * This is done by transfering bytestream.
	 * */
	private void copyDataBase() throws IOException{

		//Open your local db as the input stream
		InputStream myInput = myContext.getAssets().open(DB_NAME);

		// Path to the just created empty db
		String outFileName = DB_PATH + DB_NAME;

		//Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		//transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer))>0){
			myOutput.write(buffer, 0, length);
		}

		//Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public static void openDataBase() throws SQLException{
		//Open the database
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
	}

	@Override
	public synchronized void close() {
		if(myDataBase != null)
			myDataBase.close();
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	// Add your public helper methods to access and get content from the database.
	// You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
	// to you to create adapters for your views.

	
	
	
	/*public static  List<Radio> gethistorySet(){
		try{
		openDataBase();
		}
		catch(SQLiteException e){}
		List<Vendor> questionSet = new ArrayList<Vendor>();
		Cursor c = myDataBase.rawQuery("SELECT * FROM history", null);
		while (c.moveToNext()){
			//Log.d("QUESTION", "Question Found in DB: " + c.getString(1));
			Vendor q = new Vendor();
			q.setVendorname(c.getString(0));
			q.setV=c.getString(2);
			q.steam=c.getString(1);
		
			questionSet.add(q);
		}
		myDataBase.close();
		return questionSet;
	}
	
	public static  List<Radio> getfavouriteset(){
		try{
			openDataBase();
			}
			catch(SQLiteException e){}
		List<Radio> questionSet = new ArrayList<Radio>();
		Cursor c = myDataBase.rawQuery("SELECT * FROM favoirite", null);
		while (c.moveToNext()){
			//Log.d("QUESTION", "Question Found in DB: " + c.getString(1));
			Radio q = new Radio();
			q.radioname=c.getString(0);
			q.image=c.getString(2);
			q.steam=c.getString(1);
		
			questionSet.add(q);
		}
		myDataBase.close();
		return questionSet;
	
	}
	
	public static void addRowfavourite(Radio radio)
	{
		try{
			openDataBase();
			}
			catch(SQLiteException e){}
	    ContentValues values = new ContentValues();
	    values.put("name", radio.radioname);
	    values.put("stramlink", radio.steam);
	    values.put("imagelink", radio.image);

	    try{myDataBase.insert("favoirite", null, values);}
	    catch(Exception e)
	    {
	        Log.e("DB ERROR", e.toString());
	        e.printStackTrace();
	    }
		myDataBase.close();
	}
	
	public static  boolean deleteTitle(String name) 
	{	try{
		openDataBase();
		}
		catch(SQLiteException e){}
	  int a =  myDataBase.delete("favoirite", "name" + "='" + name+"'", null) ;
	   myDataBase.close();
	   return a>0;
	}
	
	public  static void addRowhistoy(GiftInfo gf)
	{
		try{
			openDataBase();
			}
			catch(SQLiteException e){}
	    ContentValues values = new ContentValues();
	    values.put("name", gf.getHotel_name());
	    values.put("streamlink", gf.getHotel_link());
	    values.put("imagelink", gf.getImages());

	    try{myDataBase.insert("history", null, values);}
	    catch(Exception e)
	    {
	        Log.e("DB ERROR", e.toString());
	        e.printStackTrace();
	    }
	    myDataBase.close();
	}

	public static void addRowfavourite(GiftInfo gf) {
		// TODO Auto-generated method stub
		try{
			openDataBase();
			}
			catch(SQLiteException e){}
	    ContentValues values = new ContentValues();
	    values.put("name", gf.getHotel_name());
	    values.put("stramlink", gf.getHotel_link());
	    values.put("imagelink", gf.getImages());

	    try{myDataBase.insert("favoirite", null, values);}
	    catch(Exception e)
	    {
	        Log.e("DB ERROR", e.toString());
	        e.printStackTrace();
	    }
	    myDataBase.close();
	}*/
}