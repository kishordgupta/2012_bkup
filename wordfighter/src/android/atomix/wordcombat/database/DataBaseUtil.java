package android.atomix.wordcombat.database;


import java.util.ArrayList;

import android.atomix.wordcombat.supports.ConstantValues;
import android.atomix.wordcombat.supports.WordCombatApp;
import android.atomix.wordcombat.supports.WordInfo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseUtil {
	
	private ArrayList<WordInfo> wordInfosList;
	private WordInfo wordInfo;
	
	private static final String TAG = "DataBaseUtil";
	private static final String DATABASE_NAME = "db_wordInfo";
	private static final String DATABASE_TABLE = "tb_wordWithMeaning";
	private static final int DATABASE_VERSION = 2;
	

	// Table Columns
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "wordName";
	public static final String KEY_MEANING = "wordMeaning";
	public static final String KEY_LANGUAGE = "language";
	//

	public String[] columns = { KEY_ROWID, KEY_NAME, KEY_MEANING, KEY_LANGUAGE };
	
	private static final String CREATE_TABLE_INFO = "create table "
			+ DATABASE_TABLE + " (" + KEY_ROWID
			+ " integer primary key autoincrement, " + KEY_NAME
			+ " text not null, " + KEY_MEANING + " text not null, " + KEY_LANGUAGE + " text not null);";

	private final Context context;

	private DatabaseHelper dbHelper;
	private SQLiteDatabase sqlDb;

	public DataBaseUtil(Context ctx) 
	{
		this.context = ctx;
	}

	public DataBaseUtil open() throws SQLException 
	{

		dbHelper = new DatabaseHelper(context);
		sqlDb = dbHelper.getWritableDatabase();

		return this;
	}

	public void close() 
	{
		sqlDb.close();
	}

	public long insertNameWithMeaning(String wordName, String wordMeaning, String language) 
	{

		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, wordName);
		cv.put(KEY_MEANING, wordMeaning);
		cv.put(KEY_LANGUAGE, language);

		return sqlDb.insert(DATABASE_TABLE, null, cv);
	}
	
	public void fetchAllWordNameWithMeaning() 
	{
		wordInfosList = new ArrayList<WordInfo>();
		
		Cursor cursor = sqlDb.query(DATABASE_TABLE, columns, null, null, null, null, KEY_ROWID + " DESC", ConstantValues.archive);
		cursor.moveToFirst();
		while(!cursor.isAfterLast())
		{
			wordInfo = new WordInfo();
			
			wordInfo.setWordName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
			wordInfo.setWordMeaning(cursor.getString(cursor.getColumnIndex(KEY_MEANING)));
			wordInfo.setLanguageName(cursor.getString(cursor.getColumnIndex(KEY_LANGUAGE)));
			
			wordInfosList.add(wordInfo);
			cursor.moveToNext();
		}
		cursor.close();
		
		WordCombatApp.getInstance().setWordInfosList(wordInfosList);
	}

	private class DatabaseHelper extends SQLiteOpenHelper 
	{

		public DatabaseHelper(Context context) 
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) 
		{
			db.execSQL(CREATE_TABLE_INFO);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
		{
			Log.w(TAG, "Upgrading Database from version " + oldVersion + " to " + newVersion + " version.");
		}
	}
}
