package com.kd.kdstagemanagement.database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.kd.kdstagemanagement.model.String;
import com.kd.kdstagemanagement.model.KdList;
import com.kd.kdstagemanagement.model.MovieDetails;
import com.kd.kdstagemanagement.model.Movies;
import com.kd.kdstagemanagement.model.TripsNtrivia;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



/**
 * @author robert.hinds
 *
 */
public class DBHelper extends SQLiteOpenHelper{

	//The Android's default system path of your application database.
	private static String DB_PATH = "/data/data/com.kd.dasinnovation.movie.chickflicksurvivalguide/databases/";
	private static String DB_NAME = "Movies";
	private SQLiteDatabase myDataBase; 
	private final Context myContext;

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

	public void openDataBase() throws SQLException{
		//Open the database
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
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


//	public List<Movies> getMovieset(int difficulty, int numQ){
//		List<Movies> Movieset = new ArrayList<Movies>();
//		Cursor c = myDataBase.rawQuery("SELECT * FROM Movies WHERE DIFFICULTY=" + 2 +
//				" ORDER BY RANDOM() LIMIT " + numQ, null);
//		while (c.moveToNext()){
//			//Log.d("Movies", "Movies Found in DB: " + c.getString(1));
//			Movies q = new Movies();
//			q.setMovies(c.getString(1));
//			q.setAnswer(c.getString(2));
//			q.setOption1(c.getString(3));
//			q.setOption2(c.getString(4));
//			q.setOption3(c.getString(5));
//			q.setRating(difficulty);
//			Movieset.add(q);
//		}
//		return Movieset;
//	}
	public List<String> getfilterset(){
		List<String> Movieset = new ArrayList<String>();
		Cursor c = myDataBase.query("ItemTypes",null, null, null, null, null, null);
		
		while (c.moveToNext()){
			//Log.d("Movies", "Movies Found in DB: " + c.getString(1));
			String q = new String();
			q.Filters=c.getString(2);
			q.Filtersid=c.getString(1);
			//for(int i=3;;i++)
			//{
			
			//	if(i==c.getColumnCount())
			//	{
			//		break;
			//	}
			//}
		
			Movieset.add(q);
		}
		return Movieset;
	}

	public List<MovieDetails> getMovieDetailset(){
		List<MovieDetails> Movieset = new ArrayList<MovieDetails>();
		Cursor c = myDataBase.query("MovieDetails",null, null, null, null, null, null);
		
		while (c.moveToNext()){
			//Log.d("Movies", "Movies Found in DB: " + c.getString(1));
			MovieDetails q = new MovieDetails();
			q.MovieID=c.getString(1);
			q.TypeID= KdList.Filterslist.get(Integer.parseInt(c.getString(2))-1).Filters ;
			
		    q.Time=c.getString(3);
		
			Movieset.add(q);
		}
		return Movieset;
	}
	public List<TripsNtrivia> getTriviaset(){
		List<TripsNtrivia> Movieset = new ArrayList<TripsNtrivia>();
		Cursor c = myDataBase.query("TriviaTips",null, null, null, null, null, null);
		
		while (c.moveToNext()){
			//Log.d("Movies", "Movies Found in DB: " + c.getString(1));
			TripsNtrivia q = new TripsNtrivia();
			q.ID=c.getString(0);
			q.MovieID=c.getString(1);
			q.Trivia=c.getString(2);
			
			Movieset.add(q);
		}
		return Movieset;
	}
	public List<Movies> getMovieset(){
		List<Movies> Movieset = new ArrayList<Movies>();
		Cursor c = myDataBase.query("Movies",null, null, null, null, null, null);
		
		while (c.moveToNext()){
			//Log.d("Movies", "Movies Found in DB: " + c.getString(1));
			Movies q = new Movies();
			q.Movieid=c.getString(0);
			q.Movietitle=c.getString(1);
			q.Movielength=c.getString(2);
			q.Drinkinggamelevel1="Level 1 :"+c.getString(3);
			q.Drinkinggamelevel2="Level 2 :"+c.getString(4);
			q.Drinkinggamelevel3="Level 3 :"+c.getString(5);
			q.Drinkinggamelevel4="Level 4 :"+c.getString(6);
		    q.Summmery="SUMMERY:"+c.getString(7);
			Movieset.add(q);
		}
		return Movieset;
	}
}